<script setup>
import { User, Lock } from '@element-plus/icons-vue'
import { ref } from 'vue'
import {ElMessage} from "element-plus";

//控制注册与登录表单的显示， 默认显示注册
const isRegister = ref(false)

const registerData = ref({
  username: "",
  password: "",
  rePassword: ""
})

const checkRePassword = (rule, value, callback)=>{
  if(value === '') {
    callback(new Error('请输入确认密码'))
  } else if(value !== registerData.value.password) {
    callback(new Error('请确保两次输入的密码一样'))
  } else {
    callback()
  }
}

// 表单校验规则
const rules = {
  username: [
    {required: true, message: "请输入用户名", trigger: "blur"},
    {min:5, max: 16, message: "长度为 5~16 位非空字符", trigger: "blur"}
  ],
  password: [
    {required: true, message: "请输入密码", trigger: "blur"},
    {min:5, max: 16, message: "长度为 5~16 位非空字符", trigger: "blur"}
  ],
  rePassword: [
    {validator: checkRePassword, trigger: 'blur'}
  ]
}

import {userRegisterService, userLoginService} from "@/api/user.js";
const register = async ()=>{
  // 先对表单进行校验
  form.value.validate(async (valid) => {
    if (valid) {
      // 表单校验通过，调用注册接口
      let result = await userRegisterService(registerData.value);
      ElMessage.success(result.message ? result.message : "注册成功");
    } else {
      // 表单校验不通过，提示用户
      ElMessage.error("请检查表单输入是否正确");
      return false;
    }
  });
}

import {useTokenStore} from "@/stores/token.js";
import {useRouter} from "vue-router";
const router = useRouter();
const tokenStore = useTokenStore()
// 登录
const login = async ()=>{
  let result = await userLoginService(registerData.value)
  // if(result.code === 0) {
  //   alert(result.msg ? result.msg : "登录成功")
  // } else {
  //   alert("登录失败")
  // }
  // alert(result.msg ? result.msg : "登录成功")
  ElMessage.success(result.message ? result.message : "登录成功")

  // 把得到的 token 存储到 pinia 中
  tokenStore.setToken(result.data)
  // 跳转到首页
  await router.push('/')
}

// 清空数据模型的数据，因为注册和登录共用 registerData
// 切换注册和登录会导致数据还存在，要清空
const clearRegisterData = ()=>{
  registerData.value = {
    username: '',
    password: '',
    rePassword: ''
  }
}
</script>

<template>
  <el-row class="login-page">
    <el-col :span="12" class="bg"></el-col>
    <el-col :span="6" :offset="3" class="form">
      <!-- 注册表单 -->
      <el-form ref="form" size="large" autocomplete="off" v-if="isRegister" :model="registerData" :rules="rules">
        <el-form-item>
          <h1>注册</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerData.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="registerData.password"></el-input>
        </el-form-item>
        <el-form-item prop="rePassword">
          <el-input :prefix-icon="Lock" type="password" placeholder="请输入再次密码" v-model="registerData.rePassword"></el-input>
        </el-form-item>
        <!-- 注册按钮 -->
        <el-form-item>
          <el-button class="button" type="primary" auto-insert-space @click="register">
            注册
          </el-button>
        </el-form-item>
        <el-form-item class="flex">
          <el-link type="info" :underline="false" @click="isRegister = false; clearRegisterData()">
            ← 返回
          </el-link>
        </el-form-item>
      </el-form>

<!--      复用注册时的表单的数据-->
      <!-- 登录表单 -->
      <el-form ref="form" size="large" autocomplete="off" v-else :model="registerData" :rules="rules">
        <el-form-item>
          <h1>登录</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerData.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="registerData.password"></el-input>
        </el-form-item>
        <el-form-item class="flex">
          <div class="flex">
            <el-checkbox>记住我</el-checkbox>
            <el-link type="primary" :underline="false">忘记密码？</el-link>
          </div>
        </el-form-item>
        <!-- 登录按钮 -->
        <el-form-item>
          <el-button class="button" type="primary" auto-insert-space @click="login">登录</el-button>
        </el-form-item>
        <el-form-item class="flex">
          <el-link type="info" :underline="false" @click="isRegister = true; clearRegisterData()">
            注册 →
          </el-link>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<style lang="scss" scoped>
/* 样式 */
.login-page {
  height: 100vh;
  background-color: #fff;

  .bg {
    background: url('@/assets/logo2.png') no-repeat 60% center / 240px auto,
    url('@/assets/login_bg.jpg') no-repeat center / cover;
    border-radius: 0 20px 20px 0;
  }

  .form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    user-select: none;

    .title {
      margin: 0 auto;
    }

    .button {
      width: 100%;
    }

    .flex {
      width: 100%;
      display: flex;
      justify-content: space-between;
    }
  }
}
</style>