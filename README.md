# springboot-vue3-big-event



根据黑马教程实现的一个 springboot3 和 vue3 的项目。

阿里云 OSS 的 secret key 和 access key 需要在环境变量中配置，cmd 中使用

```bash
setx OSS_ACCESS_KEY_ID "YOUR_ACCESS_KEY_ID"
setx OSS_ACCESS_KEY_SECRET "YOUR_ACCESS_KEY_SECRET"
```

## 数据库

sql 文件下的 big_event.sql 提供了部分数据，需要在后端部分 application.yml 下配置连接自己的 mysql 数据库。

## 前端部分

别忘了在根目录下用 npm 安装插件

```shell
npm install
```

## 后端部分

需要在 resources 目录下创建 $application.yml$ 参考配置如下。

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/
    username: 
    password: 

  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 100MB
      
  data:
    # 配置 redis
    redis:
      host: localhost
      port: 6379

mybatis:
  configuration:
    # 开启驼峰命名和下划线命名的自动转换
    map-underscore-to-camel-case: true

aliyun:
  oss:
    endpoint: 
    bucketName: 
    region: 

```

