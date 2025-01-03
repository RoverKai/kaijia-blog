<template>
  <div class="flex justify-center items-center mt-16">
    <div id="login" class="flex rounded-lg justify-center items-center w-96 h-96 border-gray-500 shadow-2x">
      <form ref="formRef" @keyup.enter="submit" labwidth="auto" class="w-64 flex flex-col">
        <input v-model="form.username"></input>
        <input type="password" v-model="form.password"></input>
        <div class="flex">
          <input v-model="form.code" />
          <img alt="" id="captchaImage" @click="refreshImage" class="h-8 w-32">
        </div>
        <button type="button" class="translate-y-8 rounded-lg shadow-md hover:shadow-inner" @click="submit">登录</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, getCurrentInstance } from 'vue';
import { useAuthStore } from '../stores/token';
import { useRouter } from 'vue-router';
import { useMessage } from 'naive-ui';

onMounted(() => {
  refreshImage();
})
const app = getCurrentInstance().proxy
// 刷新验证码
const refreshImage = () => {
  app.$axios.get('/captchaImage').then(res => {
    document.querySelector('#captchaImage').src = `data:image/jpeg;base64,${res.img}`;
    form.uuid = res.uuid
  })
}

const formRef = ref(null)
const form = reactive({
  username: null,
  password: null,
  code: null,
  uuid: null,
})
// 登录
const router = useRouter()
const messager = useMessage();
const userInfo = useAuthStore();
const submit = () => {

  app.$axios.post('/login', form).then(res => {
    messager.success('登录成功')
    userInfo.setId(res.userid)
    userInfo.setToken(res.token)
    router.push("/home")
  }).catch(err => {
    messager.error(err)
    form.code = null
    refreshImage()
  })

}

</script>
<style>
#login {
  background-color: #cdedde3d;
}
</style>