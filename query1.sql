create database project;
use project;
create table Inventory(
item_name varchar(255) primary key,
model varchar(255),
quantity int,
estimatedValue double,
consumable boolean
);
create table borrow(student_name varchar(255), item_name varchar(255), quantity int
);
drop table Inventory;
select * from Inventory;
select * from borrow;
create table Technician(
username varchar(255),
userid varchar(255),
pass varchar(255) not null
);
select * from Technician;
drop table borrow;
select * from Inventory;
create table Student(
student_name varchar(255),
username varchar(255) default "university id",
pass varchar(255) not null,
phone varchar(255) not null,
itemBorrowed varchar(255) default "no item"
);
Insert into Inventory values ('compass','new',10, 55,'2008-11-11',false);




