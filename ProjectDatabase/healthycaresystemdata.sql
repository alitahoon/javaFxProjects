-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 06, 2022 at 03:33 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `healthycaresystemdata`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `username` varchar(50) NOT NULL,
  `pass` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`username`, `pass`) VALUES
('ahmed', '111');

-- --------------------------------------------------------

--
-- Table structure for table `clinics`
--

CREATE TABLE `clinics` (
  `cliID` int(11) NOT NULL,
  `cliName` varchar(50) NOT NULL,
  `cliOpenTime` varchar(50) NOT NULL,
  `cliCloseTime` varchar(50) NOT NULL,
  `cliTicketPrice` float NOT NULL,
  `cliDepID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clinics`
--

INSERT INTO `clinics` (`cliID`, `cliName`, `cliOpenTime`, `cliCloseTime`, `cliTicketPrice`, `cliDepID`) VALUES
(1, 'ref', '8:15 PM', '12:45 AM', 254.2, 1);

-- --------------------------------------------------------

--
-- Stand-in structure for view `clinicsdata`
-- (See below for the actual view)
--
CREATE TABLE `clinicsdata` (
`cliID` int(11)
,`cliName` varchar(50)
,`cliOpenTime` varchar(50)
,`cliCloseTime` varchar(50)
,`cliTicketPrice` float
,`depName` varchar(50)
);

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `depName` varchar(50) NOT NULL,
  `depID` int(11) NOT NULL,
  `depbedsNum` int(11) NOT NULL,
  `depManager` varchar(50) NOT NULL,
  `depAssitantManager` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`depName`, `depID`, `depbedsNum`, `depManager`, `depAssitantManager`) VALUES
('hestology', 1, 900, 'mustafa', ' waleed '),
('gerge', 2, 1000, 'grre', 'egre');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `docID` int(11) NOT NULL,
  `docName` varchar(50) NOT NULL,
  `docBirthdate` varchar(50) NOT NULL,
  `docGender` varchar(30) NOT NULL,
  `docSalary` float NOT NULL,
  `docGrade` varchar(50) NOT NULL,
  `docShiftStart` varchar(50) NOT NULL,
  `docShiftEnd` varchar(50) NOT NULL,
  `docDepID` int(11) NOT NULL,
  `docCliID` int(11) NOT NULL,
  `docAddress` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`docID`, `docName`, `docBirthdate`, `docGender`, `docSalary`, `docGrade`, `docShiftStart`, `docShiftEnd`, `docDepID`, `docCliID`, `docAddress`) VALUES
(1, 'bfgb', ' 2022-02-15', 'Male', 6546, ' fgb', ' 12:30 PM', ' 12:50 AM', 1, 1, ' gfb');

-- --------------------------------------------------------

--
-- Stand-in structure for view `doctordata`
-- (See below for the actual view)
--
CREATE TABLE `doctordata` (
`docName` varchar(50)
,`docID` int(11)
,`docShiftStart` varchar(50)
,`docShiftEnd` varchar(50)
,`docSalary` float
,`docAddress` varchar(50)
,`docGrade` varchar(50)
,`docGender` varchar(30)
,`docBirthdate` varchar(50)
,`depName` varchar(50)
,`cliName` varchar(50)
);

-- --------------------------------------------------------

--
-- Table structure for table `nurses`
--

CREATE TABLE `nurses` (
  `nurID` int(11) NOT NULL,
  `nurName` varchar(50) NOT NULL,
  `nurSalary` float NOT NULL,
  `nurShiftStart` varchar(30) NOT NULL,
  `nurShiftEnd` varchar(30) NOT NULL,
  `nurGender` varchar(40) NOT NULL,
  `nurGrade` varchar(50) NOT NULL,
  `nurBirthDate` varchar(50) NOT NULL,
  `nurAddress` varchar(50) NOT NULL,
  `nurDepID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nurses`
--

INSERT INTO `nurses` (`nurID`, `nurName`, `nurSalary`, `nurShiftStart`, `nurShiftEnd`, `nurGender`, `nurGrade`, `nurBirthDate`, `nurAddress`, `nurDepID`) VALUES
(1, 'gerg', 4568, ' :  ', ' :  ', 'Female', 'greg', '2022-02-17', 'egergerg', 1);

-- --------------------------------------------------------

--
-- Stand-in structure for view `nursesdata`
-- (See below for the actual view)
--
CREATE TABLE `nursesdata` (
`nurID` int(11)
,`nurName` varchar(50)
,`nurSalary` float
,`nurShiftStart` varchar(30)
,`nurShiftEnd` varchar(30)
,`nurGender` varchar(40)
,`nurGrade` varchar(50)
,`nurBirthDate` varchar(50)
,`nurAddress` varchar(50)
,`depName` varchar(50)
);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `pass` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `pass`) VALUES
('ali', '111');

