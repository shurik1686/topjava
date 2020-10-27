DELETE
FROM user_roles WHERE user_id > 0;
DELETE
FROM users WHERE id > 0;
DELETE
FROM meals WHERE id >0;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, datetime, description, calories)
VALUES (100000, '2020-07-07 17:01', 'Завтрак', 500),
       (100000, '2020-07-07 17:02', 'Обед', 1000),
       (100000, '2020-07-07 17:03', 'Ужин', 500),
       (100001, '2020-07-07 17:04', 'Еда на граничное значение', 100),
       (100001, '2020-07-07 17:05', 'Завтрак', 1000),
       (100001, '2020-07-07 17:06', 'Обед', 500),
       (100001, '2020-07-07 17:07', 'Ужин', 410);