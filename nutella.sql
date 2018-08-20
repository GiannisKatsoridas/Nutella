-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 20, 2018 at 11:24 μμ
-- Server version: 5.1.41
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `nutella`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
  `UserID` bigint(10) NOT NULL,
  `PostID` bigint(10) NOT NULL,
  `Text` varchar(50) NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserID`,`PostID`,`Timestamp`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`UserID`, `PostID`, `Text`, `Timestamp`) VALUES
(1000000006, 2000000002, 'Safe travels man!', '2018-08-20 22:40:02'),
(1000000002, 2000000001, 'Nice post man!', '2018-08-20 22:40:41');

-- --------------------------------------------------------

--
-- Table structure for table `education`
--

CREATE TABLE IF NOT EXISTS `education` (
  `UserID` bigint(10) NOT NULL,
  `EducationID` bigint(10) NOT NULL,
  `Degree` varchar(30) NOT NULL,
  `Institution` varchar(30) NOT NULL,
  `YearFrom` year(4) NOT NULL,
  `YearTo` year(4) NOT NULL,
  PRIMARY KEY (`EducationID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `education`
--


-- --------------------------------------------------------

--
-- Table structure for table `experience`
--

CREATE TABLE IF NOT EXISTS `experience` (
  `UserID` bigint(10) NOT NULL,
  `ExperienceID` bigint(10) NOT NULL,
  `CompanyTitle` varchar(30) NOT NULL,
  `Position` varchar(30) DEFAULT NULL,
  `DateFrom` date NOT NULL,
  `DateTo` date NOT NULL,
  PRIMARY KEY (`ExperienceID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `experience`
--


-- --------------------------------------------------------

--
-- Table structure for table `friendrequest`
--

CREATE TABLE IF NOT EXISTS `friendrequest` (
  `Sender` bigint(10) NOT NULL,
  `Receiver` bigint(10) NOT NULL,
  PRIMARY KEY (`Sender`,`Receiver`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `friendrequest`
--


-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE IF NOT EXISTS `friends` (
  `User1` bigint(10) NOT NULL,
  `User2` bigint(10) NOT NULL,
  PRIMARY KEY (`User1`,`User2`)
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

CREATE TABLE IF NOT EXISTS `jobapplications` (
  `Applicant` bigint(10) NOT NULL,
  `Job` bigint(10) NOT NULL,
  PRIMARY KEY (`Applicant`,`Job`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jobapplications`
--


-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

CREATE TABLE IF NOT EXISTS `jobs` (
  `ID` bigint(10) NOT NULL,
  `Creator` bigint(10) NOT NULL,
  `Title` varchar(30) NOT NULL,
  `Description` varchar(200) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jobs`
--

INSERT INTO `jobs` (`ID`, `Creator`, `Title`, `Description`) VALUES
(3000000001, 1000000001, 'Junior Developer', 'Become a junior JAVA Developer at DIT!'),
(3000000002, 1000000005, 'Senior Developer', 'Become a senior PHP Developer at Oracle!');

-- --------------------------------------------------------

--
-- Table structure for table `likes`
--

CREATE TABLE IF NOT EXISTS `likes` (
  `UserID` bigint(10) NOT NULL,
  `PostID` bigint(10) NOT NULL,
  PRIMARY KEY (`UserID`,`PostID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `likes`
--

INSERT INTO `likes` (`UserID`, `PostID`) VALUES
(1000000001, 2000000001),
(1000000002, 2000000002),
(1000000006, 2000000002);

-- --------------------------------------------------------

--
-- Table structure for table `media`
--

CREATE TABLE IF NOT EXISTS `media` (
  `PostID` bigint(10) NOT NULL,
  `Link` varchar(200) NOT NULL,
  PRIMARY KEY (`PostID`,`Link`)
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

CREATE TABLE IF NOT EXISTS `messages` (
  `UserFrom` bigint(10) NOT NULL,
  `UserTo` bigint(10) NOT NULL,
  `Content` varchar(200) NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserFrom`,`UserTo`,`Timestamp`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `messages`
--


-- --------------------------------------------------------

--
-- Table structure for table `pictures`
--

CREATE TABLE IF NOT EXISTS `pictures` (
  `UserID` bigint(10) NOT NULL,
  `Link` varchar(200) NOT NULL,
  PRIMARY KEY (`UserID`,`Link`)
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

CREATE TABLE IF NOT EXISTS `posts` (
  `ID` bigint(10) NOT NULL,
  `UserID` bigint(10) NOT NULL,
  `Text` varchar(200) NOT NULL,
  `Date` date NOT NULL,
  PRIMARY KEY (`ID`)
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

CREATE TABLE IF NOT EXISTS `skills` (
  `UserID` bigint(10) NOT NULL,
  `SkillID` bigint(10) NOT NULL,
  `Skill` varchar(20) NOT NULL,
  PRIMARY KEY (`SkillID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `skills`
--


-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `ID` bigint(10) NOT NULL,
  `FirstName` varchar(30) NOT NULL,
  `LastName` varchar(30) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `IsAdmin` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
