---- Departments

INSERT INTO department_tab (id, "short_name", "name")
    VALUES (
            nextval('department_id_seq'),
            'ИТиВС',
            'Информационные технологии и вычислительные системы'
            );

INSERT INTO department_tab (id, "short_name", "name")
VALUES (
           nextval('department_id_seq'),
           'ПМ',
           'Прикладная математика'
       );

INSERT INTO department_tab (id, "short_name", "name")
VALUES (
           nextval('department_id_seq'),
           'ИС',
           'Информационные системы'
       );

INSERT INTO department_tab (id, "short_name", "name")
VALUES (
           nextval('department_id_seq'),
           'УиИвТС',
           'Управление и информатика в технических системах'
       );

INSERT INTO department_tab (id, "short_name", "name")
VALUES (
           nextval('department_id_seq'),
           'ИБ',
           'Информационная безопасность'
       );

---- Groups

INSERT INTO group_tab (id, name, department, active, study_start_year, degree)
    VALUES (nextval('group_id_seq'),
           'ИДБ-18-09',
           (SELECT ID FROM department_tab WHERE short_name = 'УиИвТС'),
           true,
           to_date('01 09 2018', 'DD MM YYYY'),
           'BACHELOR'
           );

INSERT INTO group_tab (id, name, department, active, study_start_year, degree)
    VALUES (nextval('group_id_seq'),
            'ИДБ-18-10',
            (SELECT ID FROM department_tab WHERE short_name = 'УиИвТС'),
            true,
            to_date('01 09 2018', 'DD MM YYYY'),
            'BACHELOR'
            );

INSERT INTO group_tab (id, name, department, active, study_start_year, degree)
    VALUES (nextval('group_id_seq'),
            'ИДБ-18-11',
            (SELECT ID FROM department_tab WHERE short_name = 'ПМ'),
            true,
            to_date('01 09 2018', 'DD MM YYYY'),
            'BACHELOR'
            );

---- Subjects

INSERT INTO subject_tab (id, name, teacher)
    VALUES (nextval('subject_id_seq'), 'Алгебра и геометрия', null);

INSERT INTO subject_tab (id, name, teacher)
    VALUES (nextval('subject_id_seq'), 'Введение в специальность', null);

INSERT INTO subject_tab (id, name, teacher)
    VALUES (nextval('subject_id_seq'), 'Инженерная графика', null);

INSERT INTO subject_tab (id, name, teacher)
    VALUES (nextval('subject_id_seq'), 'Иностранный язык', null);

INSERT INTO subject_tab (id, name, teacher)
    VALUES (nextval('subject_id_seq'), 'Информатика', null);

INSERT INTO subject_tab (id, name, teacher)
    VALUES (nextval('subject_id_seq'), 'История', null);

INSERT INTO subject_tab (id, name, teacher)
    VALUES (nextval('subject_id_seq'), 'Математический анализ', null);

INSERT INTO subject_tab (id, name, teacher)
    VALUES (nextval('subject_id_seq'), 'Основы программирования', null);

INSERT INTO subject_tab (id, name, teacher)
    VALUES (nextval('subject_id_seq'), 'Прикладная физическая культура', null);

INSERT INTO subject_tab (id, name, teacher)
    VALUES (nextval('subject_id_seq'), 'Русский язык и культура речи', null);

INSERT INTO subject_tab (id, name, teacher)
    VALUES (nextval('subject_id_seq'), 'Физика', null);

INSERT INTO subject_tab (id, name, teacher)
    VALUES (nextval('subject_id_seq'), 'Выполнение и защита выпускной квалификационной работы', null);

INSERT INTO subject_tab (id, name, teacher)
    VALUES (nextval('subject_id_seq'), 'Преддипломная практика', null);

INSERT INTO subject_tab (id, name, teacher)
    VALUES (nextval('subject_id_seq'), 'Программная инженерия', null);

INSERT INTO subject_tab (id, name, teacher)
    VALUES (nextval('subject_id_seq'), 'Программное обеспечение для организации взаимодействия', null);

INSERT INTO subject_tab (id, name, teacher)
    VALUES (nextval('subject_id_seq'), 'Разработка приложений для встраиваемых и мобильных устройств', null);

INSERT INTO subject_tab (id, name, teacher)
    VALUES (nextval('subject_id_seq'), 'Реинжиниринг и управление бизнес-процессами', null);

