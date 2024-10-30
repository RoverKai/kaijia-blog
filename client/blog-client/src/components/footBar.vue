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
  <hr v-if="!isMobileDevice">
  <div class="flex flex-col items-center md:flex md:flex-row md:justify-around" id="footbar">
    <hr class=" w-2/3" v-if="isMobileDevice">
    <div class=" font-serif flex flex-col items-center text-center w-1/5 md:mt-4">
      contact me:
      <div>
        2196140098@qq.com
      </div>
      <div>
        roverkai007@gmail.com
      </div>
    </div>
    <hr class=" w-3/4" v-if="isMobileDevice">
    <div class="font-serif text-center  w-3/4">
      <div class=" flex justify-around *:flex *:flex-col md:mt-4">
        <div>
          <a href="https://cn.vuejs.org/">vue</a>
          <a href="https://www.naiveui.com/zh-CN/os-theme">naive-ui</a>
          <a href="https://element-plus.org/zh-CN/">element-plus</a>
        </div>
        <div>
          <a href="https://ruoyi.csdn.net/">ruoyi</a>
          <a href="https://spring.io/projects/spring-boot">SpringBoot</a>
          <a href="https://imzbf.github.io/md-editor-v3/zh-CN/index">md-editor-v3</a>
        </div>
        <div>
          <a href="https://tailwindcss.com/">tailwindCSS</a>
          <a href="https://pinia.vuejs.org/zh/">pinia</a>
          <a href="https://dshuais-danmaku.netlify.app/">danmaku</a>
        </div>
      </div>
    </div>
  </div>
</template>

<style></style>