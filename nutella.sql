-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 27, 2018 at 09:21 μμ
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
(1000000006, 2000000002, 'Safe travels man!', '2018-08-20 22:39:38'),
(1000000002, 2000000001, 'Nice post man!', '2018-08-20 22:40:17'),
(1000000007, 2000000001, 'We don''t care!', '2018-08-21 16:18:46');

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

INSERT INTO `education` (`UserID`, `EducationID`, `Degree`, `Institution`, `YearFrom`, `YearTo`) VALUES
(1000000001, 6000000001, 'Bachelor', 'DI', 2014, 2019),
(1000000001, 6000000002, 'Minor', 'Deree', 2014, 2017);

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

INSERT INTO `experience` (`UserID`, `ExperienceID`, `CompanyTitle`, `Position`, `DateFrom`, `DateTo`) VALUES
(1000000002, 5000000001, 'Apple', 'Intern', '2017-01-23', '2018-01-23'),
(1000000002, 5000000002, 'Apple', 'Junior Developer', '2018-01-23', '2018-08-22'),
(1000000001, 5000000003, 'National Bank Of Greece', 'Intern', '2018-07-02', '2018-09-29');

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

INSERT INTO `friendrequest` (`Sender`, `Receiver`) VALUES
(1000000007, 1000000004);

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
(1000000001, 1000000004),
(1000000002, 1000000006),
(1000000003, 1000000001),
(1000000004, 1000000006),
(1000000006, 1000000001),
(1000000006, 1000000003),
(1000000006, 1000000005),
(1000000007, 1000000001);

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

INSERT INTO `jobapplications` (`Applicant`, `Job`) VALUES
(1000000002, 3000000002),
(1000000003, 3000000006),
(1000000004, 3000000001),
(1000000004, 3000000006),
(1000000005, 3000000003),
(1000000005, 3000000006),
(1000000006, 3000000001),
(1000000006, 3000000002);

-- --------------------------------------------------------

--
-- Table structure for table `jobrequirements`
--

