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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `NoCliente` varchar(10) NOT NULL,
  `Nombre` varchar(20) DEFAULT NULL,
  `ApellidoPat` varchar(20) DEFAULT NULL,
  `ApellidoMat` varchar(20) DEFAULT NULL,
  `Calle` varchar(20) DEFAULT NULL,
  `noExterior` varchar(20) DEFAULT NULL,
  `colonia` varchar(20) DEFAULT NULL,
  `CP` varchar(10) DEFAULT NULL,
  `Municipio` varchar(20) DEFAULT NULL,
  `Estado` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`NoCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES ('Cl-001','Angel','Marcial','Juarez','Gabino Barreda','56','Altamirano','34567','Martínez de la Torre','Veracruz'),('Cl-002','Diego','Guzman','Garcia','Rodriguez Cano','45','Ferrero','34212','Xalapa','Veracruz'),('Cl-003','Gerardo','Gonzalez','Flores','Cuauthemoc','111','Francisco','89654','Martínez de la Torre','Veracruz'),('Cl-004','Orlando','Flores','Rendon','Gregorio Vazquez','34','Aleman','90431','Martínez de la Torre','Veracruz'),('Cl-005','Daisy','Diaz','Fernandez','Leona Vicario','6','La Espaldilla','34562','Xalapa','Veracruz'),('Cl-006','Mario','Rodriguez','Romero','Las Flores','5','Las Trancas','78904','San Rafael','Veracruz'),('Cl-007','Guillermo','Proal','Andrade','Carranza','234','Devoirs','23850','San Rafael','Veracruz'),('Cl-008','Jazmin','Rodriguez','Garcia','Zapata','561','Castañeda','90853','Tlapacoyan','Veracruz'),('Cl-009','Samantha','Arcos','Galvan','Porfirio','543','Federacion','22267','Martínez de la Torre','Veracruz'),('Cl-010','Kevin','Hernandez','Bruno','Jacarandas','32','Altamirano','55545','Martínez de la Torre','Veracruz'),('Cl-011','Allison','Hernandez','Herrera','Olivos','233','Grande','74824','Martínez de la Torre','Veracruz'),('Cl-012','Valeria','Jimenez','Hernandez','Clemente','343','Gutierrez','23244','Martínez de la Torre','Veracruz '),('Cl-013','Adrian','Dominguez','Herrera','Juventino Rosas','123','Gamez','78920','Martinez de la Torre','Veracruz'),('Cl-014','Javier','Gamez','Valades','Fernandez','123','Galdino','34567','Martinez de la Torre','Veracruz');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
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
