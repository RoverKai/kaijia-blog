<template>
  <div class="h-16 w-full flex items-center bg-backgroundColor1 dark:bg-darkColor1" id="headContainner">
    <div class="basis-52 md:ml-8 flex justify-between">
      <div class="basis-12 text-2xl font-semibold text-center cursor-pointer text-darkColor4" @click="toggleDarkMode">
        {{ $t('button.themeToggle') }}
      </div>
      <div class="locale-changer basis-12">
        <select v-model="$i18n.locale" class="font-semibold text-2xl bg-backgroundColor1 dark:bg-darkColor1 text-darkColor4">
          <option v-for="locale in $i18n.availableLocales" :key="`locale-${locale}`" :value="locale">{{ $t(locale) }}
          </option>
        </select>
      </div>
    </div>
    <div class="grow flex justify-center">
      <router-link to="/home" class="text-2xl font-semibold text-darkColor4">{{ $t('button.logo')
        }}</router-link>
    </div>
    <div v-if="isLogin" class="flex basis-52 md:justify-between md:mr-8">
      <router-link v-if="isManager" to="/post" id="postBtn"
        class="text-center basis-12  text-darkColor4">post</router-link>
      <button type="button" class=" basis-12 flex-grow text-darkColor4"
        @click="handleLogout">logout</button>
    </div>
    <div v-else class="flex basis-52 md:justify-between md:mr-8">
      <router-link to="/login" id="loginBtn" class="text-center basis-12 text-2xl font-semibold text-darkColor4">{{
        $t('button.login') }}</router-link>
      <router-link to="/register" id="registerBtn" class="text-center text-2xl font-semibold basis-12  text-darkColor4">{{
        $t('button.register') }}</router-link>
    </div>
  </div>
  <hr class="border-darkColor4">
</template>

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




<style>

</style>