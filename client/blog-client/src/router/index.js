import { createRouter, createWebHistory } from "vue-router";
import intro from "../pages/intro.vue";
const routes = [
  //重定向
  { 
    path: '/',
    redirect: '/intro'
  },
  // intro
  { 
    path: '/intro',
    component: intro
  },
  //主页
  {
    path: '/home',
    component: () => import("../pages/home.vue")
  },
  //文章详情页
  {
    path: '/article/:articleId',
    component: () => import("../pages/article.vue"),
    props: true
  },
  //login
  {
    path: '/login',
    component: () => import("../pages/login.vue")
  },
  //log up
  {
    path: '/register',
    component: () => import("../pages/register.vue")
  },
  //post
  {
    path: '/post',
    component: () => import('../pages/post.vue')
  }
]

const router = createRouter(
  {
    history: createWebHistory(),
    routes
  }
)

export default router;