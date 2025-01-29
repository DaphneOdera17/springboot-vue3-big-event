import axios from "axios";

import {ElMessage} from "element-plus";

// http://localhost:5173/api
const baseURL = '/api';
const instance = axios.create({baseURL});

import {useTokenStore} from "@/stores/token.js";
// 添加请求拦截器
instance.interceptors.request.use(
    (config)=>{
        // 请求前的回调
        // 添加 token
        const tokenStore = useTokenStore()
        // 判断有没有 token
        if(tokenStore.token) {
            config.headers.Authorization = tokenStore.token;
        }
        return config
    },
    (err)=>{
        // 请求错误的回调
        Promise.reject(err)
    }
)

import router from '@/router'

// 添加响应拦截器
instance.interceptors.response.use(
    result=>{
        if(result.data.code === 0) {
            return result.data;
        }

        // 操作失败
        // alert(result.data.msg ? result.data.msg : "服务异常");
        ElMessage.error(result.data.message ? result.data.message : "服务异常")
        // 将异步操作的状态转换为失败
        return Promise.reject(result.data);
    },
    err=>{
        // 判断响应状态码，如果为 401 则说明未登录
        if(err.response.status === 401){
            ElMessage.error('请先登录')
            router.push('/login')
        } else {
            ElMessage.error('服务异常');
        }

        return Promise.reject(err);
    }
)

export default instance;