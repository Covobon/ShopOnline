CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';

GRANT ALL PRIVILEGES ON * . * TO 'admin'@'localhost';

#
# Starting with MySQL 8.0.4, the MySQL team changed the 
# default authentication plugin for MySQL server 
# from mysql_native_password to caching_sha2_password.
#
# The command below will make the appropriate updates for your user account.
#
# See the MySQL Reference Manual for details: 
# https://dev.mysql.com/doc/refman/8.0/en/caching-sha2-pluggable-authentication.html
#
ALTER USER 'admin'@'localhost' IDENTIFIED WITH mysql_native_password BY 'admin';

create database `urd_smartshop`;

use `urd_smartshop`;

create table `user`(
	`user_name` varchar(45) not null,
    `password` varchar(45) not null,
    `full_name` varchar(45),
    `email` varchar(45),
    `address` varchar(255),
    `phone_number` varchar(45),
    `create_time` date,
    `last_access` date,
    primary key(`user_name`)    
);

create table `user_role`(
	`user_role_id` int not null auto_increment,
    `user_name` varchar(45) not null,
    `role` varchar(45) not null,
    primary key (`user_role_id`),
    unique key `uni_user_name_role` (`role`, `user_name`),
    foreign key (`user_name`) references `user` (`user_name`)
);

create table `product`(
	`product_id` varchar(45),
    `name` varchar(45),
    `price` int,
    `category` varchar(45),
    `status` varchar(45),
    `amount` int,
    `detail` text,
    primary key (`product_id`)
);

create table `product_image`(
	`product_id` varchar(45),
    `path` varchar(255),
    primary key (`product_id`, `path`),
    foreign key (`product_id`) references `product` (`product_id`)
);

create table `cart`(
	`cart_id` int,
	`user_name` varchar(45),
    `address` varchar(255),
    primary key (`user_name`),
    foreign key (`user_name`) references `user` (`user_name`)
);

create table `cart_product`(
	`user_name` varchar(45),
    `product_id` varchar(45),
    `amount` int,
    primary key (`user_name`, `product_id`),
    foreign key (`user_name`) references `cart` (`user_name`),
    foreign key (`product_id`) references `product` (`product_id`)
);

create table `order`(
	`order_id` int auto_increment primary key,
    `user_name` varchar(45),
    `status` varchar(255),
    `date` date,
    foreign key (`user_name`) references `user` (`user_name`)
);

create table `order_product`(
	`order_id` int,
    `product_id` varchar(45),
    `amount` int,
    primary key (`order_id`, `product_id`),
    foreign key (`product_id`) references `product` (`product_id`),
    foreign key (`order_id`) references `order`(`order_id`)
);

create table `news` (
	`new_id` int auto_increment primary key,
    `title` text,
    `content` text
);

create table `news_image`(
	`new_id` int,
    `path` varchar(255),
    primary key (`new_id`, `path`),
    foreign key (`new_id`)  references `news` (`new_id`)
);