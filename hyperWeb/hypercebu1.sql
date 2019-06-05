-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2017 at 07:54 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hypercebu1`
--

-- --------------------------------------------------------

--
-- Table structure for table `card_account`
--

CREATE TABLE `card_account` (
  `Card_No` varchar(10) NOT NULL,
  `Card_Name` varchar(100) NOT NULL,
  `Card_Amount` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `card_account`
--

INSERT INTO `card_account` (`Card_No`, `Card_Name`, `Card_Amount`) VALUES
('123', 'Alex', 9000),
('345', 'Castaneda', 1000),
('54321', 'Randel', 45000);

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `event_id` int(7) NOT NULL,
  `name` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `startdate` date NOT NULL,
  `enddate` date NOT NULL,
  `starttime` time NOT NULL,
  `endtime` time NOT NULL,
  `deleted` int(7) DEFAULT '0',
  `filename` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`event_id`, `name`, `location`, `description`, `startdate`, `enddate`, `starttime`, `endtime`, `deleted`, `filename`) VALUES
(1, 'hyper', 'srp', 'thesis', '2017-05-14', '2017-05-15', '22:00:00', '04:00:00', 0, 'module_table_bottom.png'),
(3, 'hyperbantayan', 'santafe', 'summer madness', '2017-05-19', '2017-05-20', '22:01:00', '04:00:00', 0, '561768_2963699492804_160826340_n.jpg'),
(4, 'summer', 'srp', 'zzz', '2017-05-18', '2017-05-19', '22:10:00', '04:00:00', 0, '561768_2963699492804_160826340_n.jpg'),
(5, 'hyperalloween', 'sm cebu', 'aaaa', '2017-05-24', '2017-05-25', '22:00:00', '04:00:00', 0, '561768_2963699492804_160826340_n.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `ticket_no` int(7) NOT NULL,
  `type` varchar(255) NOT NULL,
  `price` int(7) NOT NULL,
  `quantity` int(7) NOT NULL,
  `event_id` int(7) NOT NULL,
  `deleted` int(7) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket`
--

INSERT INTO `ticket` (`ticket_no`, `type`, `price`, `quantity`, `event_id`, `deleted`) VALUES
(5, 'Gold', 2000, 3, 3, 0),
(6, 'silver', 500, 5, 1, 0),
(7, 'Gold', 1000, 5, 1, 0),
(8, 'Vip', 500, 5, 4, 0),
(9, 'silver', 100, 6, 5, 0);

-- --------------------------------------------------------

--
-- Table structure for table `ticket_details`
--

