-- phpMyAdmin SQL Dump
-- version 4.1.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Sam 18 Janvier 2014 à 20:27
-- Version du serveur :  5.5.24-log
-- Version de PHP :  5.3.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `projetjee`
--

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

CREATE TABLE IF NOT EXISTS `categories` (
  `idCategorie` int(11) NOT NULL AUTO_INCREMENT,
  `libelleCategorie` varchar(50) NOT NULL,
  PRIMARY KEY (`idCategorie`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `categories`
--

INSERT INTO `categories` (`idCategorie`, `libelleCategorie`) VALUES
(1, 'HEI'),
(2, 'Mèmes'),
(3, 'Rage Comics'),
(4, 'Humour');

-- --------------------------------------------------------

--
-- Structure de la table `commentaires`
--

CREATE TABLE IF NOT EXISTS `commentaires` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `commentDate` datetime DEFAULT NULL,
  `commentContent` text CHARACTER SET utf8,
  `idUser` int(11) NOT NULL,
  `cleeImg` varchar(25) NOT NULL,
  PRIMARY KEY (`commentId`),
  KEY `FK_commentaires_idUser` (`idUser`),
  KEY `FK_commentaires_cleeImg` (`cleeImg`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

-- --------------------------------------------------------

--
-- Structure de la table `contact`
--

CREATE TABLE IF NOT EXISTS `contact` (
  `idContact` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(75) NOT NULL,
  `sujet` varchar(75) NOT NULL,
  `message` text NOT NULL,
  `dateContact` datetime NOT NULL,
  `ipContact` varchar(50) NOT NULL,
  PRIMARY KEY (`idContact`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Structure de la table `historiques`
--

CREATE TABLE IF NOT EXISTS `historiques` (
  `idUser` int(11) NOT NULL,
  `cleeImg` varchar(25) NOT NULL,
  `idVote` int(11) NOT NULL AUTO_INCREMENT,
  `ptsVote` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUser`,`cleeImg`),
  KEY `FK_historiques_cleeImg` (`cleeImg`),
  KEY `idVote` (`idVote`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

-- --------------------------------------------------------

--
-- Structure de la table `images`
--

CREATE TABLE IF NOT EXISTS `images` (
  `idImg` int(11) NOT NULL AUTO_INCREMENT,
  `cleeImg` varchar(25) NOT NULL,
  `extImg` varchar(25) DEFAULT NULL,
  `titreImg` varchar(250) DEFAULT NULL,
  `textImg` text NOT NULL,
  `dateImg` datetime DEFAULT NULL,
  `ptsImg` int(11) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  `idCategorie` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idImg`),
  KEY `FK_images_idUser` (`idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=142 ;

--
-- Contenu de la table `images`
--

INSERT INTO `images` (`idImg`, `cleeImg`, `extImg`, `titreImg`, `textImg`, `dateImg`, `ptsImg`, `idUser`, `idCategorie`) VALUES
(1, '1017039', '.jpg', 'ça se passe mal', '', '2013-12-31 00:00:00', 0, 1, 2),
(2, '1390767', '.jpg', 'Constipé', '', '2013-12-31 00:00:00', 0, 1, 2),
(3, '1395214', '.jpg', 'La ressemblance n''est elle pas incroyable? :)', '', '2013-12-31 00:00:00', 0, 1, 1),
(4, '1455956', '.jpg', 'Impec!', '', '2013-12-31 00:00:00', 0, 1, 1),
(5, '1456789', '.jpg', '...', '', '2013-12-31 00:00:00', 0, 1, 1),
(6, '1458469', '.jpg', 'Kikou!!', '', '2013-12-31 00:00:00', 0, 1, 2),
(7, '1461073', '.jpg', 'Blurb', '', '2013-12-31 00:00:00', 0, 1, 2),
(8, '1461839', '.jpg', 'BG', '', '2013-12-31 00:00:00', 0, 1, 1),
(9, '1464605', '.png', '#BG', '', '2013-12-31 00:00:00', 0, 1, 1),
(10, '1468536', '.jpg', 'J''ai fait un gros dodo.', '', '2013-12-31 00:00:00', 0, 1, 2),
(11, '1468624', '.jpg', 'Quoi???? Tu veux pas être mon pote?', '', '2013-12-31 00:00:00', 0, 1, 1),
(12, '1469835', '.jpg', 'Merde! Plus de cheveux', '', '2013-12-31 00:00:00', 0, 1, 1),
(13, '1469979', '.jpg', 'OUAAAI MA GUEULE!', '', '2013-12-31 00:00:00', 0, 1, 1),
(14, '1470018', '.jpg', 'C''est beau les cours en H4', '', '2013-12-31 00:00:00', 0, 1, 1),
(15, '1470217', '.jpg', 'I''m a Fish', '', '2013-12-31 00:00:00', 0, 1, 1),
(16, '1471349', '.jpg', 'Cours de lubrification.', '', '2013-12-31 00:00:00', 0, 1, 1),
(17, '1472749', '.jpg', 'Tu pues des pieds!', '', '2013-12-31 00:00:00', 0, 1, 1),
(18, '1472927', '.jpg', 'Je suis frais', '', '2013-12-31 00:00:00', 0, 1, 1),
(19, '1472954', '.jpg', '...', '', '2013-12-31 00:00:00', 0, 1, 1),
(20, '1474416', '.jpg', 'Je comprends paaas!', '', '2013-12-31 00:00:00', 0, 1, 1),
(21, '1474428', '.jpg', 'Et ta soeur elle vient de NYC?', '', '2013-12-31 00:00:00', 0, 1, 2),
(22, '1475792', '.jpg', 'A Noël j''ai demande des oreilles...', '', '2013-12-31 00:00:00', 0, 1, 1),
(23, '1476003', '.jpg', 'Bonne nuit!', '', '2013-12-31 00:00:00', 0, 1, 1),
(24, '1476048', '.jpg', '...', '', '2013-12-31 00:00:00', 0, 1, 2),
(25, '1476107', '.jpg', 'Tellement SLIP...#gay#fouan+laroche', '', '2013-12-31 00:00:00', 0, 1, 1),
(26, '1476540', '.jpg', 'Salut!', '', '2013-12-31 00:00:00', 0, 1, 1),
(27, '1477940', '.jpg', 'Bien au chalet t''as vu!!', '', '2013-12-31 00:00:00', 0, 1, 1),
(28, '1479096', '.jpg', 'Merde, j''ai encore marché sur ma bite...', '', '2013-12-31 00:00:00', 0, 1, 1),
(29, '1479519', '.jpg', 'En live de hardelot beach padre de puta', '', '2013-12-31 00:00:00', 0, 1, 1),
(30, '1480627', '.jpg', '...', '', '2013-12-31 00:00:00', 0, 1, 1),
(31, '1480694', '.jpg', 'Facial du soir, bonsoir', '', '2013-12-31 00:00:00', 0, 1, 1),
(32, '1480778', '.jpg', 'cest le week end!!!!', '', '2013-12-31 00:00:00', 0, 1, 1),
(33, '1484158', '.jpg', '...', '', '2013-12-31 00:00:00', 0, 1, 1),
(34, '1484186', '.jpg', 'GENRE J''AI PAS EU MON BPL???', '', '2013-12-31 00:00:00', 0, 1, 2),
(35, '1484362', '.jpg', 'JB en apprenant la mort de Gandalf...', '', '2013-12-31 00:00:00', 0, 1, 1),
(36, '1484747', '.jpg', 'Horrible!! Ne fais jamais ca stp^^!!', '', '2013-12-31 00:00:00', 0, 1, 1),
(37, '1485090', '.jpg', 'Et la je suis un beau père noel?', '', '2013-12-31 00:00:00', 0, 1, 1),
(38, '1486547', '.jpg', 'Frohe weinachten', '', '2013-12-31 00:00:00', 0, 1, 1),
(39, '1486812', '.jpg', 'Caca d après déj', '', '2013-12-31 00:00:00', 0, 1, 2),
(40, '1486833', '.jpg', 'Coucou les filles!!', '', '2013-12-31 00:00:00', 0, 1, 1),
(41, '1486880', '.jpg', 'Homme soumis', '', '2013-12-31 00:00:00', 0, 1, 1),
(42, '1486928', '.jpg', 'ça pionce ici!!!', '', '2013-12-31 00:00:00', 0, 1, 1),
(43, '1488173', '.jpg', 'Dur dur', '', '2013-12-31 00:00:00', 0, 1, 1),
(44, '1488322', '.jpg', 'Toilettes avant de passer au fromage!', '', '2013-12-31 00:00:00', 0, 1, 2),
(45, '1488649', '.jpg', 'pause caca dqns un logement', '', '2013-12-31 00:00:00', 0, 1, 1),
(46, '1491672', '.jpg', 'Les tuche ????', '', '2013-12-31 00:00:00', 0, 1, 1),
(47, '1491695', '.jpg', 'Blutouffe', '', '2013-12-31 00:00:00', 0, 1, 1),
(48, '1493256', '.jpg', 'Réveil en douceur - geekage', '', '2013-12-31 00:00:00', 0, 1, 1),
(49, '1497514', '.jpg', '#hipster', '', '2013-12-31 00:00:00', 0, 1, 1),
(50, '1497516', '.jpg', 'arretes de screenshooter^^', '', '2013-12-31 00:00:00', 0, 1, 1),
(51, '1497588', '.jpg', 'SOOOOOOOO SWAG!!!!!!!', '', '2013-12-31 00:00:00', 0, 1, 1),
(52, '1499545', '.jpg', 'Cadeau de noël de mon colloc...', '', '2013-12-31 00:00:00', 0, 1, 1),
(53, '1503483', '.jpg', '...', '', '2013-12-31 00:00:00', 0, 1, 1),
(54, '1504029', '.jpg', 'Demain le deuxième repas!', '', '2013-12-31 00:00:00', 0, 1, 2),
(55, '1505105', '.jpg', 'Le repos du guerrier!', '', '2013-12-31 00:00:00', 0, 1, 1),
(56, '1506407', '.jpg', 'Represent!!!!', '', '2013-12-31 00:00:00', 0, 1, 1),
(57, '1506636', '.jpg', 'Seule dieu peut m''aider pour demain..', '', '2013-12-31 00:00:00', 0, 1, 1),
(58, '1506650', '.jpg', 'Bon bah galette!', '', '2013-12-31 00:00:00', 0, 1, 1),
(59, '1507029', '.jpg', 'C''est mon copain!!', '', '2013-12-31 00:00:00', 0, 1, 1),
(60, '1507948', '.jpg', '#Girafe', '', '2013-12-31 00:00:00', 0, 1, 1),
(61, '1508544', '.jpg', 'Bouuh', '', '2013-12-31 00:00:00', 0, 1, 2),
(62, '1511402', '.jpg', 'My nerd!!!', '', '2013-12-31 00:00:00', 0, 1, 1),
(63, '1512533', '.jpg', 'Snap gominet', '', '2013-12-31 00:00:00', 0, 1, 2),
(64, '1512743', '.jpg', 'Bourré et le bide plein, merci Noël', '', '2013-12-31 00:00:00', 0, 1, 1),
(65, '1514557', '.jpg', 'Oulahlah', '', '2013-12-31 00:00:00', 0, 1, 2),
(66, '1514653', '.jpg', 'Tu veux un bisous?', '', '2013-12-31 00:00:00', 0, 1, 2),
(67, '1514981', '.jpg', 'Beurk :)', '', '2013-12-31 00:00:00', 0, 1, 2),
(68, '1515051', '.jpg', 'Brazillllllllllllllll', '', '2013-12-31 00:00:00', 0, 1, 2),
(69, '1517512', '.jpg', 'La chimie c est chiant!', '', '2013-12-31 00:00:00', 0, 1, 1),
(70, '1517628', '.png', 'LE DECES', '', '2013-12-31 00:00:00', 0, 1, 1),
(71, '1521231', '.jpg', 'éclatés', '', '2013-12-31 00:00:00', 0, 1, 1),
(72, '1521523', '.jpg', 'Debranlé...4g', '', '2013-12-31 00:00:00', 0, 1, 1),
(73, '1522023', '.jpg', 'Ramé foreward des courriels', '', '2013-12-31 00:00:00', 0, 1, 1),
(74, '1522340', '.jpg', 'Hard monday morning', '', '2013-12-31 00:00:00', 0, 1, 1),
(75, '1524579', '.jpg', '...', '', '2013-12-31 00:00:00', 0, 1, 1),
(76, '1524715', '.jpg', 'Joyeux Noël #tropbouffé', '', '2013-12-31 00:00:00', 0, 1, 1),
(77, '1524896', '.jpg', 'petit mathou va devenir énorme', '', '2014-01-02 00:00:00', 0, 1, 2),
(78, '1525026', '.jpg', 'Noyeux Joël les copains!!', '', '2013-12-31 00:00:00', 0, 1, 2),
(79, '1525225', '.jpg', 'Pause burger pour serguei', '', '2013-12-31 00:00:00', 0, 1, 1),
(80, '1525553', '.jpg', 'Je reviens du futur', '', '2013-12-31 00:00:00', 0, 1, 1),
(81, '1526618', '.jpg', 'Faut pas croire...elle travaille :P..!!', '', '2013-12-31 00:00:00', 0, 1, 1),
(82, '1526672', '.jpg', 'Le 10eme Reich va commencer!', '', '2013-12-31 00:00:00', 0, 1, 1),
(83, '1526893', '.jpg', 'SNAPOLEOOOON', '', '2013-12-31 00:00:00', 0, 1, 1),
(84, '1526933', '.jpg', 'Si tu l aimes, envois lui de l espoir', '', '2013-12-31 00:00:00', 0, 1, 1),
(85, '1526995', '.jpg', '...', '', '2013-12-31 00:00:00', 0, 1, 1),
(86, '1527078', '.jpg', 'Bonjour!', '', '2013-12-31 00:00:00', 0, 1, 1),
(87, '1527099', '.jpg', 'Non :p', '', '2014-01-02 00:00:00', 0, 1, 2),
(88, '1528576', '.jpg', 'Vers l''infini et au delà!!!', '', '2013-12-31 00:00:00', 0, 1, 1),
(89, '1528634', '.jpg', 'Joyeux noel les copains!', '', '2013-12-31 00:00:00', 0, 1, 1),
(90, '1528643', '.jpg', 'Mon frère, ce hipster.', '', '2013-12-31 00:00:00', 0, 1, 1),
(91, '1528676', '.jpg', 'En mode ruskoff', '', '2013-12-31 00:00:00', 0, 1, 1),
(92, '1528709', '.jpg', 'Oh nooooon! Je suis demasqué', '', '2013-12-31 00:00:00', 0, 1, 1),
(93, '1530440', '.jpg', 'Trop chaud, enlèves le maillot!', '', '2013-12-31 00:00:00', 0, 1, 1),
(94, '1530500', '.jpg', 'tHEIsticule', '', '2013-12-31 00:00:00', 0, 1, 1),
(95, '1530546', '.jpg', 'PPP = Pauvre Pollak Pleurs', '', '2013-12-31 00:00:00', 0, 1, 2),
(96, '1538744', '.jpg', 'Joyeux Noël!!!!!!', '', '2013-12-31 00:00:00', 0, 1, 1),
(97, '1538756', '.jpg', 'Noël', '', '2013-12-31 00:00:00', 0, 1, 1),
(98, '1544585', '.jpg', 'JOYEUX NOËL!!!!!! <3', '', '2013-12-31 00:00:00', 0, 1, 1),
(99, '1545185', '.jpg', 'Bonne année!!!', '', '2013-12-31 00:00:00', 0, 1, 2),
(100, '1545556', '.jpg', 'Reconversion élevage de lama au Pérou', '', '2014-01-02 00:00:00', 0, 1, 1),
(101, '555288', '.jpg', 'Lulu exploooose', '', '2013-12-31 00:00:00', 0, 1, 2),
(102, '579346', '.jpg', 'Plus faim...', '', '2013-12-31 00:00:00', 0, 1, 2),
(103, '580586', '.jpg', 'Snapbiatch', '', '2013-12-31 00:00:00', 0, 1, 1),
(104, '580791', '.jpg', 'Bonjour bonjour', '', '2013-12-31 00:00:00', 0, 1, 1),
(105, '936548', '.jpg', 'Ovalies 2013', '', '2013-12-30 00:00:00', 0, 1, 1),
(106, '945841', '.jpg', 'Aaah les amoureeeeuuh', '', '2013-12-31 00:00:00', 0, 1, 1),
(107, '971393', '.jpg', 'On adore les sucettes!', '', '2013-12-31 00:00:00', 0, 1, 1),
(108, '988443', '.jpg', 'Poseeyyy', '', '2013-12-31 00:00:00', 0, 1, 1),
(109, '993812', '.jpg', 'Yeah Mister white!!', '', '2013-12-31 00:00:00', 0, 1, 1),
(110, '993836', '.jpg', 'Putain de plannif!!', '', '2013-12-31 00:00:00', 0, 1, 2),
(111, '996671', '.jpg', 'Bienvenue dans la téci wesh', '', '2013-12-31 00:00:00', 0, 1, 1),
(112, '997036', '.jpg', '...', '', '2013-12-31 00:00:00', 0, 1, 1),
(113, '999911', '.jpg', 'Bonjour les amis :)', '', '2013-12-31 00:00:00', 0, 1, 1),
(114, '999922', '.jpg', 'Luigi et Mario ont la banane!!', '', '2013-12-31 00:00:00', 0, 1, 1),
(124, '1737270185', '.jpg', 'Wesh wesh market!', '', '2014-01-18 19:00:22', 0, 1, 1),
(125, '186548372', '.jpg', 'Salut toi!', '', '2014-01-18 19:06:11', 0, 1, 2),
(126, '1296073388', '.jpg', '=O', '', '2014-01-18 19:06:45', 0, 1, 1),
(127, '1835068935', '.jpg', 'Caca fondu...', '', '2014-01-18 19:10:15', 0, 1, 2),
(128, '1690382970', '.jpg', 'C''est la fin des haricots!', '', '2014-01-18 19:11:22', 0, 1, 1),
(129, '55753686', '.jpg', 'Ã§a bosse hardcore', '', '2014-01-18 19:12:00', 0, 1, 4),
(130, '2102377907', '.jpg', 'Abraham lille con !', '', '2014-01-18 19:12:14', 0, 1, 4),
(131, '1416381157', '.jpg', 'Batman', '', '2014-01-18 19:12:25', 0, 1, 1),
(132, '576545510', '.jpg', 'Il est 8h, bonne nuit Oscar', '', '2014-01-18 19:12:38', 0, 1, 4),
(133, '211362536', '.jpg', 'QualitÃ© Time!', '', '2014-01-18 19:12:57', 0, 1, 2),
(134, '878678256', '.jpg', '#metroroissyspherevitalekisscool', '', '2014-01-18 19:13:12', 0, 1, 1),
(135, '953841536', '.jpg', 'Etre sortie 1 veille d''exam. Check', '', '2014-01-18 19:13:29', 0, 1, 1),
(136, '243937489', '.jpg', 'Qu''est skon rit avant ses exammm', '', '2014-01-18 19:13:48', 0, 1, 4),
(137, '1969418975', '.jpg', 'Galette', '', '2014-01-18 19:14:00', 0, 1, 1),
(138, '373244195', '.jpg', 'Solution cheveux long', '', '2014-01-18 19:14:16', 0, 1, 1),
(139, '1479085366', '.jpg', 'Ciao!', '', '2014-01-18 19:14:26', 0, 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `newsletter`
--

CREATE TABLE IF NOT EXISTS `newsletter` (
  `idNewsletter` int(11) NOT NULL AUTO_INCREMENT,
  `emailNewsletter` varchar(75) NOT NULL,
  `dateNewsletterInsc` datetime NOT NULL,
  PRIMARY KEY (`idNewsletter`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `user_pseudo` varchar(50) DEFAULT NULL,
  `user_mail` varchar(50) DEFAULT NULL,
  `user_password` varchar(32) DEFAULT NULL,
  `user_ip` varchar(25) DEFAULT NULL,
  `user_registered` datetime DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`idUser`, `user_pseudo`, `user_mail`, `user_password`, `user_ip`, `user_registered`) VALUES
(1, 'admin', 'admin@hotmail.fr', '9697542531b409622caae066d292bd37', '192.168.0.1', '2013-12-31 00:00:00');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
