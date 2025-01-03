<template>
  <!-- 弹窗和消息组件 -->
  <n-config-provider>
    <n-dialog-provider>
      <n-message-provider>
        <!-- 主容器 -->
        <div id = "main"
          class = "flex flex-col items-center min-h-screen min-w-96 *:w-full bg-backgroundColor1 dark:bg-darkColor1">
          <loading ref="loading" @checkLoading="checkLoading"/>
          <div class="fixed z-10">
            <top-bar></top-bar>
          </div>
          <!-- 内容 -->
          <div class="grow min-w-96">
            <router-view></router-view>
          </div>
          <div>
            <foot-bar></foot-bar>
          </div>
        </div>
      </n-message-provider>
    </n-dialog-provider>
  </n-config-provider>
</template>

<script setup>
import TopBar from './components/topBar/index.vue';
import FootBar from './components/footBar.vue';
import { NMessageProvider, NDialogProvider, NConfigProvider } from 'naive-ui';
import Loading from './components/loading/index.vue'
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const loading = ref()

function checkLoading() {
  let timer = setInterval(() => {
    if (document.readyState === 'complete') {
      clearInterval(timer)
      loading.value.moveOut()
    }
  }, 300)
}

checkLoading()
let router = useRouter()
router.beforeEach((to, form, next) => {
  isPathToIntro(to.fullPath)
  loading.value.moveIn(next)
})

const introMode = ref(true)
function isPathToIntro(path) {
  introMode.value = (path === '/intro')
}

import Lenis from 'lenis'
const lenis = new Lenis({
  autoRaf: true,
  smoothTouch: true
});
</script>

<style scoped></style>
