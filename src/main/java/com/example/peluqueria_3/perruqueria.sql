DROP DATABASE bugffmlodzmfiksquriy;
CREATE DATABASE bugffmlodzmfiksquriy;
USE bugffmlodzmfiksquriy;

create table trabajadores(
    DNI varchar(255) primary key,
    usuario varchar(255) not null,
    nombre varchar(255) not null,
    apellido varchar(255),
    correo varchar(255) not null,
    password varchar(255) not null,
    tel varchar(50),
    direccion varchar(255),
    comision_ventas float not null,
    comision_servicios float not null,
    limite_comision_servicios float not null,

    rol ENUM('administrador', 'empleado', 'invitado') NOT NULL,
    estado ENUM('Activo', 'Inactivo') NOT NULL,

    img varchar(255)
);

create table clientes(
    id_cliente int primary key AUTO_INCREMENT,
    nombre varchar(255) not null,
    apellido varchar(255),
    tel varchar(50),
    correo varchar(255),
    observaciones text,
    ley_datos boolean NOT NULL
);

create table productos (
    id_producto INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    marca varchar(255) NOT NULL,
    descripcion text,
    precio float NOT NULL,
    stock int not null,
    codigo_barras varchar(255)
);

create table servicios (
    id_servicio int primary key AUTO_INCREMENT,
    nombre varchar(255) not null,
    descripcion text not null,
    precio float not null
);

create table ventas (
    id_venta int primary key AUTO_INCREMENT,
    fk_id_trabajador varchar(255) not null,
    fecha date not null,

    FOREIGN KEY (fk_id_trabajador) REFERENCES trabajadores(DNI)
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

create table cobros(
    fk_id_cliente int not null,
    fk_id_servicio int,
    fk_id_trabajador varchar(255) not null,
    fk_id_producto int,
    fecha_cobro date not null,
    bizum float not null,
    tarjeta float not null,
    efectivo float not null,

    FOREIGN KEY (fk_id_cliente) REFERENCES clientes(id_cliente),
    FOREIGN KEY (fk_id_servicio) REFERENCES servicios(id_servicio),
    FOREIGN KEY (fk_id_trabajador) REFERENCES trabajadores(DNI),
    FOREIGN KEY (fk_id_producto) REFERENCES productos(id_producto)
);

create table agenda(
    id_agenda varchar(255) primary key,
    fk_id_trabajador varchar(255) not null,
    fecha varchar(255) not null,
    hora varchar(255) not null,
    descripcion text,

    FOREIGN KEY (fk_id_trabajador) REFERENCES trabajadores(DNI)
);

-- Inserts de los empleados
INSERT INTO trabajadores (DNI, usuario, nombre, apellido, correo, password, tel, direccion, comision_ventas, comision_servicios, limite_comision_servicios, rol, estado) VALUES
('123456789A', 'Administrador', 'NombreAdministrador', 'ApellidoAdministrador', 'Administrador@example.com', 'a', '123456789', 'Administrador', 1, 2, 3, 'administrador', 'Activo'),
('123434789A', 'Gabriel', 'NombreGabriel', 'ApellidoGabriel', 'Gabriel@example.com', '123', '123456789', 'calleGabriel', 2, 2, 2, 'administrador', 'Activo'),
('123498629E', 'Eva', 'NombreEva', 'ApellidoEva', 'Eva@example.com', '123', '123456789', 'calleEva', 3, 3, 3, 'administrador', 'Activo'),
('123457777O', 'Oriol', 'NombreOriol', 'ApellidoOriol', 'Oriol@example.com', '123', '123456789', 'calleOriol', 4, 4, 4, 'administrador', 'Activo'),
('123454982G', 'Gabriel', 'NombreAdmin', 'ApellidoAdmin', 'admin@example.com', '123', '123456789', 'calle', 1, 1, 2, 'empleado', 'Activo'),
('234956789J', 'Joaquin', 'NombreAdmin', 'ApellidoAdmin', 'admin@example.com', '123', '123456789', 'calle', 1, 2, 3, 'empleado', 'Activo');

-- Inserts servicios
INSERT INTO servicios (nombre, descripcion, precio) VALUES
('Cortar pelo', 'cortar el pelo', 10),
('Pintar el pelo', 'pintar el pelo', 20),
('Lavar el pelo', 'lavar el pelo', 6),
('Arreglar puntas', 'arreglar puntas del pelo', 8),
('Peinar', 'peinar el pelo', 8);

-- Inserts clientes
INSERT INTO clientes (nombre, apellido, tel, correo, observaciones, ley_datos) VALUES
('Cliente 1', 'Apellido Cliente 1', '111111111', 'cliente1@gmail.com', 'Observacion Cliente 1', true),
('Cliente 2', 'Apellido Cliente 2', '222222222', 'cliente2@gmail.com', 'Observacion Cliente 2', true),
('Cliente 3', 'Apellido Cliente 3', '333333333', 'cliente3@gmail.com', 'Observacion Cliente 3', true),
('Cliente 4', 'Apellido Cliente 4', '444444444', 'cliente4@gmail.com', 'Observacion Cliente 4', true),
('Cliente 5', 'Apellido Cliente 5', '555555555', 'cliente5@gmail.com', 'Observacion Cliente 5', true);

-- Inserts productos
INSERT INTO productos (nombre, marca, descripcion, precio, stock, codigo_barras) VALUES
('Producto 1', 'Marca 1', 'Descripcion 1', 1, 10, '111'),
('Producto 2', 'Marca 2', 'Descripcion 2', 2, 12, '222'),
('Producto 3', 'Marca 3', 'Descripcion 3', 3, 14, '333'),
('Producto 4', 'Marca 4', 'Descripcion 4', 4, 16, '444'),
('Producto 5', 'Marca 5', 'Descripcion 5', 5, 18, '555');