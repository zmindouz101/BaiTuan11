create database qlsv
go
use qlsv
go
create table dssv
(
	id int primary key,
    ten nvarchar(50),
    tuoi int,
    mark float
)
go

insert into dssv value(1,'Hoang',23,9)
insert into dssv value(2,'Ngoc',23,9)
insert into dssv value(3,'Long',23,8)
insert into dssv value(4,'64121',23,9)
insert into dssv value(5,'LTM',23,8)