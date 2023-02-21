-- MySQL dump 10.13  Distrib 5.7.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mymall
-- ------------------------------------------------------
-- Server version	5.7.38-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cms_help`
--

DROP TABLE IF EXISTS `cms_help`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_help` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) DEFAULT NULL COMMENT '帮助类型id',
  `icon` varchar(500) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `show_status` int(1) DEFAULT NULL COMMENT '显示状态 0>不显示 1>显示',
  `create_time` datetime DEFAULT NULL,
  `read_count` int(1) DEFAULT NULL COMMENT '浏览数量',
  `content` text COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户帮助表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_help`
--

LOCK TABLES `cms_help` WRITE;
/*!40000 ALTER TABLE `cms_help` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_help` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_help_category`
--

DROP TABLE IF EXISTS `cms_help_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_help_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '类型名称',
  `icon` varchar(500) DEFAULT NULL,
  `help_count` int(11) DEFAULT NULL COMMENT '分类下help数量',
  `show_status` int(2) DEFAULT NULL COMMENT '显示状态 0>不显示 1>显示',
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户帮助分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_help_category`
--

LOCK TABLES `cms_help_category` WRITE;
/*!40000 ALTER TABLE `cms_help_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_help_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_member_report`
--

DROP TABLE IF EXISTS `cms_member_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_member_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `report_type` int(1) DEFAULT NULL COMMENT '举报类型 0>商品评价 1>话题内容 2>用户评论',
  `report_member_name` varchar(100) DEFAULT NULL COMMENT '举报用户名称',
  `create_time` datetime DEFAULT NULL,
  `report_object` varchar(100) DEFAULT NULL,
  `report_status` int(1) DEFAULT NULL COMMENT '举报处理状态 0>未处理 1>已处理',
  `handle_status` int(1) DEFAULT NULL COMMENT '处理结果 0>无效 1>有效 2>恶意',
  `note` varchar(200) DEFAULT NULL COMMENT '处理备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户举报记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_member_report`
--

