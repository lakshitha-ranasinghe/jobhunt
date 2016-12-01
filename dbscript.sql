-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 25, 2015 at 06:30 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `jobhunt`
--

-- --------------------------------------------------------

--
-- Table structure for table `application_user`
--

CREATE TABLE IF NOT EXISTS `application_user` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `company_profile`
--

CREATE TABLE IF NOT EXISTS `company_profile` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` int(11) NOT NULL,
  `mobile` int(11) NOT NULL,
  `address1` varchar(50) NOT NULL,
  `address2` varchar(100) NOT NULL,
  `address3` varchar(100) NOT NULL,
  `website` varchar(100) NOT NULL,
  `job_type` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `id_generator`
--

CREATE TABLE IF NOT EXISTS `id_generator` (
  `gen_name` varchar(50) NOT NULL,
  `gen_value` int(11) NOT NULL,
  PRIMARY KEY (`gen_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `jobseeker`
--

CREATE TABLE IF NOT EXISTS `jobseeker` (
  `id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `title` varchar(50) NOT NULL,
  `birthday` date NOT NULL,
  `address1` varchar(50) NOT NULL,
  `address2` varchar(50) NOT NULL,
  `address3` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` int(11) NOT NULL,
  `mobile` int(11) NOT NULL,
  `qualified_field` varchar(50) NOT NULL,
  `last_job` varchar(50) NOT NULL,
  `expected_job` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `jobseeker_al`
--

CREATE TABLE IF NOT EXISTS `jobseeker_al` (
  `al_id` int(11) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `mark` varchar(50) NOT NULL,
  `jobseeker_education` int(11) NOT NULL,
  PRIMARY KEY (`al_id`),
  KEY `jobseeker` (`jobseeker_education`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jobseeker_al`
--

-- --------------------------------------------------------

--
-- Table structure for table `jobseeker_education`
--

CREATE TABLE IF NOT EXISTS `jobseeker_education` (
  `id` int(11) NOT NULL,
  `ol_year` int(11) NOT NULL,
  `ol_school` varchar(50) NOT NULL,
  `al_year` int(11) NOT NULL,
  `al_school` varchar(50) NOT NULL,
  `jobseeker` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `jobseeker` (`jobseeker`),
  KEY `jobseeker_2` (`jobseeker`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `jobseeker_experience`
--

CREATE TABLE IF NOT EXISTS `jobseeker_experience` (
  `id` int(11) NOT NULL,
  `total_years` int(11) NOT NULL,
  `jobseeker` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `jobseeker` (`jobseeker`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `jobseeker_ol`
--

CREATE TABLE IF NOT EXISTS `jobseeker_ol` (
  `ol_id` int(11) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `mark` varchar(50) NOT NULL,
  `jobseeker_education` int(11) NOT NULL,
  PRIMARY KEY (`ol_id`),
  KEY `jobseeker` (`jobseeker_education`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `jobseeker_other`
--

CREATE TABLE IF NOT EXISTS `jobseeker_other` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `published_year` int(11) NOT NULL,
  `details` varchar(100) NOT NULL,
  `jobseeker` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `jobseeker` (`jobseeker`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `jobseeker_university`
--

CREATE TABLE IF NOT EXISTS `jobseeker_university` (
  `university_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `course` varchar(100) NOT NULL,
  `completed_year` int(11) NOT NULL,
  `gpa` double NOT NULL,
  `jobseeker_education` int(11) NOT NULL,
  PRIMARY KEY (`university_id`),
  KEY `jobseeker` (`jobseeker_education`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `jobseeker_workedcompany`
--

CREATE TABLE IF NOT EXISTS `jobseeker_workedcompany` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `designation` varchar(100) NOT NULL,
  `jobseeker` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `jobseeker` (`jobseeker`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `user_cache`
--

CREATE TABLE IF NOT EXISTS `user_cache` (
  `id` int(11) NOT NULL,
  `profile_picture` blob,
  `last_login` datetime DEFAULT NULL,
  `preference1` int(11) DEFAULT NULL,
  `preference2` int(11) DEFAULT NULL,
  `preference3` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_session`
--

CREATE TABLE IF NOT EXISTS `user_session` (
  `token` varchar(50) NOT NULL,
  `userid` int(11) NOT NULL,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vacancy`
--

CREATE TABLE IF NOT EXISTS `vacancy` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` varchar(255) NOT NULL,
  `prerequisites` varchar(255) NOT NULL,
  `branch` varchar(100) NOT NULL,
  `closing_date` date NOT NULL,
  `vacancy_count` int(11) NOT NULL,
  `salary` double NOT NULL,
  `company` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `company` (`company`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `vacancy_apply`
--

CREATE TABLE IF NOT EXISTS `vacancy_apply` (
  `id` int(11) NOT NULL,
  `jobseeker_id` int(11) NOT NULL,
  `vacancy_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `interview_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `vacancy_id` (`vacancy_id`),
  KEY `jobseeker_id` (`jobseeker_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `vacancy_cache`
--

CREATE TABLE IF NOT EXISTS `vacancy_cache` (
  `id` int(11) NOT NULL,
  `posted_date` datetime NOT NULL,
  `preference1` int(11) DEFAULT NULL,
  `preference2` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `jobseeker_al`
--
ALTER TABLE `jobseeker_al`
  ADD CONSTRAINT `FK_jobseeker_al_jobseeker_education` FOREIGN KEY (`jobseeker_education`) REFERENCES `jobseeker` (`id`),
  ADD CONSTRAINT `jobseeker_al_ibfk_1` FOREIGN KEY (`jobseeker_education`) REFERENCES `jobseeker` (`id`);

--
-- Constraints for table `jobseeker_education`
--
ALTER TABLE `jobseeker_education`
  ADD CONSTRAINT `FK_jobseeker_education_jobseeker` FOREIGN KEY (`jobseeker`) REFERENCES `jobseeker` (`id`),
  ADD CONSTRAINT `jobseeker_education_ibfk_1` FOREIGN KEY (`jobseeker`) REFERENCES `jobseeker` (`id`);

--
-- Constraints for table `jobseeker_experience`
--
ALTER TABLE `jobseeker_experience`
  ADD CONSTRAINT `FK_jobseeker_experience_jobseeker` FOREIGN KEY (`jobseeker`) REFERENCES `jobseeker` (`id`),
  ADD CONSTRAINT `jobseeker_experience_ibfk_1` FOREIGN KEY (`jobseeker`) REFERENCES `jobseeker` (`id`);

--
-- Constraints for table `jobseeker_ol`
--
ALTER TABLE `jobseeker_ol`
  ADD CONSTRAINT `FK_jobseeker_ol_jobseeker_education` FOREIGN KEY (`jobseeker_education`) REFERENCES `jobseeker` (`id`),
  ADD CONSTRAINT `jobseeker_ol_ibfk_1` FOREIGN KEY (`jobseeker_education`) REFERENCES `jobseeker` (`id`);

--
-- Constraints for table `jobseeker_other`
--
ALTER TABLE `jobseeker_other`
  ADD CONSTRAINT `FK_jobseeker_other_jobseeker` FOREIGN KEY (`jobseeker`) REFERENCES `jobseeker` (`id`),
  ADD CONSTRAINT `jobseeker_other_ibfk_1` FOREIGN KEY (`jobseeker`) REFERENCES `jobseeker` (`id`);

--
-- Constraints for table `jobseeker_university`
--
ALTER TABLE `jobseeker_university`
  ADD CONSTRAINT `FK_jobseeker_university_jobseeker_education` FOREIGN KEY (`jobseeker_education`) REFERENCES `jobseeker` (`id`),
  ADD CONSTRAINT `jobseeker_university_ibfk_1` FOREIGN KEY (`jobseeker_education`) REFERENCES `jobseeker` (`id`);

--
-- Constraints for table `jobseeker_workedcompany`
--
ALTER TABLE `jobseeker_workedcompany`
  ADD CONSTRAINT `FK_jobseeker_workedcompany_jobseeker` FOREIGN KEY (`jobseeker`) REFERENCES `jobseeker` (`id`),
  ADD CONSTRAINT `jobseeker_workedcompany_ibfk_1` FOREIGN KEY (`jobseeker`) REFERENCES `jobseeker` (`id`);

--
-- Constraints for table `vacancy`
--
ALTER TABLE `vacancy`
  ADD CONSTRAINT `FK_vacancy_company` FOREIGN KEY (`company`) REFERENCES `company_profile` (`id`),
  ADD CONSTRAINT `vacancy_ibfk_1` FOREIGN KEY (`company`) REFERENCES `company_profile` (`id`);

--
-- Constraints for table `vacancy_apply`
--
ALTER TABLE `vacancy_apply`
  ADD CONSTRAINT `FK_vacancy_apply_jobseeker_id` FOREIGN KEY (`jobseeker_id`) REFERENCES `jobseeker` (`id`),
  ADD CONSTRAINT `FK_vacancy_apply_vacancy_id` FOREIGN KEY (`vacancy_id`) REFERENCES `vacancy` (`id`),
  ADD CONSTRAINT `vacancy_apply_ibfk_1` FOREIGN KEY (`jobseeker_id`) REFERENCES `jobseeker` (`id`),
  ADD CONSTRAINT `vacancy_apply_ibfk_2` FOREIGN KEY (`vacancy_id`) REFERENCES `vacancy` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
