<template>
  <div id="login" class="flex justify-center items-center rounded-xl w-1/2 h-1/2 -translate-y-10 shadow-2xl -rotate-3">
    <el-form ref="formRef" :model="form" :rules="rules" label-width="auto" class="w-2/5 flex flex-col rotate-3">
      <el-form-item label="账号" prop="account">
        <el-input v-model="form.account"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="form.password"></el-input>
      </el-form-item>
      <button type="button" class="translate-y-8 rounded-lg shadow-md hover:shadow-inner" @click="handleClick">登录</button>
    </el-form>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
const formRef = ref(null)

const form = reactive({
  account: null,
  password: null,
  rePassword: null,
})
const rules = {
  account: [
    { required: true, message: '请输入账号', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' }
  ]
}
const handleClick = () => {
  formRef.value.validate(validate => {
    console.log(form)
  })
}

</script>
<style>
#login {
  background-color: #cdedde3d;
}

</style>