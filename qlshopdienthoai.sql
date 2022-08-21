-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 21, 2022 lúc 08:04 PM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qlshopdienthoai`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `idacc` int(11) NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` bit(1) DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`idacc`, `username`, `password`, `status`) VALUES
(1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', b'1'),
(2, 'user1', 'e10adc3949ba59abbe56e057f20f883e', b'1'),
(3, 'admin1', 'e10adc3949ba59abbe56e057f20f883e', b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `billdetails`
--

CREATE TABLE `billdetails` (
  `id` int(11) NOT NULL,
  `idbill` int(11) DEFAULT NULL,
  `idproduct` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `quantity` int(11) DEFAULT 1,
  `status` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `billdetails`
--

INSERT INTO `billdetails` (`id`, `idbill`, `idproduct`, `price`, `quantity`, `status`) VALUES
(1, 1, 1, NULL, 4, b'0'),
(2, 1, 2, NULL, 2, b'0'),
(4, 2, 15, NULL, 1, b'0'),
(31, 4, 14, NULL, 4, b'0'),
(60, 1, 14, NULL, 1, b'0'),
(72, 1, 1, NULL, 1, b'0'),
(78, 1, 14, NULL, 1, b'0'),
(79, 1, 3, NULL, 1, b'0'),
(80, 1, 14, NULL, 1, b'0'),
(135, 198, 3, NULL, 1, b'0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bills`
--

CREATE TABLE `bills` (
  `id` int(11) NOT NULL,
  `idcustomer` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `totalprice` float DEFAULT NULL,
  `idpayment` int(11) DEFAULT NULL,
  `idshipping` int(11) DEFAULT NULL,
  `status` bit(1) DEFAULT b'1',
  `createddate` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `bills`
--

INSERT INTO `bills` (`id`, `idcustomer`, `quantity`, `totalprice`, `idpayment`, `idshipping`, `status`, `createddate`) VALUES
(1, 1, 4, 12345, 1, 1, b'1', '2022-08-10 15:17:32'),
(2, 4, 1, 12232, 1, 1, b'1', '2022-08-10 15:17:32'),
(3, 5, 2, 33352500, 1, 2, b'1', '2022-08-14 07:43:57'),
(4, 2, NULL, NULL, NULL, NULL, b'1', '2022-08-13 18:04:46'),
(198, 17, NULL, NULL, NULL, NULL, b'1', '2022-08-15 06:03:09');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `namecategory` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` bit(1) DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `namecategory`, `status`) VALUES
(1, 'Apple', b'1'),
(2, 'SamSung', b'1'),
(4, 'OPPO', b'1'),
(5, 'Vivo', b'1'),
(55, 'Xiaomi', b'1'),
(56, 'Huawei', b'1'),
(57, 'Realme', b'1'),
(58, 'VinSmart', b'1'),
(59, 'Lenovo', b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `colors`
--

CREATE TABLE `colors` (
  `idcol` int(11) NOT NULL,
  `namecolor` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `extraprice` float NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `colors`
--

INSERT INTO `colors` (`idcol`, `namecolor`, `extraprice`, `status`) VALUES
(1, 'Màu Đen', 0, 1),
(2, 'Màu Trắng', 500000, 1),
(3, 'Màu Đỏ', 700000, 1),
(4, 'Vàng Đồng ', 1000000, 1),
(5, 'Xanh Lá', 500000, 1),
(6, 'Xanh Dương', 500000, 1),
(7, 'Màu Hồng', 0, 1),
(8, 'Màu Galaxy', 1000000, 1),
(9, 'abcz', 99999, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `namecustomer` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `birthday` date DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` bit(1) DEFAULT b'1',
  `status` bit(1) DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `customer`
--

INSERT INTO `customer` (`id`, `namecustomer`, `username`, `password`, `birthday`, `phone`, `email`, `address`, `gender`, `status`) VALUES
(1, 'Nguyá»…n VÄƒn B', 'nguoidung1', '827ccb0eea8a706c4c34a16891f84e7b', NULL, '0987654321', 'nva@gmail.com', NULL, b'0', b'1'),
(2, 'Nguyễn Thị D', 'user1', '827ccb0eea8a706c4c34a16891f84e7b', NULL, '0123456789', 'ntd@gmail.com', NULL, b'0', b'1'),
(3, NULL, 'user12', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, NULL, NULL, b'1'),
(4, 'Nguyễn Duy Khánh', 'nguyenduykhanh', '25f9e794323b453885f5181f1b624d0b', '2022-08-03', '012345678999', 'khanh@gmail.com', 'Hà nội', b'1', b'1'),
(5, 'Lê Xuân SInh', 'lexuansinh', 'e10adc3949ba59abbe56e057f20f883e', '2022-08-30', '0973432518', NULL, 'Hà Nội', b'1', b'1'),
(8, 'Lê Xuân Sinh 10', 'lexuansinh10', '123456', NULL, '12345', NULL, 'Hà Nội', b'1', b'1'),
(9, 'LÃª XuÃ¢n Sinh 11', 'lexuansinh11', 'e10adc3949ba59abbe56e057f20f883e', NULL, '09734325189', NULL, 'HÃ  Ná»™i', b'1', b'1'),
(10, 's', 'a', '4bbde07660e5eff90873642cfae9a8dd', NULL, NULL, NULL, NULL, b'0', b'1'),
(11, 'lesinh999', 'lesinh10', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, NULL, b'0', b'1'),
(12, 'le xuan sinh moi nay', 'lexuansinhmoi', 'e10adc3949ba59abbe56e057f20f883e', NULL, '0912345678', NULL, 'Ha Noi', b'1', b'1'),
(13, 'Tai khoan da test lan thu 6', 'taikhoantest', 'c2f1366c51911b52369fe27df307ff84', NULL, '12345678910', NULL, 'Viet Nam Than Yeu12', b'0', b'1'),
(14, 'tai khoan test thu 2', 'tktest2', '14e1b600b1fd579f47433b88e8d85291', NULL, '12346789', NULL, 'Viet Nam', b'1', b'1'),
(15, 'tktest3', 'tktest3', 'e10adc3949ba59abbe56e057f20f883e', NULL, '1234567', NULL, 'Viet Nam ', b'1', b'1'),
(16, 'sdadasda', 'tktest4', 'e10adc3949ba59abbe56e057f20f883e', NULL, '323273', NULL, 'Viet Nam', b'1', b'1'),
(17, 'tai khoan test thu 5', 'tktest5', 'e10adc3949ba59abbe56e057f20f883e', NULL, '12344321', NULL, 'Ha Noi Thu Do Viet Nam', b'0', b'1'),
(18, 'tai khoan test 6', 'tktest6', 'e10adc3949ba59abbe56e057f20f883e', NULL, '34632784827', NULL, 'Ha Noi', b'1', b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `memory`
--

CREATE TABLE `memory` (
  `idmem` int(11) NOT NULL,
  `namememory` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `extraprice` float NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `memory`
--

INSERT INTO `memory` (`idmem`, `namememory`, `extraprice`, `status`) VALUES
(1, '64GB', 0, 1),
(2, '128GB', 500000, 1),
(3, '256GB', 700000, 1),
(4, '512GB', 1000000, 1),
(5, 'RAM 8GB', 450000, 1),
(6, 'RAM 4GB', 0, 1),
(10, '128GB', 345676, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `namepayment` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` bit(1) DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `payment`
--

INSERT INTO `payment` (`id`, `namepayment`, `status`) VALUES
(1, 'Thanh toán khi nhận hàng', b'1'),
(2, 'Paypal', b'1'),
(3, 'VISA', b'1'),
(4, 'Master Card', b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `idpro` int(11) NOT NULL,
  `nameproduct` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `idcategory` int(11) NOT NULL,
  `price` float NOT NULL,
  `quantity` int(11) NOT NULL,
  `sizecreen` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `screentechnology` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `rearcamera` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `frontcamera` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `chipset` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `sim` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `os` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `idcolor` int(11) NOT NULL,
  `idmemory` int(11) NOT NULL,
  `imagelist` text COLLATE utf8_unicode_ci NOT NULL,
  `otherparameters` text COLLATE utf8_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`idpro`, `nameproduct`, `idcategory`, `price`, `quantity`, `sizecreen`, `screentechnology`, `rearcamera`, `frontcamera`, `chipset`, `sim`, `os`, `idcolor`, `idmemory`, `imagelist`, `otherparameters`, `status`) VALUES
(1, 'iPhone 11 Pro Max', 1, 13790000, 4, '6.5 inch', 'OLED', '12 MP + 12 MP + 12 MP', '12 MP, f / 2.2', 'Apple A13 Bionic', '1 eSIM, 1 Nano SIM', 'IOS 13', 1, 1, 'https://i.ibb.co/Xk0QRcd/iphone11promax-den.jpg', 'Smartphone cao cấp', 1),
(2, 'Iphone 13 Pro Max', 1, 28390000, 5, '6.7 inch', 'OLED', '12.0 MP + 12.0 MP + 12.0 MP', '12.0 MP', 'Apple A15 Bionic', '1 eSIM, 1 Nano SIM', 'IOS 15', 5, 4, 'https://cdn2.cellphones.com.vn/358x/media/catalog/product/i/p/iphone_13-_pro-5_4.jpg', 'Smart Phone', 1),
(3, 'Samsung Galaxy S22 Ultra 5G', 2, 25990000, 2, '6.8 inch', 'Dynamic AMOLED 2X', '108.0 MP + 12.0 MP + 10.0 MP + 10.0 MP', '40.0 MP', 'Snapdragon 8 Gen 1', '2 Nano SIM hoặc 1 eSIM, 1 Nano SIM', 'Android 12', 2, 5, 'https://cdn2.cellphones.com.vn/358x/media/catalog/product/s/m/sm-s908_galaxys22ultra_front_burgundy_211119.jpg', 'Smartphone cao cấp', 1),
(4, 'OPPO FIND X2 PRO', 4, 7900000, 5, '6.78 Wide Screen', 'AMOLED Screen Technology', '48 MP & Sub 48 MP, 13 MP HD', '32 MP', 'Snapdragon 865 8 nhân', '2 Nano SIM hoặc 1 eSIM, 1 Nano SIM', 'Android 10', 5, 6, 'https://i.ibb.co/0qWjhRL/oppofindx2.jpg', 'Smartphone giá rẻ', 1),
(14, 'Vsmart Joy 4', 58, 7000000, 7, '6.53 inch', 'LTPS IPS LCD', '16 MP & Sub 8 MP, 2 MP, 2 MP', '6 MP & Sub 8 MP, 2 MP, 2 MP', 'Snapdragon 665', 'Android 10', 'IOS 13', 2, 6, 'https://i.ibb.co/ZgbxV8y/vsmart-joy-4-den-200x200.jpg', 'Smartphone giá rẻ', 1),
(15, 'Iphone 11 Pro Max', 1, 13790000, 5, '6.5 inch', 'OLED', '12 MP + 12 MP + 12 MP', '12 MP + 12 MP + 12 MP', 'Apple A13 Bionic', '1 eSIM, 1 Nano SIM', 'IOS 13', 2, 4, 'https://i.ibb.co/ZHZqv4P/iphone11promax-trang.jpg', 'Smartphone cao cấp', 1),
(16, 'Lenovo Legion Pro', 59, 11950000, 4, '6.65 inch Full HD', 'AMOLED', '20 MP, f/2.2, Popup', '64 MP, f/1.8 - 16 MP, f/2.2', 'SM8250 Snapdragon 865+', 'Nano SIM', 'Android 10', 1, 6, 'https://i.ibb.co/LvhqyWJ/lenovo-legion-phone-duel-2-10.jpg', 'Smartphone gaming', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
  `idrole` int(11) NOT NULL,
  `role` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `status` bit(1) DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`idrole`, `role`, `status`) VALUES
(1, 'Admin', b'1'),
(2, 'User', b'1'),
(3, 'Manager', b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `shipping`
--

CREATE TABLE `shipping` (
  `id` int(11) NOT NULL,
  `nameshipping` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` float DEFAULT NULL,
  `status` bit(1) DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `shipping`
--

INSERT INTO `shipping` (`id`, `nameshipping`, `price`, `status`) VALUES
(1, 'Shopee Express', 10000, b'1'),
(2, 'Giao Hàng Nhanh', 10000, b'1'),
(3, 'J&T Express', 10000, b'1'),
(4, 'Ninja Van', 10000, b'1');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`idacc`);

--
-- Chỉ mục cho bảng `billdetails`
--
ALTER TABLE `billdetails`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idbill` (`idbill`),
  ADD KEY `idproduct` (`idproduct`);

--
-- Chỉ mục cho bảng `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `idcustomer_2` (`idcustomer`),
  ADD KEY `idcustomer` (`idcustomer`),
  ADD KEY `idpayment` (`idpayment`),
  ADD KEY `idshipping` (`idshipping`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `colors`
--
ALTER TABLE `colors`
  ADD PRIMARY KEY (`idcol`);

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `phone` (`phone`);

--
-- Chỉ mục cho bảng `memory`
--
ALTER TABLE `memory`
  ADD PRIMARY KEY (`idmem`);

--
-- Chỉ mục cho bảng `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`idpro`),
  ADD KEY `idcategory` (`idcategory`),
  ADD KEY `idcolor` (`idcolor`),
  ADD KEY `idmemory` (`idmemory`);

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`idrole`);

--
-- Chỉ mục cho bảng `shipping`
--
ALTER TABLE `shipping`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `idacc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `billdetails`
--
ALTER TABLE `billdetails`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=136;

--
-- AUTO_INCREMENT cho bảng `bills`
--
ALTER TABLE `bills`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=200;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT cho bảng `colors`
--
ALTER TABLE `colors`
  MODIFY `idcol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT cho bảng `memory`
--
ALTER TABLE `memory`
  MODIFY `idmem` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `idpro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT cho bảng `roles`
--
ALTER TABLE `roles`
  MODIFY `idrole` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `shipping`
--
ALTER TABLE `shipping`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `billdetails`
--
ALTER TABLE `billdetails`
  ADD CONSTRAINT `billdetails_ibfk_1` FOREIGN KEY (`idbill`) REFERENCES `bills` (`id`),
  ADD CONSTRAINT `billdetails_ibfk_2` FOREIGN KEY (`idproduct`) REFERENCES `product` (`idpro`);

--
-- Các ràng buộc cho bảng `bills`
--
ALTER TABLE `bills`
  ADD CONSTRAINT `bills_ibfk_1` FOREIGN KEY (`idcustomer`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `bills_ibfk_2` FOREIGN KEY (`idpayment`) REFERENCES `payment` (`id`),
  ADD CONSTRAINT `bills_ibfk_3` FOREIGN KEY (`idshipping`) REFERENCES `shipping` (`id`);

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`idcategory`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `product_ibfk_2` FOREIGN KEY (`idcolor`) REFERENCES `colors` (`idcol`),
  ADD CONSTRAINT `product_ibfk_3` FOREIGN KEY (`idmemory`) REFERENCES `memory` (`idmem`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
