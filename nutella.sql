-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 21, 2018 at 03:40 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nutella`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `UserID` bigint(10) NOT NULL,
  `PostID` bigint(10) NOT NULL,
  `Text` varchar(50) NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`UserID`, `PostID`, `Text`, `Timestamp`) VALUES
(1000000006, 2000000002, 'Safe travels man!', '2018-08-20 19:40:02'),
(1000000002, 2000000001, 'Nice post man!', '2018-08-20 19:40:41'),
(1000000007, 2000000001, 'We don\'t care!', '2018-08-21 13:19:10');

-- --------------------------------------------------------

--
-- Table structure for table `education`
--

CREATE TABLE `education` (
  `UserID` bigint(10) NOT NULL,
  `EducationID` bigint(10) NOT NULL,
  `Degree` varchar(30) NOT NULL,
  `Institution` varchar(30) NOT NULL,
  `YearFrom` year(4) NOT NULL,
  `YearTo` year(4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `experience`
--

CREATE TABLE `experience` (
  `UserID` bigint(10) NOT NULL,
  `ExperienceID` bigint(10) NOT NULL,
  `CompanyTitle` varchar(30) NOT NULL,
  `Position` varchar(30) DEFAULT NULL,
  `DateFrom` date NOT NULL,
  `DateTo` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `friendrequest`
--

CREATE TABLE `friendrequest` (
  `Sender` bigint(10) NOT NULL,
  `Receiver` bigint(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `friendrequest`
--

INSERT INTO `friendrequest` (`Sender`, `Receiver`) VALUES
(1000000007, 1000000001),
(1000000007, 1000000002),
(1000000007, 1000000003),
(1000000007, 1000000004);

-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE `friends` (
  `User1` bigint(10) NOT NULL,
  `User2` bigint(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `friends`
--

INSERT INTO `friends` (`User1`, `User2`) VALUES
(1000000002, 1000000006),
(1000000004, 1000000006),
(1000000006, 1000000001),
(1000000006, 1000000003),
(1000000006, 1000000005);

-- --------------------------------------------------------

--
-- Table structure for table `jobapplications`
--

CREATE TABLE `jobapplications` (
  `Applicant` bigint(10) NOT NULL,
  `Job` bigint(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jobapplications`
--

INSERT INTO `jobapplications` (`Applicant`, `Job`) VALUES
(1000000003, 3000000003),
(1000000007, 3000000001),
(1000000007, 3000000002),
(1000000007, 3000000003);

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

