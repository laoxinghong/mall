mall商城是一套电商管理系统，包括前台商城系统和后台管理系统， 基于
Springboot+Mybatis实现。 前台商城系统包括首页内容管理、商品搜索、 商品
推荐、购物车管理、订单管理、会员管理等模块。



mall
├── mall-admin -- 后台管理系统模块
├── mall-common -- 工具类及通用代码模块
├── mall-mbg -- MyBatisGenerator生成的数据层代码模块
├── mall-portal -- 前台商城系统模块
├── mall-search -- 基于ElasticSearch的商品搜索系统模块
└──mall-security -- 基于SpringSecurity的安全认证模块



mall技术选型
技术							         			     说明                                 版本
SpringBoot					            容器+MVC框架                        2.6.7
SpringSecurity						    认证授权框架                           2.6.7
MyBatis								    ORM框架	                             3.5.10
MyBatisGenerator		            数据层代码生成                       1.4.1
RabbitMQ								消息队列                                   3.10.5     
Redis									    分布式缓存                               5.0.14.1
MongoDB					            NoSQL数据库                           5.0  
ElasticSearch							搜索引擎                                  7.17.3
LogStash									日志收集工具                           7.17.3    
Kibana										日志可视化查看工具                7.17.3
Druid										数据库连接池                           1.2.14
Hutool										Java工具类库                            5.8.9
PageHelper								Mybatis分页插件                      5.3.2
Swagger								    文档生成工具                           3.0.0
logstash-logback-encoder			Logstash日志收集插件              7.2    
JWT                                         JWT登录支持                           0.9.1
Lombok                                    代码简化工具                          1.18.24  



数据库表前缀说明
pms_：商品模块相关
oms_：订单模块相关
sms_：营销模块相关
ums_：用户模块相关
cms_：内容模块相关



mall实现的功能概览
商品模块
    商品管理
    商品促销管理
    商品品牌管理
    商品分类管理
    商品属性管理
订单模块
    订单管理
    购物车管理
    退货管理
营销模块
    优惠券管理
    首页广告
    新品推荐
    热门商品推荐
    专题管理
    秒杀活动管理
用户模块
    后台用户管理
    后台资源管理
    会员管理
