SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS `shea_sepherd` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `shea_sepherd`;

CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `vorname` varchar(50) DEFAULT NULL,
  `nachname` varchar(50) DEFAULT NULL,
  `telefonnummer` varchar(25) DEFAULT NULL,
  `rolle` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `geisternetz` (
  `id` int(11) NOT NULL,
  `durchmesser` double NOT NULL,
  `breitengrad` decimal(9,6) DEFAULT NULL,
  `laengengrad` decimal(9,6) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `meldendePerson_id` int(11) DEFAULT NULL,
  `bergendePerson_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE `geisternetz`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKinae7x6sf27gk0dhmpvt31byc` (`bergendePerson_id`),
  ADD UNIQUE KEY `UKg9onpds8d2xckluev08fhw2s3` (`meldendePerson_id`);


ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);


ALTER TABLE `geisternetz`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE `geisternetz`
  ADD CONSTRAINT `FK94tdw8ppbmlwfhwvpqji49i04` FOREIGN KEY (`meldendePerson_id`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `FKg42440jdlkgeat3kc4iw30r22` FOREIGN KEY (`bergendePerson_id`) REFERENCES `person` (`id`);
COMMIT;