-- =====================================================================
-- DML-script: testdata til projekt3_gruppe_3_db
-- Køres EFTER database.SQL (DDL), som opretter databasen og tabellerne.
--
-- Datoer beregnes ud fra CURDATE(), så aktive lejeaftaler altid er aktive
-- og afsluttede altid er afsluttede, uanset hvornår scriptet køres.
-- =====================================================================

USE projekt3_gruppe_3_db;


-- ---------------------------------------------------------------------
-- Brugere (én pr. rolle + demo/demo, som opgaven kræver)
-- ---------------------------------------------------------------------
INSERT INTO user (user_id, username, password, name, role) VALUES
    (1, 'demo',       'demo', 'Demo Bruger',          'BUSINESS_DEVELOPER'),
    (2, 'datareg',    '1234', 'Sofie Dataregistrering', 'DATA_REGISTRATION'),
    (3, 'skade',      '1234', 'Mads Skadesgennemgang',  'DAMAGE_AND_REPAIR'),
    (4, 'forretning', '1234', 'Laura Forretning',       'BUSINESS_DEVELOPER');


-- ---------------------------------------------------------------------
-- Biler (10 i alt)
-- Fordeling: AVAILABLE 4, RENTED 3, RETURNED 1, DAMAGED 1, READY_FOR_TRANSPORT 1
-- ---------------------------------------------------------------------
INSERT INTO car (vehicle_number, chassis_number, brand, model, color, status) VALUES
    (1,  'WVWZZZAUZPW100001', 'Volkswagen', 'Golf',      'Hvid',   'AVAILABLE'),
    (2,  'VF1RJA00068100002', 'Renault',    'Clio',      'Rød',    'AVAILABLE'),
    (3,  'TMBJJ7NE8L0100003', 'Skoda',      'Octavia',   'Grå',    'AVAILABLE'),
    (4,  'KNAB2512AMT100004', 'Kia',        'Picanto',   'Blå',    'AVAILABLE'),
    (5,  'YV1XZK9V6P2100005', 'Volvo',      'XC40',      'Sort',   'RENTED'),
    (6,  'WBA11AA0X0P100006', 'BMW',        'i4',        'Hvid',   'RENTED'),
    (7,  'VR3UPHNSKP1100007', 'Peugeot',    '208',       'Gul',    'RENTED'),
    (8,  'JTDKB3FU503100008', 'Toyota',     'Yaris',     'Sølv',   'RETURNED'),
    (9,  'WF0XXXGCDXP100009', 'Ford',       'Puma',      'Grøn',   'DAMAGED'),
    (10, 'TMBJR7NY1P0100010', 'Skoda',      'Enyaq',     'Blå',    'READY_FOR_TRANSPORT');


-- ---------------------------------------------------------------------
-- Kunder (fiktive)
-- ---------------------------------------------------------------------
INSERT INTO customer (customer_id, first_name, last_name, email, phone, address) VALUES
    (1, 'Anders', 'Jensen',      'anders.jensen@example.dk',  '20123456', 'Testvej 1, 2605 Brøndby'),
    (2, 'Maria',  'Nielsen',     'maria.nielsen@example.dk',  '30234567', 'Prøvegade 12, 2100 København Ø'),
    (3, 'Jonas',  'Hansen',      'jonas.hansen@example.dk',   '40345678', 'Eksempelvej 7, 2500 Valby'),
    (4, 'Emma',   'Pedersen',    'emma.pedersen@example.dk',  '50456789', 'Demoallé 3, 2650 Hvidovre'),
    (5, 'Lucas',  'Andersen',    'lucas.andersen@example.dk', '60567890', 'Fiktivvej 22, 2300 København S'),
    (6, 'Freja',  'Christensen', 'freja.c@example.dk',        NULL,       NULL);


