<script setup>
import { Plus, Upload } from '@element-plus/icons-vue'
import {ref} from 'vue'
import avatar from '@/assets/default.png'
import useUserInfoStore from "@/stores/userInfo.js";
import {useTokenStore} from "@/stores/token.js";
import {userAvatarService, userInfoService} from "@/api/user.js";
import {ElMessage} from "element-plus";
const uploadRef = ref()

const userInfo = useUserInfoStore()

//用户头像地址
const imgUrl = ref(userInfo.info.userPic)
const tokenStore = useTokenStore()

// 图片上传成功回调函数
const uploadSuccess = (result)=>{
  imgUrl.value = result.data
}

const userInfoStore = useUserInfoStore()
// 获取用户详细信息
const getUserInfo = async ()=>{
  let result = await userInfoService()

  // 数据存储到 pinia 中
  userInfoStore.setInfo(result.data)
}

// 头像修改
const uploadAvatar = async ()=>{
  let result = await userAvatarService(imgUrl.value)
  userInfo.info.userPic = imgUrl.value
  getUserInfo()

  ElMessage.success(result.message ? result.message : '修改成功')
}
</script>

<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>更换头像</span>
      </div>
    </template>
    <el-row>
      <el-col :span="12">
        <el-upload
            ref="uploadRef"
            class="avatar-uploader"
            :show-file-list="false"
            :auto-upload=true
            action="/api/upload"
            name="file"
            :headers="{'Authorization': tokenStore.token}"
            :on-success="uploadSuccess"
        >
          <img v-if="imgUrl" :src="imgUrl" class="avatar" />
          <img v-else :src="avatar" width="278" />
        </el-upload>
        <br />
        <el-button type="primary" :icon="Plus" size="large"  @click="uploadRef.$el.querySelector('input').click()">
          选择图片
        </el-button>
        <el-button type="success" :icon="Upload" size="large" @click="uploadAvatar">
          上传头像
        </el-button>
      </el-col>
    </el-row>
  </el-card>
</template>

<style lang="scss" scoped>
.avatar-uploader {
  :deep() {
    .avatar {
      width: 278px;
      height: 278px;
      display: block;
    }

    .el-upload {
      border: 1px dashed var(--el-border-color);
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
      transition: var(--el-transition-duration-fast);
    }

    .el-upload:hover {
      border-color: var(--el-color-primary);
    }

    .el-icon.avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 278px;
      height: 278px;
      text-align: center;
    }
  }
}
</style>