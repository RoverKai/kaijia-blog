import axios from 'axios'
import { useAuthStore } from '../stores/token'
import { useDialog, useMessage } from 'naive-ui'
import { useRouter } from 'vue-router'


axios.defaults.headers['Content-Type'] = 'application/jason;charset=utf-8'

const service = axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_API,
  timeout: 10000
})

service.interceptors.request.use(config => {
  const isToken = (config.headers || {}).isToken === false;
  const isRepeatSubmit = (config.headers || {}).repeatSubmit === false;
  const token = useAuthStore.token
  if (token && !isToken) {
    config.headers['Authorization'] = 'Bearer ' + token;  // 设置 Authorization 头部
  }

  if (config.method === 'get' && config.params) {
    let url = config.url + '?' + tansParams(config.params);
    url = url.slice(0, -1);  // 去掉最后一个 "&"
    config.params = {};
    config.url = url;
  }

  if (!isRepeatSubmit && (config.method === 'post' || config.method === 'put')) {
    const requestObj = { 
      url: config.url, 
      data: typeof config.data === 'object' ? JSON.stringify(config.data) : config.data, 
      time: new Date().getTime() 
    };
    const sessionObj = cache.session.getJSON('sessionObj');
    const interval = 1000;  // 1秒间隔判断重复请求

    if (sessionObj && sessionObj.data === requestObj.data && 
        requestObj.time - sessionObj.time < interval && 
        sessionObj.url === requestObj.url) {
      console.warn('数据正在处理，请勿重复提交');
      return Promise.reject(new Error('数据正在处理，请勿重复提交'));
    } else {
      cache.session.setJSON('sessionObj', requestObj);
    }
  }
  return config;
}, error => {
  console.log(error);
  return Promise.reject(error);
});

service.interceptors.response.use(res => {
  const code = res.data.code || 200;  // 如果没有状态码，默认为 200
  // const msg = errorCode[code] || res.data.msg || errorCode['default'];

  if (res.request.responseType === 'blob' || res.request.responseType === 'arraybuffer') {
    return res.data;  // 如果是二进制数据，直接返回
  }
  const dialog = useDialog();
  const msg = useMessage();
  if (code === 401) {  // 未授权
    dialog.warning({
      title: '登录过期',
      content: '要重新登录吗？',
      positiveText: '确定',
      negativeText: '摆了',
      onPositiveClick: () => {
        message.success('正在转跳')
        useRouter.replace('/login')
      },
      onNegativeClick: () => {
        message.success('那咋了')
      }
    })
    // if (!isRelogin.show) {
    //   isRelogin.show = true;
      // '登录状态已过期，您可以继续留在该页面，或者重新登录', '系统提示', { 
      //   confirmButtonText: '重新登录', 
      //   cancelButtonText: '取消', 
      //   type: 'warning'
      // }
      
      // .then(() => {
      //   isRelogin.show = false;
      //   useUserStore().logOut().then(() => {
      //     location.href = '/index';  // 重定向到登录页
      //   });
      // }).catch(() => {
      //   isRelogin.show = false;
      // });
    // }
    return Promise.reject('无效的会话，请重新登录');
  } else if (code === 500) {  // 服务器错误
    // ElMessage.error(msg);
    dialog.error(msg)
    return Promise.reject(new Error(msg));
  } else if (code === 601) {  // 特定业务警告
    // ElMessage.warning(msg);
    dialog.warning(msg)
    return Promise.reject(new Error(msg));
  } else if (code !== 200) {  // 其他业务错误
    // ElNotification.error({ title: msg });
    dialog.error(msg);
    return Promise.reject('error');
  } else {
    return res.data;  // 返回成功数据
  }
}, error => {
  let { message } = error;
  if (message === 'Network Error') message = '后端接口连接异常';
  else if (message.includes('timeout')) message = '系统接口请求超时';
  else if (message.includes('Request failed with status code')) {
    message = `系统接口${message.substr(message.length - 3)}异常`;
  }
  // ElMessage.error(message);
  dialog.error(message)
  return Promise.reject(error);
});

export default service;