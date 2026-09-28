/*
DDL Proyecto PAY TO WIN
------------------------

Nombre: Juan Pablo Sierra Amariles
Curso: 1ºDAW
*/

-- Creación de las tablas--
------------------------------

create table PROYECTO_usuario (
    nombreUsuario varchar2(30) primary key not null,
    nombreReal varchar2(40) not null,
    correo varchar2(100) unique not null,
    passw varchar2(60) not null,
    fnac date not null, 
    tel number(13), --OPCIONAL
    ultconex date, --OPCIONAL
    tipo varchar2(20) not null,
    foto varchar2(30) --OPCIONAL
);

create table PROYECTO_direccion (
    codigo number(35) primary key not null,
    calle varchar2(255) not null,
    ciudad varchar2(100) not null,
    provincia varchar2(100) not null,
    cp number(5) not null,
    pais varchar2(100) not null,
    usuario varchar2(30) references PROYECTO_usuario not null,
    tipo varchar2(20) not null
);

create table PROYECTO_productos(
    codigo number(10) primary key not null,
    nombre varchar2(100) not null,
    precio number(10,5) not null,
    iva number(2) not null,
    stockMinimo number (10) not null,
    stock number(10) not null,
    foto varchar2(255) not null, 
    usuarioCrea varchar2(30)references proyecto_usuario not null,
    fechacrea date not null,
    usuarioModif varchar2(30) references proyecto_usuario, --OPCIONAL
    fechamodif date, --OPCIONAL
    descripcion varchar2(500) not null
);

create table PROYECTO_pedido(
    codigo number(10) primary key not null,
    fecha date not null,
    metpago varchar2(100) not null, 
    facturado varchar2(2) not null,
    direccion number(10) references PROYECTO_direccion not null,
    cliente varchar2(30) references PROYECTO_usuario not null
);

create table PROYECTO_lineaPedido(
    pedido number(10) references PROYECTO_pedido not null,
    productos number(10) references PROYECTO_productos not null,
    cantidad number(5) not null,
    precio number(10,2) not null,
    constraint pk_lineaPedido primary key (pedido, productos)
);

create table PROYECTO_factura(
    codigo number(10) primary key not null,
    fecha date not null,
    iva number(2) not null,
    pedido number(10) unique references PROYECTO_pedido not null,
    direcFactura number(10) references PROYECTO_direccion not null
);

create table PROYECTO_categoria(
    codigo number(10) primary key not null,
    nombre varchar2(30) not null,
    descripcion varchar2(500) not null
);

create table PROYECTO_clasificacionProducto(
    productos number(10) references PROYECTO_productos not null,
    categoria number(10) references PROYECTO_categoria not null,
    constraint pk_clasificacionProducto primary key (productos, categoria)
);

--Restricciones check--
-------------------------

--Especificamos que el campo 'tipo' de la tabla usuario solo pueda ser Cliente o Admin.--
alter table PROYECTO_usuario add check (tipo in ('Cliente','Admin'));

--Especificamos que el campo 'tipo' de la tabla direccion solo puede ser Envío o Facturación.--
alter table PROYECTO_direccion add check (tipo in ('Envío','Facturación'));

--Especificamos que el campo 'facturado' de la tabla pedido solo pueda ser SI o NO.--
alter table PROYECTO_pedido add check (facturado in ('SI','NO'));

--Especificamos que la fecha de Creación del producto debe ser mayor al 1 de enero de 2023.--
alter table PROYECTO_productos add check (fechacrea > to_date('01/01/2023', 'DD/MM/YYYY'));

--Especificamos que el campo 'metpago' de la tabla pedido debe ser Tarjeta, Paypal y Bizum.--
alter table PROYECTO_pedido add check (metpago in ('Tarjeta', 'Paypal', 'Bizum'));

--Eliminación de tablas--
---------------------------
/*drop table PROYECTO_clasificacionProducto;
drop table PROYECTO_categoria cascade constraints;
drop table PROYECTO_factura;
drop table PROYECTO_lineaPedido;
drop table PROYECTO_pedido cascade constraints;
drop table PROYECTO_productos cascade constraints;
drop table PROYECTO_direccion cascade constraints;
drop table PROYECTO_usuario cascade constraints;*/
