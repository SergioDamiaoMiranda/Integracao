-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.77-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema integracao_bd
--

CREATE DATABASE IF NOT EXISTS integracao_bd;
USE integracao_bd;

--
-- Definition of table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
CREATE TABLE `estoque` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `cnpj_loja` varchar(14) NOT NULL,
  `codigo` varchar(14) NOT NULL,
  `descricao` varchar(100) NOT NULL,
  `saldo` double NOT NULL,
  `ativo` varchar(3) NOT NULL,
  `unidade` varchar(5) NOT NULL,
  `ean` varchar(30) default NULL,
  `data_ult_ent` varchar(10) default NULL,
  `data_ult_sai` varchar(10) default NULL,
  `data_cadastro` varchar(10) default NULL,
  `nome_arquivo` varchar(20) NOT NULL,
  `observacao` varchar(50) default NULL,
  `hora_cadastro` varchar(6) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `Index_2` (`cnpj_loja`,`codigo`),
  UNIQUE KEY `Index_3` USING BTREE (`cnpj_loja`,`ean`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `estoque`
--

/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
INSERT INTO `estoque` (`id`,`cnpj_loja`,`codigo`,`descricao`,`saldo`,`ativo`,`unidade`,`ean`,`data_ult_ent`,`data_ult_sai`,`data_cadastro`,`nome_arquivo`,`observacao`,`hora_cadastro`) VALUES 
 (1,'10767579000176','000001','Refrigerante coca-cola	    ',200,'Sim','Un			','7894900701159',NULL,NULL,'2020/01/16','cad-produto_A2.env','Teste cadastro   ','12:50'),
 (2,'10767579000176','000002','Refrigerante coca-cola	    ',200,'Sim','Un			','7894900701150',NULL,NULL,'2020/01/16','cad-produto_A2.env','Desativado teste chave codigo','12:51'),
 (3,'10767579000176','000003','Refrigerante coca-cola CODIGO 3',-198,'Sim','Un','7894900701154',NULL,NULL,'2020/01/16','cad-produto_A3.env','Desativado teste chave codigo','13:51'),
 (5,'10767579000176','000004','Refrigerante coca-cola CODIGO 4 ean 7894900701144',240,'Sim','Un','7894900701144',NULL,NULL,'2020/01/16','cad-produto_A8.env','Teste cadastro','18:07'),
 (6,'10767579000176','000005','Refrigerante coca-cola CODIGO 5 ean 7894900701145',240,'Sim','Un','7894900701145',NULL,NULL,'2020/01/16','cad-produto_A8.env','Teste cadastro','18:10'),
 (7,'10767579000176','000006','Refrigerante coca-cola CODIGO 6 ean 7894900701166',6,'Sim','Un','7894900701166',NULL,NULL,'2020/01/16','cad-produto_A8.env','Teste cadastro 6','18:15'),
 (8,'10767579000176','000007','Refrigerante coca-cola CODIGO 7 ean 7894900701167',7,'Sim','Un','7894900701167',NULL,NULL,'2020/01/16','cad-produto_A8.env','Teste cadastro 7','18:17'),
 (9,'10767579000176','000008','Refrigerante coca-cola CODIGO 8 ean 7894900701167',8,'Sim','Un','7894900701168',NULL,NULL,'2020/01/16','cad-produto_A8.env','Teste cadastro 8','18:19'),
 (10,'10767579000176','000009','Refrigerante coca-cola CODIGO 9 ean 7894900701169',9,'Sim','Un','7894900701169',NULL,NULL,'2020/01/16','cad-produto_A8.env','Teste cadastro 9','18:21'),
 (11,'10767579000176','000010','Refrigerante coca-cola CODIGO 10 ean 7894900701110',4,'NAO','Un','7894900701110',NULL,NULL,'2020/01/16','cad-produto_A11.env','Teste cadastro 10','18:46');
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;


--
-- Definition of table `hist_arquivos`
--

DROP TABLE IF EXISTS `hist_arquivos`;
CREATE TABLE `hist_arquivos` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `arquivo` varchar(30) NOT NULL,
  `processado` varchar(3) NOT NULL,
  `data_cadastro` varchar(10) default NULL,
  `hora_cadastro` varchar(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hist_arquivos`
--

/*!40000 ALTER TABLE `hist_arquivos` DISABLE KEYS */;
INSERT INTO `hist_arquivos` (`id`,`arquivo`,`processado`,`data_cadastro`,`hora_cadastro`) VALUES 
 (36,'cad-produto_A2.env','Sim','2020/01/16','12:50'),
 (37,'cad-produto_A2.env','NAO','2020/01/16','12:51'),
 (38,'cad-produto_A2.env','Sim','2020/01/16','12:51'),
 (39,'alt-status_A3.env','NAO','2020/01/16','13:29'),
 (40,'alt-status_A3.env','NAO','2020/01/16','13:31'),
 (41,'alt-status_A3.env','NAO','2020/01/16','13:36'),
 (42,'alt-status_A3.env','NAO','2020/01/16','13:41'),
 (43,'alt-status_A3.env','Sim','2020/01/16','13:44'),
 (44,'alt-status_A3.env','Sim','2020/01/16','13:49'),
 (45,'alt-status_A3.env','Sim','2020/01/16','13:50'),
 (46,'cad-produto_A3.env','Sim','2020/01/16','13:51'),
 (47,'alt-status_A5.env','Sim','2020/01/16','13:54'),
 (48,'alt-status_A5.env','Sim','2020/01/16','13:54'),
 (49,'alt-status_A5.env','Sim','2020/01/16','13:55'),
 (50,'alt-status_A5.env','Sim','2020/01/16','13:56'),
 (51,'mov-produto_A7.env','NAO','2020/01/16','15:52'),
 (52,'mov-produto_A7.env','Sim','2020/01/16','16:06'),
 (53,'mov-produto_A7.env','Sim','2020/01/16','16:06'),
 (54,'mov-produto_A7.env','Sim','2020/01/16','16:08'),
 (55,'mov-produto_A7.env','Sim','2020/01/16','16:10'),
 (56,'mov-produto_A7.env','Sim','2020/01/16','16:11'),
 (57,'mov-produto_A7.env','Sim','2020/01/16','16:12'),
 (58,'mov-produto_A7.env','Sim','2020/01/16','16:19'),
 (59,'mov-produto_A7.env','Sim','2020/01/16','16:20'),
 (60,'mov-produto_A7.env','Sim','2020/01/16','16:23'),
 (61,'mov-produto_A7.env','Sim','2020/01/16','16:24'),
 (62,'mov-produto_A7.env','Sim','2020/01/16','16:24'),
 (63,'cad-produto_A8.env','Sim','2020/01/16','18:03'),
 (64,'cad-produto_A8.env','NAO','2020/01/16','18:07'),
 (65,'cad-produto_A8.env','NAO','2020/01/16','18:10'),
 (66,'cad-produto_A8.env','NAO','2020/01/16','18:15'),
 (67,'cad-produto_A8.env','NAO','2020/01/16','18:17'),
 (68,'cad-produto_A8.env','NAO','2020/01/16','18:19'),
 (69,'cad-produto_A8.env','Sim','2020/01/16','18:21'),
 (70,'cad-loja_A10','Sim','2020/01/16','18:44'),
 (71,'cad-produto_A11.env','Sim','2020/01/16','18:46'),
 (72,'alt-status_A15.env','NAO','2020/01/16','18:49'),
 (73,'alt-status_A15.env','Sim','2020/01/16','18:50'),
 (74,'mov-produto_A16.env','Sim','2020/01/16','18:51'),
 (75,'mov-produto_A16.env','Sim','2020/01/16','18:52'),
 (76,'mov-produto_A16.env','Sim','2020/01/16','18:53'),
 (77,'mov-produto_A16.env','Sim','2020/01/16','18:53'),
 (78,'mov-produto_A16.env','Sim','2020/01/16','18:54'),
 (79,'mov-produto_A16.env','Sim','2020/01/16','18:54'),
 (80,'mov-produto_A16.env','Sim','2020/01/16','18:55');
/*!40000 ALTER TABLE `hist_arquivos` ENABLE KEYS */;


--
-- Definition of table `hist_movimentacao`
--

DROP TABLE IF EXISTS `hist_movimentacao`;
CREATE TABLE `hist_movimentacao` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `cnpj_loja` varchar(14) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `codigo` varchar(14) NOT NULL,
  `ean` varchar(30) default NULL,
  `quantidade` double NOT NULL,
  `observacao` varchar(50) default NULL,
  `nome_arquivo` varchar(20) NOT NULL,
  `qual_foi_chave` varchar(6) NOT NULL,
  `data_movimentacao` varchar(10) NOT NULL,
  `hora_movimentacao` varchar(6) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hist_movimentacao`
--

/*!40000 ALTER TABLE `hist_movimentacao` DISABLE KEYS */;
INSERT INTO `hist_movimentacao` (`id`,`cnpj_loja`,`tipo`,`codigo`,`ean`,`quantidade`,`observacao`,`nome_arquivo`,`qual_foi_chave`,`data_movimentacao`,`hora_movimentacao`) VALUES 
 (1,'10767579000176','C','000009','7894900701169',9,'Teste cadastro 9','cad-produto_A8.env','','2020/01/16','18:21'),
 (2,'10767579000176','C','000010','7894900701110',10,'Teste cadastro 10','cad-produto_A11.env','','2020/01/16','18:46'),
 (3,'10767579000176','A','000010','7894900701110',0,'Desativado teste chave codigo','alt-status_A15.env','C','2020/01/16','18:50'),
 (4,'10767579000176','I','000001','7894900701154',200,'','mov-produto_A16.env','E','2020/01/16','18:51'),
 (5,'10767579000176','I','000001','7894900701154',202,'Erro na contagem','mov-produto_A16.env','E','2020/01/16','18:52'),
 (6,'10767579000176','I','000010','7894900701154',202,'Erro na contagem','mov-produto_A16.env','C','2020/01/16','18:53'),
 (7,'10767579000176','E','000010','7894900701154',202,'Erro na contagem','mov-produto_A16.env','C','2020/01/16','18:53'),
 (8,'10767579000176','2','000010','7894900701154',400,'venda 012','mov-produto_A16.env','C','2020/01/16','18:54'),
 (9,'10767579000176','S','000010','7894900701154',400,'venda 012','mov-produto_A16.env','C','2020/01/16','18:54'),
 (10,'10767579000176','S','000010','7894900701154',400,'venda 012','mov-produto_A16.env','E','2020/01/16','18:55');
/*!40000 ALTER TABLE `hist_movimentacao` ENABLE KEYS */;


--
-- Definition of table `hist_produto`
--

DROP TABLE IF EXISTS `hist_produto`;
CREATE TABLE `hist_produto` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `data` varchar(10) NOT NULL,
  `loja` varchar(14) NOT NULL,
  `codigo` varchar(14) NOT NULL,
  `ean` varchar(30) NOT NULL,
  `descricao` varchar(100) NOT NULL,
  `unidade` varchar(5) NOT NULL,
  `quantidade` double(15,4) NOT NULL,
  `observacao` varchar(50) default NULL,
  `operacao` varchar(10) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hist_produto`
--

/*!40000 ALTER TABLE `hist_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `hist_produto` ENABLE KEYS */;


--
-- Definition of table `loja`
--

DROP TABLE IF EXISTS `loja`;
CREATE TABLE `loja` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `cnpj` varchar(14) NOT NULL,
  `codigo` varchar(14) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `nome_arquivo` varchar(20) NOT NULL,
  `data_cadastro` varchar(10) NOT NULL,
  `hora_cadastro` varchar(10) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `Index_2` (`cnpj`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loja`
--

/*!40000 ALTER TABLE `loja` DISABLE KEYS */;
INSERT INTO `loja` (`id`,`cnpj`,`codigo`,`nome`,`nome_arquivo`,`data_cadastro`,`hora_cadastro`) VALUES 
 (3,'10767579000176','0001','Show Pisos Material De Construcao Ltda - Me','cad-loja_A1.env','2020/01/16','11:01'),
 (4,'10767579000172','0002','Show Pisos Material De Construcao Ltda - Me','cad-loja_A10','2020/01/16','18:44');
/*!40000 ALTER TABLE `loja` ENABLE KEYS */;


--
-- Definition of table `parametro`
--

DROP TABLE IF EXISTS `parametro`;
CREATE TABLE `parametro` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `nome_matriz` varchar(45) NOT NULL,
  `diretorio` varchar(45) NOT NULL,
  `tempo_pesquisa` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
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
  `id` int(10) unsigned NOT NULL auto_increment,
  `codigo` varchar(1) NOT NULL,
  `descricao` varchar(15) NOT NULL,
  PRIMARY KEY  (`id`)
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
