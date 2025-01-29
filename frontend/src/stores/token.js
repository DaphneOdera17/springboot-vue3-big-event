// 定义 store
import {defineStore} from "pinia";
import {ref} from "vue";

export const useTokenStore = defineStore('token', ()=>{
    // 定义状态的内容

    // 响应式变量
    const token = ref('')

    // 定义一个函数，修改 token 的值
    const setToken = (newToken)=>{
        token.value = newToken
    }

    // 函数 移除 token 的值
    const removeToken = ()=>{
        token.value = ''
    }

    return {
        token, setToken, removeToken
    }
},
    // 持久化存储
    {
        persist: true
    }
);
