-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: sistem
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `album` (
  `albumID` int NOT NULL AUTO_INCREMENT,
  `Album_Adi` varchar(45) NOT NULL,
  `Sanatci_Adi` varchar(45) NOT NULL,
  `SanatciID` int NOT NULL,
  `Tarih` varchar(45) NOT NULL,
  `Tur` varchar(45) NOT NULL,
  `TurID` int NOT NULL,
  PRIMARY KEY (`albumID`),
  KEY `sanatciID` (`SanatciID`) /*!80000 INVISIBLE */,
  KEY `turID` (`TurID`) /*!80000 INVISIBLE */,
  CONSTRAINT `album_ibfk_1` FOREIGN KEY (`SanatciID`) REFERENCES `sanatci` (`SanatciID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `album_ibfk_3` FOREIGN KEY (`TurID`) REFERENCES `tur` (`turID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` VALUES (2,'Thriller','Michael Jackson',4,'1982','Pop',1),(3,'Kara Toprak','Fazıl Say',5,'2013','Klasik',3),(4,'Francis Albert Sinatra','Frank Sinatra',6,'1967','Jazz',2),(5,'Like a Prayer','Madonna',7,'1989','Pop',1),(6,'In A Time Lapse','Ludovico Einaudi',8,'2013','Klasik',3);
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calmalistesi`
--

DROP TABLE IF EXISTS `calmalistesi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calmalistesi` (
  `listeID` int NOT NULL AUTO_INCREMENT,
  `Tur` varchar(45) NOT NULL,
  `kullaniciAdi` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`listeID`),
  KEY `kullaniciAdi` (`kullaniciAdi`) /*!80000 INVISIBLE */
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calmalistesi`
--

LOCK TABLES `calmalistesi` WRITE;
/*!40000 ALTER TABLE `calmalistesi` DISABLE KEYS */;
INSERT INTO `calmalistesi` VALUES (4,'Pop','asd'),(5,'Jazz','asd'),(6,'Klasik','asd'),(7,'Pop','ahmet'),(8,'Jazz','ahmet'),(9,'Klasik','ahmet'),(10,'Pop','ali'),(11,'Jazz','ali'),(12,'Klasik','ali'),(13,'Pop','ayşe'),(14,'Jazz','ayşe'),(15,'Klasik','ayşe'),(16,'Pop','emir'),(17,'Jazz','emir'),(18,'Klasik','emir'),(19,'Pop','burak'),(20,'Jazz','burak'),(21,'Klasik','burak'),(22,'Pop','murat'),(23,'Jazz','murat'),(24,'Klasik','murat'),(25,'Pop','mert'),(26,'Jazz','mert'),(27,'Klasik','mert');
/*!40000 ALTER TABLE `calmalistesi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calmalistesi_sarkilar`
--

DROP TABLE IF EXISTS `calmalistesi_sarkilar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calmalistesi_sarkilar` (
  `sarkiID` int NOT NULL,
  `listeSahibi` varchar(45) DEFAULT NULL,
  `listeID` int DEFAULT NULL,
  KEY `sarkiID` (`sarkiID`) /*!80000 INVISIBLE */,
  KEY `listeID` (`listeID`),
  CONSTRAINT `calmalistesi_sarkilar_ibfk_1` FOREIGN KEY (`sarkiID`) REFERENCES `sarki` (`SarkiID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `calmalistesi_sarkilar_ibfk_2` FOREIGN KEY (`listeID`) REFERENCES `calmalistesi` (`listeID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calmalistesi_sarkilar`
--

LOCK TABLES `calmalistesi_sarkilar` WRITE;
/*!40000 ALTER TABLE `calmalistesi_sarkilar` DISABLE KEYS */;
INSERT INTO `calmalistesi_sarkilar` VALUES (2,'ahmet',7),(3,'asd',4),(10,'asd',6),(8,'asd',4),(2,'asd',4),(4,'asd',4),(32,'asd',4),(23,'asd',5),(24,'asd',5),(13,'asd',6),(8,'ahmet',7),(6,'asd',4),(32,'ahmet',7),(6,'ahmet',7),(10,'ahmet',9),(24,'ahmet',8),(4,'ahmet',7),(32,'burak',19),(24,'emre',NULL),(8,'emre',NULL);
/*!40000 ALTER TABLE `calmalistesi_sarkilar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kullanici`
--

DROP TABLE IF EXISTS `kullanici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kullanici` (
  `kullanıcıId` int NOT NULL AUTO_INCREMENT,
  `Kullanici_Adi` varchar(45) NOT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Sifre` varchar(45) DEFAULT NULL,
  `Abonelik_Turu` varchar(45) DEFAULT NULL,
  `Ulke` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`kullanıcıId`,`Kullanici_Adi`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kullanici`
--

LOCK TABLES `kullanici` WRITE;
/*!40000 ALTER TABLE `kullanici` DISABLE KEYS */;
INSERT INTO `kullanici` VALUES (2,'emre','asdasd@gmail.com','12345','Normal','Türkiye'),(12,'erkan','erkan@hotmail.com','123','Normal','Türkiye'),(13,'asd','asd','123','Premium','Türkiye'),(14,'ahmet','ahmet@hotmail.com','123','Premium','Türkiye'),(15,'ali','ali@gmail.com','123','Normal','Türkiye'),(16,'ayşe','ayşe@gmail.com','123','Normal','Türkiye'),(17,'emir','emir@gmail.com','123','Normal','Türkiye'),(18,'burak','burak@gmail.com','123','Premium','Türkiye'),(19,'murat','murat@gmail.com','123','Premium','Türkiye'),(20,'mert','mert@gmail.com','123','Premium','Türkiye');
/*!40000 ALTER TABLE `kullanici` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanatci`
--

DROP TABLE IF EXISTS `sanatci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanatci` (
  `SanatciID` int NOT NULL AUTO_INCREMENT,
  `Sanatci_Adi` varchar(45) NOT NULL,
  `Sanatci_Ulkesi` varchar(45) NOT NULL,
  PRIMARY KEY (`SanatciID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanatci`
--

LOCK TABLES `sanatci` WRITE;
/*!40000 ALTER TABLE `sanatci` DISABLE KEYS */;
INSERT INTO `sanatci` VALUES (1,'Emre','Türkiye'),(2,'Ahmet','Türkiye'),(3,'Metallica','Amerika'),(4,'Michael Jackson','Amerika'),(5,'Fazıl Say','Türkiye'),(6,'Frank Sinatra','Amerika'),(7,'Madonna','Amerika'),(8,'Ludovico Einaudi','İtalya');
/*!40000 ALTER TABLE `sanatci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sarki`
--

DROP TABLE IF EXISTS `sarki`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sarki` (
  `SarkiID` int NOT NULL AUTO_INCREMENT,
  `Sarki_Adi` varchar(45) NOT NULL,
  `Tarih` varchar(45) NOT NULL,
  `Sanatci` varchar(45) NOT NULL,
  `SanatciID` int NOT NULL,
  `Album` varchar(45) NOT NULL,
  `AlbumID` int NOT NULL,
  `Tur` varchar(45) NOT NULL,
  `TurID` int NOT NULL,
  `Sure` varchar(45) NOT NULL,
  `Dinlenme_Sayisi` int NOT NULL,
  PRIMARY KEY (`SarkiID`),
  KEY `SanatciID` (`SanatciID`) /*!80000 INVISIBLE */,
  KEY `AlbumID` (`AlbumID`) /*!80000 INVISIBLE */,
  KEY `TurID` (`TurID`) /*!80000 INVISIBLE */,
  CONSTRAINT `sarki_ibfk_1` FOREIGN KEY (`SanatciID`) REFERENCES `sanatci` (`SanatciID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sarki_ibfk_2` FOREIGN KEY (`AlbumID`) REFERENCES `album` (`albumID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sarki_ibfk_3` FOREIGN KEY (`TurID`) REFERENCES `tur` (`turID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sarki`
--

LOCK TABLES `sarki` WRITE;
/*!40000 ALTER TABLE `sarki` DISABLE KEYS */;
INSERT INTO `sarki` VALUES (2,'Billie Jean','1982','Michael Jackson',4,'Thriller',2,'Pop',1,'5',30000000),(3,'P.Y.T','1982','Michael Jackson',4,'Thriller',2,'Pop',1,'4',5000000),(4,'Wanna Be Startin Somethin','1982','Michael Jackson',4,'Thriller',2,'Pop',1,'6',5000000),(5,'Baby Be Mine','1982','Michael Jackson',4,'Thriller',2,'Pop',1,'4',2000000),(6,'The Girl Is Mine','1982','Michael Jackson',4,'Thriller',2,'Pop',1,'4',2000000),(7,'Thriller','1982','Michael Jackson',4,'Thriller',2,'Pop',1,'4',30000000),(8,'Human Nature','1982','Michael Jackson',4,'Thriller',2,'Pop',1,'4',2000000),(9,'The Lady In My Life','1982','Michael Jackson',4,'Thriller',2,'Pop',1,'4',4500000),(10,'Black Earth','2013','Fazıl Say',5,'Kara Toprak',3,'Klasik',3,'5',500000),(11,'Violin Sonata','2013','Fazıl Say',5,'Kara Toprak',3,'Klasik',3,'5',200000),(12,'Piano Con','2013','Fazıl Say',5,'Kara Toprak',3,'Klasik',3,'5',200000),(13,'Silence of Anatolia','2013','Fazıl Say',5,'Kara Toprak',3,'Klasik',3,'5',200000),(14,'Silence of AnatoliaObstinacy ','2013','Fazıl Say',5,'Kara Toprak',3,'Klasik',3,'5',200000),(15,'Paganini Variations','2013','Fazıl Say',5,'Kara Toprak',3,'Klasik',3,'5',50000),(16,'Dervish in Manhattan','2013','Fazıl Say',5,'Kara Toprak',3,'Klasik',3,'3',120000),(17,'The Girl From Ipanema','1967','Frank Sinatra',6,'Francis Albert Sinatra',4,'Jazz',2,'3',29000000),(18,'Dindi','1967','Frank Sinatra',6,'Francis Albert Sinatra',4,'Jazz',2,'3',1500000),(19,'Change Partners','1967','Frank Sinatra',6,'Francis Albert Sinatra',4,'Jazz',2,'2',9300000),(20,'Quiet Nights of Quiet Stars','1967','Frank Sinatra',6,'Francis Albert Sinatra',4,'Jazz',2,'2',2400000),(21,'Meditation','1967','Frank Sinatra',6,'Francis Albert Sinatra',4,'Jazz',2,'2',108000),(22,'If You Never Come To Me','1967','Frank Sinatra',6,'Francis Albert Sinatra',4,'Jazz',2,'2',1100000),(23,'How Insensitive','1967','Frank Sinatra',6,'Francis Albert Sinatra',4,'Jazz',2,'3',2990000),(24,'I Concentrate On You','1967','Frank Sinatra',6,'Francis Albert Sinatra',4,'Jazz',2,'2',1490000),(25,'Baubles Bangles And Beads','1967','Frank Sinatra',6,'Francis Albert Sinatra',4,'Jazz',2,'2',1500000),(26,'Once I Loved','1967','Frank Sinatra',6,'Francis Albert Sinatra',4,'Jazz',2,'2',1100000),(27,'Sinatra/Jobim Medley','1967','Frank Sinatra',6,'Francis Albert Sinatra',4,'Jazz',2,'6',155000),(28,'Like a Prayer','1989','Madonna',7,'Like a Prayer',5,'Pop',1,'5',55000000),(29,'Express Yourself','1989','Madonna',7,'Like a Prayer',5,'Pop',1,'4',7100000),(30,'Love Song','1989','Madonna',7,'Like a Prayer',5,'Pop',1,'4',1300000),(31,'Till Death Do Us Part','1989','Madonna',7,'Like a Prayer',5,'Pop',1,'5',1800000),(32,'Promise to Try','1989','Madonna',7,'Like a Prayer',5,'Pop',1,'3',1500000),(33,'Cherish','1989','Madonna',7,'Like a Prayer',5,'Pop',1,'5',6800000),(34,'Dear Jessie','1989','Madonna',7,'Like a Prayer',5,'Pop',1,'4',3400000),(35,'Oh Father','1989','Madonna',7,'Like a Prayer',5,'Pop',1,'5',2800000),(36,'Keep It Together','1989','Madonna',7,'Like a Prayer',5,'Pop',1,'5',1400000),(37,'Pray for Spanish Eyes','1989','Madonna',7,'Like a Prayer',5,'Pop',1,'5',2700000),(38,'Act of Contrition','1989','Madonna',7,'Like a Prayer',5,'Pop',1,'2',706000),(39,'Corale','2013','Ludovico Einaudi',8,'In A Time Lapse',6,'Klasik',3,'2',11273429),(40,'Time Lapse','2013','Ludovico Einaudi',8,'In A Time Lapse',6,'Klasik',3,'5',19332401),(41,'Life','2013','Ludovico Einaudi',8,'In A Time Lapse',6,'Klasik',3,'4',19637121),(42,'Walk','2013','Ludovico Einaudi',8,'In A Time Lapse',6,'Klasik',3,'3',14683719),(43,'Discovery At Night','2013','Ludovico Einaudi',8,'In A Time Lapse',6,'Klasik',3,'4:25',11621979),(44,'Run','2013','Ludovico Einaudi',8,'In A Time Lapse',6,'Klasik',3,'5:32',16174077),(45,'Brothers','2013','Ludovico Einaudi',8,'In A Time Lapse',6,'Klasik',3,'4:51',11110007),(46,'Orbits','2013','Ludovico Einaudi',8,'In A Time Lapse',6,'Klasik',3,'2:57',7693553),(47,'Two Trees','2013','Ludovico Einaudi',8,'In A Time Lapse',6,'Klasik',3,'6:25',9804290),(48,'Newton\'s Cradle','2013','Ludovico Einaudi',8,'In A Time Lapse',6,'Klasik',3,'7:52',4549010),(49,'Waterways','2013','Ludovico Einaudi',8,'In A Time Lapse',6,'Klasik',3,'4:17',9363586),(50,'Experience','2013','Ludovico Einaudi',8,'In A Time Lapse',6,'Klasik',3,'5:15',100757363),(51,'Underwood','2013','Ludovico Einaudi',8,'In A Time Lapse',6,'Klasik',3,'4:13',8412449),(52,'Burning','2013','Ludovico Einaudi',8,'In A Time Lapse',6,'Klasik',3,'5:08',8080000);
/*!40000 ALTER TABLE `sarki` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tur`
--

DROP TABLE IF EXISTS `tur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tur` (
  `turID` int NOT NULL,
  `Tur_Adi` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`turID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tur`
--

LOCK TABLES `tur` WRITE;
/*!40000 ALTER TABLE `tur` DISABLE KEYS */;
INSERT INTO `tur` VALUES (1,'Pop'),(2,'Jazz'),(3,'Klasik');
/*!40000 ALTER TABLE `tur` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-08 21:53:50
