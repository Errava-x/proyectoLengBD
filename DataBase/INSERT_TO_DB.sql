/* ===================================== */
/* INSERTS COMPLETOS SISTEMA VETERINARIA */
/* ===================================== */

/* ===== 1. CLIENTE ===== */
INSERT INTO CLIENTES VALUES (100, 'Erick', 'Ramos', 'Perez');

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