INSERT INTO subject_tab (id, name, teacher)
    VALUES (nextval('subject_id_seq'), 'Системы интеллектуального анализа данных',
            (SELECT id FROM credentials_tab WHERE email = 'teacher@test.test'));

---- Group semesters

-- Group 1 year semesters

INSERT INTO semester (id, first_module_deadline, ordinal, second_module_deadline, session_deadline, "group")
    VALUES (
            nextval('semester_id_seq'),
            to_timestamp('01 10 2018', 'DD MM YYYY'), 1,
            to_timestamp('20 12 2018', 'DD MM YYYY'),
            to_timestamp('27 01 2019', 'DD MM YYYY'),
            (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
            );

INSERT INTO semester (id, first_module_deadline, ordinal, second_module_deadline, session_deadline, "group")
    VALUES (
            nextval('semester_id_seq'),
            to_timestamp('10 03 2019', 'DD MM YYYY'), 2,
            to_timestamp('10 05 2019', 'DD MM YYYY'),
            to_timestamp('27 06 2019', 'DD MM YYYY'),
            (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
            );

-- Group 2 year semesters

INSERT INTO semester (id, first_module_deadline, ordinal, second_module_deadline, session_deadline, "group")
    VALUES (
            nextval('semester_id_seq'),
            to_timestamp('01 10 2019', 'DD MM YYYY'), 3,
            to_timestamp('20 12 2019', 'DD MM YYYY'),
            to_timestamp('27 01 2020', 'DD MM YYYY'),
            (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
            );

INSERT INTO semester (id, first_module_deadline, ordinal, second_module_deadline, session_deadline, "group")
    VALUES (
            nextval('semester_id_seq'),
            to_timestamp('10 03 2020', 'DD MM YYYY'), 4,
            to_timestamp('10 05 2020', 'DD MM YYYY'),
            to_timestamp('27 06 2020', 'DD MM YYYY'),
            (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
            );

-- Group 3 year semesters

INSERT INTO semester (id, first_module_deadline, ordinal, second_module_deadline, session_deadline, "group")
    VALUES (
            nextval('semester_id_seq'),
            to_timestamp('01 10 2020', 'DD MM YYYY'), 5,
            to_timestamp('20 12 2020', 'DD MM YYYY'),
            to_timestamp('27 01 2021', 'DD MM YYYY'),
            (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
            );

INSERT INTO semester (id, first_module_deadline, ordinal, second_module_deadline, session_deadline, "group")
    VALUES (
            nextval('semester_id_seq'),
            to_timestamp('10 03 2021', 'DD MM YYYY'), 6,
            to_timestamp('10 05 2021', 'DD MM YYYY'),
            to_timestamp('27 06 2021', 'DD MM YYYY'),
            (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
            );

-- Group 4 year semesters

INSERT INTO semester (id, first_module_deadline, ordinal, second_module_deadline, session_deadline, "group")
    VALUES (
            nextval('semester_id_seq'),
            to_timestamp('01 10 2021', 'DD MM YYYY'), 7,
            to_timestamp('20 12 2021', 'DD MM YYYY'),
            to_timestamp('27 01 2022', 'DD MM YYYY'),
            (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
            );

INSERT INTO semester (id, first_module_deadline, ordinal, second_module_deadline, session_deadline, "group")
    VALUES (
            nextval('semester_id_seq'),
            to_timestamp('10 03 2022', 'DD MM YYYY'), 8,
            to_timestamp('10 05 2022', 'DD MM YYYY'),
            to_timestamp('27 06 2022', 'DD MM YYYY'),
            (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')
            );


---- Semester subjects

INSERT INTO semester_subject (id, course_work, final_examination_type, first_module, second_module, semester, subject, coefficient)
    VALUES (
           nextval('semester_subject_id_seq'),
           false, 'EXAMINATION', true, true,
           (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
           (SELECT id FROM subject_tab WHERE name = 'Алгебра и геометрия'), 3.5
       );

INSERT INTO semester_subject (id, course_work, final_examination_type, first_module, second_module, semester, subject, coefficient)
    VALUES (
           nextval('semester_subject_id_seq'),
           false, 'TEST', true, true,
           (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
           (SELECT id FROM subject_tab WHERE name = 'Введение в специальность'), 1.5
       );

INSERT INTO semester_subject (id, course_work, final_examination_type, first_module, second_module, semester, subject, coefficient)
    VALUES (
           nextval('semester_subject_id_seq'),
           false, 'EXAMINATION', true, true,
           (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
           (SELECT id FROM subject_tab WHERE name = 'Инженерная графика'), 3.0
       );

INSERT INTO semester_subject (id, course_work, final_examination_type, first_module, second_module, semester, subject, coefficient)
    VALUES (
           nextval('semester_subject_id_seq'),
           false, 'TEST', true, true,
           (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
           (SELECT id FROM subject_tab WHERE name = 'Иностранный язык'), 2.0
       );

INSERT INTO semester_subject (id, course_work, final_examination_type, first_module, second_module, semester, subject, coefficient)
    VALUES (
           nextval('semester_subject_id_seq'),
           false, 'TEST', true, true,
           (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
           (SELECT id FROM subject_tab WHERE name = 'Информатика'), 3.0
       );

INSERT INTO semester_subject (id, course_work, final_examination_type, first_module, second_module, semester, subject, coefficient)
    VALUES (
           nextval('semester_subject_id_seq'),
           false, 'EXAMINATION', true, true,
           (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
           (SELECT id FROM subject_tab WHERE name = 'История'), 2.5
       );

INSERT INTO semester_subject (id, course_work, final_examination_type, first_module, second_module, semester, subject, coefficient)
    VALUES (
           nextval('semester_subject_id_seq'),
           false, 'EXAMINATION', true, true,
           (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
           (SELECT id FROM subject_tab WHERE name = 'Математический анализ'), 3.5
       );

INSERT INTO semester_subject (id, course_work, final_examination_type, first_module, second_module, semester, subject, coefficient)
    VALUES (
           nextval('semester_subject_id_seq'),
           false, 'EXAMINATION', true, true,
           (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
           (SELECT id FROM subject_tab WHERE name = 'Основы программирования'), 3.5
       );

INSERT INTO semester_subject (id, course_work, final_examination_type, first_module, second_module, semester, subject, coefficient)
    VALUES (
           nextval('semester_subject_id_seq'),
           false, 'NONE', true, true,
           (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
           (SELECT id FROM subject_tab WHERE name = 'Прикладная физическая культура'), 1.0
       );

INSERT INTO semester_subject (id, course_work, final_examination_type, first_module, second_module, semester, subject, coefficient)
    VALUES (
           nextval('semester_subject_id_seq'),
           false, 'TEST', true, true,
           (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
           (SELECT id FROM subject_tab WHERE name = 'Русский язык и культура речи'), 3.0
       );

INSERT INTO semester_subject (id, course_work, final_examination_type, first_module, second_module, semester, subject, coefficient)
    VALUES (
           nextval('semester_subject_id_seq'),
           false, 'TEST', true, true,
           (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
           (SELECT id FROM subject_tab WHERE name = 'Физика'), 4.0
       );

INSERT INTO semester_subject (id, course_work, final_examination_type, first_module, second_module, semester, subject)
    VALUES (
           nextval('semester_subject_id_seq'),
           false, 'NONE', false, false,
           (SELECT id FROM semester WHERE ordinal = 8 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
           (SELECT id FROM subject_tab WHERE name = 'Выполнение и защита выпускной квалификационной работы')
       );

INSERT INTO semester_subject (id, course_work, final_examination_type, first_module, second_module, semester, subject, coefficient)
    VALUES (
            nextval('semester_subject_id_seq'),
            false, 'TEST', false, false,
            (SELECT id FROM semester WHERE ordinal = 8 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
            (SELECT id FROM subject_tab WHERE name = 'Преддипломная практика'), 1.0
       );

INSERT INTO semester_subject (id, course_work, final_examination_type, first_module, second_module, semester, subject, coefficient)
    VALUES (
            nextval('semester_subject_id_seq'),
            true, 'EXAMINATION', true, true,
            (SELECT id FROM semester WHERE ordinal = 8 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
            (SELECT id FROM subject_tab WHERE name = 'Программная инженерия'), 4.5
       );

INSERT INTO semester_subject (id, course_work, final_examination_type, first_module, second_module, semester, subject, coefficient)
    VALUES (
            nextval('semester_subject_id_seq'),
            false, 'EXAMINATION', true, true,
            (SELECT id FROM semester WHERE ordinal = 8 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
            (SELECT id FROM subject_tab WHERE name = 'Программное обеспечение для организации взаимодействия'), 2.5
       );

INSERT INTO semester_subject (id, course_work, final_examination_type, first_module, second_module, semester, subject, coefficient)
    VALUES (
            nextval('semester_subject_id_seq'),
            false, 'EXAMINATION', true, true,
            (SELECT id FROM semester WHERE ordinal = 8 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
            (SELECT id FROM subject_tab WHERE name = 'Разработка приложений для встраиваемых и мобильных устройств'), 3.0
       );

INSERT INTO semester_subject (id, course_work, final_examination_type, first_module, second_module, semester, subject, coefficient)
    VALUES (
            nextval('semester_subject_id_seq'),
            false, 'TEST', true, true,
            (SELECT id FROM semester WHERE ordinal = 8 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
            (SELECT id FROM subject_tab WHERE name = 'Реинжиниринг и управление бизнес-процессами'), 2.5
       );

INSERT INTO semester_subject (id, course_work, final_examination_type, first_module, second_module, semester, subject, coefficient)
    VALUES (
            nextval('semester_subject_id_seq'),
            false, 'EXAMINATION', true, true,
            (SELECT id FROM semester WHERE ordinal = 8 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')),
            (SELECT id FROM subject_tab WHERE name = 'Системы интеллектуального анализа данных'), 3.0
       );


---- Grades

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'FIRST_MODULE', true, 40, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Алгебра и геометрия')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'SECOND_MODULE', true, 40, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Алгебра и геометрия')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'EXAMINATION', true, 25, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Алгебра и геометрия')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'FIRST_MODULE', false, 25, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Введение в специальность')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'SECOND_MODULE', true, 40, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Введение в специальность')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'TEST', true, 40, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Введение в специальность')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'FIRST_MODULE', true, 30, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Инженерная графика')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'SECOND_MODULE', true, 35, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Инженерная графика')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'EXAMINATION', true, 30, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Инженерная графика')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'FIRST_MODULE', true, 45, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Иностранный язык')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'SECOND_MODULE', true, 46, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Иностранный язык')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'TEST', true, 48, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Иностранный язык')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'FIRST_MODULE', true, 45, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Информатика')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'SECOND_MODULE', true, 35, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Информатика')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'TEST', true, 35, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Информатика')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'FIRST_MODULE', true, 35, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'История')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'SECOND_MODULE', true, 39, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'История')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'EXAMINATION', true, 40, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'История')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'FIRST_MODULE', true, 30, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Математический анализ')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'SECOND_MODULE', true, 36, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Математический анализ')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'EXAMINATION', true, 35, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Математический анализ')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'FIRST_MODULE', true, 46, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Основы программирования')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'SECOND_MODULE', false, 29, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Основы программирования')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'EXAMINATION', true, 44, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Основы программирования')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'FIRST_MODULE', true, 45, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Прикладная физическая культура')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'SECOND_MODULE', true, 40, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Прикладная физическая культура')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
            nextval('grade_id_seq'), 'FIRST_MODULE', true, 30, null,
            (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
            (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Программная инженерия')
                                               AND semester = (SELECT id FROM semester WHERE ordinal = 8 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
            );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'FIRST_MODULE', true, 35, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Русский язык и культура речи')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'SECOND_MODULE', true, 35, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Русский язык и культура речи')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'TEST', true, 35, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Русский язык и культура речи')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'FIRST_MODULE', true, 35, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Физика')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'SECOND_MODULE', true, 32, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Физика')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'TEST', true, 35, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Физика')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 1 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'FIRST_MODULE', true, 25, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Реинжиниринг и управление бизнес-процессами')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 8 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'SECOND_MODULE', true, 35, null,
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Реинжиниринг и управление бизнес-процессами')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 8 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'FIRST_MODULE', true, 50,
           (SELECT id FROM credentials_tab WHERE email = 'teacher@test.test'),
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Системы интеллектуального анализа данных')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 8 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );

INSERT INTO grade_tab (id, grade_type, on_time, value, rated_by_id, rated_student, semester_subject)
    VALUES (
           nextval('grade_id_seq'), 'SECOND_MODULE', true, 51,
           (SELECT id FROM credentials_tab WHERE email = 'teacher@test.test'),
           (SELECT id FROM credentials_tab WHERE email = 'student@test.test'),
           (SELECT id FROM semester_subject WHERE subject = (SELECT id FROM subject_tab WHERE name = 'Системы интеллектуального анализа данных')
                                              AND semester = (SELECT id FROM semester WHERE ordinal = 8 AND "group" = (SELECT id FROM group_tab WHERE "name" = 'ИДБ-18-10')))
       );