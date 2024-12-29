-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: test_online
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `baithi`
--

DROP TABLE IF EXISTS `baithi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `baithi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tenbaithi` varchar(45) DEFAULT NULL,
  `monhoc` varchar(45) DEFAULT NULL,
  `socauhoi` int DEFAULT NULL,
  `thoigian` varchar(45) DEFAULT NULL,
  `id_giangvien` int DEFAULT NULL,
  `id_lop` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_giangvien` (`id_giangvien`),
  KEY `id_lop` (`id_lop`),
  CONSTRAINT `baithi_ibfk_1` FOREIGN KEY (`id_giangvien`) REFERENCES `giangvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baithi`
--

LOCK TABLES `baithi` WRITE;
/*!40000 ALTER TABLE `baithi` DISABLE KEYS */;
INSERT INTO `baithi` VALUES (14,'Ktra cuối kì 1','Toán',NULL,'00:15:00',3,'1'),(15,'Ktra giữa kì','Hóa',NULL,'00:20:00',2,'1'),(16,'Bài ktra cuối kì','Địa lý',NULL,'00:30:00',3,'1'),(17,'Bài ktra cuối kì','Lịch sử',NULL,'00:20:00',3,'1'),(25,'Bài Kiểm tra 15p','Sinh học',NULL,'00:15:00',3,'1'),(26,'Bài ktra cuối kì','âm nhạc',NULL,'00:15:00',3,'1'),(27,'Bài kiểm tra giữa kì','Triết',NULL,'00:40:00',3,'1');
/*!40000 ALTER TABLE `baithi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cauhoi`
--

DROP TABLE IF EXISTS `cauhoi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cauhoi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cauhoi` varchar(255) DEFAULT NULL,
  `dapandung` varchar(255) DEFAULT NULL,
  `dapan1` varchar(255) DEFAULT NULL,
  `dapan2` varchar(255) DEFAULT NULL,
  `dapan3` varchar(255) DEFAULT NULL,
  `dapan4` varchar(255) DEFAULT NULL,
  `id_baithi` int DEFAULT NULL,
  `cauhoiso` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_baithi` (`id_baithi`),
  CONSTRAINT `cauhoi_ibfk_1` FOREIGN KEY (`id_baithi`) REFERENCES `baithi` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cauhoi`
--

LOCK TABLES `cauhoi` WRITE;
/*!40000 ALTER TABLE `cauhoi` DISABLE KEYS */;
INSERT INTO `cauhoi` VALUES (18,'What is 5 + 3?','8','6',' 7',' 8',' 9',14,'1'),(19,'Solve y in equation y - 7 = 3.','10','8',' 9',' 10',' 11',14,'3'),(20,'Solve z in equation z/2 = 6.','12','10',' 11',' 12',' 13',14,'4'),(21,'Solve x in equation x^2 = 49.','7','5',' 6',' 7',' 8',14,'5'),(22,'Solve x in equation 2x = 10.','5','3',' 4',' 5',' 6',14,'2'),(23,'Name the largest planet in the solar system.','Jupiter','Earth',' Saturn',' Jupiter',' Mars',15,'3'),(24,'What is the square root of 64?','8','6',' 7',' 8',' 9',15,'2'),(25,'What is the chemical symbol for gold?','Au','Au',' Ag',' Fe',' Hg',15,'1'),(26,'What is the chemical formula for water?','H2O','O2',' H2O',' CO2',' COOH',15,'5'),(27,'What is the freezing point of water (°C)?','0','3',' 0',' 1',' 2',15,'4'),(28,'What is the capital of France?','Paris','Berlin',' London',' Paris',' Madrid',16,'1'),(29,'Who was the famous queen of Egypt?','Cleopatra','Cleopatra',' Nefertiti',' Elizabeth I',' Catherine the Great',16,'4'),(30,'Which river is the longest in the world?','Nile','Nile',' Amazon',' Ganges',' Yangtze',16,'2'),(31,'What is the capital of Ancient Egypt?','Thebes','Athens',' Rome',' Cairo',' Thebes',16,'4'),(32,'Who discovered America?','Christopher Columbus','Christopher Columbus',' Ferdinand Magellan',' Marco Polo',' Leif Erikson',16,'3'),(33,'In which year did World War II end?','1945.0','1943',' 1944',' 1945',' 1946',16,'2'),(35,'What is the process by which plants make their food?','Photosynthesis','Respiration',' Photosynthesis',' Fermentation',' Digestion',25,'3'),(36,'What is the basic unit of life?','Cell','Cell',' Atom',' Tissue',' Organ',25,'1'),(37,'Which organelle is known as the powerhouse of the cell?','Mitochondria','Nucleus',' Mitochondria',' Ribosome',' Chloroplast',25,'2'),(38,'What is the genetic material in most organisms?','DNA','DNA',' RNA',' Protein',' Lipid',25,'4'),(39,'What is the term for the speed of a musical piece?','Tempo','Tempo',' Pitch',' Rhythm',' Harmony',16,'1'),(40,'What is the name of the five-line staff used in musical notation?','Staff','Bar Line',' Clef',' Staff',' Note',16,'3'),(41,'Who is the composer of \"Fur Elise\"?','Beethoven','Mozart',' Beethoven',' Bach',' Chopin',16,'4'),(42,'Which instrument is known as the \"king of instruments\"?','Organ','Piano',' Violin',' Organ',' Guitar',16,'2'),(43,'Which philosopher wrote \"The Republic\"?','Plato','Plato',' Kant',' Hegel',' Nietzsche',27,'3'),(44,'What is the term for the study of the nature of reality?','Metaphysics','Epistemology',' Ethics',' Metaphysics',' Logic',27,'2'),(45,'Who is considered the father of Western philosophy?','Socrates','Socrates',' Plato',' Aristotle',' Descartes',27,'1'),(46,'What is the concept of \"existence precedes essence\" linked to?','Existentialism','Existentialism',' Idealism',' Realism',' Pragmatism',27,'4');
/*!40000 ALTER TABLE `cauhoi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giangvien`
--

DROP TABLE IF EXISTS `giangvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giangvien` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `fullname` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giangvien`
--

LOCK TABLES `giangvien` WRITE;
/*!40000 ALTER TABLE `giangvien` DISABLE KEYS */;
INSERT INTO `giangvien` VALUES (1,'thanhtuan','123','Ngo Thanh Tuan','ngothanhtuan@gmail.com'),(2,'thanhkha','123','thanhkha','dsfjh'),(3,'huyvu','123','Huyvu','dewe'),(4,'longthien','123','Long Thien','lt@mail.com');
/*!40000 ALTER TABLE `giangvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lop`
--

DROP TABLE IF EXISTS `lop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lop` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lop` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lop`
--

LOCK TABLES `lop` WRITE;
/*!40000 ALTER TABLE `lop` DISABLE KEYS */;
INSERT INTO `lop` VALUES (1,'12A'),(2,'12B');
/*!40000 ALTER TABLE `lop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sinhvien`
--

DROP TABLE IF EXISTS `sinhvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sinhvien` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `fullname` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `class` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sinhvien`
--

LOCK TABLES `sinhvien` WRITE;
/*!40000 ALTER TABLE `sinhvien` DISABLE KEYS */;
INSERT INTO `sinhvien` VALUES (1,'longthien','123','Le Long Thien','longthienl80@gmail.com','12A'),(2,'thanhkha','123','Nguyen Thanh Kha','kha@gmail.com','12A'),(3,'thanhkhaa','123','Nguyen Thanh Kha','kha@gmail.com','12A');
/*!40000 ALTER TABLE `sinhvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thongke`
--

DROP TABLE IF EXISTS `thongke`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thongke` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_sinhvien` int DEFAULT NULL,
  `id_baithi` int DEFAULT NULL,
  `diem` varchar(45) DEFAULT NULL,
  `thoigianlambai` varchar(100) DEFAULT NULL,
  `ngaylam` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_sinhvien` (`id_sinhvien`),
  KEY `id_baithi` (`id_baithi`),
  CONSTRAINT `thongke_ibfk_1` FOREIGN KEY (`id_sinhvien`) REFERENCES `sinhvien` (`id`),
  CONSTRAINT `thongke_ibfk_2` FOREIGN KEY (`id_baithi`) REFERENCES `baithi` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thongke`
--

LOCK TABLES `thongke` WRITE;
/*!40000 ALTER TABLE `thongke` DISABLE KEYS */;
INSERT INTO `thongke` VALUES (15,3,14,'0','00:00:24','20/12/2024'),(16,3,14,'80','00:00:11','21/12/2024'),(17,2,15,'60','00:00:51','22/12/2024'),(18,2,15,'44','00:10:51','22/12/2024'),(19,2,14,'60','00:10:10','22/12/2024'),(20,2,16,'80','00:15:51','25/12/2024'),(21,3,16,'40','00:20:51','25/12/2024'),(22,3,17,'50','00:25:51','21/12/2024'),(24,2,16,'33','00:00:15','25/12/2024'),(25,3,14,'80','00:01:01','25/12/2024'),(26,2,16,'33','00:01:02','25/12/2024'),(28,2,25,'0','00:00:22','25/12/2024'),(29,3,25,'0','00:00:39','25/12/2024'),(30,2,16,'16','00:00:24','26/12/2024'),(31,2,15,'60','00:00:16','27/12/2024'),(32,2,27,'75','00:00:19','28/12/2024');
/*!40000 ALTER TABLE `thongke` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-29 10:17:32
