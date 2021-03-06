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
  PRIMARY KEY (`item_id`),
  CONSTRAINT `item_attribute_item_info_item_id_fk` FOREIGN KEY (`item_id`) REFERENCES `item_info` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品属性';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_attribute`
--

LOCK TABLES `item_attribute` WRITE;
/*!40000 ALTER TABLE `item_attribute` DISABLE KEYS */;
INSERT INTO `item_attribute` VALUES (1,'black','42',1499.00,NULL,'2021-03-05 10:22:35',30,5),(2,'white','44',999.00,NULL,'2021-03-01 10:28:14',25,0),(3,'red','40',299.00,NULL,'2021-02-05 10:31:34',5,1),(4,'pink','L',299.00,499.00,'2021-03-06 10:37:29',10,2),(5,'white','36',620.00,NULL,'2021-03-05 17:56:40',20,10),(6,'black','34',210.00,240.00,'2021-03-05 21:57:30',30,12),(7,'colorful','XL',300.00,320.00,'2021-03-02 21:58:15',4,20);
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
INSERT INTO `item_category` VALUES (1,'men','running shoes','shoes'),(2,'men','basketball shoes','shoes'),(3,'men','slippers','shoes'),(4,'women','sweatshirts','clothing'),(5,'kids','running shoes','shoes'),(6,'kids','running shoes','shoes'),(7,'women','T-shirt','clothing');
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
  `item_name` varchar(32) NOT NULL COMMENT '商品名',
  `item_pic1` varchar(512) DEFAULT NULL COMMENT '商品图片1',
  `item_pic2` varchar(512) DEFAULT NULL COMMENT '商品图片2',
  `item_pic3` varchar(512) DEFAULT NULL COMMENT '商品图片3',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_info`
--

LOCK TABLES `item_info` WRITE;
/*!40000 ALTER TABLE `item_info` DISABLE KEYS */;
INSERT INTO `item_info` VALUES (1,'Nike Air VaporMax EVO NRG','../static/air-vapormax-evo-nrg-0.jpg','../static/air-vapormax-evo-nrg-1.jpg','../static/air-vapormax-evo-nrg-2.jpg'),(2,'Kyrie 7 EP','../static/kyrie-7-ep-0.jpg','../static/kyrie-7-ep-1.jpg','../static/kyrie-7-ep-2.jpg'),(3,'Nike Benassi JDI Print','../static/benassi-jdi-print-0.jpg','../static/benassi-jdi-print-1.jpg','../static/benassi-jdi-print-2.jpg'),(4,'Nike Sportswear','../static/sportswear-0.jpg','../static/sportswear-1.jpg','../static/sportswear-2.jpg'),(5,'Nike Air Max 90 EOI','../static/air-max-90-eoi-0.jpg','../static/air-max-90-eoi-1.jpg','../static/air-max-90-eoi-2.jpg'),(6,'Tensaur Shoes Black','../static/Tensaur_Shoes_Black-0.jpg','../static/Tensaur_Shoes_Black-1.jpg','../static/Tensaur_Shoes_Black-2.jpg'),(7,'HER Studio London T-Shirt','../static/HER_Studio_London_T-Shirt-0.jpg','../static/HER_Studio_London_T-Shirt-1.jpg','../static/HER_Studio_London_T-Shirt-2.jpg');
/*!40000 ALTER TABLE `item_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-06 12:44:08
