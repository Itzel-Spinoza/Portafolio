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
-- Table structure for table `ventasfisicas`
--

DROP TABLE IF EXISTS `ventasfisicas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventasfisicas` (
  `NoVenta` varchar(10) NOT NULL,
  `FechaVenta` date DEFAULT NULL,
  `TotalVenta` float DEFAULT NULL,
  `NoEmpleado` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`NoVenta`),
  KEY `Fk_VentasFisiEmpleado` (`NoEmpleado`),
  CONSTRAINT `Fk_VentasFisiEmpleado` FOREIGN KEY (`NoEmpleado`) REFERENCES `empleados` (`NoEmpleado`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventasfisicas`
--

LOCK TABLES `ventasfisicas` WRITE;
/*!40000 ALTER TABLE `ventasfisicas` DISABLE KEYS */;
INSERT INTO `ventasfisicas` VALUES ('V-001','2023-01-01',0,'em-001'),('V-002','2023-01-01',0,'em-001'),('V-003','2023-01-01',0,'em-001'),('V-004','2023-01-01',0,'em-001'),('V-005','2023-01-01',0,'em-001'),('V-006','2023-01-02',110,'em-001'),('V-007','2023-01-02',0,'em-001'),('V-008','2023-01-02',0,'em-001'),('V-009','2023-01-02',0,'em-001'),('V-010','2023-01-02',825,'em-001'),('V-011','2023-01-02',0,'em-001'),('V-012','2023-01-02',0,'em-001'),('V-013','2023-01-02',0,'em-001'),('V-014','2023-01-02',0,'em-001'),('V-015','2023-01-02',0,'em-001'),('V-016','2023-01-02',0,'em-001'),('V-017','2023-01-02',0,'em-001'),('V-018','2023-01-02',0,'em-001'),('V-019','2023-01-02',0,'em-001'),('V-020','2023-01-04',0,'em-001'),('V-021','2023-01-04',0,'em-001'),('V-022','2023-01-04',0,'em-001'),('V-023','2023-01-04',0,'em-001'),('V-024','2023-01-05',0,'em-001'),('V-025','2023-01-07',603.2,'em-001'),('V-026','2023-01-07',0,'em-001'),('V-027','2023-01-07',552,'em-001'),('V-028','2023-01-07',0,'em-001'),('V-029','2023-01-07',1240,'em-001'),('V-030','2023-01-07',705,'em-001'),('V-031','2023-01-07',0,'em-001'),('V-032','2023-01-07',0,'em-001'),('V-033','2023-01-08',304,'em-001'),('V-034','2023-01-12',0,'em-001'),('V-035','2023-01-12',0,'em-001');
/*!40000 ALTER TABLE `ventasfisicas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-12 10:06:50
