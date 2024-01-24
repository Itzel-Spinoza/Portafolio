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
-- Table structure for table `tiene`
--

DROP TABLE IF EXISTS `tiene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiene` (
  `NoVenta` varchar(10) DEFAULT NULL,
  `codigoProducto` varchar(10) DEFAULT NULL,
  `NombreProducto` varchar(20) DEFAULT NULL,
  `CantidadProducto` tinyint DEFAULT NULL,
  `PrecioProducto` float DEFAULT NULL,
  `Subtotal` float DEFAULT NULL,
  KEY `Fk_TieneVentas` (`NoVenta`),
  KEY `Fk_TieneProductos` (`codigoProducto`),
  CONSTRAINT `Fk_TieneProductos` FOREIGN KEY (`codigoProducto`) REFERENCES `productos` (`codigoProducto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Fk_TieneVentas` FOREIGN KEY (`NoVenta`) REFERENCES `ventasfisicas` (`NoVenta`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiene`
--

LOCK TABLES `tiene` WRITE;
/*!40000 ALTER TABLE `tiene` DISABLE KEYS */;
INSERT INTO `tiene` VALUES ('V-001','P-016','Libreta totoro',1,150,150),('V-001','P-015','Post -it craft',1,35,35),('V-002','P-015','Post -it craft',1,35,35),('V-003','P-017','Libreta',1,34.56,34.56),('V-003','P-017','Libreta',1,34.56,34.56),('V-003','P-016','Libreta totoro',1,150,150),('V-004','P-018','Goma',1,23.5,23.5),('V-004','P-009','Paquete Gelly Roll',1,250,250),('V-004','P-002','Libreta Van Gogh',1,240,240),('V-006','P-014','Post-it transparente',1,40,40),('V-006','P-013','Washi cuadros',2,35,70),('V-008','P-012','Post-its animalitos',1,60,60),('V-008','P-007','Plumon perm Sharpie',1,20,20),('V-009','P-003','SketchBook Van Gogh',1,180,180),('V-010','P-004','Plumones Clickart',1,550,550),('V-010','P-006','Plumones Zig Brush',1,275,275),('V-011','P-014','Post-it transparente',1,40,40),('V-012','P-004','Plumones Clickart',1,550,550),('V-012','P-005','Plumones Tombow',1,350,350),('V-013','P-012','Post-its animalitos',1,60,60),('V-014','P-003','SketchBook Van Gogh',1,180,180),('V-015','P-002','Libreta Van Gogh',1,240,240),('V-016','P-009','Paquete Gelly Roll',1,250,250),('V-017','P-006','Plumones Zig Brush',1,275,275),('V-017','P-004','Plumones Clickart',1,550,550),('V-018','P-004','Plumones Clickart',1,550,550),('V-018','P-006','Plumones Zig Brush',1,275,275),('V-019','P-018','Goma',1,23.5,23.5),('V-020','P-003','SketchBook Van Gogh',1,180,180),('V-021','P-004','Plumones Clickart',1,550,550),('V-021','P-007','Plumon perm Sharpie',4,20,80),('V-023','P-003','SketchBook Van Gogh',2,180,360),('V-023','P-005','Plumones Tombow',1,350,350),('V-023','P-006','Plumones Zig Brush',2,275,550),('V-024','P-004','Plumones Clickart',1,550,550),('V-024','P-005','Plumones Tombow',2,350,700),('V-025','P-016','Libreta totoro',2,150,300),('V-025','P-018','Goma',4,23.5,94),('V-025','P-003','SketchBook Van Gogh',2,180,360),('V-027','P-017','Libreta',5,34.56,172.8),('V-027','P-015','Post -it craft',1,35,35),('V-027','P-013','Washi cuadros',3,35,105),('V-027','P-002','Libreta Van Gogh',1,240,240),('V-029','P-002','Libreta Van Gogh',2,240,480),('V-029','P-008','Colores Indra',2,320,640),('V-029','P-010','Post it otoño',3,40,120),('V-030','P-002','Libreta Van Gogh',2,240,480),('V-030','P-010','Post it otoño',3,40,120),('V-030','P-013','Washi cuadros',3,35,105),('V-033','P-006','Plumones Zig Brush',1,275,275),('V-033','P-013','Washi cuadros',3,35,105);
/*!40000 ALTER TABLE `tiene` ENABLE KEYS */;
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
