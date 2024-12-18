<script setup>
import { getCurrentInstance, onMounted, reactive, ref, watch } from 'vue';
import { MdEditor } from 'md-editor-v3';
import 'md-editor-v3/lib/style.css'
import { useThemeStore } from '../stores/theme';
import { useAuthStore } from '../stores/token';
import { useMessage } from 'naive-ui';
import { useRouter } from 'vue-router';

// theme
const themeStore = useThemeStore()

// init
const article = reactive({
  title: '',
  content: '',
  status: 'draft',
  categoryId: 0,
})
const proxy = getCurrentInstance().proxy
const token = useAuthStore().token
const message = useMessage()
const router = useRouter()

// validate and submit
function submit() {
  if (!validateForm()) {
    return
  }
  proxy.$axios.post('/blog/article', article, {
    headers: {
      Authorization: `Bearer ${token}`
    }
  }).then(msg => {
    message.success('操作成功')
    reset()
    router.push('/home')
  }).catch(err => {
    message.error(err)
  })
}

import { validate } from '../utils/article';
function validateForm() {
  if (!validate(article.content)) {
    message.error('内容不能为空')
    return false
  }
  if (!validate(article.title)) {
    message.error('文章标题不能为空')
    return false
  }
  return true
}

// article operate
function saveArticle() {
  article.status = 'draft'
  submit()
}

function submitArticle() {
  article.status = 'published'
  submit()
}

const reset = () => {
  article.title = '',
    article.content = '',
    article.status = 'draft',
    article.categoryId = 0
}

// query article type option
const articleTypeOption = ref([])
onMounted(() => {
  proxy.$axios.get('blog/category/list')
    .then(res => {
      articleTypeOption.value = res.rows
      console.log(articleTypeOption)
    })
})

function handleArticleTypeChange(categoryId) {
  article.categoryId = categoryId
}
</script>

<template>
  <div class="h-screen w-full">
    <div class="flex justify-center">
      <div class=" h-12 flex items-center *:border w-24 justify-around">
        <button @click="saveArticle">保存</button>
        <button @click="submitArticle">发布</button>
      </div>

    </div>
    <div class="h-full flex flex-col">
      <input type="text" class="basis-12 text-center text-lg" placeholder="标题" v-model="article.title">
      <md-editor v-model="article.content" :theme="themeStore.isDark ? 'dark' : 'default'" class="w-full h-2/3" />
      <ul>
        <li v-for="option in articleTypeOption" :key="option.id" class="*:cursor-pointer">
          <input type="radio" name="articleTypeGroup" :value="option.id" v-model="article.categoryId">
          <span @click="handleArticleTypeChange(option.id)">{{ option.name }}</span>
        </li>
      </ul>
    </div>
  </div>
</template>

<style scoped></style>