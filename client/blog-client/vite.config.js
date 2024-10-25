import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  // VITE_APP_BASE_API : 'localhost:8080/',
  server: {
    host: '0.0.0.0'
  }
})
