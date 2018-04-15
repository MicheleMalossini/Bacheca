-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 05, 2018 at 01:28 PM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bacheca`
--

-- --------------------------------------------------------

--
-- Table structure for table `commento`
--

CREATE DATABASE bacheca;
USE bacheca;

CREATE TABLE `commento` (
  `id` int(11) NOT NULL,
  `contenuto` text NOT NULL,
  `date_time` date NOT NULL,
  `n_like` int(11) NOT NULL,
  `id_m` int(11) NOT NULL,
  `cod_utente` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `commento`
--

INSERT INTO `commento` (`id`, `contenuto`, `date_time`, `n_like`, `id_m`, `cod_utente`) VALUES
(1, 'Brao belo', '2048-04-06', 0, 1, 'aa001aa'),
(2, 'Questa bacheca fa schifo', '2018-04-22', 0, 2, 'aa000aa');

-- --------------------------------------------------------

--
-- Table structure for table `messaggio`
--

CREATE TABLE `messaggio` (
  `id` int(11) NOT NULL,
  `contenuto` text NOT NULL,
  `date_time` date NOT NULL,
  `n_like` int(11) NOT NULL,
  `cod_utente` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `messaggio`
--

INSERT INTO `messaggio` (`id`, `contenuto`, `date_time`, `n_like`, `cod_utente`) VALUES
(1, 'Ciao mi sono registrato', '2018-04-05', 0, 'aa000aa'),
(2, 'Mi sono registrato anche io ', '2048-04-18', 0, 'aa001aa');

-- --------------------------------------------------------

--
-- Table structure for table `utente`
--

CREATE TABLE `utente` (
  `cod_alfanum` varchar(20) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `utente`
--

INSERT INTO `utente` (`cod_alfanum`, `nome`, `cognome`) VALUES
('aa000aa', 'Federico', 'Sandrinelli'),
('aa001aa', 'Michele', 'Malossini');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `commento`
--
ALTER TABLE `commento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cod_utente` (`cod_utente`),
  ADD KEY `id_m` (`id_m`);

--
-- Indexes for table `messaggio`
--
ALTER TABLE `messaggio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cod_utente` (`cod_utente`);

--
-- Indexes for table `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`cod_alfanum`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `commento`
--
ALTER TABLE `commento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `messaggio`
--
ALTER TABLE `messaggio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `commento`
--
ALTER TABLE `commento`
  ADD CONSTRAINT `commento_ibfk_1` FOREIGN KEY (`cod_utente`) REFERENCES `utente` (`cod_alfanum`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `commento_ibfk_2` FOREIGN KEY (`id_m`) REFERENCES `messaggio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `messaggio`
--
ALTER TABLE `messaggio`
  ADD CONSTRAINT `messaggio_ibfk_1` FOREIGN KEY (`cod_utente`) REFERENCES `utente` (`cod_alfanum`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
