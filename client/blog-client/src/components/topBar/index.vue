<script setup>
import { getCurrentInstance, onMounted, ref, watch } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '../../stores/token';
import { useThemeStore } from '../../stores/theme'
import { useMessage } from 'naive-ui';

const themeStore = useThemeStore();
const authStore = useAuthStore();
const router = useRouter();
const isLogin = ref(null)
const isManager = ref(false)
isLogin.value = authStore.token
const message = useMessage();
const handleLogout = () => {
  authStore.clearToken();
  authStore.clearId();
  isLogin.value = false;
  message.success('登出成功')
}

// 显示post按钮
const app = getCurrentInstance().proxy

app.$axios.get('/getInfo', {
  headers: {
    Authorization: `Bearer ${authStore.token}`
  }
}).then(res => {
  isManager.value = res.roles.find(role => role === 'manager')
})

watch(() => authStore.token, (newStatus) => {
  app.$axios.get('/getInfo', {
    headers: {
      Authorization: `Bearer ${authStore.token}`
    }
  }).then(res => {
    isManager.value = res.roles.find(role => role === 'manager')
  })

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
    <div class="grow flex  justify-center">
      <router-link to="/home" class="font-serif font-semibold dark:text-darkColor4">Kaijia's
        Blog</router-link>
    </div>
    <div v-if="isLogin" class="flex basis-28 md:justify-between md:mr-8">
      <router-link v-if="isManager" to="/post" id="postBtn"
        class="text-center basis-12 font-serif dark:text-darkColor4">post</router-link>
      <button type="button" class="font-serif basis-12 flex-grow dark:text-darkColor4"
        @click="handleLogout">logout</button>
    </div>
    <div v-else class="flex basis-28 md:justify-between md:mr-8">
      <router-link to="/login" id="loginBtn"
        class="text-center basis-12 font-serif dark:text-darkColor4">login</router-link>
      <router-link to="/register" id="registerBtn"
        class="text-center basis-12 font-serif dark:text-darkColor4">register</router-link>
    </div>
  </div>
  <hr class="dark:border-darkColor4">
</template>


<style></style>