-- ---------------------------------------------------------------------
-- Lejeaftaler
--  1-3: AKTIVE (startdato <= i dag <= slutdato)  -> biler med status RENTED
--  4-6: AFSLUTTEDE (slutdato før i dag)          -> RETURNED, DAMAGED, READY_FOR_TRANSPORT
--  7:   Gammel afsluttet aftale på en bil, der nu er ledig igen (historik)
-- Alle oprettet af dataregistrering (user_id 2)
-- ---------------------------------------------------------------------
INSERT INTO rental_agreement
    (agreement_id, vehicle_number, customer_id, created_by, pickup_location,
     start_date, end_date, monthly_price, subscription_type) VALUES
    (1, 5,  1, 2, 'Bilabonnement, Vibeholmsvej 31, Brøndby',
        DATE_SUB(CURDATE(), INTERVAL 2 MONTH), DATE_ADD(CURDATE(), INTERVAL 10 MONTH), 5499.00, 'UNLIMITED'),
    (2, 6,  2, 2, 'FDM Roskilde',
        DATE_SUB(CURDATE(), INTERVAL 5 MONTH), DATE_ADD(CURDATE(), INTERVAL 1 MONTH),  6999.00, 'LIMITED'),
    (3, 7,  3, 2, 'Bilabonnement, Vibeholmsvej 31, Brøndby',
        DATE_SUB(CURDATE(), INTERVAL 10 DAY),  DATE_ADD(CURDATE(), INTERVAL 3 MONTH),  2999.00, 'LIMITED'),

    (4, 8,  4, 2, 'FDM Aarhus',
        DATE_SUB(CURDATE(), INTERVAL 6 MONTH), DATE_SUB(CURDATE(), INTERVAL 2 DAY),    3299.00, 'LIMITED'),
    (5, 9,  5, 2, 'Bilabonnement, Vibeholmsvej 31, Brøndby',
        DATE_SUB(CURDATE(), INTERVAL 12 MONTH), DATE_SUB(CURDATE(), INTERVAL 7 DAY),   4199.00, 'UNLIMITED'),
    (6, 10, 6, 2, 'FDM Odense',
        DATE_SUB(CURDATE(), INTERVAL 4 MONTH), DATE_SUB(CURDATE(), INTERVAL 14 DAY),   5999.00, 'UNLIMITED'),

    (7, 1,  2, 2, 'Bilabonnement, Vibeholmsvej 31, Brøndby',
        DATE_SUB(CURDATE(), INTERVAL 15 MONTH), DATE_SUB(CURDATE(), INTERVAL 3 MONTH), 3499.00, 'LIMITED');


-- ---------------------------------------------------------------------
-- Skadesrapporter (udarbejdet af skade og udbedring, user_id 3)
--  Aftale 4 (bil 8, RETURNED) har endnu ingen rapport: afventer gennemgang.
--  Aftale 5 (bil 9, DAMAGED):             rapport med skader.
--  Aftale 6 (bil 10, READY_FOR_TRANSPORT): rapport med en enkelt mindre skade.
--  Aftale 7 (bil 1, AVAILABLE igen):       gammel rapport uden skader.
-- ---------------------------------------------------------------------
INSERT INTO damage_report (report_id, agreement_id, created_by, report_date) VALUES
    (1, 5, 3, DATE_SUB(CURDATE(), INTERVAL 5 DAY)),
    (2, 6, 3, DATE_SUB(CURDATE(), INTERVAL 12 DAY)),
    (3, 7, 3, DATE_SUB(CURDATE(), INTERVAL 3 MONTH));


-- ---------------------------------------------------------------------
-- Skader med vejledende pris
-- ---------------------------------------------------------------------
INSERT INTO damage (damage_id, report_id, description, price) VALUES
    (1, 1, 'Ridse i højre fordør',         1800.00),
    (2, 1, 'Stenslag i forrude',            950.00),
    (3, 1, 'Buleret bagkofanger',          3200.00),
    (4, 2, 'Fælgskade, venstre forhjul',    600.00);
