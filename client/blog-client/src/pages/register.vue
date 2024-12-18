<template>
  <div class="flex justify-center items-center mt-16">
    <div id="register" class=" flex rounded-lg justify-center items-center w-96 h-96 border-gray-500 shadow-2x">
      <el-form @keyup.enter="handleRegistry" ref="formRef" :model="form" :rules="rules" label-width="auto" class="w-64 flex flex-col items-center">
        <el-form-item label="账号" prop="username">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="重复密码" prop="rePassword">
          <el-input type="password" v-model="form.rePassword"></el-input>
        </el-form-item>
        <div class="flex">
          <el-form-item label="验证码" prop="code">
            <el-input v-model="form.code"></el-input>
          </el-form-item>
          <img alt="" id="captchaImage" @click="refreshImage" class="h-8 w-32">
        </div>
        <button type="button" class="translate-y-8 rounded-lg shadow-md hover:shadow-inner w-48" @keyup.enter="handleRegistry" @click="handleRegistry">注册</button>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, getCurrentInstance } from 'vue';
import { useRouter } from 'vue-router';
import { useDialog,useMessage } from 'naive-ui';

const formRef = ref(null)
const form = reactive({
  username: null,
  password: null,
  rePassword: null,
  code: null,
  uuid: null,
})
const rules = {
  username: [
    { required: true, message: '请输入账号', trigger: 'blur' },
    { max: 8, message: '最多8个字符', trigger: 'blur'}
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { max: 8, message: '最多8个字符', trigger: 'blur'}
  ],
  rePassword: [
    { required: true, message: '请重复密码', trigger: 'blur' },
    { validator: () => {
      return form.password == form.rePassword;
    },message: '两次密码输入不一致', trigger: 'blur'}
  ],
  code: [
    { required: true, message: '请输入验证码', trigger: 'blur' }
  ]
}
// 获取验证码
onMounted(() => {
  refreshImage();
})
const app = getCurrentInstance().proxy
const refreshImage = () => {
  app.$axios.get('/captchaImage').then(res => {
    document.querySelector('#captchaImage').src = `data:image/jepg;base64,${res.img}`;
    form.uuid = res.uuid
  })
}
// 注册
const router = useRouter()
const dialog = useDialog()
const messager = useMessage();
const handleRegistry = () => {
  formRef.value.validate(validate => {
    if (validate) {
      app.$axios.post('/register',form).then(res => {
        dialog.success({title: '注册成功',
        content: '转跳登录页？',
        positiveText: '确定',
        negativeText: '取消',
        onPositiveClick:() => {
          router.push('/login')
          messager.success('转跳中')
        },
        onNegativeClick:() => messager.success('╮(╯-╰)╭')
      })
      }).catch(err => {
        messager.error(err)
        form.code = null
        refreshImage()
      })
    }
  })
}

</script>
<style scoped>
#register {
  background-color: #cdedde3d;
}
</style>