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
-- Temporary view structure for view `vistaempleados`
--

DROP TABLE IF EXISTS `vistaempleados`;
/*!50001 DROP VIEW IF EXISTS `vistaempleados`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vistaempleados` AS SELECT 
 1 AS `Noempleado`,
 1 AS `Nombre`,
 1 AS `ApellidoPat`,
 1 AS `ApellidoMat`,
 1 AS `Calle`,
 1 AS `noExterior`,
 1 AS `colonia`,
 1 AS `CP`,
 1 AS `Municipio`,
 1 AS `Estado`,
 1 AS `Sueldo`,
 1 AS `Puesto`,
 1 AS `CURP`,
 1 AS `NoTelefono`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vistaentregas`
--

DROP TABLE IF EXISTS `vistaentregas`;
/*!50001 DROP VIEW IF EXISTS `vistaentregas`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vistaentregas` AS SELECT 
 1 AS `NoEntrega`,
 1 AS `FechaPedido`,
 1 AS `FechaEntrega`,
 1 AS `Total`,
 1 AS `NoEmpleado`,
 1 AS `NoCliente`,
 1 AS `TipoEnvio`,
 1 AS `MetodoPago`,
 1 AS `Estatus`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vistaclientes`
--

DROP TABLE IF EXISTS `vistaclientes`;
/*!50001 DROP VIEW IF EXISTS `vistaclientes`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vistaclientes` AS SELECT 
 1 AS `NoCliente`,
 1 AS `Nombre`,
 1 AS `ApellidoPat`,
 1 AS `ApellidoMat`,
 1 AS `Calle`,
 1 AS `noExterior`,
 1 AS `colonia`,
 1 AS `CP`,
 1 AS `Municipio`,
 1 AS `Estado`,
 1 AS `NoTelefono`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vistaproveedores`
--

DROP TABLE IF EXISTS `vistaproveedores`;
/*!50001 DROP VIEW IF EXISTS `vistaproveedores`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vistaproveedores` AS SELECT 
 1 AS `CodigoProveedor`,
 1 AS `CompaÃ±ia`,
 1 AS `NombreContacto`,
 1 AS `ApellidoPat`,
 1 AS `ApellidoMat`,
 1 AS `Calle`,
 1 AS `noExterior`,
 1 AS `colonia`,
 1 AS `CP`,
 1 AS `Municipio`,
 1 AS `Estado`,
 1 AS `telefono`,
 1 AS `eMail`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `vistaempleados`
--

/*!50001 DROP VIEW IF EXISTS `vistaempleados`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vistaempleados` AS select `empleados`.`NoEmpleado` AS `Noempleado`,`empleados`.`Nombre` AS `Nombre`,`empleados`.`ApellidoPat` AS `ApellidoPat`,`empleados`.`ApellidoMat` AS `ApellidoMat`,`empleados`.`Calle` AS `Calle`,`empleados`.`noExterior` AS `noExterior`,`empleados`.`colonia` AS `colonia`,`empleados`.`CP` AS `CP`,`empleados`.`Municipio` AS `Municipio`,`empleados`.`Estado` AS `Estado`,`empleados`.`Sueldo` AS `Sueldo`,`empleados`.`Puesto` AS `Puesto`,`curpempleados`.`CURP` AS `CURP`,`notelefonoempleados`.`NoTelefono` AS `NoTelefono` from ((`empleados` join `curpempleados`) join `notelefonoempleados`) where ((`empleados`.`NoEmpleado` = `curpempleados`.`NoEmpleado`) and (`empleados`.`NoEmpleado` = `notelefonoempleados`.`NoEmpleado`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vistaentregas`
--

/*!50001 DROP VIEW IF EXISTS `vistaentregas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vistaentregas` AS select `entregas`.`NoEntrega` AS `NoEntrega`,`entregas`.`FechaPedido` AS `FechaPedido`,`entregas`.`FechaEntrega` AS `FechaEntrega`,`entregas`.`Total` AS `Total`,`entregas`.`NoEmpleado` AS `NoEmpleado`,`entregas`.`NoCliente` AS `NoCliente`,`tipoenvioentregas`.`TipoEnvio` AS `TipoEnvio`,`metodopagoentregas`.`MetodoPago` AS `MetodoPago`,`estatusentregas`.`Estatus` AS `Estatus` from (((`entregas` join `metodopagoentregas`) join `estatusentregas`) join `tipoenvioentregas`) where ((`entregas`.`NoEntrega` = `metodopagoentregas`.`NoEntrega`) and (`entregas`.`NoEntrega` = `estatusentregas`.`NoEntrega`) and (`entregas`.`NoEntrega` = `tipoenvioentregas`.`NoEntrega`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vistaclientes`
--

/*!50001 DROP VIEW IF EXISTS `vistaclientes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vistaclientes` AS select `clientes`.`NoCliente` AS `NoCliente`,`clientes`.`Nombre` AS `Nombre`,`clientes`.`ApellidoPat` AS `ApellidoPat`,`clientes`.`ApellidoMat` AS `ApellidoMat`,`clientes`.`Calle` AS `Calle`,`clientes`.`noExterior` AS `noExterior`,`clientes`.`colonia` AS `colonia`,`clientes`.`CP` AS `CP`,`clientes`.`Municipio` AS `Municipio`,`clientes`.`Estado` AS `Estado`,`notelefonoclientes`.`NoTelefono` AS `NoTelefono` from (`clientes` join `notelefonoclientes`) where (`clientes`.`NoCliente` = `notelefonoclientes`.`NoCliente`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vistaproveedores`
--

/*!50001 DROP VIEW IF EXISTS `vistaproveedores`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vistaproveedores` AS select `proveedores`.`CodigoProveedor` AS `CodigoProveedor`,`proveedores`.`Compa¤ia` AS `Compa¤ia`,`proveedores`.`NombreContacto` AS `NombreContacto`,`proveedores`.`ApellidoPat` AS `ApellidoPat`,`proveedores`.`ApellidoMat` AS `ApellidoMat`,`proveedores`.`Calle` AS `Calle`,`proveedores`.`noExterior` AS `noExterior`,`proveedores`.`colonia` AS `colonia`,`proveedores`.`CP` AS `CP`,`proveedores`.`Municipio` AS `Municipio`,`proveedores`.`Estado` AS `Estado`,`telefonoproveedores`.`telefono` AS `telefono`,`emailproveedores`.`eMail` AS `eMail` from ((`proveedores` join `telefonoproveedores`) join `emailproveedores`) where ((`proveedores`.`CodigoProveedor` = `telefonoproveedores`.`CodigoProveedor`) and (`proveedores`.`CodigoProveedor` = `emailproveedores`.`CodigoProveedor`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Dumping routines for database 'ohmymarkers3'
--
/*!50003 DROP PROCEDURE IF EXISTS `actualizarClientes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarClientes`(IN nocli varchar(10), IN nomcli varchar(20), IN apepat varchar(20), 
IN apemat varchar(20), IN calle varchar(20), IN noext varchar(20), IN col varchar(20), 
IN cp varchar(20), IN muni varchar(20), IN estado varchar(20), IN telefono varchar(13))
BEGIN 
UPDATE clientes SET Nombre = nomcli, ApellidoPat = apepat, ApellidoMat = apemat, 
Calle = calle, noExterior = noext, colonia = col, CP = cp, Municipio = muni, 
Estado = estado 
WHERE NoCliente = nocli;  
UPDATE NoTelefonoClientes SET NoTelefono = telefono WHERE NoCliente = nocli; 

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `actualizarEmpleado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarEmpleado`(IN noEmp varchar(10), IN nombremp varchar(20), 
IN apepat 
varchar(20), IN apemat varchar(20), IN calle varchar(20), IN noext varchar(20), 
IN col varchar(20), IN cp varchar(10), IN muni varchar(20), IN estado varchar(20), 
IN sueldo float, IN puesto varchar(20), IN curp varchar(18), IN telefono varchar(13))
BEGIN 
UPDATE empleados SET Nombre = nombremp, ApellidoPat = apepat, ApellidoMat = apemat, 
Calle = calle, noExterior = noext, colonia = col, CP = cp, Municipio = muni, 
Estado = estado, Sueldo = sueldo, Puesto = puesto 
WHERE NoEmpleado = noEmp;  

UPDATE CURPEmpleados SET CURP = curp WHERE NoEmpleado = noEmp; 
UPDATE NoTelefonoEmpleados SET NoTelefono = telefono WHERE NoEmpleado = noEmp;  

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `actualizarEntrega` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarEntrega`(IN noentre varchar(10), IN fechaentre DATETIME)
BEGIN
	UPDATE entregas SET FechaEntrega = fechaentre WHERE NoEntrega = noentre;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `actualizarProveedor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarProveedor`(IN codProv varchar(10), IN compa varchar(20), IN nombcon varchar(20), 
IN apepat varchar(20), IN apemat varchar(20), IN calle varchar(20), IN noext varchar(10), IN colonia varchar(10), 
IN cp varchar(10), IN municipio varchar(20), IN estado varchar(20), IN telefono varchar(13), 
IN email varchar(30))
BEGIN 
UPDATE Proveedores SET Compa¤ia = compa, NombreContacto = nombcon, ApellidoPat = apepat, 
ApellidoMat = apemat, Calle = calle, noExterior = noext, CP = cp, Municipio = municipio, 
Estado = estado WHERE CodigoProveedor = codProv; 
UPDATE TelefonoProveedores SET telefono = telefono WHERE CodigoProveedor = codProv;
UPDATE eMailProveedores SET eMail = email WHERE CodigoProveedor = codProv;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `aDocimicilio` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `aDocimicilio`(IN noentre varchar(10))
BEGIN 
INSERT INTO tipoenvioentregas VALUES(noentre , 'A domicilio');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `agregarProductoaEntrega` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `agregarProductoaEntrega`(IN noentre varchar(10), IN codprod varchar(10), IN cant tinyint)
BEGIN 
	DECLARE preciop float; 
	DECLARE sub float; 
	DECLARE nom varchar(20); 
	SET nom = (SELECT NombreProducto FROM productos WHERE CodigoProducto = codprod); 
	UPDATE productos SET Stock = Stock - cant WHERE CodigoProducto = codprod; 
	SET preciop = (SELECT Precio FROM productos WHERE codigoProducto = codprod); 
	SET sub = preciop * cant; 
	INSERT INTO existe VALUES (noentre, codprod, nom, cant, preciop, sub); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `agregarProductoaVenta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `agregarProductoaVenta`(IN noven varchar(10), IN codprod varchar(10), IN cantidad tinyint)
BEGIN
	DECLARE preciop float; 
	DECLARE sub float; 
	DECLARE nom varchar(20); 
	SET nom = (SELECT NombreProducto FROM productos WHERE CodigoProducto = codprod); 
	UPDATE productos SET Stock = Stock - cantidad WHERE CodigoProducto = codprod; 
	SET preciop = (SELECT Precio FROM productos WHERE codigoProducto = codprod); 
	SET sub = preciop * cantidad; 
	INSERT INTO tiene VALUES (noven, codprod, nom, cantidad, preciop, sub); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `calcularTotalVen` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `calcularTotalVen`(IN noven varchar(10))
BEGIN
	DECLARE sumador float; 
		SET sumador = (SELECT SUM(Subtotal) FROM tiene WHERE NoVenta = noven); 
	UPDATE ventasFisicas SET TotalVenta = sumador WHERE NoVenta = noven; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `calcularTotalVen2` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `calcularTotalVen2`(IN noven varchar(10), IN subtot float)
BEGIN
	UPDATE ventasFisicas SET TotalVenta = subtot WHERE NoVenta = noven; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `cancelarVenta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `cancelarVenta`(IN noven varchar(10))
BEGIN
DELETE FROM ventasFisicas WHERE NoVenta = noven; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `codigoProductos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `codigoProductos`()
BEGIN 
	DECLARE contador int; 
	DECLARE codprod varchar(10); 
    BEGIN
        SET contador= (SELECT COUNT(*)+1 FROM Productos); 
        IF(contador<10)THEN
            SET codprod= CONCAT('P-00',contador);
            ELSE IF(contador<100) THEN
                SET codprod= CONCAT('P-0',contador);
                ELSE IF(contador<1000)THEN
                    SET codprod= CONCAT('P-',contador);
                END IF;
            END IF;
        END IF; 
	END; 
	INSERT INTO productos (codigoProducto) VALUES (codprod); 
	SELECT codigoProducto FROM Productos WHERE codigoProducto = codprod; 	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `costoTotalInv` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `costoTotalInv`()
BEGIN 
SELECT SUM(Precio*Stock) FROM productos; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `crearCategoria` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `crearCategoria`(IN nomCat varchar(10), 
IN descrip varchar(100))
BEGIN
    DECLARE contador INT;
		DECLARE claveCat varchar(10); 
    BEGIN
        SET contador= (SELECT COUNT(*)+1 FROM Categorias); 
        IF(contador<10)THEN
            SET claveCat= CONCAT('C-00',contador);
            ELSE IF(contador<100) THEN
                SET claveCat= CONCAT('C-0',contador);
                ELSE IF(contador<1000)THEN
                    SET claveCat= CONCAT('C-',contador);
                END IF;
            END IF;
        END IF; 
	END;
INSERT INTO Categorias VALUES (claveCat, nomCat, descrip); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `datosEntregas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `datosEntregas`(IN nocli varchar(10))
BEGIN 
SELECT NoCliente, Nombre, ApellidoPat, ApellidoMat, Calle, noExterior, colonia, CP, 
Municipio, Estado FROM clientes WHERE NoCliente = nocli; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminarEntrega` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarEntrega`(IN noentre varchar(10))
BEGIN 
DELETE FROM entregas WHERE NoEntrega = noentre; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminarProductoEntrega` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarProductoEntrega`(IN noentre varchar(10), IN codprod varchar(10))
BEGIN 
DELETE FROM existe WHERE NoEntrega = noentre AND CodigoProducto = codprod; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminarProductoVenta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarProductoVenta`(IN noven varchar(10), IN codprod varchar(10))
BEGIN 
DELETE FROM tiene WHERE NoVenta = noven AND CodigoProducto = codprod; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `enEfecti` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `enEfecti`(IN noentre varchar(10))
BEGIN 
INSERT INTO metodopagoentregas VALUES(noentre , 'En efectivo');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `estadoEnProceso` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `estadoEnProceso`(IN noentre varchar(10))
BEGIN 
UPDATE estatusentregas SET Estatus = 'En proceso' WHERE NoEntrega = noentre; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `estadoEntregado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `estadoEntregado`(IN noentre varchar(10))
BEGIN 
UPDATE estatusentregas SET Estatus = 'Entregado' WHERE NoEntrega = noentre;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `estadoNoEntre` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `estadoNoEntre`(IN noentre varchar(10))
BEGIN 
UPDATE estatusentregas SET Estatus = 'No Entregado' WHERE NoEntrega = noentre; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `estadoProce` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `estadoProce`(IN noentre varchar(10))
BEGIN
UPDATE estatusentregas SET Estatus = 'Procesando' WHERE NoEntrega = noentre; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `generarCodigoCliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `generarCodigoCliente`()
BEGIN
    DECLARE contador INT;
		DECLARE nocli varchar(10); 
    BEGIN
        SET contador= (SELECT COUNT(*)+1 FROM clientes); 
        IF(contador<10)THEN
            SET nocli= CONCAT('Cl-00',contador);
            ELSE IF(contador<100) THEN
                SET nocli= CONCAT('Cl-0',contador);
                ELSE IF(contador<1000)THEN
                    SET nocli= CONCAT('Cl-',contador);
                END IF;
            END IF;
        END IF; 
	END;
	INSERT INTO clientes(NoCliente) VALUES (nocli); 
	SELECT NoCliente FROM clientes WHERE NoCliente = nocli; 	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `generarCodigoEmpleado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `generarCodigoEmpleado`()
BEGIN 
    DECLARE contador INT;
		DECLARE noEmp varchar(10); 
    BEGIN
        SET contador= (SELECT COUNT(*)+1 FROM Empleados); 
        IF(contador<10)THEN
            SET noEmp= CONCAT('Em-00',contador);
            ELSE IF(contador<100) THEN
                SET noEmp= CONCAT('Em-0',contador);
                ELSE IF(contador<1000)THEN
                    SET noEmp= CONCAT('Em-',contador);
                END IF;
            END IF;
        END IF; 
	END;
	INSERT INTO empleados(NoEmpleado) VALUES (noEmp); 
	SELECT NoEmpleado FROM empleados WHERE NoEmpleado = noEmp; 	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `hacerEntrega` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `hacerEntrega`(IN numEmp varchar(10), IN numCli varchar(10))
BEGIN 
	DECLARE contador INT; 
	DECLARE noentrega varchar(10); 
	BEGIN
        SET contador= (SELECT COUNT(*)+1 FROM entregas); 
        IF(contador<10)THEN
            SET noentrega= CONCAT('en-00',contador);
            ELSE IF(contador<100) THEN
                SET noentrega= CONCAT('en-0',contador);
                ELSE IF(contador<1000)THEN
                    SET noentrega= CONCAT('en-',contador);
                END IF;
            END IF;
        END IF; 
	END; 
INSERT INTO entregas VALUES (noentrega, NOW(), NULL, 0, numEmp, numCli); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `hacerNuevaEntrega` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `hacerNuevaEntrega`(nocli VARCHAR(10))
BEGIN 
	DECLARE contador INT; 
	DECLARE numemp varchar(10); 
	DECLARE noentre varchar(10);
	BEGIN 
        SET contador= (SELECT COUNT(*)+1 FROM Entregas); 
        IF(contador<10)THEN
            SET noentre= CONCAT('en-00',contador);
            ELSE IF(contador<100) THEN
                SET noentre= CONCAT('en-0',contador);
                ELSE IF(contador<1000)THEN
                    SET noentre= CONCAT('en-',contador);
                END IF;
            END IF;
        END IF; 
	END; 
	SET numemp=(SELECT NoEmpleado FROM entradasSalidas WHERE HoraSalidas IS NULL);	 
INSERT INTO entregas VALUES (noentre, NOW(), NULL, 0, numEmp, nocli); 
SELECT NoEntrega, NoEmpleado FROM entregas WHERE NoEntrega = noentre; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `hacerNuevaVenta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `hacerNuevaVenta`()
BEGIN
    DECLARE contador INT;
		DECLARE numemp varchar(10);
		DECLARE noven varchar(10); 
    BEGIN
        SET contador= (SELECT COUNT(*)+1 FROM ventasFisicas); 
        IF(contador<10)THEN
            SET noven= CONCAT('V-00',contador);
            ELSE IF(contador<100) THEN
                SET noven= CONCAT('V-0',contador);
                ELSE IF(contador<1000)THEN
                    SET noven= CONCAT('V-',contador);
                END IF;
            END IF;
        END IF; 
	END; 
	SET numemp=(SELECT NoEmpleado FROM entradasSalidas WHERE HoraSalidas IS NULL);
	
INSERT INTO ventasFisicas VALUES (noven, NOW(), 0, numemp);
SELECT NoVenta, NoEmpleado FROM ventasFisicas WHERE NoVenta = noven; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertarCliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarCliente`(IN nomcli varchar(20), IN apepat varchar(20), 
IN apemat varchar(20), IN calle varchar(20), IN noext varchar(20), IN colonia varchar(20), 
IN cp varchar(20), IN municipio varchar(20), IN estado varchar(20), IN telefono varchar(13))
BEGIN
    DECLARE contador INT;
		DECLARE nocli varchar(10); 
    BEGIN
        SET contador= (SELECT COUNT(*)+1 FROM clientes); 
        IF(contador<10)THEN
            SET nocli= CONCAT('Cl-00',contador);
            ELSE IF(contador<100) THEN
                SET nocli= CONCAT('Cl-0',contador);
                ELSE IF(contador<1000)THEN
                    SET nocli= CONCAT('Cl-',contador);
                END IF;
            END IF;
        END IF; 
	END; 
INSERT INTO clientes VALUES (nocli, nomcli, apepat, apemat, calle, noext, colonia, 
cp, municipio, estado);

INSERT INTO NoTelefonoClientes VALUES (nocli, telefono);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertarDatosClientes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarDatosClientes`(IN nocli varchar(10), IN nomcli varchar(20), IN apepat varchar(20), 
IN apemat varchar(20), IN calle varchar(20), IN noext varchar(20), IN col varchar(20), 
IN cp varchar(20), IN muni varchar(20), IN estado varchar(20), IN telefono varchar(13))
BEGIN 
UPDATE clientes SET Nombre = nomcli, ApellidoPat = apepat, ApellidoMat = apemat, 
Calle = calle, noExterior = noext, colonia = col, CP = cp, Municipio = muni, 
Estado = estado 
WHERE NoCliente = nocli;  
INSERT INTO NoTelefonoClientes VALUES (nocli, telefono);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertarDatosEmpleado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarDatosEmpleado`(IN nombremp varchar(20), IN apepat 
varchar(20), IN apemat varchar(20), IN calle varchar(20), IN noext varchar(20), 
IN col varchar(20), IN cp varchar(10), IN muni varchar(20), IN estado varchar(20), 
IN sueldo float, IN puesto varchar(20), IN curp varchar(18), IN telefono varchar(13))
BEGIN
    DECLARE contador INT;
		DECLARE noEmp varchar(10); 
    BEGIN
        SET contador= (SELECT COUNT(*)+1 FROM Empleados); 
        IF(contador<10)THEN
            SET noEmp= CONCAT('Em-00',contador);
            ELSE IF(contador<100) THEN
                SET noEmp= CONCAT('Em-0',contador);
                ELSE IF(contador<1000)THEN
                    SET noEmp= CONCAT('Em-',contador);
                END IF;
            END IF;
        END IF; 
	END;
INSERT INTO Empleados VALUES (noEmp, nombremp, apepat, apemat, calle, noext, col, cp, muni, estado, 
sueldo, puesto);   
INSERT INTO CURPEmpleados VALUES (noEmp, curp); 
INSERT INTO NoTelefonoEmpleados VALUES (noEmp, telefono); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertarDatosEmpleado2` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarDatosEmpleado2`(IN noEmp varchar(10), IN nombremp varchar(20), 
IN apepat 
varchar(20), IN apemat varchar(20), IN calle varchar(20), IN noext varchar(20), 
IN col varchar(20), IN cp varchar(10), IN muni varchar(20), IN estado varchar(20), 
IN sueldo float, IN puest varchar(20), IN curp varchar(18), IN telefono varchar(13))
BEGIN 
UPDATE empleados SET Nombre = nombremp, ApellidoPat = apepat, ApellidoMat = apemat, 
Calle = calle, noExterior = noext, colonia = col, CP = cp, Municipio = muni, 
Estado = estado, Sueldo = sueldo, Puesto = puest
WHERE NoEmpleado = noEmp;  

INSERT INTO CURPEmpleados VALUES (noEmp, curp); 
INSERT INTO NoTelefonoEmpleados VALUES (noEmp, telefono); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertarProductos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarProductos`(IN codprod varchar(10), IN nomprod varchar(20), 
IN descripcion varchar(100), IN stock tinyint, IN precio float, IN codprove varchar(10), 
IN clavcat varchar(10))
BEGIN
UPDATE productos SET NombreProducto = nomprod, Descripcion = descripcion, 
Stock = stock, Precio = precio, CodigoProveedor = codprove, ClaveCategoria = clavcat
WHERE CodigoProducto = codprod;    

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertarProveedor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarProveedor`(IN compa¤ia varchar(20), IN nombcon varchar(20), 
IN apepat varchar(20), IN apemat varchar(20), IN calle varchar(20), IN noext varchar(10), IN colonia varchar(10), 
IN cp varchar(10), IN municipio varchar(20), IN estado varchar(20), IN telefono varchar(13), 
IN email varchar(30))
BEGIN
    DECLARE contador INT;
		DECLARE codProv varchar(10); 
    BEGIN
        SET contador= (SELECT COUNT(*)+1 FROM Proveedores); 
        IF(contador<10)THEN
            SET codProv= CONCAT('pv-00',contador);
            ELSE IF(contador<100) THEN
                SET codProv= CONCAT('pv-0',contador);
                ELSE IF(contador<1000)THEN
                    SET codProv= CONCAT('pv-',contador);
                END IF;
            END IF;
        END IF; 
	END;
INSERT INTO Proveedores VALUES (codProv, compa¤ia, nombcon, apepat, apemat, calle, noext, colonia, 
cp, municipio, estado); 

INSERT INTO TelefonoProveedores VALUES (codProv, telefono); 
INSERT INTO eMailProveedores VALUES (codProv, email); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertarProveedor2` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarProveedor2`(IN codProv varchar(10), IN compa varchar(20), IN nombcon varchar(20), 
IN apepat varchar(20), IN apemat varchar(20), IN calle varchar(20), IN noext varchar(10), IN colonia varchar(10), 
IN cp varchar(10), IN municipio varchar(20), IN estado varchar(20), IN telefono varchar(13), 
IN email varchar(30))
BEGIN

UPDATE Proveedores SET Compa¤ia = compa, NombreContacto = nombcon, ApellidoPat = apepat, 
ApellidoMat = apemat, Calle = calle, noExterior = noext, Colonia = colonia, CP = cp, Municipio = municipio, 
Estado = estado WHERE CodigoProveedor = codProv; 

INSERT INTO TelefonoProveedores VALUES (codProv, telefono); 
INSERT INTO eMailProveedores VALUES (codProv, email); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `mostrarProductosVen` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `mostrarProductosVen`(IN noven varchar(10))
BEGIN 
	SELECT codigoProducto, NombreProducto, CantidadProducto, PrecioProducto, Subtotal 
	FROM tiene
	WHERE NoVenta = noven; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `paqueteria` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `paqueteria`(IN noentre varchar(10))
BEGIN 
INSERT INTO tipoenvioentregas VALUES(noentre , 'Paqueter¡a');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `productoEntrega` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `productoEntrega`(IN noentre varchar(10), IN codprod varchar(10))
BEGIN 
SELECT codigoProducto, nombreProducto, CantidadE, PrecioProductoE, SubtotalE 
FROM existe WHERE NoEntrega = noentre AND codigoProducto = codprod; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `productoVenta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `productoVenta`(IN noven varchar(10), IN codprod varchar(10))
BEGIN 
SELECT codigoProducto, NombreProducto, CantidadProducto, PrecioProducto, Subtotal
FROM tiene WHERE NoVenta = noven AND codigoProducto = codprod;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `puntoEntre` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `puntoEntre`(IN noentre varchar(10))
BEGIN 
INSERT INTO tipoenvioentregas VALUES(noentre , 'Punto de entrega');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registrarEntrada` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrarEntrada`(IN noemp varchar(10))
BEGIN
    DECLARE contador INT;
		DECLARE idOpe VARCHAR(10); 
    BEGIN
        SET contador= (SELECT COUNT(*)+1 FROM entradasSalidas); 
        IF(contador<10)THEN
            SET idOpe= CONCAT('Op-00',contador);
            ELSE IF(contador<100) THEN
                SET idOpe= CONCAT('Op-0',contador);
                ELSE IF(contador<1000)THEN
                    SET idOpe= CONCAT('Op-',contador);
                END IF;
            END IF;
        END IF; 
	END; 

INSERT INTO entradasSalidas VALUES (idOpe, NOW(), null, noemp); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registrarEntrega` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrarEntrega`(IN noentre varchar(10), IN subtot float, IN noCli varchar(10))
BEGIN
	UPDATE entregas SET Total = subtot, NoCliente = noCli WHERE NoEntrega = noentre; 
	INSERT INTO estatusentregas VALUES(noentre , 'Procesando'); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registrarNuevoProv` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrarNuevoProv`()
BEGIN 
    DECLARE contador INT;
		DECLARE codProv varchar(10); 
    BEGIN
        SET contador= (SELECT COUNT(*)+1 FROM Proveedores); 
        IF(contador<10)THEN
            SET codProv= CONCAT('pv-00',contador);
            ELSE IF(contador<100) THEN
                SET codProv= CONCAT('pv-0',contador);
                ELSE IF(contador<1000)THEN
                    SET codProv= CONCAT('pv-',contador);
                END IF;
            END IF;
        END IF; 
	END;
	INSERT INTO Proveedores(CodigoProveedor) VALUES (codProv); 
	SELECT CodigoProveedor FROM Proveedores WHERE CodigoProveedor = codProv; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registrarSalida` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrarSalida`()
BEGIN 
UPDATE entradasSalidas SET HoraSalidas = NOW() WHERE HoraSalidas IS NULL; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registrarUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrarUsuario`(IN noEmp varchar(10), IN contra varchar(8))
BEGIN 
INSERT INTO Usuarios VALUES (noEmp, contra); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `subtotalDeLaEntrega` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `subtotalDeLaEntrega`(IN noentre varchar(10))
BEGIN 
SELECT SUM(SubtotalE) FROM existe WHERE NoEntrega = noentre; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `subtotalDeLaVenta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `subtotalDeLaVenta`(IN noven varchar(10))
BEGIN 
SELECT SUM(Subtotal) FROM tiene WHERE NoVenta = noven; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `subtotalTotalEntrega` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `subtotalTotalEntrega`(IN noentre varchar(10))
BEGIN 
SELECT SUM(SubtotalE) FROM existe WHERE NoEntrega = noentre; 

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `tipoPagoEnvio` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `tipoPagoEnvio`(IN noEntre varchar(10), IN tipoPago varchar(20), IN tipoEnvio varchar(20))
BEGIN 
	INSERT INTO MetodoPagoEntregas VALUES (noEntre, tipoPago); 
	INSERT INTO TipoEnvioEntregas VALUES (noEntre, tipoEnvio); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `totalEntrega` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `totalEntrega`(IN noEntre varchar(10))
BEGIN 
	DECLARE sumador float; 
	SET sumador = (SELECT SUM(SubtotalE) FROM existe WHERE NoEntrega = noEntre); 
	UPDATE entregas SET Total = sumador WHERE NoEntrega = noEntre; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `totalProductosInv` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `totalProductosInv`()
BEGIN 
SELECT SUM(Stock) FROM productos; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `transfe` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `transfe`(IN noentre varchar(10))
BEGIN 
INSERT INTO metodopagoentregas VALUES(noentre , 'Transferencia');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `verificarEntregasSinEntregar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `verificarEntregasSinEntregar`()
BEGIN 
SELECT NoEntrega, Estatus FROM estatusEntregas WHERE Estatus = 'Procesando' OR Estatus = 'No entregado';

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `verificarStock` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `verificarStock`(IN codprod varchar(10))
BEGIN 
	SELECT Stock FROM Productos WHERE CodigoProducto = codprod; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `verificarStockBajo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `verificarStockBajo`()
BEGIN 
SELECT codigoProducto, NombreProducto, Stock FROM Productos WHERE Stock < 5; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `verProductosVenta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `verProductosVenta`(IN noven varchar(10))
BEGIN 
SELECT codigoProducto, NombreProducto, CantidadProducto, PrecioProducto, Subtotal 
FROM tiene WHERE NoVenta = noven; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `verTotalVenta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `verTotalVenta`(IN noven varchar(10))
BEGIN 
SELECT TotalVenta FROM ventasFisicas WHERE NoVenta = noven; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-12 10:06:51
