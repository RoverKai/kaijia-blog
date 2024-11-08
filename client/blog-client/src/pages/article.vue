<template>
  <div class="flex grow md:min-h-screen mt-16 justify-center">
    <div v-if="!isMobile" class="basis-1/6 h-full px-4 sticky top-1/3 hidden md:block">
      <MdCatalog editorId="default" :scrollElement="scrollElement"
        class="bg-gray-100 rounded dark:bg-darkColor2 dark:text-darkColor4" />
    </div>
    <div class="w-screen md:w-2/3">
      <div class="title rounded-t-md h-12 flex justify-center items-center dark:text-darkColor4 dark:bg-darkColor3">{{
        article.title }}</div>
      <MdPreview class="w-full rounded-b-md dark:*:*:*:text-darkColor4 dark:bg-darkColor3" editorId="default"
        :modelValue="article.content" />
      <n-input-group class=" *:h-12 mt-4">
        <n-input ref="inputRef" v-model:value="commentInfo.content"
          class=" dark:bg-darkColor3 rounded-b-none rounded-tl-md dark:text-darkColor4" placeholder="怎么说..."
          maxlength="800" show-count />
        <n-button color="#a4cab6" @click="submitComment" class="dark:bg-darkColor3 dark:text-darkColor4">评价一手</n-button>
        <n-button color="#a4cab6" @click="clearComment"
          class="rounded-br-none dark:bg-darkColor3 dark:text-darkColor4">算了</n-button>
      </n-input-group>
      <div class="flex flex-col text-md rounded-b-md mb-4 min-h-12 dark:text-darkColor4" id="commentList">
        <div v-if="comments.length > 0">
          <div v-for="comment in comments" :key="comment.id" class="dark:bg-darkColor2">
            <hr class="dark:border-darkColor4">
            <div class="flex justify-between">
              <div class="ml-4 opacity-70 font-bold dark:text-darkColor4">{{ comment.userName }}:</div>
              <div class="opacity-40 mr-4 dark:text-darkColor4">{{ comment.createTime }}</div>
            </div>
            <p id="comment" class="ml-4 indent-4 dark:text-darkColor4">{{ comment.content }}</p>
          </div>
        </div>
        <div class="grow flex justify-center items-center" v-else>
          <p class="opacity-30 dark:text-darkColor1">暂无评论~</p>
        </div>
      </div>
    </div>
    <div v-if="!isMobile" class="basis-1/6 h-1/4 sticky top-1/3 justify-center hidden md:flex">
      <div class="h-36 flex flex-col *:basis-1/3">
        <div>
          <svg id="like" class="size-7 cursor-pointer dark:text-darkColor4" @click="handleLikeClick"
            :class="{ 'text-rose-400': article.isLike, 'text-gray-100': !article.isLike }"
            xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512">
            <path
              d="M462.3 62.6C407.5 15.9 326 24.3 275.7 76.2L256 96.5l-19.7-20.3C186.1 24.3 104.5 15.9 49.7 62.6c-62.8 53.6-66.1 149.8-9.9 207.9l193.5 199.8c12.5 12.9 32.8 12.9 45.3 0l193.5-199.8c56.3-58.1 53-154.3-9.8-207.9z"
              fill="currentColor"></path>
          </svg>
        </div>
        <div>
          <svg @click="toComment" class="cursor-pointer size-7 fill-current text-gray-200 dark:text-darkColor4"
            xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 16 16">
            <g fill="none">
              <path
                d="M1 4.5A2.5 2.5 0 0 1 3.5 2h9A2.5 2.5 0 0 1 15 4.5v5a2.5 2.5 0 0 1-2.5 2.5H8.688l-3.063 2.68A.98.98 0 0 1 4 13.942V12h-.5A2.5 2.5 0 0 1 1 9.5v-5zM3.5 3A1.5 1.5 0 0 0 2 4.5v5A1.5 1.5 0 0 0 3.5 11H5v2.898L8.312 11H12.5A1.5 1.5 0 0 0 14 9.5v-5A1.5 1.5 0 0 0 12.5 3h-9z"
                fill="currentColor"></path>
            </g>
          </svg>
        </div>
        <div>
          <svg @click="toTop" class="cursor-pointer text-gray-200 dark:text-darkColor4"
            xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 32 32">
            <path d="M16 14L6 24l1.4 1.4l8.6-8.6l8.6 8.6L26 24z" fill="currentColor"></path>
            <path d="M4 8h24v2H4z" fill="currentColor"></path>
          </svg>
        </div>
      </div>
    </div>
  </div>
