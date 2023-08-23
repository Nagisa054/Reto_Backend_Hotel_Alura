#Este código pertenece a la base de datos del proyecto HotelAlura.

#En caso de se encuentre una base de datos
#con mismo nombre se eliminará para evitar
#conflictos con los siguientes comandos.

DROP DATABASE hotel_alura_db;

#Creación de la Base de Datos.
CREATE DATABASE hotel_alura_db;

#Se muestran todas las bases de datos que
#se encueran en el sistema.
#Este paso es opcional.
SHOW DATABASES;

#Entramos a la base hotel_alura_db
USE hotel_alura_db;

#Tabla Huespedes.
#Aquí se almacenan todos los huespedes que
#sean registrados en el sistema.
#Está enlazada con la tabla Reserva atrevés del
#campo: "reserva_id."
CREATE TABLE Huespedes(
	id INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(100) NOT NULL,
	apellido VARCHAR(100) NOT NULL,
	fecha_nacimiento DATE NOT NULL,
	nacionalidad VARCHAR(250) NOT NULL,
	telefono VARCHAR(20) NOT NULL,
	reserva_id INT NOT NULL,
	PRIMARY KEY (id)
	);

#Tabla Reserva.
#Aquí se almacenan las reservas que sean
#registradas en el sistema.
#Está relacionada con la tabla de "Huespedes"
#atrevés de campo: "id".
CREATE TABLE Reserva(
	id INT NOT NULL AUTO_INCREMENT,
	fecha_entrada DATE NOT NULL,
	fecha_salida DATE NOT NULL,
	valor FLOAT NOT NULL,
	forma_pago VARCHAR(50) NOT NULL,
	PRIMARY KEY(id)
);

#Tabla Empleado.
#Se usa para almacenar los usuarios y contraseñas
#usadas para poder ingresar al sistema.
CREATE TABLE Empleado(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    edad INT(3) NOT NULL,
    Puesto VARCHAR(150) DEFAULT 'Recepcionista',
    nombre_en_sistema VARCHAR(100),
    clave_en_sistema VARCHAR(20) DEFAULT '000000',
    PRIMARY KEY(id)
);

#Clave foránea para relacionar la tabla "Huespedes"
#con la tabla de Reserva, el campo "Huespedes.reserva_id"
#hace referencia a "Reserva.id".
ALTER TABLE Huespedes
ADD FOREIGN KEY (reserva_id) 
REFERENCES Reserva(id);

#Disparador "asignar_nombre_en_sistema" de la tabla Empleado.

#Si al insertar un nuevo registro, el campo
#"Empleado.nombre_en_sistema" es nulo ensconces el valor
#del campo "Empleado.nombre_en_sistema" será igual a
#el valor del campo Empleado.nombre.
DELIMITER //
CREATE TRIGGER asignar_nombre_en_sistema
BEFORE INSERT ON Empleado
FOR EACH ROW
BEGIN
   IF NEW.nombre_en_sistema IS NULL THEN
      SET NEW.nombre_en_sistema = NEW.nombre;
   END IF;
END;//
DELIMITER ;



#Mostramos todas las tablas creadas.
#Este paso es opcional
SHOW TABLES;

#A continuación se inserta un registro en
#cada una de las tabas para asegurarse de que
#todo funcione adecuadamente.

#Test Reserva.
INSERT INTO Reserva(
fecha_entrada,
fecha_salida,
valor,
forma_pago)
VALUES(
	'2000-10-10',
	'2000-10-15',
	150.5,
	'prueva'
);

#Test huespedes.
INSERT INTO Huespedes(
nombre,
apellido,
fecha_nacimiento,
nacionalidad,
telefono,
reserva_id)
VALUES(
'prueva',
'prueva',
'2000-10-10',
'prueva',
'+01 234567689',
1
);

#Test Empleado.
#Aquí no se inserta el campo "Empleado.nombre_en_sistema".
#Con esto nos aseguramos que el disparador funcione adecuadamente.
INSERT INTO Empleado(
    nombre,
    apellido,
    edad,
    Puesto,
    clave_en_sistema
) VALUES(
    'Juan',
    'Rojas',
    19,
    'Administrador',
    'Admin'
);

#Aquí listamos todos los registros guardados anteriormente.
SELECT * FROM Huespedes;
SELECT * FROM Reserva;
SELECT * FROM Empleado;

