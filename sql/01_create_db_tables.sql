CREATE DATABASE  IF NOT EXISTS `super_rent` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `super_rent`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: super_rent
-- ------------------------------------------------------
-- Server version	5.6.17

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
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch` (
  `BranchID` int(11) NOT NULL AUTO_INCREMENT,
  `BranchName` varchar(100) NOT NULL,
  `BranchPhone` varchar(15) NOT NULL,
  `BranchAddress` varchar(200) NOT NULL,
  PRIMARY KEY (`BranchID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `buy_insurance`
--

DROP TABLE IF EXISTS `buy_insurance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buy_insurance` (
  `InsuranceId` int(11) NOT NULL DEFAULT '0',
  `ReservationInfoId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`InsuranceId`,`ReservationInfoId`),
  KEY `buy_insur_res_fk_idx` (`ReservationInfoId`),
  CONSTRAINT `buy_insur_res_fk` FOREIGN KEY (`ReservationInfoId`) REFERENCES `reservation_info` (`ReservationInfoId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `buy_insur_insur_fk` FOREIGN KEY (`InsuranceId`) REFERENCES `insurance` (`InsuranceId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `CustomerID` int(11) NOT NULL AUTO_INCREMENT,
  `PhoneNo` varchar(45) NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `MiddleName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) NOT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `DriverLicenseNo` varchar(45) DEFAULT NULL,
  `IsClubMember` bit(1) NOT NULL,
  `MemberPoint` int(11) DEFAULT NULL,
  `MembershipExpireDate` date DEFAULT NULL,
  `Username` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CustomerID`),
  UNIQUE KEY `PhoneNo_UNIQUE` (`PhoneNo`),
  KEY `Username_fk_idx` (`Username`),
  CONSTRAINT `customer_username_fk` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment` (
  `EquipmentId` int(11) NOT NULL AUTO_INCREMENT,
  `EquipmentType` varchar(45) NOT NULL,
  `Status` enum('AVAILABLE','UNAVAILABLE') NOT NULL,
  `Manufactor` varchar(45) DEFAULT NULL,
  `ManufactureDate` date DEFAULT NULL,
  `Mode` varchar(45) DEFAULT NULL,
  `BranchID` int(11) NOT NULL,
  PRIMARY KEY (`EquipmentId`),
  KEY `equipment_branch_fk_idx` (`BranchID`),
  KEY `equipment_equipmenttype_fk_idx` (`EquipmentType`),
  CONSTRAINT `equipment_etype_fk` FOREIGN KEY (`EquipmentType`) REFERENCES `equipment_type` (`TypeName`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `equipment_branch_fk` FOREIGN KEY (`BranchID`) REFERENCES `branch` (`BranchID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `equipment_type`
--

DROP TABLE IF EXISTS `equipment_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment_type` (
  `TypeName` varchar(45) NOT NULL,
  `DailyRate` int(11) NOT NULL,
  `HourlyRate` int(11) NOT NULL,
  PRIMARY KEY (`TypeName`),
  UNIQUE KEY `TypeName_UNIQUE` (`TypeName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `insurance`
--

DROP TABLE IF EXISTS `insurance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `insurance` (
  `InsuranceId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `Rate` int(11) DEFAULT NULL,
  `Status` enum('AVAILABLE','UNAVAILABLE') DEFAULT NULL,
  PRIMARY KEY (`InsuranceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `ReservationNo` int(11) NOT NULL AUTO_INCREMENT,
  `ReservationInfoId` int(11) NOT NULL,
  `Status` enum('VALID','CANCELLED') NOT NULL,
  PRIMARY KEY (`ReservationNo`),
  KEY `res_resinfo_fk_idx` (`ReservationInfoId`),
  CONSTRAINT `res_resinfo_fk` FOREIGN KEY (`ReservationInfoId`) REFERENCES `reservation_info` (`ReservationInfoId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reservation_info`
--

DROP TABLE IF EXISTS `reservation_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation_info` (
  `ReservationInfoId` int(11) NOT NULL AUTO_INCREMENT,
  `BranchId` int(11) NOT NULL,
  `ReserveTime` datetime NOT NULL,
  `EstimatePrice` int(11) DEFAULT NULL,
  `PickUpTime` datetime NOT NULL,
  `ReturnTime` datetime NOT NULL,
  `CustomerId` int(11) NOT NULL,
  `StaffId` int(11) NOT NULL,
  `VehicleClassName` varchar(45) NOT NULL,
  `vDailyRate` int(11) NOT NULL,
  `vHourlyRate` int(11) NOT NULL,
  `vWeeklyRate` int(11) NOT NULL,
  PRIMARY KEY (`ReservationInfoId`),
  KEY `resinfo_branch_idx` (`BranchId`),
  KEY `resinfo_customer_idx` (`CustomerId`),
  KEY `resinfo_staff_idx` (`StaffId`),
  KEY `refinfo_vclass_fk_idx` (`VehicleClassName`),
  CONSTRAINT `refinfo_vclass_fk` FOREIGN KEY (`VehicleClassName`) REFERENCES `vehicle_class` (`ClassName`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `resinfo_branch_fk` FOREIGN KEY (`BranchId`) REFERENCES `branch` (`BranchID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `resinfo_customer_fk` FOREIGN KEY (`CustomerId`) REFERENCES `customer` (`CustomerID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `resinfo_staff_fk` FOREIGN KEY (`StaffId`) REFERENCES `staff` (`StaffID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reserve_equipment`
--

DROP TABLE IF EXISTS `reserve_equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserve_equipment` (
  `ReservationInfoId` int(11) NOT NULL DEFAULT '0',
  `EquipmentType` varchar(45) NOT NULL DEFAULT '0',
  `eHourlyRate` int(11) NOT NULL,
  `eDailyRate` int(11) NOT NULL,
  PRIMARY KEY (`ReservationInfoId`,`EquipmentType`),
  KEY `reserve_equipment_equip_fk_idx` (`EquipmentType`),
  CONSTRAINT `reserve_equipment_equip_fk` FOREIGN KEY (`EquipmentType`) REFERENCES `equipment_type` (`TypeName`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `reserve_equipment_reserve_fk` FOREIGN KEY (`ReservationInfoId`) REFERENCES `reservation_info` (`ReservationInfoId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `StaffID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) NOT NULL,
  `MiddleName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `PhoneNo` varchar(15) NOT NULL,
  `Type` enum('CLERK','MANAGER','ADMIN') NOT NULL,
  `Status` enum('ACTIVE','DISACTIVE') NOT NULL,
  `Username` varchar(45) NOT NULL,
  `BranchID` int(11) NOT NULL,
  PRIMARY KEY (`StaffID`),
  KEY `Username_fk_idx` (`Username`),
  KEY `staff_branck_fk_idx` (`BranchID`),
  CONSTRAINT `staff_branck_fk` FOREIGN KEY (`BranchID`) REFERENCES `branch` (`BranchID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `staff_username_fk` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `support`
--

DROP TABLE IF EXISTS `support`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `support` (
  `EquipmentType` varchar(45) NOT NULL,
  `VehicleClassName` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`EquipmentType`,`VehicleClassName`),
  KEY `support_equipmenttype_fk_idx` (`EquipmentType`),
  KEY `support_vehicleclass_fk_idx` (`VehicleClassName`),
  CONSTRAINT `support_equipmenttype_fk` FOREIGN KEY (`EquipmentType`) REFERENCES `equipment_type` (`TypeName`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `support_vehicleclass_fk` FOREIGN KEY (`VehicleClassName`) REFERENCES `vehicle_class` (`ClassName`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Type` enum('CUSTOMER','STAFF') NOT NULL,
  `LastLogin` datetime DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle` (
  `VehicleNo` int(11) NOT NULL AUTO_INCREMENT,
  `PlateNo` varchar(45) NOT NULL,
  `ManufactureDate` date DEFAULT NULL,
  `Mode` varchar(45) DEFAULT NULL,
  `Odometer` int(11) DEFAULT NULL,
  `BranchID` int(11) NOT NULL,
  `Status` enum('FORRENT','FORSALE') DEFAULT NULL,
  `RentStatus` enum('AVAILABLE','UNAVAILABLE') DEFAULT NULL,
  `ClassName` varchar(45) NOT NULL,
  `Price` int(11) DEFAULT NULL,
  `SellStatus` enum('FORSALE','SOLD') DEFAULT NULL,
  PRIMARY KEY (`VehicleNo`),
  UNIQUE KEY `PlateNo_UNIQUE` (`PlateNo`),
  KEY `vehicleforrent_branch_fk_idx` (`BranchID`),
  KEY `vehicleforrent_class_fk_idx` (`ClassName`),
  CONSTRAINT `vehicleforrent_branch_fk` FOREIGN KEY (`BranchID`) REFERENCES `branch` (`BranchID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `vehicleforrent_class_fk` FOREIGN KEY (`ClassName`) REFERENCES `vehicle_class` (`ClassName`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vehicle_class`
--

DROP TABLE IF EXISTS `vehicle_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle_class` (
  `ClassName` varchar(45) NOT NULL,
  `Type` enum('Car','Truck') NOT NULL,
  `HourlyRate` int(11) NOT NULL,
  `DailyRate` int(11) NOT NULL,
  `WeeklyRate` int(11) NOT NULL,
  PRIMARY KEY (`ClassName`),
  UNIQUE KEY `ClassName_UNIQUE` (`ClassName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-06 12:31:20
