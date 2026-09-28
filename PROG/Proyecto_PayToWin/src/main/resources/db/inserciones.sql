/*
DML Proyecto PAY TO WIN
------------------------

Nombre: Juan Pablo Sierra Amariles
Curso: 1ºDAW
*/

--Inserción de contenido de la tabla PROYECTO_usuario--
----------------------------------------------------------
insert into PROYECTO_usuario values ('JScorpioDv', 'Juan Pablo', 'juanpablo@gmail.com', '$2a$10$rcSB0f1oySK5kHF0T5r2zOsp4sgPQ/cgh58Fqz0m6UQHNbDvgY652', to_date('02/06/2002','DD/MM/YYYY'), 781235946, to_date('06/02/2023','DD/MM/YYYY'), 'Admin', null);

insert into PROYECTO_usuario values ('ElTerceroDelMundo', 'Chema', 'chema@gmail.com', '$2a$10$d6OuxpPrmR1GlgRRkbN68ur5EBQc/ncAb73ZysXmNfO7Y66WxHyKq', to_date('09/11/2005','DD/MM/YYYY'), 632542874, null, 'Cliente', null);

insert into PROYECTO_usuario values ('LaVillenera', 'Diana', 'diana@gmail.com', '$2a$10$d6OuxpPrmR1GlgRRkbN68ur5EBQc/ncAb73ZysXmNfO7Y66WxHyKq', to_date('15/07/1999','DD/MM/YYYY'), 642587139, null, 'Cliente', null);

insert into PROYECTO_usuario values ('BrunoyLuis', 'Pedro', 'pedro@gmail.com', '$2a$10$d6OuxpPrmR1GlgRRkbN68ur5EBQc/ncAb73ZysXmNfO7Y66WxHyKq', to_date('07/09/1979','DD/MM/YYYY'), 743923415, null, 'Cliente', null);

insert into PROYECTO_usuario values ('Lenna', 'Silvia', 'silvia@gmail.com', '$2a$10$d6OuxpPrmR1GlgRRkbN68ur5EBQc/ncAb73ZysXmNfO7Y66WxHyKq', to_date('12/10/2003','DD/MM/YYYY'), 651238549, null, 'Cliente', null);


--Inserción de contenido de la tabla PROYECTO_direccion--
------------------------------------------------------------
insert into PROYECTO_direccion values (1, 'C\ Tejeda, 18', 'Elda', 'Alicante', 03600, 'España', 'ElTerceroDelMundo', 'Facturación');

insert into PROYECTO_direccion values (2, 'C\ Principal, 2', 'San Fernando de Apure', 'Apure', 7001, 'Venezuela', 'BrunoyLuis', 'Facturación');

insert into PROYECTO_direccion values (3, 'C\ San Fernando, 73', 'Villena', 'Alicante', 03400, 'España', 'LaVillenera', 'Envío');

insert into PROYECTO_direccion values (4, 'Av. Bassa Perico, 118', 'Petrer', 'Alicante', 03610, 'España', 'Lenna', 'Envío');


--Inserción de contenido de la tabla PROYECTO_productos--
----------------------------------------------------------
--Videojuegos--

insert into PROYECTO_productos values (1, 'Dead Space Remake', 49.99, 21, 0, 320, 'deadSpace.jpg', 'JScorpioDv', to_date('28/01/2023','DD,MM,YYYY'), 'JScorpioDv', to_date('07/02/2023','DD,MM,YYYY'), 
'La tripulación ha sido masacrada por «necromorfos», monstruos creados por una forma de vida alienígena. El protagonista, Isaac Clarke, tiene que enfrentarse a los «necromorfos» en solitario.');

