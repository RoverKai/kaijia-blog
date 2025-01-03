import { createI18n } from 'vue-i18n'

export default createI18n({
  locale: 'en',
  fallbackLocale: 'ch',
  messages: {
    ch: {
      ch: '中文',
      en: 'English',
      button: {
        logo: '凯佳同学的博客',
        login: '登录',
        register: '注册',
        themeToggle: '主题',
        contactMe: '联系我'
      },
      content: {
        
        
      }
    },
    en: {
      en: 'English',
      ch: '中文',
      button: {
        logo: "Kaijia's Blog",
        login: 'Login',
        register: 'Register',
        themeToggle: 'Theme',
        contactMe: 'contact me'
      }
    }
  }
})