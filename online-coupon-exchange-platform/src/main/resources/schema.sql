-- Create database
CREATE DATABASE couponExchangePlatform;

-- Switch to the new database
USE couponExchangePlatform;

-- Create the roles table
CREATE TABLE IF NOT EXISTS `roles` (
    `role_id` int NOT NULL AUTO_INCREMENT,
    `role_name` varchar(50) NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `created_by` varchar(50) NOT NULL,
    `updated_at` TIMESTAMP DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`role_id`)
);

CREATE TABLE IF NOT EXISTS `address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `address1` varchar(200) NOT NULL,
  `address2` varchar(200) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `zip_code` int NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
   PRIMARY KEY (`address_id`)
);

-- Create the brands table
CREATE TABLE IF NOT EXISTS `brands` (
    `brand_id` int NOT NULL AUTO_INCREMENT,
    `brand_name` varchar(50) NOT NULL,
    `brand_logo` varchar(200) null,
    PRIMARY KEY (`brand_id`)
);

-- Create the person table
CREATE TABLE IF NOT EXISTS `person` (
    `person_id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `email` varchar(50) NOT NULL,
    `mobile_number` varchar(20) NOT NULL,
    `pwd` varchar(200) NOT NULL,
    `role_id` int NOT NULL,
    `address_id`int NULL,
    `created_at` TIMESTAMP NOT NULL,
    `created_by` varchar(50) NOT NULL,
    `updated_at` TIMESTAMP DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`person_id`),
    FOREIGN KEY (role_id) REFERENCES roles(role_id),
    FOREIGN KEY (address_id) REFERENCES address(address_id)
);

-- Create the coupon table
CREATE TABLE IF NOT EXISTS `coupon` (
    `coupon_id` int NOT NULL AUTO_INCREMENT,
    `code` varchar(255) DEFAULT NULL,
    `coupon_type` varchar(500) NOT NULL,
    `discount_value` varchar(20) NOT NULL,
    `expiry_date` date DEFAULT NULL,
    `min_purchase_amount` int,
    `description` varchar(1000),
    `person_id` int NOT NULL,
    `brand_id` int NOT NULL,
    `used_id` int DEFAULT NULL,
    `shop_now` varchar(500) DEFAULT NULL,
    PRIMARY KEY (`coupon_id`),
    FOREIGN KEY (person_id) REFERENCES person(person_id),
    FOREIGN KEY (brand_id) REFERENCES brands(brand_id),
    FOREIGN KEY (used_id) REFERENCES person(person_id)
);

CREATE TABLE IF NOT EXISTS `contact_msg` (
  `contact_id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `mobile_num` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `message` varchar(500) NOT NULL,
  `status` varchar(10) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `person_coupons` (
  `person_id` int NOT NULL,
  `coupon_id` int NOT NULL,
  FOREIGN KEY (person_id) REFERENCES person(person_id),
  FOREIGN KEY (coupon_id) REFERENCES coupon(coupon_id),
   PRIMARY KEY (`person_id`,`coupon_id`)
);
