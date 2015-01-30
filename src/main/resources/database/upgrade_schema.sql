create table if not exists sys_user(
	id int auto_increment,
	user_name varchar(32) not null,
	user_password varchar(128) not null,
	real_name varchar(32),
	roles varchar(64),
	create_time datetime,
	update_time datetime,
	create_by varchar(16),
	update_by varchar(16),
	primary key(id));
	