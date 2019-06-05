-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 03, 2017 at 11:05 AM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

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
  `Card_Amount` int(10) NOT NULL,
  `used` int(11) NOT NULL DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `card_account`
--

INSERT INTO `card_account` (`Card_No`, `Card_Amount`, `used`, `deleted`) VALUES
('750060957', 8476, 0, 0),
('972002717', 0, 0, 0);

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
  `finished` int(11) NOT NULL DEFAULT '0',
  `filename` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`event_id`, `name`, `location`, `description`, `startdate`, `enddate`, `starttime`, `endtime`, `deleted`, `finished`, `filename`) VALUES
(1, 'HyperWonderland', 'South road properties', 'It''s time to party!', '2017-08-02', '2017-08-03', '12:59:00', '12:59:00', 0, 1, 'hyperbanner4.png'),
(3, 'Hyper Wangi', 'South road properties', 'The best rave in town!', '2017-08-02', '2017-08-04', '01:00:00', '12:59:00', 0, 0, 'hyperbanner6.png');

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
(1, 'Gold', 500, 4, 2, 0),
(2, 'Silver', 300, 5, 2, 0),
(3, 'Gold', 500, 3, 3, 0),
(4, 'Silver', 300, 5, 3, 0);

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
(1, 0, 1, '9cfa0c07-4eac-4272-b038-88d446fd0a17', 0, 2, 1, NULL),
(2, 0, 0, 'dcd81088-58f3-5d3b-adb4-d2dd14a837a1', 0, 2, 1, NULL),
(3, 0, 0, 'd9653890-1e62-5cc7-a001-fe76ee01f7d9', 0, 2, 1, NULL),
(4, 0, 0, '199470d7-1a2c-534e-80b7-e08346fe401e', 0, 2, 1, NULL),
(5, 0, 0, 'b8588080-f227-5f84-b79b-5ffb4afdf097', 0, 2, 1, NULL),
(6, 0, 0, '8b9e3173-cf10-4414-9f50-3387c9dfb5f0', 0, 2, 2, NULL),
(7, 0, 0, 'c6c07fb2-1e9c-5fc8-a589-a729b747aa2e', 0, 2, 2, NULL),
(8, 0, 0, '6f5762a7-b72d-5a72-80a1-d023118bad28', 0, 2, 2, NULL),
(9, 0, 0, '5a66b6f2-c620-51eb-a32a-838b41f41694', 0, 2, 2, NULL),
(10, 0, 0, 'bcd89109-86eb-5858-a118-a396709fdfba', 0, 2, 2, NULL),
(11, 0, 1, '10fff745-83de-4e00-855b-254212ab31b8', 0, 3, 3, NULL),
(12, 1, 1, '6b04ab64-c917-5145-9c7a-93c22ec61f4d', 0, 3, 3, NULL),
(13, 0, 0, '72581af7-af0e-53ce-b0eb-a06a0e6a5edd', 0, 3, 3, NULL),
(14, 0, 0, 'f37e288e-1376-5c8d-9d96-cc6c36599d51', 0, 3, 3, NULL),
(15, 0, 0, '68b776c8-70f8-50de-9783-b4082c2e1f0a', 0, 3, 3, NULL),
(16, 0, 0, 'e1469dda-bbf6-4d01-ae0f-703870383a32', 0, 3, 4, NULL),
(17, 0, 0, 'db808504-30e4-51ad-bf48-605cfb40708c', 0, 3, 4, NULL),
(18, 0, 0, '8be1909c-6158-538f-b071-525e6331dadb', 0, 3, 4, NULL),
(19, 0, 0, 'b6ea4969-61e8-5b16-a911-7531061315ea', 0, 3, 4, NULL),
(20, 0, 0, '99ceff6e-0e3e-5c70-87e5-47c42fc412bb', 0, 3, 4, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `ticket_purchased_history`
--

CREATE TABLE `ticket_purchased_history` (
  `ticket_history_id` int(11) NOT NULL,
  `ticket_event` varchar(200) NOT NULL,
  `ticket_event_location` varchar(500) NOT NULL,
  `ticket_type` varchar(50) NOT NULL,
  `ticket_price` int(11) NOT NULL,
  `ticket_no` int(11) NOT NULL,
  `ticket_date_of_purchase` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ticket_qrcode` varchar(200) NOT NULL,
  `ticket_hypercard_no` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `user_type` int(7) NOT NULL,
  `card_no` int(11) DEFAULT NULL,
  `deleted` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `firstname`, `lastname`, `username`, `password`, `cpassword`, `user_type`, `card_no`, `deleted`) VALUES
(1, 'admin', 'admin', 'admin', 'admin', 'admin', 1, 972002717, 0),
(2, 'Alex', 'Thunder', 'alexthunder', 'alexthunder', 'alexthunder', 0, 750060957, 0);

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
  MODIFY `event_id` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `ticket_no` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `ticket_details`
--
ALTER TABLE `ticket_details`
  MODIFY `ticket_details_no` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `ticket_purchased_history`
--
ALTER TABLE `ticket_purchased_history`
  MODIFY `ticket_history_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
