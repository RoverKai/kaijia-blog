<template>
  <danmaku :danmus="danmuList" class="absolute w-full h-screen top-16" />
  <div class="flex justify-center flex-wrap items-center h-screen md:min-h-screen">
    <div class="w-2/3 flex flex-col items-center">
      <n-input-group id="searchBar" class="mb-12 *:rounded-md min-w-96 w-2/3">
        <n-dropdown trigger="hover" class="dark:bg-darkColor4" :options="categoryList" @select="handleCategory">
          <n-button :theme="darkTheme" class="dark:text-darkColor4">{{ categoryBtnName }}</n-button>
        </n-dropdown>
        <n-input class="text-center dark:text-darkColor4" :theme="darkTheme" @keyup.enter="handleSearch" placeholder="比旁边的按钮更直接"
          v-model:value="queryParams.content" />
        <n-button @click="handleSearch" :theme="darkTheme" class="dark:text-darkColor4">搜索</n-button>
        <n-button @click="sendDanmu" :theme="darkTheme" class="dark:text-darkColor4">发送弹幕</n-button>
      </n-input-group>
      <div class="h-52 md:h-48 w-screen md:w-2/3">
        <n-infinite-scroll :distance="2" @load="handleLoad">
          <div @click="toDetail(article.id)" v-for="(article, index) in articles" :key="index" id="article"
            class="cursor-pointer rounded h-8 w-full shadow-xl mb-4 md:mb-3 last:mb-0 dark:bg-darkColor2">
            <div class="title rounded text-center flex flex-col">
              <div class="flex dark:bg-darkColor2 items-center justify-between *:flex *:items-center *:mr-2 *:*:mr-1">
                <div class="font-bold basis-64 dark:text-darkColor4">《{{ article.title }}》</div>
                <div class="dark:text-darkColor4">
                  <svg class="size-4" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                    viewBox="0 0 576 512">
                    <path
                      d="M572.52 241.4C518.29 135.59 410.93 64 288 64S57.68 135.64 3.48 241.41a32.35 32.35 0 0 0 0 29.19C57.71 376.41 165.07 448 288 448s230.32-71.64 284.52-177.41a32.35 32.35 0 0 0 0-29.19zM288 400a144 144 0 1 1 144-144a143.93 143.93 0 0 1-144 144zm0-240a95.31 95.31 0 0 0-25.31 3.79a47.85 47.85 0 0 1-66.9 66.9A95.78 95.78 0 1 0 288 160z"
                      fill="currentColor"></path>
                  </svg>
                  <div>{{ article.viewCount }}</div>
                </div>
                <div class="dark:text-darkColor4">
                  <svg class="size-4" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                    viewBox="0 0 12 12">
                    <g fill="none">
                      <path
                        d="M5.656 2.737a2.394 2.394 0 0 0-3.447-.01c-.95.975-.945 2.559.01 3.537l3.53 3.623c.146.15.384.15.53 0l3.513-3.602a2.548 2.548 0 0 0-.01-3.535a2.395 2.395 0 0 0-3.45-.009l-.336.345l-.34-.35z"
                        fill="currentColor"></path>
                    </g>
                  </svg>
                  <div>{{ article.likeCount }}</div>
                </div>
                <div class="dark:text-darkColor4">
                  <svg class="size-4" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                    viewBox="0 0 24 24">
                    <path opacity=".3" d="M5 8h14V6H5z" fill="currentColor"></path>
                    <path
                      d="M7 11h2v2H7zm12-7h-1V2h-2v2H8V2H6v2H5c-1.11 0-1.99.9-1.99 2L3 20a2 2 0 0 0 2 2h14c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2zm0 16H5V10h14v10zm0-12H5V6h14v2zm-4 3h2v2h-2zm-4 0h2v2h-2z"
                      fill="currentColor"></path>
                  </svg>
                  <div class="font-serif">{{ article.createTime.substr(0, 10) }}</div>
                </div>
              </div>
            </div>
          </div>
        </n-infinite-scroll>
      </div>
    </div>
  </div>
