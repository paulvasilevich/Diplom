-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: University
-- ------------------------------------------------------
-- Server version	5.7.19-0ubuntu0.16.04.1

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
-- Table structure for table `disciplines`
--

DROP TABLE IF EXISTS `disciplines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disciplines` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `discipline` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `discipline_UNIQUE` (`discipline`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disciplines`
--

LOCK TABLES `disciplines` WRITE;
/*!40000 ALTER TABLE `disciplines` DISABLE KEYS */;
INSERT INTO `disciplines` VALUES (9,'Biology'),(7,'Chemistry'),(1,'Cybernetics'),(2,'DNA Engineering'),(5,'Logic'),(8,'Mathematics'),(6,'Philosophy'),(4,'Physics'),(3,'Social Engineering');
/*!40000 ALTER TABLE `disciplines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_num` varchar(45) NOT NULL,
  `id_lecturer` int(11) DEFAULT NULL,
  `id_discipline` int(11) DEFAULT NULL,
  `id_lecturehall` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `group_num_UNIQUE` (`group_num`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'A01',1,1,1),(2,'A02',2,2,2),(3,'B02',2,3,4),(5,'D99',1,3,7),(6,'S101',2,2,8);
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lectDiscpRelationship`
--

DROP TABLE IF EXISTS `lectDiscpRelationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lectDiscpRelationship` (
  `id_lecturer` int(11) NOT NULL,
  `id_discipline` int(11) NOT NULL,
  PRIMARY KEY (`id_lecturer`,`id_discipline`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lectDiscpRelationship`
--

LOCK TABLES `lectDiscpRelationship` WRITE;
/*!40000 ALTER TABLE `lectDiscpRelationship` DISABLE KEYS */;
INSERT INTO `lectDiscpRelationship` VALUES (1,1),(1,3),(2,1),(2,2),(2,3),(3,5),(11,4),(11,6),(11,9);
/*!40000 ALTER TABLE `lectDiscpRelationship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturehalls`
--

DROP TABLE IF EXISTS `lecturehalls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lecturehalls` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lecturehall` varchar(45) NOT NULL,
  `id_lecturer` int(11) DEFAULT NULL,
  `id_group` int(11) DEFAULT NULL,
  `id_discipline` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `lecturehall_UNIQUE` (`lecturehall`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturehalls`
--

LOCK TABLES `lecturehalls` WRITE;
/*!40000 ALTER TABLE `lecturehalls` DISABLE KEYS */;
INSERT INTO `lecturehalls` VALUES (1,'111',NULL,NULL,NULL),(2,'111a',NULL,NULL,NULL),(3,'112',NULL,NULL,NULL),(4,'113',NULL,NULL,NULL),(5,'113b',NULL,NULL,NULL),(6,'333',NULL,NULL,NULL),(7,'115',NULL,NULL,NULL),(8,'210',NULL,NULL,NULL),(9,'213',NULL,NULL,NULL),(10,'342',NULL,NULL,NULL);
/*!40000 ALTER TABLE `lecturehalls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturers`
--

DROP TABLE IF EXISTS `lecturers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lecturers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(45) NOT NULL,
  `date_of_birth` varchar(45) NOT NULL,
  `passport` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `passport_UNIQUE` (`passport`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturers`
--

LOCK TABLES `lecturers` WRITE;
/*!40000 ALTER TABLE `lecturers` DISABLE KEYS */;
INSERT INTO `lecturers` VALUES (1,'Albert Einstein','22.10.1983','MC1255215'),(2,'Vasya Pupkin','10.12.1999','PS1351515'),(3,'James Broun','11.11.1989','PS5463646'),(5,'Ioganess Brams','14.09.1998','PS7345342'),(6,'Mr. Hollywood','15.08.1997','PS9214919'),(7,'Mike Kenwood','13.03.1971','MM0513981'),(8,'Ken Mikewood','22.01.1993','KS1241566');
/*!40000 ALTER TABLE `lecturers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(45) NOT NULL,
  `date_of_birth` varchar(45) NOT NULL,
  `passport` varchar(45) NOT NULL,
  `id_group` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `passport_UNIQUE` (`passport`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (2,'Rick Sanchez','22.08.1988','MC137',2),(3,'Rick Sanchez','22.08.1988','MC149',3),(6,'Almost Programmer','12.03.1978','ST6530235',3),(9,'Best lazyboy','05.07.1950','ST5754353',2),(10,'Jon Bones Jones','14.12.1988','SP2300114',5),(11,'Zelda Warbabe','19.03.1980','OPS215151',5);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-28  4:07:16
