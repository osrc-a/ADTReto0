-- drop database BD_ADT;
drop database BD_ADT;
-- Crear la base de datos
create database BD_ADT;

-- Entrar en la base de datos
use BD_ADT;

-- Crear las tablas
create table enunciado (
	id int primary key,
    descripcion varchar(100) not null,
    nivel varchar(10) not null,
    disponible boolean not null,
    ruta varchar(50) not null);
    
create table unidadDidactica (
	id int primary key,
    acronimo varchar(20) not null,
    titulo varchar(20) not null,
    evaluacion varchar(20) not null,
    descripcion varchar(100) not null);
    
create table convocatoriaExamen (
    id int primary key,
    convocatoria varchar (50) not null,
    descripcion varchar (100) not null,
    fecha date not null,
    curso varchar(50) not null,
    idEnunciado int,
    foreign key (idEnunciado) references enunciado(id)
    );
    
    
create table tiene (
	idUnidad int,
    idEnunciado int,
    primary key(idUnidad, idEnunciado),
    foreign key (idUnidad) references unidadDidactica(id),
	foreign key (idEnunciado) references enunciado(id));