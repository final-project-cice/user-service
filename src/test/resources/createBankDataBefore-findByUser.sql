delete from address;
delete from bank_data;
delete from usr;



INSERT INTO usr(id, first_name, last_name, email, password, birthday) VALUES (1, 'Tsyupryk', 'Roman', 'tsyupryk.roman.lyubomyrovych@gmail.com', 'strong_password', TO_DATE('26-JUN-1988', 'DD-MON-YYYY'));

INSERT INTO bank_data(id, bank_account_number, cvi, date_of_expiry, usr_id) vALUES (1, '1111111111111111111', 111, TO_DATE('01-JAN-1970', 'DD-MON-YYYY'), 1);
INSERT INTO bank_data(id, bank_account_number, cvi, date_of_expiry, usr_id) vALUES (2, '2222222222222222222', 222, TO_DATE('01-JAN-1970', 'DD-MON-YYYY'), 1);