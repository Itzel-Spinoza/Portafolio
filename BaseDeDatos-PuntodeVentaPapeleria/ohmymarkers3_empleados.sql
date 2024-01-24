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
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `NoEmpleado` varchar(10) NOT NULL,
  `Nombre` varchar(20) DEFAULT NULL,
  `ApellidoPat` varchar(20) DEFAULT NULL,
  `ApellidoMat` varchar(20) DEFAULT NULL,
  `Calle` varchar(20) DEFAULT NULL,
  `noExterior` varchar(20) DEFAULT NULL,
  `colonia` varchar(20) DEFAULT NULL,
  `CP` varchar(10) DEFAULT NULL,
  `Municipio` varchar(20) DEFAULT NULL,
  `Estado` varchar(20) DEFAULT NULL,
  `Sueldo` float DEFAULT NULL,
  `Puesto` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`NoEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES ('Em-001','Angelica','Macias','Salazar','Manlio Fabio','38','Colosio','93629','San Rafael','Veracruz',3000,'Vendedor'),('Em-002','Lourdes','Landero','Escobar','Pascuala vazquez','55','Gavilán','93629','San Rafael','Veracruz',2000,'Vendedor'),('Em-003','Alfonso','Hernández','Bautista','Ignacio Zaragoza','667','Colosio','94365','Tlapacoyan','Veracruz',5000,'Contratación P'),('Em-004','Elias','Jacobo','Landero','Miguel de Iturbide','621','Costitucion','94365','Tlapacoyan','Veracruz ',3000,'Administración Nom.'),('Em-005','Alberto','Gonzalez','Calderon','Cuahutemoc','202','Centro','91234','Martínez de la Torre','Veracruz',2000,'Adm. inventario'),('Em-006','Reyna','Caballero','Ramos','Cuahutemoc','433','Cualquiera','91234','Martínez de la Torre','Veracruz',2500,'Adm. inventario'),('Em-007','Irene','Carmona','Martínez','Manuel Camacho','55','Rodriguez cano','93506','Martínez de la Torre','Veracruz',12345,'Vendedor'),('Em-008','Daniel','Aguirre','Jaimes','Emiliano zapata','78','Rojo Gomez','93506','Martínez de la Torre','Veracruz',5000,'Entregas'),('Em-009','Fabiola','Castañeda','Cruz','Isabel de castillo','356','Vernandita','95608','Martínez de la Torre','Veracruz',3000,'Entregas'),('Em-010','Laura','Díaz','Vazquez','Maricruz de allende','544','El Progreso','94336','Martínez de la Torre','Veracruz',2000,'Entregas'),('Em-011','Angelica','Macias','Salazar','Manlio Fabio','38','Colosio','93629','San Rafael','Veracruz',3000,'Vendedor'),('Em-012','Lilibeth','Espinoza','López','Maclovio H.','111','Francisco','93610','Martínez de la Torre','Veracruz',2000,'Ventas'),('Em-013','Lilly','González','Espinoza','Gabino','277','Maclovio','394213','Martínez de la Torre','Veracruz ',2345,'Vendedor'),('Em-014',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Em-015',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
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
