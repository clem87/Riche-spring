-- MySQL dump 10.13  Distrib 5.5.46, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: riche2
-- ------------------------------------------------------
-- Server version	5.5.46-0ubuntu0.14.04.2-log

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
-- Table structure for table `Article`
--

DROP TABLE IF EXISTS `Article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AuthorityNotice`
--

DROP TABLE IF EXISTS `AuthorityNotice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AuthorityNotice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `noticeRef` varchar(255) DEFAULT NULL,
  `organisme_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_di4tsx4xuhu18mkx7scbq5fj0` (`organisme_id`),
  CONSTRAINT `FK_di4tsx4xuhu18mkx7scbq5fj0` FOREIGN KEY (`organisme_id`) REFERENCES `AuthorityOrganism` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AuthorityOrganism`
--

DROP TABLE IF EXISTS `AuthorityOrganism`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AuthorityOrganism` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `BibliographicType`
--

DROP TABLE IF EXISTS `BibliographicType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BibliographicType` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Person`
--

DROP TABLE IF EXISTS `Person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Person` (
  `DTYPE` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fistName` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Person_AuthorityNotice`
--

DROP TABLE IF EXISTS `Person_AuthorityNotice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Person_AuthorityNotice` (
  `Person_id` bigint(20) NOT NULL,
  `notices_id` bigint(20) NOT NULL,
  KEY `FK_qsvpj9vhcnjc0ees0f237olkp` (`notices_id`),
  KEY `FK_revd7fkybpes9k5u63k9txb2d` (`Person_id`),
  CONSTRAINT `FK_qsvpj9vhcnjc0ees0f237olkp` FOREIGN KEY (`notices_id`) REFERENCES `AuthorityNotice` (`id`),
  CONSTRAINT `FK_revd7fkybpes9k5u63k9txb2d` FOREIGN KEY (`Person_id`) REFERENCES `Person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `RelationSourcePerson`
--

DROP TABLE IF EXISTS `RelationSourcePerson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RelationSourcePerson` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rolePublication` varchar(255) DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  `source_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lpb3cvox12hxk1f7sykii93fn` (`person_id`),
  KEY `FK_q0o2o90882js2hh84ueftanjh` (`source_id`),
  CONSTRAINT `FK_lpb3cvox12hxk1f7sykii93fn` FOREIGN KEY (`person_id`) REFERENCES `Person` (`id`),
  CONSTRAINT `FK_q0o2o90882js2hh84ueftanjh` FOREIGN KEY (`source_id`) REFERENCES `Source` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `RelationWorkSource`
--

DROP TABLE IF EXISTS `RelationWorkSource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RelationWorkSource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `extract` varchar(255) DEFAULT NULL,
  `nature` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `tome` varchar(255) DEFAULT NULL,
  `source_id` bigint(20) DEFAULT NULL,
  `workEntity_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pelyatd30ffxjkx2kcy3lmamo` (`source_id`),
  KEY `FK_r50bfajvlw3irgiw8ml58n6n9` (`workEntity_id`),
  CONSTRAINT `FK_pelyatd30ffxjkx2kcy3lmamo` FOREIGN KEY (`source_id`) REFERENCES `Source` (`id`),
  CONSTRAINT `FK_r50bfajvlw3irgiw8ml58n6n9` FOREIGN KEY (`workEntity_id`) REFERENCES `WorkEntity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SecondaryName`
--

DROP TABLE IF EXISTS `SecondaryName`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SecondaryName` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_r3e86fy82o266xatvra2cymxt` (`person_id`),
  CONSTRAINT `FK_r3e86fy82o266xatvra2cymxt` FOREIGN KEY (`person_id`) REFERENCES `Person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Source`
--

DROP TABLE IF EXISTS `Source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Source` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `articleTitle` varchar(255) DEFAULT NULL,
  `editor` varchar(255) DEFAULT NULL,
  `journal` varchar(255) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `releaseTown` varchar(255) DEFAULT NULL,
  `releaseYear` int(11) DEFAULT NULL,
  `series` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `volume` int(11) DEFAULT NULL,
  `bibliographicType_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9dlp92cim9ohdbc5ip6u9pelg` (`bibliographicType_id`),
  CONSTRAINT `FK_9dlp92cim9ohdbc5ip6u9pelg` FOREIGN KEY (`bibliographicType_id`) REFERENCES `BibliographicType` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Theme`
--

DROP TABLE IF EXISTS `Theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Theme` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `WorkAuthor`
--

DROP TABLE IF EXISTS `WorkAuthor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WorkAuthor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `WorkEntity`
--

DROP TABLE IF EXISTS `WorkEntity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WorkEntity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `centuryMax` int(11) DEFAULT NULL,
  `centuryMin` int(11) DEFAULT NULL,
  `exactDate` varchar(255) DEFAULT NULL,
  `note` varchar(700) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `WorkEntity_Theme`
--

DROP TABLE IF EXISTS `WorkEntity_Theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WorkEntity_Theme` (
  `works_id` bigint(20) NOT NULL,
  `theme_id` bigint(20) NOT NULL,
  PRIMARY KEY (`works_id`,`theme_id`),
  KEY `FK_dy8y7j0v1rtr2wa494mwccgjv` (`theme_id`),
  CONSTRAINT `FK_dy8y7j0v1rtr2wa494mwccgjv` FOREIGN KEY (`theme_id`) REFERENCES `Theme` (`id`),
  CONSTRAINT `FK_p1ld4ixj2fnuwwpvwpu81n6js` FOREIGN KEY (`works_id`) REFERENCES `WorkEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `WorkEntity_WorkAuthor`
--

DROP TABLE IF EXISTS `WorkEntity_WorkAuthor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WorkEntity_WorkAuthor` (
  `WorkEntity_id` bigint(20) NOT NULL,
  `authors_id` bigint(20) NOT NULL,
  PRIMARY KEY (`WorkEntity_id`,`authors_id`),
  KEY `FK_ekryq9wkii5awir2d7r082amb` (`authors_id`),
  CONSTRAINT `FK_aer19gihsii0cnljjcdbhv72c` FOREIGN KEY (`WorkEntity_id`) REFERENCES `WorkEntity` (`id`),
  CONSTRAINT `FK_ekryq9wkii5awir2d7r082amb` FOREIGN KEY (`authors_id`) REFERENCES `WorkAuthor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-25 12:45:16
