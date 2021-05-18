-- MySQL dump 10.13  Distrib 8.0.23, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: sports_store
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `item_attribute`
--

DROP TABLE IF EXISTS `item_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_attribute` (
  `item_id` int NOT NULL COMMENT '商品id',
  `item_color` varchar(16) NOT NULL COMMENT '商品颜色',
  `item_size` varchar(16) NOT NULL COMMENT '商品大小',
  `current_price` decimal(8,2) NOT NULL COMMENT '当前价格',
  `previous_price` decimal(8,2) DEFAULT NULL COMMENT '原价',
  `update_time` datetime NOT NULL COMMENT '上架时间',
  `number_stock` int NOT NULL COMMENT '库存数量',
  `number_sale` int NOT NULL COMMENT '已售数量',
  `item_brand` varchar(32) NOT NULL,
  PRIMARY KEY (`item_id`),
  CONSTRAINT `item_attribute_item_info_item_id_fk` FOREIGN KEY (`item_id`) REFERENCES `item_info` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品属性';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_attribute`
--

LOCK TABLES `item_attribute` WRITE;
/*!40000 ALTER TABLE `item_attribute` DISABLE KEYS */;
INSERT INTO `item_attribute` VALUES (0,'黑色','42',999.00,1099.00,'2021-03-08 19:12:11',15,17,'李宁'),(1,'黑色','42',1499.00,NULL,'2021-03-05 10:22:35',30,5,'Nike'),(2,'白色','44',999.00,NULL,'2021-03-01 10:28:14',25,20,'Nike'),(3,'红色','40',299.00,NULL,'2021-02-05 10:31:34',0,6,'Nike'),(4,'粉色','L',299.00,499.00,'2021-03-06 10:37:29',9,3,'Nike'),(5,'白色','36',620.00,NULL,'2021-03-05 17:56:40',20,10,'Nike'),(6,'黑色','34',210.00,240.00,'2021-03-05 21:57:30',30,3,'Adidas'),(7,'彩色','XL',300.00,320.00,'2021-03-02 21:58:15',3,21,'Adidas'),(8,'黑色','M',520.00,550.00,'2021-03-01 21:35:34',40,1,'Nike'),(9,'黑色','M',150.00,NULL,'2021-03-06 21:41:42',20,3,'Nike'),(10,'红色','L',369.00,NULL,'2021-03-01 21:51:21',15,20,'Nike'),(11,'红色','M',199.00,209.00,'2021-03-13 16:39:55',20,1,'Nike');
/*!40000 ALTER TABLE `item_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_category`
--

DROP TABLE IF EXISTS `item_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_category` (
  `item_id` int NOT NULL COMMENT '商品id',
  `target_group` varchar(16) NOT NULL COMMENT '目标群体',
  `usage_style` varchar(64) NOT NULL COMMENT '用途或风格',
  `category_name` varchar(64) NOT NULL COMMENT '分类名',
  PRIMARY KEY (`item_id`),
  CONSTRAINT `item_category_item_info_item_id_fk` FOREIGN KEY (`item_id`) REFERENCES `item_info` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_category`
--

LOCK TABLES `item_category` WRITE;
/*!40000 ALTER TABLE `item_category` DISABLE KEYS */;
INSERT INTO `item_category` VALUES (0,'男子','跑鞋','鞋类'),(1,'男子','休闲鞋','鞋类'),(2,'男子','篮球鞋','鞋类'),(3,'男子','拖鞋','鞋类'),(4,'女子','运动服','服装'),(5,'儿童','休闲鞋','鞋类'),(6,'儿童','休闲鞋','鞋类'),(7,'女子','T恤','服装'),(8,'男子','包','配件'),(9,'女子','包','配件'),(10,'儿童','运动服','服装'),(11,'女子','帽子','配件');
/*!40000 ALTER TABLE `item_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_info`
--

DROP TABLE IF EXISTS `item_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_info` (
  `item_id` int NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `item_name` varchar(128) NOT NULL COMMENT '商品名',
  `item_pic1` varchar(512) DEFAULT NULL COMMENT '商品图片1',
  `item_pic2` varchar(512) DEFAULT NULL COMMENT '商品图片2',
  `item_pic3` varchar(512) DEFAULT NULL COMMENT '商品图片3',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_info`
--

LOCK TABLES `item_info` WRITE;
/*!40000 ALTER TABLE `item_info` DISABLE KEYS */;
INSERT INTO `item_info` VALUES (0,'烈骏ACE 2减震跑鞋','../static/Lining-liejun-ACE-men-0.jpg','../static/Lining-liejun-ACE-men-1.jpg','../static/Lining-liejun-ACE-men-2.jpg'),(1,'Air VaporMax EVO NRG','../static/air-vapormax-evo-nrg-0.jpg','../static/air-vapormax-evo-nrg-1.jpg','../static/air-vapormax-evo-nrg-2.jpg'),(2,'Kyrie 7 EP','../static/kyrie-7-ep-0.jpg','../static/kyrie-7-ep-1.jpg','../static/kyrie-7-ep-2.jpg'),(3,'Benassi JDI Print','../static/benassi-jdi-print-0.jpg','../static/benassi-jdi-print-1.jpg','../static/benassi-jdi-print-2.jpg'),(4,'Sportswear','../static/sportswear-0.jpg','../static/sportswear-1.jpg','../static/sportswear-2.jpg'),(5,'Air Max 90 EOI','../static/air-max-90-eoi-0.jpg','../static/air-max-90-eoi-1.jpg','../static/air-max-90-eoi-2.jpg'),(6,'Tensaur Shoes Black','../static/Tensaur_Shoes_Black-0.jpg','../static/Tensaur_Shoes_Black-1.jpg','../static/Tensaur_Shoes_Black-2.jpg'),(7,'HER Studio London T-Shirt','../static/HER_Studio_London_T-Shirt-0.jpg','../static/HER_Studio_London_T-Shirt-1.jpg','../static/HER_Studio_London_T-Shirt-2.jpg'),(8,'Sportswear Essentials','../static/sportswear-essentials-backpack-0.jpg','../static/sportswear-essentials-backpack-1.jpg','../static/sportswear-essentials-backpack-2.jpg'),(9,'Sportswear Heritage','../static/sportswear-heritage-hip-pack-0.jpg','../static/sportswear-heritage-hip-pack-1.jpg','../static/sportswear-heritage-hip-pack-2.jpg'),(10,'Printed Hoodie Sportswear','../static/sportswear-older-printed-hoodie-0.jpg','../static/sportswear-older-printed-hoodie-1.jpg','../static/sportswear-older-printed-hoodie-2.jpg'),(11,'F.C. Barcelona Beanie','../static/fc-barcelona-beanie-0.jpg','../static/fc-barcelona-beanie-1.jpg','../static/fc-barcelona-beanie-2.jpg');
/*!40000 ALTER TABLE `item_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_info`
--

DROP TABLE IF EXISTS `order_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_info` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `items_id` int DEFAULT NULL,
  `payment_id` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `items_price` decimal(8,2) DEFAULT NULL,
  `delivery_type` varchar(32) DEFAULT NULL,
  `delivery_price` decimal(8,2) DEFAULT NULL,
  `payment_method` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `order_info_user_info_user_id_fk` (`user_id`),
  KEY `order_info_payment_info_payment_id_fk` (`payment_id`),
  KEY `order_info_order_items_info_items_id_fk` (`items_id`),
  CONSTRAINT `order_info_payment_info_payment_id_fk` FOREIGN KEY (`payment_id`) REFERENCES `payment_info` (`payment_id`),
  CONSTRAINT `order_info_user_info_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_info`
--

LOCK TABLES `order_info` WRITE;
/*!40000 ALTER TABLE `order_info` DISABLE KEYS */;
INSERT INTO `order_info` VALUES (3,15,0,14,'2021-05-01 23:08:54',2498.00,'普通达',0.00,'微信'),(4,15,1,14,'2021-05-01 23:16:04',999.00,'普通达',0.00,'微信'),(7,15,4,14,'2021-05-02 17:27:42',1140.00,'普通达',0.00,'支付宝'),(8,15,5,14,'2021-05-02 23:18:31',1298.00,'超级快递达',30.00,'支付宝'),(9,15,6,14,'2021-05-02 23:23:31',299.00,'普通达',0.00,'微信支付'),(10,15,7,14,'2021-05-02 23:30:05',299.00,'普通达',0.00,'支付宝'),(11,15,8,14,'2021-05-02 23:35:01',1196.00,'普通达',0.00,'支付宝'),(12,40,9,16,'2021-05-03 01:00:36',5295.00,'超级快递达',30.00,'微信支付'),(13,15,10,14,'2021-05-03 21:01:08',299.00,'普通达',0.00,'支付宝');
/*!40000 ALTER TABLE `order_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `items_id` int NOT NULL,
  `cart_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (5,0,10),(6,0,11),(7,1,18),(12,4,17),(13,4,19),(14,5,20),(15,5,21),(16,6,24),(17,7,25),(18,8,26),(19,9,29),(20,9,30),(21,10,28);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_info`
--

DROP TABLE IF EXISTS `payment_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_info` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `family_name` varchar(16) NOT NULL,
  `given_name` varchar(16) NOT NULL,
  `province` varchar(16) NOT NULL,
  `city` varchar(16) NOT NULL,
  `district` varchar(16) NOT NULL,
  `detailed_address` varchar(64) NOT NULL,
  `payment_tel` varchar(16) NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `payment_info_user_info_user_id_fk` (`user_id`),
  CONSTRAINT `payment_info_user_info_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_info`
--

LOCK TABLES `payment_info` WRITE;
/*!40000 ALTER TABLE `payment_info` DISABLE KEYS */;
INSERT INTO `payment_info` VALUES (2,1,'sdaf','fdsf','dfdff','dfffd','das','dsfasf','231424'),(7,20,'峥岩','李','天津市','市辖区','','天津市太极区parkwaygate','18623608909'),(14,15,'gjhg','sa','北京市','市辖区','东城区','sasa','11111111111'),(16,40,'李','峥岩','山西省','太原市','小店区','山西省太原市坞城路11','18523003581');
/*!40000 ALTER TABLE `payment_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_cart`
--

DROP TABLE IF EXISTS `user_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `item_id` int NOT NULL,
  `item_num` int NOT NULL,
  `is_valid` tinyint(1) DEFAULT '1',
  `is_paid` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`cart_id`),
  KEY `user_basket_item_info_item_id_fk` (`item_id`),
  KEY `user_basket_user_info_user_id_fk` (`user_id`),
  CONSTRAINT `user_basket_item_info_item_id_fk` FOREIGN KEY (`item_id`) REFERENCES `item_info` (`item_id`),
  CONSTRAINT `user_basket_user_info_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_cart`
--

LOCK TABLES `user_cart` WRITE;
/*!40000 ALTER TABLE `user_cart` DISABLE KEYS */;
INSERT INTO `user_cart` VALUES (2,1,1,1,1,0),(12,16,7,1,1,0),(13,16,1,1,1,0),(14,16,8,1,1,0),(15,20,4,1,1,0),(24,15,3,1,1,1),(25,15,3,5,1,1),(26,15,3,4,1,1),(27,15,1,1,1,0),(28,15,4,1,1,1),(29,40,0,5,1,1),(30,40,7,1,1,1);
/*!40000 ALTER TABLE `user_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `user_tel` varchar(16) NOT NULL,
  `user_password` varchar(128) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_info_user_name_uindex` (`user_name`),
  UNIQUE KEY `user_info_user_tel_uindex` (`user_tel`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'SongtaoWang','18215257896','$2a$10$.rtjF13hpkRHVtV287md4.DLyhSIoXCJDZiqPf.aSXo3APIIRJcSG'),(15,'765','22222222222','$2a$10$6VTMBSYfu8VpIS8LxFjb5.O29nB8F6Ft7rRBxiqYCoN1Md1oyQbfC'),(16,'7656','22222222223','$2a$10$nLedWpITukuvL2lcf7kTCOceZn9EDr7qjCj8fO062.nPokWXRLRu6'),(17,'7688','22222222277','$2a$10$hNNDGB2a/o4Dq7bAQDNqOep0xh/eInu9UKDzLsEEOV1EqvNn7ZhoK'),(18,'765jjjj','22222222299','$2a$10$3xJCeCNVxYdFyKWlKzbGUeM9GV8KZhO/UXzcU4tVWyIX4tIZbkAFK'),(19,'lzy250','12345678900','$2a$10$baLH1qJhGbMhcc7fOpssjuyc0GF38xKvCSLyfBmMWM9ZkrX1loX06'),(20,'1234','4','$2a$10$Ee/L1F2yt/B.Oko2npUTOOvkuMY4JqikY88BLLKC9e8LmjSd/TMC2'),(40,'eee','13902020202','$2a$10$ngXo.yQzDSJy1VBUb/hqY.65YLN97P/6am6hVgVFZOlVKSyhqyfF.'),(41,'34ds','13982828282','$2a$10$aitMQ2v87oZw76.HzENXKuu9Yn/eYFAETv1fwQWiM0rjSCGcrLLn2');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-18 16:52:20
