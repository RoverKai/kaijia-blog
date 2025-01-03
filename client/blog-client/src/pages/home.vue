<template>
  <div class="flex flex-col justify-center items-center">
    
  </div>
</template>


<script setup>
import { ref, onMounted, getCurrentInstance, watch, onUnmounted } from 'vue';
import { useRouter } from 'vue-router';
import { useMessage } from 'naive-ui';
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
    queryParams.value.pageSize += 3;
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