-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 02 juin 2021 à 20:23
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.0

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestion-comptabilite3`
--

--
-- Déchargement des données de la table `connection`
--

INSERT INTO `connection` (`id`, `password`, `type`, `username`, `etat`, `societe_login`) VALUES
(39, 'admin', 'admin', 'admin', 'valider', NULL),
(51, '123', 'Societe', 'hamid', 'valider', 41),
(52, '123', 'Societe', 'yassin', 'valider', 3),
(53, '123', 'Societe', 'said', 'valider', 77),
(57, '123', 'Societe', 'oussama', 'valider', 74),
(59, 'AA', 'Societe', 'AA', 'valider', 60),
(61, '123', 'Societe', 'amine', 'valider', 71),
(63, NULL, NULL, NULL, NULL, 62),
(64, '123', 'Societe', 'ISSA', 'valider', NULL),
(66, NULL, NULL, NULL, NULL, 65),
(67, '123', 'Societe', 'hossam', 'valider', 70),
(69, NULL, NULL, NULL, NULL, 68),
(73, NULL, NULL, NULL, NULL, 72),
(76, NULL, NULL, NULL, NULL, 75),
(78, '123', 'Societe', 'ZOZO', 'valider', 81),
(80, NULL, NULL, NULL, NULL, 79),
(83, '123', 'Societe', 'kokab', 'valider', 84),
(85, 'zer', 'Societe', 'dfg', 'valider', 86),
(87, '123', 'Societe', 'HIHI', 'valider', NULL);

--
-- Déchargement des données de la table `etape`
--

INSERT INTO `etape` (`id`, `delai`, `description`, `libelle`, `montantcomptable`, `montantfix`, `type_operation`) VALUES
(2, '3 jours', 'EE', 'ouvrage', 100, 1000, 1),
(19, 'JFJ', 'string', 'E1', 10, 10, 0),
(22, 'JFJ', 'string', 'E22', 10, 10, 21),
(26, 'NN', 'string', 'H', 10, 10, 25);

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(89);

--
-- Déchargement des données de la table `operation_societe`
--

INSERT INTO `operation_societe` (`id`, `date_operation_societe`, `fraix_comptable`, `fraix_fix`, `libelle`, `ref`, `comptable_taiteur`, `comptable_validateur`, `etat_operation_societe`, `societe`, `type_operation`) VALUES
(4, '2020-10-10 00:00:00', 10, 10, 'string', 'O1', NULL, NULL, NULL, 3, 1),
(5, '2021-05-22 00:00:00', 10, 10, 'HHH', 'O2', NULL, NULL, NULL, 3, 1),
(6, '2021-05-15 00:00:00', 10, 10, 'ff', 'fffff', NULL, NULL, NULL, 3, 1),
(13, '2021-05-23 00:00:00', 10, 10, 'ddd', 'dd', NULL, NULL, NULL, 3, 1),
(14, '2021-05-16 00:00:00', 10, 10, 'fgfg', 'gg', NULL, NULL, NULL, 3, 1),
(15, '2021-05-14 00:00:00', 10, 10, 'DD', 'KOKA', NULL, NULL, NULL, 3, 1),
(16, '2021-05-09 00:00:00', 10, 10, 'XX', 'XX', NULL, NULL, NULL, 3, 1),
(17, '2021-05-15 00:00:00', 10, 10, 'qsd', 'sd', NULL, NULL, NULL, 3, 1),
(27, '2021-05-15 00:00:00', 20, 20, 'XX', 'XXVC', NULL, NULL, NULL, 3, 25),
(28, '2021-05-09 00:00:00', 10, 10, 'qq', 'qq', NULL, NULL, NULL, 3, 1),
(29, '2021-05-16 00:00:00', 10, 10, 'jj', 'mlko', NULL, NULL, NULL, 3, 1),
(31, '2021-05-15 00:00:00', 20, 20, 'FCXXFVB', 'O11', NULL, NULL, NULL, 3, 21),
(32, '2021-05-15 00:00:00', 10, 10, 'wxc', 'wxwx', NULL, NULL, NULL, 3, 1),
(33, '2021-05-22 00:00:00', 10, 10, 'vv', 'vv', NULL, NULL, NULL, 3, 1),
(34, '2021-05-15 00:00:00', 10, 10, 'sc', 'sdsdv', NULL, NULL, NULL, 3, 1),
(35, '2021-05-15 00:00:00', 20, 20, 'WC', 'WCX', NULL, NULL, NULL, 3, 25),
(38, '2021-05-14 00:00:00', 10, 10, 'gsdh', 'rezd', NULL, NULL, NULL, 3, 1),
(88, '2021-06-20 00:00:00', 10, 10, 'AZD', 'OUSS1', NULL, NULL, NULL, 74, 1);

--
-- Déchargement des données de la table `paiement`
--

INSERT INTO `paiement` (`id`, `date_paiement`, `description`, `montant`, `ref`, `operation_societe`) VALUES
(30, '2021-05-05 00:00:00', 'ddd', 1000, 'p1', 4),
(9, '2020-01-01 00:00:00', 'HHH', 110, 'p2', 5),
(10, '2020-01-01 00:00:00', 'HHH', 110, 'p3', 4),
(36, '2021-05-04 00:00:00', 'sf', 132, 'p4', 4);

--
-- Déchargement des données de la table `societe`
--

INSERT INTO `societe` (`id`, `adresse`, `age`, `annee_exploitation`, `blocked`, `date_creation`, `ice`, `nbr_cnx`, `raison_sociale`, `comptable`, `etat_societe`, `login`, `connection`) VALUES
(3, 'string', 0, 0, b'1', '2020-10-10 00:00:00', 'S1', 0, 'string', NULL, NULL, NULL, NULL),
(41, NULL, 5, 0, b'0', NULL, 'S2', 0, NULL, NULL, NULL, NULL, NULL),
(42, NULL, 5, 0, b'0', NULL, 'S3', 0, NULL, NULL, NULL, NULL, NULL),
(43, NULL, 5, 0, b'0', NULL, 'S4', 4, NULL, NULL, NULL, NULL, NULL),
(54, 'DF', 1, 10, b'0', '2020-01-01 00:00:00', 'S31', 4, 'GOLF', NULL, NULL, NULL, NULL),
(55, 'FHG', 0, 21, b'0', '2021-05-12 00:00:00', 'S44', 4, 'GHF', NULL, NULL, NULL, NULL),
(56, 'vcb', 0, 12, b'0', '2021-06-09 00:00:00', 'cvb', 4, 'cvb', NULL, NULL, NULL, NULL),
(58, 'SSS', 0, 12, b'0', '2021-06-23 00:00:00', 'OUSS', 4, 'SSS', NULL, NULL, NULL, NULL),
(60, 'AA', 0, 11, b'0', '2021-06-15 00:00:00', 'AA', 4, 'AA', NULL, NULL, NULL, NULL),
(62, 'AZ', 0, 2, b'0', '2021-06-30 00:00:00', 'AM', 4, 'AZ', NULL, NULL, NULL, NULL),
(65, 'SS', 0, 12, b'0', '2021-06-15 00:00:00', 'ISS', 4, 'SS', NULL, NULL, NULL, NULL),
(68, 'SS', 0, 33, b'0', '2021-06-09 00:00:00', 'HOSS', 4, 'SS', NULL, NULL, NULL, NULL),
(70, 'QSD', 0, 213, b'0', '2021-06-24 00:00:00', 'QSDF', 4, 'QSD', NULL, NULL, NULL, NULL),
(71, 'SDF', 0, 23, b'0', '2021-06-22 00:00:00', 'AAMI', 4, 'QSD', NULL, NULL, NULL, NULL),
(72, 'SDF', 0, 34, b'0', '2021-06-30 00:00:00', 'OIK', 4, 'SDF', NULL, NULL, NULL, NULL),
(74, 'SDF', 0, 34, b'0', '2021-06-30 00:00:00', 'OIKV', 4, 'SDF', NULL, NULL, NULL, NULL),
(75, 'QADQ', 0, 4, b'0', '2021-06-02 00:00:00', 'SAID', 4, 'QSD', NULL, NULL, NULL, NULL),
(77, 'QADQ', 0, 4, b'0', '2021-06-02 00:00:00', 'SAIDG', 4, 'QSD', NULL, NULL, NULL, NULL),
(79, 'ZZ', 0, 12, b'0', '2021-06-08 00:00:00', 'ZOZO', 4, 'ZZ', NULL, NULL, NULL, NULL),
(81, 'ZZ', 0, 12, b'0', '2021-06-08 00:00:00', 'ZOZOG', 4, 'ZZ', NULL, NULL, NULL, NULL),
(82, 'FGFG', 0, 3, b'0', '2021-06-08 00:00:00', 'YASSIN', 4, 'FGFG', NULL, NULL, NULL, NULL),
(84, 'WXC', 0, 2, b'0', '2021-06-22 00:00:00', 'KACM', 4, 'WXC', NULL, NULL, NULL, NULL),
(86, 'azd', 0, 21, b'0', '2021-06-09 00:00:00', 'dd', 4, 'azd', NULL, NULL, NULL, NULL);

--
-- Déchargement des données de la table `type_operation`
--

INSERT INTO `type_operation` (`id`, `description`, `fraixcomptabletotal`, `fraixfixtotal`, `libelle`) VALUES
(1, 'HH', 10, 10, 'T1'),
(25, 'SS', 20, 20, 'T2'),
(20, 'SS', 0, 0, 'T3'),
(21, 'SS', 20, 20, 'T4'),
(23, 'SS', 10, 10, 'T5'),
(24, 'SS', 10, 10, 'T6');
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
