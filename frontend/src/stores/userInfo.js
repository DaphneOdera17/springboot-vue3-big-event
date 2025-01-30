import {defineStore} from "pinia";
import {ref} from "vue";

const useUserInfoStore = defineStore('userInfo', ()=>{
    const info = ref({})

    const setInfo = (newInfo)=>{
        info.value = newInfo
    }

    const removeInfo = ()=>{
        info.value = {}
    }

    return {info, setInfo, removeInfo}

},// 持久化存储
{
    persist: true
})

export default useUserInfoStore;