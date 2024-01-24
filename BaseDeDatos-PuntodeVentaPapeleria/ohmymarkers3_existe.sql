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
-- Table structure for table `existe`
--

DROP TABLE IF EXISTS `existe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `existe` (
  `NoEntrega` varchar(10) DEFAULT NULL,
  `codigoProducto` varchar(10) DEFAULT NULL,
  `nombreProducto` varchar(20) DEFAULT NULL,
  `CantidadE` tinyint DEFAULT NULL,
  `PrecioProductoE` float DEFAULT NULL,
  `SubtotalE` float DEFAULT NULL,
  KEY `Fk_existeProductos` (`codigoProducto`),
  KEY `Fk_existeEntregas` (`NoEntrega`),
  CONSTRAINT `Fk_existeEntregas` FOREIGN KEY (`NoEntrega`) REFERENCES `entregas` (`NoEntrega`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Fk_existeProductos` FOREIGN KEY (`codigoProducto`) REFERENCES `productos` (`codigoProducto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `existe`
--

LOCK TABLES `existe` WRITE;
/*!40000 ALTER TABLE `existe` DISABLE KEYS */;
INSERT INTO `existe` VALUES ('en-001','P-004','Plumones Clickart',1,550,550),('en-001','P-007','Plumon perm Sharpie',5,20,100),('en-001','P-006','Plumones Zig Brush',1,275,275),('en-002','P-003','SketchBook Van Gogh',4,180,720),('en-002','P-016','Libreta totoro',2,150,300),('en-003','P-014','Post-it transparente',5,40,200),('en-003','P-016','Libreta totoro',2,150,300),('en-003','P-001','Libreta Van Gogh',1,170,170),('en-004','P-009','Paquete Gelly Roll',3,250,750),('en-004','P-011','Stickers picnic day',1,60,60),('en-004','P-014','Post-it transparente',1,40,40),('en-004','P-015','Post -it craft',1,35,35),('en-004','P-018','Goma',3,23.5,70.5),('en-004','P-003','SketchBook Van Gogh',2,180,360),('en-006','P-002','Libreta Van Gogh',2,240,480),('en-006','P-006','Plumones Zig Brush',3,275,825),('en-006','P-012','Post-its animalitos',1,60,60),('en-006','P-016','Libreta totoro',2,150,300),('en-007','P-016','Libreta totoro',1,150,150),('en-007','P-010','Post it otoño',3,40,120),('en-007','P-012','Post-its animalitos',1,60,60),('en-008','P-011','Stickers picnic day',3,60,180),('en-008','P-002','Libreta Van Gogh',2,240,480),('en-009','P-005','Plumones Tombow',6,350,2100);
/*!40000 ALTER TABLE `existe` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-12 10:06:48
