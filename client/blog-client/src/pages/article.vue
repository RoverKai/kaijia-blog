<template>
  <div class="flex justify-center items-center grow md:min-h-screen mt-16">
    <div class="w-screen md:w-2/3">
      <div class="title rounded-t-md h-12 flex justify-center items-center">{{ article.title }}</div>
      <MdPreview class="w-full rounded-b-md" editorId="default" :modelValue="article.content" />
      <n-input-group class=" *:h-12 mt-4">
        <n-input v-model:value="commentInfo.content" class=" rounded-b-none rounded-tl-md" placeholder="怎么说..." maxlength="800"
        show-count />
        <n-button color="#a4cab6" @click="submitComment" class="">评价一手</n-button>
        <n-button color="#a4cab6" @click="clearComment" class=" rounded-br-none">算了</n-button>
      </n-input-group>
      <!-- <svg class="size-4" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 12 12"><g fill="none"><path d="M5.656 2.737a2.394 2.394 0 0 0-3.447-.01c-.95.975-.945 2.559.01 3.537l3.53 3.623c.146.15.384.15.53 0l3.513-3.602a2.548 2.548 0 0 0-.01-3.535a2.395 2.395 0 0 0-3.45-.009l-.336.345l-.34-.35z" fill="currentColor"></path></g></svg> -->
      <div class="flex flex-col text-md rounded-b-md mb-4 min-h-12" id="commentList">
        <div v-if="comments.length > 0">
          <div v-for="comment in comments" :key="comment.id">
            <hr>
            <div class=" flex justify-between">
              <div class="ml-4 opacity-70 font-bold">{{ comment.userName }}:</div>
              <div class=" opacity-40 mr-4">{{ comment.createTime }}</div>
            </div>
            <p id="comment" class="ml-4 indent-4">{{ comment.content }}</p>
          </div>
        </div>
        <div class="grow flex justify-center items-center" v-else>
          <p class=" opacity-30">暂无评论~</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { MdPreview } from 'md-editor-v3'
import 'md-editor-v3/lib/preview.css'
import { getCurrentInstance, onMounted, ref } from 'vue';
import { NInput, NButton, NInputGroup, useMessage } from 'naive-ui';
import { useAuthStore } from '../stores/token';

const props = defineProps(['articleId'])
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
})
const app = getCurrentInstance().proxy
const messager = useMessage();
//加载文章
onMounted(() => {
  app.$axios.get(`/blog/article/${props.articleId}`).then(res => {
    article.value = res.data;
    getCommentList();
  }).catch(msg => {
    messager.error(msg)
  })
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
  app.$axios.post('/blog/comment',commentInfo.value,{
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
</script>

<style>
#title,
#commentList {
  background-color: #ecdbbf;
}


</style>