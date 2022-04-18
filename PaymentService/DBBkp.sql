-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 18, 2022 at 03:56 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `eg_finance`
--
CREATE DATABASE IF NOT EXISTS `eg_finance` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `eg_finance`;

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `id` int(11) NOT NULL,
  `dtime` datetime NOT NULL,
  `bill_id` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `pay_type` varchar(15) NOT NULL,
  `amount` float NOT NULL,
  `status` varchar(18) NOT NULL DEFAULT 'processing'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`id`, `dtime`, `bill_id`, `user`, `pay_type`, `amount`, `status`) VALUES
(15, '2022-04-18 18:58:12', 101, 1, 'Visa', 10, 'Processing'),
(16, '2022-04-18 19:07:27', 101, 1, 'Visa', 10, 'Processing');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;
