-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 09 Tem 2021, 19:32:07
-- Sunucu sürümü: 8.0.18
-- PHP Sürümü: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `projemdb`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `islemler`
--

CREATE TABLE `islemler` (
  `islemID` int(11) NOT NULL,
  `user` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `islemAciklama` varchar(250) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `islemTutar` double NOT NULL,
  `islemTarih` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `islemler`
--

INSERT INTO `islemler` (`islemID`, `user`, `islemAciklama`, `islemTutar`, `islemTarih`) VALUES
(1, 'admin', 'Kahvaltı Malzemeleri', 125, '2021-04-01'),
(2, 'admin', 'Kahve Malzemeleri', 100, '2021-04-04'),
(3, 'calisan', 'Manav giderleri', 500, '2021-04-14'),
(4, 'calisan', 'Kahve giderleri', 17.5, '2021-04-06'),
(5, 'calisan', 'Manav Giderleri', 87.4, '2021-04-10'),
(6, 'furkan', 'Temizlik Malzemeleri', 210, '2021-04-05');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `login`
--

CREATE TABLE `login` (
  `kID` int(11) NOT NULL,
  `kul_ad` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `sifre` varchar(100) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `yetki` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `login`
--

INSERT INTO `login` (`kID`, `kul_ad`, `sifre`, `yetki`) VALUES
(1, 'admin', '123', 1),
(2, 'furkan', '123', 0),
(5, 'deneme', '1234', 1),
(6, 'deneme2', '123', 0),
(8, 'deneme', 'd10906c3dac1172d4f60bd41f224ae75', 0),
(9, 'deneme25', '6512bd43d9caa6e02c990b0a82652dca', 0);

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `islemler`
--
ALTER TABLE `islemler`
  ADD PRIMARY KEY (`islemID`);

--
-- Tablo için indeksler `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`kID`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `islemler`
--
ALTER TABLE `islemler`
  MODIFY `islemID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Tablo için AUTO_INCREMENT değeri `login`
--
ALTER TABLE `login`
  MODIFY `kID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
