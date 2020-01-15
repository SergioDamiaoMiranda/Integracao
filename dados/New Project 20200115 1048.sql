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
-- Create schema estuni
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
  `dt_ult_ent` varchar(10) default NULL,
  `dt_ult_sai` varchar(10) default NULL,
  `dt_cadastro` varchar(10) default NULL,
  `nome_arquivo` varchar(20) NOT NULL,
  `observacao` varchar(50) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `Index_2` (`cnpj_loja`,`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `estoque`
--

/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;


--
-- Definition of table `hist_movimentacao`
--

DROP TABLE IF EXISTS `hist_movimentacao`;
CREATE TABLE `hist_movimentacao` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `data` varchar(10) NOT NULL,
  `cnpj_loja` varchar(14) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `codigo` varchar(14) NOT NULL,
  `ean` varchar(30) default NULL,
  `qtd` double NOT NULL,
  `observacao` varchar(50) default NULL,
  `nome_arquivo` varchar(20) NOT NULL,
  `qual_foi_chave` varchar(6) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hist_movimentacao`
--

/*!40000 ALTER TABLE `hist_movimentacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `hist_movimentacao` ENABLE KEYS */;


--
-- Definition of table `hist_processamento`
--

DROP TABLE IF EXISTS `hist_processamento`;
CREATE TABLE `hist_processamento` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `arquivo` varchar(30) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hist_processamento`
--

/*!40000 ALTER TABLE `hist_processamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `hist_processamento` ENABLE KEYS */;


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
  `razao` varchar(50) NOT NULL,
  `nome_arquivo` varchar(20) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `Index_2` (`cnpj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loja`
--

/*!40000 ALTER TABLE `loja` DISABLE KEYS */;
/*!40000 ALTER TABLE `loja` ENABLE KEYS */;


--
-- Definition of table `parametro`
--

DROP TABLE IF EXISTS `parametro`;
CREATE TABLE `parametro` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `nome_matriz` varchar(45) NOT NULL,
  `diretorio` varchar(45) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parametro`
--

/*!40000 ALTER TABLE `parametro` DISABLE KEYS */;
INSERT INTO `parametro` (`id`,`nome_matriz`,`diretorio`) VALUES 
 (1,'Show Piso - Teste','c:\\integracao');
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
