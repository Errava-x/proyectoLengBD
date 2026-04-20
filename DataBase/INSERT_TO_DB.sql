/* ===================================== */
/* INSERTS COMPLETOS SISTEMA VETERINARIA */
/* ===================================== */


/* ===== 1. CLIENTE ===== */
INSERT INTO CLIENTES VALUES (100, 'Erick', 'Ramos', 'Vargas');

/* ===== 2. DATOS DEL CLIENTE ===== */
INSERT INTO CORREOS_CLIENTES VALUES (1, 'erick@gmail.com', 100);

INSERT INTO TELEFONOS_CLIENTES VALUES (1, '8888-9999', 100);

INSERT INTO MEDIOS_PREFERIDOS_CLIENTES VALUES (1, 'WhatsApp', 100);

INSERT INTO DIRECCIONES_CLIENTES VALUES (
    1,
    'San Jose',
    'Desamparados',
    'San Miguel',
    '200 metros norte de la iglesia',
    100
);

/* ===== 3. MASCOTA ===== */
INSERT INTO MASCOTAS VALUES (
    200,
    'Firulais',
    'Perro',
    'Labrador',
    3,
    TO_DATE('2022-05-10','YYYY-MM-DD'),
    'Activo',
    'Macho',
    'Cafe',
    25.50
);

/* Relación cliente-mascota */
INSERT INTO CLIENTES_MASCOTAS VALUES (1, 100, 200);

/* ===== 4. VENTA ===== */
INSERT INTO VENTAS VALUES (
    300,
    'Alimento Premium',
    'Consulta General',
    15000,
    15000,
    'Tarjeta',
    SYSDATE
);

/* Relación cliente-venta */
INSERT INTO CLIENTES_VENTAS VALUES (1, 100, 300);

/* ===== 5. VACUNA ===== */
INSERT INTO VACUNAS VALUES (
    400,
    'Rabia',
    'LOT123',
    'Pfizer',
    SYSDATE,
    SYSDATE + 365,
    'Primera dosis',
    200
);

/* ===== 6. MEDICO ===== */
INSERT INTO MEDICOS VALUES (500, 'Laura', 'Gomez', 'Solis');

INSERT INTO CORREOS_MEDICOS VALUES (1, 'laura@vet.com', 500);

INSERT INTO TELEFONOS_MEDICOS VALUES (1, '2222-3333', 500);

INSERT INTO ESPECIALIDADES_MEDICOS VALUES (1, 'Veterinaria General', 500);

INSERT INTO HORARIOS_MEDICOS VALUES (1, 'Lunes a Viernes 8am-5pm', 500);

/* Relación médico-vacuna */
INSERT INTO MEDICOS_VACUNAS VALUES (1, 500, 400);

/* ===== 7. CITA ===== */
INSERT INTO CITAS VALUES (
    600,
    SYSDATE,
    '10:00',
    'Consulta general',
    'Programada',
    'Chequeo anual',
    200
);

/* Relación médico-cita */
INSERT INTO MEDICOS_CITAS VALUES (1, 500, 600);

/* ===== GUARDAR CAMBIOS ===== */
COMMIT;

/* ===== CONSULTAS DE VERIFICACIÓN ===== */
CREATE OR REPLACE VIEW VW_RESUMEN_SISTEMA AS
SELECT 
    c.ID_CLIENTE,
    c.NOMBRE || ' ' || c.PRIMER_APELLIDO || ' ' || c.SEGUNDO_APELLIDO AS CLIENTE,

    m.ID_MASCOTA,
    m.NOMBRE AS MASCOTA,
    m.ESPECIE,
    m.RAZA,

    med.ID_MEDICO,
    med.NOMBRE || ' ' || med.PRIMER_APELLIDO AS MEDICO,

    v.ID_VENTA,
    v.PRODUCTO,
    v.MONTO_TOTAL,

    ci.ID_CITA,
    ci.FECHA,
    ci.TIPO_CONSULTA,
    ci.ESTADO

FROM CLIENTES c

LEFT JOIN CLIENTES_MASCOTAS cm 
    ON c.ID_CLIENTE = cm.ID_CLIENTE

LEFT JOIN MASCOTAS m 
    ON cm.ID_MASCOTA = m.ID_MASCOTA

LEFT JOIN CLIENTES_VENTAS cv 
    ON c.ID_CLIENTE = cv.ID_CLIENTE

LEFT JOIN VENTAS v 
    ON cv.ID_VENTA = v.ID_VENTA

LEFT JOIN CITAS ci 
    ON m.ID_MASCOTA = ci.ID_MASCOTA

LEFT JOIN MEDICOS_CITAS mc 
    ON ci.ID_CITA = mc.ID_CITA

LEFT JOIN MEDICOS med 
    ON mc.ID_MEDICO = med.ID_MEDICO;
    
SELECT * FROM  vw_resumen_sistema



