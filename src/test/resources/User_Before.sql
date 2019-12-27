DELETE
FROM address_user;
DELETE
FROM bank_data_user;
DELETE
FROM usr;

INSERT INTO usr (id, first_name, last_name, user_name, email, password, birthday)
VALUES (1, 'Tsyupryk', 'Roman', 'TRL', 'tsyupryk.roman@gmail.com', 'strong_password',
        TO_DATE('26-JUN-1988', 'DD-MON-YYYY'));

INSERT INTO usr (id, first_name, last_name, user_name, email, password, birthday)
VALUES (2, 'AAAAAAAA', 'AAAAAAA', 'AAA', 'aaaaaaaaa@mail.com', 'strong_password',
        TO_DATE('01-JAN-2000', 'DD-MON-YYYY'));



INSERT INTO address_user (id, country, city, street, house_number, postcode, usr_id)
VALUES (1, 'Spain', 'Madrid', 'Calle Madrid', '1A', 1111, 1);

INSERT INTO address_user (id, country, city, street, house_number, postcode, usr_id)
VALUES (2, 'Spain', 'Barcelona', 'Calle Barcelona', '2B', 22222, 1);

INSERT INTO address_user (id, country, city, street, house_number, postcode, usr_id)
VALUES (3, 'Spain', 'Toledo', 'Calle Toledo', '3C', 33333, 1);



INSERT INTO bank_data_user (id, bank_account_number, date_of_expiry, cvi, usr_id)
VALUES (1, '1212121212121212',  TO_DATE('01-JAN-2000', 'DD-MON-YYYY'), 111, 1);

INSERT INTO bank_data_user (id, bank_account_number, date_of_expiry, cvi, usr_id)
VALUES (2, '343434343434343',  TO_DATE('02-JAN-2000', 'DD-MON-YYYY'), 222, 1);

INSERT INTO bank_data_user (id, bank_account_number, date_of_expiry, cvi, usr_id)
VALUES (3, '565656565656565',  TO_DATE('03-JAN-2000', 'DD-MON-YYYY'), 333, 1);