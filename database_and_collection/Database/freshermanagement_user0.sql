-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: freshermanagement
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

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
  `position_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKajxjckf0bsbkrsd5qc1kvnee0` (`position_id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,'HN','01234','12-11-2002','viet@gmail.com','Nguyen Viet','$2a$10$SdfvvnGWtSxgKE10eDOCdedbQuPcFoZMOANHEh9psdzSFf7eXz4eO',100000,'1234567',_binary '','admin',2),(8,'HN','01234','12-11-2002','v@gmail.com','Nguyen V','$2a$10$kxbOFJHdjRXB4k5h5hgCZuRmNXuzNMS983vauE92NIY8IpktNygLy',1000,'123456',_binary '','manager1',3),(9,'NY','01234','12-11-2002','c@gmail.com','Tran C','$2a$10$Z43A.DgzyvJGeqCJaMuijef0SEpjo3hyqXTEeY5oVYwHloggwy8zC',1000,'834928',_binary '','manager2',3),(10,'HN','123456','12-2-2002','son@gmail.com','Son','fres1',10,'0938277',_binary '','fres1',NULL),(11,'BK','00123','12-11-2002','vi@gmail.com','Viet','fres2',10,'09876',_binary '','fres2',NULL),(12,'123 Main St','123456789','1990-01-01','johndoe@example.com','John Doe','password',5000,'0901234567',_binary '\0','johndoe',NULL),(13,'123 Main St','123456789','1990-01-01','johndoe@example.com','John Doe','password',5000,'0901234567',_binary '','johndoe',NULL),(14,'123 Main St','123456789','1990-01-01','johndoe@example.com','John Doe','password',5000,'0901234567',_binary '','johndoe',NULL),(15,'123 Main St','123456789','1990-01-01','johndoe@example.com','John Doe','password',5000,'0901234567',_binary '','johndoe',1),(16,'123 Main St','123456789','1990-01-01','johndoe@example.com','John Doe','password',5000,'0901234567',_binary '','frehs3',1),(17,'123 Mai','123456789','1990-01-01','johndoe@example.com','John Doe','$2a$10$.qcapp50DTETrDBmVLFtKOEZ59f8A0Awno3nVOhI/oFjMZkX6nyl6',5000,'0901234567',_binary '','fresh3',1);
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

-- Dump completed on 2024-07-04 16:44:37
