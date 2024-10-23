<script setup>

</script>

<template>
  <div>
    <div class="h-16 flex *:flex items-center" id="headContainner">
      <div class="basis-8"></div>
      <div>
        切换主题
      </div>
      <router-link to="/home" class=" font-serif font-semibold grow flex justify-center">kaijia's blog</router-link>
      <div class="flex basis-24 justify-between">
        <router-link to="/login">登录</router-link>
        <router-link to="/register">注册</router-link>
      </div>
      <div class="basis-8"></div>
    </div>
  </div>
</template>

<style>

</style>