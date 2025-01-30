<script setup>
import { ref } from 'vue'
import {ElMessage, ElMessageBox} from "element-plus";
import {userPasswordUpdateService} from "@/api/user.js";

const formRef = ref(null)

const passwordForm = ref({
  old_pwd: '',
  new_pwd: '',
  re_pwd: ''
})

const rules = {
  old_pwd: [
    { required: true, message: '请输入原密码', trigger: 'blur' },
    {
      pattern: /^\S{5,16}$/,
      message: '密码长度在 5 到 16 个字符',
      trigger: 'blur'
    }
  ],
  new_pwd: [
    { required: true, message: '请输入新密码', trigger: 'blur' },
    {
      validator: (rule, value, callback) => {
        if (value === passwordForm.value.old_pwd) {
          callback(new Error('新密码不能与原密码相同'))
        } else if (!/^\S{5,16}$/.test(value)) {
          callback(new Error('密码为 5-16 个字符'))
        } else {
          callback()
        }
      },
      trigger: 'blur'
    }
  ],
  re_pwd: [
    { required: true, message: '请确认新密码', trigger: 'blur' },
    {
      validator: (rule, value, callback) => {
        if (value !== passwordForm.value.new_pwd) {
          callback(new Error('两次输入密码不一致'))
        } else {
          callback()
        }
      },
      trigger: 'blur'
    }
  ]
}

const submitForm = async (form)=>{
  if (!form) return

  try {
    // 表单验证
    await form.validate()

    // 二次确认
    await ElMessageBox.confirm('确定要修改密码吗？', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })

    // 调用接口
    const result = await userPasswordUpdateService({
      old_pwd: passwordForm.value.old_pwd,
      new_pwd: passwordForm.value.new_pwd,
      re_pwd: passwordForm.value.re_pwd
    })

    ElMessage.success(result.message || '密码修改成功')

    // 清空表单
    form.resetFields()
  } catch (error) {
    if (error === 'cancel') return

    ElMessage.error(error.response?.data?.message || error.message || '修改失败')
  }
}
</script>
<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>重置密码</span>
      </div>
    </template>

    <el-row>
      <el-col :span="12">
        <el-form :model="passwordForm" :rules="rules" ref="formRef" label-width="100px" size="large">
          <el-form-item label="原密码" prop="old_pwd">
            <el-input
                v-model="passwordForm.old_pwd"
                show-password
                placeholder="请输入当前使用的密码"
            ></el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="new_pwd">
            <el-input
                v-model="passwordForm.new_pwd"
                type="password"
                placeholder="5-16 位字符"
            ></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="re_pwd">
            <el-input
                v-model="passwordForm.re_pwd"
                type="password"
                placeholder="请再次输入新密码"
            ></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm(formRef)">提交修改</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </el-card>
</template>