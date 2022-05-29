DROP DATABASE IF EXISTS dbcompusac;
CREATE DATABASE `dbcompusac`;
USE dbcompusac;
-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-05-2022 a las 02:19:50
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbcompusac`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorys`
--

CREATE TABLE `categorys` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categorys`
--

INSERT INTO `categorys` (`id`, `name`, `description`) VALUES
(1, 'Laptops', 'laptops'),
(2, 'Computadoras', 'Computadoras'),
(3, 'Impresoras', 'Impresoras'),
(4, 'Teclados', 'Teclados');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `offers`
--

CREATE TABLE `offers` (
  `id` int(11) NOT NULL,
  `banner` varchar(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `subtitle` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `product` int(11) NOT NULL,
  `expirate` datetime NOT NULL,
  `status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `offers`
--

INSERT INTO `offers` (`id`, `banner`, `title`, `subtitle`, `description`, `product`, `expirate`, `status`) VALUES
(1, 'hero-1.jpg', 'Accesorios de computo', 'Al por mayor y menor', 'Para el regreso a clase y oficinas, somos el aliado número 1', 2, '2022-12-31 00:00:00', 'A'),
(2, 'hero-2.jpg', 'Respaldo y garantia', 'Más de 10 años', 'Operamos más de 10 años en el mercado', 1, '2022-12-31 00:00:00', 'A'),
(3, 'hero-3.jpg', 'Oferton del año', 'Paquete completo', 'LLevelo que no se arrepentira y se lo garantizo', 5, '2022-12-31 00:00:00', 'A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `fecha_recibida` datetime DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `total` double NOT NULL,
  `usuario_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `orders`
--

INSERT INTO `orders` (`id`, `fecha_creacion`, `fecha_recibida`, `numero`, `total`, `usuario_id`) VALUES
(1, '2022-05-27 19:03:23', NULL, '0000000001', 15700, 5),
(2, '2022-05-27 19:08:35', NULL, '0000000002', 500, 5),
(3, '2022-05-27 19:09:37', NULL, '0000000003', 2400, 5),
(4, '2022-05-27 21:11:05', NULL, '0000000004', 11000, 6),
(5, '2022-05-27 21:22:23', NULL, '0000000005', 2000, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `order_details`
--

CREATE TABLE `order_details` (
  `id` bigint(20) NOT NULL,
  `cantidad` double NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double NOT NULL,
  `total` double NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `order_details`
--

INSERT INTO `order_details` (`id`, `cantidad`, `nombre`, `precio`, `total`, `order_id`, `product_id`) VALUES
(1, 1, 'Computadora', 2100, 2100, 1, 4),
(2, 1, 'Computadora', 3500, 3500, 1, 6),
(3, 1, 'Laptop', 500, 500, 1, 1),
(4, 4, 'Computadora', 2400, 9600, 1, 5),
(5, 1, 'Laptop', 500, 500, 2, 1),
(6, 1, 'Computadora', 2400, 2400, 3, 5),
(7, 1, 'Impresora', 900, 900, 4, 7),
(8, 1, 'Laptop', 3600, 3600, 4, 3),
(9, 1, 'Computadora', 3500, 3500, 4, 6),
(10, 2, 'Laptop', 1500, 3000, 4, 2),
(11, 1, 'Laptop', 500, 500, 5, 1),
(12, 1, 'Laptop', 1500, 1500, 5, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persons`
--

CREATE TABLE `persons` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telephone` int(11) DEFAULT NULL,
  `register_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `persons`
--

INSERT INTO `persons` (`id`, `name`, `last_name`, `email`, `telephone`, `register_date`) VALUES
(1, 'omar', 'pardo', 'omar123@gmail.com', NULL, NULL),
(4, 'ricardo', 'mendoza', 'ricardo123@gmail.com', NULL, NULL),
(5, 'JUAN', 'CARDENAS', 'ROCAJOSUE98@GMAIL.COM', NULL, NULL),
(6, 'JOSUE', 'ROCA', 'josueroca98@gmail.com', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `products`
--

CREATE TABLE `products` (
  `id` int(5) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `category` int(5) DEFAULT NULL,
  `tag` varchar(200) DEFAULT NULL,
  `number_serie` int(11) DEFAULT NULL,
  `product_information` varchar(500) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `size` varchar(50) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `material` varchar(500) DEFAULT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `expirate` datetime DEFAULT NULL,
  `product` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `products`
--

INSERT INTO `products` (`id`, `name`, `category`, `tag`, `number_serie`, `product_information`, `price`, `size`, `color`, `material`, `banner`, `description`, `expirate`, `product`, `status`, `subtitle`, `title`) VALUES
(1, 'Laptop', 1, 'lap', 1234567890, 'Windows 10, Corei7', 500, '14*', 'negro', 'aluminio', 'legion_2.jpg', NULL, NULL, 0, NULL, NULL, NULL),
(2, 'Laptop', 1, 'lap', 1234567891, 'LAPTOP LENOVO V14-ARE, AMD RYZEN 5 4500', 1500, '14', 'Gris', 'Aluminio', 'laptop-lenovo-amd-ryzen-5-4500.jpg', NULL, NULL, 0, NULL, NULL, NULL),
(3, 'Laptop', 1, 'lap', 1234567892, 'LAPTOP LENOVO YOGA SLIM, CI5-1135G7, 15.6\"', 3600, '15', 'Negro', 'Aluminio', 'laptop-lenovo-ci5.jpg', NULL, NULL, 0, NULL, NULL, NULL),
(4, 'Computadora', 2, 'pc', 1234567800, 'Pc Gaming Plus Amd: RYZEN 5 5600g', 2100, NULL, 'Negro', NULL, 'pc-gaming-plus-amd-ryzen-5-5600g.jpg', NULL, NULL, 0, NULL, NULL, NULL),
(5, 'Computadora', 2, 'Pc', 1234567801, 'Pc Gaming Plus Amd: RYZEN 7 5700g, 16GB, SSD 500GB', 2400, NULL, 'Negro', 'Aluminio', 'pc-gaming-plus-amd-ryzen-7-5700g.jpg', NULL, NULL, 0, NULL, NULL, NULL),
(6, 'Computadora', 2, 'Pc', 1234567802, 'Pc Gaming Plus Intel: Ci5-11400F, 16GB, SSD 512GB, RTX 2060', 3500, NULL, 'Negro', NULL, 'pc-gaming-plus-intel-ci5-11400f.jpg', NULL, NULL, 0, NULL, NULL, NULL),
(7, 'Impresora', 3, 'Impr', 1234567803, 'IMPRESORA MULTIFUNCIONAL HP SMART TANK 530 PLUS ADF WIRELESS', 900, NULL, 'Negro', NULL, 'Impresora-hp-smart-tank530plus.jpg', NULL, NULL, 0, NULL, NULL, NULL),
(8, 'Impresora', 3, 'Impr', 1234567804, 'IMPRESORA MULTIFUNCIONAL EPSON ECOTANK L5190 TINTA', 1150, NULL, 'Negro', NULL, 'impresora-epson-ecotank-l5190.jpg', NULL, NULL, 0, NULL, NULL, NULL),
(9, 'Teclado', 4, 'tec', 1234567805, 'TECLADO RAZER HIBRIDO ORNATA V2 SP CHROMA BLACK', 310, NULL, 'Negro', 'Aluminio', 'teclado-razer-hibrido.jpg', NULL, NULL, 0, NULL, NULL, NULL),
(10, 'Teclado', 4, 'tec', 1234567806, 'TECLADO MECANICO INALAMBRICO LOGITECH G915 TLK LIGHTSPEED RGB', 730, NULL, 'Negro', 'Aluminio', 'teclado-logitech-g615.jpg', NULL, NULL, 0, NULL, NULL, NULL),
(11, 'Teclado', 4, 'tec', 1234567807, 'TECLADO MECANICO HYPERX ALLOY ORIGINS CORE, SWITCH BLUE, RGB', 325, NULL, 'Negro', 'Aluminio', 'teclado-hyperx-alloy.jpg', NULL, NULL, 0, NULL, NULL, NULL),
(12, 'PC de Escritorio', 2, 'PC', 1234567807, 'PC WORKSTATION PARA TODO TIPO DE TRABAJO DE INGINIERIA', 5000, NULL, 'Negro', 'Metal', 'pc_03.jpg', NULL, NULL, 0, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product_details`
--

CREATE TABLE `product_details` (
  `id` bigint(20) NOT NULL,
  `product` int(11) NOT NULL,
  `description` text DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `main` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `product_details`
--

INSERT INTO `product_details` (`id`, `product`, `description`, `image`, `main`) VALUES
(1, 1, 'ss', 'legion_2.jpg', 1),
(2, 2, 'asa', 'laptop-lenovo-amd-ryzen-5-4500.jpg', NULL),
(3, 3, 'aa', 'laptop-lenovo-ci5.jpg', NULL),
(4, 4, 'aaa', 'pc-gaming-plus-amd-ryzen-5-5600g.jpg', NULL),
(5, 5, 'aaaa', 'pc-gaming-plus-amd-ryzen-7-5700g.jpg', NULL),
(6, 6, 'aaaaa', 'pc-gaming-plus-intel-ci5-11400f.jpg', NULL),
(7, 7, 'bb', 'Impresora-hp-smart-tank530plus.jpg', NULL),
(8, 8, 'bbb', 'impresora-epson-ecotank-l5190.jpg', NULL),
(9, 9, 'cc', 'teclado-razer-hibrido.jpg', NULL),
(10, 10, 'ccc', 'teclado-logitech-g615.jpg', NULL),
(11, 11, 'cccc', 'teclado-hyperx-alloy.jpg', NULL),
(12, 12, 'ss', 'pc_03.jpg', 1),
(13, 12, 's', 'pc_05.jpg', NULL),
(14, 12, 'c', 'pc_06.jpg', NULL),
(15, 12, 'd', 'pc_07.jpg', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sales`
--

CREATE TABLE `sales` (
  `id` int(11) NOT NULL,
  `product` int(11) NOT NULL,
  `unit_price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `user` int(11) NOT NULL,
  `date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spring_session`
--

CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spring_session_attributes`
--

CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stocks`
--

CREATE TABLE `stocks` (
  `id` int(5) NOT NULL,
  `product` int(5) NOT NULL,
  `historical_quantity` int(8) DEFAULT NULL,
  `actual_quantity` int(8) DEFAULT NULL,
  `selled` int(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `user_pass` varchar(100) NOT NULL,
  `person` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `user_name`, `user_pass`, `person`) VALUES
(1, 'omar123@gmail.com', '123', 1),
(4, 'ricardo123@gmail.com', '123', 4),
(5, 'ROCAJOSUE98@GMAIL.COM', '123', 5),
(6, 'josueroca98@gmail.com', '123', 6);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorys`
--
ALTER TABLE `categorys`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `offers`
--
ALTER TABLE `offers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `offers_fk` (`product`);

--
-- Indices de la tabla `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKa102nnp0x6it9ei3my87cvnga` (`usuario_id`);

--
-- Indices de la tabla `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`),
  ADD KEY `FK4q98utpd73imf4yhttm3w0eax` (`product_id`);

--
-- Indices de la tabla `persons`
--
ALTER TABLE `persons`
  ADD PRIMARY KEY (`id`),
  ADD KEY `person_name_IDX` (`name`) USING BTREE,
  ADD KEY `person_last_name_IDX` (`last_name`) USING BTREE,
  ADD KEY `person_email_IDX` (`email`) USING BTREE,
  ADD KEY `person_telephone_IDX` (`telephone`) USING BTREE;

--
-- Indices de la tabla `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_fk` (`category`),
  ADD KEY `product_name_IDX` (`name`) USING BTREE,
  ADD KEY `product_product_information_IDX` (`product_information`) USING BTREE,
  ADD KEY `product_number_serie_IDX` (`number_serie`) USING BTREE,
  ADD KEY `product_tag_IDX` (`tag`) USING BTREE;

--
-- Indices de la tabla `product_details`
--
ALTER TABLE `product_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_details_fk` (`product`);

--
-- Indices de la tabla `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sales_fk` (`product`),
  ADD KEY `sales_fk_1` (`user`);

--
-- Indices de la tabla `spring_session`
--
ALTER TABLE `spring_session`
  ADD PRIMARY KEY (`PRIMARY_ID`),
  ADD UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  ADD KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  ADD KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`);

--
-- Indices de la tabla `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`);

--
-- Indices de la tabla `stocks`
--
ALTER TABLE `stocks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `stock_fk` (`product`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_fk` (`person`),
  ADD KEY `user_user_name_IDX` (`user_name`) USING BTREE,
  ADD KEY `user_user_pass_IDX` (`user_pass`) USING BTREE;

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorys`
--
ALTER TABLE `categorys`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `offers`
--
ALTER TABLE `offers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `persons`
--
ALTER TABLE `persons`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `products`
--
ALTER TABLE `products`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `product_details`
--
ALTER TABLE `product_details`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `sales`
--
ALTER TABLE `sales`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `stocks`
--
ALTER TABLE `stocks`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `offers`
--
ALTER TABLE `offers`
  ADD CONSTRAINT `offers_fk` FOREIGN KEY (`product`) REFERENCES `products` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `product_fk` FOREIGN KEY (`category`) REFERENCES `categorys` (`id`);

--
-- Filtros para la tabla `product_details`
--
ALTER TABLE `product_details`
  ADD CONSTRAINT `product_details_fk` FOREIGN KEY (`product`) REFERENCES `products` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `sales_fk` FOREIGN KEY (`product`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `sales_fk_1` FOREIGN KEY (`user`) REFERENCES `users` (`id`);

--
-- Filtros para la tabla `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE;

--
-- Filtros para la tabla `stocks`
--
ALTER TABLE `stocks`
  ADD CONSTRAINT `stock_fk` FOREIGN KEY (`product`) REFERENCES `products` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `user_fk` FOREIGN KEY (`person`) REFERENCES `persons` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
