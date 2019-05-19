create table shoe_users
(
	user_id integer auto_increment,
 	user_name varchar(1000),
	password text, 
	primary key(user_id)
);

create table user_shoes
(
	user_id integer,
	shoe_id integer,
);

create table shoes
(
	shoe_id integer auto_increment,
 	brand varchar(255),
	model varchar(255),
	distance integer,
	start_date date, 
	end_date date,
	description varchar(255),
	name varchar(255),
	size varchar(255),
	pic blob,
	primary key(shoe_id)
);

create table favourites
(
	user_id integer,
	shoe_id integer,
	description varchar(255)
);