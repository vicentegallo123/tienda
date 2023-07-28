show databases;

create database test1;

use test1;

create table producto (
  codigo int,
  nombre varchar(50),
  precio int,
  presentacion varchar(50),
  primary key(codigo)
);

