# 商场购物车API
一个基于 Spring Boot 的购物车后端 API，个人后端实践项目。接口规范参考课程案例，代码由自己编写。

## 技术栈
Spring Boot + Spring Data JPA + MySQL + Postman调试

## 项目结构
```
controllers   接口层，接收请求、返回响应
services      业务逻辑
repositories  数据访问（Spring Data JPA）
entities      主要实体：Cart、CartItem、Product
dtos          接口传输对象
mappers       MapStruct 转换接口
exceptions    自定义业务异常
GlobalExceptionHandler  统一异常处理
```

## 接口列表
| 请求方法 | 接口路径 | 功能说明 | 成功响应码 |
| :---: | :--- | :--- | :---: |
| POST | `/carts` | 创建购物车 | 201 |
| POST | `/carts/{cartId}/items` | 添加商品（已存在则数量+1） | 201 |
| GET | `/carts/{cartId}` | 查询购物车详情 | 200 |
| PUT | `/carts/{cartId}/items/{productId}` | 更新商品数量 | 200 |
| DELETE | `/carts/{cartId}/items/{productId}` | 移除购物车中的单个商品 | 204 |
| DELETE | `/carts/{cartId}/items` | 清空购物车 | 204 |

错误响应：购物车不存在返回 404，商品不存在返回 400，请求参数校验失败返回 400。
