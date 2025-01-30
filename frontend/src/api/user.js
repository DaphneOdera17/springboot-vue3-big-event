// 导入 request.js 请求工具
import request from "@/utils/request.js";

// 注册
export const userRegisterService = (registerData)=>{
    const params = new URLSearchParams()
    for(let key in registerData) {
        params.append(key, registerData[key])
    }

    return request.post('/user/register', params)
}

// 登录
export const userLoginService = (loginData)=>{
    const params = new URLSearchParams()
    for(let key in loginData) {
        params.append(key, loginData[key])
    }

    return request.post('/user/login', params)
}

// 获取用户详细信息
export const userInfoService = ()=>{
    return request.get('/user/userInfo')
}

// 更新用户信息
export const userInfoUpdateService = (userInfoData)=>{
    return request.put('/user/update', userInfoData)
}

// 更新用户头像
export const userAvatarService = (avatarUrl)=>{
    const params = new URLSearchParams()
    params.append('avatarUrl', avatarUrl)
    return request.patch('/user/updateAvatar', params)
}

// 更新用户密码
export const userPasswordUpdateService = (passwordData)=>{
    return request.patch('/user/updatePwd', passwordData)
}