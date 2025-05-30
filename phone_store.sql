-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 30, 2025 at 06:34 PM
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
-- Database: `phone_store`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `role` int(11) NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`username`, `password`, `dob`, `email`, `address`, `role`, `id`) VALUES
('chuotcon', '{bcrypt}$2a$10$fs3UbWrpxklln93F.g6yGO8EKZFGYE.fAEtso6FqUdauU83ObhfMm', '2023-11-10', 'pdat646@gmail.com', 'binh chieu thu duc', 0, 0),
('gacon', '{bcrypt}2a$10$O4w9m54bl1LyoQKchRGrR.SS9EoZD0C/qPNgdLA9I.qgqNacGBRZK', '2023-10-31', 'chuotcon@123', '5 so 2 binh chieu tp thu duc', 0, 0),
('huyanh', '$2a$10$2BDhDh7LJ8CvQGs2tFVHEOb9SllrlEVmzW0oKxrm.3ejFEUCWKSbK', '2001-09-18', 'huyanh@gmail.com', 'ss', 1, 52),
('huyanh1', '$2a$10$okxSx6eGZhwRjTnp1jhEd.7iCjAuPSNnATcMK2Mi64M4qlt/dpf7e', '2020-01-01', 'huyanh1@gmail.com', '123123123', 0, 1),
('testing', '$2a$10$ARI/LrVBmjo1FwOWOvUQg.rX0w.jBb4wWaa89FwkrlmFkUzmtJT2C', NULL, NULL, NULL, 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `account_seq`
--

CREATE TABLE `account_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account_seq`
--

INSERT INTO `account_seq` (`next_val`) VALUES
(151);

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `OrderID` tinyint(4) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `dateOrder` date NOT NULL,
  `TotalPrice` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `total_price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id_cart` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `ProductID` int(11) NOT NULL,
  `ProductName` char(255) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `imgpath` varchar(1000) NOT NULL,
  `img_path` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id_cart`, `username`, `ProductID`, `ProductName`, `amount`, `price`, `imgpath`, `img_path`, `product_name`) VALUES
(1, 'admin2', 15, 'Xiaomi 13T Pro Xanh', 1, 23, 'xiaomi-13t-pro-xanh.jpg', NULL, NULL),
(2, 'admin2', 18, 'Nokia 8210 4G Đỏ', 1, 23, 'nokia 8210-4g-do.jpg', NULL, NULL),
(3, 'admin2', 10, 'Samsung Galaxy S23 Fe Xanh', 2, 231, 'samsung-galaxy-s23-fe-xanh.jpg', NULL, NULL),
(4, 'admin2', 16, 'Xiaomi 13T Xanh Dương', 1, 213, 'xiaomi-13-t-xanh-duong.jpg', NULL, NULL),
(5, 'admin2', 12, 'Samsung Galaxy Z-Flip 5 Xanh Mint', 1, 1, 'samsung-galaxy-z-flip5-xanh-mint.jpg', NULL, NULL),
(6, 'chuotcon', 26, 'Nokia 105', 1, 21, 'nokia-105-4g-blue.jpg', NULL, NULL),
(7, 'gacon', 14, 'Samsung Galaxy M54 5G', 3, 34, 'samsung-galaxy-m54-5g.jpg', NULL, NULL),
(12, 'gacon', 26, 'Nokia 105', 1, 21, 'nokia-105-4g-blue.jpg', NULL, NULL),
(13, 'gacon', 25, 'Nokia C21 Plus', 1, 2, 'nokia-c21-plus-blue.jpg', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cartdetail`
--

CREATE TABLE `cartdetail` (
  `OrderDetailID` int(10) UNSIGNED NOT NULL,
  `OrderID` int(10) UNSIGNED NOT NULL,
  `ProductID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `categoryid` int(11) NOT NULL,
  `category` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`categoryid`, `category`) VALUES
(1, 'IPhone'),
(2, 'Samsung'),
(3, 'Xiaomi'),
(5, 'Oppo'),
(7, 'Nokia');

-- --------------------------------------------------------

--
-- Table structure for table `discount`
--

CREATE TABLE `discount` (
  `code` varchar(255) NOT NULL,
  `value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `discount`
--

INSERT INTO `discount` (`code`, `value`) VALUES
('DHNONGLAM', 11),
('KHOACNTT', 20),
('NONGLAM', 1);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `ProductID` int(10) UNSIGNED NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `categoryid` int(11) DEFAULT NULL,
  `Description` varchar(5000) NOT NULL,
  `Price` int(11) NOT NULL,
  `stock_quantity` int(11) DEFAULT NULL,
  `unit_price` varchar(255) DEFAULT NULL,
  `imgpath` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`ProductID`, `product_name`, `categoryid`, `Description`, `Price`, `stock_quantity`, `unit_price`, `imgpath`, `color`) VALUES
(0, 'sda', 1, '	dsad											\r\n															', 23, 2, ' ', '1725957892293_2_iphone_16_xanh_duong_didongviet.jpg', 'x'),
(2, 'Samsung S23 Ultra', 2, 'Samsung Galaxy S21 Ultra là một trong những mẫu điện thoại thông minh hàng đầu của Samsung, thuộc dòng Galaxy S21, được ra mắt vào tháng 1 năm 2021. Đây là một trong những sản phẩm cao cấp của hãng Samsung, và dưới đây là một số thông tin chung về Samsung Galaxy S21 Ultra:\r\n\r\nThiết kế: Samsung Galaxy S21 Ultra có thiết kế hiện đại với vỏ kim loại và mặt kính cường lực. Máy có màn hình cong với các góc màn hình vô cực, tạo ra một hình ảnh rất đẹp.\r\n\r\nMàn hình: Máy có màn hình Dynamic AMOLED 2X kích thước 6.8 inch hoặc 6.9 inch (tùy từng phiên bản) với độ phân giải Quad HD+ (3200 x 1440 pixel), cho hình ảnh sắc nét và màu sắc sống động. Màn hình hỗ trợ tốt cho việc xem nội dung đa phương tiện và chơi game.\r\n\r\nHiệu năng: Samsung Galaxy S21 Ultra sử dụng một chip xử lý mạnh mẽ, thường là chip Exynos hoặc Snapdragon tùy theo khu vực. Điều này giúp máy chạy mượt mà và xử lý tất cả các tác vụ đa nhiệm một cách dễ dàng.\r\n\r\nCamera: Một trong những điểm đáng chú ý nhất của Galaxy S21 Ultra là hệ thống camera mạnh mẽ với nhiều ống kính. Nó bao gồm camera chính 108 MP, camera góc rộng 12 MP, camera telephoto 10 MP và camera telephoto 10 MP với khả năng zoom quang học 10x và 100x, giúp bạn chụp ảnh chất lượng cao và thực hiện chức năng zoom mạnh mẽ.\r\n\r\nHệ điều hành: Máy chạy trên hệ điều hành Android với giao diện người dùng Samsung One UI, có thể nâng cấp lên phiên bản Android mới nhất.\r\n\r\nBộ nhớ: Có các phiên bản với dung lượng lưu trữ khác nhau, bao gồm 128GB, 256GB, và 512GB, cho phép bạn lựa chọn theo nhu cầu cá nhân.\r\n\r\nKhả năng kết nối: Samsung Galaxy S21 Ultra hỗ trợ nhiều tính năng kết nối, bao gồm 4G LTE, 5G, Wi-Fi 6E, Bluetooth, NFC, và USB-C.\r\n\r\nS Pen: Máy hỗ trợ S Pen (bút cảm ứng) tùy chọn, cho phép bạn viết, vẽ và thực hiện các tác vụ tương tác khác trên màn hình.\r\n\r\nPin: Máy được trang bị pin lớn, giúp sử dụng cả ngày mà không cần sạc lại liên tục.', 500, 10, '$', 'samsung-galaxy-s21-ultra.jpg', 'Đen'),
(3, 'iPhone 15 Pro Max', 1, '																																																												\r\n															\r\n															\r\n															\r\n															', 300, 10, '$', 'iphone-15-pro-max.jpg', 'Tím'),
(4, 'IPhone 15 Pro', 1, '															\r\n															', 222, 4, '$', 'iphone-15-pro.jpg', 'Đen'),
(5, 'IPhone 14 Pro Max', 1, '																														\r\n															\r\n															', 111, 1, '$', 'iphone-14-pro-max.jpg', 'Đen'),
(6, 'IPhone 15 Plus', 1, '															\r\n															', 231, 1, '$', 'iphone-15-plus.jpg', 'Đen'),
(7, 'Samsung Galaxy A05', 2, '															\r\n															', 231, 23, '$', 'samsung-galaxy-a05.jpg', 'Xanh'),
(9, 'Samsung Galaxy S23', 2, '', 23, 23, '$', 'samsung-galaxy-s23.jpg', 'Xám'),
(10, 'Samsung Galaxy S23 Fe Xanh', 2, '', 231, 2, '$', 'samsung-galaxy-s23-fe-xanh.jpg', 'Xanh'),
(11, 'Samsung Galaxy S23 Plus', 2, '															dasdasdasd\r\n															', 1, 1, '$', 'samsung-galaxy-s23-plus.jpg', 'Xám'),
(12, 'Samsung Galaxy Z-Flip 5 Xanh Mint', 2, '', 1, 1, '$', 'samsung-galaxy-z-flip5-xanh-mint.jpg', 'Xanh'),
(13, 'Samsung Galaxy Z-Fold 5', 2, '															sdadas\r\n															', 2, 4, '$', 'samsung-galaxy-z-fold5.jpg', 'Kem'),
(14, 'Samsung Galaxy M54 5G', 2, '															\r\n															', 34, 3, '$', 'samsung-galaxy-m54-5g.jpg', 'Tím'),
(16, 'Xiaomi 13T', 3, '															\r\n															', 213, 1, '$', 'xiaomi-13-t-xanh-duong.jpg', 'Xanh dương'),
(18, 'Nokia 8210 4G Đỏ', 7, '', 23, 123, '$', 'nokia 8210-4g-do.jpg', 'Đỏ'),
(19, 'IPhone 11', 1, 'asdasd', 232, 10, '$', 'iphone-11.jpg', 'Xanh'),
(20, 'IPhone 11', 1, 'asdaskjd', 3874, 2, '$', 'iphone-11.jpg', 'Đỏ'),
(25, 'Nokia C21 Plus', 7, '															Nokia c21 plus mau xanh duong												\r\n															', 2, 4, '$', 'nokia-c21-plus-blue.jpg', 'Xanh dương'),
(26, 'Nokia 105', 7, '															nokia 105 4g xanh duong														\r\n															', 21, 2, '$', 'nokia-105-4g-blue.jpg', 'Xanh dương');

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `ProductID` int(5) NOT NULL,
  `username` varchar(255) NOT NULL,
  `Comment` varchar(1000) NOT NULL,
  `sosao` int(11) DEFAULT NULL,
  `TimeReviews` char(255) NOT NULL,
  `time_reviews` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`ProductID`, `username`, `Comment`, `sosao`, `TimeReviews`, `time_reviews`) VALUES
