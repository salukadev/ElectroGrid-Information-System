-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2022 at 12:25 PM
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
(16, '2022-04-18 19:07:27', 101, 1, 'Visa', 10, 'Processing'),
(17, '2022-04-18 21:53:39', 999, 444, 'MasterCard', 999.99, 'Processing'),
(18, '2022-04-18 22:02:12', 999, 444, 'MasterCard', 999.99, 'Processing'),
(19, '2022-04-19 00:11:29', 999, 444, 'MasterCard', 999.99, 'Processing'),
(20, '2022-04-19 00:16:05', 999, 444, 'MasterCard', 999.99, 'Processing'),
(21, '2022-04-19 00:17:14', 999, 444, 'MasterCard', 999.99, 'Done'),
(28, '2022-04-23 20:43:57', 999, 444, 'MasterCard', 999.99, 'Processing'),
(29, '2022-04-23 22:12:45', 999, 444, 'MasterCard', 999.99, 'Processing'),
(30, '2022-04-23 22:15:06', 999, 444, 'MasterCard', 999.99, 'Processing'),
(31, '2022-04-23 22:16:35', 999, 444, 'MasterCard', 999.99, 'Processing'),
(32, '2022-04-23 22:18:50', 999, 444, 'MasterCard', 999.99, 'Processing'),
(33, '2022-04-23 22:21:10', 999, 444, 'MasterCard', 999.99, 'Processing'),
(34, '2022-04-23 22:24:28', 999, 444, 'MasterCard', 999.99, 'Processing'),
(35, '2022-04-23 22:28:04', 999, 444, 'MasterCard', 999.99, 'Processing'),
(36, '2022-04-23 22:28:47', 3, 444, 'MasterCard', 97.9, 'Processing'),
(37, '2022-04-24 14:59:50', 5, 111, 'MasterCard', 200, 'Done');

-- --------------------------------------------------------

--
-- Table structure for table `recurring_pay`
--

CREATE TABLE `recurring_pay` (
  `id` int(11) NOT NULL,
  `payId` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `bill_account` float NOT NULL,
  `until` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `recurring_pay`
--

INSERT INTO `recurring_pay` (`id`, `payId`, `amount`, `user`, `bill_account`, `until`) VALUES
(2, 789, 1100, 444, 101, '2022-12-25');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `recurring_pay`
--
ALTER TABLE `recurring_pay`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `recurring_pay`
--
ALTER TABLE `recurring_pay`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;
