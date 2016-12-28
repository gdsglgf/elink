-- MySQL dump 10.13  Distrib 5.6.16, for Win32 (x86)
--
-- Host: localhost    Database: elink
-- ------------------------------------------------------
-- Server version	5.6.16

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
-- Table structure for table `t_batch`
--

DROP TABLE IF EXISTS `t_batch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_batch` (
  `batch_id` int(10) NOT NULL,
  `batch_name` varchar(32) NOT NULL,
  PRIMARY KEY (`batch_id`),
  UNIQUE KEY `batch_name` (`batch_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_directory`
--

DROP TABLE IF EXISTS `t_directory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_directory` (
  `dir_id` int(10) NOT NULL,
  `dir_name` varchar(32) NOT NULL,
  PRIMARY KEY (`dir_id`),
  UNIQUE KEY `dir_name` (`dir_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_html`
--

DROP TABLE IF EXISTS `t_html`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_html` (
  `html_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `docno` char(66) NOT NULL,
  `url` text NOT NULL,
  `title` text NOT NULL,
  `keywords` text NOT NULL,
  `description` text NOT NULL,
  `loc_id` bigint(20) unsigned NOT NULL,
  `rank` int(10) DEFAULT NULL,
  PRIMARY KEY (`html_id`),
  UNIQUE KEY `docno` (`docno`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_location`
--

DROP TABLE IF EXISTS `t_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_location` (
  `loc_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `disk_id` int(10) DEFAULT NULL,
  `dir_id` int(10) DEFAULT NULL,
  `batch_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`loc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_parser_log`
--

DROP TABLE IF EXISTS `t_parser_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_parser_log` (
  `log_id` int(10) NOT NULL AUTO_INCREMENT,
  `loc_id` bigint(20) unsigned NOT NULL,
  `html_amount` int(10) DEFAULT NULL,
  `cost_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`log_id`),
  UNIQUE KEY `loc_id` (`loc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-28 12:50:14
