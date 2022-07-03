INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@gmail.com', '{noop}user'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT (NAME)
VALUES ('MacDonalds'),
       ('KFC'),
       ('Burger King');

INSERT INTO DISH (NAME, DISH_DATE, PRICE, RESTAURANT_ID)
VALUES ('Big Mac', now(), 3, 1),
       ('Twister', now(), 4, 2),
       ('Wopper', now(), 5, 3 );