</template>


<script setup lang="ts">
import { MdPreview, MdCatalog } from 'md-editor-v3'
import 'md-editor-v3/lib/preview.css'
import { getCurrentInstance, onMounted, onUnmounted, ref } from 'vue';
import { NInput, NButton, NInputGroup, useMessage, darkTheme } from 'naive-ui';
import { useAuthStore } from '../stores/token';

const scrollElement = document.documentElement
const props = defineProps({
  articleId: {
    type: String,
    default: 0
  }
})
const article = ref({
  id: '',
  authorId: 0,
  authorNickName: '',
  categoryId: 0,
  categoryName: '',
  content: '',
  createBy: '',
  createTime: '',
  title: '',
  isLike: false,
})
const app = getCurrentInstance().proxy
const messager = useMessage();
//加载文章
onMounted(() => {
  window.addEventListener('resize', handleResize)
  const userId = userInfo.id || 0
  app.$axios.get(`/blog/article/${props.articleId}/${userId}`).then(res => {
    article.value = res.data;
    getCommentList();
    // getArticleLike();
  }).catch(msg => {
    messager.error(msg)
  })
  app.$axios.get
})
const isMobile = ref(false)
const handleResize = () => {
  isMobile.value = window.innerWidth < 768
}

onUnmounted(() => {
  window.removeEventListener('resize', handleResize)
})

//获取评论
const comments = ref([])
const getCommentList = () => {
  app.$axios.get(`blog/comment/getCommentList?articleId=${article.value.id}`).then(res => {
    comments.value = res.rows;
    messager.success(`搜索到${res.total}条评论~`)
  }).catch(msg => console.log(msg))
}

//发送评论
const userInfo = useAuthStore();
const commentInfo = ref({
  articleId: null,
  userId: null,
  content: null,
})
const submitComment = () => {
  commentInfo.value.userId = userInfo.id
  commentInfo.value.articleId = article.value.id
  app.$axios.post('/blog/comment', commentInfo.value, {
    headers: {
      Authorization: `Bearer ${userInfo.token}`
    }
  }).then(() => {
    messager.success('发送成功')
    getCommentList();
  }).catch(err => {
    messager.error(err)
  })
}

//清空评论
const clearComment = () => {
  commentInfo.value.content = null
}

// 获取文章点赞状态
// const getArticleLike = () => {
//   app?.$axios.get(`/blog/articleLike/${article.value.id}`,{
//     headers: {
//       Authorization: `Bearer ${userInfo.token}`
//     }
//   }).then(res => {
//     console.log(res)
//   })
// }

// 点赞
const handleLikeClick = () => {
  article.value.isLike = !article.value.isLike
  if (article.value.isLike) {
    app.$axios.post(`/blog/articleLike`, {
      articleId: article.value.id,
      userId: userInfo.id
    }, { headers: { Authorization: `Bearer ${userInfo.token}` } }).catch(err => {
      messager.error(err)
      // 回滚
      article.value.isLike = !article.value.isLike
    })
  } else {
    app.$axios.delete(`/blog/articleLike/${article.value.id}/${userInfo.id}`,
      {
        headers: {
          Authorization: `Bearer ${userInfo.token}`
        }
      }).catch(err => {
        messager.error(err)
        // 回滚
        article.value.isLike = !article.value.isLike
      })
  }
}

// 转跳评论区
const inputRef = ref(null)

const toComment = () => {
  if (inputRef.value?.$el) {
    inputRef.value.$el.scrollIntoView({ behavior: 'smooth' })
    setTimeout(() => {
      inputRef.value.focus()
    }, 800)
  }
}
onUnmounted(() => {
  inputRef.value?.blur()
})

// 回到顶部
const toTop = () => {
  window.scrollTo({ top: 0, behavior: 'smooth' })
}
</script>

<style>
#title,
#commentList {
  background-color: #ecdbbf;
}
</style>