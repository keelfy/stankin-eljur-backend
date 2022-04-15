---- Departments

INSERT INTO department_tab (id, "name", "short_name")
    VALUES (nextval('department_id_seq'), 'ИИСТ', 'Институт информационных систем и технологий');

---- Groups

INSERT INTO group_tab (id, name, department_id)
    VALUES (nextval('group_id_seq'), 'ИДБ-18-10', (SELECT ID FROM department_tab WHERE short_name = 'ИИСТ'));

---- Credentials
-- Password: 'admin'

-- System
INSERT INTO credentials_tab (id, email, enabled, locked, password, roles, username)
    VALUES (nextval('credentials_id_seq'), 'keelfy@gmail.com', 'true', false, '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW', 'system', 'keelfy');

-- Student
INSERT INTO credentials_tab (id, email, enabled, locked, password, roles, username, group_id)
    VALUES (nextval('credentials_id_seq'), 'student@test.test', 'true', false, '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW', 'student', 'student', (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10'));

-- Teacher
INSERT INTO credentials_tab (id, email, enabled, locked, password, roles, username, department_id)
    VALUES (nextval('credentials_id_seq'), 'teacher@test.test', 'true', false, '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW', 'teacher', 'teacher', (SELECT id FROM department_tab WHERE "short_name" = 'ИИСТ'));

-- Admin
INSERT INTO credentials_tab (id, email, enabled, locked, password, roles, username, department_id)
    VALUES (nextval('credentials_id_seq'), 'admin@test.test', 'true', false, '$2a$10$BvoBFyPU5Re5bufomcNkzuj2BqMCJy9broBaS6T1BtdcPqcOrHNEW', 'admin', 'admin', (SELECT id FROM department_tab WHERE "short_name" = 'ИИСТ'));

---- Subjects

INSERT INTO subject_tab (id, name, teacher_id)
    VALUES (nextval('subject_id_seq'), 'Выполнение и защита выпускной квалификационной работы', null);

INSERT INTO subject_tab (id, name, teacher_id)
    VALUES (nextval('subject_id_seq'), 'Преддипломная практика', null);

INSERT INTO subject_tab (id, name, teacher_id)
    VALUES (nextval('subject_id_seq'), 'Программная инженерия', null);

INSERT INTO subject_tab (id, name, teacher_id)
    VALUES (nextval('subject_id_seq'), 'Программное обеспечение для организации взаимодействия', null);

INSERT INTO subject_tab (id, name, teacher_id)
    VALUES (nextval('subject_id_seq'), 'Разработка приложений для встраиваемых и мобильных устройств', null);

INSERT INTO subject_tab (id, name, teacher_id)
    VALUES (nextval('subject_id_seq'), 'Реинжиниринг и управление бизнес-процессами', null);

INSERT INTO subject_tab (id, name, teacher_id)
    VALUES (nextval('subject_id_seq'), 'Системы интеллектуального анализа данных', (SELECT id FROM credentials_tab WHERE email = 'teacher@test.test'));

---- Group semesters

-- Group 1 year semesters

INSERT INTO group_semester_tab (id, first_module_deadline, ordinal, second_module_deadline, session_deadline, group_id)
    VALUES (nextval('group_semester_id_seq'), to_timestamp('01 10 2018', 'DD MM YYYY'), 1, to_timestamp('20 12 2018', 'DD MM YYYY'), to_timestamp('27 01 2019', 'DD MM YYYY'), (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10'));

INSERT INTO group_semester_tab (id, first_module_deadline, ordinal, second_module_deadline, session_deadline, group_id)
    VALUES (nextval('group_semester_id_seq'), to_timestamp('10 03 2019', 'DD MM YYYY'), 2, to_timestamp('10 05 2019', 'DD MM YYYY'), to_timestamp('27 06 2019', 'DD MM YYYY'), (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10'));

-- Group 2 year semesters

INSERT INTO group_semester_tab (id, first_module_deadline, ordinal, second_module_deadline, session_deadline, group_id)
    VALUES (nextval('group_semester_id_seq'), to_timestamp('01 10 2019', 'DD MM YYYY'), 3, to_timestamp('20 12 2019', 'DD MM YYYY'), to_timestamp('27 01 2020', 'DD MM YYYY'), (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10'));

INSERT INTO group_semester_tab (id, first_module_deadline, ordinal, second_module_deadline, session_deadline, group_id)
    VALUES (nextval('group_semester_id_seq'), to_timestamp('10 03 2020', 'DD MM YYYY'), 4, to_timestamp('10 05 2020', 'DD MM YYYY'), to_timestamp('27 06 2020', 'DD MM YYYY'), (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10'));

-- Group 3 year semesters

INSERT INTO group_semester_tab (id, first_module_deadline, ordinal, second_module_deadline, session_deadline, group_id)
    VALUES (nextval('group_semester_id_seq'), to_timestamp('01 10 2020', 'DD MM YYYY'), 5, to_timestamp('20 12 2020', 'DD MM YYYY'), to_timestamp('27 01 2021', 'DD MM YYYY'), (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10'));

INSERT INTO group_semester_tab (id, first_module_deadline, ordinal, second_module_deadline, session_deadline, group_id)
    VALUES (nextval('group_semester_id_seq'), to_timestamp('10 03 2021', 'DD MM YYYY'), 6, to_timestamp('10 05 2021', 'DD MM YYYY'), to_timestamp('27 06 2021', 'DD MM YYYY'), (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10'));

-- Group 4 year semesters

INSERT INTO group_semester_tab (id, first_module_deadline, ordinal, second_module_deadline, session_deadline, group_id)
    VALUES (nextval('group_semester_id_seq'), to_timestamp('01 10 2021', 'DD MM YYYY'), 7, to_timestamp('20 12 2021', 'DD MM YYYY'), to_timestamp('27 01 2022', 'DD MM YYYY'), (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10'));

INSERT INTO group_semester_tab (id, first_module_deadline, ordinal, second_module_deadline, session_deadline, group_id)
    VALUES (nextval('group_semester_id_seq'), to_timestamp('10 03 2022', 'DD MM YYYY'), 8, to_timestamp('10 05 2022', 'DD MM YYYY'), to_timestamp('27 06 2022', 'DD MM YYYY'), (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10'));


---- Group semester subjects

INSERT INTO group_semester_subject_tab (group_semester_id, course_work, final_examination_type, first_module, second_module, subject_id)
    VALUES (
            (SELECT id FROM group_semester_tab WHERE ordinal = 8 AND group_id = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
            false, 'NONE', false, false,
            (SELECT id FROM subject_tab WHERE name = 'Выполнение и защита выпускной квалификационной работы')
           );

INSERT INTO group_semester_subject_tab (group_semester_id, course_work, final_examination_type, first_module, second_module, subject_id)
    VALUES (
           (SELECT id FROM group_semester_tab WHERE ordinal = 8 AND group_id = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
           false, 'TEST', false, false,
           (SELECT id FROM subject_tab WHERE name = 'Преддипломная практика')
       );

INSERT INTO group_semester_subject_tab (group_semester_id, course_work, final_examination_type, first_module, second_module, subject_id)
    VALUES (
           (SELECT id FROM group_semester_tab WHERE ordinal = 8 AND group_id = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
           true, 'EXAMINATION', true, true,
           (SELECT id FROM subject_tab WHERE name = 'Программная инженерия')
       );

INSERT INTO group_semester_subject_tab (group_semester_id, course_work, final_examination_type, first_module, second_module, subject_id)
    VALUES (
           (SELECT id FROM group_semester_tab WHERE ordinal = 8 AND group_id = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
           false, 'EXAMINATION', true, true,
           (SELECT id FROM subject_tab WHERE name = 'Программное обеспечение для организации взаимодействия')
       );

INSERT INTO group_semester_subject_tab (group_semester_id, course_work, final_examination_type, first_module, second_module, subject_id)
    VALUES (
           (SELECT id FROM group_semester_tab WHERE ordinal = 8 AND group_id = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
           false, 'EXAMINATION', true, true,
           (SELECT id FROM subject_tab WHERE name = 'Разработка приложений для встраиваемых и мобильных устройств')
       );

INSERT INTO group_semester_subject_tab (group_semester_id, course_work, final_examination_type, first_module, second_module, subject_id)
    VALUES (
           (SELECT id FROM group_semester_tab WHERE ordinal = 8 AND group_id = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
           false, 'TEST', true, true,
           (SELECT id FROM subject_tab WHERE name = 'Реинжиниринг и управление бизнес-процессами')
       );

INSERT INTO group_semester_subject_tab (group_semester_id, course_work, final_examination_type, first_module, second_module, subject_id)
    VALUES (
           (SELECT id FROM group_semester_tab WHERE ordinal = 8 AND group_id = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
           false, 'EXAMINATION', true, true,
           (SELECT id FROM subject_tab WHERE name = 'Системы интеллектуального анализа данных')
       );


---- Student semesters

-- Student 1 year semesters

INSERT INTO student_semester_tab (id, group_semester_id, student_id)
    VALUES (nextval('student_semester_id_seq'), (SELECT id FROM group_semester_tab WHERE group_id = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10') AND ordinal = 1), (SELECT id FROM credentials_tab WHERE email = 'student@test.test'));

INSERT INTO student_semester_tab (id, group_semester_id, student_id)
    VALUES (nextval('student_semester_id_seq'), (SELECT id FROM group_semester_tab WHERE group_id = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10') AND ordinal = 2), (SELECT id FROM credentials_tab WHERE email = 'student@test.test'));

-- Student 2 year semesters

INSERT INTO student_semester_tab (id, group_semester_id, student_id)
    VALUES (nextval('student_semester_id_seq'), (SELECT id FROM group_semester_tab WHERE group_id = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10') AND ordinal = 3), (SELECT id FROM credentials_tab WHERE email = 'student@test.test'));

INSERT INTO student_semester_tab (id, group_semester_id, student_id)
    VALUES (nextval('student_semester_id_seq'), (SELECT id FROM group_semester_tab WHERE group_id = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10') AND ordinal = 4), (SELECT id FROM credentials_tab WHERE email = 'student@test.test'));

-- Student 3 year semesters

INSERT INTO student_semester_tab (id, group_semester_id, student_id)
    VALUES (nextval('student_semester_id_seq'), (SELECT id FROM group_semester_tab WHERE group_id = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10') AND ordinal = 5), (SELECT id FROM credentials_tab WHERE email = 'student@test.test'));

INSERT INTO student_semester_tab (id, group_semester_id, student_id)
    VALUES (nextval('student_semester_id_seq'), (SELECT id FROM group_semester_tab WHERE group_id = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10') AND ordinal = 6), (SELECT id FROM credentials_tab WHERE email = 'student@test.test'));

-- Student 4 year semesters

INSERT INTO student_semester_tab (id, group_semester_id, student_id)
    VALUES (nextval('student_semester_id_seq'), (SELECT id FROM group_semester_tab WHERE group_id = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10') AND ordinal = 7), (SELECT id FROM credentials_tab WHERE email = 'student@test.test'));

INSERT INTO student_semester_tab (id, group_semester_id, student_id)
    VALUES (nextval('student_semester_id_seq'), (SELECT id FROM group_semester_tab WHERE group_id = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10') AND ordinal = 8), (SELECT id FROM credentials_tab WHERE email = 'student@test.test'));

---- Grades

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, student_semester_id)
    VALUES (
            nextval('grade_id_seq'), 'FIRST_MODULE', true, 30, null,
            (SELECT id FROM student_semester_tab WHERE)
            )