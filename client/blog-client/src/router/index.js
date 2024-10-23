import { createMemoryHistory, createRouter, createWebHistory } from "vue-router";
import home from "../pages/home.vue";
import article from "../pages/article.vue";
import login from "../pages/login.vue";
import regiter from "../pages/register.vue";
const routes = [
  //重定向
  { 
    path: '/',
    redirect: '/home'
  },
  //主页
  {
    path: '/home',
    component: home
  },
  //文章详情页
  {
    path: '/article',
    component: article
  },
  //login
  {
    path: '/login',
    component: login
  },
  //log up
  {
    path: '/register',
    component: regiter
  },
  // 404 页面
  {
    path: '/:pathMatch(.*)*',
    component: () => import('../pages/NotFound.vue') // 动态导入404页面
  }
]

const router = createRouter(
  {
    history: createWebHistory(),
    routes
  }
)

export default router;