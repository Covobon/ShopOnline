CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';

GRANT ALL PRIVILEGES ON * . * TO 'admin'@'localhost';

ALTER USER 'admin'@'localhost' IDENTIFIED WITH mysql_native_password BY 'admin';

drop database `urd_smartshop`;
create database `urd_smartshop`;

use `urd_smartshop`;

create table `user`(
	`user_name` varchar(45) not null,
    `password` varchar(255) not null,
    `full_name` varchar(45),
    `email` varchar(45),
    `address` varchar(255),
    `phone_number` varchar(45),
    `create_time` datetime,
    `last_access` datetime,
    primary key(`user_name`)    
);

create table `user_role`(
    `user_name` varchar(45) not null,
    `role` varchar(45) not null,
    primary key `uni_user_name_role` (`role`, `user_name`),
    foreign key (`user_name`) references `user` (`user_name`)
);

create table `product`(
	`product_id` varchar(100),
    `name` varchar(100),
    `price` int,
    `category` varchar(45),
    `status` varchar(45),
    `amount` int,
    primary key (`product_id`)
);

create table `product_detail`(
    `product_id` varchar(100) primary key,
    `screen` varchar(45),
    `os` varchar(45),
    `camera` varchar(45),
    `cpu` varchar(45),
    `ram` varchar(45),
    `hard_disk` varchar(45),
    `battery` varchar(45),
    `detail` text,
    foreign key (`product_id`) references `product` (`product_id`)
);

create table `product_image`(
<<<<<<< HEAD
	`product_id` varchar(100),
    `name_image` varchar(255),
    primary  key (`product_id`, `name_image`),
=======
	`product_id` varchar(45),
    `name_image` varchar(255) primary key,
>>>>>>> 73c94e86acec21654d1cb5a133224032f7722dd4
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
    `product_id` varchar(100),
    `amount` int,
    primary key (`user_name`, `product_id`),
    foreign key (`user_name`) references `cart` (`user_name`),
    foreign key (`product_id`) references `product` (`product_id`)
);

create table `order`(
	`order_id` int auto_increment primary key,
    `user_name` varchar(45),
    `status` varchar(255),
    `datetime` datetime,
    foreign key (`user_name`) references `user` (`user_name`)
);

create table `order_product`(
	`order_id` int,
    `product_id` varchar(100),
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
    `name_image` varchar(255) primary key,
    foreign key (`new_id`)  references `news` (`new_id`)
);