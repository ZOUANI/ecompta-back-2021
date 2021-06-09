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

--
-- Dumping data for table `classe_comptable`
--

INSERT INTO `classe_comptable` (`id`, `libelle`, `numero`) VALUES
(1196, 'dh', 5);

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

--
-- Dumping data for table `demande`
--

INSERT INTO `demande` (`id`, `annee`, `mois`, `operation`, `ref`, `valider`, `societe`, `user`, `date_demande`) VALUES
(1422, 2018, 4, 'Declaration TVA', 'dem5', b'0', 8, NULL, NULL),
(1423, 2013, 2, 'declaration IR', 'dm6', b'0', 7, NULL, NULL),
(1424, 123, 2, 'decvlaration tva', 'fd', b'0', 7, NULL, NULL),
(1425, 145, 7, 'declaration IS', 'ds', b'0', 7, NULL, NULL),
(1429, 2013, 2, 'Declaration IS', 'test f', b'0', 7, NULL, NULL);

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

--
-- Dumping data for table `etape`
--

INSERT INTO `etape` (`id`, `montant_comptable`, `montant_fix`, `description`, `délai`, `libelle`, `type_operation`, `delai`, `montantcomptable`, `montantfix`, `type_operation_id`) VALUES
(2, 0, 0, 'EE', NULL, 'ouvrage', 1, '3 jours', 100, 1000, NULL),
(19, 0, 0, 'string', NULL, 'E1', 0, 'JFJ', 10, 10, NULL),
(22, 0, 0, 'string', NULL, 'E22', 21, 'JFJ', 10, 10, NULL),
(26, 0, 0, 'string', NULL, 'H', 25, 'NN', 10, 10, NULL);

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1511),
(89);

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `password`, `type`, `username`, `societe_login`, `societe_login_id`) VALUES
(1198, 'google', 'societe', 'gog', 7, NULL),
(1199, 'microsoft', 'societe', 'mic', NULL, NULL),
(1200, 'facebook', 'societe', 'face', NULL, NULL),
(1201, 'admin', 'Admin', 'admin', NULL, NULL),
(1203, 'comptable', 'comptable', 'comptable', NULL, NULL);

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

--
-- Dumping data for table `paiement`
--

INSERT INTO `paiement` (`id`, `date_paiement`, `description`, `montant`, `ref`, `operation_societe`, `operation_societe_id`) VALUES
(30, '2021-05-05 00:00:00', 'ddd', 1000, 'p1', 4, NULL),
(9, '2020-01-01 00:00:00', 'HHH', 110, 'p2', 5, NULL),
(10, '2020-01-01 00:00:00', 'HHH', 110, 'p3', 4, NULL),
(36, '2021-05-04 00:00:00', 'sf', 132, 'p4', 4, NULL);

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(4, 'ROLE_SOCIETE'),
(5, 'ROLE_COMPTABLE'),
(6, 'ROLE_ADMIN');

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

--
-- Dumping data for table `sous_classe_comptable`
--

INSERT INTO `sous_classe_comptable` (`id`, `libelle`, `numero`, `classe_comptable`, `classe_comptable_id`) VALUES
(1197, 'fgh', 7, 1196, NULL);

--
-- Dumping data for table `taux_ir`
--

INSERT INTO `taux_ir` (`id`, `pourcentage`, `salaire_max`, `salaire_min`, `ref_taux`) VALUES
(16, 0, 2500, 0, 't1'),
(17, 10, 4166, 2501, 't2'),
(18, 20, 5000, 4167, 't3'),
(19, 30, 6666, 5001, 't4');

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


-------------------------------------------------------------------------------------------
--
-- Déchargement des données de la table `acomptes`
--

INSERT INTO `acomptes` (`id`, `annee_paye`, `montant`, `numero`, `societe`) VALUES
(287, 2021, 355608.6025, 1, NULL),
(285, 2021, 42478.3, 1, NULL),
(291, 2021, 0, 1, NULL),
(293, 2021, 290500, 1, NULL),
(295, 2021, 42478.3, 1, NULL),
(297, 2021, 42478.3, 1, NULL),
(330, 2021, 290500, 1, 1);

