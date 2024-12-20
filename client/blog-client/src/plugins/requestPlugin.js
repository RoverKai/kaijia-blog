import axios from 'axios';
import { useAuthStore } from '../stores/token';
// 调用pinia存储token

// 创建 axios 实例
const service = axios.create({
  // baseURL: 'http://8.210.105.98:80/prod-api',
  // baseURL: 'http://192.168.1.193:8080',
  // baseURL: 'http://192.168.43.84:8080',
  // baseURL: 'http://192.168.1.192:8080',
  baseURL: 'http://localhost:8080',
  timeout: 10000, // 超时时间
});

// let lastRequstTime = 0;
// const requestInterval =1000;

// 请求拦截器
service.interceptors.request.use(
  (config) => {
    // authStore.token

    // 限制请求频率
    // const currentTime = Date.now();
    // if (currentTime - lastRequstTime < requestInterval) {
    //   return Promise.reject(new Error("请求过于频繁"));
    // }
    // lastRequstTime = currentTime;
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// 响应拦截器
service.interceptors.response.use(
  (res) => {
    const authStore = useAuthStore()
    const code = res.data.code || 200;
    const msg = res.data.msg
    if (
      res.request.responseType === 'blob' ||
      res.request.responseType === 'arraybuffer'
    ) {
      return res.data;
    }
    if (code === 401) {
      authStore.clearToken()
      return Promise.reject('无效的会话，请重新登录');
    } else if (code !== 200) {
      return Promise.reject(msg);
    } else {
      return res.data;
    }
  },
  (error) => {
    console.log(error)
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
