drop database if exists osahaneat;
create database osahaneat;

use osahaneat;

create table Roles(
	id int auto_increment,
    role_name varchar(20),
    create_date timestamp,
    primary key(id)
);

create table Users(
	id int auto_increment,
    user_name varchar(100),
    password varchar(100),
    full_name varchar(50),
    create_date timestamp,
    role_id int,
    primary key(id)
);

create table RatingFood(
	id int auto_increment,
    user_id int,
    food_id int,
    content text,
    rate_point int(5),
    
    primary key(id)
);

create table Category(
	id int auto_increment,
    name_cate varchar(100),
    create_date timestamp,
    primary key(id)
);

create table Food(
	id int auto_increment,
    title varchar(255),
    image text,
    time_ship varchar(10),
    price decimal,
    cate_id int,
    primary key(id)
);


create table RatingRestaurant(
	id int auto_increment,
    user_id int,
    res_id int,
    content text,
    rate_point int(5),
    
    primary key(id)
);


create table Orders(
	id int auto_increment,
    user_id int,
    res_id int,
    create_date timestamp,
    primary key(id)
);


create table MenuRestaurant(
	cate_id int,
    res_id int,
    create_date timestamp,
    
    primary key(res_id,cate_id)
);
 
-- done
create table Restaurant(
	id int auto_increment,
    title varchar(255),
    subtitle varchar(255),
    description text,
    image text,
    is_freeship boolean,
    address varchar(255),
    open_date timestamp,
    
    primary key(id)
);


create table Promo(
	id int auto_increment,
    res_id int,
    percent int,
    start_date timestamp,
    end_date timestamp,
    primary key(id)
);

create table OrderItem(
	order_id  int,
    food_id int,
    quantity int,
    create_date timestamp,
    
    primary key(order_id,food_id)
);


alter table Users add constraint FK_USERS_ROLE_ID foreign key(role_id) references Roles(id);
alter table RatingFood add constraint FK_RATINGFOOD_USER_ID foreign key(user_id) references Users(id);
alter table RatingFood add constraint FK_RATINGFOOD_food_ID foreign key(food_id) references Food(id);
alter table Food add constraint fk_food_cate_id foreign key(cate_id) references Category(id);
alter table RatingRestaurant add constraint fk_ratingrestaurant_user_id foreign key(user_id) references Users(id);
alter table RatingRestaurant add constraint fk_ratingrestaurant_res_id foreign key(res_id) references Restaurant(id);
alter table Orders add constraint fk_orders_user_id foreign key(user_id) references Users(id);
alter table Orders add constraint fk_orders_res_id foreign key(res_id) references Restaurant(id);
alter table OrderItem add constraint fk_orderitem_order_id foreign key(order_id) references Orders(id);
alter table OrderItem add constraint fk_orderitem_food_id foreign key(food_id) references Food(id);
alter table MenuRestaurant add constraint fk_menurestaurant_cate_id foreign key(cate_id) references Category(id);
alter table MenuRestaurant add constraint fk_menurestaurant_res_id foreign key(res_id) references Restaurant(id);
alter table Promo add constraint fk_promo_res_id foreign key(res_id) references Restaurant(id);


-- Insert roles
INSERT INTO Roles (role_name, create_date) VALUES
('Admin', NOW()),
('Customer', NOW());

-- Insert users
INSERT INTO Users (user_name, password, full_name, create_date, role_id) VALUES
('admin_user', 'admin123', 'Admin User', NOW(), 1),
('customer1', 'password1', 'John Doe', NOW(), 2),
('customer2', 'password2', 'Jane Smith', NOW(), 2);

-- Insert categories
INSERT INTO Category (name_cate, create_date) VALUES
('Beverages', NOW()),
('Fast Food', NOW()),
('Desserts', NOW());

-- Insert food
INSERT INTO Food (title, image, time_ship, price, cate_id) VALUES
('Coke', 'coke.jpg', '15 mins', 1.5, 1),
('Burger', 'burger.jpg', '20 mins', 5.0, 2),
('Ice Cream', 'icecream.jpg', '10 mins', 2.0, 3);

-- Insert restaurants
INSERT INTO Restaurant (title, subtitle, description, image, is_freeship, address, open_date) VALUES
('Pizza Palace', 'Best Pizza in Town', 'Serving delicious pizzas since 1990.', 'pizza_palace.jpg', TRUE, '123 Main St.', NOW()),
('Burger Bistro', 'All About Burgers', 'Juicy burgers made fresh to order.', 'burger_bistro.jpg', FALSE, '456 Elm St.', NOW());

-- Insert ratings for food
INSERT INTO RatingFood (user_id, food_id, content, rate_point) VALUES
(2, 1, 'Great taste!', 5),
(3, 2, 'Could be better.', 3);

-- Insert ratings for restaurants
INSERT INTO RatingRestaurant (user_id, res_id, content, rate_point) VALUES
(2, 1, 'Excellent service!', 5),
(3, 2, 'Not bad, but room for improvement.', 4);

-- Insert orders
INSERT INTO Orders (user_id, res_id, create_date) VALUES
(2, 1, NOW()),
(3, 2, NOW());

-- Insert order items
INSERT INTO OrderItem (order_id, food_id, quantity, create_date) VALUES
(1, 1, 2, NOW()),
(2, 2, 1, NOW());

-- Insert menu for restaurants
INSERT INTO MenuRestaurant (cate_id, res_id, create_date) VALUES
(1, 1, NOW()),
(2, 2, NOW());

-- Insert promos
INSERT INTO Promo (res_id, percent, start_date, end_date) VALUES
(1, 20, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY)),
(2, 10, NOW(), DATE_ADD(NOW(), INTERVAL 5 DAY));

select * from users;
