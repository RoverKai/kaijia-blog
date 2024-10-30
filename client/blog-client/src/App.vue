<script setup>
import TopBar from './components/topBar/index.vue';
import FootBar from './components/footBar.vue';
import { NMessageProvider,NDialogProvider } from 'naive-ui';
</script>

<template>
  <!-- 弹窗和消息组件 -->
  <n-dialog-provider>
    <n-message-provider>
      <!-- 主容器 -->
      <div id="main" class="flex flex-col items-center min-h-screen min-w-96 *:w-full">
        <div class="">
          <top-bar></top-bar>
        </div>
        <!-- 内容 -->
        <div class="">
          <router-view></router-view>
        </div>
        <div class="">
          <foot-bar></foot-bar>
        </div>
      </div>
    </n-message-provider>
  </n-dialog-provider>
</template>

<style scoped>
#main {
  background-color: #6f9986;
}
</style>
