<script setup>
import { ref, watch } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '../../stores/token';
import { useThemeStore } from '../../stores/theme'
import { useMessage } from 'naive-ui';

const themeStore = useThemeStore();
const authStore = useAuthStore();
const router = useRouter();
const isLogin = ref(false)
const message = useMessage();
const handleLogout = () => {
  authStore.clearToken();
  isLogin.value = false;
  message.success('登出成功')
}
watch(() => authStore.token, (newStatus) => {
  if (newStatus) {
    router.push('/home')
    isLogin.value = true;
  } else {
    isLogin.value = false
  }
})

// 切换暗色主题 
function toggleDarkMode() {
  themeStore.toggleTheme();
}
</script>

<template>
  <div class="h-16 w-full flex items-center" id="headContainner">
    <div class="w-28 font-serif md:ml-8 text-center cursor-pointer dark:text-darkColor4" @click="toggleDarkMode">
      dark theme
    </div>
    <router-link to="/home" class="font-serif font-semibold flex justify-center grow dark:text-darkColor4">xiaoYi Blog</router-link>
    <div v-if="isLogin">
      <button type="button" class="font-serif w-28 md:mr-8 dark:text-darkColor4" @click="handleLogout">logout</button>
    </div>
    <div v-else class="flex basis-28 md:justify-between md:mr-8">
      <router-link to="/login" id="loginBtn" class="text-center w-12 font-serif dark:text-darkColor4">login</router-link>
      <router-link to="/register" id="registerBtn" class="text-center w-12 font-serif dark:text-darkColor4">register</router-link>
    </div>
  </div>
  <hr class="dark:border-darkColor4">
</template>


<style>

</style>