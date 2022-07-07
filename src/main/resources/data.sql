INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@gmail.com', '{noop}user'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2),
       ('USER', 3);


INSERT INTO RESTAURANT (NAME)
VALUES ('MacDonalds'),
       ('KFC'),
       ('Burger King'),
       ('Taco Bell');


INSERT INTO DISH (NAME, DISH_DATE, PRICE, RESTAURANT_ID)
VALUES ('Big Mac', now(), 3, 1),
       ('Double cheeseburger', now()-1, 2.4, 1),
       ('Twister', now(), 4, 2),
       ('Chicken wings', now()-1, 2, 2),
       ('Wopper', now(), 5, 3 ),
       ('Long Chicken', now() - 1, 2.5, 3),
       ('Taco', now() - 1, 3, 4);

INSERT INTO VOTE (VOTE_DATE, RESTAURANT_ID, USER_ID)
VALUES ( now(), 1, 1 ),
       (now (), 2, 2),
       (now()-1, 3, 1),
        (now()-2, 1,1 );






