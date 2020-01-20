-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.6.45-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema rosamarc_show_pisos
--

CREATE DATABASE IF NOT EXISTS rosamarc_show_pisos;
USE rosamarc_show_pisos;

--
-- Definition of table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
CREATE TABLE `estoque` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cnpj_loja` varchar(14) NOT NULL,
  `codigo` varchar(14) NOT NULL,
  `descricao` varchar(100) NOT NULL,
  `saldo` double NOT NULL,
  `ativo` varchar(3) NOT NULL,
  `unidade` varchar(5) NOT NULL,
  `ean` varchar(30) DEFAULT NULL,
  `data_ult_ent` varchar(10) DEFAULT NULL,
  `data_ult_sai` varchar(10) DEFAULT NULL,
  `data_cadastro` varchar(10) DEFAULT NULL,
  `nome_arquivo` varchar(20) NOT NULL,
  `observacao` varchar(50) DEFAULT NULL,
  `hora_cadastro` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_2` (`cnpj_loja`,`codigo`),
  UNIQUE KEY `Index_3` (`cnpj_loja`,`ean`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `estoque`
--

/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
INSERT INTO `estoque` (`id`,`cnpj_loja`,`codigo`,`descricao`,`saldo`,`ativo`,`unidade`,`ean`,`data_ult_ent`,`data_ult_sai`,`data_cadastro`,`nome_arquivo`,`observacao`,`hora_cadastro`) VALUES 
 (12,'10767579000176','000001','Piso Ceramico Eliane Forma Acetinado Bold C: 45cm X L: 45cm Branco',20,'Sim','CX','7890942437469',NULL,NULL,'2020/01/20','cad-produto_A3.env','Cadastro inicial','09:24'),
 (13,'10767579000176','000002','Piso Ceramico Eliane Forma Acetinado Bold C: 45cm X L: 45cm Preto',200,'Sim','CX','7890942437466',NULL,NULL,'2020/01/20','cad-produto_A4.env','Cadastro inicial 100 cx','09:26');
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;


--
-- Definition of table `hist_arquivos`
--

DROP TABLE IF EXISTS `hist_arquivos`;
CREATE TABLE `hist_arquivos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `arquivo` varchar(30) NOT NULL,
  `processado` varchar(3) NOT NULL,
  `data_cadastro` varchar(10) DEFAULT NULL,
  `hora_cadastro` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hist_arquivos`
--

/*!40000 ALTER TABLE `hist_arquivos` DISABLE KEYS */;
INSERT INTO `hist_arquivos` (`id`,`arquivo`,`processado`,`data_cadastro`,`hora_cadastro`) VALUES 
 (8,'cad-loja_A1','Sim','2020/01/20','08:39'),
 (9,'cad-loja_a2.env','Sim','2020/01/20','08:44'),
 (10,'cad-produto_A3.env','NAO','2020/01/20','08:52'),
 (11,'cad-produto_A3.env','NAO','2020/01/20','09:10'),
 (12,'cad-produto_A3env','NAO','2020/01/20','09:11'),
 (13,'cad-produto_A3.env','NAO','2020/01/20','09:15'),
 (14,'cad-produto_A3.env','NAO','2020/01/20','09:17'),
 (15,'cad-produto_A3.env','NAO','2020/01/20','09:18'),
 (16,'cad-produto_A3.env','NAO','2020/01/20','09:19'),
 (17,'cad-produto_A3.env','NAO','2020/01/20','09:23'),
 (18,'cad-produto_A3.env','Sim','2020/01/20','09:24'),
 (19,'cad-produto_A4.env','Sim','2020/01/20','09:26'),
 (20,'alt-status_A5.env','Sim','2020/01/20','09:29'),
 (21,'alt-status_A6.env','Sim','2020/01/20','09:30'),
 (22,'mov-produto_A7.env','Sim','2020/01/20','09:32'),
 (23,'mov-produto_A8.env','Sim','2020/01/20','09:33'),
 (24,'mov-produto_A9.env','Sim','2020/01/20','09:34');
/*!40000 ALTER TABLE `hist_arquivos` ENABLE KEYS */;


--
-- Definition of table `hist_movimentacao`
--

DROP TABLE IF EXISTS `hist_movimentacao`;
CREATE TABLE `hist_movimentacao` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cnpj_loja` varchar(14) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `codigo` varchar(14) NOT NULL,
  `ean` varchar(30) DEFAULT NULL,
  `quantidade` double NOT NULL,
  `observacao` varchar(50) DEFAULT NULL,
  `nome_arquivo` varchar(20) NOT NULL,
  `qual_foi_chave` varchar(6) NOT NULL,
  `data_movimentacao` varchar(10) NOT NULL,
  `hora_movimentacao` varchar(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hist_movimentacao`
--

/*!40000 ALTER TABLE `hist_movimentacao` DISABLE KEYS */;
INSERT INTO `hist_movimentacao` (`id`,`cnpj_loja`,`tipo`,`codigo`,`ean`,`quantidade`,`observacao`,`nome_arquivo`,`qual_foi_chave`,`data_movimentacao`,`hora_movimentacao`) VALUES 
 (11,'10767579000176','C','000001','7890942437469',0,'Cadastro inicial','cad-produto_A3.env','','2020/01/20','09:24'),
 (12,'10767579000176','C','000002','7890942437466',100,'Cadastro inicial 100 cx','cad-produto_A4.env','','2020/01/20','09:26'),
 (13,'10767579000176','A','000002','7890942437466',0,'Troca status por codigo','alt-status_A5.env','C','2020/01/20','09:29'),
 (14,'10767579000176','A','000002','7890942437466',0,'Troca status por ean','alt-status_A6.env','e','2020/01/20','09:30'),
 (15,'10767579000176','E','000002','7890942437466',120,'Entrada de 120','mov-produto_A7.env','C','2020/01/20','09:32'),
 (16,'10767579000176','S','000002','7890942437466',20,'Saida de 20','mov-produto_A8.env','C','2020/01/20','09:33'),
 (17,'10767579000176','I','000001','7890942437466',20,'Saida de 20','mov-produto_A9.env','C','2020/01/20','09:34');
/*!40000 ALTER TABLE `hist_movimentacao` ENABLE KEYS */;


--
-- Definition of table `loja`
--

DROP TABLE IF EXISTS `loja`;
CREATE TABLE `loja` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cnpj` varchar(14) NOT NULL,
  `codigo` varchar(14) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `nome_arquivo` varchar(20) NOT NULL,
  `data_cadastro` varchar(10) NOT NULL,
  `hora_cadastro` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_2` (`cnpj`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loja`
--

/*!40000 ALTER TABLE `loja` DISABLE KEYS */;
INSERT INTO `loja` (`id`,`cnpj`,`codigo`,`nome`,`nome_arquivo`,`data_cadastro`,`hora_cadastro`) VALUES 
 (3,'10767579000176','0001','Show Pisos Material De Construcao Ltda - Me','cad-loja_a2.env','2020/01/20','08:44');
/*!40000 ALTER TABLE `loja` ENABLE KEYS */;


--
-- Definition of table `parametro`
--

DROP TABLE IF EXISTS `parametro`;
CREATE TABLE `parametro` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome_matriz` varchar(45) NOT NULL,
  `diretorio` varchar(45) NOT NULL,
  `tempo_pesquisa` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parametro`
--

/*!40000 ALTER TABLE `parametro` DISABLE KEYS */;
INSERT INTO `parametro` (`id`,`nome_matriz`,`diretorio`,`tempo_pesquisa`) VALUES 
 (1,'Show Piso - Teste','c:\\intx',5);
/*!40000 ALTER TABLE `parametro` ENABLE KEYS */;


--
-- Definition of table `tipo`
--

DROP TABLE IF EXISTS `tipo`;
CREATE TABLE `tipo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `codigo` varchar(1) NOT NULL,
  `descricao` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo`
--

/*!40000 ALTER TABLE `tipo` DISABLE KEYS */;
INSERT INTO `tipo` (`id`,`codigo`,`descricao`) VALUES 
 (1,'E','Entrada'),
 (2,'S','Saída'),
 (3,'I','Inventário'),
 (4,'A','Ativo'),
 (5,'D','Desativado'),
 (6,'C','Cadastrado');
/*!40000 ALTER TABLE `tipo` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
