delete from address;
delete from bank_data;
delete from usr;


--INSERT INTO usr(id, birthday, email, first_name, last_name, password) VALUES (1, TO_DATE('26-JUN-1988', 'DD-MON-YYYY'), 'tsyupryk.roman.lyubomyrovych@gmail.com', 'Tsyupryk', 'Roman', 'strong_password');
--
--INSERT INTO address(id, city, country, house_number, post_code, street) VALUES (1, 'Madrid', 'Spain', '1A', 111111, 'Calle Madrid');
--INSERT INTO address(id, city, country, house_number, post_code, street) VALUES (2, 'Barcelona', 'Spain', '2A', 222222, 'Calle Barcelona');
--
--INSERT INTO bank_data(id, bank_account_number, cvi, date_of_expiry) vALUES (1, '1111111111111111111', 111, TO_DATE('01-JAN-1970', 'DD-MON-YYYY'));
--INSERT INTO bank_data(id, bank_account_number, cvi, date_of_expiry) vALUES (2, '2222222222222222222', 222, TO_DATE('01-JAN-1970', 'DD-MON-YYYY'));
--
--INSERT INTO usr_address(usr_id, address_id) vALUES (1, 1);
--INSERT INTO usr_address(usr_id, address_id) vALUES (1, 2);
--
--INSERT INTO usr_bank_data(usr_id, bank_data_id) vALUES (1, 1);
--INSERT INTO usr_bank_data(usr_id, bank_data_id) vALUES (1, 2);