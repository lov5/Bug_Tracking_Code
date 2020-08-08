-- MySQL dump 10.11
--
-- Host: localhost    Database: bts
-- ------------------------------------------------------
-- Server version	5.0.77-community-nt

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
-- Table structure for table `assignproject`
--

DROP TABLE IF EXISTS `assignproject`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `assignproject` (
  `projectID` int(11) default NULL,
  `empCode` int(11) default NULL,
  KEY `projectID` (`projectID`),
  KEY `empCode` (`empCode`),
  KEY `empCode_2` (`empCode`),
  CONSTRAINT `assignproject_ibfk_2` FOREIGN KEY (`empCode`) REFERENCES `employee` (`empCode`) ON DELETE CASCADE,
  CONSTRAINT `assignproject_ibfk_1` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `assignproject`
--

LOCK TABLES `assignproject` WRITE;
/*!40000 ALTER TABLE `assignproject` DISABLE KEYS */;
INSERT INTO `assignproject` VALUES (12,1005),(1,1003);
/*!40000 ALTER TABLE `assignproject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bugreport`
--

DROP TABLE IF EXISTS `bugreport`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `bugreport` (
  `bugNo` int(11) NOT NULL,
  `bugCode` int(11) default NULL,
  `projectID` int(11) default NULL,
  `Tcode` int(11) default NULL,
  `Ecode` int(11) default NULL,
  `status` varchar(20) default NULL,
  `bugDes` varchar(100) default NULL,
  PRIMARY KEY  (`bugNo`),
  KEY `Tcode` (`Tcode`),
  KEY `Ecode` (`Ecode`),
  KEY `projectID_2` (`projectID`),
  KEY `bugCode` (`bugCode`),
  CONSTRAINT `bugreport_ibfk_4` FOREIGN KEY (`bugCode`) REFERENCES `bugtype` (`bugCode`) ON DELETE CASCADE,
  CONSTRAINT `bugreport_ibfk_1` FOREIGN KEY (`Tcode`) REFERENCES `employee` (`empCode`) ON DELETE CASCADE,
  CONSTRAINT `bugreport_ibfk_2` FOREIGN KEY (`Ecode`) REFERENCES `employee` (`empCode`) ON DELETE CASCADE,
  CONSTRAINT `bugreport_ibfk_3` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `bugreport`
--

LOCK TABLES `bugreport` WRITE;
/*!40000 ALTER TABLE `bugreport` DISABLE KEYS */;
INSERT INTO `bugreport` VALUES (1,1,12,1004,1005,'pending','IITK');
/*!40000 ALTER TABLE `bugreport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bugtype`
--

DROP TABLE IF EXISTS `bugtype`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `bugtype` (
  `bugCode` int(11) NOT NULL,
  `bugCatgory` varchar(30) default NULL,
  `bugSeverty` varchar(20) default NULL,
  PRIMARY KEY  (`bugCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `bugtype`
--

LOCK TABLES `bugtype` WRITE;
/*!40000 ALTER TABLE `bugtype` DISABLE KEYS */;
INSERT INTO `bugtype` VALUES (1,'Functional Error','Critical'),(2,'Compilation Errors','Major'),(3,'Missing Commands','Low'),(4,'Run Time Errors','Critical'),(5,'Communication Problems','Medium'),(6,'Logical Errors','Critical'),(7,'Inapporopriate Error Handling','Medium'),(8,'Calculation Issues','Low'),(10,'fix bug','major');
/*!40000 ALTER TABLE `bugtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `employee` (
  `empCode` int(11) NOT NULL,
  `empName` varchar(30) default NULL,
  `empEmail` varchar(40) default NULL,
  `empPassword` varchar(20) default NULL,
  `gender` varchar(20) default NULL,
  `DOB` varchar(20) default NULL,
  `mobileNo` bigint(20) default NULL,
  `Role` varchar(20) default NULL,
  PRIMARY KEY  (`empCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1001,'Bhoopesh','bhoopesh@gmail.com','1234','Male','09-07-1998',9837980099,'admin'),(1002,'Eshan','eshan@gmail.com','12345','Male','09-07-1997',9837980098,'manager'),(1003,'Esha','esha@yahoo.com','esha123','Female','09-08-1995',9837980098,'developer'),(1004,'Anupam','anupam@hotmail.com','anupam123','Male','09-09-1999',8837980098,'tester'),(1005,'Luv','luv@hotmail.com','Luv123','Male','09-01-1996',7537980098,'developer'),(1006,'Megha','megha@gmail.com','Megha@123','Female','09-01-1992',6337980098,'manager');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `project` (
  `projectID` int(11) NOT NULL,
  `projectName` varchar(30) default NULL,
  `SDate` varchar(30) default NULL,
  `EDate` varchar(30) default NULL,
  `projectDec` varchar(200) default NULL,
  PRIMARY KEY  (`projectID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'Bug Monitor','09/07/2019','09/08/2020','Sponsored'),(12,'Missile Guider','12/3/112','1/3/2212','Aerospace');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-07 16:08:32
