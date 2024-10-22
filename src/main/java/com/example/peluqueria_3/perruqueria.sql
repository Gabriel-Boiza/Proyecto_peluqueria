DROP DATABASE bugffmlodzmfiksquriy;

CREATE DATABASE bugffmlodzmfiksquriy;

USE bugffmlodzmfiksquriy;

create table trabajadores(
    id_trabajador int primary key AUTO_INCREMENT,
    usuario varchar(255) not null,
    nombre varchar(255) not null,
    apellido varchar(255),
    correo varchar(255) not null,
    password varchar(255) not null,
    tel varchar(50),
    direccion varchar(255),

    rol ENUM('administrador', 'empleado', 'invitado') NOT NULL,
    estado ENUM('Activo', 'Inactivo') NOT NULL
);

create table clientes(
    id_cliente int primary key, -- La clave primaria es el numero de telefono
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
    precio int NOT NULL,
    stock int not null,
    codigo_barras varchar(255)
);

create table servicios (
    id_servicio int primary key AUTO_INCREMENT,
    nombre varchar(255) not null,
    descripcion text not null,
    fecha date not null,
    hora time not null,
    precio int
);

create table ventas (
    id_venta int primary key AUTO_INCREMENT,
    fk_id_trabajador int not null,
    fecha date not null,

    FOREIGN KEY (fk_id_trabajador) REFERENCES trabajadores(id_trabajador)
);

create table productos_ventas (
    id_producto_venta int primary key AUTO_INCREMENT,
    fk_id_venta int not null,
    fk_id_producto int not null,
    cantidad int not null,
    precio_unidad int not null,
    
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

INSERT INTO trabajadores (usuario, nombre, apellido, correo, password, tel, direccion, rol, estado)
VALUES ('admin', 'NombreAdmin', 'ApellidoAdmin', 'admin@example.com', '123', '123456789', 'calle', 'administrador', 'Activo');