LOCK TABLES `cms_member_report` WRITE;
/*!40000 ALTER TABLE `cms_member_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_member_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_preference_area`
--

DROP TABLE IF EXISTS `cms_preference_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_preference_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sub_title` varchar(255) DEFAULT NULL,
  `pic` varbinary(500) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `show_status` int(1) DEFAULT NULL COMMENT '显示状态 0>不显示 1>显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优选专区表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_preference_area`
--

LOCK TABLES `cms_preference_area` WRITE;
/*!40000 ALTER TABLE `cms_preference_area` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_preference_area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_preference_area_product_relation`
--

DROP TABLE IF EXISTS `cms_preference_area_product_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_preference_area_product_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `preference_area_id` bigint(20) DEFAULT NULL COMMENT '优选专区id',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优选专区与商品关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_preference_area_product_relation`
--

LOCK TABLES `cms_preference_area_product_relation` WRITE;
/*!40000 ALTER TABLE `cms_preference_area_product_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_preference_area_product_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_subject`
--

DROP TABLE IF EXISTS `cms_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) DEFAULT NULL COMMENT '专题分类id',
  `title` varchar(100) DEFAULT NULL,
  `pic` varchar(500) DEFAULT NULL,
  `product_count` int(11) DEFAULT NULL COMMENT '关联商品数量',
  `recommend_status` int(1) DEFAULT NULL COMMENT '是否推荐 0>不推荐 1>推荐',
  `create_time` datetime DEFAULT NULL,
  `collect_count` int(11) DEFAULT NULL COMMENT '收藏数量',
  `read_count` int(11) DEFAULT NULL COMMENT '浏览数量',
  `comment_count` int(11) DEFAULT NULL COMMENT '评论数量',
  `album_pics` varchar(1000) DEFAULT NULL COMMENT '相册图片，以逗号分隔',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `show_status` int(1) DEFAULT NULL COMMENT '显示状态 0>不显示 1>显示',
  `content` text,
  `forward_count` int(11) DEFAULT NULL COMMENT '转发数',
  `category_name` varchar(200) DEFAULT NULL COMMENT '专题分类名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专题表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_subject`
--

LOCK TABLES `cms_subject` WRITE;
/*!40000 ALTER TABLE `cms_subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_subject_category`
--

DROP TABLE IF EXISTS `cms_subject_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_subject_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '专题分类名称',
  `icon` varchar(500) DEFAULT NULL,
  `subject_count` int(11) DEFAULT NULL COMMENT '专题数量',
  `show_status` int(2) DEFAULT NULL COMMENT '显示状态 0>不显示 1>显示',
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专题分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_subject_category`
--

LOCK TABLES `cms_subject_category` WRITE;
/*!40000 ALTER TABLE `cms_subject_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_subject_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_subject_comment`
--

DROP TABLE IF EXISTS `cms_subject_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_subject_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(20) DEFAULT NULL COMMENT '专题id',
  `member_nick_name` varchar(255) DEFAULT NULL COMMENT '会员昵称',
  `member_icon` varchar(255) DEFAULT NULL COMMENT '会员头像',
  `content` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `show_status` int(1) DEFAULT NULL COMMENT '显示状态 0>不显示 1>显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专题评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_subject_comment`
--

LOCK TABLES `cms_subject_comment` WRITE;
/*!40000 ALTER TABLE `cms_subject_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_subject_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_subject_product_relation`
--

DROP TABLE IF EXISTS `cms_subject_product_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_subject_product_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(20) DEFAULT NULL COMMENT '专题id',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专题与商品关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_subject_product_relation`
--

LOCK TABLES `cms_subject_product_relation` WRITE;
/*!40000 ALTER TABLE `cms_subject_product_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_subject_product_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_topic`
--

DROP TABLE IF EXISTS `cms_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) DEFAULT NULL COMMENT '话题分类id',
  `name` varchar(255) DEFAULT NULL COMMENT '话题名称',
  `create_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `attend_count` int(11) DEFAULT NULL COMMENT '参与人数',
  `attention_count` int(11) DEFAULT NULL COMMENT '关注人数',
  `read_count` int(11) DEFAULT NULL COMMENT '浏览数量',
  `award_name` varchar(100) DEFAULT NULL COMMENT '奖品名称',
  `attend_type` varchar(100) DEFAULT NULL COMMENT '参与方式',
  `content` text COMMENT '话题内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='话题表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_topic`
--

LOCK TABLES `cms_topic` WRITE;
/*!40000 ALTER TABLE `cms_topic` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_topic_category`
--

DROP TABLE IF EXISTS `cms_topic_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_topic_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '话题分类名称',
  `icon` varchar(500) DEFAULT NULL,
  `topic_count` int(11) DEFAULT NULL COMMENT '话题数量',
  `show_status` int(2) DEFAULT NULL COMMENT '显示状态 0>不显示 1>显示',
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='话题分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_topic_category`
--

LOCK TABLES `cms_topic_category` WRITE;
/*!40000 ALTER TABLE `cms_topic_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_topic_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_topic_comment`
--

DROP TABLE IF EXISTS `cms_topic_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_topic_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_nick_name` varchar(255) DEFAULT NULL COMMENT '会员昵称',
  `topic_id` bigint(20) DEFAULT NULL COMMENT '话题id',
  `member_icon` varchar(255) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL COMMENT '评论内容',
  `create_time` datetime DEFAULT NULL,
  `show_status` int(1) DEFAULT NULL COMMENT '显示状态 0>不显示 1>显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='话题评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_topic_comment`
--

LOCK TABLES `cms_topic_comment` WRITE;
/*!40000 ALTER TABLE `cms_topic_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_topic_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oms_cart_item`
--

DROP TABLE IF EXISTS `oms_cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oms_cart_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `product_sku_id` bigint(20) DEFAULT NULL COMMENT '商品sku id',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `quantity` int(11) DEFAULT NULL COMMENT '购买数量',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `product_publish_status` int(1) DEFAULT NULL COMMENT '上架状态 0>下架 1>上架',
  `product_pic` varchar(1000) DEFAULT NULL,
  `product_name` varchar(500) DEFAULT NULL,
  `product_brand` varchar(200) DEFAULT NULL,
  `product_sn` varchar(200) DEFAULT NULL COMMENT '商品编码',
  `product_sub_title` varchar(500) DEFAULT NULL,
  `product_sku_code` varchar(200) DEFAULT NULL COMMENT '商品sku编码',
  `member_nickname` varchar(500) DEFAULT NULL COMMENT '用户昵称',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_status` int(1) DEFAULT NULL COMMENT '删除状态 0>不删除 1>删除',
  `product_category_id` bigint(20) DEFAULT NULL COMMENT '商品分类id',
  `product_attr` varchar(500) DEFAULT NULL COMMENT '商品销售属性',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='购物车项目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oms_cart_item`
--

LOCK TABLES `oms_cart_item` WRITE;
/*!40000 ALTER TABLE `oms_cart_item` DISABLE KEYS */;
INSERT INTO `oms_cart_item` VALUES (1,1,1,1,1,0.00,0,'string','string','string','string','string','string',NULL,'2023-02-21 15:11:21','2023-02-21 18:17:24',1,1,'string'),(2,1,1,1,3,0.00,0,'string','string','string','string','string','string',NULL,'2023-02-21 15:14:01','2023-02-21 18:17:32',1,1,'string'),(3,1,1,1,1,0.00,0,'string','string','string','string','string','string',NULL,'2023-02-21 18:17:41','2023-02-21 19:24:24',1,1,'string'),(4,0,0,1,23,0.00,0,'string','string','string','string','string','string',NULL,'2023-02-21 18:22:36','2023-02-21 18:23:05',0,0,'string'),(5,1,1,1,1,0.00,0,'string','string','string','string','string','string',NULL,'2023-02-21 19:39:31','2023-02-21 19:40:42',1,1,'string');
/*!40000 ALTER TABLE `oms_cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oms_company_address`
--

DROP TABLE IF EXISTS `oms_company_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oms_company_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_name` varchar(200) DEFAULT NULL,
  `send_status` int(1) DEFAULT NULL COMMENT '默认发货地址 0>否 1>是',
  `receive_status` int(1) DEFAULT NULL COMMENT '默认收货地址 0>否 1>是',
  `name` varchar(64) DEFAULT NULL COMMENT '收发货人名称',
  `phone` varchar(64) DEFAULT NULL,
  `province` varchar(64) DEFAULT NULL,
  `city` varchar(64) DEFAULT NULL,
  `region` varchar(64) DEFAULT NULL,
  `detail_address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司收发货地址表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oms_company_address`
--

LOCK TABLES `oms_company_address` WRITE;
/*!40000 ALTER TABLE `oms_company_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `oms_company_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oms_order`
--

DROP TABLE IF EXISTS `oms_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oms_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) NOT NULL COMMENT '用户ID',
  `coupon_id` bigint(20) DEFAULT NULL COMMENT '优惠券id',
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编码',
  `create_time` datetime DEFAULT NULL,
  `member_username` varchar(64) DEFAULT NULL COMMENT '用户账号',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '订单总金额',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '实际支付总金额',
  `freight_amount` decimal(10,2) DEFAULT NULL COMMENT '运费',
  `promotion_amount` decimal(10,2) DEFAULT NULL COMMENT '促销优惠金额',
  `integration_amount` decimal(10,2) DEFAULT NULL COMMENT '积分抵扣金额',
  `coupon_amount` decimal(10,2) DEFAULT NULL COMMENT '优惠券抵减金额',
  `discount_amount` decimal(10,2) DEFAULT NULL COMMENT '管理员后台调整订单使用的折扣金额',
  `pay_type` int(1) DEFAULT NULL COMMENT '支付方式 0>未支付 1>支付宝 2>微信',
  `source_type` int(1) DEFAULT NULL COMMENT '订单来源 0>pc 1>app',
  `status` int(1) DEFAULT NULL COMMENT '订单状态 0>待付款 1>待发货 2>已发货 3>已完成 4>已关闭 5>退款中 6>无效订单',
  `order_type` int(1) DEFAULT NULL COMMENT '订单类型 0>普通订单 1>秒杀订单',
  `delivery_company` varchar(64) DEFAULT NULL COMMENT '物流公司',
  `delivery_sn` varchar(64) DEFAULT NULL COMMENT '物流单号',
  `auto_confirm_day` int(11) DEFAULT NULL COMMENT '自动确认收货天数',
  `integration` int(11) DEFAULT NULL COMMENT '获得的积分',
  `growth` int(11) DEFAULT NULL COMMENT '获得的成长值',
  `promotion_info` varchar(100) DEFAULT NULL COMMENT '促销活动信息',
  `bill_type` int(1) DEFAULT NULL COMMENT '发票类型 0>不开发票 1>电子发票 2>纸质发票',
  `bill_header` varchar(200) DEFAULT NULL COMMENT '发票抬头',
  `bill_content` varchar(200) DEFAULT NULL COMMENT '发票内容',
  `bill_receiver_phone` varchar(32) DEFAULT NULL COMMENT '收票人电话',
  `bill_receiver_email` varchar(64) DEFAULT NULL,
  `receiver_name` varchar(100) NOT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(32) NOT NULL COMMENT '收货人电话',
  `receiver_post_code` varchar(32) DEFAULT NULL,
  `receiver_province` varchar(32) DEFAULT NULL,
  `receiver_city` varchar(32) DEFAULT NULL,
  `receiver_region` varchar(32) DEFAULT NULL,
  `receiver_detail_address` varchar(200) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL COMMENT '订单备注',
  `confirm_status` int(1) DEFAULT NULL COMMENT '收货确认状态',
  `delete_status` int(1) NOT NULL COMMENT '删除状态 0>未删除 1>已删除',
  `use_integration` int(11) DEFAULT NULL COMMENT '订单使用积分',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receiver_time` datetime DEFAULT NULL COMMENT '收货时间',
  `comment_time` datetime DEFAULT NULL COMMENT '评价时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oms_order`
--

LOCK TABLES `oms_order` WRITE;
/*!40000 ALTER TABLE `oms_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `oms_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oms_order_item`
--

DROP TABLE IF EXISTS `oms_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oms_order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编码',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `product_pic` varchar(500) DEFAULT NULL,
  `product_name` varchar(200) DEFAULT NULL,
  `product_brand` varchar(200) DEFAULT NULL,
  `product_sn` varchar(64) DEFAULT NULL COMMENT '商品编码',
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '商品销售价格',
  `product_quantity` int(11) DEFAULT NULL COMMENT '购买数量',
  `product_sku_id` bigint(20) DEFAULT NULL COMMENT '商品sku id',
  `product_sku_code` varchar(50) DEFAULT NULL COMMENT '商品sku编码',
  `product_category_id` bigint(20) DEFAULT NULL,
  `promotion_name` varchar(100) DEFAULT NULL COMMENT '促销活动名称',
  `promotion_amount` decimal(10,2) DEFAULT NULL COMMENT '促销优惠金额',
  `coupon_amount` decimal(10,2) DEFAULT NULL COMMENT '优惠券抵减金额',
  `integration_amount` decimal(10,2) DEFAULT NULL COMMENT '积分抵减金额',
  `real_amount` decimal(10,2) DEFAULT NULL COMMENT '实际支付金额',
  `gift_integration` int(11) DEFAULT NULL COMMENT '赠送的积分',
  `gift_growth` int(11) DEFAULT NULL COMMENT '赠送的成长值',
  `product_attr` varchar(500) DEFAULT NULL COMMENT '商品销售参数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='订单项目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oms_order_item`
--

LOCK TABLES `oms_order_item` WRITE;
/*!40000 ALTER TABLE `oms_order_item` DISABLE KEYS */;
INSERT INTO `oms_order_item` VALUES (1,2,'202302210100000004',1,NULL,'string','string','string',12.00,1,1,'string',1,'无促销优惠',0.00,0.00,0.00,12.00,0,0,'string'),(2,3,'202302210100000005',1,NULL,'string','string','string',12.00,1,1,'string',1,'无促销优惠',0.00,0.00,0.00,12.00,0,0,'string');
/*!40000 ALTER TABLE `oms_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oms_order_operate_history`
--

DROP TABLE IF EXISTS `oms_order_operate_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oms_order_operate_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `operate_man` varchar(100) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime DEFAULT NULL,
  `order_status` int(1) DEFAULT NULL COMMENT '订单状态 0>待付款 1>待发货 2>已发货 3>已完成 4>已关闭 5>无效订单',
  `note` varchar(500) DEFAULT NULL COMMENT '操作备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单操作历史记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oms_order_operate_history`
--

LOCK TABLES `oms_order_operate_history` WRITE;
/*!40000 ALTER TABLE `oms_order_operate_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `oms_order_operate_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oms_order_return_apply`
--

DROP TABLE IF EXISTS `oms_order_return_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oms_order_return_apply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `company_address_id` bigint(20) DEFAULT NULL COMMENT '收货公司地址id',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编码',
  `create_time` datetime DEFAULT NULL,
  `member_username` varchar(64) DEFAULT NULL,
  `return_amount` decimal(10,2) DEFAULT NULL COMMENT '退货金额',
  `return_name` varchar(100) DEFAULT NULL COMMENT '退货人姓名',
  `return_phone` varchar(100) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '申请状态 0>待处理 1>退货中 2>已完成 3>已拒绝',
  `handle_time` datetime DEFAULT NULL COMMENT '处理时间',
  `product_pic` varchar(500) DEFAULT NULL,
  `product_name` varchar(200) DEFAULT NULL,
  `product_brand` varchar(200) DEFAULT NULL,
  `product_attr` varchar(500) DEFAULT NULL COMMENT '商品销售参数',
  `product_count` int(11) DEFAULT NULL,
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '商品单价',
  `product_real_price` decimal(10,2) DEFAULT NULL COMMENT '商品实际支付单价',
  `reason` varchar(200) DEFAULT NULL COMMENT '退货理由',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `proof_pics` varchar(1000) DEFAULT NULL COMMENT '凭证图片，以逗号分隔',
  `handle_note` varchar(500) DEFAULT NULL COMMENT '处理备注',
  `handle_man` varchar(100) DEFAULT NULL COMMENT '处理人',
  `receive_man` varchar(100) DEFAULT NULL COMMENT '收货人',
  `receive_time` datetime DEFAULT NULL COMMENT '收货时间',
  `receive_note` varchar(500) DEFAULT NULL COMMENT '收货备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='退货申请记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oms_order_return_apply`
--

LOCK TABLES `oms_order_return_apply` WRITE;
/*!40000 ALTER TABLE `oms_order_return_apply` DISABLE KEYS */;
INSERT INTO `oms_order_return_apply` VALUES (1,0,NULL,0,'string','2023-02-21 15:17:33','string',NULL,'string','string',0,NULL,'string','string','string','string',0,0.00,0.00,'string','string','string',NULL,NULL,NULL,NULL,NULL),(2,2,NULL,1,'string','2023-02-21 19:56:50','string',NULL,'string','string',0,NULL,'string','string','string','string',0,0.00,0.00,'string','string','string',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `oms_order_return_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oms_order_return_reason`
--

DROP TABLE IF EXISTS `oms_order_return_reason`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oms_order_return_reason` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '原因',
  `sort` int(11) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '启用状态 0>不启用 1>启用',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退货原因设置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oms_order_return_reason`
--

LOCK TABLES `oms_order_return_reason` WRITE;
/*!40000 ALTER TABLE `oms_order_return_reason` DISABLE KEYS */;
/*!40000 ALTER TABLE `oms_order_return_reason` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oms_order_setting`
--

DROP TABLE IF EXISTS `oms_order_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oms_order_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flash_order_overtime` int(11) DEFAULT NULL COMMENT '秒杀订单超时关闭时间',
  `normal_order_overtime` int(11) DEFAULT NULL COMMENT '普通订单超时关闭时间',
  `confirm_overtime` int(11) DEFAULT NULL COMMENT '发货后超时自动确认时间',
  `finish_overtime` int(11) DEFAULT NULL COMMENT '自动完成交易时间',
  `comment_overtime` int(11) DEFAULT NULL COMMENT '订单完成后自动好评时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='订单设置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oms_order_setting`
--

LOCK TABLES `oms_order_setting` WRITE;
/*!40000 ALTER TABLE `oms_order_setting` DISABLE KEYS */;
INSERT INTO `oms_order_setting` VALUES (1,10,10,7,14,14);
/*!40000 ALTER TABLE `oms_order_setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_album`
--

DROP TABLE IF EXISTS `pms_album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_album` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `cover_pic` varchar(1000) DEFAULT NULL,
  `pic_count` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='相册表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_album`
--

LOCK TABLES `pms_album` WRITE;
/*!40000 ALTER TABLE `pms_album` DISABLE KEYS */;
/*!40000 ALTER TABLE `pms_album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_album_pic`
--

DROP TABLE IF EXISTS `pms_album_pic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_album_pic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `album_id` bigint(20) DEFAULT NULL COMMENT '相册表id',
  `pic` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_album_pic`
--

LOCK TABLES `pms_album_pic` WRITE;
/*!40000 ALTER TABLE `pms_album_pic` DISABLE KEYS */;
/*!40000 ALTER TABLE `pms_album_pic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_brand`
--

DROP TABLE IF EXISTS `pms_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `first_letter` varchar(8) DEFAULT NULL COMMENT '首字母',
  `sort` int(11) DEFAULT NULL,
  `factory_status` int(1) DEFAULT NULL COMMENT '是否为品牌制造商 0>不是，1>是',
  `show_status` int(1) DEFAULT NULL,
  `product_count` int(11) DEFAULT NULL COMMENT '产品数量',
  `product_comment_count` int(11) DEFAULT NULL COMMENT '产品评论数量',
  `logo` varchar(255) DEFAULT NULL COMMENT '品牌logo',
  `big_pic` varchar(255) DEFAULT NULL COMMENT '专区大图',
  `brand_story` text COMMENT '品牌故事',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='品牌表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_brand`
--

LOCK TABLES `pms_brand` WRITE;
/*!40000 ALTER TABLE `pms_brand` DISABLE KEYS */;
INSERT INTO `pms_brand` VALUES (1,'oppo','o',1,1,1,1,0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `pms_brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_comment`
--

DROP TABLE IF EXISTS `pms_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `member_nick_name` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `star` int(3) DEFAULT NULL COMMENT '评论星级 0-5',
  `member_ip` varchar(64) DEFAULT NULL COMMENT '会员ip',
  `create_time` datetime DEFAULT NULL,
  `show_status` int(1) DEFAULT NULL COMMENT '是否显示',
  `product_attribute` varchar(255) DEFAULT NULL,
  `collect_count` int(11) DEFAULT NULL COMMENT '收藏数量',
  `read_count` int(11) DEFAULT NULL COMMENT '浏览数量',
  `content` text COMMENT '内容',
  `pics` varchar(1000) DEFAULT NULL COMMENT '图片',
  `member_icon` varchar(255) DEFAULT NULL COMMENT '会员头像',
  `replay_count` int(11) DEFAULT NULL COMMENT '回复数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_comment`
--

LOCK TABLES `pms_comment` WRITE;
/*!40000 ALTER TABLE `pms_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `pms_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_comment_replay`
--

DROP TABLE IF EXISTS `pms_comment_replay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_comment_replay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(20) DEFAULT NULL COMMENT '商品评论表id',
  `member_nick_name` varchar(255) DEFAULT NULL COMMENT '会员昵称',
  `member_icon` varchar(255) DEFAULT NULL COMMENT '会员头像',
  `content` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '评论人员类型 0>会员 1>管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品评价回复表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_comment_replay`
--

LOCK TABLES `pms_comment_replay` WRITE;
/*!40000 ALTER TABLE `pms_comment_replay` DISABLE KEYS */;
/*!40000 ALTER TABLE `pms_comment_replay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_freight_template`
--

DROP TABLE IF EXISTS `pms_freight_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_freight_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `charge_type` int(1) DEFAULT NULL COMMENT '计费类型 0>按重量 1>按件数',
  `first_weight` decimal(10,2) DEFAULT NULL COMMENT '初始重量',
  `first_fee` decimal(10,2) DEFAULT NULL COMMENT '初始价格',
  `continue_weight` decimal(10,2) DEFAULT NULL,
  `continue_fee` decimal(10,2) DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL COMMENT '目的地',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运费模板表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_freight_template`
--

LOCK TABLES `pms_freight_template` WRITE;
/*!40000 ALTER TABLE `pms_freight_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `pms_freight_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_member_price`
--

DROP TABLE IF EXISTS `pms_member_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_member_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `member_level_id` bigint(20) DEFAULT NULL COMMENT '会员等级id',
  `member_price` decimal(10,2) DEFAULT NULL COMMENT '会员价格',
  `member_level_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品会员价格表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_member_price`
--

LOCK TABLES `pms_member_price` WRITE;
/*!40000 ALTER TABLE `pms_member_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `pms_member_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_product`
--

DROP TABLE IF EXISTS `pms_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_id` bigint(20) DEFAULT NULL COMMENT '商品品牌表id',
  `product_category_id` bigint(20) DEFAULT NULL COMMENT '商品分类表id',
  `freight_template_id` bigint(20) DEFAULT NULL COMMENT '运费模板表id',
  `product_attribute_category_id` bigint(20) DEFAULT NULL COMMENT '商品属性分类表id',
  `name` varchar(64) NOT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `product_sn` varchar(64) NOT NULL COMMENT '货号',
  `delete_status` int(1) DEFAULT NULL COMMENT '删除状态 0>未删除 1>已删除',
  `publish_status` int(1) DEFAULT NULL COMMENT '上架状态 0>下架 1>上架',
  `new_status` int(1) DEFAULT NULL COMMENT '新品状态 0>非新品 1>新品',
  `recommend_status` int(1) DEFAULT NULL COMMENT '推荐状态 0>不推荐 1>推荐',
  `verify_status` int(1) DEFAULT NULL COMMENT '审核状态 0>未审核 1>审核通过',
  `sort` int(11) DEFAULT NULL,
  `sale` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `promotion_price` decimal(10,2) DEFAULT NULL COMMENT '促销价格',
  `gift_growth` int(11) DEFAULT '0' COMMENT '赠送的成长值',
  `gift_point` int(11) DEFAULT '0' COMMENT '赠送的积分',
  `use_point_limit` int(11) DEFAULT NULL COMMENT '限制使用的积分数',
  `sub_title` varchar(255) DEFAULT NULL COMMENT '副标题',
  `description` text COMMENT '商品描述',
  `original_price` decimal(10,2) DEFAULT NULL COMMENT '市场价',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `low_stock` int(11) DEFAULT NULL COMMENT '库存预警值',
  `unit` varchar(16) DEFAULT NULL COMMENT '单位',
  `weight` decimal(10,2) DEFAULT NULL COMMENT '商品重量，单位g',
  `preview_status` int(1) DEFAULT NULL COMMENT '是否为预告商品 0>不是 1>是',
  `service_ids` varchar(64) DEFAULT NULL COMMENT '以逗号分隔的商品服务 1>无忧退货 2>快速退款 3>免费包邮',
  `keywords` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `album_pics` varchar(255) DEFAULT NULL COMMENT '连同商品图片限制为5张，以逗号分隔',
  `detail_title` varchar(255) DEFAULT NULL COMMENT '详情标题',
  `detail_desc` text COMMENT '详情描述',
  `detail_html` text COMMENT '详情网页内容',
  `detail_mobile_html` text COMMENT '移动端网页详情',
  `promotion_start_time` datetime DEFAULT NULL COMMENT '促销活动开始时间',
  `promotion_end_time` datetime DEFAULT NULL COMMENT '促销活动结束时间',
  `promotion_per_limit` int(11) DEFAULT NULL COMMENT '促销活动限购数量',
  `promotion_type` int(1) DEFAULT NULL COMMENT '促销类型 0>无促销 1>单品促销  2>折扣价格 3>满减价格 4>限时购 5>会员价',
  `product_category_name` varchar(255) DEFAULT NULL COMMENT '商品分类名称',
  `brand_name` varchar(255) DEFAULT NULL COMMENT '商品品牌名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='商品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_product`
--

LOCK TABLES `pms_product` WRITE;
/*!40000 ALTER TABLE `pms_product` DISABLE KEYS */;
INSERT INTO `pms_product` VALUES (1,1,1,0,1,'string','string','string',0,1,0,0,0,0,0,0.00,0.00,0,0,0,'string','string',0.00,0,0,'string',0.00,0,'string','手机','string','string','string','string','string','string','2023-02-21 14:53:21','2023-02-21 14:53:21',0,0,'手机','oppo'),(2,1,1,0,1,'小米9',NULL,'null',0,1,0,0,0,0,0,0.00,0.00,0,0,0,'小米手机',NULL,0.00,0,0,NULL,0.00,0,'1','手机','string','string','string','string','string','string','2023-02-21 17:52:22',NULL,0,0,'手机','oppo'),(3,1,1,0,1,'小米10',NULL,'null',0,1,0,0,0,0,0,0.00,0.00,0,0,0,'小米手机',NULL,0.00,0,0,NULL,0.00,0,'1','手机','string','string','string','string','string','string','2023-02-21 17:52:36',NULL,0,0,'手机','oppo'),(4,1,1,0,1,'小米20',NULL,'null',0,1,0,0,0,0,0,0.00,0.00,0,0,0,'小米手机',NULL,0.00,0,0,NULL,0.00,0,'1','手机','string','string','string','string','string','string','2023-02-21 17:52:45',NULL,0,0,'手机','oppo'),(5,1,1,0,1,'小米30',NULL,'null',0,1,0,0,0,0,0,0.00,0.00,0,0,0,'小米手机',NULL,0.00,0,0,NULL,0.00,0,'1','手机','string','string','string','string','string','string','2023-02-21 17:53:04',NULL,0,0,'手机','oppo');
/*!40000 ALTER TABLE `pms_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_product_attribute`
--

DROP TABLE IF EXISTS `pms_product_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_product_attribute` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_attribute_category_id` bigint(20) DEFAULT NULL COMMENT '商品属性分类表id',
  `name` varchar(64) DEFAULT NULL,
  `select_type` int(1) DEFAULT NULL COMMENT '属性选择类型 0>唯一 1>单选 2>多选',
  `input_type` int(1) DEFAULT NULL COMMENT '属性录入类型 0>手工录入 1>从列表中选取',
  `input_list` varchar(255) DEFAULT NULL COMMENT '可选值列表，以逗号分隔',
  `sort` int(11) DEFAULT NULL,
  `filter_type` int(1) DEFAULT NULL COMMENT '分类筛选样式 0>普通 1>颜色',
  `search_type` int(1) DEFAULT NULL COMMENT '检索类型 0>不需要进行检索 1>关键字检索 2>范围检索',
  `related_status` int(1) DEFAULT NULL COMMENT '相同属性商品是否关联 0>不关联 1>关联',
  `hand_add_status` int(1) DEFAULT NULL COMMENT '是否支持手动新增属性 0>不支持 1>支持',
  `type` int(1) DEFAULT NULL COMMENT '属性类型 0>规格 1>参数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品属性表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_product_attribute`
--

LOCK TABLES `pms_product_attribute` WRITE;
/*!40000 ALTER TABLE `pms_product_attribute` DISABLE KEYS */;
/*!40000 ALTER TABLE `pms_product_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_product_attribute_category`
--

DROP TABLE IF EXISTS `pms_product_attribute_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_product_attribute_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `specification_count` int(11) DEFAULT NULL COMMENT '规格数量',
  `param_count` int(11) DEFAULT NULL COMMENT '参数数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品属性分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_product_attribute_category`
--

LOCK TABLES `pms_product_attribute_category` WRITE;
/*!40000 ALTER TABLE `pms_product_attribute_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `pms_product_attribute_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_product_attribute_value`
--

DROP TABLE IF EXISTS `pms_product_attribute_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_product_attribute_value` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品表id',
  `product_attribute_id` bigint(20) DEFAULT NULL COMMENT '商品属性表id',
  `value` varchar(64) DEFAULT NULL COMMENT '规格或参数的值，参数单值，多个规格以逗号分隔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品属性值表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_product_attribute_value`
--

LOCK TABLES `pms_product_attribute_value` WRITE;
/*!40000 ALTER TABLE `pms_product_attribute_value` DISABLE KEYS */;
/*!40000 ALTER TABLE `pms_product_attribute_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_product_category`
--

DROP TABLE IF EXISTS `pms_product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_product_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父分类id',
  `name` varchar(64) DEFAULT NULL,
  `level` int(1) DEFAULT NULL COMMENT '分类级别 0>1级 1>2级',
  `product_count` int(11) DEFAULT NULL,
  `product_unit` varchar(64) DEFAULT NULL,
  `nav_status` int(1) DEFAULT NULL COMMENT '是否显示在导航栏 0>不显示 1>显示',
  `show_status` int(1) DEFAULT NULL COMMENT '显示状态 0>不显示 1>显示',
  `sort` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL COMMENT '分类图标',
  `keywords` varchar(255) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商品分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_product_category`
--

LOCK TABLES `pms_product_category` WRITE;
/*!40000 ALTER TABLE `pms_product_category` DISABLE KEYS */;
INSERT INTO `pms_product_category` VALUES (1,0,'手机',0,1,NULL,1,1,1,NULL,'手机','手机');
/*!40000 ALTER TABLE `pms_product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_product_category_attribute_relation`
--

DROP TABLE IF EXISTS `pms_product_category_attribute_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_product_category_attribute_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_category_id` bigint(20) DEFAULT NULL COMMENT '商品分类表id',
  `product_attribute_id` bigint(20) DEFAULT NULL COMMENT '商品属性表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类与商品属性关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_product_category_attribute_relation`
--

LOCK TABLES `pms_product_category_attribute_relation` WRITE;
/*!40000 ALTER TABLE `pms_product_category_attribute_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `pms_product_category_attribute_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_product_discount_ladder`
--

DROP TABLE IF EXISTS `pms_product_discount_ladder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_product_discount_ladder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `count` int(11) DEFAULT NULL COMMENT '满足数量',
  `discount` decimal(10,2) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL COMMENT '折后价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品折扣阶梯表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_product_discount_ladder`
--

LOCK TABLES `pms_product_discount_ladder` WRITE;
/*!40000 ALTER TABLE `pms_product_discount_ladder` DISABLE KEYS */;
/*!40000 ALTER TABLE `pms_product_discount_ladder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_product_full_reduction`
--

DROP TABLE IF EXISTS `pms_product_full_reduction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_product_full_reduction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `full_price` decimal(10,2) DEFAULT NULL,
  `reduce_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品满减表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_product_full_reduction`
--

LOCK TABLES `pms_product_full_reduction` WRITE;
/*!40000 ALTER TABLE `pms_product_full_reduction` DISABLE KEYS */;
/*!40000 ALTER TABLE `pms_product_full_reduction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_product_operate_log`
--

DROP TABLE IF EXISTS `pms_product_operate_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_product_operate_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `price_old` decimal(10,2) DEFAULT NULL COMMENT '原价格',
  `price_new` decimal(10,2) DEFAULT NULL COMMENT '新价格',
  `sale_price_old` decimal(10,2) DEFAULT NULL,
  `sale_price_new` decimal(10,2) DEFAULT NULL,
  `gift_point_old` int(11) DEFAULT NULL,
  `gift_point_new` int(11) DEFAULT NULL,
  `use_point_limit_old` int(11) DEFAULT NULL COMMENT '原使用积分限制',
  `use_point_limit_new` int(11) DEFAULT NULL,
  `operate_man` varchar(64) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品操作日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_product_operate_log`
--

LOCK TABLES `pms_product_operate_log` WRITE;
/*!40000 ALTER TABLE `pms_product_operate_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `pms_product_operate_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_product_verify_record`
--

DROP TABLE IF EXISTS `pms_product_verify_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_product_verify_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `create_time` datetime DEFAULT NULL,
  `verify_man` varchar(64) DEFAULT NULL COMMENT '审核人',
  `status` int(1) DEFAULT NULL COMMENT '审核状态 0>未通过 1>已通过',
  `detail` varchar(255) DEFAULT NULL COMMENT '反馈详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品审核记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_product_verify_record`
--

LOCK TABLES `pms_product_verify_record` WRITE;
/*!40000 ALTER TABLE `pms_product_verify_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `pms_product_verify_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_sku_stock`
--

DROP TABLE IF EXISTS `pms_sku_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pms_sku_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `sku_code` varchar(64) DEFAULT NULL COMMENT 'sku编码',
  `price` decimal(10,2) DEFAULT NULL,
  `stock` int(11) DEFAULT '0' COMMENT '库存',
  `low_stock` int(11) DEFAULT NULL COMMENT '库存预警值',
  `sp_date` varchar(500) DEFAULT NULL COMMENT '商品销售属性json',
  `pic` varchar(255) DEFAULT NULL,
  `sale` int(11) DEFAULT NULL,
  `promotion_price` decimal(10,2) DEFAULT NULL COMMENT '促销价格',
  `lock_stock` int(11) DEFAULT '0' COMMENT '锁定库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商品sku库存表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_sku_stock`
--

LOCK TABLES `pms_sku_stock` WRITE;
/*!40000 ALTER TABLE `pms_sku_stock` DISABLE KEYS */;
INSERT INTO `pms_sku_stock` VALUES (1,1,'1',12.00,11,1,NULL,NULL,0,11.00,-3);
/*!40000 ALTER TABLE `pms_sku_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_coupon`
--

DROP TABLE IF EXISTS `sms_coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(1) DEFAULT NULL COMMENT '优惠券类型 0>全场赠送 1>会员赠送 2>购物赠送 3>注册赠送',
  `name` varchar(100) DEFAULT NULL,
  `platform` int(1) DEFAULT NULL COMMENT '使用平台 0>全平台 1>移动 2>pc',
  `count` int(11) DEFAULT NULL COMMENT '优惠券数量',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '优惠券金额',
  `per_limit` int(11) DEFAULT NULL COMMENT '每人限领数量',
  `min_point` decimal(10,2) DEFAULT NULL COMMENT '使用门槛 0>无门槛',
  `start_time` datetime DEFAULT NULL COMMENT '开始使用时间',
  `end_time` datetime DEFAULT NULL COMMENT '截止使用时间',
  `use_type` int(1) DEFAULT NULL COMMENT '适用类型 0>全场通用 1>指定分类 2>指定商品',
  `note` varchar(200) DEFAULT NULL COMMENT '备注',
  `publish_count` int(11) DEFAULT NULL COMMENT '发行数量',
  `use_count` int(11) DEFAULT NULL COMMENT '已使用数量',
  `receive_count` int(11) DEFAULT NULL COMMENT '领取数量',
  `enable_time` datetime DEFAULT NULL COMMENT '截至领取时间',
  `code` varchar(64) DEFAULT NULL COMMENT '优惠券编码',
  `member_level` int(1) DEFAULT NULL COMMENT '可领取会员等级 0>无限制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_coupon`
--

LOCK TABLES `sms_coupon` WRITE;
/*!40000 ALTER TABLE `sms_coupon` DISABLE KEYS */;
/*!40000 ALTER TABLE `sms_coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_coupon_history`
--

DROP TABLE IF EXISTS `sms_coupon_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_coupon_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `coupon_id` bigint(20) DEFAULT NULL COMMENT '优惠券id',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `coupon_code` varchar(64) DEFAULT NULL COMMENT '优惠券编码',
  `member_nickname` varchar(64) DEFAULT NULL,
  `get_type` int(1) DEFAULT NULL COMMENT '获取类型 0>后台赠送 1>主动获取',
  `create_time` datetime DEFAULT NULL,
  `use_status` int(1) DEFAULT NULL COMMENT '使用状态 0>未使用 1>已使用 2>已过期',
  `use_time` datetime DEFAULT NULL COMMENT '使用时间',
  `order_sn` varchar(100) DEFAULT NULL COMMENT '订单编码',
  PRIMARY KEY (`id`),
  KEY `idx_coupon_id` (`coupon_id`),
  KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券领取使用记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_coupon_history`
--

LOCK TABLES `sms_coupon_history` WRITE;
/*!40000 ALTER TABLE `sms_coupon_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `sms_coupon_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_coupon_product_category_relation`
--

DROP TABLE IF EXISTS `sms_coupon_product_category_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_coupon_product_category_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `coupon_id` bigint(20) DEFAULT NULL COMMENT '优惠券id',
  `product_category_id` bigint(20) DEFAULT NULL COMMENT '商品分类id',
  `product_category_name` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券与商品分类关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_coupon_product_category_relation`
--

LOCK TABLES `sms_coupon_product_category_relation` WRITE;
/*!40000 ALTER TABLE `sms_coupon_product_category_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `sms_coupon_product_category_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_coupon_product_relation`
--

DROP TABLE IF EXISTS `sms_coupon_product_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_coupon_product_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `coupon_id` bigint(20) DEFAULT NULL COMMENT '优惠券id',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `product_name` varchar(500) DEFAULT NULL,
  `product_sn` varchar(200) DEFAULT NULL COMMENT '商品编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券与商品关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_coupon_product_relation`
--

LOCK TABLES `sms_coupon_product_relation` WRITE;
/*!40000 ALTER TABLE `sms_coupon_product_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `sms_coupon_product_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_flash_promotion`
--

DROP TABLE IF EXISTS `sms_flash_promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_flash_promotion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL COMMENT '活动标题',
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '活动上下线状态 0>下线 1>上线',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='限时购活动表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_flash_promotion`
--

LOCK TABLES `sms_flash_promotion` WRITE;
/*!40000 ALTER TABLE `sms_flash_promotion` DISABLE KEYS */;
/*!40000 ALTER TABLE `sms_flash_promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_flash_promotion_log`
--

DROP TABLE IF EXISTS `sms_flash_promotion_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_flash_promotion_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `member_phone` varchar(64) DEFAULT NULL COMMENT '通知电话',
  `product_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `subscribe_time` datetime DEFAULT NULL COMMENT '会员订阅时间',
  `send_time` datetime DEFAULT NULL COMMENT '发送通知时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='限时购活动通知记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_flash_promotion_log`
--

LOCK TABLES `sms_flash_promotion_log` WRITE;
/*!40000 ALTER TABLE `sms_flash_promotion_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sms_flash_promotion_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_flash_promotion_product_relation`
--

DROP TABLE IF EXISTS `sms_flash_promotion_product_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_flash_promotion_product_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flash_promotion_id` bigint(20) DEFAULT NULL COMMENT '限时购活动id',
  `flash_promotion_session_id` bigint(20) DEFAULT NULL COMMENT '限时购活动场次id',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `flash_promotion_price` decimal(10,2) DEFAULT NULL COMMENT '活动价格',
  `flash_promotion_count` int(11) DEFAULT NULL COMMENT '抢购商品数量',
  `flash_promotion_limit` int(11) DEFAULT NULL COMMENT '每人限购数量',
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='限时购活动与商品关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_flash_promotion_product_relation`
--

LOCK TABLES `sms_flash_promotion_product_relation` WRITE;
/*!40000 ALTER TABLE `sms_flash_promotion_product_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `sms_flash_promotion_product_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_flash_promotion_session`
--

DROP TABLE IF EXISTS `sms_flash_promotion_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_flash_promotion_session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flash_promotion_id` bigint(20) DEFAULT NULL COMMENT '限时购活动id',
  `name` varchar(200) DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '启用状态 0>不启用 1>启用',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='限时购活动场次表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_flash_promotion_session`
--

LOCK TABLES `sms_flash_promotion_session` WRITE;
/*!40000 ALTER TABLE `sms_flash_promotion_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `sms_flash_promotion_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_home_advertise`
--

DROP TABLE IF EXISTS `sms_home_advertise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_home_advertise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '轮播位置 0>pc 1>app',
  `pic` varchar(500) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '上下线状态 0>下线 1>上线',
  `click_count` int(11) DEFAULT NULL COMMENT '点击数量',
  `order_count` int(11) DEFAULT NULL COMMENT '下单数量',
  `url` varchar(500) DEFAULT NULL COMMENT '链接地址',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='首页轮播广告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_home_advertise`
--

LOCK TABLES `sms_home_advertise` WRITE;
/*!40000 ALTER TABLE `sms_home_advertise` DISABLE KEYS */;
/*!40000 ALTER TABLE `sms_home_advertise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_home_brand`
--

DROP TABLE IF EXISTS `sms_home_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_home_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_id` bigint(20) DEFAULT NULL COMMENT '品牌id',
  `brand_name` varchar(64) DEFAULT NULL,
  `recommend_status` int(1) DEFAULT NULL COMMENT '推荐状态 0>不推荐 1>推荐',
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='首页品牌推荐表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_home_brand`
--

LOCK TABLES `sms_home_brand` WRITE;
/*!40000 ALTER TABLE `sms_home_brand` DISABLE KEYS */;
/*!40000 ALTER TABLE `sms_home_brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_home_new_product`
--

DROP TABLE IF EXISTS `sms_home_new_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_home_new_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `product_name` varchar(64) DEFAULT NULL,
  `recommend_status` int(1) DEFAULT NULL COMMENT '推荐状态 0>不推荐 1>推荐',
  `sort` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='首页新品推荐表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_home_new_product`
--

LOCK TABLES `sms_home_new_product` WRITE;
/*!40000 ALTER TABLE `sms_home_new_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `sms_home_new_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_home_recommend_product`
--

DROP TABLE IF EXISTS `sms_home_recommend_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_home_recommend_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `product_name` varchar(64) DEFAULT NULL,
  `recommend_status` int(1) DEFAULT NULL COMMENT '推荐状态 0>不推荐 1>推荐',
  `sort` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='首页人气推荐商品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_home_recommend_product`
--

LOCK TABLES `sms_home_recommend_product` WRITE;
/*!40000 ALTER TABLE `sms_home_recommend_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `sms_home_recommend_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_home_recommend_subject`
--

DROP TABLE IF EXISTS `sms_home_recommend_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_home_recommend_subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(20) DEFAULT NULL COMMENT '专题id',
  `subject_name` varchar(64) DEFAULT NULL COMMENT '专题名称',
  `recommend_status` int(1) DEFAULT NULL COMMENT '推荐状态 0>不推荐 1>推荐',
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='首页推荐专题表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_home_recommend_subject`
--

LOCK TABLES `sms_home_recommend_subject` WRITE;
/*!40000 ALTER TABLE `sms_home_recommend_subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `sms_home_recommend_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_admin`
--

DROP TABLE IF EXISTS `ums_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL,
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime DEFAULT NULL,
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) DEFAULT '1' COMMENT '账号启用状态 0>禁用 1>启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='后台用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_admin`
--

LOCK TABLES `ums_admin` WRITE;
/*!40000 ALTER TABLE `ums_admin` DISABLE KEYS */;
INSERT INTO `ums_admin` VALUES (1,'jitgur','$2a$10$thilSq8/FGT.xzI76FpfLueNQFyPX5TLdsWGyF7MfI8DDIRRjqbcO','string','178@126.com','string','string','2023-02-21 16:54:17','2023-02-21 16:54:17',1);
/*!40000 ALTER TABLE `ums_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_admin_login_log`
--

DROP TABLE IF EXISTS `ums_admin_login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_admin_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL COMMENT '后台用户id',
  `create_time` datetime DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `user_agent` varchar(100) DEFAULT NULL COMMENT '浏览器登录类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='后台用户登录记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_admin_login_log`
--

LOCK TABLES `ums_admin_login_log` WRITE;
/*!40000 ALTER TABLE `ums_admin_login_log` DISABLE KEYS */;
INSERT INTO `ums_admin_login_log` VALUES (1,1,'2023-02-21 16:49:22','192.168.0.109',NULL,NULL);
/*!40000 ALTER TABLE `ums_admin_login_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_admin_permission_relation`
--

DROP TABLE IF EXISTS `ums_admin_permission_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_admin_permission_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL COMMENT '后台用户id',
  `permission_id` bigint(20) DEFAULT NULL COMMENT '权限id',
  `type` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户与权限关系表（除角色中定义的权限以外的加减权限）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_admin_permission_relation`
--

LOCK TABLES `ums_admin_permission_relation` WRITE;
/*!40000 ALTER TABLE `ums_admin_permission_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `ums_admin_permission_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_admin_role_relation`
--

DROP TABLE IF EXISTS `ums_admin_role_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_admin_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL COMMENT '后台用户id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='后台用户角色关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_admin_role_relation`
--

LOCK TABLES `ums_admin_role_relation` WRITE;
/*!40000 ALTER TABLE `ums_admin_role_relation` DISABLE KEYS */;
INSERT INTO `ums_admin_role_relation` VALUES (1,1,1);
/*!40000 ALTER TABLE `ums_admin_role_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_member`
--

DROP TABLE IF EXISTS `ums_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_level_id` bigint(20) DEFAULT NULL COMMENT '会员等级id',
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(64) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '账号启用状态 0>禁用 1>启用',
  `create_time` datetime DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `gender` int(1) DEFAULT NULL COMMENT '性别 0>未知 1>男 2>女',
  `birthday` date DEFAULT NULL,
  `city` varchar(64) DEFAULT NULL,
  `job` varchar(100) DEFAULT NULL,
  `personalized_signature` varchar(200) DEFAULT NULL COMMENT '个性签名',
  `source_type` int(1) DEFAULT NULL COMMENT '用户来源',
  `integration` int(11) DEFAULT NULL COMMENT '积分数',
  `growth` int(11) DEFAULT NULL COMMENT '成长值',
  `lucky_count` int(11) DEFAULT NULL COMMENT '剩余抽奖次数',
  `history_integration` int(11) DEFAULT NULL COMMENT '历史积分数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_phone` (`phone`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='会员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_member`
--

LOCK TABLES `ums_member` WRITE;
/*!40000 ALTER TABLE `ums_member` DISABLE KEYS */;
INSERT INTO `ums_member` VALUES (1,NULL,'jitgur','$2a$10$oEMEovoMvpxWaAW5hu55Je/P8s6O4jVgqCSnAF4Ys0TI0sQ1T5Fae',NULL,'17806709693',1,'2023-02-21 00:13:52',NULL,NULL,NULL,NULL,NULL,NULL,NULL,133,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ums_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_member_growth_change_history`
--

DROP TABLE IF EXISTS `ums_member_growth_change_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_member_growth_change_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `create_time` datetime DEFAULT NULL,
  `change_type` int(1) DEFAULT NULL COMMENT '改变类型 0>增加 1>减少',
  `change_count` int(11) DEFAULT NULL COMMENT '改变数量',
  `operate_man` varchar(100) DEFAULT NULL COMMENT '操作人',
  `operate_note` varchar(200) DEFAULT NULL COMMENT '操作备注',
  `source_type` int(1) DEFAULT NULL COMMENT '来源类型 0>购物 1>登录赠送',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员成长值变化历史记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_member_growth_change_history`
--

LOCK TABLES `ums_member_growth_change_history` WRITE;
/*!40000 ALTER TABLE `ums_member_growth_change_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `ums_member_growth_change_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_member_integration_change_history`
--

DROP TABLE IF EXISTS `ums_member_integration_change_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_member_integration_change_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `create_time` datetime DEFAULT NULL,
  `change_type` int(1) DEFAULT NULL COMMENT '改变类型 0>增加 1>减少',
  `change_count` int(11) DEFAULT NULL COMMENT '改变数量',
  `operate_man` varchar(100) DEFAULT NULL COMMENT '操作人',
  `operate_note` varchar(200) DEFAULT NULL COMMENT '操作备注',
  `source_type` int(1) DEFAULT NULL COMMENT '积分来源 0>购物 1>登录赠送',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员积分变化历史记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_member_integration_change_history`
--

LOCK TABLES `ums_member_integration_change_history` WRITE;
/*!40000 ALTER TABLE `ums_member_integration_change_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `ums_member_integration_change_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_member_integration_consume_setting`
--

DROP TABLE IF EXISTS `ums_member_integration_consume_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_member_integration_consume_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deduction_per_amount` int(11) DEFAULT NULL COMMENT '每一元需要抵扣的积分数量',
  `max_percent_per_order` int(11) DEFAULT NULL COMMENT '每笔订单最高抵用百分比',
  `use_unit` int(11) DEFAULT NULL COMMENT '每次使用积分最小单位，100',
  `coupon_status` int(1) DEFAULT NULL COMMENT '是否可以与优惠券一起使用 0>不可以 1>可以',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='会员积分消费设置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_member_integration_consume_setting`
--

LOCK TABLES `ums_member_integration_consume_setting` WRITE;
/*!40000 ALTER TABLE `ums_member_integration_consume_setting` DISABLE KEYS */;
INSERT INTO `ums_member_integration_consume_setting` VALUES (1,100,10,100,0);
/*!40000 ALTER TABLE `ums_member_integration_consume_setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_member_level`
--

DROP TABLE IF EXISTS `ums_member_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_member_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `growth_point` int(11) DEFAULT NULL,
  `default_status` int(1) DEFAULT NULL COMMENT '是否为默认等级 0>不是 1>是',
  `free_freight_point` decimal(10,2) DEFAULT NULL COMMENT '免运费标准',
  `comment_growth_point` int(11) DEFAULT NULL COMMENT '每次评论获得的成长值',
  `privilege_free_freight` int(1) DEFAULT NULL COMMENT '是否有免运费特权 0>不是 1>是',
  `privilege_sign_in` int(1) DEFAULT NULL COMMENT '是否有签到特权 0>不是 1>是',
  `privilege_comment` int(1) DEFAULT NULL COMMENT '是否有评论获得奖励特权 0>不是 1>是',
  `privilege_promotion` int(1) DEFAULT NULL COMMENT '是否有专享活动特权 0>不是 1>是',
  `privilege_member_price` int(1) DEFAULT NULL COMMENT '是否有会员价格特权 0>不是 1>是',
  `privilege_birthday` int(1) DEFAULT NULL COMMENT '是否有生日特权 0>不是 1>是',
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员等级表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_member_level`
--

LOCK TABLES `ums_member_level` WRITE;
/*!40000 ALTER TABLE `ums_member_level` DISABLE KEYS */;
/*!40000 ALTER TABLE `ums_member_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_member_login_log`
--

DROP TABLE IF EXISTS `ums_member_login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_member_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `create_time` datetime DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `city` varchar(64) DEFAULT NULL,
  `login_type` int(1) DEFAULT NULL COMMENT '登录类型 0>PC 1>android 2>ios 3>小程序',
  `province` varchar(64) DEFAULT NULL COMMENT '省份',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员登录记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_member_login_log`
--

LOCK TABLES `ums_member_login_log` WRITE;
/*!40000 ALTER TABLE `ums_member_login_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `ums_member_login_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_member_receive_address`
--

DROP TABLE IF EXISTS `ums_member_receive_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_member_receive_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `name` varchar(100) DEFAULT NULL COMMENT '收货人名称',
  `phone_number` varchar(64) DEFAULT NULL,
  `default_status` int(1) DEFAULT NULL COMMENT '是否为默认地址 0>不是 1>是',
  `post_code` varchar(100) DEFAULT NULL COMMENT '邮编',
  `province` varchar(100) DEFAULT NULL COMMENT '省份',
  `city` varchar(100) DEFAULT NULL,
  `region` varchar(100) DEFAULT NULL,
  `detail_address` varchar(128) DEFAULT NULL COMMENT '详细地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='会员收货地址表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_member_receive_address`
--

LOCK TABLES `ums_member_receive_address` WRITE;
/*!40000 ALTER TABLE `ums_member_receive_address` DISABLE KEYS */;
INSERT INTO `ums_member_receive_address` VALUES (1,1,'jitgur','17806709690',1,NULL,'guan','g','fd','fd');
/*!40000 ALTER TABLE `ums_member_receive_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_member_rule_setting`
--

DROP TABLE IF EXISTS `ums_member_rule_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_member_rule_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `continue_sign_day` int(11) DEFAULT NULL COMMENT '连续登录天数',
  `continue_sign_point` int(11) DEFAULT NULL COMMENT '连续登录获取点数',
  `consume_per_point` decimal(10,2) DEFAULT NULL COMMENT '获取一个点数需要消费的金额',
  `low_order_amount` decimal(10,2) DEFAULT NULL COMMENT '能够获取点数的最低订单金额',
  `max_point_per_order` int(11) DEFAULT NULL COMMENT '每笔订单最高获取点数',
  `type` int(1) DEFAULT NULL COMMENT '类型 0>积分规则 1>成长值规则',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员积分成长值获取规则';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_member_rule_setting`
--

LOCK TABLES `ums_member_rule_setting` WRITE;
/*!40000 ALTER TABLE `ums_member_rule_setting` DISABLE KEYS */;
/*!40000 ALTER TABLE `ums_member_rule_setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_member_statistics_info`
--

DROP TABLE IF EXISTS `ums_member_statistics_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_member_statistics_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `consume_amount` decimal(10,2) DEFAULT NULL COMMENT '累计消费金额',
  `order_count` int(11) DEFAULT NULL COMMENT '订单数量',
  `coupon_count` int(11) DEFAULT NULL COMMENT '优惠券数量',
  `comment_count` int(11) DEFAULT NULL COMMENT '评论数量',
  `return_order_count` int(11) DEFAULT NULL COMMENT '退货订单数量',
  `login_count` int(11) DEFAULT NULL COMMENT '登录次数',
  `attend_count` int(11) DEFAULT NULL COMMENT '关注数量',
  `fans_count` int(11) DEFAULT NULL COMMENT '粉丝数量',
  `collect_product_count` int(11) DEFAULT NULL COMMENT '商品收藏数量',
  `collect_subject_count` int(11) DEFAULT NULL COMMENT '专题收藏数量',
  `collect_topic_count` int(11) DEFAULT NULL COMMENT '话题收藏数量',
  `collect_comment_count` int(11) DEFAULT NULL COMMENT '评论收藏数量',
  `invite_friend_count` int(11) DEFAULT NULL,
  `recent_order_time` datetime DEFAULT NULL COMMENT '最后一次下单时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员统计信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_member_statistics_info`
--

LOCK TABLES `ums_member_statistics_info` WRITE;
/*!40000 ALTER TABLE `ums_member_statistics_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `ums_member_statistics_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_menu`
--

DROP TABLE IF EXISTS `ums_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级菜单id',
  `create_time` datetime DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL COMMENT '菜单标题',
  `level` int(4) DEFAULT NULL COMMENT '菜单等级',
  `sort` int(4) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '前端名称',
  `icon` varchar(200) DEFAULT NULL COMMENT '前端头像',
  `hidden` int(1) DEFAULT NULL COMMENT '前端是否隐藏 0>否 1>是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_menu`
--

LOCK TABLES `ums_menu` WRITE;
/*!40000 ALTER TABLE `ums_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `ums_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_permission`
--

DROP TABLE IF EXISTS `ums_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级权限id',
  `name` varchar(100) DEFAULT NULL,
  `value` varchar(200) DEFAULT NULL COMMENT '权限值',
  `icon` varchar(500) DEFAULT NULL COMMENT '图标',
  `type` int(1) DEFAULT NULL COMMENT '权限类型 0>目录 1>菜单 2>按钮',
  `uri` varchar(200) DEFAULT NULL COMMENT '资源路径',
  `status` int(1) DEFAULT NULL COMMENT '状态 0>禁用 1>启用',
  `create_time` datetime DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_permission`
--

LOCK TABLES `ums_permission` WRITE;
/*!40000 ALTER TABLE `ums_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `ums_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_resource`
--

DROP TABLE IF EXISTS `ums_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) DEFAULT NULL COMMENT '资源url',
  `description` varchar(500) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL COMMENT '资源分类id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='后台资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_resource`
--

LOCK TABLES `ums_resource` WRITE;
/*!40000 ALTER TABLE `ums_resource` DISABLE KEYS */;
INSERT INTO `ums_resource` VALUES (1,'2023-02-21 17:08:34','后台用户管理','/admin/**',NULL,1);
/*!40000 ALTER TABLE `ums_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_resource_category`
--

DROP TABLE IF EXISTS `ums_resource_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_resource_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '资源分类名称',
  `sort` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='后台资源分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_resource_category`
--

LOCK TABLES `ums_resource_category` WRITE;
/*!40000 ALTER TABLE `ums_resource_category` DISABLE KEYS */;
INSERT INTO `ums_resource_category` VALUES (1,'2023-02-21 17:07:15','admin',1);
/*!40000 ALTER TABLE `ums_resource_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_role`
--

DROP TABLE IF EXISTS `ums_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(500) DEFAULT NULL,
  `admin_count` int(11) DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT '1' COMMENT '启用状态 0>禁用 1>启用',
  `sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='后台用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_role`
--

LOCK TABLES `ums_role` WRITE;
/*!40000 ALTER TABLE `ums_role` DISABLE KEYS */;
INSERT INTO `ums_role` VALUES (1,'超级管理员',NULL,1,'2023-02-21 17:00:23',1,1);
/*!40000 ALTER TABLE `ums_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_role_menu_relation`
--

DROP TABLE IF EXISTS `ums_role_menu_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_role_menu_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台角色与菜单关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_role_menu_relation`
--

LOCK TABLES `ums_role_menu_relation` WRITE;
/*!40000 ALTER TABLE `ums_role_menu_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `ums_role_menu_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_role_permission_relation`
--

DROP TABLE IF EXISTS `ums_role_permission_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_role_permission_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(20) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台角色与权限关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_role_permission_relation`
--

LOCK TABLES `ums_role_permission_relation` WRITE;
/*!40000 ALTER TABLE `ums_role_permission_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `ums_role_permission_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_role_resource_relation`
--

DROP TABLE IF EXISTS `ums_role_resource_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ums_role_resource_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='后台角色与资源关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_role_resource_relation`
--

LOCK TABLES `ums_role_resource_relation` WRITE;
/*!40000 ALTER TABLE `ums_role_resource_relation` DISABLE KEYS */;
INSERT INTO `ums_role_resource_relation` VALUES (1,1,1);
/*!40000 ALTER TABLE `ums_role_resource_relation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-21 21:01:02
