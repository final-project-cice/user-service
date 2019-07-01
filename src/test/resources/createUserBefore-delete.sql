delete from address;
delete from bank_data;
delete from usr;



INSERT INTO usr(id, first_name, last_name, email, password, birthday) VALUES (1, 'Tsyupryk', 'Roman', 'tsyupryk.roman.lyubomyrovych@gmail.com', 'strong_password', TO_DATE('26-JUN-1988', 'DD-MON-YYYY'));

INSERT INTO address(id, country, city, street, house_number, postcode, usr_id) VALUES (1, 'Spain', 'Madrid', 'Calle Madrid', '1A', 111111, 1);
INSERT INTO address(id, country, city, street, house_number, postcode, usr_id) VALUES (2, 'Spain', 'Barcelona', 'Calle Barcelona', '2A', 222222, 1);

INSERT INTO bank_data(id, bank_account_number, cvi, date_of_expiry, usr_id) vALUES (1, '1111111111111111111', 111, TO_DATE('01-JAN-1970', 'DD-MON-YYYY'), 1);
INSERT INTO bank_data(id, bank_account_number, cvi, date_of_expiry, usr_id) vALUES (2, '2222222222222222222', 222, TO_DATE('01-JAN-1970', 'DD-MON-YYYY'), 1);



INSERT INTO usr(id, first_name, last_name, email, password, birthday) VALUES (2, 'First Name User_2', 'Last Name User_2', 'user_2@email.com', 'strong_password', TO_DATE('02-JAN-1970', 'DD-MON-YYYY'));

INSERT INTO address(id, country, city, street, house_number, postcode, usr_id) VALUES (3, 'Spain', 'Madrid', 'Calle Madrid', '3A', 333333, 2);
INSERT INTO address(id, country, city, street, house_number, postcode, usr_id) VALUES (4, 'Spain', 'Sevilla', 'Calle Sevilla', '4A', 444444, 2);

INSERT INTO bank_data(id, bank_account_number, cvi, date_of_expiry, usr_id) vALUES (3, '3333333333333333333', 333, TO_DATE('01-JAN-1970', 'DD-MON-YYYY'), 2);
INSERT INTO bank_data(id, bank_account_number, cvi, date_of_expiry, usr_id) vALUES (4, '4444444444444444444', 444, TO_DATE('01-JAN-1970', 'DD-MON-YYYY'), 2);



INSERT INTO usr(id, first_name, last_name, email, password, birthday) VALUES (3, 'First Name User_3', 'Last Name User_3', 'user_3@email.com', 'strong_password', TO_DATE('03-JAN-1970', 'DD-MON-YYYY'));

INSERT INTO address(id, country, city, street, house_number, postcode, usr_id) VALUES (5, 'Spain', 'Madrid', 'Calle Madrid', '5A', 555555, 3);
INSERT INTO address(id, country, city, street, house_number, postcode, usr_id) VALUES (6, 'Spain', 'Valencia', 'Calle Valencia', '6A', 666666, 3);

INSERT INTO bank_data(id, bank_account_number, cvi, date_of_expiry, usr_id) vALUES (5, '5555555555555555555', 555, TO_DATE('01-JAN-1970', 'DD-MON-YYYY'), 3);
INSERT INTO bank_data(id, bank_account_number, cvi, date_of_expiry, usr_id) vALUES (6, '6666666666666666666', 666, TO_DATE('01-JAN-1970', 'DD-MON-YYYY'), 3);