CREATE TABLE `jobs` (
  `ID` bigint(10) NOT NULL,
  `Creator` bigint(10) NOT NULL,
  `Title` varchar(30) NOT NULL,
  `Description` varchar(200) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jobs`
--

INSERT INTO `jobs` (`ID`, `Creator`, `Title`, `Description`) VALUES
(3000000001, 1000000001, 'Junior Developer', 'Become a junior JAVA Developer at DIT!'),
(3000000002, 1000000005, 'Senior Developer', 'Become a senior PHP Developer at Oracle!'),
(3000000003, 1000000006, 'FrontEnd Developer', 'Become a part time front end developer at Wings!');

-- --------------------------------------------------------

--
-- Table structure for table `likes`
--

CREATE TABLE `likes` (
  `UserID` bigint(10) NOT NULL,
  `PostID` bigint(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `likes`
--

INSERT INTO `likes` (`UserID`, `PostID`) VALUES
(1000000001, 2000000001),
(1000000001, 2000000002),
(1000000002, 2000000002),
(1000000006, 2000000002),
(1000000007, 2000000001);

-- --------------------------------------------------------

--
-- Table structure for table `media`
--

CREATE TABLE `media` (
  `PostID` bigint(10) NOT NULL,
  `Link` varchar(200) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `media`
--

INSERT INTO `media` (`PostID`, `Link`) VALUES
(2000000001, 'IphonePic1'),
(2000000001, 'IphonePic2'),
(2000000002, 'FerrariPic1'),
(2000000002, 'FiatPic1');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `UserFrom` bigint(10) NOT NULL,
  `UserTo` bigint(10) NOT NULL,
  `Content` varchar(200) NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `ID` bigint(10) NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Category` tinyint(1) NOT NULL,
  `UserFrom` bigint(10) NOT NULL,
  `UserTo` bigint(10) NOT NULL,
  `Post` bigint(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`ID`, `Timestamp`, `Category`, `UserFrom`, `UserTo`, `Post`) VALUES
(4000000001, '2018-08-21 13:09:45', 1, 1000000007, 1000000004, 2000000001),
(4000000002, '2018-08-21 13:19:11', 2, 1000000007, 1000000004, 2000000001),
(4000000003, '2018-08-21 13:29:22', 3, 1000000007, 1000000001, 0),
(4000000004, '2018-08-21 13:38:45', 1, 1000000001, 1000000004, 2000000002);

-- --------------------------------------------------------

--
-- Table structure for table `pictures`
--

CREATE TABLE `pictures` (
  `UserID` bigint(10) NOT NULL,
  `Link` varchar(200) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pictures`
--

INSERT INTO `pictures` (`UserID`, `Link`) VALUES
(1000000001, 'IoannisKatsoridasImage'),
(1000000002, 'LeonidasPapanastasiouImage'),
(1000000003, 'LittleGeorgeImage'),
(1000000004, 'FirstTesterImage'),
(1000000005, 'EvitaMintzaPicture'),
(1000000006, 'popularImage'),
(1000000007, 'unpopularImage');

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `ID` bigint(10) NOT NULL,
  `UserID` bigint(10) NOT NULL,
  `Text` varchar(200) NOT NULL,
  `Date` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`ID`, `UserID`, `Text`, `Date`) VALUES
(2000000001, 1000000004, 'Just got a new phone LOL!', '2018-08-20'),
(2000000002, 1000000004, 'Just got a new ride LOL!', '2018-08-20');

-- --------------------------------------------------------

--
-- Table structure for table `skills`
--

CREATE TABLE `skills` (
  `UserID` bigint(10) NOT NULL,
  `SkillID` bigint(10) NOT NULL,
  `Skill` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `ID` bigint(10) NOT NULL,
  `FirstName` varchar(30) NOT NULL,
  `LastName` varchar(30) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `IsAdmin` tinyint(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`ID`, `FirstName`, `LastName`, `Email`, `Password`, `Phone`, `IsAdmin`) VALUES
(1000000001, 'Ioannis', 'Katsoridas', 'i.katsoridas@gmail.com', 'myp@ss', '6948114717', 1),
(1000000003, 'Little', 'George', 'littlegeorge@gmail.com', 'l1ttlegeorge', '6945632148', 0),
(1000000002, 'Leonidas', 'Papanastasiou', 'leopap25@gmail.com', 'mySecondPass', 'DontRemember', 0),
(1000000004, 'First', 'Tester', 'tester@gmail.com', 'testerpass', '6944444444', 0),
(1000000005, 'Evita', 'Mintza', 'evita@gmail.com', 'evitaPass', '6955555555', 0),
(1000000006, 'Mr', 'Popular', 'popular@gmail.com', 'popularPass', 'EverybodyKnows', 0),
(1000000007, 'Mr', 'unPopular', 'unpopular@gmail.com', 'unpopularPass', '6912365478', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`UserID`,`PostID`,`Timestamp`);

--
-- Indexes for table `education`
--
ALTER TABLE `education`
  ADD PRIMARY KEY (`EducationID`);

--
-- Indexes for table `experience`
--
ALTER TABLE `experience`
  ADD PRIMARY KEY (`ExperienceID`);

--
-- Indexes for table `friendrequest`
--
ALTER TABLE `friendrequest`
  ADD PRIMARY KEY (`Sender`,`Receiver`);

--
-- Indexes for table `friends`
--
ALTER TABLE `friends`
  ADD PRIMARY KEY (`User1`,`User2`);

--
-- Indexes for table `jobapplications`
--
ALTER TABLE `jobapplications`
  ADD PRIMARY KEY (`Applicant`,`Job`);

--
-- Indexes for table `jobs`
--
ALTER TABLE `jobs`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`UserID`,`PostID`);

--
-- Indexes for table `media`
--
ALTER TABLE `media`
  ADD PRIMARY KEY (`PostID`,`Link`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`UserFrom`,`UserTo`,`Timestamp`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `pictures`
--
ALTER TABLE `pictures`
  ADD PRIMARY KEY (`UserID`,`Link`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `skills`
--
ALTER TABLE `skills`
  ADD PRIMARY KEY (`SkillID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
