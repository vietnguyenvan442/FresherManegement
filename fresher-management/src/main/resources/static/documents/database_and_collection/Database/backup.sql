-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: freshermanagement
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (5);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `area` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (1,NULL,'Japane'),(2,NULL,'America'),(3,NULL,'VietNam');
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `center`
--

DROP TABLE IF EXISTS `center`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `center` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sdt` varchar(255) DEFAULT NULL,
  `state` bit(1) NOT NULL,
  `area_id` int DEFAULT NULL,
  `manager_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2y8h5v6omi4yjwp3cfe6nh4d0` (`area_id`),
  KEY `FKexkjvhqc1fea7mbntbip67wc2` (`manager_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `center`
--

LOCK TABLES `center` WRITE;
/*!40000 ALTER TABLE `center` DISABLE KEYS */;
INSERT INTO `center` VALUES (5,'123 HN','This is a new center','center@example.com','Center 3','123456789',_binary '',3,8),(6,'123 HN','This is a new center','center@example.com','Center 4','123456789',_binary '',3,8),(7,'NY','This is a center 5','c5@example.com','Center5','0328634564',_binary '\0',2,9),(8,'NY','This is a center 6','c6@example.com','Center 6','0328634',_binary '',2,9),(9,'HP','This is a center 7','c7@example.com','Center 9','0328678094',_binary '',1,9),(10,'HP','This is a center 8','c8@example.com','Center 8','0328678004',_binary '\0',2,9),(11,'HP','This is a center 11','c11@example.com','Center 11','0328678005',_binary '\0',2,9);
/*!40000 ALTER TABLE `center` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_time` date DEFAULT NULL,
  `center_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt3ghp7629iiejx65tf4cc4cun` (`center_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,NULL,'2024-06-01','Dao tao fresher VMO','2024-08-01',5),(2,'k2','2024-08-11','K2','2024-06-01',6),(3,'k3','2024-09-10','k3','2024-07-01',8),(4,'k4','2024-10-01','k4','2024-08-01',9);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fresher`
--

DROP TABLE IF EXISTS `fresher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fresher` (
  `id` int NOT NULL,
  `language_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2vk7p2wu3872e0y3rumli5abb` (`language_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fresher`
--

LOCK TABLES `fresher` WRITE;
/*!40000 ALTER TABLE `fresher` DISABLE KEYS */;
INSERT INTO `fresher` VALUES (10,1),(11,1),(15,2),(17,3),(18,3),(19,1),(20,2);
/*!40000 ALTER TABLE `fresher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,NULL,'JAVA'),(2,NULL,'.NET'),(3,NULL,'NOTEJS'),(4,NULL,'FLUTTER');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (8),(9);
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merger`
--

DROP TABLE IF EXISTS `merger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `merger` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `center_first_id` int DEFAULT NULL,
  `center_new_id` int DEFAULT NULL,
  `center_second_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbm94ymlt16fw5k3nx7n5ik7br` (`center_first_id`),
  KEY `FK5sn0woafucyx1272rqgb1tpnk` (`center_new_id`),
  KEY `FKlici7nfdjrnciyhb7jg4566hv` (`center_second_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merger`
--

LOCK TABLES `merger` WRITE;
/*!40000 ALTER TABLE `merger` DISABLE KEYS */;
INSERT INTO `merger` VALUES (1,'2024-07-12',10,10,11),(2,'2024-07-12',10,9,9);
/*!40000 ALTER TABLE `merger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rank`
--

DROP TABLE IF EXISTS `rank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rank` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rank`
--

LOCK TABLES `rank` WRITE;
/*!40000 ALTER TABLE `rank` DISABLE KEYS */;
INSERT INTO `rank` VALUES (1,NULL,'1'),(2,NULL,'2'),(3,NULL,'3');
/*!40000 ALTER TABLE `rank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `record`
--

DROP TABLE IF EXISTS `record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `end_time` date DEFAULT NULL,
  `start_time` date DEFAULT NULL,
  `fresher_id` int DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgkqa95wcbbcd9xyi2mlnsyc6c` (`course_id`),
  KEY `FKpjcjfnqda4olcdmp5nwk7ch2f` (`fresher_id`),
  KEY `FKph0k3g342ww77i7bfuix7m9f0` (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record`
--

LOCK TABLES `record` WRITE;
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
INSERT INTO `record` VALUES (1,'2024-07-04','2024-07-03',17,1,1),(2,NULL,'2024-07-03',17,1,1),(3,'2024-07-04','2024-07-04',17,1,1),(4,'2024-07-04','2024-07-04',17,1,1),(5,NULL,'2024-07-04',17,1,1),(6,NULL,'2024-07-12',18,2,1),(7,NULL,'2024-07-12',19,3,1),(8,NULL,'2024-07-12',20,4,1);
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result` (
  `id` int NOT NULL AUTO_INCREMENT,
  `point` float NOT NULL,
  `test_id` int DEFAULT NULL,
  `end_test` datetime DEFAULT NULL,
  `start_test` datetime DEFAULT NULL,
  `fresher_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgl2xou1u7d6jrri2k1v85npw3` (`fresher_id`),
  KEY `FKsyvhlvlv6k1d4gkqvu12rha0j` (`test_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` VALUES (1,8,6,'2024-06-20 09:00:00','2024-06-01 08:00:00',17),(2,7,7,'2024-07-15 09:30:00','2024-07-15 08:00:00',17),(3,9,8,'2024-08-01 10:00:00','2024-08-01 08:00:00',17),(4,4,6,'2024-06-15 09:00:00','2024-06-15 08:00:00',18),(5,5,7,'2024-07-15 09:30:00','2024-07-15 08:00:00',18),(6,6,6,'2024-07-10 09:00:00','2024-07-10 08:00:00',19),(7,7,7,'2024-08-10 09:30:00','2024-08-10 08:00:00',19),(8,8,8,'2024-08-30 10:00:00','2024-08-30 08:00:00',19),(9,4,6,'2024-08-10 09:00:00','2024-08-10 08:00:00',20),(10,5,7,'2024-09-10 09:30:00','2024-09-10 08:00:00',20),(11,6,8,'2024-09-30 10:00:00','2024-09-30 08:00:00',20),(12,7,6,'2024-06-15 02:00:00','2024-06-15 01:00:00',18);
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,NULL,'FRESHER'),(2,NULL,'ADMIN'),(3,NULL,'MANAGER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test` (
  `id` int NOT NULL AUTO_INCREMENT,
  `period` int DEFAULT NULL,
  `start_time` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `rank_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf40k4y4x3i52fdg3e7nqx4fhh` (`rank_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (6,60,'2020-06-01','Thuat toan',1),(7,90,'2020-06-01','code',2),(8,120,'2020-06-01','Thuyet trinh',3);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `cccd` varchar(255) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salary` float NOT NULL,
  `sdt` varchar(255) DEFAULT NULL,
  `state` bit(1) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdl9dqp078pc03g6kdnxmnlqpc` (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,'HN','01234','12-11-2002','viet@gmail.com','Nguyen Viet','$2a$10$SdfvvnGWtSxgKE10eDOCdedbQuPcFoZMOANHEh9psdzSFf7eXz4eO',100000,'1234567',_binary '','admin',2),(8,'HN','01234','12-11-2002','v@gmail.com','Nguyen V','$2a$10$kxbOFJHdjRXB4k5h5hgCZuRmNXuzNMS983vauE92NIY8IpktNygLy',1000,'123456',_binary '','manager1',3),(9,'NY','01234','12-11-2002','c@gmail.com','Tran C','$2a$10$Z43A.DgzyvJGeqCJaMuijef0SEpjo3hyqXTEeY5oVYwHloggwy8zC',1000,'834928',_binary '','manager2',3),(10,'HN','123456','12-2-2002','son@gmail.com','Son','fres1',10,'0938277',_binary '\0','fres1',1),(11,'BK','00123','12-11-2002','vi@gmail.com','Viet','fres2',10,'09876',_binary '\0','fres2',1),(15,'123 Main St','123456789','1990-01-01','johndoe@example.com','John Doe','password',5000,'0901234567',_binary '','johndoe',1),(17,'123 Mai','123456789','1990-01-01','johndoe@example.com','John','$2a$10$.qcapp50DTETrDBmVLFtKOEZ59f8A0Awno3nVOhI/oFjMZkX6nyl6',0,'0901234567',_binary '','fresh3',1),(18,'123 Main St','12344590','1990-01-01','john4@example.com','son','$2a$10$hc1Z7DO487Vb4qdgwOHnbuPHCxZPpopmGw1GNilCP8H6Pe5ighIYW',0,'0909034567',_binary '','frehs4',1),(19,'123 Main St','1234450','1990-01-01','john5@example.com','John 5','$2a$10$gXinDYoUEk/Dk2YjxB3bkeSAucASfhZMehiAYkMWWRn5XZWpaD3C6',5000,'0909034587',_binary '','frehs5',1),(20,'123 Main St','1234470','1990-01-01','john6@example.com','John 6','$2a$10$kko65Kw2CvPziWBttjkA5u87QQbHBUvOjK.Fw3JDh2Mur/I21CKQy',5000,'0909037587',_binary '','frehs6',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-19  8:58:15
