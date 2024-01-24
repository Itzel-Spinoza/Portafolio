-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ohmymarkers3
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedores` (
  `CodigoProveedor` varchar(10) NOT NULL,
  `Compañia` varchar(20) DEFAULT NULL,
  `NombreContacto` varchar(20) DEFAULT NULL,
  `ApellidoPat` varchar(20) DEFAULT NULL,
  `ApellidoMat` varchar(20) DEFAULT NULL,
  `Calle` varchar(20) DEFAULT NULL,
  `noExterior` varchar(10) DEFAULT NULL,
  `colonia` varchar(20) DEFAULT NULL,
  `CP` varchar(10) DEFAULT NULL,
  `Municipio` varchar(20) DEFAULT NULL,
  `Estado` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`CodigoProveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedores`
--

LOCK TABLES `proveedores` WRITE;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` VALUES ('pv-001','Scrittura','Ernesto','López','Avila','16 de septiembre','112','Encino','53125','Los Robles','Guadalajara'),('pv-002','Tombow','Fabiola','Alfonso','Dominguez','16 sur','566','Distrito','51245','Toluca','México'),('pv-003','Zebra','Carina','Vazquez','Castillo','Santa Trinidad','899','Cuahutemoc','55673','Toluca','México'),('pv-004','Kawai Products','Paolass','Rodriguez','Romero','Manuel Avila Camacho','455','Pedernales','41987','Toluca',''),('pv-005','Indra','Javier','Gonzalez','Flores','Gabriela Mistral','321','San Juan','32012','Valle de Bravo','México'),('pv-006','Sakura','Gabriela','Rendón','Flores','Rodriguez rivera','954','Lala','78324','Valle de Bravo','México'),('pv-007','Sharpie','Brenda','Bello','Ramirez','Herrera Leonardo','213','Juventino','93829','Chalco','México'),('pv-008','World Washi tape','Julian','Ramirez','Lomeli','Leona Vicario','456','Leones','23980','Chalco','México'),('pv-009','Eurocolors','David','Revelles','Hernández','Pino Suarez','231','Falancio','12340','Ixtapan','México'),('pv-010','Miniso Washis','Leonardo','Dominguez','Loeza','Josefa Ortiz','566','Vertino','23213','Ixtapan','Estado de México '),('pv-011','Norma','Thelma','Clatza','Ramirez','Jardines','234','La Gaviota','23456','Texcoco','Estado de México '),('pv-012',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('pv-013','hojitas','Poncian','Otro','Mas','Aqui','21','Allá','90000','Misantla','Veracruz');
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-12 10:06:49
