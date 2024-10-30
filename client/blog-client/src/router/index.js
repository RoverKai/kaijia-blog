import { createRouter, createWebHistory } from "vue-router";
import home from "../pages/home.vue";
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
    path: '/article/:articleId',
    component: import("../pages/article.vue"),
    props: true
  },
  //login
  {
    path: '/login',
    component: import("../pages/login.vue")
  },
  //log up
  {
    path: '/register',
    component: import("../pages/register.vue")
  }
]

const router = createRouter(
  {
    history: createWebHistory(),
    routes
  }
)

export default router;