insert into PROYECTO_productos values (2, 'God of War Ragnarok', 59.99, 21, 0, 154, 'godOfWar.jpeg', 'JScorpioDv', to_date('28/01/2023','DD,MM,YYYY'), 'JScorpioDv', to_date('09/02/2023','DD,MM,YYYY'), 
'La jugabilidad es similar a la entrega anterior, God of War , y al igual que ese juego, es solo para un jugador. A lo largo del juego, los jugadores luchan contra enemigos mitológicos nórdicos, 
con más tipos de enemigos que en el juego anterior. Además de los enemigos que se encuentran en el juego anterior, algunos tipos de enemigos más nuevos incluyen Einherjars, guivernos, acechadores , fantasmas, asaltantes humanos y nokkens, entre muchos otros.');

insert into PROYECTO_productos values (3, 'Alan Wake II', 39.99, 21, 0, 284, 'alanWake2.jpeg', 'JScorpioDv', to_date('28/01/2023','DD,MM,YYYY'), 'JScorpioDv', to_date('09/02/2023','DD,MM,YYYY'), 
'Alan Wake regresa por todo lo alto. El aclamado thriller psicológico vuelve a ponernos en la piel del novelista con tendencia a meterse en problemas muy serios, siendo mucho más que una secuela: según la propia Remedy, Alan Wake 2 es su primer videojuego de survival horror.');

insert into PROYECTO_productos values (4, 'Dead Island 2', 69.99, 21, 0, 0, 'deadIsland2.jpg', 'JScorpioDv', to_date('28/01/2023','DD,MM,YYYY'), 'JScorpioDv', to_date('09/02/2023','DD,MM,YYYY'), 
'¡La secuela de Dead Island no estaba muerta! Es más, Deep Silver se ha tomado muy en serio lo de convertir la ciudad de Los Ángeles en el lugar ideal para experimentar un post-apocalipsis zombi tras 
el episodio final de The Walking Dead. Otra cosa es que logremos sobrevivir en él. En todo caso, no será por falta de armas.');

--Ordenadores--

insert into PROYECTO_productos values (5, 'Ordenador Intel i7-10700 con 4,8 GHz/16 GB RAM/Intel UHD 630/1 TB Seagate', 989.99, 21, 0, 132, 'ordenador (1).png', 'JScorpioDv', to_date('28/01/2023','DD,MM,YYYY'), null, null, 
'- Procesador: Intel i7-10700 con 4,8 GHz.<br />
- Memoria RAM: 16 GB RAM.<br />
- Gráficos: Intel UHD 630.<br />
- Almacenamiento: 1 TB Seagate');

insert into PROYECTO_productos values (6, 'Ordenador AMD Ryzen 5 5600G/32 GB RAM/NVIDIA 1650 Super/1 TB Seagate', 1699.99, 21, 0, 147, 'descarga (2).png', 'JScorpioDv', to_date('28/01/2023','DD,MM,YYYY'), 'JScorpioDv', to_date('09/02/2023','DD,MM,YYYY'), 
'- Procesador: AMD Ryzen 5 5600G.<br />
- Memoria RAM: 32 GB RAM.<br />
- Gráficos: NVIDIA 1650 Super.<br />
- Almacenamiento: 1,5 TB Seagate.');

insert into PROYECTO_productos values (7, 'Ordenador intel i7-10700 con 4,8 GHz/16 GB RAM/RADEON RX 6700 12GB AMD/2 TB Seagate', 2195.99, 21, 0, 15, 'imagenGrande1.png', 'JScorpioDv', to_date('28/01/2023','DD,MM,YYYY'), 'JScorpioDv', to_date('09/02/2023','DD,MM,YYYY'), 
'- Procesador: intel i7-10700 con 4,8 GHz.<br />
- Memoria RAM: 16 GB RAM.<br />
- Gráficos: RADEON RX 6700 12GB AMD.<br />
- Almacenamiento: 2 TB Seagate.');

--Consolas--

insert into PROYECTO_productos values (8, 'PlayStation 5', 559.99, 21, 0, 10, '51469696886_9cb9e251a5_o-scaled.jpg', 'JScorpioDv', to_date('28/01/2023','DD,MM,YYYY'), 'JScorpioDv', to_date('09/02/2023','DD,MM,YYYY'), 
'- Procesador: AMD Jaguar 16 núcleos 2,13 GHz<br />
- Memoria RAM: 16 GB RAM.<br />
- Gráficos: Vídeo resolución 1080p.<br />
- Almacenamiento: 1TB.');


insert into PROYECTO_productos values (9, 'PlayStation 4 Pro', 215.00, 21, 0, 93, 'descarga (1).png', 'JScorpioDv', to_date('28/01/2023','DD,MM,YYYY'), 'JScorpioDv', to_date('09/02/2023','DD,MM,YYYY'), 
'- Procesador: AMD Jaguar 8 núcleos 2,13 GHz.<br />
- Memoria RAM: 8 GB RAM.<br />
- Gráficos: Vídeo resolución 1080p.<br />
- Almacenamiento: 500GB.');

insert into PROYECTO_productos values (10, 'Xbox Series', 459.99, 21, 0, 105, 'b90ad58f-9950-44a7-87fa-1ee8f0b6a90e.png', 'JScorpioDv', to_date('28/01/2023','DD,MM,YYYY'), 'JScorpioDv', to_date('09/02/2023','DD,MM,YYYY'), 
'- Procesador: CPU Zen 2 de 8 núcleos 3,4 GHz.<br />
- Memoria RAM: 16 GB RAM.<br />
- Gráficos: Vídeo resolución 4k.<br />
- Almacenamiento: 910 GB');


--Inserción de contenido a la tabla PROYECTO_pedido--
-------------------------------------------------------
insert into PROYECTO_pedido values (1, to_date('12/03/2022','DD/MM/YYYY'), 'Tarjeta', 'NO', 4, 'Lenna');

insert into PROYECTO_pedido values (2, to_date('02/06/2022','DD/MM/YYYY'), 'Bizum', 'SI', 2, 'BrunoyLuis');

insert into PROYECTO_pedido values (3, to_date('31/01/2023','DD/MM/YYYY'), 'Tarjeta', 'NO', 1, 'ElTerceroDelMundo');

insert into PROYECTO_pedido values (4, to_date('11/02/2023','DD/MM/YYYY'), 'Tarjeta', 'NO', 3, 'LaVillenera');

insert into PROYECTO_pedido values (5, to_date('01/02/2023','DD/MM/YYYY'), 'Paypal', 'SI', 3, 'LaVillenera');


--Inserción de contenido a la tabla PROYECTO_lineaPedido--
------------------------------------------------------------
insert into PROYECTO_lineaPedido values (1, 1, 1, 49.99);

insert into PROYECTO_lineaPedido values (1, 7, 1, 2195.99);

insert into PROYECTO_lineaPedido values (3, 7, 2, 4391.98);

insert into PROYECTO_lineaPedido values (2, 2, 1, 59.99);


--Inserción de contenido a la tabla PROYECTO_factura--
--------------------------------------------------------
insert into PROYECTO_factura values (1, to_date('02/06/2022','DD/MM/YYYY'), 21, 2, 2);

insert into PROYECTO_factura values (2, to_date('13/03/2022','DD/MM/YYYY'), 21, 1, 4);

insert into PROYECTO_factura values (3, to_date('11/02/2023','DD/MM/YYYY'), 21, 4, 3);

insert into PROYECTO_factura values (4, to_date('31/01/2023','DD/MM/YYYY'), 21, 3, 1);

insert into PROYECTO_factura values (5, to_date('01/02/2023','DD/MM/YYYY'), 21, 5, 3);


--Inserción de contenido a la tabla PROYECTO_categoria--
-----------------------------------------------------------
insert into PROYECTO_categoria values (1, 'Ordenadores', ' El equipo que mereces está aquí. Una selección con los mejores ordenadores que puedes encontrar en el mercado. Lo tienes todos en PayToWin. Los mejores componentes:<br />

Tarjetas gráficas, procesadores, memorias RAM... El pc de tus sueños está aqui mismo, ven y elige el tuyo. ');

insert into PROYECTO_categoria values (2, 'Videojuegos', 'Las últimas novedades y juegos clásicos de las consolas y ordenadores. En físico, y ahora también en digital (códigos de descarga). Disfruta de una gran variedad de títulos y ediciones coleccionista de los últimos lanzamientos y mejores jugadores que les gusta quedar con amigos para jugar.¡Para vosotros, jugadores!. ');

insert into PROYECTO_categoria values (3, 'Consolas', 'Ya está aquí la nueva generación de consolas, la PS5, la XBOX... Reservalo ya en PayToWin y recibela el 19 de noviembre en casa. No esperes más ¡están volando! ');


--Inserción de contenido a la tabla PROYECTO_clasificacionProducto--
----------------------------------------------------------------------
--Ordenadores--

insert into PROYECTO_clasificacionProducto values (5, 1);

insert into PROYECTO_clasificacionProducto values (6, 1);

insert into PROYECTO_clasificacionProducto values (7, 1);

--Videojuegos--

insert into PROYECTO_clasificacionProducto values (1, 2);

insert into PROYECTO_clasificacionProducto values (2, 2);

insert into PROYECTO_clasificacionProducto values (3, 2);

insert into PROYECTO_clasificacionProducto values (4, 2);

--Consolas--

insert into PROYECTO_clasificacionProducto values (8, 3);

insert into PROYECTO_clasificacionProducto values (9, 3);

insert into PROYECTO_clasificacionProducto values (10, 3);


--Modificaciones--
--------------------
--Modificar que la ultima conexión del usuario 'Lenna' fue el 10 de febrero.--
update PROYECTO_usuario set ultconex= to_date('10/02/2023','DD/MM/YYYY') where nombreUsuario like 'Lenna';

--Modificar que el tipo será 'Envió' en la dirección 1.--
update PROYECTO_direccion set tipo= 'Envío' where codigo=1;

--Modificarle el usuaro y fecha de la última modificación del producto 'O1'.--
update PROYECTO_productos set usuarioModif=usuarioCrea where codigo=5;
update PROYECTO_productos set fechamodif= to_date('11/02/2023', 'DD/MM/YYYY') where codigo= 5;

--Modificar el campo 'facturado' a 'SI' en el pedido 3.--
update PROYECTO_pedido set facturado='SI' where codigo=3;


--Eliminación de filas--
-------------------------

--Eliminar el ultimo pedido con su factura asignada.--
delete PROYECTO_factura where codigo=5;
delete PROYECTO_pedido where codigo=5;

--Nota: Primero hay que borrar la factura porque la factura usa la clave primaria del pedido para su creación.--