-- --------------------------------------------------------

--
-- Structure for view `clinicsdata`
--
DROP TABLE IF EXISTS `clinicsdata`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `clinicsdata`  AS SELECT `clinics`.`cliID` AS `cliID`, `clinics`.`cliName` AS `cliName`, `clinics`.`cliOpenTime` AS `cliOpenTime`, `clinics`.`cliCloseTime` AS `cliCloseTime`, `clinics`.`cliTicketPrice` AS `cliTicketPrice`, `department`.`depName` AS `depName` FROM (`clinics` join `department`) WHERE `clinics`.`cliDepID` = `department`.`depID` ;

-- --------------------------------------------------------

--
-- Structure for view `doctordata`
--
DROP TABLE IF EXISTS `doctordata`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `doctordata`  AS SELECT `doctor`.`docName` AS `docName`, `doctor`.`docID` AS `docID`, `doctor`.`docShiftStart` AS `docShiftStart`, `doctor`.`docShiftEnd` AS `docShiftEnd`, `doctor`.`docSalary` AS `docSalary`, `doctor`.`docAddress` AS `docAddress`, `doctor`.`docGrade` AS `docGrade`, `doctor`.`docGender` AS `docGender`, `doctor`.`docBirthdate` AS `docBirthdate`, `department`.`depName` AS `depName`, `clinics`.`cliName` AS `cliName` FROM ((`doctor` join `department`) join `clinics`) WHERE `doctor`.`docDepID` = `department`.`depID` AND `doctor`.`docCliID` = `clinics`.`cliID` ;

-- --------------------------------------------------------

--
-- Structure for view `nursesdata`
--
DROP TABLE IF EXISTS `nursesdata`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `nursesdata`  AS SELECT `nurses`.`nurID` AS `nurID`, `nurses`.`nurName` AS `nurName`, `nurses`.`nurSalary` AS `nurSalary`, `nurses`.`nurShiftStart` AS `nurShiftStart`, `nurses`.`nurShiftEnd` AS `nurShiftEnd`, `nurses`.`nurGender` AS `nurGender`, `nurses`.`nurGrade` AS `nurGrade`, `nurses`.`nurBirthDate` AS `nurBirthDate`, `nurses`.`nurAddress` AS `nurAddress`, `department`.`depName` AS `depName` FROM (`nurses` join `department`) WHERE `nurses`.`nurDepID` = `department`.`depID` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `clinics`
--
ALTER TABLE `clinics`
  ADD PRIMARY KEY (`cliID`),
  ADD KEY `cliDepID` (`cliDepID`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`depID`,`depName`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`docID`),
  ADD KEY `docDepID` (`docDepID`),
  ADD KEY `docCliID` (`docCliID`);

--
-- Indexes for table `nurses`
--
ALTER TABLE `nurses`
  ADD PRIMARY KEY (`nurID`),
  ADD KEY `nurDepID` (`nurDepID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `clinics`
--
ALTER TABLE `clinics`
  ADD CONSTRAINT `clinics_ibfk_1` FOREIGN KEY (`cliDepID`) REFERENCES `department` (`depID`);

--
-- Constraints for table `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`docCliID`) REFERENCES `clinics` (`cliID`),
  ADD CONSTRAINT `doctor_ibfk_2` FOREIGN KEY (`docDepID`) REFERENCES `department` (`depID`);

--
-- Constraints for table `nurses`
--
ALTER TABLE `nurses`
  ADD CONSTRAINT `nurses_ibfk_1` FOREIGN KEY (`nurDepID`) REFERENCES `department` (`depID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