(11, 'admin', 'Sản phẩm tuyệt vời', 5, '2023-11-02', NULL),
(11, 'admin', 'Sản phẩm tuyệt vời', 5, '2023-11-02', NULL),
(1, 'admin', 'Sản phẩm tuyệt vời', 5, '12:33:32 2023-33-02', NULL),
(18, 'admin', 'tuyệt vời', 5, '12:39:41   02-11-2023', NULL),
(20, 'admin', 'Sản phẩm tuyệt vời.', 5, '10:17:52   02-11-2023', NULL),
(20, 'admin', 'Sản phẩm tuyệt vời.', 5, '10:19:19   02-11-2023', NULL),
(20, 'admin', 'sdaskdjksa', 5, '10:19:53   02-11-2023', NULL),
(20, 'admin', 'sdsd', 3, '10:21:20   02-11-2023', NULL),
(5, 'admin', 'tuet', 5, '12:53:00   07-11-2023', NULL),
(5, 'admin', '', 3, '12:54:14   07-11-2023', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`OrderID`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id_cart`);

--
-- Indexes for table `cartdetail`
--
ALTER TABLE `cartdetail`
  ADD PRIMARY KEY (`OrderDetailID`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryid`);

--
-- Indexes for table `discount`
--
ALTER TABLE `discount`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ProductID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id_cart` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