--------------------------------------------------
-----------------MAS INSERTS----------------------
--------------------------------------------------
--CLIENTES
INSERT INTO CLIENTES
SELECT LEVEL,
       INITCAP(
         CASE MOD(LEVEL,10)
           WHEN 0 THEN 'juan'
           WHEN 1 THEN 'maria'
           WHEN 2 THEN 'carlos'
           WHEN 3 THEN 'ana'
           WHEN 4 THEN 'luis'
           WHEN 5 THEN 'sofia'
           WHEN 6 THEN 'jose'
           WHEN 7 THEN 'valeria'
           WHEN 8 THEN 'andres'
           ELSE 'diana'
         END),
       INITCAP(
         CASE MOD(LEVEL,10)
           WHEN 0 THEN 'rodriguez'
           WHEN 1 THEN 'lopez'
           WHEN 2 THEN 'martinez'
           WHEN 3 THEN 'hernandez'
           WHEN 4 THEN 'garcia'
           WHEN 5 THEN 'ramirez'
           WHEN 6 THEN 'vargas'
           WHEN 7 THEN 'castro'
           WHEN 8 THEN 'mora'
           ELSE 'solis'
         END),
       INITCAP(
         CASE MOD(LEVEL,10)
           WHEN 0 THEN 'perez'
           WHEN 1 THEN 'jimenez'
           WHEN 2 THEN 'ruiz'
           WHEN 3 THEN 'soto'
           WHEN 4 THEN 'navarro'
           WHEN 5 THEN 'arias'
           WHEN 6 THEN 'chavez'
           WHEN 7 THEN 'cordero'
           WHEN 8 THEN 'leon'
           ELSE 'alvarado'
         END)
FROM DUAL
CONNECT BY LEVEL <= 10;

-- Correos

INSERT INTO CORREOS_CLIENTES
SELECT SEQ_CORREO_CLIENTE.NEXTVAL,
       LOWER('cliente' || LEVEL || '@gmail.com'),
       LEVEL
FROM DUAL
CONNECT BY LEVEL <= 10;

-- Telefonos
INSERT INTO TELEFONOS_CLIENTES
SELECT SEQ_TELEFONO_CLIENTE.NEXTVAL,
       '8' || TRUNC(DBMS_RANDOM.VALUE(1000000,9999999)),
       LEVEL
FROM DUAL
CONNECT BY LEVEL <= 10;

-- Direcciones

INSERT INTO DIRECCIONES_CLIENTES
SELECT LEVEL,
       CASE MOD(LEVEL,5)
         WHEN 0 THEN 'San Jose'
         WHEN 1 THEN 'Cartago'
         WHEN 2 THEN 'Alajuela'
         WHEN 3 THEN 'Heredia'
         ELSE 'Puntarenas'
       END,
       'Canton ' || LEVEL,
       'Distrito ' || LEVEL,
       '200m norte del parque, casa ' || LEVEL,
       LEVEL
FROM DUAL
CONNECT BY LEVEL <= 10;

-- Mascotas

INSERT INTO MASCOTAS
SELECT LEVEL,
       CASE MOD(LEVEL,6)
         WHEN 0 THEN 'Max'
         WHEN 1 THEN 'Luna'
         WHEN 2 THEN 'Rocky'
         WHEN 3 THEN 'Milo'
         WHEN 4 THEN 'Nala'
         ELSE 'Toby'
       END,
       CASE MOD(LEVEL,2)
         WHEN 0 THEN 'Perro'
         ELSE 'Gato'
       END,
       CASE MOD(LEVEL,4)
         WHEN 0 THEN 'Labrador'
         WHEN 1 THEN 'Criollo'
         WHEN 2 THEN 'Pastor Aleman'
         ELSE 'Siames'
       END,
       TRUNC(DBMS_RANDOM.VALUE(1,12)),
       SYSDATE - TRUNC(DBMS_RANDOM.VALUE(100,2000)),
       'ACTIVO',
       CASE MOD(LEVEL,2)
         WHEN 0 THEN 'Macho'
         ELSE 'Hembra'
       END,
       CASE MOD(LEVEL,5)
         WHEN 0 THEN 'Negro'
         WHEN 1 THEN 'Blanco'
         WHEN 2 THEN 'Cafe'
         WHEN 3 THEN 'Gris'
         ELSE 'Dorado'
       END,
       ROUND(DBMS_RANDOM.VALUE(2,40),2)
FROM DUAL
CONNECT BY LEVEL <= 10;

-- Vacunas

INSERT INTO VACUNAS
SELECT LEVEL,
       CASE MOD(LEVEL,4)
         WHEN 0 THEN 'Rabia'
         WHEN 1 THEN 'Parvovirus'
         WHEN 2 THEN 'Moquillo'
         ELSE 'Leptospirosis'
       END,
       'L' || TRUNC(DBMS_RANDOM.VALUE(1000,9999)),
       CASE MOD(LEVEL,3)
         WHEN 0 THEN 'Pfizer'
         WHEN 1 THEN 'Moderna'
         ELSE 'Bayer'
       END,
       SYSDATE - TRUNC(DBMS_RANDOM.VALUE(1,200)),
       SYSDATE + TRUNC(DBMS_RANDOM.VALUE(30,365)),
       'Aplicada sin complicaciones',
       LEVEL
