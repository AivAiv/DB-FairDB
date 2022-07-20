-- MariaDB dump 10.19  Distrib 10.4.20-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: fairdb
-- ------------------------------------------------------
-- Server version	10.4.20-MariaDB

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
-- Table structure for table `aziende`
--
CREATE DATABASE fairdb;
USE fairdb;
DROP TABLE IF EXISTS `aziende`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aziende` (
  `codiceAzienda` int(11) NOT NULL,
  `denominazione` varchar(45) NOT NULL,
  `specializzazione` varchar(45) NOT NULL,
  `codicePadiglione` int(11) DEFAULT NULL,
  PRIMARY KEY (`codiceAzienda`),
  KEY `codicePadiglione` (`codicePadiglione`),
  CONSTRAINT `codicePadiglione` FOREIGN KEY (`codicePadiglione`) REFERENCES `padiglioni` (`codicePadiglione`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aziende`
--

LOCK TABLES `aziende` WRITE;
/*!40000 ALTER TABLE `aziende` DISABLE KEYS */;
INSERT INTO `aziende` VALUES (1,'ABREX','Modellismo',10),(2,'CD STAR','CD-DVD',7),(3,'2 Emme Foto','Audio e Video',5),(4,'Milk Audio Store','Audio e Video',5),(5,'HP','Stampanti',1),(6,'3DiTALY','Stampanti',1),(7,'Arduino','Arduino',9),(8,'Candy','Elettromestici',4),(9,'Ignis','Elettromestici',4),(10,'OCEAN','Elettromestici',4),(11,'Smeg','Elettromestici',4),(12,'Samsung','Telefonia',6),(13,'Apple','Telefonia',6),(14,'Oppo','Telefonia',6),(15,'Philips','Computer',2),(16,'SiComputer','Computer',2),(17,'Asus','Computer',2),(18,'Acer','Computer',2),(19,'Lenovo','Computer',2),(20,'DSE','Videosorveglianza',8),(21,'AMELI SRL','Videosorveglianza',8),(22,'NSC ITALIA','Videosorveglianza',8),(23,'Robotecnica Srl.','Robot',3),(24,'Tekna Automazioni Srl.','Robot',3),(25,'ABL Automazione S.p.A.','Robot',3);
/*!40000 ALTER TABLE `aziende` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aziende-spazi_esposizione`
--

DROP TABLE IF EXISTS `aziende-spazi_esposizione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aziende-spazi_esposizione` (
  `letteraOccupata` varchar(2) NOT NULL,
  `numeroOccupato` int(12) NOT NULL,
  `aziendaOccupante` int(11) NOT NULL,
  PRIMARY KEY (`aziendaOccupante`,`numeroOccupato`,`letteraOccupata`),
  KEY `SpazioOccupato` (`letteraOccupata`,`numeroOccupato`),
  CONSTRAINT `AziendaOccupante` FOREIGN KEY (`aziendaOccupante`) REFERENCES `aziende` (`codiceAzienda`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `SpazioOccupato` FOREIGN KEY (`letteraOccupata`, `numeroOccupato`) REFERENCES `spazi_esposizione` (`lettera`, `numero`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aziende-spazi_esposizione`
--

LOCK TABLES `aziende-spazi_esposizione` WRITE;
/*!40000 ALTER TABLE `aziende-spazi_esposizione` DISABLE KEYS */;
INSERT INTO `aziende-spazi_esposizione` VALUES ('A',1,1),('A',2,2),('A',3,3),('A',4,4),('B',1,5),('B',2,6),('B',3,7),('B',4,8),('C',1,9),('C',2,10),('C',3,11),('C',4,12),('D',1,13),('D',2,14),('D',3,15),('D',4,16),('E',1,17),('E',2,18),('E',3,19),('E',4,20),('F',1,21),('A',1,22),('A',2,23),('A',3,24),('A',4,25);
/*!40000 ALTER TABLE `aziende-spazi_esposizione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `biglietti`
--

DROP TABLE IF EXISTS `biglietti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `biglietti` (
  `codiceBiglietto` varchar(20) NOT NULL,
  `prezzo` decimal(10,2) NOT NULL,
  `descrizione` varchar(45) NOT NULL,
  PRIMARY KEY (`codiceBiglietto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biglietti`
--

LOCK TABLES `biglietti` WRITE;
/*!40000 ALTER TABLE `biglietti` DISABLE KEYS */;
INSERT INTO `biglietti` VALUES ('bambini',0.00,'Entrata gratuita per bambini fino ai 5 anni'),('online ridotto',7.20,'Biglietto ridotto online'),('online singolo',13.50,'Biglietto singolo online'),('pass azienda',0.00,'Entrata per i dipendenti delle aziende'),('ridotto',8.00,'Entrata per singolo padiglione con prezzo rid'),('singolo',15.00,'Entrata base per singolo padiglione'),('tutti padiglioni',65.00,'Entrata che consente l’accesso a tutti i padi'),('tutti padiglioni rid',35.00,'Entrata ridotta per l’accesso a tutti i padig');
/*!40000 ALTER TABLE `biglietti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giorni`
--

DROP TABLE IF EXISTS `giorni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `giorni` (
  `data` date NOT NULL,
  `descizione` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`data`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giorni`
--

LOCK TABLES `giorni` WRITE;
/*!40000 ALTER TABLE `giorni` DISABLE KEYS */;
INSERT INTO `giorni` VALUES ('2022-05-06','Primo giorno'),('2022-05-07',NULL),('2022-05-08',NULL),('2022-05-09',NULL),('2022-05-10','Ultimo giorno');
/*!40000 ALTER TABLE `giorni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giorni-biglietti`
--

DROP TABLE IF EXISTS `giorni-biglietti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `giorni-biglietti` (
  `biglietto` varchar(45) NOT NULL,
  `giorno` date NOT NULL,
  PRIMARY KEY (`giorno`,`biglietto`),
  KEY `Biglietto` (`biglietto`),
  CONSTRAINT `Biglietto` FOREIGN KEY (`biglietto`) REFERENCES `biglietti` (`codiceBiglietto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Giorno` FOREIGN KEY (`giorno`) REFERENCES `giorni` (`data`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giorni-biglietti`
--

LOCK TABLES `giorni-biglietti` WRITE;
/*!40000 ALTER TABLE `giorni-biglietti` DISABLE KEYS */;
INSERT INTO `giorni-biglietti` VALUES ('online singolo','2022-05-06'),('ridotto','2022-05-06'),('singolo','2022-05-06'),('pass azienda','2022-05-07'),('singolo','2022-05-07'),('online singolo','2022-05-08'),('singolo','2022-05-08'),('singolo','2022-05-09'),('online singolo','2022-05-10');
/*!40000 ALTER TABLE `giorni-biglietti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordini`
--

DROP TABLE IF EXISTS `ordini`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordini` (
  `codiceOrdine` int(11) NOT NULL,
  `giorno` varchar(45) NOT NULL,
  `saldoTotale` decimal(10,2) NOT NULL,
  `codPromozione` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codiceOrdine`),
  KEY `codPromozione` (`codPromozione`),
  CONSTRAINT `codPromozione` FOREIGN KEY (`codPromozione`) REFERENCES `promozioni` (`codicePromozione`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordini`
--

LOCK TABLES `ordini` WRITE;
/*!40000 ALTER TABLE `ordini` DISABLE KEYS */;
INSERT INTO `ordini` VALUES (1,'2022-05-06',9.99,'famiglia'),(2,'2022-05-09',94.00,'gruppo 5-10'),(3,'2022-05-10',13.00,NULL),(4,'2022-05-07',0.00,NULL),(5,'2022-05-07',65.00,NULL),(6,'2022-05-08',15.00,NULL),(7,'2022-05-06',13.00,NULL),(8,'2022-05-07',15.00,NULL),(9,'2022-05-08',13.00,NULL),(10,'2022-05-09',15.00,NULL);
/*!40000 ALTER TABLE `ordini` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `padiglioni`
--

DROP TABLE IF EXISTS `padiglioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `padiglioni` (
  `codicePadiglione` int(11) NOT NULL,
  `specializzazioneRichiesta` varchar(45) NOT NULL,
  `orarioApertura` time NOT NULL,
  `orarioChiusura` time NOT NULL,
  `giornoAperturaAreaBambini` date DEFAULT NULL,
  `numSpaziEsposizioneTot` int(11) NOT NULL,
  `numSpaziEsposizioneOccupati` int(11) NOT NULL,
  PRIMARY KEY (`codicePadiglione`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `padiglioni`
--

LOCK TABLES `padiglioni` WRITE;
/*!40000 ALTER TABLE `padiglioni` DISABLE KEYS */;
INSERT INTO `padiglioni` VALUES (1,'Stampanti','15:00:00','19:00:00',NULL,60,2),(2,'Computer','12:00:00','19:00:00','2022-05-06',100,5),(3,'Robot','17:00:00','20:00:00',NULL,50,3),(4,'Elettromestici','09:00:00','20:00:00','2022-05-09',200,4),(5,'Audio e Video','16:00:00','17:00:00',NULL,100,2),(6,'Telefonia','09:00:00','20:00:00','2022-05-07',500,3),(7,'CD-DVD','14:00:00','16:00:00',NULL,250,1),(8,'Videosorveglianza','09:00:00','13:00:00','2022-05-08',300,3),(9,'Arduino','12:00:00','19:00:00',NULL,50,1),(10,'Modellismo','10:00:00','16:00:00','2022-05-10',80,1);
/*!40000 ALTER TABLE `padiglioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `padiglioni-visitatori`
--

DROP TABLE IF EXISTS `padiglioni-visitatori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `padiglioni-visitatori` (
  `codPadVisitato` int(11) NOT NULL,
  `codicePersona` varchar(45) NOT NULL,
  PRIMARY KEY (`codPadVisitato`,`codicePersona`),
  KEY `codiceFiscaleS` (`codicePersona`),
  CONSTRAINT `codiceFiscaleS` FOREIGN KEY (`codicePersona`) REFERENCES `visitatori` (`codiceFiscale`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `codicePadiglioneS` FOREIGN KEY (`codPadVisitato`) REFERENCES `padiglioni` (`codicePadiglione`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `padiglioni-visitatori`
--

LOCK TABLES `padiglioni-visitatori` WRITE;
/*!40000 ALTER TABLE `padiglioni-visitatori` DISABLE KEYS */;
INSERT INTO `padiglioni-visitatori` VALUES (1,'FRNYRA71L41B634G'),(1,'RYNSTV96A20A227E'),(2,'SVDPRZ74A53C137O'),(2,'TPSVRN72M25B498R'),(3,'CHNDTN65H02M295N'),(3,'GRMFNC64S07G878U'),(4,'NCCRCC74A44F203Z'),(4,'TRCTMS09R11C155Z'),(5,'CNSDRD55C13D771Y'),(5,'DLCGRL84H63G154R'),(6,'BGGMLS35E51L887G'),(6,'SPNMML44D42C556W'),(7,'LAANRN30H44H204T'),(7,'LBCGRL09A53A446C'),(8,'PSCNLD62D69M410Q'),(9,'NTTLLC98R28M212V'),(9,'SKLMNL54L14B936K'),(9,'SPRYSR66S12L349O'),(10,'BRBJSS77C51D600F'),(10,'STARLD48T62B832W');
/*!40000 ALTER TABLE `padiglioni-visitatori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personale`
--

DROP TABLE IF EXISTS `personale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personale` (
  `codiceFiscale` varchar(20) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `dataNascita` date NOT NULL,
  `sesso` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telefono` int(11) NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codiceFiscale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personale`
--

LOCK TABLES `personale` WRITE;
/*!40000 ALTER TABLE `personale` DISABLE KEYS */;
INSERT INTO `personale` VALUES ('BCCVCN66S27H790C','VINCENZO ARTURO','BECCE','1966-11-27','M','VINCEN@OUTLOOK.COM',60661680,'sicurezza'),('BLLFRT97D70H481W','FIQIRETE','BELLISI','1997-04-30','F','FIQIRE@OUTLOOK.IT',30025161,'biglietteria'),('BLLMRN00S15A581S','MAROUANE','BELLORA','2000-11-15','M','MAROUA@HOTMAIL.COM',44018126,'pulizia'),('BNFDMT63A27C065W','DHIMITRI','BANFI','1963-01-27','M','DHIMIT@LIVE.COM',26414720,'sicurezza'),('BRSGNN66P70L363B','GIOVANNA MARIA TERESA','BRESSAN DE NATALI','1966-09-30','F','GIOVAN@GMAIL.COM',47863232,'pulizia'),('CHVNGJ77S47M361B','ANGJE','CHIOVETTA','1977-11-07','F','ANGJE@OUTLOOK.COM',70374210,'sicurezza'),('CNNGNM83M62I629Y','GILIANA MARIA','CANNIOTO','1983-08-22','F','GILIAN@HOTMAIL.IT',26380653,'sicurezza'),('CNTTSL72S42D496U','TERESA ELSA','CONTARDI','1972-11-02','F','TERESA@HOTMAIL.IT',50114856,'biglietteria'),('GRGJRA94L05G152S','JARI','GIORGIS','1994-07-05','M','JARI@GMAIL.COM',81099376,'pulizia'),('MRTBNG04C03I124I','BRUNO GIOVANNI ANTONIO','MARTELLINI','2004-03-03','M','BRUNO@OUTLOOK.IT',19322391,'pulizia'),('NVRLNN51L68F631D','LUNA ANNA','NAVIRE','1951-07-28','F','LUNAA@GMAIL.COM',95833566,'biglietteria'),('PLTFNT71M50E911K','FANTA','PLATON','1971-08-10','F','FANTA@HOTMAIL.IT',91543131,'pulizia'),('RGGHNG96M14H088I','HAIQING','REGGE','1996-08-14','M','HAIQIN@OUTLOOK.IT',47413325,'biglietteria'),('SMSLDC87T67I132F','ELPIDIA CARLITA','SAMSEM','1987-12-27','F','ELPIDI@OUTLOOK.COM',78946522,'biglietteria'),('SNZSSH70H64M126W','SHASHINI SATHIERA','SONZINI','1970-06-24','F','SHASHI@OUTLOOK.IT',48282120,'sicurezza');
/*!40000 ALTER TABLE `personale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodotti`
--

DROP TABLE IF EXISTS `prodotti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prodotti` (
  `codiceProdotto` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `prezzo` decimal(4,2) NOT NULL,
  `descrizione` varchar(150) DEFAULT NULL,
  `codiceAzienda` int(11) DEFAULT NULL,
  PRIMARY KEY (`codiceProdotto`),
  KEY `codiceAzienda` (`codiceAzienda`),
  CONSTRAINT `codiceAzienda` FOREIGN KEY (`codiceAzienda`) REFERENCES `aziende` (`codiceAzienda`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotti`
--

LOCK TABLES `prodotti` WRITE;
/*!40000 ALTER TABLE `prodotti` DISABLE KEYS */;
INSERT INTO `prodotti` VALUES (1,'Supporto Aerografi Magnetico',20.00,'pezzi modello auto',1),(2,'Kit pneumatico lavorazione lamierati',15.00,'kit ',1),(3,'VINILE 12 pollici',40.00,'vinile',2),(4,'CD',20.00,'disco',2),(5,'Stampa su cd serigrafica',20.00,'servizio di stampa in tempo reale',2),(6,'DJI RONIN 4D 4-AXIS CINECAM 8K COMBO',11.00,'drone',3),(7,'GOXTREME BARRACUDA WP',92.00,'action cam',3),(8,'Buchla Easel Command',3.28,'audio',4),(9,'Stampante HP LaserJet M209dw',99.99,'stampante laser',5),(10,'Scanner Enterprise HP ScanJet Flow N7000 snw1',99.99,'scanner',5),(11,'Macchina da stampa a bobina HP PageWide T250 ',20.00,'macchina da stampa industriali',5),(12,'FORM 3BL BASIC PACKAGE WITH SERVICE',13.00,'stampante 3D',6),(13,'Laser cutter',10.00,'macchine a taglio laser',6),(14,'Stampa 3D',99.99,'stampa dei vostri modelli in tempo reale',6),(15,'Uno Rev 3 Arduino',25.00,'arduino',7),(16,'Leonardo with headers Arduino',20.00,'arduino',7),(17,'Lavatrice RCSS149HMC-S',99.99,'lavatrice a carica frontale',8),(18,'CANDY CRE H10A2DE-S asciugatrice',99.99,'asciugatrice',8),(19,'CANDY CSTG 28TE/1-11',99.99,'lavatrice a carica dall\'alto',8),(20,'NFW 830 H IX',99.99,'forno',9),(21,'REFRIGERATOR PFRC 400 S A+ WFS .',99.99,'frigorifero',10),(22,'NO FROST REFRIG. MDD 550 NF BG A+',99.99,'frigorifero',10),(23,'frigorifero SMEG FAB28',99.99,'frigorifero ',11),(24,'Frigo Snoopy',99.99,'frigorifero',11),(25,'Frigo Topolino',99.99,'frigorifero',11),(26,'Samsung Galaxy A12 (128 GB) Black',99.99,'smartphone',12),(27,'Samsung Galaxy A32 Black',99.99,'smartphone',12),(28,'Samsung Galaxy S22 5G Black 128 GB',99.99,'smartphone',12),(29,'Apple iPhone 13 Pro 128 GB Argento',99.99,'smartphone',13),(30,'Apple Watch Nike Series 7 GPS 41mm Alluminio',99.99,'apple watch',13),(31,'Apple 10.2-inch iPad 2021 Wi-Fi 64GB - SILVER',99.99,'tablet',13),(32,'OnePlus 10 Pro 5G',99.99,'smartphone',14),(33,'Monitor Led 49\" Philips\"',1.13,'monitor pc',15),(34,'SI COMPUTER ACTIVA PC USATO',50.00,'computer case',16),(35,'Asus 14\" Zenbook Duo UX482EGR\"',1.29,'portatile',17),(36,'ACER - CONCEPTD 300 CT300',2.14,'computer case',18),(37,'ThinkPad X1 Carbon Gen 9',2.80,'portatile',19),(38,'MINI SPEED DOME POE',99.99,'telecamera autotracking',20),(39,'Kit Videosorveglianza Ultra AHD 4 Telecamere',99.99,'telecamera ',21),(40,'Comelit',40.00,'telecamera autotracking',22),(41,'Sistemi di irrigazione con pannelli fotovolta',1.00,'estrazione acqua',23),(42,'Scheda tecnica Aerogeneratore 30kW',1.00,'uso turbine',24),(43,'Impianto di Produzione Contatori Gas',2.00,'montaggio contatori',25);
/*!40000 ALTER TABLE `prodotti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promozioni`
--

DROP TABLE IF EXISTS `promozioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promozioni` (
  `codicePromozione` varchar(20) NOT NULL,
  `sconto` int(11) NOT NULL,
  `descrizione` varchar(45) NOT NULL,
  PRIMARY KEY (`codicePromozione`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promozioni`
--

LOCK TABLES `promozioni` WRITE;
/*!40000 ALTER TABLE `promozioni` DISABLE KEYS */;
INSERT INTO `promozioni` VALUES ('famiglia',5,'2 adulti + 2 bambini paganti'),('gruppo 11-30',25,'Promozione per gruppi fino a 30 persone'),('gruppo 5-10',10,'Promozione per gruppi da 5 a 10 persone'),('gruppo più 30',40,'Promozione per gruppi con più di 30 persone');
/*!40000 ALTER TABLE `promozioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spazi_esposizione`
--

DROP TABLE IF EXISTS `spazi_esposizione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spazi_esposizione` (
  `lettera` varchar(1) NOT NULL,
  `numero` int(11) NOT NULL,
  PRIMARY KEY (`lettera`,`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spazi_esposizione`
--

LOCK TABLES `spazi_esposizione` WRITE;
/*!40000 ALTER TABLE `spazi_esposizione` DISABLE KEYS */;
INSERT INTO `spazi_esposizione` VALUES ('A',1),('A',2),('A',3),('A',4),('B',1),('B',2),('B',3),('B',4),('C',1),('C',2),('C',3),('C',4),('D',1),('D',2),('D',3),('D',4),('E',1),('E',2),('E',3),('E',4),('F',1),('F',2),('F',3),('F',4);
/*!40000 ALTER TABLE `spazi_esposizione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turni`
--

DROP TABLE IF EXISTS `turni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turni` (
  `idTurno` int(11) NOT NULL,
  `giorno` date NOT NULL,
  `orario` time NOT NULL,
  `codiceFiscale` varchar(45) DEFAULT NULL,
  `padiglione` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTurno`),
  KEY `codiceFiscale` (`codiceFiscale`),
  KEY `padiglione` (`padiglione`),
  CONSTRAINT `codiceFiscale` FOREIGN KEY (`codiceFiscale`) REFERENCES `personale` (`codiceFiscale`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `padiglione` FOREIGN KEY (`padiglione`) REFERENCES `padiglioni` (`codicePadiglione`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turni`
--

LOCK TABLES `turni` WRITE;
/*!40000 ALTER TABLE `turni` DISABLE KEYS */;
INSERT INTO `turni` VALUES (1,'2022-05-06','09:00:00','BLLFRT97D70H481W',NULL),(2,'2022-05-07','09:00:00','CNTTSL72S42D496U',NULL),(3,'2022-05-08','09:00:00','SMSLDC87T67I132F',NULL),(4,'2022-05-09','09:00:00','RGGHNG96M14H088I',NULL),(5,'2022-05-10','09:00:00','NVRLNN51L68F631D',NULL),(6,'2022-05-06','20:00:00','BCCVCN66S27H790C',5),(7,'2022-05-07','08:00:00','SNZSSH70H64M126W',6),(8,'2022-05-08','20:00:00','BNFDMT63A27C065W',3),(9,'2022-05-09','08:00:00','CHVNGJ77S47M361B',1),(10,'2022-05-10','20:00:00','CNNGNM83M62I629Y',7),(11,'2022-05-06','13:00:00','BLLMRN00S15A581S',8),(12,'2022-05-07','20:00:00','BRSGNN66P70L363B',4),(13,'2022-05-08','17:00:00','GRGJRA94L05G152S',5),(14,'2022-05-09','19:00:00','MRTBNG04C03I124I',9),(15,'2022-05-10','16:00:00','PLTFNT71M50E911K',10);
/*!40000 ALTER TABLE `turni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitatori`
--

DROP TABLE IF EXISTS `visitatori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visitatori` (
  `codiceFiscale` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `dataNascita` date NOT NULL,
  `sesso` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codiceFiscale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitatori`
--

LOCK TABLES `visitatori` WRITE;
/*!40000 ALTER TABLE `visitatori` DISABLE KEYS */;
INSERT INTO `visitatori` VALUES ('BGGMLS35E51L887G','MARIA LUISA PIA','BAGGIA','1935-05-11','F'),('BRBJSS77C51D600F','JULISSA JANNETH','BARBUTO','1977-03-11','F'),('CHNDTN65H02M295N','DALTON JONATAN','CHINELLI','1965-06-02','M'),('CNSDRD55C13D771Y','EDGARDO','CONSTANTIN','1955-03-13','M'),('DLCGRL84H63G154R','GABRIELA INES','DE LEUCE','1984-06-23','F'),('FRNYRA71L41B634G','YARA','FRANCESCHI','1971-07-01','F'),('GRMFNC64S07G878U','FRANCO GIORGIO','GARAM','1964-11-07','M'),('LAANRN30H44H204T','ANDREINA ELISA','ALA','1930-06-04','F'),('LBCGRL09A53A446C','GABRIELLA LUIGINA','ALBUCCA','2009-01-13','F'),('NCCRCC74A44F203Z','REBECCA FLORINA','NACCARATO','1974-01-04','F'),('NTTLLC98R28M212V','LEOLUCA','NETTINI','1998-10-28','M'),('PSCNLD62D69M410Q','ANGELA EDUARDA','PASCALINA','1962-04-29','F'),('RYNSTV96A20A227E','STEVE','REYNAUD','1996-01-20','M'),('SKLMNL54L14B936K','MARIAN LUCIAN','SKLIAR','1954-07-14','M'),('SPNMML44D42C556W','MAYA EMILIA','SPINILLO','1944-04-02','F'),('SPRYSR66S12L349O','YASIR','ASPROMONTE','1966-11-12','M'),('STARLD48T62B832W','ORLAIDA','ASTI','1948-12-22','F'),('SVDPRZ74A53C137O','PATRIZIA NATALINA','SVEDU','1974-01-13','F'),('TPSVRN72M25B498R','VERIANO','TEPSHI','1972-08-25','M'),('TRCTMS09R11C155Z','TOMMASO','TURCI','2009-10-11','M');
/*!40000 ALTER TABLE `visitatori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitatori-ordini-biglietti`
--

DROP TABLE IF EXISTS `visitatori-ordini-biglietti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visitatori-ordini-biglietti` (
  `codVisitatore` varchar(45) NOT NULL,
  `codOrdine` int(11) NOT NULL,
  `codBiglietto` varchar(45) NOT NULL,
  `Data` date NOT NULL,
  PRIMARY KEY (`codVisitatore`,`codOrdine`,`codBiglietto`,`Data`),
  KEY `codBiglietto` (`codBiglietto`),
  KEY `codOrdine` (`codOrdine`),
  KEY `Data` (`Data`),
  CONSTRAINT `Data` FOREIGN KEY (`Data`) REFERENCES `giorni-biglietti` (`giorno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `codBiglietto` FOREIGN KEY (`codBiglietto`) REFERENCES `biglietti` (`codiceBiglietto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `codOrdine` FOREIGN KEY (`codOrdine`) REFERENCES `ordini` (`codiceOrdine`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `codVisitatore` FOREIGN KEY (`codVisitatore`) REFERENCES `visitatori` (`codiceFiscale`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitatori-ordini-biglietti`
--

LOCK TABLES `visitatori-ordini-biglietti` WRITE;
/*!40000 ALTER TABLE `visitatori-ordini-biglietti` DISABLE KEYS */;
INSERT INTO `visitatori-ordini-biglietti` VALUES ('BGGMLS35E51L887G',8,'singolo','2022-05-07'),('BRBJSS77C51D600F',2,'singolo','2022-05-09'),('CHNDTN65H02M295N',10,'singolo','2022-05-09'),('CNSDRD55C13D771Y',9,'online singolo','2022-05-08'),('FRNYRA71L41B634G',1,'singolo','2022-05-06'),('FRNYRA71L41B634G',2,'singolo','2022-05-09'),('GRMFNC64S07G878U',2,'singolo','2022-05-09'),('LAANRN30H44H204T',2,'singolo','2022-05-09'),('LBCGRL09A53A446C',1,'ridotto','2022-05-06'),('NCCRCC74A44F203Z',2,'singolo','2022-05-09'),('NTTLLC98R28M212V',2,'singolo','2022-05-09'),('PSCNLD62D69M410Q',4,'pass azienda','2022-05-07'),('RYNSTV96A20A227E',3,'online singolo','2022-05-10'),('SKLMNL54L14B936K',2,'singolo','2022-05-09'),('SPNMML44D42C556W',4,'pass azienda','2022-05-07'),('SPRYSR66S12L349O',6,'singolo','2022-05-08'),('STARLD48T62B832W',7,'online singolo','2022-05-06'),('TPSVRN72M25B498R',1,'singolo','2022-05-06'),('TRCTMS09R11C155Z',1,'ridotto','2022-05-06');
/*!40000 ALTER TABLE `visitatori-ordini-biglietti` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-20 17:35:55
