-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2022 at 11:51 AM
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
(3, 199, 320, '2022-05-20', 4000.35, 4000.35, 6600, 1400.7),
(5, 199, 320, '2022-06-21', 7000, 4000.35, 1700, 9300.3);

-- --------------------------------------------------------

--
-- Table structure for table `complaints`
--

CREATE TABLE `complaints` (
  `compId` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `title` varchar(15) NOT NULL,
  `description` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `complaints`
--

INSERT INTO `complaints` (`compId`, `user`, `date`, `title`, `description`) VALUES
(1, 12, '2022-04-25 16:38:20', 'Power Failure', '*updated description-4* '),
(2, 19, '2022-04-24 16:49:58', 'Electricity Int', 'Suddenly, there was no electricity'),
(3, 17, '2022-04-24 16:51:24', 'Electricity Fal', 'Suddenly, there was a power interruption.'),
(5, 99, '2022-04-24 16:53:10', 'Test title', 'test data!'),
(6, 97, '2022-04-24 16:59:48', 'Test title 2', 'test data 2!'),
(7, 90, '2022-04-24 16:59:26', 'Test title 3', 'test data 3!');

-- --------------------------------------------------------

--
-- Table structure for table `consumption`
--

CREATE TABLE `consumption` (
  `consumptionId` int(11) NOT NULL,
  `accNo` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `units` int(11) DEFAULT NULL,
  `calculatedBal` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `consumption`
--

INSERT INTO `consumption` (`consumptionId`, `accNo`, `year`, `month`, `units`, `calculatedBal`) VALUES
(1233, 49927993, 2021, 12, 78, 975.84),
(1243, 652349934, 2021, 12, 89, 1210.47),
(12332, 49927993, 2022, 1, 67, 741.21),
(12335, 652349934, 2022, 1, 90, 1231.8),
(43233, 49927993, 2022, 2, 59, 577.38),
(43332, 652349934, 2022, 2, 58, 562.86),
(144342, 252366844, 2022, 3, 58, 562.86);

-- --------------------------------------------------------

--
-- Table structure for table `domesticrates`
--

CREATE TABLE `domesticrates` (
  `rateId` int(11) NOT NULL,
  `year` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `c0_30` float DEFAULT NULL,
  `c31_60` float DEFAULT NULL,
  `c61_90` float DEFAULT NULL,
  `c91_120` float DEFAULT NULL,
  `c121` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `domesticrates`
--

INSERT INTO `domesticrates` (`rateId`, `year`, `month`, `c0_30`, `c31_60`, `c61_90`, `c91_120`, `c121`) VALUES
(1, 2021, 3, 5.21, 14.52, 21.33, 34.12, 42.21),
(425, 2021, 5, 7.9, 12.23, 22.45, 33.9, 45.76),
(12122, 2021, 6, 7.9, 12.23, 22.45, 33.9, 45.76);

-- --------------------------------------------------------

--
-- Table structure for table `industrialrates`
--

CREATE TABLE `industrialrates` (
  `rateId` int(11) NOT NULL,
  `year` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `c0_300` float DEFAULT NULL,
  `c301_600` float DEFAULT NULL,
  `c601` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `supply_connection`
--

CREATE TABLE `supply_connection` (
  `accNo` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `area` varchar(30) DEFAULT NULL,
  `type` varchar(3) DEFAULT NULL,
  `connection_status` varchar(5) DEFAULT 'UP',
  `timestamp` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `supply_connection`
--

INSERT INTO `supply_connection` (`accNo`, `name`, `address`, `area`, `type`, `connection_status`, `timestamp`) VALUES
(10023, 'Esala Jay', '12,troad,kandy', 'kandy', 'STD', 'UP', '2022-04-19 15:49:21'),
(10024, 'Randy Bay', '12,Stout Road,Colombo', 'Colombo', 'STD', 'UP', '2022-04-19 16:32:03'),
(10025, 'Barry Coleman', '14,Stout Road,Colombo', 'Colombo', 'STD', 'DOWN', '2022-04-19 16:35:04'),
(10026, 'Harry Coleman', '17,Main Road,Kegalle', 'Kegalle', 'STD', 'UP', '2022-04-19 16:49:54'),
(10027, 'Ron Weasly', '17,Main Road,Jaffna', 'Jaffna', 'STD', 'UP', '2022-04-19 16:51:45'),
(10028, 'Dan Brown', '17,Pachchakatu Road,Kandy', 'Kandy', 'STD', 'DOWN', '2022-04-19 17:06:59'),
(10029, 'Jerrt Filander', '18,Tribrois Road,Kandy', 'Kandy', 'STD', 'UP', '2022-04-26 11:47:25'),
(10030, 'Saul Goodman', '18,JRBay Road,Mathale', 'Mathale', 'STD', 'UP', '2022-04-26 11:32:55');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userId` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `phone_no` int(9) NOT NULL,
  `accNo` int(11) NOT NULL,
  `nic` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userId`, `username`, `email`, `password`, `phone_no`, `accNo`, `nic`) VALUES
(1, 'esala', 'esala@localhost.com', 'talker124', 712343457, 10023, '993452763V'),
(3, 'randy', 'randy@localhost.com', 'randy123', 712346756, 10024, '993462863V'),
(4, 'thomas', 'thomas@localhost.com', 'thomas123', 712376756, 10025, '993461863V'),
(5, 'Sergio', 'sergio@localhost.com', 'sergio123', 711376756, 10026, '993461853V');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`billId`);

--
-- Indexes for table `complaints`
--
ALTER TABLE `complaints`
  ADD PRIMARY KEY (`compId`);

--
-- Indexes for table `consumption`
--
ALTER TABLE `consumption`
  ADD PRIMARY KEY (`consumptionId`);

--
-- Indexes for table `domesticrates`
--
ALTER TABLE `domesticrates`
  ADD PRIMARY KEY (`rateId`);

--
-- Indexes for table `industrialrates`
--
ALTER TABLE `industrialrates`
  ADD PRIMARY KEY (`rateId`);

--
-- Indexes for table `supply_connection`
--
ALTER TABLE `supply_connection`
  ADD PRIMARY KEY (`accNo`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `billId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `complaints`
--
ALTER TABLE `complaints`
  MODIFY `compId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `domesticrates`
--
ALTER TABLE `domesticrates`
  MODIFY `rateId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12123;
COMMIT;
