-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 21, 2024 at 10:55 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qems`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_username_password`
--

CREATE TABLE `data_username_password` (
  `ID` int(3) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `id` varchar(10) NOT NULL,
  `name` varchar(500) NOT NULL,
  `opt1` varchar(500) NOT NULL,
  `opt2` varchar(500) NOT NULL,
  `opt3` varchar(500) NOT NULL,
  `opt4` varchar(500) NOT NULL,
  `answer` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `name`, `opt1`, `opt2`, `opt3`, `opt4`, `answer`) VALUES
('1', 'Ibukota negara Jepang adalah?', 'Beijing', 'Seoul', 'Tokyo', 'Bangkok', 'Tokyo'),
('2', 'Siapa penemu telepon?', 'Alexander Graham Bell', 'Thomas Edison', 'Nikola Tesla', 'Guglielmo Marconi', 'Alexander Graham Bell'),
('3', 'Gunung tertinggi di dunia adalah?', 'Gunung Kilimanjaro', 'Gunung K2', 'Gunung Everest', 'Gunung Elbrus', 'Gunung Everest'),
('4', 'Laut terbesar di dunia adalah?', 'Laut Cina Selatan', 'Laut Karibia', 'Laut Tengah', 'Laut Kaspia', 'Laut Kaspia'),
('5', 'Presiden pertama Indonesia adalah?', 'Joko Widodo', 'Soekarno', 'Soeharto', 'Megawati', 'Soekarno'),
('6', 'Planet terbesar di tata surya kita adalah?', 'Bumi', 'Mars', 'Jupiter ', 'Saturnus', 'Jupiter'),
('7', 'Mata uang resmi Jepang adalah?', 'Yen', 'Won', 'Yuan', 'Baht', 'Yen'),
('8', 'Siapakah penulis novel \"Harry Potter\"?', 'J.R.R Tolkien', 'George R.R. Martin', 'J.K. Rowling', 'Stephen King', 'J.K. Rowling'),
('9', 'Negara manakah yang memiliki piramida yang bernama Giza?', 'Mesir', 'Peru', 'Meksiko', 'Yunani', 'Mesir'),
('10', 'Siapakah ilmuwan yang menemukan teori relativitas?', 'Adam Malik', 'B. J. Habibie', 'Marrie Curie', 'Albert Einstein', 'Albert Einstein');

-- --------------------------------------------------------

--
-- Table structure for table `studentdata`
--

CREATE TABLE `studentdata` (
  `no_pendaftaran` varchar(10) NOT NULL,
  `nama_mahasiswa` varchar(100) NOT NULL,
  `npm` varchar(50) NOT NULL,
  `jenis_kelamin` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `nomor_telepon` varchar(50) NOT NULL,
  `fakultas` varchar(100) NOT NULL,
  `prodi` varchar(100) NOT NULL,
  `nama_universitas` varchar(100) NOT NULL,
  `alamat` varchar(500) NOT NULL,
  `nilai` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_username_password`
--
ALTER TABLE `data_username_password`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data_username_password`
--
ALTER TABLE `data_username_password`
  MODIFY `ID` int(3) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
