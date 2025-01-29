import './assets/main.scss'

import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from '@/router'
import {createPinia} from "pinia";
import {createPersistedState} from "pinia-persistedstate-plugin";

import App from './App.vue'

const pinia = createPinia()
const app = createApp(App);
const persist = createPersistedState()
pinia.use(persist)

app.use(pinia).
    use(router).
    use(ElementPlus).
    mount('#app');