</template>


<script setup>
import { ref, onMounted, getCurrentInstance, watch, onUnmounted } from 'vue';
import { useRouter } from 'vue-router';
import { NInfiniteScroll, NInput, NInputGroup, NButton, NDropdown, useMessage, darkTheme } from 'naive-ui';
import Danmaku from 'danmaku-vue'
import { useAuthStore } from '../stores/token';
const articles = ref([])
const proxy = getCurrentInstance().proxy;
const danmuList = ref(['666'])
const danmuContent = ref('')
const userInfo = useAuthStore();
// const {isMobile} = defineProps({
//   isMobile
// })

// 初始化
onMounted(() => {
  handleLoad()
  listCategory()
  getDanmuList()
  // window.addEventListener('resize', handleResize);
})

// onUnmounted(() => {
//   window.removeEventListener('resize', handleResize)
// })

// 适配移动设备
// const isMobile = ref(false)
// const handleResize = () => {
//   isMobile.value = window.innerWidth < 768
// }

// 获取弹幕列表
const getDanmuList = () => {
  proxy.$axios.get('/blog/danmu/list').then(res => {
    res.map(danmu => {
      danmuList.value.push(danmu.content)
    })
  }).catch(err => messager.error(err))
}

// 获取文章分类数据
const categoryList = ref([{ label: '你喜欢什么类型的？', disabled: true }])
const listCategory = () => {
  proxy.$axios.get('/blog/category/list').then(res => {
    const data = res.rows;
    categoryList.value.push(...data.map(category => {
      return { key: category.id, label: category.name }
    }))
  })
}

// 文章查询参数
const queryParams = ref({
  pageNum: 1,
  pageSize: 5,
  categoryId: null,
  title: '',
  content: ''
})

// 分类查询
const categoryBtnName = ref('分类')
const handleCategory = categoryId => {
  queryParams.value = {
    pageNum: 1,
    pageSize: 5,
    categoryId: categoryId
  }
  categoryBtnName.value = categoryList.value.find(category => category.key === categoryId).label
  proxy.$axios.get('/blog/article/getList4User', { params: queryParams.value }).then(res => {
    if (res.code === 200) {
      messager.success('分类查询成功')
      articles.value = res.rows
    } else {
      messager.error(res.msg)
    }
  })
}
// 文章模糊搜索
const handleSearch = () => {
  proxy.$axios.get('/blog/article/getList4User', { params: queryParams.value }).then(res => {
    articles.value = res.rows
    messager.success(`搜索到${res.total}条数据~`)
  }).catch(err => messager.error(err))
}
// 加载文章列表
const messager = useMessage()
const handleLoad = () => {
  proxy.$axios.get('/blog/article/getList4User', { params: queryParams.value }).then(res => {
    articles.value = res.rows
    queryParams.value.pageSize++;
  }).catch(err => messager.error(err))
}

// 发送弹幕
watch(queryParams, (newVal, oldVal) => {
  danmuContent.value = newVal.content;
  queryParams.value = newVal;
}, {
  deep: true
})
const sendDanmu = () => {
  if (danmuContent.value.length > 0) {
    proxy.$axios.post('/blog/danmu', {
      userId: userInfo.id,
      content: danmuContent.value
    }, {
      headers: {
        Authorization: `Bearer ${userInfo.token}`
      }
    }).then(
      () => {
        danmuList.value.push(danmuContent.value)
        messager.success('发送成功！')
        danmuContent.value = '';
      }
    ).catch(err => {
      messager.error(err)
    })
  } else {
    messager.error('还没输入内容喔')
  }
}
// 进入详情页
const router = useRouter();
const toDetail = articleId => {
  router.push(`/article/${articleId}`)
}
</script>

<style>
#article {
  background-color: #cdedde;
}

.title {
  background-color: #cdedde;
}

#searchBar {
  width: 30rem;
}
</style>