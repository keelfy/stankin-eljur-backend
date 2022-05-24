---- Credentials
-- Password: 'admin'

-- System
INSERT INTO credentials_tab (id, email, enabled, locked, password, roles, username)
VALUES (
           nextval('credentials_id_seq'),
           'keelfy@gmail.com',
           'true', false,
           '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
           'system', 'keelfy'
       );

-- Teacher
INSERT INTO credentials_tab (id, first_name, "second_name", "middle_name", email, enabled, locked, password, roles, username, department)
VALUES (
           nextval('credentials_id_seq'),
           'Иван', 'Иванов', 'Иванович',
           'teacher@test.test',
           'true', false,
           '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
           'teacher', 'teacher',
           (SELECT id FROM department_tab WHERE "short_name" = 'УиИвТС')
       );

-- Admin
INSERT INTO credentials_tab (id, email, enabled, locked, password, roles, username, department)
VALUES (
           nextval('credentials_id_seq'),
           'admin@test.test',
           'true', false,
           '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
           'admin', 'admin',
           (SELECT id FROM department_tab WHERE "short_name" = 'УиИвТС')
       );

-- Students
INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
           'Егор', 'Кузьмин', 'Олегович',
           'kuzmin@test.test',
           'true', false,
           '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
           'student', 'kuzmin',
           (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );

INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
           'Леонид', 'Алексеев', 'Дмитриевич',
           'alekseev@test.test',
           'true', false,
           '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
           'student', 'alekseev',
           (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );

INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
           'Елизавета', 'Белова', 'Юрьевна',
           'belova@test.test',
           'true', false,
           '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
           'student', 'belova',
           (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );

INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
        'Никита', 'Воронин', 'Сергеевич',
        'voronin@test.test',
        'true', false,
        '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
        'student', 'voronin',
        (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );

INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
        'Анастасия', 'Крылова', 'Андреевна',
        'krilova@test.test',
        'true', false,
        '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
        'student', 'krilova',
        (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );

INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
        'Магамедрасул', 'Гаджиев', 'Гасанович',
        'gadzhiev@test.test',
        'true', false,
        '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
        'student', 'gadzhiev',
        (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );

INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
        'Андрей', 'Гончаров', 'Алексеевич',
        'goncharov@test.test',
        'true', false,
        '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
        'student', 'goncharov',
        (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );

INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
        'Александр', 'Дума', 'Андреевич',
        'duma@test.test',
        'true', false,
        '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
        'student', 'duma',
        (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );

INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
        'Наталья', 'Журавлева', 'Алексеевна',
        'zhuravleva@test.test',
        'true', false,
        '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
        'student', 'zhuravleva',
        (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );

INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
        'Максим', 'Яковлев', 'Дмитриевич',
        'yakovlev@test.test',
        'true', false,
        '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
        'student', 'yakovlev',
        (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );

INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
        'Артём', 'Ткачук', 'Сергеевич',
        'tkachuk@test.test',
        'true', false,
        '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
        'student', 'tkachuk',
        (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );

INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
        'Максим', 'Тихомиров', 'Дмитриевич',
        'tikhomirov@test.test',
        'true', false,
        '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
        'student', 'tikhomirov',
        (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );

INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
        'Никита', 'Сливка', 'Алексеевич',
        'slivka@test.test',
        'true', false,
        '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
        'student', 'slivka',
        (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );

INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
        'Степан', 'Самойлов', 'Сергеевич',
        'samoilov@test.test',
        'true', false,
        '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
        'student', 'samoilov',
        (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );

INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
        'Дарья', 'Савочкина', 'Сергеевна',
        'savochkina@test.test',
        'true', false,
        '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
        'student', 'savochkina',
        (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );

INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
        'Артемий', 'Полухин', 'Игоревич',
        'polykhin@test.test',
        'true', false,
        '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
        'student', 'polykhin',
        (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );

INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
        'Александр', 'Новиков', 'Владимирович',
        'novikov@test.test',
        'true', false,
        '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
        'student', 'novikov',
        (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );

INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
        'Денис', 'Мясников', 'Валерьевич',
        'myasnikov@test.test',
        'true', false,
        '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
        'student', 'myasnikov',
        (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );

INSERT INTO credentials_tab (id, first_name, second_name, middle_name, email, enabled, locked, password, roles, username, "group")
VALUES (nextval('credentials_id_seq'),
        'Тимур', 'Мухаметрахимов', 'Азатович',
        'muhametrakhimov@test.test',
        'true', false,
        '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW',
        'student', 'muhametrakhimov',
        (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
       );
