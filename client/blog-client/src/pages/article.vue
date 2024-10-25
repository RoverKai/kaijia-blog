<script setup>
import { MdPreview } from 'md-editor-v3'
import 'md-editor-v3/lib/preview.css'
import { getCurrentInstance, onMounted, ref } from 'vue';
import { NInput, NButton, NInputGroup, NFlex } from 'naive-ui';

const props = defineProps(['articleId'])
const article = ref({})
const app = getCurrentInstance().proxy
onMounted(() => {
  app.$axios.get(`/blog/article/${props.articleId}`).then(res => {
    article.value = res.data.data;
  })
})

const comment = ref(null)
</script>

<template>
  <div>
    <div class="title rounded-t-md h-12 flex justify-center items-center">{{ article.title }}</div>
    <MdPreview class="" editorId="default" :modelValue="article.content" />
    <n-input-group class=" *:h-12">
      <n-input v-model="comment" class="rounded-none " placeholder="怎么说..." maxlength="800" show-count/>
      <n-button color="#a4cab6" >评价一手</n-button>
      <n-button color="#a4cab6" >算了</n-button>
    </n-input-group>
    <div class="min-h-24" id="comment">
      <!-- <svg class="size-4" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 12 12"><g fill="none"><path d="M5.656 2.737a2.394 2.394 0 0 0-3.447-.01c-.95.975-.945 2.559.01 3.537l3.53 3.623c.146.15.384.15.53 0l3.513-3.602a2.548 2.548 0 0 0-.01-3.535a2.395 2.395 0 0 0-3.45-.009l-.336.345l-.34-.35z" fill="currentColor"></path></g></svg> -->
      <div>username</div>
      <div></div>
    </div>
  </div>
</template>

<style>
#comment {
  background-color: #f0c9cf;
}

#commentArea {
  background-color: #e6d2d5;
}
</style>