--
-- Déchargement des données de la table `declarationis`
--

INSERT INTO `declarationis` (`id`, `annee`, `montantiscalcule`, `montantispaye`, `totalhtcharge`, `totalhtdiff`, `totalhtgain`, `societe`, `taux_is_config`, `tauxis`, `ref`, `etat_declaration`, `total_paye`, `acomptes`) VALUES
(294, 2020, 1162000, 1162000, 4100000, 4200000, 8300000, 1, 91, 6, '1622765357838', 101, 1452500, 293),
(292, 2020, 170000, 0, 8000000, 1000000, 9000000, 157, 91, 6, '1622765343570', 100, 0, 291),
(325, 2021, 1.1, 3000, 0, 11, 11, 3, 91, 4, '1622852151408', 101, 3000, NULL),
(289, 2019, 0, 3000, 73200, -36300, 36900, 1, 91, NULL, '1622764928672', 100, 3000, NULL),
(288, 2020, 1422434.41, 1422434.41, 5100000, 5040111, 10140111, 2, 91, 6, '1622764871986', 100, 1778043.0125, 287);

--
-- Déchargement des données de la table `demande`
--

INSERT INTO `demande` (`id`, `annee`, `mois`, `operation`, `ref`, `societe`, `user`, `date_demande`, `trim`, `trimestre`, `comptable_traiteur`, `comptable_validateur`, `etat_demande`) VALUES
(364, 2020, NULL, 'Declaration IS', 'd1', 2, NULL, '2021-06-09 00:52:14', NULL, NULL, NULL, NULL, 363),
(365, 2020, NULL, 'Declaration IS', 'd2', 1, NULL, '2021-06-09 00:53:13', NULL, NULL, NULL, NULL, 363),
(368, 2020, 3, 'Declaration IR', 'd3', 1, NULL, '2021-06-09 01:11:33', NULL, 1, NULL, NULL, 363),
(369, 2019, 11, 'Declaration IR', 'd4', 1, NULL, '2021-06-09 01:12:45', NULL, 4, NULL, NULL, 363),
(370, 2019, NULL, 'Declaration IS', 'd5', 1, NULL, '2021-06-09 01:13:43', NULL, NULL, NULL, NULL, 363),
(371, 2019, 4, 'Declaration TVA', 'd6', 157, NULL, '2021-06-09 01:14:28', NULL, 2, NULL, NULL, 363),
(372, 2018, 7, 'Declaration TVA', 'd7', 157, NULL, '2021-06-09 01:17:41', NULL, 3, NULL, NULL, 363);

--
-- Déchargement des données de la table `etat_declaration`
--

INSERT INTO `etat_declaration` (`id`, `libelle`, `ref`) VALUES
(100, 'valider', 'etat1'),
(101, 'brouillon', 'etat2');

--
-- Déchargement des données de la table `etat_demande`
--

INSERT INTO `etat_demande` (`id`, `libelle`, `ref`) VALUES
(359, 'traitée', 'e1'),
(360, 'en cours de traitement', 'e2'),
(361, 'rejetée', 'e3'),
(362, 'acceptée', 'e4'),
(363, 'en attente de réponse', 'e5');

--
-- Déchargement des données de la table `tauxis`
--

INSERT INTO `tauxis` (`id`, `pourcentage`, `ref`, `resultat_fiscal_max`, `resultat_fiscal_min`) VALUES
(4, 10, 't1', 300000, 0),
(5, 20, 't2', 1000000, 300000),
(6, 31, 't3', 9999999999, 1000000);

--
-- Déchargement des données de la table `taux_is_config`
--

INSERT INTO `taux_is_config` (`id`, `cotisation_minimale`, `ref`, `annee_max`, `annee_min`) VALUES
(91, 3000, 'cm1', 2021, 2018),
(139, 1500, 'cm2', 2017, 2015);

-------------------------------------------------------------------------------------------


SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
