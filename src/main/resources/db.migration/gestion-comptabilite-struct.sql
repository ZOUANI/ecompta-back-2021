-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 03, 2021 at 02:39 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
