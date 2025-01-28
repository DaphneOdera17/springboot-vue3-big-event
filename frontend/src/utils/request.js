import axios from "axios";

import {ElMessage} from "element-plus";

// http://localhost:5173/api
const baseURL = '/api';
const instance = axios.create({baseURL});

// 相应拦截器
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
        alert('服务异常');
        return Promise.reject(err);
    }
)

export default instance;