-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 03, 2021 at 02:36 PM
-- Server version: 5.7.31
-- PHP Version: 7.3.21

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestion-comptabilite`
--

-- --------------------------------------------------------

--
-- Table structure for table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `section` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl1ytwg73lsi4to06d7l744a18` (`section`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `categorie_service`
--

DROP TABLE IF EXISTS `categorie_service`;
CREATE TABLE IF NOT EXISTS `categorie_service` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `lien` varchar(255) DEFAULT NULL,
  `montant` double DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categorie_service`
--

INSERT INTO `categorie_service` (`id`, `description`, `lien`, `montant`, `titre`) VALUES
(388, 'This is a wider card with supporting as a naturalas a natural creation ', 'creation', 300, 'Création'),
(390, 'This card has supporting text below as a natural liquidation .', 'liquidation', 100, 'Liquidation'),
(391, 'This is a wider card with supporting text below as a natural Declaration TVA', 'declaration-tva', 350, 'Declaration TVA'),
(392, 'This is a wider card with supporting text below as a naturalent Declaration IR', 'declaration-ir', 500, 'Declaration IR'),
(393, 'This is a wider card with supporting text below as a Declaration IS', 'declaration-is', 550, 'Declaration IS'),
(394, 'This is a wider card with supporting text below as Facture', 'facture', 600, 'Facture');

-- --------------------------------------------------------

--
-- Table structure for table `classe_comptable`
--

DROP TABLE IF EXISTS `classe_comptable`;
CREATE TABLE IF NOT EXISTS `classe_comptable` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `numero` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `classe_comptable`
--

INSERT INTO `classe_comptable` (`id`, `libelle`, `numero`) VALUES
(1196, 'dh', 5);

-- --------------------------------------------------------

--
-- Table structure for table `class_comptable`
--

DROP TABLE IF EXISTS `class_comptable`;
CREATE TABLE IF NOT EXISTS `class_comptable` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `comptable`
--

DROP TABLE IF EXISTS `comptable`;
CREATE TABLE IF NOT EXISTS `comptable` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `compte_comptable`
--

DROP TABLE IF EXISTS `compte_comptable`;
CREATE TABLE IF NOT EXISTS `compte_comptable` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `sous_classe_comptable` bigint(20) DEFAULT NULL,
  `sous_classe_comptable_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcb4he1hnsyc9fwrt4s1kw4mqp` (`sous_classe_comptable_id`),
  KEY `FKajf6wdmvrh66jg39vg1aryt4m` (`sous_classe_comptable`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `connection`
--

DROP TABLE IF EXISTS `connection`;
CREATE TABLE IF NOT EXISTS `connection` (
  `id` bigint(20) NOT NULL,
  `etat` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `societe_login` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlfhr11nhro69n4e5bld37676v` (`societe_login`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connection`
--

INSERT INTO `connection` (`id`, `etat`, `password`, `type`, `username`, `societe_login`) VALUES
(39, 'valider', 'admin', 'admin', 'admin', NULL),
(51, 'valider', '123', 'Societe', 'hamid', 41),
(52, 'valider', '123', 'Societe', 'yassin', 3),
(53, 'valider', '123', 'Societe', 'said', 77),
(57, 'valider', '123', 'Societe', 'oussama', 74),
(59, 'valider', 'AA', 'Societe', 'AA', 60),
(61, 'valider', '123', 'Societe', 'amine', 71),
(63, NULL, NULL, NULL, NULL, 62),
(64, 'valider', '123', 'Societe', 'ISSA', NULL),
(66, NULL, NULL, NULL, NULL, 65),
(67, 'valider', '123', 'Societe', 'hossam', 70),
(69, NULL, NULL, NULL, NULL, 68),
(73, NULL, NULL, NULL, NULL, 72),
(76, NULL, NULL, NULL, NULL, 75),
(78, 'valider', '123', 'Societe', 'ZOZO', 81),
(80, NULL, NULL, NULL, NULL, 79),
(83, 'valider', '123', 'Societe', 'kokab', 84),
(85, 'valider', 'zer', 'Societe', 'dfg', 86),
(87, 'valider', '123', 'Societe', 'HIHI', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `declarationir`
--

DROP TABLE IF EXISTS `declarationir`;
CREATE TABLE IF NOT EXISTS `declarationir` (
  `id` bigint(20) NOT NULL,
  `annee` int(11) DEFAULT NULL,
  `mois` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `societe` bigint(20) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKexcs28w0o81k0brx25ryqnew5` (`societe`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `declarationir`
--

INSERT INTO `declarationir` (`id`, `annee`, `mois`, `total`, `societe`, `ref`) VALUES
(1507, 2020, 12, 1032.4, 7, 'd12'),
(1501, 2020, 11, 1432, 7, 'd11'),
(1494, 2020, 10, 2264.6, 7, 'd10'),
(1486, 2020, 9, 2364.5, 7, 'd9'),
(1480, 2020, 8, 2164.7, 7, 'd8'),
(1473, 2020, 7, 1531.9, 7, 'd7'),
(1465, 2020, 6, 2364.5, 7, 'd6'),
(1458, 2020, 5, 1865, 7, 'd5'),
(1452, 2020, 4, 1432, 7, 'd4'),
(1446, 2020, 3, 1032.4, 7, 'd3'),
(1442, 2020, 2, 1332.1, 7, 'd2'),
(1434, 2020, 1, 2364.5, 7, 'd1');

-- --------------------------------------------------------

--
-- Table structure for table `declarationiremploye`
--

DROP TABLE IF EXISTS `declarationiremploye`;
CREATE TABLE IF NOT EXISTS `declarationiremploye` (
  `id` bigint(20) NOT NULL,
  `montantir` double DEFAULT NULL,
  `salaire_brut` double DEFAULT NULL,
  `salaire_net` double DEFAULT NULL,
  `declarationir` bigint(20) DEFAULT NULL,
  `employe` bigint(20) DEFAULT NULL,
  `taux_ir` bigint(20) DEFAULT NULL,
  `ref_emp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9fi3ikjrqlp51k5b4frxl1lb5` (`declarationir`),
  KEY `FKp4c78i82c7ufslwte6bgy8nwt` (`employe`),
  KEY `FKmgl7m92v5oyrt2fo41jmeu9yg` (`taux_ir`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `declarationiremploye`
--

INSERT INTO `declarationiremploye` (`id`, `montantir`, `salaire_brut`, `salaire_net`, `declarationir`, `employe`, `taux_ir`, `ref_emp`) VALUES
(1476, 0, 200, 200, 1473, 59, NULL, NULL),
(1475, 99.9, 3500, 3400.1, 1473, 60, NULL, NULL),
(1474, 99.9, 3500, 3400.1, 1473, 56, NULL, NULL),
(1472, 832.6, 15000, 14167.4, 1465, 337, NULL, NULL),
(1471, 499.5, 5000, 4500.5, 1465, 71, NULL, NULL),
(1470, 0, 3, 3, 1465, 70, NULL, NULL),
(1469, 832.6, 7000, 6167.4, 1465, 69, NULL, NULL),
(1468, 0, 200, 200, 1465, 59, NULL, NULL),
(1467, 99.9, 3500, 3400.1, 1465, 60, NULL, NULL),
(1466, 99.9, 3500, 3400.1, 1465, 56, NULL, NULL),
(1464, 832.6, 15000, 14167.4, 1458, 337, NULL, NULL),
(1463, 0, 3, 3, 1458, 70, NULL, NULL),
(1462, 832.6, 7000, 6167.4, 1458, 69, NULL, NULL),
(1461, 0, 200, 200, 1458, 59, NULL, NULL),
(1460, 99.9, 3500, 3400.1, 1458, 60, NULL, NULL),
(1459, 99.9, 3500, 3400.1, 1458, 56, NULL, NULL),
(1457, 832.6, 15000, 14167.4, 1452, 337, NULL, NULL),
(1456, 499.5, 5000, 4500.5, 1452, 71, NULL, NULL),
(1455, 0, 3, 3, 1452, 70, NULL, NULL),
(1454, 0, 200, 200, 1452, 59, NULL, NULL),
(1453, 99.9, 3500, 3400.1, 1452, 60, NULL, NULL),
(1451, 832.6, 15000, 14167.4, 1446, 337, NULL, NULL),
(1450, 0, 3, 3, 1446, 70, NULL, NULL),
(1449, 0, 200, 200, 1446, 59, NULL, NULL),
(1448, 99.9, 3500, 3400.1, 1446, 60, NULL, NULL),
(1447, 99.9, 3500, 3400.1, 1446, 56, NULL, NULL),
(1445, 832.6, 15000, 14167.4, 1442, 337, NULL, NULL),
(1444, 499.5, 5000, 4500.5, 1442, 71, NULL, NULL),
(1443, 0, 3, 3, 1442, 70, NULL, NULL),
(1441, 832.6, 15000, 14167.4, 1434, 337, NULL, NULL),
(1440, 499.5, 5000, 4500.5, 1434, 71, NULL, NULL),
(1439, 0, 3, 3, 1434, 70, NULL, NULL),
(1438, 832.6, 7000, 6167.4, 1434, 69, NULL, NULL),
(1437, 0, 200, 200, 1434, 59, NULL, NULL),
(1436, 99.9, 3500, 3400.1, 1434, 60, NULL, NULL),
(1435, 99.9, 3500, 3400.1, 1434, 56, NULL, NULL),
(1477, 832.6, 7000, 6167.4, 1473, 69, NULL, NULL),
(1478, 0, 3, 3, 1473, 70, NULL, NULL),
(1479, 499.5, 5000, 4500.5, 1473, 71, NULL, NULL),
(1481, 0, 200, 200, 1480, 59, NULL, NULL),
(1482, 832.6, 7000, 6167.4, 1480, 69, NULL, NULL),
(1483, 0, 3, 3, 1480, 70, NULL, NULL),
(1484, 499.5, 5000, 4500.5, 1480, 71, NULL, NULL),
(1485, 832.6, 15000, 14167.4, 1480, 337, NULL, NULL),
(1487, 99.9, 3500, 3400.1, 1486, 56, NULL, NULL),
(1488, 99.9, 3500, 3400.1, 1486, 60, NULL, NULL),
(1489, 0, 200, 200, 1486, 59, NULL, NULL),
(1490, 832.6, 7000, 6167.4, 1486, 69, NULL, NULL),
(1491, 0, 3, 3, 1486, 70, NULL, NULL),
(1492, 499.5, 5000, 4500.5, 1486, 71, NULL, NULL),
(1493, 832.6, 15000, 14167.4, 1486, 337, NULL, NULL),
(1495, 99.9, 3500, 3400.1, 1494, 60, NULL, NULL),
(1496, 0, 200, 200, 1494, 59, NULL, NULL),
(1497, 832.6, 7000, 6167.4, 1494, 69, NULL, NULL),
(1498, 0, 3, 3, 1494, 70, NULL, NULL),
(1499, 499.5, 5000, 4500.5, 1494, 71, NULL, NULL),
(1500, 832.6, 15000, 14167.4, 1494, 337, NULL, NULL),
(1502, 99.9, 3500, 3400.1, 1501, 60, NULL, NULL),
(1503, 0, 200, 200, 1501, 59, NULL, NULL),
(1504, 832.6, 7000, 6167.4, 1501, 69, NULL, NULL),
(1505, 0, 3, 3, 1501, 70, NULL, NULL),
(1506, 499.5, 5000, 4500.5, 1501, 71, NULL, NULL),
(1508, 99.9, 3500, 3400.1, 1507, 56, NULL, NULL),
(1509, 99.9, 3500, 3400.1, 1507, 60, NULL, NULL),
(1510, 832.6, 15000, 14167.4, 1507, 337, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `declarationis`
--

DROP TABLE IF EXISTS `declarationis`;
CREATE TABLE IF NOT EXISTS `declarationis` (
  `id` bigint(20) NOT NULL,
  `annee` int(11) DEFAULT NULL,
  `montantiscalcule` double DEFAULT NULL,
  `montantispaye` double DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `totalhtcharge` double DEFAULT NULL,
  `totalhtdiff` double DEFAULT NULL,
  `totalhtgain` double DEFAULT NULL,
  `societe` bigint(20) DEFAULT NULL,
  `tauxis` bigint(20) DEFAULT NULL,
  `taux_is_config` bigint(20) DEFAULT NULL,
  `etat_declaration` bigint(20) DEFAULT NULL,
  `etat_declaration_id` bigint(20) DEFAULT NULL,
  `societe_id` bigint(20) DEFAULT NULL,
  `tauxis_id` bigint(20) DEFAULT NULL,
  `taux_is_config_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5k2w5tkcsbhrcptk9g9ywymgn` (`etat_declaration_id`),
  KEY `FK5tr0mcqn71eefcxa272tfmmb5` (`societe_id`),
  KEY `FK11d23sy3g4trnr24kjnq7p57t` (`tauxis_id`),
  KEY `FKobuar2360op5rhbifp4m7gpuh` (`taux_is_config_id`),
  KEY `FKhrgj9wu6hrtdtm2pe9ilw9b7j` (`etat_declaration`),
  KEY `FKs9d8lt5i9bcq2x98muj6cjw65` (`societe`),
  KEY `FK8ebtmtwn5b3q4qcvnafsyrbcl` (`tauxis`),
  KEY `FK9faw3kaqntoarvd1rtrgjuogt` (`taux_is_config`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `declaration_tva`
--

DROP TABLE IF EXISTS `declaration_tva`;
CREATE TABLE IF NOT EXISTS `declaration_tva` (
  `id` bigint(20) NOT NULL,
  `annee` double NOT NULL,
  `difftva` double NOT NULL,
  `mois` double NOT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `trim` double NOT NULL,
  `tvacollecter` double NOT NULL,
  `tvaperdue` double NOT NULL,
  `societe` bigint(20) DEFAULT NULL,
  `type_declaration_tva` bigint(20) DEFAULT NULL,
  `etat_declaration` bigint(20) DEFAULT NULL,
  `etat_declaration_id` bigint(20) DEFAULT NULL,
  `societe_id` bigint(20) DEFAULT NULL,
  `type_declaration_tva_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcnogxxcxjs0ho8l62pprnd4go` (`etat_declaration_id`),
  KEY `FKq1q6ku3fis9jnj8gapkcg426m` (`societe_id`),
  KEY `FKs0dhviiys4mxd0jo782khbwrh` (`type_declaration_tva_id`),
  KEY `FK9jik3v4e0359rq2j1r9ek2te3` (`etat_declaration`),
  KEY `FKgwbagwpqe8jdr7jtjbks2ybcm` (`societe`),
  KEY `FKhib9p3529ikvttics1k7pdmpe` (`type_declaration_tva`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `demande`
--

DROP TABLE IF EXISTS `demande`;
CREATE TABLE IF NOT EXISTS `demande` (
  `id` bigint(20) NOT NULL,
  `annee` double NOT NULL,
  `mois` int(11) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `valider` bit(1) NOT NULL,
  `societe` bigint(20) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL,
  `date_demande` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn083x4c13po4b1yrut10j0p57` (`societe`),
  KEY `FKbu336b8cte43wk3e44wtmkd2d` (`user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `demande`
--

INSERT INTO `demande` (`id`, `annee`, `mois`, `operation`, `ref`, `valider`, `societe`, `user`, `date_demande`) VALUES
(1422, 2018, 4, 'Declaration TVA', 'dem5', b'0', 8, NULL, NULL),
(1423, 2013, 2, 'declaration IR', 'dm6', b'0', 7, NULL, NULL),
(1424, 123, 2, 'decvlaration tva', 'fd', b'0', 7, NULL, NULL),
(1425, 145, 7, 'declaration IS', 'ds', b'0', 7, NULL, NULL),
(1429, 2013, 2, 'Declaration IS', 'test f', b'0', 7, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `details`
--

DROP TABLE IF EXISTS `details`;
CREATE TABLE IF NOT EXISTS `details` (
  `id` bigint(20) NOT NULL,
  `montant_tranche_revenu` double DEFAULT NULL,
  `pourcentage` double DEFAULT NULL,
  `valeur` double DEFAULT NULL,
  `declarationiremploye` bigint(20) DEFAULT NULL,
  `taux_ir` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKludujo525y38yicgvacbrisni` (`declarationiremploye`),
  KEY `FKo248o3y4fdvla3nqktjdj25qg` (`taux_ir`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `employe`
--

DROP TABLE IF EXISTS `employe`;
CREATE TABLE IF NOT EXISTS `employe` (
  `id` bigint(20) NOT NULL,
  `cin` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `salaire` double DEFAULT NULL,
  `societe_emp` bigint(20) DEFAULT NULL,
  `societe_emp_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr0bc7v1jcqi9pux1w0frta167` (`societe_emp_id`),
  KEY `FKpn7yd0yepktcu4mhb72rip3t6` (`societe_emp`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employe`
--

INSERT INTO `employe` (`id`, `cin`, `nom`, `prenom`, `salaire`, `societe_emp`, `societe_emp_id`) VALUES
(56, 'EE7895', 'BENKHAZRA', 'Mohammed', 3500, 7, NULL),
(60, 'EF489785', 'lkhder', 'Asmae', 3500, 7, NULL),
(59, 'EF48985', 'lkhder', 'oussama', 200, 7, NULL),
(61, 'FF489785', 'dabachi', 'ayoub', 10000, 9, NULL),
(62, 'FF09785', 'aitdari', 'salma', 8000, 9, NULL),
(63, 'FF99785', 'msika', 'Achraf', 8000, 9, NULL),
(64, 'FF97785', 'Reda', 'Razi', 20000, 9, NULL),
(65, 'FF37785', 'dali', 'keltoum', 4000, 9, NULL),
(66, 'FF30785', 'ben', 'Badr', 65000, 9, NULL),
(67, 'FF00785', 'Shita', 'Youness', 65000, 9, NULL),
(68, 'FF00985', 'bertaouch', 'ayoub', 67000, 9, NULL),
(69, 'F7800985', 'rami', 'abdellah', 7000, 7, NULL),
(70, 'F7800785', 'ramzi', 'abdeljalil', 3, 7, NULL),
(71, 'F7819785', 'snik', 'aziz', 5000, 7, NULL),
(72, 'F78785', 'dukali', 'aziz', 9000, 10, NULL),
(73, 'RF78785', 'dukali', 'marwa', 9000, 10, NULL),
(74, 'RF878785', 'azir', 'marwa', 9000, 10, NULL),
(75, 'RF7870085', 'ldari', 'marwa', 10000, 11, NULL),
(76, 'RF781085', 'adri', 'ibtissam', 10000, 11, NULL),
(337, 'AA9999', 'titoos', 'rachid', 15000, 7, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `etape`
--

DROP TABLE IF EXISTS `etape`;
CREATE TABLE IF NOT EXISTS `etape` (
  `id` bigint(20) NOT NULL,
  `montant_comptable` double NOT NULL,
  `montant_fix` double NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `délai` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `type_operation` bigint(20) DEFAULT NULL,
  `delai` varchar(255) DEFAULT NULL,
  `montantcomptable` double NOT NULL,
  `montantfix` double NOT NULL,
  `type_operation_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKktapk0nrur4xhscwriwjoyntx` (`type_operation_id`),
  KEY `FKcuegv6uhoe1tkuopignyeddjy` (`type_operation`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `etape`
--

INSERT INTO `etape` (`id`, `montant_comptable`, `montant_fix`, `description`, `délai`, `libelle`, `type_operation`, `delai`, `montantcomptable`, `montantfix`, `type_operation_id`) VALUES
(2, 0, 0, 'EE', NULL, 'ouvrage', 1, '3 jours', 100, 1000, NULL),
(19, 0, 0, 'string', NULL, 'E1', 0, 'JFJ', 10, 10, NULL),
(22, 0, 0, 'string', NULL, 'E22', 21, 'JFJ', 10, 10, NULL),
(26, 0, 0, 'string', NULL, 'H', 25, 'NN', 10, 10, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `etat_declaration`
--

DROP TABLE IF EXISTS `etat_declaration`;
CREATE TABLE IF NOT EXISTS `etat_declaration` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `etat_facture`
--

DROP TABLE IF EXISTS `etat_facture`;
CREATE TABLE IF NOT EXISTS `etat_facture` (
  `id` bigint(20) NOT NULL,
  `code` double NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `etat_operation_societe`
--

DROP TABLE IF EXISTS `etat_operation_societe`;
CREATE TABLE IF NOT EXISTS `etat_operation_societe` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `etat_paiement`
--

DROP TABLE IF EXISTS `etat_paiement`;
CREATE TABLE IF NOT EXISTS `etat_paiement` (
  `id` bigint(20) NOT NULL,
  `code` double NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `etat_societe`
--

DROP TABLE IF EXISTS `etat_societe`;
CREATE TABLE IF NOT EXISTS `etat_societe` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `facture`
--

DROP TABLE IF EXISTS `facture`;
CREATE TABLE IF NOT EXISTS `facture` (
  `id` bigint(20) NOT NULL,
  `annee` double NOT NULL,
  `date_operation` datetime DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `mois` double NOT NULL,
  `montant_hors_taxe` double NOT NULL,
  `montantttc` double NOT NULL,
  `montanttva` double NOT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `trim` double NOT NULL,
  `type_operation` varchar(255) DEFAULT NULL,
  `class_comptable` bigint(20) DEFAULT NULL,
  `declarationir` bigint(20) DEFAULT NULL,
  `declarationis` bigint(20) DEFAULT NULL,
  `declaration_tva` bigint(20) DEFAULT NULL,
  `societe_distination` bigint(20) DEFAULT NULL,
  `societe_source` bigint(20) DEFAULT NULL,
  `tva` bigint(20) DEFAULT NULL,
  `credit` varchar(255) DEFAULT NULL,
  `debit` varchar(255) DEFAULT NULL,
  `etat_facture` bigint(20) DEFAULT NULL,
  `etat_paiement` bigint(20) DEFAULT NULL,
  `class_comptable_id` bigint(20) DEFAULT NULL,
  `declarationir_id` bigint(20) DEFAULT NULL,
  `declarationis_id` bigint(20) DEFAULT NULL,
  `declaration_tva_id` bigint(20) DEFAULT NULL,
  `etat_facture_id` bigint(20) DEFAULT NULL,
  `etat_paiement_id` bigint(20) DEFAULT NULL,
  `societe_distination_id` bigint(20) DEFAULT NULL,
  `societe_source_id` bigint(20) DEFAULT NULL,
  `tva_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK61u2x8c4aq3489ip30n4xj0bc` (`class_comptable_id`),
  KEY `FK57b4913q26q15nv65pfgymjhr` (`declarationir_id`),
  KEY `FKesksp3wkd1auq6gsr2t9ej8uo` (`declarationis_id`),
  KEY `FK2fb6xdauc6jphb6fla9hyw68k` (`declaration_tva_id`),
  KEY `FKlg8816qn45ie9yhqf8jbhsxg9` (`etat_facture_id`),
  KEY `FKnwaha2neflct059ha7ygntbvn` (`etat_paiement_id`),
  KEY `FK4awmg45u29oeqcj6806l180rk` (`societe_distination_id`),
  KEY `FKks1tooe3yrlq8atduykyg613l` (`societe_source_id`),
  KEY `FK5abnnwmnprq9r41qjdnhxe9k1` (`tva_id`),
  KEY `FK91gg897e94th5ddolarykb5wg` (`class_comptable`),
  KEY `FK1vhjtkljul7u7sgqnf832qaru` (`declarationir`),
  KEY `FKsjjtqw76htgw0f6xwlwc5bxt6` (`declarationis`),
  KEY `FKsburti4kj44462x058oi43eae` (`declaration_tva`),
  KEY `FKkr3ksts74gennv8v3sftugtj8` (`etat_facture`),
  KEY `FKjve9pyoc84dr4klbv34lvts8f` (`etat_paiement`),
  KEY `FKjyarvuk8jge8frk5fuqsxaxh6` (`societe_distination`),
  KEY `FKcw44mq41ieyb1ohrp1a9wtp3g` (`societe_source`),
  KEY `FKnfg1mkl9rtx7sewcxlpvm91x8` (`tva`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1511),
(89);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `societe_login` bigint(20) DEFAULT NULL,
  `societe_login_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbinmojda7spcsmaxcnd3l3sw4` (`societe_login_id`),
  KEY `FKje6b5e204hbs03l7mv14ihn80` (`societe_login`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `password`, `type`, `username`, `societe_login`, `societe_login_id`) VALUES
(1198, 'google', 'societe', 'gog', 7, NULL),
(1199, 'microsoft', 'societe', 'mic', NULL, NULL),
(1200, 'facebook', 'societe', 'face', NULL, NULL),
(1201, 'admin', 'Admin', 'admin', NULL, NULL),
(1203, 'comptable', 'comptable', 'comptable', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `operation_societe`
--

DROP TABLE IF EXISTS `operation_societe`;
CREATE TABLE IF NOT EXISTS `operation_societe` (
  `id` bigint(20) NOT NULL,
  `date_operation_societe` datetime DEFAULT NULL,
  `fraix_comptable` double NOT NULL,
  `fraix_fix` double NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `comptable_taiteur` bigint(20) DEFAULT NULL,
  `comptable_validateur` bigint(20) DEFAULT NULL,
  `etat_operation_societe` bigint(20) DEFAULT NULL,
  `societe` bigint(20) DEFAULT NULL,
  `type_operation` bigint(20) DEFAULT NULL,
  `comptable_taiteur_id` bigint(20) DEFAULT NULL,
  `comptable_validateur_id` bigint(20) DEFAULT NULL,
  `etat_operation_societe_id` bigint(20) DEFAULT NULL,
  `societe_id` bigint(20) DEFAULT NULL,
  `type_operation_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK47wufjifjc2rmddepcsxldddk` (`comptable_taiteur_id`),
  KEY `FK6ebk4jr7gv6oowhuv645kelva` (`comptable_validateur_id`),
  KEY `FKc0ai1erqr2l39q5yast7cahwf` (`etat_operation_societe_id`),
  KEY `FKn6j77lhqo7rn2y2229ybxqwpj` (`societe_id`),
  KEY `FK5m87bhweyv7fxbrmnykfdvq6g` (`type_operation_id`),
  KEY `FK8wgtlqlykqum3aosolcsmm9fr` (`comptable_taiteur`),
  KEY `FKp374pje2pxaftbfbr5vhwjkrv` (`comptable_validateur`),
  KEY `FK236u8q4brd0m5hfs2runxnllh` (`etat_operation_societe`),
  KEY `FKh8or785pap4d7rvigp87o03pg` (`societe`),
  KEY `FK1oawajdyyqiknhmeqqu719i0y` (`type_operation`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `operation_societe`
--

INSERT INTO `operation_societe` (`id`, `date_operation_societe`, `fraix_comptable`, `fraix_fix`, `libelle`, `ref`, `comptable_taiteur`, `comptable_validateur`, `etat_operation_societe`, `societe`, `type_operation`, `comptable_taiteur_id`, `comptable_validateur_id`, `etat_operation_societe_id`, `societe_id`, `type_operation_id`) VALUES
(4, '2020-10-10 00:00:00', 10, 10, 'string', 'O1', NULL, NULL, NULL, 3, 1, NULL, NULL, NULL, NULL, NULL),
(5, '2021-05-22 00:00:00', 10, 10, 'HHH', 'O2', NULL, NULL, NULL, 3, 1, NULL, NULL, NULL, NULL, NULL),
(6, '2021-05-15 00:00:00', 10, 10, 'ff', 'fffff', NULL, NULL, NULL, 3, 1, NULL, NULL, NULL, NULL, NULL),
(13, '2021-05-23 00:00:00', 10, 10, 'ddd', 'dd', NULL, NULL, NULL, 3, 1, NULL, NULL, NULL, NULL, NULL),
(14, '2021-05-16 00:00:00', 10, 10, 'fgfg', 'gg', NULL, NULL, NULL, 3, 1, NULL, NULL, NULL, NULL, NULL),
(15, '2021-05-14 00:00:00', 10, 10, 'DD', 'KOKA', NULL, NULL, NULL, 3, 1, NULL, NULL, NULL, NULL, NULL),
(16, '2021-05-09 00:00:00', 10, 10, 'XX', 'XX', NULL, NULL, NULL, 3, 1, NULL, NULL, NULL, NULL, NULL),
(17, '2021-05-15 00:00:00', 10, 10, 'qsd', 'sd', NULL, NULL, NULL, 3, 1, NULL, NULL, NULL, NULL, NULL),
(27, '2021-05-15 00:00:00', 20, 20, 'XX', 'XXVC', NULL, NULL, NULL, 3, 25, NULL, NULL, NULL, NULL, NULL),
(28, '2021-05-09 00:00:00', 10, 10, 'qq', 'qq', NULL, NULL, NULL, 3, 1, NULL, NULL, NULL, NULL, NULL),
(29, '2021-05-16 00:00:00', 10, 10, 'jj', 'mlko', NULL, NULL, NULL, 3, 1, NULL, NULL, NULL, NULL, NULL),
(31, '2021-05-15 00:00:00', 20, 20, 'FCXXFVB', 'O11', NULL, NULL, NULL, 3, 21, NULL, NULL, NULL, NULL, NULL),
(32, '2021-05-15 00:00:00', 10, 10, 'wxc', 'wxwx', NULL, NULL, NULL, 3, 1, NULL, NULL, NULL, NULL, NULL),
(33, '2021-05-22 00:00:00', 10, 10, 'vv', 'vv', NULL, NULL, NULL, 3, 1, NULL, NULL, NULL, NULL, NULL),
(34, '2021-05-15 00:00:00', 10, 10, 'sc', 'sdsdv', NULL, NULL, NULL, 3, 1, NULL, NULL, NULL, NULL, NULL),
(35, '2021-05-15 00:00:00', 20, 20, 'WC', 'WCX', NULL, NULL, NULL, 3, 25, NULL, NULL, NULL, NULL, NULL),
(38, '2021-05-14 00:00:00', 10, 10, 'gsdh', 'rezd', NULL, NULL, NULL, 3, 1, NULL, NULL, NULL, NULL, NULL),
(88, '2021-06-20 00:00:00', 10, 10, 'AZD', 'OUSS1', NULL, NULL, NULL, 74, 1, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `operation_societe_justif`
--

DROP TABLE IF EXISTS `operation_societe_justif`;
CREATE TABLE IF NOT EXISTS `operation_societe_justif` (
  `id` bigint(20) NOT NULL,
  `chemin` varchar(255) DEFAULT NULL,
  `date_justif` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `operation_societe` bigint(20) DEFAULT NULL,
  `operation_societe_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5o75egc2t24p9vxp3my9awde4` (`operation_societe_id`),
  KEY `FKh7n12nvkmv27pyd4cyac6qwwt` (`operation_societe`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `paiement`
--

DROP TABLE IF EXISTS `paiement`;
CREATE TABLE IF NOT EXISTS `paiement` (
  `id` bigint(20) NOT NULL,
  `date_paiement` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `montant` double NOT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `operation_societe` bigint(20) DEFAULT NULL,
  `operation_societe_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfnj1junk7g26muud6kdg59lsa` (`operation_societe_id`),
  KEY `FKo2c18i2i34e4e67bhlsaykffk` (`operation_societe`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `paiement`
--

INSERT INTO `paiement` (`id`, `date_paiement`, `description`, `montant`, `ref`, `operation_societe`, `operation_societe_id`) VALUES
(30, '2021-05-05 00:00:00', 'ddd', 1000, 'p1', 4, NULL),
(9, '2020-01-01 00:00:00', 'HHH', 110, 'p2', 5, NULL),
(10, '2020-01-01 00:00:00', 'HHH', 110, 'p3', 4, NULL),
(36, '2021-05-04 00:00:00', 'sf', 132, 'p4', 4, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(4, 'ROLE_SOCIETE'),
(5, 'ROLE_COMPTABLE'),
(6, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
CREATE TABLE IF NOT EXISTS `section` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `class_comptable` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrrpscwqyyqwsykk6588g6yjyt` (`class_comptable`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `societe`
--

DROP TABLE IF EXISTS `societe`;
CREATE TABLE IF NOT EXISTS `societe` (
  `id` bigint(20) NOT NULL,
  `ice` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `age` double DEFAULT NULL,
  `date_creation` datetime DEFAULT NULL,
  `raison_sociale` varchar(255) DEFAULT NULL,
  `annee_exploitation` int(11) NOT NULL,
  `blocked` bit(1) NOT NULL,
  `nbr_cnx` int(11) NOT NULL,
  `login` bigint(20) DEFAULT NULL,
  `comptable` bigint(20) DEFAULT NULL,
  `etat_societe` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl8l2m3ml0vgfu4udg8ko2n9k4` (`comptable`),
  KEY `FKd8ac7ipj4ubb260iw4p5mj274` (`etat_societe`),
  KEY `FK9l06hsvog72fauu4pg0c5pj31` (`login`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `societe`
--

INSERT INTO `societe` (`id`, `ice`, `nom`, `adresse`, `age`, `date_creation`, `raison_sociale`, `annee_exploitation`, `blocked`, `nbr_cnx`, `login`, `comptable`, `etat_societe`) VALUES
(7, '2', 'google', 'USA', 10, '2021-04-15 22:07:19', 'SCI', 0, b'0', 0, NULL, NULL, NULL),
(8, '3', 'microsoft', 'england', 6, '2020-11-11 22:08:05', 'SCM', 0, b'0', 0, NULL, NULL, NULL),
(9, '4', 'facebook', 'usa', 15, '2021-04-20 22:08:35', 'SCI', 0, b'0', 0, NULL, NULL, NULL),
(10, '5', 'airbus', 'france', 30, '2021-04-24 22:08:39', 'SCM', 0, b'0', 0, NULL, NULL, NULL),
(11, '6', 'michlon', 'france', 12, '2021-04-13 22:08:55', 'SCI', 0, b'0', 0, NULL, NULL, NULL),
(77, '7', 'safron', 'casablanca', 20, '2000-12-13 00:00:00', 'SCI', 0, b'0', 0, NULL, NULL, NULL),
(78, '8', 'MenaraPrefa', 'Marrakech', 19, '2000-12-13 00:00:00', 'SCM', 0, b'0', 0, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `sous_classe_comptable`
--

DROP TABLE IF EXISTS `sous_classe_comptable`;
CREATE TABLE IF NOT EXISTS `sous_classe_comptable` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `numero` int(11) NOT NULL,
  `classe_comptable` bigint(20) DEFAULT NULL,
  `classe_comptable_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsejqaijcijcmq8aqa2g1cftva` (`classe_comptable_id`),
  KEY `FKhacjc0ryy5m37e5fb4cftpsia` (`classe_comptable`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sous_classe_comptable`
--

INSERT INTO `sous_classe_comptable` (`id`, `libelle`, `numero`, `classe_comptable`, `classe_comptable_id`) VALUES
(1197, 'fgh', 7, 1196, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tauxis`
--

DROP TABLE IF EXISTS `tauxis`;
CREATE TABLE IF NOT EXISTS `tauxis` (
  `id` bigint(20) NOT NULL,
  `pourcentage` double DEFAULT NULL,
  `resultat_fiscal_max` double DEFAULT NULL,
  `resultat_fiscal_min` double DEFAULT NULL,
  `societe` bigint(20) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK774kevdarg64q4wcelt73xhbg` (`societe`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `taux_ir`
--

DROP TABLE IF EXISTS `taux_ir`;
CREATE TABLE IF NOT EXISTS `taux_ir` (
  `id` bigint(20) NOT NULL,
  `pourcentage` double DEFAULT NULL,
  `salaire_max` double DEFAULT NULL,
  `salaire_min` double DEFAULT NULL,
  `ref_taux` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `taux_ir`
--

INSERT INTO `taux_ir` (`id`, `pourcentage`, `salaire_max`, `salaire_min`, `ref_taux`) VALUES
(16, 0, 2500, 0, 't1'),
(17, 10, 4166, 2501, 't2'),
(18, 20, 5000, 4167, 't3'),
(19, 30, 6666, 5001, 't4');

-- --------------------------------------------------------

--
-- Table structure for table `taux_is_config`
--

DROP TABLE IF EXISTS `taux_is_config`;
CREATE TABLE IF NOT EXISTS `taux_is_config` (
  `id` bigint(20) NOT NULL,
  `cotisation_minimale` double DEFAULT NULL,
  `date_max` datetime DEFAULT NULL,
  `date_min` datetime DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `annee_max` int(11) DEFAULT NULL,
  `annee_min` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tva`
--

DROP TABLE IF EXISTS `tva`;
CREATE TABLE IF NOT EXISTS `tva` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `valeur` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `type_declaration_tva`
--

DROP TABLE IF EXISTS `type_declaration_tva`;
CREATE TABLE IF NOT EXISTS `type_declaration_tva` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `type_operation`
--

DROP TABLE IF EXISTS `type_operation`;
CREATE TABLE IF NOT EXISTS `type_operation` (
  `id` bigint(20) NOT NULL,
  `fraix_comptable_total` double NOT NULL,
  `fraix_fix_total` double NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `fraixcomptabletotal` double NOT NULL,
  `fraixfixtotal` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `username`) VALUES
(2, 'med@gmail.com', '$2a$10$lk.8lpx699diLZKlJaQ8H.rTcNC9e4z8yTEBm9AA/CGrf0Ai342lm', 'mohamed'),
(3, 'medbenkhazrra@gmail.com', '$2a$10$C/3/cLF//CYNdf.xiyM2quDg6QZS377YARVoFoF.rFNp/OJ0pXE6K', 'med'),
(4, 'ous@gmail.com', '$2a$10$IpsFRUTUEUUn390bx.bFsOt/ri7jcpOLhpZ3ivQ0Cx4Bo5ESSx/RC', 'ous'),
(5, 'badr@gmail.com', '$2a$10$dyleFlM3G2C//P9QlMnFoOtkRz7z9cI.Orn7XMW6eN0kFjWCgrDXC', 'admin'),
(6, 'fgdf@gmail.com', '$2a$10$tzuV5xHvsFmc3UG2uR2zS.v2ZMtb/TRXNN85l2cucklpv88aI4h5a', 'gog'),
(7, 'mic@gmail.com', '$2a$10$5v3WSH85SJT75QvAtbWN/.2JKptm9094.dEdNO2Gwgh3BdlYAsnzC', 'mic'),
(8, 'dfsgdf@gmail.com', '$2a$10$yK3z74FZUXRAJIc5Yo1GquVSdE3914ssKErxeKQCRBjOH2WVRYpem', 'mohamtud'),
(9, 'ayoub@gmail.com', '$2a$10$NoYMm9gmpREbmjywh08MEemDZzw2GKYe.o5eMGtMmjGKw3VtDd1oK', 'ayoub'),
(10, 'societe@gmail.com', '$2a$10$pL/QEUAiD/OzdUbKTorWrOoxKE5xisFVn2caJmwiD/BtHHrt/BvoK', 'societe'),
(11, 'compb@outlook.com', '$2a$10$1y6s.qIfPGw.kBXdSIXq..D7xDm9a7cOy8wNl0PMjhfHJqYoJjjw6', 'comptable'),
(12, 'google@gamil.com', '$2a$10$KEaUKd7tOHBkHWVU5Yd35uW8KzZo.D4yFBMEluHS45moiALoY8az.', 'google');

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE IF NOT EXISTS `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `roles_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`roles_id`),
  KEY `FKa62j07k5mhgifpp955h37ponj` (`roles_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(2, 4),
(3, 4),
(4, 4),
(7, 4),
(8, 4),
(9, 4),
(10, 4),
(12, 4),
(6, 5),
(11, 5),
(5, 6);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKa62j07k5mhgifpp955h37ponj` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
