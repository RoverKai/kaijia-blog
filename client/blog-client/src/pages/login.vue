<template>
  <div class="flex justify-center items-center mt-16">
    <div id="login"
      class="flex rounded-lg justify-center items-center w-96 h-96 border-gray-500 shadow-2x">
      <el-form ref="formRef" @keyup.enter="submit" :model="form" :rules="rules" label-width="auto"
        class="w-64 flex flex-col">
        <el-form-item label="账号" prop="username">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="form.password"></el-input>
        </el-form-item>
        <div class="flex">
          <el-form-item label="验证码" prop="code">
            <el-input v-model="form.code" />
          </el-form-item>
          <img alt="" id="captchaImage" @click="refreshImage" class="h-8 w-32">
        </div>
        <button type="button" class="translate-y-8 rounded-lg shadow-md hover:shadow-inner" @click="submit">登录</button>
      </el-form>
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

const rules = {
  username: [
    { required: true, message: '请输入账号', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' }
  ],
  code: [
    { required: true, message: '请输入验证码', trigger: 'blur' }
  ]
}
// 登录
const router = useRouter()
const messager = useMessage();
const userInfo = useAuthStore();
const submit = () => {
  formRef.value.validate(validate => {
    if (validate) {
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
  })
}

</script>
<style>
#login {
  background-color: #cdedde3d;
}
</style>