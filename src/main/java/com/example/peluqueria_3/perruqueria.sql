DROP DATABASE peluqueria;

CREATE DATABASE peluqueria;

USE peluqueria;

create table trabajadores(
    id_trabajador INT primary key AUTO_INCREMENT,
    usuarios varchar(255) not null,
    nombre varchar(255) not null,
    apellido varchar(255),
    correo varchar(255),
    tel int(9),
    direccion varchar(255),
    rol ENUM('administrador', 'empleado', 'invitado') NOT NULL,
    estado ENUM('Activo', 'Inactivo') NOT NULL
);

create table clientes(
    id_cliente INT primary key, -- La clave primaria es el numero de telefono
    nombre varchar(255) not null,
    apellido varchar(255),
    correo varchar(255),
    observaciones text,
    ley_datos boolean NOT NULL
);

create table productos (
    id_producto INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    marca varchar(255) not null,
    descripcion text,
    precio INT(8) NOT NULL,
    stock int(8) not null,
    codigo_barras varchar(255)
);

create table servicios (
    id_servicio int primary key AUTO_INCREMENT,
    nombre varchar(255) not null,
    descripcion text not null,
    fecha date not null,
    hora time not null,
    precio int(8)
);

create table ventas (
    id_venta int primary key AUTO_INCREMENT,
    fk_id_trabajador int(8) not null,
    fecha date not null,

    FOREIGN KEY (fk_id_trabajador) REFERENCES trabajadores(id_trabajador)
);

create table productos_ventas (
    id_producto_venta int primary key AUTO_INCREMENT,
    fk_id_venta int(8) not null,
    fk_id_producto int(8) not null,
    cantidad int(8) not null,
    precio_unidad int(8) not null,
    
    FOREIGN KEY (fk_id_venta) REFERENCES ventas(id_venta),
    FOREIGN KEY (fk_id_producto) REFERENCES productos(id_producto)
);

create table agenda(
    fk_id_cliente int not null,
    fk_id_servicio int not null,
    fk_id_trabajador int not null,

    FOREIGN KEY (fk_id_cliente) REFERENCES clientes(id_cliente),
    FOREIGN KEY (fk_id_servicio) REFERENCES servicios(id_servicio),
    FOREIGN KEY (fk_id_trabajador) REFERENCES trabajadores(id_trabajador)
);