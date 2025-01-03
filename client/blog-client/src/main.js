import { createApp } from 'vue'
import './style.css'
import { createPinia } from 'pinia'
import createPersistedState from 'pinia-plugin-persistedstate'
import 'lenis/dist/lenis.css'
import requestPlugin from './plugins/requestPlugin'
import i18n from './i18n/index'
import App from './App.vue'
import router from './router/index'

// pinia
const pinia = createPinia()
pinia.use(createPersistedState)

createApp(App)
.use(pinia)
.use(router)
.use(i18n)
.use(requestPlugin)
.mount('#app')
