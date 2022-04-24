-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 20, 2022 at 09:18 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `eg_general`
--
CREATE DATABASE IF NOT EXISTS `eg_general` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `eg_general`;

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `billId` int(11) NOT NULL,
  `accNo` int(11) NOT NULL,
  `totUnits` int(8) NOT NULL,
  `period` date NOT NULL,
  `previousBal` float NOT NULL,
  `calculatedBal` float NOT NULL,
  `totPay` float NOT NULL,
  `totalDue` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`billId`, `accNo`, `totUnits`, `period`, `previousBal`, `calculatedBal`, `totPay`, `totalDue`) VALUES
(1, 199, 320, '2022-04-20', 0, 4000.35, 0, 4000.35),
(3, 199, 320, '2022-05-20', 4000.35, 4000.35, 1000.7, 7000),
(5, 199, 320, '2022-06-21', 7000, 4000.35, 0, 11000.3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`billId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `billId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;
