import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },

  server: {
    proxy: {
      // 获取路径中包含 /api 的请求
      '/api': {
        // 后台服务所在的源
        target: 'http://localhost:8080',
        changeOrigin: true, // 修改源
        // 将 /api 替换为 ''
        rewrite: (path)=>path.replace(/^\/api/,"")
      }
    }
  }
})