CREATE TABLE IF NOT EXISTS `jobrequirements` (
  `JobID` bigint(10) NOT NULL,
  `Skill` varchar(20) NOT NULL,
  PRIMARY KEY (`JobID`,`Skill`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jobrequirements`
--

INSERT INTO `jobrequirements` (`JobID`, `Skill`) VALUES
(3000000001, 'Cooking'),
(3000000001, 'English'),
(3000000001, 'Good-looking'),
(3000000002, 'English'),
(3000000002, 'Java'),
(3000000003, 'Acting'),
(3000000003, 'English'),
(3000000003, 'Friendly'),
(3000000004, 'Acting'),
(3000000004, 'French'),
(3000000004, 'Friendly'),
(3000000005, 'DJ'),
(3000000005, 'House'),
(3000000005, 'Mainstream'),
(3000000006, 'Friendly'),
(3000000006, 'Intern'),
(3000000006, 'Python');

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

CREATE TABLE IF NOT EXISTS `jobs` (
  `ID` bigint(10) NOT NULL,
  `Creator` bigint(10) NOT NULL,
  `Title` varchar(30) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Date` date NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jobs`
--

INSERT INTO `jobs` (`ID`, `Creator`, `Title`, `Description`, `Date`) VALUES
(3000000002, 1000000001, 'Java Junior Developer', 'Become a Junior Java Developer at Oracle!', '2018-08-24'),
(3000000001, 1000000004, 'Chef', 'Become a chef at the best restaurant of Europe!', '2018-08-24'),
(3000000003, 1000000002, 'Actor for Huge Hollywood movie', 'Play the infamous Batman in hollywoods latest hit: Batman!', '2018-08-27'),
(3000000004, 1000000002, 'Actor for tiny Hollywood movie', 'Play the famous Bob in hollywoods latest musical: Dance!', '2018-08-27'),
(3000000005, 1000000002, 'DJ', 'The club Washing Machine is looking for a new DJ!', '2018-08-27'),
(3000000006, 1000000002, 'Python Developer', 'We are looking for passionate young men to work in data science!', '2018-08-27');

-- --------------------------------------------------------

--
-- Table structure for table `likes`
--

CREATE TABLE IF NOT EXISTS `likes` (
  `UserID` bigint(10) NOT NULL,
  `PostID` bigint(10) NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserID`,`PostID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `likes`
--

INSERT INTO `likes` (`UserID`, `PostID`, `Timestamp`) VALUES
(1000000001, 2000000001, '2018-08-24 08:42:07'),
(1000000002, 2000000002, '2018-08-24 08:42:07'),
(1000000006, 2000000002, '2018-08-24 08:42:07'),
(1000000001, 2000000002, '2018-08-24 08:42:07'),
(1000000007, 2000000001, '2018-08-24 08:42:07'),
(1000000005, 2000000001, '2018-08-24 08:45:56');

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

INSERT INTO `messages` (`UserFrom`, `UserTo`, `Content`, `Timestamp`) VALUES
(1000000006, 1000000004, 'Hey!', '2018-08-22 16:49:15'),
(1000000006, 1000000004, 'How are ya?', '2018-08-22 16:49:26'),
(1000000004, 1000000006, 'Leave me alone!', '2018-08-22 16:49:44'),
(1000000001, 1000000007, 'Why did you add me?', '2018-08-22 17:49:34'),
(1000000001, 1000000002, 'You should become an admin like me!', '2018-08-22 17:49:49');

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE IF NOT EXISTS `notifications` (
  `ID` bigint(10) NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Category` tinyint(1) NOT NULL,
  `UserFrom` bigint(10) NOT NULL,
  `UserTo` bigint(10) NOT NULL,
  `Post` bigint(10) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`ID`, `Timestamp`, `Category`, `UserFrom`, `UserTo`, `Post`) VALUES
(4000000001, '2018-08-21 16:09:21', 1, 1000000007, 1000000004, 2000000001),
(4000000002, '2018-08-21 16:18:47', 2, 1000000007, 1000000004, 2000000001),
(4000000003, '2018-08-21 16:28:58', 3, 1000000007, 1000000001, 0),
(4000000004, '2018-08-21 16:38:21', 1, 1000000001, 1000000004, 2000000002),
(4000000005, '2018-08-22 13:11:03', 3, 1000000003, 1000000001, 0),
(4000000006, '2018-08-22 13:46:13', 4, 1000000003, 1000000001, 0),
(4000000007, '2018-08-22 13:46:59', 4, 1000000007, 1000000001, 0),
(4000000008, '2018-08-24 08:45:58', 1, 1000000005, 1000000004, 2000000001);

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

INSERT INTO `skills` (`UserID`, `SkillID`, `Skill`) VALUES
(1000000001, 7000000001, 'JAVA'),
(1000000001, 7000000002, 'Skiing'),
(1000000001, 7000000003, 'Basketball'),
(1000000001, 7000000004, 'Dunking'),
(1000000001, 7000000005, 'English'),
(1000000001, 7000000006, 'Java'),
(1000000002, 7000000007, 'Cooking');

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
(1000000003, 'Little', 'George', 'littlegeorge25@gmail.com', 'l1ttlegeorge25', '6945632148', 0),
(1000000002, 'Leonidas', 'Papanastasiou', 'leopap25@gmail.com', 'mySecondPass', 'DontRemember', 0),
(1000000004, 'First', 'Tester', 'tester@gmail.com', 'testerpass', '6944444444', 0),
(1000000005, 'Evita', 'Mintza', 'evita@gmail.com', 'evitaPass', '6955555555', 0),
(1000000006, 'Mr', 'Popular', 'popular@gmail.com', 'popularPass', 'EverybodyKnows', 0),
(1000000007, 'Mr', 'unPopular', 'unpopular@gmail.com', 'unpopularPass', '6912365478', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
