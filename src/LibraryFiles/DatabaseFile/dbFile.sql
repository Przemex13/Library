-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Lis 24, 2023 at 05:49 PM
-- Wersja serwera: 10.4.28-MariaDB
-- Wersja PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `librarytest`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `0009_20231124_1`
--

CREATE TABLE `0009_20231124_1` (
  `id_reader` varchar(50) NOT NULL,
  `borrow_book_date` date DEFAULT NULL,
  `give_back_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `0009_20231124_2`
--

CREATE TABLE `0009_20231124_2` (
  `id_reader` varchar(50) NOT NULL,
  `borrow_book_date` date DEFAULT NULL,
  `give_back_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `0009_20231124_3`
--

CREATE TABLE `0009_20231124_3` (
  `id_reader` varchar(50) NOT NULL,
  `borrow_book_date` date DEFAULT NULL,
  `give_back_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `0009_20231124_4`
--

CREATE TABLE `0009_20231124_4` (
  `id_reader` varchar(50) NOT NULL,
  `borrow_book_date` date DEFAULT NULL,
  `give_back_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `0010_20231124_1`
--

CREATE TABLE `0010_20231124_1` (
  `id_reader` varchar(50) NOT NULL,
  `borrow_book_date` date DEFAULT NULL,
  `give_back_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `0010_20231124_2`
--

CREATE TABLE `0010_20231124_2` (
  `id_reader` varchar(50) NOT NULL,
  `borrow_book_date` date DEFAULT NULL,
  `give_back_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `0010_20231124_3`
--

CREATE TABLE `0010_20231124_3` (
  `id_reader` varchar(50) NOT NULL,
  `borrow_book_date` date DEFAULT NULL,
  `give_back_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `0010_20231124_4`
--

CREATE TABLE `0010_20231124_4` (
  `id_reader` varchar(50) NOT NULL,
  `borrow_book_date` date DEFAULT NULL,
  `give_back_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `0012_20231124_1`
--

CREATE TABLE `0012_20231124_1` (
  `id_reader` varchar(50) NOT NULL,
  `borrow_book_date` date DEFAULT NULL,
  `give_back_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `0012_20231124_2`
--

CREATE TABLE `0012_20231124_2` (
  `id_reader` varchar(50) NOT NULL,
  `borrow_book_date` date DEFAULT NULL,
  `give_back_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `0012_20231124_3`
--

CREATE TABLE `0012_20231124_3` (
  `id_reader` varchar(50) NOT NULL,
  `borrow_book_date` date DEFAULT NULL,
  `give_back_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `booktable`
--

CREATE TABLE `booktable` (
  `id_book` int(5) NOT NULL,
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `booktable`
--

INSERT INTO `booktable` (`id_book`, `author`, `title`) VALUES
(9, 'Carroll, Lewis', 'Alice in Wonderland'),
(10, 'Tolkien, J.R.R.', 'Lord of Rings'),
(11, 'Tolkien, J.R.R', 'Hobbit'),
(12, 'Rowling, J. K.', 'Harry Potter and Chamber of Secrets');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `inventory`
--

CREATE TABLE `inventory` (
  `id_copy` varchar(30) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `id_book` int(11) NOT NULL,
  `reader` varchar(50) DEFAULT NULL,
  `enter_data` date NOT NULL,
  `withdraw_data` date DEFAULT NULL,
  `rent_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`id_copy`, `id_book`, `reader`, `enter_data`, `withdraw_data`, `rent_date`) VALUES
('0009_20231124_1', 9, NULL, '2023-11-24', NULL, NULL),
('0009_20231124_2', 9, NULL, '2023-11-24', NULL, NULL),
('0009_20231124_3', 9, NULL, '2023-11-24', NULL, NULL),
('0009_20231124_4', 9, NULL, '2023-11-24', NULL, NULL),
('0010_20231124_1', 10, NULL, '2023-11-24', NULL, NULL),
('0010_20231124_2', 10, NULL, '2023-11-24', NULL, NULL),
('0010_20231124_3', 10, NULL, '2023-11-24', NULL, NULL),
('0010_20231124_4', 10, NULL, '2023-11-24', NULL, NULL),
('0012_20231124_1', 12, NULL, '2023-11-24', NULL, NULL),
('0012_20231124_2', 12, NULL, '2023-11-24', NULL, NULL),
('0012_20231124_3', 12, NULL, '2023-11-24', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `loggintable`
--

CREATE TABLE `loggintable` (
  `id_worker` int(5) NOT NULL,
  `login` varchar(20) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `userType` varchar(20) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loggintable`
--

INSERT INTO `loggintable` (`id_worker`, `login`, `password`, `userType`) VALUES
(2, 'Przemex13', 'Admin12345', 'Admin'),
(5, 'Jan123', 'Jan12345', 'staffMember');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `readerslist`
--

CREATE TABLE `readerslist` (
  `idReader` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `surname` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `Pesel` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `postcode` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `city` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `readerslist`
--

INSERT INTO `readerslist` (`idReader`, `name`, `surname`, `Pesel`, `address`, `postcode`, `city`) VALUES
('Kowalski_Jan_24342342423', 'Jan', 'Kowalski', '24342342423', 'Al. Jerozolimskie 34', '12123', 'Warsaw'),
('Nowak_Adam_432242423423', 'Adam', 'Nowak', '432242423423', 'Ul. Poznanska 23', '2342', 'Poznan'),
('Smith_John_4322342342', 'John', 'Smith', '4322342342', '1212 Brooklyn Street', '324332', 'New York');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `stafflist`
--

CREATE TABLE `stafflist` (
  `id_worker` int(11) NOT NULL,
  `name` varchar(20) CHARACTER SET utf16 COLLATE utf16_polish_ci NOT NULL,
  `surname` varchar(20) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `address` varchar(20) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `postcode` varchar(20) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `login` varchar(20) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `stafflist`
--

INSERT INTO `stafflist` (`id_worker`, `name`, `surname`, `address`, `postcode`, `city`, `login`) VALUES
(4, 'Przemyslaw', 'Smith', '1213 Brooklyn ', '23111', 'New York', 'Przemex13'),
(5, 'Jan', 'Kowalski', 'Konopnickiej 5', '3423', 'Budapest', 'Jan123');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `booktable`
--
ALTER TABLE `booktable`
  ADD PRIMARY KEY (`id_book`);

--
-- Indeksy dla tabeli `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`id_copy`);

--
-- Indeksy dla tabeli `loggintable`
--
ALTER TABLE `loggintable`
  ADD PRIMARY KEY (`id_worker`);

--
-- Indeksy dla tabeli `stafflist`
--
ALTER TABLE `stafflist`
  ADD PRIMARY KEY (`id_worker`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booktable`
--
ALTER TABLE `booktable`
  MODIFY `id_book` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `stafflist`
--
ALTER TABLE `stafflist`
  MODIFY `id_worker` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
