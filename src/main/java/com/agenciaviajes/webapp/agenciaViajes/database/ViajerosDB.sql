
use viajesDB;


select * from rutas;
select * from paradas;
select * from itinerarios;
select * from viajeros;
select * from vehiculos;
select * from ofertas;
select * from comentarios;
select * from usuarios;

INSERT INTO vehiculos(marca, modelo, capacidad, disponibilidad,tipo_vehiculo) VALUE 
('Toyota', 'Corolla', 'Sedán', TRUE,'Turismo compacto');
INSERT INTO rutas(nombre, distancia, duracion) VALUE 
('Ruta del Norte', '150 km', '03:00:00');
INSERT INTO paradas(nombre, ubicacion, tiempo, tipo_parada) VALUE 
('Estación Central', 'Calle Principal 123', '00:30:00', 'Temporal');

INSERT INTO VIAJEROS( apellido,correo, fecha_registro,nombre,telefono,itinerario_id) VALUE
('Carrera', 'giovannicarrera@gmail.com', '2024-05-04', 'Giovanni', '42819131', 1); 

INSERT INTO OFERTAS( fecha_final, fecha_inicio,tiempo, tipo_parada, ruta_id, vehiculo_id) VALUE
( '2024-09-29 2:00:00','2024-09-29 2:00:00','08:30:00.000','parada corta',1, 1); 
/* itinerario y comentario 

