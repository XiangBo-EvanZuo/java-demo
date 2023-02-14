# Author：[二十多岁的老王](https://space.bilibili.com/309430466)
- [B站免费全栈视频](https://space.bilibili.com/309430466) 
- [直接戳](https://space.bilibili.com/309430466)

# 验证
- 参数验证
- 统一拦截Request
- 分组、自定义拦截器

## 拦截器
- 统一返回固定格式Result (Done)
- 统一错误拦截 (Done)

## 查询
- lambda方式查询（Done）
- 查询投影 Partial Result | Custom Result
  - Dao中包含的字段筛选
  - 不存在的字段筛选，原生Sql，添加BaseMapper的Imp

# todos
- 目录结构
- log
- Bear jwt登陆态 （Session Done）

## 权限管理 rbac
- spring-security（主要是这个重量级框架）
  - 资源：如按钮、菜单
  - 角色：用户 
  - 越权：url（done）
- shiro（了解）
## Spring高并发
- 缓存
- 分布式缓存Redis
- 消息中间件
## 数据库与事务
- 隔离级别
- 一张表成功，领一张表失败的复杂业务，需要事务统一处理
## 微服务
## 运维系列
- 打包、部署、监控、日志

- 
# Done
- 分页(Done)
- 多环境部署（Maven | config）(Done - Maven没有配置)
- 去除banner-Spring MP（Done）
- id类型（UUID、输入、正常自增）设置
- table表名称统一前缀设置prefix
- 逻辑删除
- 乐观锁-拦截器
- session 统一拦截session（single-model-session分支下）
- 序列化与反序列化的字段过滤
- 

# 开发效率
- Yaml的提示
- 高亮log

# branch说明
- single-model-session

- table表名称统一前缀设置prefix
