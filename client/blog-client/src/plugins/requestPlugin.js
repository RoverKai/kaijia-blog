import axios from 'axios';
import { useDialog, useMessage } from 'naive-ui';
import { useRouter } from 'vue-router';

// 创建 axios 实例
const service = axios.create({
  baseURL: 'http://localhost:8080', // 后端地址
  timeout: 10000, // 超时时间
});

// 请求拦截器
service.interceptors.request.use(
  (config) => {
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// 响应拦截器
service.interceptors.response.use(
  (res) => {
    const code = res.data.code || 200;
    const msg = res.data.msg
    if (
      res.request.responseType === 'blob' ||
      res.request.responseType === 'arraybuffer'
    ) {
      return res.data;
    }
    console.log(res)
    if (code === 401) {
      return Promise.reject('无效的会话，请重新登录');
    }else if (code !== 200) {
      return Promise.reject(msg);
    } else {
      return res.data;
    }
  },
  (error) => {
    let message = error.message;
    if (message === 'Network Error') message = '后端接口连接异常';
    else if (message.includes('timeout')) message = '系统接口请求超时';
    else if (message.includes('Request failed with status code')) {
      message = `系统接口${message.slice(-3)}异常`;
    }
    return Promise.reject(message);
  }
);

// 插件注册
export default {
  install(app) {
    app.config.globalProperties.$axios = service; // 将 axios 实例挂载到全局
  },
};
