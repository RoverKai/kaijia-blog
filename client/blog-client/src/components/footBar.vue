<script setup>
import { onMounted, ref } from 'vue';

// 移动端适配
const width = window.innerWidth
const isMobileDevice = ref(false)
window.onresize = () => {
  isMobileDevice.value = window.innerWidth < 768
}
onMounted(() => {
  if (width < 768) {
    isMobileDevice.value = true
  }
})


</script>

<template>
  <hr v-if="!isMobileDevice" class="dark:border-darkColor4">
  <div class="flex flex-col items-center md:flex md:flex-row md:justify-around" id="footbar">
    <hr class="w-2/3" v-if="isMobileDevice">
    <div class="font-serif flex flex-col items-center text-center w-1/5 md:mt-4 dark:text-darkColor4">
      {{ $t('button.contactMe') }}
      <div class="dark:text-darkColor4">
        2196140098@qq.com
      </div>
      <div class="dark:text-darkColor4">
        rover.kai007@gmail.com
      </div>
    </div>
    <hr class="w-3/4" v-if="isMobileDevice">
    <div class="font-serif text-center w-3/4 dark:text-darkColor4">
      <div class="flex justify-around *:flex *:flex-col md:mt-4">
        <div class="dark:text-darkColor4">
          <a href="https://cn.vuejs.org/" class="dark:text-darkColor4">vue</a>
          <a href="https://www.naiveui.com/zh-CN/os-theme" class="dark:text-darkColor4">naive-ui</a>
          <a href="https://element-plus.org/zh-CN/" class="dark:text-darkColor4">element-plus</a>
        </div>
        <div class="dark:text-darkColor4">
          <a href="https://ruoyi.csdn.net/" class="dark:text-darkColor4">ruoyi</a>
          <a href="https://spring.io/projects/spring-boot" class="dark:text-darkColor4">SpringBoot</a>
          <a href="https://imzbf.github.io/md-editor-v3/zh-CN/index" class="dark:text-darkColor4">md-editor-v3</a>
        </div>
        <div class="dark:text-darkColor4">
          <a href="https://tailwindcss.com/" class="dark:text-darkColor4">tailwindCSS</a>
          <a href="https://pinia.vuejs.org/zh/" class="dark:text-darkColor4">pinia</a>
          <a href="https://dshuais-danmaku.netlify.app/" class="dark:text-darkColor4">danmaku</a>
        </div>
      </div>
    </div>
  </div>
</template>


<style></style>