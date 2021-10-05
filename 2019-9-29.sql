-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.67-community-nt

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
-- Current Database: `hpc`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `hpc` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `hpc`;

--
-- Table structure for table `applications`
--

DROP TABLE IF EXISTS `applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applications` (
  `appid` int(11) NOT NULL AUTO_INCREMENT,
  `appname` varchar(50) NOT NULL,
  `exports` varchar(1000) DEFAULT NULL,
  `scripts` varchar(1000) DEFAULT NULL,
  `files` varchar(100) NOT NULL,
  PRIMARY KEY (`appid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applications`
--

LOCK TABLES `applications` WRITE;
/*!40000 ALTER TABLE `applications` DISABLE KEYS */;
INSERT INTO `applications` VALUES (1,'Abaqus','export PATH=/storage/Application/DassaultSystemes/SIMULIA/Commands/:\\$PATH','infile=\\$(basename \'file1\')::export job_name=\\$(basename \\${infile%.*})::abq2017 job=\\$job_name interactive  cpus=\\$PBS_NP ask_delete=OFF','*.inp'),(2,'Ansys_Fluent','export PATH=/storage/Application/ansys_inc/v182/fluent/bin/:\\$PATH::export LD_LIBRARY_PATH=/storage/Application/ansys_inc/v182/fluent/lib/:\\$LD_LIBRARY_PATH::export NCPUS=\\$PBS_NP','infile=\\$(basename \'file1\')::export TEST=\\$(basename \\${infile%.*})::rm -f \\$TEST.dat::fluent 3ddp -g -t\\$NCPUS -i \\$TEST.in  > out_\\$PBS_JOBID.log','*.in'),(3,'Comsol','NP=\\$PBS_NP::export COMSOL_PATH=/storage/Application/comsol51/multiphysics/bin/glnxa64/','INPUT=\\$(basename \'file1\')::\\$COMSOL_PATH/comsol -np \\$NP  batch -inputfile \\$INPUT -outputfile \\$PBS_JOBID.\\$INPUT.mph','*.mph'),(4,'gaussian','export GAUSS_EXEDIR=/storage/Application/g16/minos/g16/sse4/g16/::export GAUSS_SCRDIR=/state/partition1/scratch/','infile=\\$(basename \'file1\')::export G09IN=\\$(basename \\${infile%.*}).gjf::export G09OUT=\\$(basename \\${infile%.*}).out::\\$GAUSS_EXEDIR/g16 < \\$G09IN > \\$G09OUT','*.gjf'),(5,'java','NP=\\$PBS_NP','infile=\\$(basename \'file1\')::JavaFile=\\$(basename \\${infile%.*})::javac \\$JavaFile.java::java \\$JavaFile > \\$PBS_JOBID.txt','*.java'),(6,'lammps','NP=\\$PBS_NP::export LAMMPSPATH=/storage/Application/lammps/lammps-11Aug17/src::export LD_LIBRARY_PATH=\\$LD_LIBRARY_PATH:/share/binary/mpich2-1.5_intel/lib/:/share/binary/fftw-2.1.5_intel/lib/::export LD_LIBRARY_PATH=/share/binary/fftw-3.3/lib:\\$LD_LIBRARY_PATH::source /storage/Application/Intel/source.sh','LAMMPSCONF=\\$(basename \'file1\')::mpirun -n \\$NP \\$LAMMPSPATH/lmp_intel_cpu_intelmpi < \\${LAMMPSCONF}','*.rhodo'),(7,'lumerical','source /storage/Application/Lumer.sh::NUM_PROCS=\\`/bin/awk \'END {print NR}\' \\$PBS_NODEFILE\\`::echo \\\"Starting run at: \\`date\\`\\\"::echo \\\"Running on \\$NUM_PROCS processors.\\\"::MY_PROG=\\\"/opt/lumerical/fdtd/bin/fdtd-engine-mpichp4\\\"','INPUT=\\\"\\$(basename \'file1\')\\\"::/opt/lumerical/fdtd/mpich/ch_p4/bin/mpirun -n \\$NUM_PROCS \\$MY_PROG ./\\${INPUT}::echo \\\"Job finished at: \\`date\\`\\\"::exit','*.fsp'),(8,'matlab','export MATLAB18=/storage/Application/MATLAB/R2018a/::export PATH=\\$PATH:/usr/local/bin','export MFILE=\\$(basename \'file1\')::time \\$MATLAB18/bin/matlab < \\$MFILE &> \\${PBS_JOBID}.Matout','*.m'),(9,'mpi','source /storage/Application/Intel/source.sh::NUM_PROCS=\\`/bin/awk \'END {print NR}\' \\$PBS_NODEFILE\\`','echo \\\"Starting run at: \\`date\\`\\\"::echo \\\"Running on \\$NUM_PROCS processors.\\\"::mpirun -n \\$NUM_PROCS --hostfile \\$PBS_NODEFILE ./\\$(basename \'file1\')::echo \\\"Job finished at: \\`date\\`\\\"::exit','*'),(10,'Python','source /storage/Application/Anaconda/Source.sh::NUM_PROCS=\\`/bin/awk \'END {print NR}\' \\$PBS_NODEFILE\\`','echo \\\"Starting run at: \\`date\\`\\\"::echo \\\"Running on \\$NUM_PROCS processors.\\\"::python  \\$(basename \'file1\')::echo \\\"Job finished at: \\`date\\`\\\"::exit','*.py'),(11,'siesta','NP=\\$PBS_NP::source /storage/Application/siesta/siesta-4.0/Build/source.sh::SIESTAPATH=/storage/Application/siesta/siesta-4.0/Build','infile=\\$(basename \'file1\')::SIESTACONF=\\$(basename \\${infile%.*}).fdf::SIESTAOUT=\\$(basename \\${infile%.*}).out::NUM_PROCS=\\`/bin/awk \'END {print NR}\' \\$PBS_NODEFILE\\`::echo \\\"Starting run at: \\`date\\`\\\"::echo \\\"Running on \\$NUM_PROCS processors.\\\"::mpirun -n \\$NUM_PROCS --hostfile \\$PBS_NODEFILE  \\$SIESTAPATH/siesta < \\$SIESTACONF | tee \\$SIESTAOUT::echo \\\"Finished run at: \\`date\\`\\\"','*.fdf'),(12,'OpenFoam','NP=\\$BPS_NP::NUM_PROCS=\\`/bin/awk \'END {print NR}\' \\$PBS_NODEFILE\\`::source /storage/Application/OF50/OpenFoam5/OpenFOAM-5.0/etc/bashrc::mkdir \\$PBS_JOBID::cp -r 0/  constant/ system/ \\$PBS_JOBID/::cd \\$PBS_JOBID','rm processor* -rf::blockMesh::dsmcInitialise::decomposePar::mpirun -n \\$NUM_PROCS --hostfile \\$PBS_NODEFILE  dsmcFoam -parallel','*');
/*!40000 ALTER TABLE `applications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty` (
  `facultyid` int(11) NOT NULL AUTO_INCREMENT,
  `facultyname` varchar(45) NOT NULL,
  PRIMARY KEY (`facultyid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'none'),(2,'Basic Sciences'),(3,'Chemical Engineering'),(4,'Mechanical Engineering'),(5,'Civil Engineering'),(6,'Electrical and Computer Engineering'),(7,'Materials and Industrial Engineering');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fees_group`
--

DROP TABLE IF EXISTS `fees_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fees_group` (
  `fgid` int(11) NOT NULL AUTO_INCREMENT,
  `fgname` varchar(20) NOT NULL,
  `uid` int(11) NOT NULL,
  `fgdescription` varchar(45) DEFAULT NULL COMMENT 'group owner (profesor id)',
  `charge` double NOT NULL,
  PRIMARY KEY (`fgid`),
  UNIQUE KEY `fgname` (`fgname`),
  KEY `FK_fees_group_uid` (`uid`),
  CONSTRAINT `FK_fees_group_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fees_group`
--

LOCK TABLES `fees_group` WRITE;
/*!40000 ALTER TABLE `fees_group` DISABLE KEYS */;
INSERT INTO `fees_group` VALUES (1,'raga',2,'description',490513.2293123857),(2,'ragro',35,'description',91601.49486606434),(3,'HPC_Gust',3,'description',9984989.92829192),(4,'Computer_Jazayeri',49,'description',-183068.8721330961),(5,'Electronic_Gholipour',59,'description',10000),(6,'Mechanic_Miansari',61,'description',10000),(7,'Electronic_AzizolaGa',62,'description',10000),(8,'Civil_Envirome_Abesi',67,'description',10000),(9,'Mechanic_Domiri',69,'description',10000),(10,'Civil_Struct_Fallah',74,'description',10000),(11,'Metal_HosseiniPour',78,'description',10000),(12,'Electronic_Hasanzade',81,'description',9543.974489572316),(13,'BioElectric_Sakhaei',83,'description',10000),(14,'Structures_Dehestani',85,'description',9985.073072740766),(15,'Mechanic_Farhadi',91,'description',10000),(16,'Mechanic_Ranjbar',98,'description',10000),(17,'Power_Hosseini',119,'description',10000),(18,'EarthQ_Abdollahzadeh',120,'description',10000),(19,'Telecom_Ebrahimzadeh',125,'description',100000),(20,'Physics_Mehri',56,'description',100000),(21,'Telecom_Zakeri',131,'description',100000),(22,'Manufacturing_Elyasi',132,'description',94000),(23,'Structures_Naghipour',134,'description',94000),(24,'NanoElectr_Soleimani',135,'description',100000);
/*!40000 ALTER TABLE `fees_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fees_group_users`
--

DROP TABLE IF EXISTS `fees_group_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fees_group_users` (
  `fguid` int(11) NOT NULL AUTO_INCREMENT,
  `fgid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `state` int(10) unsigned NOT NULL,
  `storage` int(10) unsigned NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  PRIMARY KEY (`fguid`),
  KEY `FK_fees_group_users_uid` (`uid`),
  KEY `FK_fees_group_users_fgid` (`fgid`),
  CONSTRAINT `FK_fees_group_users_fgid` FOREIGN KEY (`fgid`) REFERENCES `fees_group` (`fgid`),
  CONSTRAINT `FK_fees_group_users_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fees_group_users`
--

LOCK TABLES `fees_group_users` WRITE;
/*!40000 ALTER TABLE `fees_group_users` DISABLE KEYS */;
INSERT INTO `fees_group_users` VALUES (8,2,35,1,10,'2018-12-28 18:00:34','2018-12-28 18:00:34'),(9,1,41,1,10,'2019-09-25 11:48:55','2019-10-25 11:48:55'),(11,1,43,1,10,'2018-12-28 18:00:34','2018-12-28 18:00:34'),(20,2,50,1,10,'2018-12-28 18:00:34','2018-12-28 18:00:34'),(23,1,57,1,10,'2018-12-29 18:36:07','2019-01-28 18:36:07'),(24,1,12,1,10,'2019-01-01 16:07:07','2019-01-01 16:07:07'),(26,3,3,1,10,'2019-01-07 12:08:27','2019-02-06 12:08:27'),(27,4,49,1,10,'2019-01-16 12:08:59','2019-01-16 12:08:59'),(28,5,59,1,10,'2019-01-21 11:51:37','2019-01-21 11:51:37'),(29,4,60,1,10,'2019-01-29 09:36:07','2019-01-29 09:36:07'),(30,6,61,1,10,'2019-01-29 13:35:45','2019-01-29 13:35:45'),(31,7,62,1,10,'2019-02-05 11:54:03','2019-02-05 11:54:03'),(32,7,66,1,10,'2019-02-05 12:42:09','2019-02-05 12:42:09'),(33,8,67,1,10,'2019-02-05 15:38:41','2019-02-05 15:38:41'),(34,8,68,1,10,'2019-02-06 10:39:59','2019-02-06 10:39:59'),(35,9,69,1,10,'2019-02-23 16:04:57','2019-02-23 16:04:57'),(36,9,72,1,10,'2019-02-24 15:16:03','2019-02-24 15:16:03'),(37,10,74,1,10,'2019-02-26 11:52:00','2019-02-26 11:52:00'),(38,10,73,1,10,'2019-02-26 11:52:27','2019-02-26 11:52:27'),(39,9,75,1,10,'2019-02-27 11:15:04','2019-02-27 11:15:04'),(40,5,77,1,10,'2019-04-09 12:08:10','2019-04-09 12:08:10'),(42,11,78,1,10,'2019-04-30 09:54:36','2019-04-30 09:54:36'),(43,11,79,1,10,'2019-04-30 09:55:42','2019-04-30 09:55:42'),(44,12,81,1,10,'2019-04-30 09:57:43','2019-04-30 09:57:43'),(45,12,80,1,10,'2019-05-01 13:46:30','2019-05-01 13:46:30'),(46,13,83,1,10,'2019-05-21 12:44:13','2019-05-21 12:44:13'),(49,13,82,1,10,'2019-05-21 12:46:56','2019-05-21 12:46:56'),(50,14,85,1,10,'2019-05-28 10:45:59','2019-05-28 10:45:59'),(51,14,86,1,10,'2019-05-29 12:10:06','2019-05-29 12:10:06'),(52,15,91,1,10,'2019-06-10 14:24:18','2019-06-10 14:24:18'),(53,15,92,1,10,'2019-06-11 15:29:06','2019-06-11 15:29:06'),(54,16,98,1,10,'2019-06-17 12:08:00','2019-06-17 12:08:00'),(55,16,99,1,10,'2019-06-17 12:35:37','2019-06-17 12:35:37'),(56,17,119,1,10,'2019-06-22 10:00:02','2019-06-22 10:00:02'),(57,17,90,1,10,'2019-06-22 10:00:58','2019-06-22 10:00:58'),(58,18,120,1,10,'2019-06-22 15:52:20','2019-06-22 15:52:20'),(59,18,87,1,10,'2019-06-22 15:55:05','2019-06-22 15:55:05'),(60,18,88,1,10,'2019-06-22 15:55:30','2019-06-22 15:55:30'),(61,18,89,1,10,'2019-06-22 15:55:53','2019-06-22 15:55:53'),(62,16,122,1,10,'2019-06-24 08:45:56','2019-06-24 08:45:56'),(63,19,125,1,10,'2019-06-26 11:57:25','2019-06-26 11:57:25'),(64,19,126,1,10,'2019-06-26 12:16:18','2019-06-26 12:16:18'),(65,20,56,1,10,'2019-06-26 14:26:54','2019-06-26 14:26:54'),(66,12,127,0,10,'2019-07-07 09:46:56','2019-07-07 09:46:56'),(67,12,128,0,10,'2019-07-15 10:27:59','2019-07-15 10:27:59'),(69,21,131,1,10,'2019-07-16 11:41:33','2019-07-16 11:41:33'),(70,21,130,1,10,'2019-07-16 11:47:41','2019-07-16 11:47:41'),(71,22,132,1,10,'2019-07-21 13:05:08','2019-07-21 13:05:08'),(72,22,129,1,10,'2019-07-21 13:07:37','2019-08-20 13:07:37'),(74,23,134,1,10,'2019-08-03 10:25:37','2019-08-03 10:25:37'),(75,23,133,1,10,'2019-08-03 10:28:15','2019-09-02 10:28:15'),(76,24,135,1,10,'2019-08-05 14:08:52','2019-08-05 14:08:52'),(77,15,136,1,10,'2019-09-14 15:21:41','2019-09-14 15:21:41'),(80,1,137,1,10,'2019-09-25 11:45:54','2019-09-25 11:45:54'),(81,1,2,1,10,'2019-09-25 14:21:54','2019-09-25 14:21:54');
/*!40000 ALTER TABLE `fees_group_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade` (
  `gradeid` int(11) NOT NULL AUTO_INCREMENT,
  `gradename` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`gradeid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES (1,'none'),(2,'Bachelor'),(3,'Masters'),(4,'P.H.D');
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_permissions`
--

DROP TABLE IF EXISTS `group_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_permissions` (
  `gpid` int(11) NOT NULL AUTO_INCREMENT,
  `gid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`gpid`),
  KEY `FK_group_permissions_gid` (`gid`),
  KEY `FK_group_permissions_pid` (`pid`),
  CONSTRAINT `FK_group_permissions_gid` FOREIGN KEY (`gid`) REFERENCES `groups` (`gid`),
  CONSTRAINT `FK_group_permissions_pid` FOREIGN KEY (`pid`) REFERENCES `permissions` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_permissions`
--

LOCK TABLES `group_permissions` WRITE;
/*!40000 ALTER TABLE `group_permissions` DISABLE KEYS */;
INSERT INTO `group_permissions` VALUES (1,2,5),(2,2,6),(3,2,7),(4,2,8),(5,2,9),(6,2,10),(7,2,15),(8,1,1),(9,1,2),(10,1,3),(11,1,12),(12,1,14),(13,1,17),(14,1,18),(15,1,19),(16,1,20),(17,1,22),(18,1,23),(19,1,24),(20,3,4),(21,3,5),(22,3,6),(23,3,7),(24,3,8),(25,3,9),(26,3,10),(27,3,11),(28,3,13),(29,3,15),(30,3,16),(31,3,21),(32,1,25),(33,1,26),(34,1,27),(35,1,28),(36,2,28),(37,3,28),(38,1,5),(39,1,6);
/*!40000 ALTER TABLE `group_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_users`
--

DROP TABLE IF EXISTS `group_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_users` (
  `guid` int(11) NOT NULL AUTO_INCREMENT,
  `gid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`guid`),
  KEY `FK_group_users_gid` (`gid`),
  KEY `FK_group_users_uid` (`uid`),
  CONSTRAINT `FK_group_users_gid` FOREIGN KEY (`gid`) REFERENCES `groups` (`gid`),
  CONSTRAINT `FK_group_users_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_users`
--

LOCK TABLES `group_users` WRITE;
/*!40000 ALTER TABLE `group_users` DISABLE KEYS */;
INSERT INTO `group_users` VALUES (1,1,1),(2,2,2),(3,3,2),(4,1,3),(5,2,4),(9,2,12),(10,2,13),(11,2,35),(12,3,35),(13,2,41),(14,2,42),(15,2,43),(19,2,48),(20,2,49),(21,2,50),(22,2,53),(23,3,3),(24,2,55),(25,2,56),(26,2,57),(27,3,49),(28,2,59),(29,3,59),(30,2,60),(31,2,61),(32,3,61),(33,2,62),(34,3,62),(35,2,66),(36,2,67),(37,3,67),(38,2,68),(40,2,72),(41,2,73),(42,2,74),(43,3,74),(44,2,69),(45,3,69),(46,2,75),(47,2,77),(48,2,78),(49,2,79),(50,2,80),(51,2,81),(52,3,78),(53,3,81),(54,2,82),(55,2,83),(56,3,83),(57,2,85),(58,3,85),(59,2,86),(60,2,87),(61,2,88),(62,2,89),(63,2,91),(64,3,91),(65,2,90),(66,2,92),(67,2,98),(68,3,98),(69,2,99),(70,2,119),(71,3,119),(72,2,120),(73,3,120),(74,2,122),(75,2,125),(76,3,125),(77,2,126),(78,3,56),(79,2,127),(80,2,128),(81,2,129),(82,2,130),(83,2,131),(84,3,131),(85,2,132),(86,3,132),(87,2,133),(88,2,134),(89,3,134),(90,2,135),(91,3,135),(92,2,136),(93,2,137);
/*!40000 ALTER TABLE `group_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(20) NOT NULL,
  `gdescription` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'Admin','HPC admin'),(2,'HPCUser','HPC users'),(3,'owner','HPC Group Owner');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hpclimitations`
--

DROP TABLE IF EXISTS `hpclimitations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hpclimitations` (
  `hpclid` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `nodemax` int(11) NOT NULL,
  `coremax` int(11) NOT NULL,
  `memmax` int(11) NOT NULL,
  `storagemax` int(11) NOT NULL,
  PRIMARY KEY (`hpclid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hpclimitations`
--

LOCK TABLES `hpclimitations` WRITE;
/*!40000 ALTER TABLE `hpclimitations` DISABLE KEYS */;
INSERT INTO `hpclimitations` VALUES (1,'verylimited',1,4,8,20),(2,'perfect',1,16,24,200);
/*!40000 ALTER TABLE `hpclimitations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hpctariff`
--

DROP TABLE IF EXISTS `hpctariff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hpctariff` (
  `hpctid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `corecost` int(10) unsigned NOT NULL,
  `maxnodes` int(10) unsigned NOT NULL,
  `maxcores` int(10) unsigned NOT NULL,
  `maxmemory` int(10) unsigned NOT NULL,
  `maxstorage` int(10) unsigned NOT NULL,
  `mincharge` int(10) unsigned NOT NULL,
  `queuesize` int(10) unsigned NOT NULL,
  `maxuserjobs` int(10) unsigned NOT NULL,
  `maxcput` int(10) unsigned NOT NULL,
  `maxwallt` int(10) unsigned NOT NULL,
  `maxusercjobs` int(10) unsigned NOT NULL,
  `memcost` int(10) unsigned NOT NULL,
  PRIMARY KEY (`hpctid`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hpctariff`
--

LOCK TABLES `hpctariff` WRITE;
/*!40000 ALTER TABLE `hpctariff` DISABLE KEYS */;
INSERT INTO `hpctariff` VALUES (4,'batch',10,1,44,64,10,1000,240,3,31724,720,2,5),(8,'limited',10,1,1,8,10,1200,1200,80,241,241,40,5),(9,'Gust_tariff',15,1,44,64,1,10000,6,3,500,130,3,100),(10,'On_Demand_Tarif',10,2,44,64,2,20000,10,4,1000,130,10,10);
/*!40000 ALTER TABLE `hpctariff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hpcuserapp`
--

DROP TABLE IF EXISTS `hpcuserapp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hpcuserapp` (
  `hpcuappid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `appid` int(11) NOT NULL,
  PRIMARY KEY (`hpcuappid`),
  KEY `FK_hpcuserapp_uid` (`uid`),
  KEY `FK_hpcuserapp_appid` (`appid`),
  CONSTRAINT `FK_hpcuserapp_appid` FOREIGN KEY (`appid`) REFERENCES `applications` (`appid`),
  CONSTRAINT `FK_hpcuserapp_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hpcuserapp`
--

LOCK TABLES `hpcuserapp` WRITE;
/*!40000 ALTER TABLE `hpcuserapp` DISABLE KEYS */;
INSERT INTO `hpcuserapp` VALUES (1,1,1);
/*!40000 ALTER TABLE `hpcuserapp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hpcuserlimitation`
--

DROP TABLE IF EXISTS `hpcuserlimitation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hpcuserlimitation` (
  `hpculid` int(11) NOT NULL,
  `hpclid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`hpculid`),
  KEY `FK_hpcuserlimitation_hpclid` (`hpclid`),
  KEY `FK_hpcuserlimitation_uid` (`uid`),
  CONSTRAINT `FK_hpcuserlimitation_hpclid` FOREIGN KEY (`hpclid`) REFERENCES `hpclimitations` (`hpclid`),
  CONSTRAINT `FK_hpcuserlimitation_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hpcuserlimitation`
--

LOCK TABLES `hpcuserlimitation` WRITE;
/*!40000 ALTER TABLE `hpcuserlimitation` DISABLE KEYS */;
/*!40000 ALTER TABLE `hpcuserlimitation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobs` (
  `jid` int(11) NOT NULL AUTO_INCREMENT,
  `jname` varchar(20) DEFAULT NULL,
  `appname` varchar(20) DEFAULT 'matlab',
  `uid` int(11) NOT NULL,
  `nodenum` int(11) DEFAULT NULL,
  `corenum` int(11) DEFAULT NULL,
  `memnum` int(11) DEFAULT NULL,
  `jobid` int(11) DEFAULT NULL,
  `queue` varchar(20) DEFAULT 'limited',
  `walltime` int(10) unsigned DEFAULT '1',
  `hpctid` int(10) unsigned DEFAULT NULL,
  `fgid` int(10) unsigned DEFAULT NULL,
  `state` varchar(1) NOT NULL,
  `cputime` double DEFAULT NULL,
  `createdate` datetime NOT NULL,
  PRIMARY KEY (`jid`),
  KEY `FK_jobs_uid` (`uid`),
  KEY `FK_jobs_hpctid` (`hpctid`),
  CONSTRAINT `FK_jobs_hpctid` FOREIGN KEY (`hpctid`) REFERENCES `hpctariff` (`hpctid`),
  CONSTRAINT `FK_jobs_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=234 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobs`
--

LOCK TABLES `jobs` WRITE;
/*!40000 ALTER TABLE `jobs` DISABLE KEYS */;
INSERT INTO `jobs` VALUES (1,' matlabtest','Matlab',2,1,8,4,31,'batch',100,4,1,'C',0,'2018-10-22 13:01:45'),(2,'sam','Matlab',2,1,1,1,32,'batch',1,4,1,'C',0,'2018-10-23 12:53:44'),(3,'sam2','Matlab',2,1,1,1,33,'batch',1,4,1,'C',0,'2018-10-23 13:01:47'),(4,'sam3','Matlab',2,1,1,1,34,'batch',1,4,1,'C',0,'2018-10-23 13:36:21'),(5,'sam4','Matlab',2,1,1,1,35,'batch',1,4,1,'C',0,'2018-10-23 13:40:11'),(6,'sam5','Matlab',2,1,3,1,44,'batch',2,4,1,'C',0,'2018-10-25 23:56:33'),(7,'sam14','matlab',2,1,1,1,58,'batch',1,4,1,'C',0.05,'2018-10-26 18:14:36'),(8,'sam15','matlab',2,1,1,1,59,'batch',1,4,1,'C',0.0166666666666667,'2018-10-26 18:21:11'),(9,'sam16','matlab',2,1,1,1,60,'batch',1,4,1,'C',0,'2018-10-26 18:25:35'),(10,'sam17','matlab',2,1,1,1,61,'batch',1,4,1,'C',0,'2018-10-26 18:31:43'),(11,'sam19','Python',2,1,1,1,65,'batch',1,4,1,'C',0,'2018-10-26 19:01:20'),(12,'sam20','Python',2,1,1,1,66,'batch',1,4,1,'C',0,'2018-10-26 19:07:07'),(13,'sam21','Python',2,1,1,1,67,'batch',1,4,1,'C',0,'2018-10-26 19:24:36'),(14,'sam22','Abaqus',2,1,1,1,68,'batch',1,4,1,'C',0,'2018-10-26 19:27:25'),(15,'sam24','Comsol',2,1,1,1,69,'batch',1,4,1,'C',0,'2018-10-26 19:30:56'),(16,'sam25','Ansys',2,1,1,1,70,'batch',1,4,1,'C',0,'2018-10-26 19:34:38'),(17,'sam25','Comsol',2,1,1,1,71,'batch',1,4,1,'C',0,'2018-10-26 19:38:19'),(18,'sam26','Ansys',2,1,1,1,72,'batch',1,4,1,'C',0,'2018-10-26 19:41:02'),(19,'sam27','Ansys',2,1,1,1,73,'batch',1,4,1,'C',0,'2018-10-26 19:45:11'),(20,'sam28','Ansys',2,1,1,1,74,'batch',1,4,1,'C',0,'2018-10-26 19:50:57'),(21,'sam29','java',2,1,1,1,75,'batch',1,4,1,'C',0,'2018-10-26 19:55:10'),(22,'sam30','gaussian',2,1,1,1,76,'batch',1,4,1,'C',0,'2018-10-26 20:16:50'),(23,'sam31','java',2,1,1,1,77,'batch',1,4,1,'C',0,'2018-10-26 20:34:52'),(24,'sam32','java',2,1,1,1,78,'batch',1,4,1,'C',0,'2018-10-26 20:58:23'),(25,'sam33','lammps',2,1,1,1,79,'batch',1,4,1,'C',0,'2018-10-26 21:00:12'),(26,'sam34','mpi',2,1,1,1,80,'batch',1,4,1,'C',0,'2018-10-26 21:07:16'),(27,'sam35','mpi',2,1,1,1,81,'batch',1,4,1,'C',0,'2018-10-26 21:10:28'),(28,'36','mpi',2,1,1,1,82,'batch',1,4,1,'C',0,'2018-10-26 21:14:22'),(29,'sam37','mpi',2,1,1,1,83,'batch',1,4,1,'C',0,'2018-10-26 21:16:05'),(30,'samR','Abaqus',2,1,7,4,87,'batch',2,4,1,'C',0.166666666666667,'2018-10-27 10:38:03'),(31,'testRa','gaussian',2,1,1,12,92,'batch',100,4,1,'C',0.166666666666667,'2018-10-27 10:57:44'),(32,'testCom','Comsol',2,1,8,12,93,'batch',100,4,1,'C',0,'2018-10-27 11:15:21'),(33,'samR2','Comsol',2,1,4,2,99,'batch',3,4,1,'C',0,'2018-10-27 11:39:48'),(34,'comsol2','Comsol',2,1,1,1,102,'batch',1,4,1,'C',0,'2018-10-27 11:43:49'),(35,'comsol3','Comsol',2,1,1,1,104,'batch',1,4,1,'C',0,'2018-10-27 11:49:27'),(36,'1','Comsol',2,1,1,1,106,'batch',1,4,1,'C',0,'2018-10-27 12:00:20'),(37,'sam_newtest','matlab',2,1,1,1,109,'batch',1,4,1,'C',0,'2018-10-28 19:18:44'),(38,'sam_1','matlab',2,1,1,1,110,'batch',1,4,1,'C',0,'2018-10-28 19:29:14'),(39,'a','matlab',2,1,1,1,122,'batch',1,4,1,'C',0,'2018-10-28 20:24:38'),(40,'ddd','matlab',2,1,1,1,124,'batch',2,4,1,'C',0,'2018-10-31 00:45:49'),(41,'ddd','matlab',12,1,1,1,125,'batch',1,4,1,'C',0,'2018-10-31 01:48:30'),(42,'mahsa','java',12,1,1,1,126,'batch',1,4,1,'C',0,'2018-10-31 15:02:42'),(43,'mahsatest','Python',12,1,1,1,127,'batch',1,4,1,'C',0,'2018-10-31 15:04:42'),(44,'mahsa','matlab',13,1,1,1,128,'batch',1,4,1,'C',0,'2018-10-31 15:26:52'),(45,'ra_matlab','matlab',35,1,10,10,129,'batch',100,4,2,'C',0,'2018-11-02 17:39:34'),(46,'ra_python','Python',35,1,15,10,130,'batch',100,4,2,'C',0,'2018-11-02 17:40:37'),(47,'ra_abaqus','Abaqus',35,1,16,6,131,'batch',12,4,2,'C',0.0166666666666667,'2018-11-02 17:42:09'),(48,'ra_comsol','Comsol',35,1,10,10,132,'batch',10,4,2,'C',0,'2018-11-02 17:43:46'),(49,'ra_ansys','Ansys',35,1,12,12,133,'batch',12,4,2,'C',0,'2018-11-02 17:46:05'),(50,'ra_gaussian','gaussian',35,1,6,12,134,'batch',6,4,2,'C',0,'2018-11-02 17:46:49'),(51,'ra_java','java',35,1,2,2,135,'batch',1,4,2,'C',0,'2018-11-02 17:48:42'),(52,'ra_lammps','lammps',35,1,7,8,136,'batch',7,4,2,'C',7,'2018-11-02 17:49:07'),(53,'ra_mpi','mpi',35,2,3,3,137,'batch',3,4,2,'C',0,'2018-11-02 17:50:41'),(54,'matlab','matlab',2,1,5,1,139,'batch',1,4,1,'C',0.1,'2018-11-03 01:51:24'),(55,'testJob_ma','matlab',41,1,1,1,140,'batch',1,4,1,'C',0,'2018-11-03 12:18:13'),(56,'jusef-test','matlab',41,1,8,1,142,'batch',10,4,1,'C',0,'2018-11-04 10:56:28'),(57,'test_at','matlab',2,1,1,1,143,'batch',1,4,1,'C',0.1,'2018-11-04 11:02:41'),(58,'test1','matlab',43,1,4,1,144,'batch',1,4,1,'C',0,'2018-11-04 11:20:59'),(59,'matlab_tt','matlab',48,1,24,6,145,'batch',1,4,1,'C',0,'2018-11-04 18:09:38'),(60,'mat','matlab',48,1,24,1,146,'batch',4,4,1,'C',0,'2018-11-04 18:12:38'),(61,'single_j','matlab',2,1,1,8,147,'limited',240,8,1,'C',0,'2018-11-04 18:38:16'),(62,'sam_matlab','matlab',2,1,1,1,154,'batch',1,4,1,'C',1.21722222222222,'2018-11-11 12:10:31'),(63,'matlab_t4','matlab',2,1,1,1,155,'limited',1,8,1,'C',0.133333333333333,'2018-11-11 12:15:54'),(64,'matla_t','matlab',50,1,1,1,156,'limited',1,8,1,'C',0,'2018-11-12 16:52:49'),(65,'mat_test','matlab',2,1,24,6,159,'batch',1,4,1,'C',1.24444444444444,'2018-11-13 21:47:34'),(66,'mat_test2','matlab',2,1,44,64,160,'batch',1,4,1,'C',1.24,'2018-11-13 22:02:49'),(67,'mat_test3','matlab',2,1,44,64,161,'batch',1,4,1,'C',1.2125,'2018-11-13 22:16:16'),(68,'mat_test4','matlab',2,1,44,64,162,'batch',2,4,1,'C',1.18,'2018-11-13 22:16:58'),(69,'mat_test5','matlab',2,1,44,64,163,'batch',1,4,1,'C',1.17833333333333,'2018-11-13 22:17:28'),(70,'matlab_t5','matlab',50,1,44,64,164,'batch',2,4,1,'C',0.0741666666666667,'2018-11-13 22:18:58'),(71,'sss','matlab',50,1,1,1,165,'batch',1,4,1,'C',0.0708333333333333,'2018-11-13 22:32:05'),(72,'mat_test6','matlab',50,1,44,64,166,'batch',33,4,1,'C',0,'2018-11-18 14:28:29'),(73,'mat_test7','matlab',50,1,1,1,167,'limited',1,8,1,'C',0,'2018-11-18 15:19:03'),(74,'mat_test8','matlab',50,1,1,1,168,'limited',1,8,1,'C',0,'2018-11-18 15:20:15'),(75,'mat_test9','matlab',50,1,1,1,172,'limited',1,8,1,'C',0.0744444444444445,'2018-11-18 15:22:56'),(76,'mat_test10','matlab',50,1,1,1,173,'limited',1,8,1,'C',0.0708333333333333,'2018-11-18 15:26:47'),(77,'mat_test11','matlab',50,1,1,1,174,'limited',1,8,1,'C',0.0713888888888889,'2018-11-18 16:01:49'),(78,'hamid','matlab',2,1,44,64,175,'batch',1,4,1,'C',1.20138888888889,'2018-11-19 17:48:14'),(79,'matlab_6','matlab',2,1,1,1,178,'limited',1,8,1,'C',1.20166666666667,'2018-11-21 03:43:23'),(80,'tt','matlab',49,1,1,1,226,'batch',1,4,1,'C',0.00611111111111111,'2018-11-28 11:06:51'),(81,'ttt','matlab',49,1,1,1,227,'batch',1,4,1,'C',0.005,'2018-11-28 11:16:11'),(82,'ttt','matlab',49,1,1,1,228,'batch',1,4,1,'C',0.00472222222222222,'2018-11-28 11:17:02'),(83,'ttt','matlab',49,1,1,1,229,'batch',1,4,1,'C',0.00472222222222222,'2018-11-28 11:17:24'),(84,'ttt','matlab',49,1,1,1,230,'batch',1,4,1,'C',0.00472222222222222,'2018-11-28 11:19:01'),(85,'tt','matlab',42,1,1,1,231,'batch',1,4,1,'C',0.00611111111111111,'2018-11-28 18:19:39'),(86,'ttt','matlab',42,1,1,1,232,'batch',1,4,1,'C',0.005,'2018-11-28 18:30:05'),(87,'tttt','matlab',42,1,1,1,233,'batch',1,4,1,'C',0.00444444444444444,'2018-11-28 18:30:56'),(88,'tabaqus','Abaqus',50,1,1,1,234,'limited',1,8,1,'C',0.0172222222222222,'2018-12-01 02:26:33'),(89,'tansys','Ansys',50,1,1,1,235,'limited',1,8,1,'C',0.0025,'2018-12-01 02:27:42'),(90,'tansys2','Ansys',50,1,1,1,236,'limited',1,8,1,'C',0.0025,'2018-12-01 02:33:00'),(91,'tcomsol','Comsol',50,1,1,1,237,'limited',1,8,1,'C',0.00472222222222222,'2018-12-01 02:34:23'),(92,'tcomsol2','Comsol',50,1,1,1,238,'limited',1,8,1,'C',0.00444444444444444,'2018-12-01 02:40:51'),(93,'tabaqus2','Abaqus',50,1,1,1,239,'limited',1,8,1,'C',0.0169444444444444,'2018-12-01 03:07:04'),(94,'tansys3','Ansys',50,1,1,1,240,'limited',1,8,1,'C',0.0075,'2018-12-01 03:08:27'),(95,'tcomsol3','Comsol',50,1,1,1,241,'limited',1,8,1,'C',0.00694444444444444,'2018-12-01 03:13:00'),(96,'tgassian','gaussian',50,1,1,1,242,'limited',1,8,1,'C',0.379166666666667,'2018-12-01 03:26:28'),(97,'tgassian2','gaussian',50,1,1,1,243,'limited',1,8,1,'C',0.364444444444444,'2018-12-01 03:28:57'),(98,'tjava','java',50,1,1,1,244,'limited',1,8,1,'C',0.000555555555555556,'2018-12-01 03:31:31'),(99,'tlammps','lammps',50,1,1,1,245,'limited',1,8,1,'C',2.01361111111111,'2018-12-01 03:35:08'),(100,'tlumerical','lumerical',50,1,1,1,246,'limited',1,8,1,'C',0.00222222222222222,'2018-12-01 03:37:38'),(101,'tlumerical','lumerical',50,1,1,1,247,'limited',1,8,1,'C',0.00222222222222222,'2018-12-01 03:38:10'),(102,'tmatlab','matlab',50,1,1,1,248,'limited',1,8,1,'C',0.0694444444444444,'2018-12-01 03:43:20'),(103,'tmpi','mpi',50,1,1,1,249,'limited',1,8,1,'C',1.00277777777778,'2018-12-01 03:45:52'),(104,'tpython','Python',50,1,1,1,250,'limited',1,8,1,'C',0,'2018-12-01 03:48:11'),(105,'tsiesta','siesta',50,1,1,1,251,'limited',1,8,1,'C',0.0025,'2018-12-01 03:52:26'),(106,'tabaqus','Abaqus',2,1,1,1,253,'limited',1,8,1,'C',0.0172222222222222,'2018-12-01 07:42:48'),(107,'testrama','lumerical',2,1,4,1,254,'batch',2,4,1,'C',0,'2018-12-02 11:54:44'),(108,'testLamp','lammps',2,1,4,2,255,'batch',24,4,1,'C',0,'2018-12-02 11:59:22'),(109,'ttt','Abaqus',2,1,1,1,256,'limited',1,8,1,'C',0.0177777777777778,'2018-12-02 14:21:13'),(110,'ttt','Abaqus',2,1,1,1,257,'limited',1,8,1,'C',0.000277777777777778,'2018-12-02 14:21:35'),(111,'trityrg','Abaqus',53,1,1,1,260,'batch',2,4,3,'C',0.0172222222222222,'2018-12-02 16:16:58'),(112,'Test','Abaqus',2,1,20,4,262,'batch',1,4,1,'C',0.339722222222222,'2018-12-02 20:11:27'),(113,'Test2','Abaqus',2,1,44,20,263,'batch',1,4,1,'C',1.39388888888889,'2018-12-02 20:16:55'),(114,'Test2','Abaqus',2,1,44,20,264,'batch',1,4,1,'C',0,'2018-12-02 20:18:50'),(115,'Test2','Abaqus',2,1,44,20,265,'batch',1,4,1,'C',0,'2018-12-02 20:18:56'),(116,'Test2','Abaqus',2,1,44,20,266,'batch',1,4,1,'C',0,'2018-12-02 20:19:02'),(117,'Test2','Abaqus',2,1,44,20,267,'batch',1,4,1,'C',1.53666666666667,'2018-12-02 20:19:31'),(118,'Test2','Abaqus',2,1,44,20,268,'batch',1,4,1,'C',0,'2018-12-02 20:19:54'),(119,'Test','Ansys',2,1,20,5,269,'batch',2,4,1,'C',0.0555555555555556,'2018-12-02 20:21:32'),(120,'Test','mpi',2,1,44,1,270,'batch',1,4,1,'C',0.000833333333333334,'2018-12-02 20:22:39'),(121,'11','mpi',2,1,11,1,271,'batch',1,4,1,'C',0,'2018-12-02 20:23:26'),(122,'11','Comsol',2,1,11,1,272,'batch',11,4,1,'C',0.0075,'2018-12-02 20:24:10'),(123,'44','mpi',2,1,44,1,273,'batch',4,4,1,'C',176.076944444444,'2018-12-02 20:33:35'),(124,'44','mpi',2,1,44,1,274,'batch',4,4,1,'C',176.015277777778,'2018-12-02 20:33:40'),(125,'44','mpi',2,1,44,1,275,'batch',4,4,1,'C',176.463333333333,'2018-12-02 20:34:10'),(126,'tttr','Abaqus',42,1,1,1,278,'limited',1,8,1,'C',0.0241666666666667,'2018-12-03 01:50:18'),(127,'roozbeh','lumerical',55,1,8,16,280,'batch',2,4,3,'C',0.00444444444444444,'2018-12-04 09:02:24'),(128,'mehri','matlab',56,1,8,8,285,'batch',1,4,3,'C',0.108888888888889,'2018-12-04 15:56:45'),(129,'hiMohsen','matlab',2,1,1,1,288,'batch',1,4,1,'C',0.175,'2018-12-04 21:23:36'),(130,'test','matlab',56,1,1,1,289,'limited',1,8,3,'C',0.00444444444444444,'2018-12-04 21:40:41'),(131,'sss','Abaqus',2,1,1,1,291,'limited',1,8,1,'C',0,'2018-12-05 01:49:10'),(132,'ra_comsol','lumerical',2,1,1,1,292,'limited',1,8,1,'C',0.00222222222222222,'2018-12-05 01:50:57'),(133,'mehri2','matlab',2,1,8,8,297,'batch',1,4,1,'C',0.174166666666667,'2018-12-05 09:34:30'),(134,'Irandoost2','lumerical',2,1,8,8,298,'batch',2,4,1,'C',0.141666666666667,'2018-12-05 09:36:47'),(135,'mehri3','siesta',2,1,8,8,299,'batch',2,4,1,'C',0,'2018-12-05 09:43:12'),(136,'matlab','matlab',42,1,8,8,300,'batch',1,4,1,'C',0.180833333333333,'2018-12-05 09:52:25'),(137,'siesta','siesta',42,1,8,8,301,'batch',3,4,1,'C',0,'2018-12-05 13:05:35'),(138,'lumerical','lumerical',42,1,16,16,302,'batch',3,4,1,'C',0.0575,'2018-12-05 13:09:26'),(139,'nima','matlab',2,1,8,11,303,'batch',1,4,1,'C',1.01333333333333,'2018-12-07 00:49:41'),(140,'we','Comsol',2,1,1,1,305,'batch',1,4,1,'C',0.00694444444444444,'2018-12-08 10:51:19'),(141,'tre','matlab',49,1,1,1,307,'batch',1,4,1,'C',0.005,'2018-12-08 11:02:51'),(142,'TEST','java',49,1,1,1,308,'batch',1,4,1,'C',0.00138888888888889,'2018-12-08 11:18:11'),(143,'Pakzad','Ansys',42,1,16,16,309,'batch',2,4,1,'C',1.67833333333333,'2018-12-09 14:20:16'),(144,'Fluent','Ansys',42,1,44,32,310,'batch',2,4,1,'C',4.08277777777778,'2018-12-09 14:30:19'),(145,'matlab_has','matlab',2,1,15,30,311,'batch',1,4,1,'C',1.20194444444444,'2018-12-10 11:37:41'),(146,'test_comso','Comsol',2,1,1,1,312,'batch',1,4,1,'C',0.230833333333333,'2018-12-14 16:05:52'),(147,'test_java','java',2,1,1,1,313,'batch',1,4,1,'C',0.00166666666666667,'2018-12-14 16:11:16'),(148,'test_matla','matlab',2,1,1,1,314,'batch',1,4,1,'C',0.178055555555556,'2018-12-14 16:55:18'),(149,'test_fluen','Ansys',2,1,1,1,315,'batch',1,4,1,'C',0.0341666666666667,'2018-12-14 17:04:08'),(150,'test_siest','siesta',2,1,1,1,316,'batch',1,4,1,'C',0,'2018-12-14 17:06:29'),(151,'wttt','Abaqus',2,1,1,1,319,'Gust_tariff',1,9,1,'C',0,'2018-12-14 17:34:38'),(152,'fdhj','siesta',2,1,1,1,320,'Gust_tariff',1,9,1,'C',0.0025,'2018-12-14 17:41:10'),(153,'test2siest','siesta',2,1,1,1,321,'batch',1,4,1,'C',0,'2018-12-14 18:15:59'),(154,'a1','Abaqus',2,1,1,1,325,'batch',1,4,1,'C',0,'2018-12-14 20:33:27'),(155,'testQu','matlab',2,1,1,1,326,'Gust_tariff',1,9,1,'C',1.20166666666667,'2018-12-14 20:57:29'),(156,'Fluent2','Ansys',42,1,44,16,327,'Gust_tariff',2,9,2,'C',2.47416666666667,'2018-12-15 14:43:15'),(157,'Fluent3','Ansys',42,1,44,32,328,'Gust_tariff',2,9,2,'C',0.144166666666667,'2018-12-15 15:10:22'),(158,'matlab_tst','matlab',42,1,8,8,329,'Gust_tariff',1,9,2,'C',0.00472222222222222,'2018-12-19 10:35:45'),(159,'mehri_sies','siesta',42,1,44,16,332,'Gust_tariff',1,9,2,'C',0.00222222222222222,'2018-12-25 09:40:15'),(160,'meh2_siest','siesta',42,1,44,16,333,'Gust_tariff',1,9,2,'C',12.6380555555556,'2018-12-25 09:51:19'),(161,'meri_matla','matlab',42,1,8,8,334,'Gust_tariff',1,9,2,'C',0.233333333333333,'2018-12-25 10:01:48'),(162,'test_mat','matlab',42,1,8,8,335,'Gust_tariff',1,9,2,'C',0.0669444444444444,'2018-12-25 10:20:34'),(163,'ma2_30','matlab',2,1,1,1,336,'batch',1,4,1,'C',0,'2018-12-28 23:49:13'),(164,'ma2_301','matlab',2,1,1,1,337,'batch',1,4,1,'C',1.201111111111111,'2018-12-28 23:51:52'),(165,'advanced_m','matlab',2,1,1,1,338,'batch',1,4,1,'C',0,'2018-12-29 01:55:13'),(166,'advanced_2','matlab',2,1,1,1,339,'batch',1,4,1,'C',1.2019444444444445,'2018-12-29 01:58:32'),(167,'mat_test6','matlab',2,1,1,1,340,'batch',1,4,1,'C',0.07194444444444445,'2018-12-29 18:40:47'),(168,'final_test','OpenFoam',2,1,1,1,354,'batch',1,4,1,'C',0,'2019-01-01 14:34:25'),(169,'final_2','OpenFoam',2,1,6,1,355,'batch',1,4,1,'C',0,'2019-01-01 14:37:04'),(170,'hi','OpenFoam',57,1,6,1,356,'batch',1,4,1,'C',0,'2019-01-01 14:38:05'),(171,'Hi_Finla','OpenFoam',2,1,6,1,370,'batch',1,4,1,'C',0,'2019-01-01 15:11:31'),(172,'Hi_Final2','OpenFoam',2,1,6,1,373,'batch',1,4,1,'C',0,'2019-01-01 15:17:15'),(173,'hihi','OpenFoam',2,1,1,1,375,'batch',1,4,1,'C',0,'2019-01-01 15:22:38'),(174,'hitest','OpenFoam',2,1,1,1,376,'batch',1,4,1,'C',0,'2019-01-01 15:24:06'),(175,'hitest2','OpenFoam',2,1,6,1,377,'batch',1,4,1,'C',0,'2019-01-01 15:25:41'),(176,'hitest3','OpenFoam',2,1,1,1,378,'batch',1,4,1,'C',0,'2019-01-01 15:27:09'),(177,'hi_matlab','matlab',2,1,1,1,379,'batch',1,4,1,'C',0,'2019-01-01 15:27:59'),(178,'hassan','matlab',2,1,1,1,380,'batch',1,4,1,'C',0,'2019-01-01 15:30:34'),(179,'ttts','matlab',2,1,1,1,381,'limited',1,8,1,'C',0,'2019-01-01 15:36:23'),(180,'ttts2','matlab',2,1,1,1,382,'limited',1,8,1,'C',0,'2019-01-01 15:42:21'),(181,'hassan2','matlab',2,1,1,1,383,'batch',1,4,1,'C',0,'2019-01-01 15:45:15'),(182,'ttts','matlab',12,1,1,1,385,'limited',1,8,1,'C',0.004722222222222222,'2019-01-01 16:08:09'),(183,'op_test','OpenFoam',57,1,6,12,386,'batch',1,4,1,'C',0,'2019-01-01 16:09:37'),(184,'sam','matlab',57,1,1,1,387,'limited',1,8,1,'C',0.006111111111111111,'2019-01-01 16:17:59'),(185,'testnn','OpenFoam',57,1,6,1,394,'batch',1,4,1,'C',0,'2019-01-01 16:45:02'),(186,'ttts2','OpenFoam',57,1,6,12,396,'batch',1,4,1,'C',3.2830555555555554,'2019-01-01 17:31:23'),(187,'mat_test22','matlab',57,1,4,1,397,'batch',1,4,1,'C',0,'2019-01-01 18:03:08'),(188,'mat_test23','matlab',57,1,1,1,398,'batch',1,4,1,'C',0.004722222222222222,'2019-01-01 18:04:50'),(189,'abaq1','Abaqus',57,1,1,1,399,'batch',1,4,1,'C',0,'2019-01-01 18:06:17'),(190,'abaq2','Abaqus',57,1,1,1,400,'batch',1,4,1,'C',0,'2019-01-01 18:06:50'),(191,'fluent','Ansys_Fluent',57,1,1,1,401,'batch',1,4,1,'C',0.0002777777777777778,'2019-01-01 18:08:03'),(192,'opf1','OpenFoam',57,1,1,1,402,'batch',1,4,1,'C',0.034999999999999996,'2019-01-01 18:09:05'),(193,'opf2','OpenFoam',57,1,1,1,403,'batch',1,4,1,'C',0.011666666666666667,'2019-01-01 18:09:24'),(194,'opf3','OpenFoam',57,1,6,1,404,'batch',1,4,1,'C',0.006944444444444444,'2019-01-01 18:09:56'),(195,'opf4','OpenFoam',57,1,6,1,405,'batch',1,4,1,'C',5.421666666666667,'2019-01-01 18:10:22'),(196,'sam_test','matlab',57,1,1,1,406,'limited',1,8,1,'C',0.004166666666666667,'2019-01-01 21:26:57'),(197,'ansys_t','Ansys_Fluent',57,1,1,1,407,'limited',1,8,1,'C',0.0025,'2019-01-01 21:28:49'),(198,'nima','Ansys_Fluent',57,1,1,1,408,'batch',1,4,1,'C',0.002777777777777778,'2019-01-02 00:39:57'),(199,'OF_Ebrahim','OpenFoam',42,1,44,32,409,'Gust_tariff',2,9,2,'C',0.11333333333333334,'2019-01-02 08:39:14'),(200,'Fluent4','Ansys_Fluent',42,1,44,16,410,'Gust_tariff',2,9,2,'C',6.0725,'2019-01-02 08:59:36'),(201,'tt','matlab',2,1,1,1,411,'batch',1,4,1,'C',0,'2019-01-09 04:25:02'),(202,'lum-soleym','lumerical',42,1,32,8,412,'Gust_tariff',5,9,2,'C',0.052222222222222225,'2019-01-15 14:09:58'),(203,'mat_salah','matlab',60,1,44,64,444,'batch',1,4,4,'C',0.14472222222222222,'2019-01-29 10:14:00'),(204,'m_s','matlab',60,1,44,64,445,'batch',3,4,4,'C',32.977222222222224,'2019-01-29 11:08:27'),(205,'fitnes','matlab',60,1,44,64,449,'batch',5,4,4,'C',40.02222222222222,'2019-01-29 13:10:08'),(206,'fit','matlab',60,1,44,64,465,'batch',6,4,4,'C',48.505833333333335,'2019-02-05 09:55:15'),(207,'vd','matlab',60,1,44,64,466,'batch',5,4,4,'C',0.12944444444444445,'2019-02-05 10:38:02'),(208,'nsa','matlab',60,1,44,64,467,'batch',6,4,4,'C',15.6325,'2019-02-05 11:14:54'),(209,'NSA_none','matlab',60,1,44,64,469,'batch',10,4,4,'C',1.2952777777777778,'2019-02-05 15:30:43'),(210,'NSA_non2','matlab',60,1,44,64,470,'batch',1,4,4,'C',0.005555555555555556,'2019-02-05 17:23:58'),(211,'NON_2','matlab',60,1,44,64,471,'batch',10,4,4,'C',1.9797222222222224,'2019-02-05 17:25:23'),(212,'tst_fitnes','matlab',60,1,44,64,474,'batch',20,4,4,'C',161.3838888888889,'2019-02-06 14:23:47'),(213,'mat_test','matlab',42,1,16,8,504,'Gust_tariff',1,9,2,'C',0.0044444444444444444,'2019-03-05 13:52:53'),(214,'mat_test_2','matlab',42,1,8,8,505,'batch',1,4,2,'C',0.0005555555555555556,'2019-03-05 13:57:04'),(215,'mat_test_3','matlab',42,1,8,8,506,'batch',1,4,2,'C',0.0044444444444444444,'2019-03-05 14:27:12'),(216,'sam','Abaqus',50,1,1,1,513,'batch',1,4,2,'C',0,'2019-03-16 06:02:02'),(217,'tt','matlab',50,1,1,1,514,'limited',1,8,2,'C',0.005,'2019-03-17 15:56:08'),(218,'Tensor_Ker','Python',80,1,16,16,540,'batch',2,4,12,'C',22.616388888888892,'2019-05-01 14:13:22'),(219,'ga','matlab',60,1,1,6,716,'batch',1,4,4,'C',0.004722222222222222,'2019-06-08 11:34:57'),(220,'ga2','matlab',60,1,44,64,717,'batch',300,4,4,'C',3152.6275,'2019-06-08 11:38:29'),(221,'shahab_Ab','Abaqus',86,1,16,16,736,'batch',72,4,14,'C',0,'2019-06-10 15:40:36'),(222,'shahab_Aba','Abaqus',86,1,1,16,737,'batch',72,4,14,'C',0.0005555555555555556,'2019-06-10 15:48:02'),(223,'shahab_Abc','Abaqus',86,1,4,16,738,'batch',72,4,14,'C',0.0002777777777777778,'2019-06-10 15:52:26'),(224,'shahab_tst','Abaqus',86,1,8,8,762,'batch',24,4,14,'C',0,'2019-06-15 16:12:46'),(225,'shahab_t','Abaqus',86,1,16,16,788,'batch',10,4,14,'C',0.8752777777777778,'2019-06-18 10:43:05'),(226,'test2R','matlab',57,1,1,1,9351,'limited',1,8,1,'C',0.004722222222222222,'2019-09-25 10:50:40'),(227,'test3R','matlab',57,1,1,1,9352,'batch',1,4,1,'C',0.005,'2019-09-25 10:51:49'),(228,'testarez','Comsol',137,1,1,1,9353,'batch',1,4,1,'C',0.022222222222222223,'2019-09-25 11:26:35'),(229,'testarez2','Abaqus',137,1,1,1,9354,'batch',1,4,1,'C',0.018055555555555554,'2019-09-25 11:27:17'),(230,'arezli','lammps',137,1,1,1,9355,'limited',1,8,1,'C',2.013888888888889,'2019-09-25 11:28:36'),(231,'arezgu','OpenFoam',137,1,1,1,9356,'Gust_tariff',1,9,1,'C',0.11805555555555555,'2019-09-25 11:29:14'),(232,'ttt','Abaqus',137,1,1,1,9357,'batch',1,4,1,'C',0.01888888888888889,'2019-09-25 12:51:19'),(233,'hhh','Abaqus',2,1,1,1,9358,'On_Demand_Tarif',1,10,1,'C',0,'2019-09-25 14:02:17');
/*!40000 ALTER TABLE `jobs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `mtopic` varchar(45) NOT NULL,
  `mtext` varchar(200) NOT NULL,
  `fromuid` int(11) NOT NULL,
  `touid` int(11) NOT NULL,
  `postdate` datetime NOT NULL,
  PRIMARY KEY (`mid`),
  KEY `FK_messages_fromuid` (`fromuid`),
  KEY `FK_messages_touid` (`touid`),
  CONSTRAINT `FK_messages_fromuid` FOREIGN KEY (`fromuid`) REFERENCES `user` (`uid`),
  CONSTRAINT `FK_messages_touid` FOREIGN KEY (`touid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `nid` int(11) NOT NULL AUTO_INCREMENT,
  `topic` varchar(45) NOT NULL,
  `uid` int(11) NOT NULL,
  `postdate` datetime NOT NULL,
  `expdate` datetime NOT NULL,
  `text` varchar(1000) NOT NULL,
  PRIMARY KEY (`nid`),
  KEY `FK_news_uid` (`uid`),
  CONSTRAINT `FK_news_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (3,'Help file',1,'2018-12-14 11:41:18','2018-12-28 00:00:00','<p><a href=\"https://10.14.20.100/user/documentation/Help_users.pdf\">view Help file </a></p>\r\n');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissions` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(20) NOT NULL,
  `pdescription` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (1,'userupdate','show users'),(2,'userprofile','add a user'),(3,'userlist','update a user'),(4,'usermemberslist','delete a user'),(5,'usermyprofile','show jobs'),(6,'usermyupdate','add a job'),(7,'jobnew','update a job'),(8,'jobupdate','delete a job'),(9,'jobdelete',' '),(10,'jobmylist',' '),(11,'jobmemberslist',' '),(12,'joblist',' '),(13,'jobmemberdelete',' '),(14,'jobdelete',' '),(15,'jobreport1',' '),(16,'jobreport2',' '),(17,'jobreport3',' '),(18,'hpcgroupnew',' '),(19,'hpcgroupupdate',' '),(20,'hpcgrouplist',' '),(21,'hpcgroupcharge',' '),(22,'tariffnew',' '),(23,'tariffupdate',' '),(24,'tarifflist',' '),(25,'newsnew',NULL),(26,'newslist',NULL),(27,'newsdelete',NULL),(28,'newsshow','show news to all users');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `queue`
--

DROP TABLE IF EXISTS `queue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `queue` (
  `qid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `qname` varchar(20) NOT NULL,
  `qdescription` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`qid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `queue`
--

LOCK TABLES `queue` WRITE;
/*!40000 ALTER TABLE `queue` DISABLE KEYS */;
INSERT INTO `queue` VALUES (1,'limited','my limited queue');
/*!40000 ALTER TABLE `queue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scripts`
--

DROP TABLE IF EXISTS `scripts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scripts` (
  `scid` int(11) NOT NULL AUTO_INCREMENT,
  `scname` varchar(45) NOT NULL,
  `script` varchar(400) NOT NULL,
  `scdescription` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`scid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scripts`
--

LOCK TABLES `scripts` WRITE;
/*!40000 ALTER TABLE `scripts` DISABLE KEYS */;
/*!40000 ALTER TABLE `scripts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `university`
--

DROP TABLE IF EXISTS `university`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `university` (
  `unid` int(11) NOT NULL AUTO_INCREMENT,
  `unname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`unid`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `university`
--

LOCK TABLES `university` WRITE;
/*!40000 ALTER TABLE `university` DISABLE KEYS */;
INSERT INTO `university` VALUES (1,'Babol Noshirvani University of Technology'),(2,'Urmia University of Technology'),(3,'Sharif University of Technology'),(4,'Amirkabir University of Technology'),(5,'University of Tehran'),(6,'Razi University'),(7,'K.N.Toosi University of Technology'),(8,'Payame Noor University'),(9,'Meybod University'),(10,'Zanjan University'),(11,'University of Tabriz'),(12,'Zabol University'),(13,'University of Semnan'),(14,'Qazvin Islamic Azad University'),(15,'University of Mazandaran'),(16,'Shomal University'),(17,'Mazandaran Uni. of  Science and Technology'),(18,'University of Arak'),(19,'Lorestan University'),(20,'University of Kurdistan'),(21,'University of Yasuj'),(22,'University of Birjand'),(23,'University of Neyshabur['),(24,'Ferdowsi University of Mashhad'),(25,'University of Bojnord'),(26,'University of Isfahan'),(27,'Isfahan University of Technology'),(28,'University of Ilam'),(29,'Bu-Ali Sina University'),(30,'Golestan University'),(31,'University of Gilan'),(32,'Shiraz University');
/*!40000 ALTER TABLE `university` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `pass` varchar(50) NOT NULL,
  `nationalCode` varchar(10) DEFAULT NULL,
  `email` varchar(70) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phone` varchar(14) DEFAULT NULL,
  `state` int(2) DEFAULT '3',
  `SN_PN` varchar(12) DEFAULT NULL,
  `unid` int(11) DEFAULT '1',
  `facultyid` int(11) DEFAULT '1',
  `field` varchar(50) DEFAULT NULL,
  `gradeid` int(11) DEFAULT '1',
  `nationalCard` varchar(200) DEFAULT NULL,
  `recommendation` varchar(200) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `expTime` datetime DEFAULT NULL,
  `isactive` int(1) unsigned NOT NULL DEFAULT '0',
  `st_pr_card` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `userName` (`userName`),
  UNIQUE KEY `email` (`email`),
  KEY `FK_user_facultyid` (`facultyid`),
  KEY `FK_user_unid` (`unid`),
  KEY `FK_user_gradeid` (`gradeid`),
  CONSTRAINT `FK_user_facultyid` FOREIGN KEY (`facultyid`) REFERENCES `faculty` (`facultyid`),
  CONSTRAINT `FK_user_gradeid` FOREIGN KEY (`gradeid`) REFERENCES `grade` (`gradeid`),
  CONSTRAINT `FK_user_unid` FOREIGN KEY (`unid`) REFERENCES `university` (`unid`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-29 15:31:08
