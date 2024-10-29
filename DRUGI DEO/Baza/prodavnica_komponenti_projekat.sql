-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: prodavnica_komponenti_projekat
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `id_gen_musterija`
--

DROP TABLE IF EXISTS `id_gen_musterija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `id_gen_musterija` (
  `gen_name` varchar(15) NOT NULL,
  `gen_val` int(11) DEFAULT NULL,
  PRIMARY KEY (`gen_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id_gen_musterija`
--

LOCK TABLES `id_gen_musterija` WRITE;
/*!40000 ALTER TABLE `id_gen_musterija` DISABLE KEYS */;
INSERT INTO `id_gen_musterija` VALUES ('musterija_gen',41);
/*!40000 ALTER TABLE `id_gen_musterija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `id_gen_narudzbina`
--

DROP TABLE IF EXISTS `id_gen_narudzbina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `id_gen_narudzbina` (
  `gen_name` varchar(15) NOT NULL,
  `gen_val` int(11) DEFAULT NULL,
  PRIMARY KEY (`gen_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id_gen_narudzbina`
--

LOCK TABLES `id_gen_narudzbina` WRITE;
/*!40000 ALTER TABLE `id_gen_narudzbina` DISABLE KEYS */;
INSERT INTO `id_gen_narudzbina` VALUES ('narudzbina_gen',37);
/*!40000 ALTER TABLE `id_gen_narudzbina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `id_gen_narudzbina_proizvod`
--

DROP TABLE IF EXISTS `id_gen_narudzbina_proizvod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `id_gen_narudzbina_proizvod` (
  `gen_name` varchar(45) NOT NULL,
  `gen_val` int(11) DEFAULT NULL,
  PRIMARY KEY (`gen_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id_gen_narudzbina_proizvod`
--

LOCK TABLES `id_gen_narudzbina_proizvod` WRITE;
/*!40000 ALTER TABLE `id_gen_narudzbina_proizvod` DISABLE KEYS */;
INSERT INTO `id_gen_narudzbina_proizvod` VALUES ('korpa_gen',51),('narudzbina_proizvod_gen',47);
/*!40000 ALTER TABLE `id_gen_narudzbina_proizvod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `id_gen_proizvod`
--

DROP TABLE IF EXISTS `id_gen_proizvod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `id_gen_proizvod` (
  `gen_name` varchar(15) NOT NULL,
  `gen_val` int(11) DEFAULT NULL,
  PRIMARY KEY (`gen_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id_gen_proizvod`
--

LOCK TABLES `id_gen_proizvod` WRITE;
/*!40000 ALTER TABLE `id_gen_proizvod` DISABLE KEYS */;
INSERT INTO `id_gen_proizvod` VALUES ('proizvod_gen',34);
/*!40000 ALTER TABLE `id_gen_proizvod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `id_gen_reklamacija`
--

