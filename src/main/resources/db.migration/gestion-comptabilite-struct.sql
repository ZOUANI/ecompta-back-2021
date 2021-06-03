-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 02 juin 2021 à 20:22
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

-- --------------------------------------------------------

--
-- Structure de la table `categorie_service`
--

CREATE TABLE `categorie_service` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `lien` varchar(255) DEFAULT NULL,
  `montant` double DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `classe_comptable`
--

CREATE TABLE `classe_comptable` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `numero` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `comptable`
--

CREATE TABLE `comptable` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `compte_comptable`
--

CREATE TABLE `compte_comptable` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `sous_classe_comptable` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `connection`
--

CREATE TABLE `connection` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `etat` varchar(255) DEFAULT NULL,
  `societe_login` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `declarationir`
--

CREATE TABLE `declarationir` (
  `id` bigint(20) NOT NULL,
  `annee` int(11) DEFAULT NULL,
  `mois` int(11) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `societe` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `declarationiremploye`
--

CREATE TABLE `declarationiremploye` (
  `id` bigint(20) NOT NULL,
  `montantir` double DEFAULT NULL,
  `ref_emp` varchar(255) DEFAULT NULL,
  `salaire_brut` double DEFAULT NULL,
  `salaire_net` double DEFAULT NULL,
  `declarationir` bigint(20) DEFAULT NULL,
  `employe` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `declarationis`
--

CREATE TABLE `declarationis` (
  `id` bigint(20) NOT NULL,
  `annee` int(11) DEFAULT NULL,
  `montantiscalcule` double DEFAULT NULL,
  `montantispaye` double DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `totalhtcharge` double DEFAULT NULL,
  `totalhtdiff` double DEFAULT NULL,
  `totalhtgain` double DEFAULT NULL,
  `etat_declaration` bigint(20) DEFAULT NULL,
  `societe` bigint(20) DEFAULT NULL,
  `tauxis` bigint(20) DEFAULT NULL,
  `taux_is_config` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `declaration_tva`
--

CREATE TABLE `declaration_tva` (
  `id` bigint(20) NOT NULL,
  `annee` double NOT NULL,
  `difftva` double NOT NULL,
  `mois` double NOT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `trim` double NOT NULL,
  `tvacollecter` double NOT NULL,
  `tvaperdue` double NOT NULL,
  `etat_declaration` bigint(20) DEFAULT NULL,
  `societe` bigint(20) DEFAULT NULL,
  `type_declaration_tva` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `details`
--

CREATE TABLE `details` (
  `id` bigint(20) NOT NULL,
  `montant_tranche_revenu` double DEFAULT NULL,
  `pourcentage` double DEFAULT NULL,
  `valeur` double DEFAULT NULL,
  `declarationiremploye` bigint(20) DEFAULT NULL,
  `taux_ir` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

CREATE TABLE `employe` (
  `id` bigint(20) NOT NULL,
  `cin` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `salaire` double DEFAULT NULL,
  `societe_emp` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `etape`
--

CREATE TABLE `etape` (
  `id` bigint(20) NOT NULL,
  `delai` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `montantcomptable` double NOT NULL,
  `montantfix` double NOT NULL,
  `type_operation` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `etat_declaration`
--

CREATE TABLE `etat_declaration` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `etat_facture`
--

CREATE TABLE `etat_facture` (
  `id` bigint(20) NOT NULL,
  `code` double NOT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `etat_operation_societe`
--

CREATE TABLE `etat_operation_societe` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `etat_paiement`
--

CREATE TABLE `etat_paiement` (
  `id` bigint(20) NOT NULL,
  `code` double NOT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `etat_societe`
--

CREATE TABLE `etat_societe` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE `facture` (
  `id` bigint(20) NOT NULL,
  `annee` double NOT NULL,
  `credit` varchar(255) DEFAULT NULL,
  `date_operation` datetime DEFAULT NULL,
  `debit` varchar(255) DEFAULT NULL,
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
  `etat_facture` bigint(20) DEFAULT NULL,
  `etat_paiement` bigint(20) DEFAULT NULL,
  `societe_distination` bigint(20) DEFAULT NULL,
  `societe_source` bigint(20) DEFAULT NULL,
  `tva` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `login`
--

CREATE TABLE `login` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `societe_login` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `operation_societe`
--

CREATE TABLE `operation_societe` (
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
  `type_operation` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `operation_societe_justif`
--

CREATE TABLE `operation_societe_justif` (
  `id` bigint(20) NOT NULL,
  `chemin` varchar(255) DEFAULT NULL,
  `date_justif` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `operation_societe` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

CREATE TABLE `paiement` (
  `id` bigint(20) NOT NULL,
  `date_paiement` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `montant` double NOT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `operation_societe` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `societe`
--

CREATE TABLE `societe` (
  `id` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `age` double DEFAULT NULL,
  `annee_exploitation` int(11) NOT NULL,
  `blocked` bit(1) NOT NULL,
  `date_creation` datetime DEFAULT NULL,
  `ice` varchar(255) DEFAULT NULL,
  `nbr_cnx` int(11) NOT NULL,
  `raison_sociale` varchar(255) DEFAULT NULL,
  `comptable` bigint(20) DEFAULT NULL,
  `etat_societe` bigint(20) DEFAULT NULL,
  `login` bigint(20) DEFAULT NULL,
  `connection` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `sous_classe_comptable`
--

CREATE TABLE `sous_classe_comptable` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `numero` int(11) NOT NULL,
  `classe_comptable` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `tauxis`
--

CREATE TABLE `tauxis` (
  `id` bigint(20) NOT NULL,
  `pourcentage` double DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `resultat_fiscal_max` double DEFAULT NULL,
  `resultat_fiscal_min` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `taux_ir`
--

CREATE TABLE `taux_ir` (
  `id` bigint(20) NOT NULL,
  `pourcentage` double DEFAULT NULL,
  `salaire_max` double DEFAULT NULL,
  `salaire_min` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `taux_is_config`
--

CREATE TABLE `taux_is_config` (
  `id` bigint(20) NOT NULL,
  `annee_max` int(11) DEFAULT NULL,
  `annee_min` int(11) DEFAULT NULL,
  `cotisation_minimale` double DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `tva`
--

CREATE TABLE `tva` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `valeur` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `type_declaration_tva`
--

CREATE TABLE `type_declaration_tva` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `type_operation`
--

CREATE TABLE `type_operation` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fraixcomptabletotal` double NOT NULL,
  `fraixfixtotal` double NOT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categorie_service`
--
ALTER TABLE `categorie_service`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `classe_comptable`
--
ALTER TABLE `classe_comptable`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `comptable`
--
ALTER TABLE `comptable`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `compte_comptable`
--
ALTER TABLE `compte_comptable`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKajf6wdmvrh66jg39vg1aryt4m` (`sous_classe_comptable`);

--
-- Index pour la table `connection`
--
ALTER TABLE `connection`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKlfhr11nhro69n4e5bld37676v` (`societe_login`);

--
-- Index pour la table `declarationir`
--
ALTER TABLE `declarationir`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKexcs28w0o81k0brx25ryqnew5` (`societe`);

--
-- Index pour la table `declarationiremploye`
--
ALTER TABLE `declarationiremploye`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9fi3ikjrqlp51k5b4frxl1lb5` (`declarationir`),
  ADD KEY `FKp4c78i82c7ufslwte6bgy8nwt` (`employe`);

--
-- Index pour la table `declarationis`
--
ALTER TABLE `declarationis`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhrgj9wu6hrtdtm2pe9ilw9b7j` (`etat_declaration`),
  ADD KEY `FKs9d8lt5i9bcq2x98muj6cjw65` (`societe`),
  ADD KEY `FK8ebtmtwn5b3q4qcvnafsyrbcl` (`tauxis`),
  ADD KEY `FK9faw3kaqntoarvd1rtrgjuogt` (`taux_is_config`);

--
-- Index pour la table `declaration_tva`
--
ALTER TABLE `declaration_tva`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9jik3v4e0359rq2j1r9ek2te3` (`etat_declaration`),
  ADD KEY `FKgwbagwpqe8jdr7jtjbks2ybcm` (`societe`),
  ADD KEY `FKhib9p3529ikvttics1k7pdmpe` (`type_declaration_tva`);

--
-- Index pour la table `details`
--
ALTER TABLE `details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKludujo525y38yicgvacbrisni` (`declarationiremploye`),
  ADD KEY `FKo248o3y4fdvla3nqktjdj25qg` (`taux_ir`);

--
-- Index pour la table `employe`
--
ALTER TABLE `employe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpn7yd0yepktcu4mhb72rip3t6` (`societe_emp`);

--
-- Index pour la table `etape`
--
ALTER TABLE `etape`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcuegv6uhoe1tkuopignyeddjy` (`type_operation`);

--
-- Index pour la table `etat_declaration`
--
ALTER TABLE `etat_declaration`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `etat_facture`
--
ALTER TABLE `etat_facture`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `etat_operation_societe`
--
ALTER TABLE `etat_operation_societe`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `etat_paiement`
--
ALTER TABLE `etat_paiement`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `etat_societe`
--
ALTER TABLE `etat_societe`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK91gg897e94th5ddolarykb5wg` (`class_comptable`),
  ADD KEY `FK1vhjtkljul7u7sgqnf832qaru` (`declarationir`),
  ADD KEY `FKsjjtqw76htgw0f6xwlwc5bxt6` (`declarationis`),
  ADD KEY `FKsburti4kj44462x058oi43eae` (`declaration_tva`),
  ADD KEY `FKkr3ksts74gennv8v3sftugtj8` (`etat_facture`),
  ADD KEY `FKjve9pyoc84dr4klbv34lvts8f` (`etat_paiement`),
  ADD KEY `FKjyarvuk8jge8frk5fuqsxaxh6` (`societe_distination`),
  ADD KEY `FKcw44mq41ieyb1ohrp1a9wtp3g` (`societe_source`),
  ADD KEY `FKnfg1mkl9rtx7sewcxlpvm91x8` (`tva`);

--
-- Index pour la table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKje6b5e204hbs03l7mv14ihn80` (`societe_login`);

--
-- Index pour la table `operation_societe`
--
ALTER TABLE `operation_societe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8wgtlqlykqum3aosolcsmm9fr` (`comptable_taiteur`),
  ADD KEY `FKp374pje2pxaftbfbr5vhwjkrv` (`comptable_validateur`),
  ADD KEY `FK236u8q4brd0m5hfs2runxnllh` (`etat_operation_societe`),
  ADD KEY `FKh8or785pap4d7rvigp87o03pg` (`societe`),
  ADD KEY `FK1oawajdyyqiknhmeqqu719i0y` (`type_operation`);

--
-- Index pour la table `operation_societe_justif`
--
ALTER TABLE `operation_societe_justif`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh7n12nvkmv27pyd4cyac6qwwt` (`operation_societe`);

--
-- Index pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKo2c18i2i34e4e67bhlsaykffk` (`operation_societe`);

--
-- Index pour la table `societe`
--
ALTER TABLE `societe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt5wcqyopf3wqwnk5lpcjk7jhf` (`connection`),
  ADD KEY `FKl8l2m3ml0vgfu4udg8ko2n9k4` (`comptable`),
  ADD KEY `FKd8ac7ipj4ubb260iw4p5mj274` (`etat_societe`),
  ADD KEY `FK9l06hsvog72fauu4pg0c5pj31` (`login`);

--
-- Index pour la table `sous_classe_comptable`
--
ALTER TABLE `sous_classe_comptable`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhacjc0ryy5m37e5fb4cftpsia` (`classe_comptable`);

--
-- Index pour la table `tauxis`
--
ALTER TABLE `tauxis`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `taux_ir`
--
ALTER TABLE `taux_ir`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `taux_is_config`
--
ALTER TABLE `taux_is_config`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `tva`
--
ALTER TABLE `tva`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `type_declaration_tva`
--
ALTER TABLE `type_declaration_tva`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `type_operation`
--
ALTER TABLE `type_operation`
  ADD PRIMARY KEY (`id`);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