CREATE TABLE `ticket_details` (
  `ticket_details_no` int(7) NOT NULL,
  `status` int(7) DEFAULT '0',
  `purchased` int(7) DEFAULT '0',
  `qr_code` varchar(255) NOT NULL,
  `deleted` int(7) NOT NULL DEFAULT '0',
  `event_id` int(7) NOT NULL,
  `ticket_no` int(7) NOT NULL,
  `user_id` int(7) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket_details`
--

INSERT INTO `ticket_details` (`ticket_details_no`, `status`, `purchased`, `qr_code`, `deleted`, `event_id`, `ticket_no`, `user_id`) VALUES
(1, 0, 1, '732f2eb1-92f2-407e-87b4-7032fa3243a4', 0, 3, 5, NULL),
(2, 1, 1, '34e06e14-e271-5d9a-839d-880c1b789228', 0, 3, 5, NULL),
(3, 0, 0, 'c13cb4bf-cc2d-58aa-b9c2-55eaf6da98e7', 0, 3, 5, NULL),
(4, 0, 0, '2d12194d-457b-583e-b1a7-e7421d5434a4', 0, 3, 5, NULL),
(5, 0, 0, '0d86e781-1612-5ff3-8861-9109d159d19b', 0, 3, 5, NULL),
(6, 0, 0, 'ee341c6b-9375-4e9f-9676-405823ad8a87', 0, 1, 6, NULL),
(7, 0, 0, '7cc8603b-65e2-5260-bd1e-4305cf6cd0ec', 0, 1, 6, NULL),
(8, 0, 0, '8ddce94e-997b-56ac-9197-3f9e9cadc340', 0, 1, 6, NULL),
(9, 0, 0, '82ea0256-2386-55fe-8469-d12e3c5eb0b7', 0, 1, 6, NULL),
(10, 0, 0, '02ba0b7c-cf3b-551f-9ee2-52792d6703a3', 0, 1, 6, NULL),
(11, 0, 0, '298f919d-c4bc-493c-8d77-5e28b6934204', 0, 1, 7, NULL),
(12, 0, 0, '3b059a18-0b56-5008-960d-7e7c5f33c820', 0, 1, 7, NULL),
(13, 0, 0, '44bc8f13-f133-5217-88dc-ad2914ee874c', 0, 1, 7, NULL),
(14, 0, 0, '19a79ed8-027e-5e83-988b-42dea7a6ab9e', 0, 1, 7, NULL),
(15, 0, 0, 'c1c69951-06c1-5049-b5b4-1ad69d79bb0b', 0, 1, 7, NULL),
(16, 0, 0, '910e29ad-f8a3-5376-bef6-1ec5ca0c758a', 0, 1, 7, NULL),
(17, 0, 0, '290f3dcd-105c-56a5-a8f2-d2818e88d002', 0, 1, 7, NULL),
(18, 0, 0, '875ad1de-a502-5fb4-9baa-632397384cd6', 0, 1, 7, NULL),
(19, 0, 0, 'ab147fd9-4677-5017-9223-da737047a5e7', 0, 1, 7, NULL),
(20, 0, 0, '1c177841-1004-554b-b88b-915bbe995287', 0, 1, 7, NULL),
(21, 0, 0, '546d5926-bcca-4de7-a86b-5d2ad8be0565', 0, 1, 7, NULL),
(22, 0, 0, '1e95ac66-00a1-580f-b71e-f8e3ca3e7ccd', 0, 1, 7, NULL),
(23, 0, 0, '0bb3cf5e-4071-5b52-80a4-d82fe005c43a', 0, 1, 7, NULL),
(24, 0, 0, 'fe32d228-2615-54b0-83c7-ba6bed9ac701', 0, 1, 7, NULL),
(25, 0, 0, '3646240c-8621-5dad-a9f6-471fc249fc88', 0, 1, 7, NULL),
(26, 0, 0, '54f48329-7c6c-413d-8193-10dc6e57d58b', 0, 4, 8, NULL),
(27, 0, 0, '00d5f09f-afe0-5533-93c1-15d2c905dd9e', 0, 4, 8, NULL),
(28, 0, 0, '460f134e-641b-5d52-bfdc-0f33ab439546', 0, 4, 8, NULL),
(29, 0, 0, '75375472-bb83-5d61-95d2-6ad88dd82962', 0, 4, 8, NULL),
(30, 0, 0, '7de6963e-234a-550d-83fc-ce0ee2b17b5b', 0, 4, 8, NULL),
(31, 0, 0, 'd9e1e979-54ae-447a-a1c9-72f1c10d579b', 0, 5, 9, NULL),
(32, 0, 0, 'fc5eb016-a192-56eb-8606-2e90d5e4e51f', 0, 5, 9, NULL),
(33, 0, 0, 'afd5ed25-4d7f-56d2-8c62-97dccb58c8f6', 0, 5, 9, NULL),
(34, 0, 0, 'fb101295-ef11-5629-94a0-aa833072de39', 0, 5, 9, NULL),
(35, 0, 0, '378214d8-77d6-5cb9-9e33-fa60ddb47a79', 0, 5, 9, NULL),
(36, 0, 0, '17fa2704-aa1f-5ff1-91e8-700e51771da6', 0, 5, 9, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `ticket_purchased_history`
--

CREATE TABLE `ticket_purchased_history` (
  `ticket_history_id` int(11) NOT NULL,
  `event` varchar(200) NOT NULL,
  `ticket_type` varchar(50) NOT NULL,
  `ticket_price` int(10) NOT NULL,
  `ticket_qrcode` varchar(200) NOT NULL,
  `ticket_hypercard_no` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket_purchased_history`
--

INSERT INTO `ticket_purchased_history` (`ticket_history_id`, `event`, `ticket_type`, `ticket_price`, `ticket_qrcode`, `ticket_hypercard_no`) VALUES
(1, 'hyper', 'vip', 500, '26a64e8e-166e-46ee-b050-fdcb9e3e00a9', '123'),
(3, 'hyperbantayan', 'Gold', 2000, '732f2eb1-92f2-407e-87b4-7032fa3243a4', '345'),
(4, 'hyperbantayan', 'Gold', 2000, '34e06e14-e271-5d9a-839d-880c1b789228', '345');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(7) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `cpassword` varchar(255) NOT NULL,
  `user_type` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `firstname`, `lastname`, `username`, `password`, `cpassword`, `user_type`) VALUES
(1, 'admin', 'admin', 'admin', 'admin', 'admin', 1),
(5, 'alfox', 'alforqye', 'alfox1', '123456', '123456', 0),
(6, 'christian', 'maderazo', 'christian', 'madz12', 'madz12', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `card_account`
--
ALTER TABLE `card_account`
  ADD PRIMARY KEY (`Card_No`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`event_id`);

--
-- Indexes for table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`ticket_no`);

--
-- Indexes for table `ticket_details`
--
ALTER TABLE `ticket_details`
  ADD PRIMARY KEY (`ticket_details_no`);

--
-- Indexes for table `ticket_purchased_history`
--
ALTER TABLE `ticket_purchased_history`
  ADD PRIMARY KEY (`ticket_history_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `event_id` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `ticket_no` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `ticket_details`
--
ALTER TABLE `ticket_details`
  MODIFY `ticket_details_no` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
--
-- AUTO_INCREMENT for table `ticket_purchased_history`
--
ALTER TABLE `ticket_purchased_history`
  MODIFY `ticket_history_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