DROP TABLE IF EXISTS `id_gen_reklamacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `id_gen_reklamacija` (
  `gen_name` varchar(15) NOT NULL,
  `gen_val` int(11) DEFAULT NULL,
  PRIMARY KEY (`gen_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id_gen_reklamacija`
--

LOCK TABLES `id_gen_reklamacija` WRITE;
/*!40000 ALTER TABLE `id_gen_reklamacija` DISABLE KEYS */;
INSERT INTO `id_gen_reklamacija` VALUES ('reklamacija_gen',7);
/*!40000 ALTER TABLE `id_gen_reklamacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korpa`
--

DROP TABLE IF EXISTS `korpa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `korpa` (
  `id_korpa` int(11) NOT NULL,
  `id_musterija` int(11) NOT NULL,
  `id_proizvod` int(11) NOT NULL,
  `kolicina` int(11) NOT NULL,
  PRIMARY KEY (`id_korpa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korpa`
--

LOCK TABLES `korpa` WRITE;
/*!40000 ALTER TABLE `korpa` DISABLE KEYS */;
/*!40000 ALTER TABLE `korpa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musterija`
--

DROP TABLE IF EXISTS `musterija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `musterija` (
  `id_musterija` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `ime` varchar(25) DEFAULT NULL,
  `prezime` varchar(25) DEFAULT NULL,
  `adresa` varchar(45) DEFAULT NULL,
  `telefon` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_musterija`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musterija`
--

LOCK TABLES `musterija` WRITE;
/*!40000 ALTER TABLE `musterija` DISABLE KEYS */;
INSERT INTO `musterija` VALUES (5,'stanko','Stanko','Trajkovic','Beograd','018605434'),(10,'marko33','Marko','Markovic','Nis','018234345'),(29,'ficax99','Filip','Trajkovic','ASDF','123415'),(32,'Marko11','Marko','Markoivc','Nis','06123454'),(40,'MARKOX','Marko','Nikolic','Nis','061055324');
/*!40000 ALTER TABLE `musterija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `narudzbina`
--

DROP TABLE IF EXISTS `narudzbina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `narudzbina` (
  `id_narudzbina` int(11) NOT NULL,
  `id_musterija` int(11) DEFAULT NULL,
  `ukupna_cena` double DEFAULT NULL,
  `ukupna_kolicina` int(11) DEFAULT NULL,
  `datum_narudzbine` datetime DEFAULT NULL,
  PRIMARY KEY (`id_narudzbina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `narudzbina`
--

LOCK TABLES `narudzbina` WRITE;
/*!40000 ALTER TABLE `narudzbina` DISABLE KEYS */;
INSERT INTO `narudzbina` VALUES (29,5,900,2,'2021-05-24 16:21:29'),(30,5,11490,14,'2021-05-24 16:22:43'),(31,29,53550,9,'2021-05-24 16:24:51'),(32,29,99900,10,'2021-05-24 23:42:43'),(33,5,46000,23,'2021-05-24 23:44:33'),(34,5,72000,80,'2021-05-26 02:01:25'),(35,29,30000,20,'2021-05-26 02:01:48'),(36,29,22500,15,'2021-05-26 02:42:54');
/*!40000 ALTER TABLE `narudzbina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `narudzbina_proizvod`
--

DROP TABLE IF EXISTS `narudzbina_proizvod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `narudzbina_proizvod` (
  `id_narudzbina_proizvod` int(11) NOT NULL,
  `id_narudzbina` int(11) NOT NULL,
  `id_proizvod` int(11) NOT NULL,
  `kolicina` int(11) NOT NULL,
  PRIMARY KEY (`id_narudzbina_proizvod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `narudzbina_proizvod`
--

LOCK TABLES `narudzbina_proizvod` WRITE;
/*!40000 ALTER TABLE `narudzbina_proizvod` DISABLE KEYS */;
INSERT INTO `narudzbina_proizvod` VALUES (37,29,9,2),(38,30,2,2),(39,30,13,12),(40,31,9,4),(41,31,13,5),(42,32,13,10),(43,33,12,23),(44,34,9,80),(45,35,2,20),(46,36,2,15);
/*!40000 ALTER TABLE `narudzbina_proizvod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proizvod`
--

DROP TABLE IF EXISTS `proizvod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proizvod` (
  `id_proizvod` int(11) NOT NULL,
  `serijski_broj` varchar(45) NOT NULL,
  `tip_komponente` varchar(25) NOT NULL,
  `naziv` varchar(45) NOT NULL,
  `slika` varchar(150) NOT NULL,
  `cena` int(11) NOT NULL,
  `kolicina` int(11) NOT NULL,
  PRIMARY KEY (`id_proizvod`),
  UNIQUE KEY `serijski_broj_UNIQUE` (`serijski_broj`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proizvod`
--

LOCK TABLES `proizvod` WRITE;
/*!40000 ALTER TABLE `proizvod` DISABLE KEYS */;
INSERT INTO `proizvod` VALUES (2,'0AXDS4','Maticna ploca','ASUS ROG','C://SLIKA.JPG',1500,115),(9,'0AASD4','CPU','FX 6300H','C://SLIKA.JPG',900,150),(10,'0AASxx4','CPU','FX 5600H','https://images10.newegg.com/ProductImage/19-113-286-04.jpg',900,25),(12,'0AXREWDS4','CPU','R7 2700X','http://www.tutorialspoint.com/images/jsf-mini-logo.png',2000,27),(13,'3245DFG0','Napajanje','Corsair TX650M','C://slika.jpg',9990,1);
/*!40000 ALTER TABLE `proizvod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reklamacija`
--

DROP TABLE IF EXISTS `reklamacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reklamacija` (
  `id_reklamacija` int(11) NOT NULL,
  `id_musterija` int(11) NOT NULL,
  `id_proizvod` int(11) NOT NULL,
  `datum_reklamacije` datetime NOT NULL,
  PRIMARY KEY (`id_reklamacija`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reklamacija`
--

LOCK TABLES `reklamacija` WRITE;
/*!40000 ALTER TABLE `reklamacija` DISABLE KEYS */;
INSERT INTO `reklamacija` VALUES (3,8,2,'2021-05-21 16:43:02'),(4,29,2,'2021-05-24 02:03:27'),(5,10,12,'2021-05-24 02:05:36'),(6,10,12,'2021-05-24 16:02:21');
/*!40000 ALTER TABLE `reklamacija` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-14  21:42:49
