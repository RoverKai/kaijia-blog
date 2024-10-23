import { createApp } from 'vue'
import './style.css'
import { createPinia } from 'pinia'
import createPersistedState from 'pinia-plugin-persistedstate'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router/index'

const pinia = createPinia()
pinia.use(createPersistedState)

createApp(App)
.use(pinia)
.use(router)
.use(ElementPlus)
.mount('#app')
