-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 10, 2017 at 10:21 AM
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
-- Table structure for table `card_member`
--

CREATE TABLE `card_member` (
  `card_id` int(11) NOT NULL,
  `card_no` int(10) NOT NULL,
  `card_name` varchar(100) NOT NULL,
  `card_amount` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `card_member`
--

INSERT INTO `card_member` (`card_id`, `card_no`, `card_name`, `card_amount`) VALUES
(1, 12345, 'Charles', 1000000);

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
  `deleted` int(7) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`event_id`, `name`, `location`, `description`, `startdate`, `enddate`, `starttime`, `endtime`, `deleted`) VALUES
(3, 'Sinulog', 'Fuente Osmena', 'Party and Drink', '2017-04-20', '2017-04-22', '00:12:00', '00:12:00', 0),
(4, 'Compendium', 'Dota 2 Steam', 'Play till die', '2017-05-08', '2017-05-29', '01:00:00', '13:00:00', 0);

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
(1, 'ga', 500, 5, 3, 0),
(2, 'VIP', 5000, 5, 4, 0);

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
(1, 0, 0, 'ab6cb7f5-76a7-46e0-9c57-7e613b4207ce', 0, 3, 1, NULL),
(2, 0, 0, '50bb1d4c-79eb-5b5e-bcb8-af0791cc5c35', 0, 3, 1, NULL),
(3, 0, 0, 'b15e15c5-6efc-5829-ae99-3c33f7661abf', 0, 3, 1, NULL),
(4, 0, 0, '9bd0d1f5-c60a-5b07-a6e1-4a2e6101de62', 0, 3, 1, NULL),
(5, 0, 0, '2d9e6406-98d4-5ed2-990c-f07bd4212aad', 0, 3, 1, NULL),
(6, 0, 0, '19bad013-fba6-4521-ad75-29a2001558f4', 0, 4, 2, NULL),
(7, 0, 0, 'eb587f6d-b536-5615-a787-6100d969ac84', 0, 4, 2, NULL),
(8, 0, 0, 'e5639c48-ff2a-501e-a117-6378e5a00d5c', 0, 4, 2, NULL),
(9, 0, 0, '181767a4-73bb-5971-944f-47a4e1ac5410', 0, 4, 2, NULL),
(10, 0, 0, '6a20608b-76c8-5413-94ca-015aa2a4d45e', 0, 4, 2, NULL);

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
(2, 'alex', 'alex', 'alex', 'alex', 'alex', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `card_member`
--
ALTER TABLE `card_member`
  ADD PRIMARY KEY (`card_id`);

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
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `card_member`
--
ALTER TABLE `card_member`
  MODIFY `card_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `event_id` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `ticket_no` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `ticket_details`
--
ALTER TABLE `ticket_details`
  MODIFY `ticket_details_no` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
