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
-- Table structure for table `entregas`
--

DROP TABLE IF EXISTS `entregas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entregas` (
  `NoEntrega` varchar(10) NOT NULL,
  `FechaPedido` datetime DEFAULT NULL,
  `FechaEntrega` date DEFAULT NULL,
  `Total` float DEFAULT NULL,
  `NoEmpleado` varchar(10) DEFAULT NULL,
  `NoCliente` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`NoEntrega`),
  KEY `Fk_EntregasEmpleado` (`NoEmpleado`),
  KEY `Fk_Clientes` (`NoCliente`),
  CONSTRAINT `Fk_Clientes` FOREIGN KEY (`NoCliente`) REFERENCES `clientes` (`NoCliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Fk_EntregasEmpleado` FOREIGN KEY (`NoEmpleado`) REFERENCES `empleados` (`NoEmpleado`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entregas`
--

LOCK TABLES `entregas` WRITE;
/*!40000 ALTER TABLE `entregas` DISABLE KEYS */;
INSERT INTO `entregas` VALUES ('en-001','2023-01-06 22:43:30','2021-01-14',1315,'em-001','Cl-001'),('en-002','2023-01-06 22:44:15','2021-01-14',1315,'em-001','Cl-001'),('en-003','2023-01-06 22:45:05','2021-01-14',1315,'em-001','Cl-001'),('en-004','2023-01-07 15:02:11','2023-01-13',1315,'em-001','Cl-001'),('en-005','2023-01-08 01:57:38',NULL,0,NULL,'Cl-005'),('en-006','2023-01-08 02:52:59','2023-01-08',1665,'em-001','Cl-004'),('en-007','2023-01-08 02:54:39','2023-01-08',330,'em-001','Cl-001'),('en-008','2023-01-09 23:01:27','2023-01-09',660,'em-001','Cl-004'),('en-009','2023-01-12 09:51:17',NULL,2100,'em-001','cl-001');
/*!40000 ALTER TABLE `entregas` ENABLE KEYS */;
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