FROM DUAL
CONNECT BY LEVEL <= 10;

--  Medicos
INSERT INTO MEDICOS
SELECT LEVEL,
       CASE MOD(LEVEL,5)
         WHEN 0 THEN 'Daniel'
         WHEN 1 THEN 'Laura'
         WHEN 2 THEN 'Fernando'
         WHEN 3 THEN 'Paula'
         ELSE 'Ricardo'
       END,
       'Gomez',
       'Rojas'
FROM DUAL
CONNECT BY LEVEL <= 10;

-- Especialidades
INSERT INTO ESPECIALIDADES_MEDICOS
SELECT LEVEL,
       CASE MOD(LEVEL,4)
         WHEN 0 THEN 'Veterinaria General'
         WHEN 1 THEN 'Cirugia'
         WHEN 2 THEN 'Dermatologia'
         ELSE 'Odontologia'
       END,
       LEVEL
FROM DUAL
CONNECT BY LEVEL <= 10;

-- Citas
ALTER TRIGGER TRG_VALIDAR_FECHA_CITA DISABLE;
INSERT INTO CITAS
SELECT LEVEL,
       SYSDATE + TRUNC(DBMS_RANDOM.VALUE(1,30)),
       LPAD(TRUNC(DBMS_RANDOM.VALUE(8,17)),2,'0') || ':00',
       'Consulta veterinaria',
       CASE MOD(LEVEL,3)
         WHEN 0 THEN 'PROGRAMADA'
         WHEN 1 THEN 'COMPLETADA'
         ELSE 'CANCELADA'
       END,
       'Revision general',
       LEVEL
FROM DUAL
CONNECT BY LEVEL <= 10;
ALTER TRIGGER TRG_VALIDAR_FECHA_CITA ENABLE;
commit;

-- Vista total

CREATE OR REPLACE VIEW VW_VER_TODO AS
SELECT 
    c.ID_CLIENTE,
    c.NOMBRE || ' ' || c.PRIMER_APELLIDO || ' ' || c.SEGUNDO_APELLIDO AS NOMBRE_CLIENTE,

    co.DESCRIPCION AS CORREO,
    t.NUMERO_TELEFONO,
    d.PROVINCIA,
    d.CANTON,
    d.DISTRITO,

    m.ID_MASCOTA,
    m.NOMBRE AS NOMBRE_MASCOTA,
    m.ESPECIE,
    m.RAZA,
    m.EDAD,
    m.ESTADO,

    v.ID_VACUNA,
    v.TIPO_VACUNA,
    v.FECHA_APLICACION,

    ci.ID_CITA,
    ci.FECHA AS FECHA_CITA,
    ci.HORA,
    ci.ESTADO AS ESTADO_CITA,

    med.ID_MEDICO,
    med.NOMBRE || ' ' || med.PRIMER_APELLIDO AS NOMBRE_MEDICO,
    esp.NOMBRE_ESPECIALIDAD,

    ve.ID_VENTA,
    ve.PRODUCTO,
    ve.SERVICIO,
    ve.MONTO_TOTAL,
    ve.METODO_PAGO

FROM CLIENTES c

-- CONTACTO CLIENTE
LEFT JOIN CORREOS_CLIENTES co 
    ON c.ID_CLIENTE = co.ID_CLIENTE

LEFT JOIN TELEFONOS_CLIENTES t 
    ON c.ID_CLIENTE = t.ID_CLIENTE

LEFT JOIN DIRECCIONES_CLIENTES d 
    ON c.ID_CLIENTE = d.ID_CLIENTE

-- CLIENTE ? MASCOTA
LEFT JOIN CLIENTES_MASCOTAS cm 
    ON c.ID_CLIENTE = cm.ID_CLIENTE

LEFT JOIN MASCOTAS m 
    ON cm.ID_MASCOTA = m.ID_MASCOTA

-- MASCOTA ? VACUNAS
LEFT JOIN VACUNAS v 
    ON m.ID_MASCOTA = v.ID_MASCOTA

-- MASCOTA ? CITAS
LEFT JOIN CITAS ci 
    ON m.ID_MASCOTA = ci.ID_MASCOTA

-- CITA ? MEDICO
LEFT JOIN MEDICOS_CITAS mc 
    ON ci.ID_CITA = mc.ID_CITA

LEFT JOIN MEDICOS med 
    ON mc.ID_MEDICO = med.ID_MEDICO

LEFT JOIN ESPECIALIDADES_MEDICOS esp 
    ON med.ID_MEDICO = esp.ID_MEDICO

-- CLIENTE ? VENTAS
LEFT JOIN CLIENTES_VENTAS cv 
    ON c.ID_CLIENTE = cv.ID_CLIENTE

LEFT JOIN VENTAS ve 
    ON cv.ID_VENTA = ve.ID_VENTA;
    
SELECT * FROM VW_VER_TODO;    

commit;
