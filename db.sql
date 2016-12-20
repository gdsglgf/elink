
-- sogout_data.3.comp
t_directory
	dir_id
	dir_name

-- part-m-00000.bz2
t_batch
	batch_id
	batch_name

t_location
	loc_id
	disk_id
	dir_id
	batch_id

t_html
	html_id
	url
	docno
	title
	keywords
	description

	loc_id
	rank

t_parser_log
	log_id
	loc_id
	html_amount
	cost_time(miliseconds)


create table t_directory(
	dir_id int(10) not null primary key,
	dir_name varchar(32) not null unique
);

create table t_batch(
	batch_id int(10) not null primary key,
	batch_name varchar(32) not null unique
);

create table t_location(
	loc_id bigint(20) unsigned not null auto_increment primary key,
	disk_id int(10),
	dir_id int(10),
	batch_id int(10)
);

create table t_html(
	html_id bigint(20) unsigned not null auto_increment primary key,
	docno char(66) not null unique,
	url text not null,
	title text not null default '',
	keywords text not null default '',
	description text not null default '',

	loc_id bigint(20) unsigned not null,
	rank int(10)
);

create table t_parser_log(
	log_id int(10) not null auto_increment primary key,
	loc_id bigint(20) unsigned not null,
	html_amount int(10),
	cost_time int(10)
);