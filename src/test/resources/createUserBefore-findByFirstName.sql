delete from address;
delete from bank_data;
delete from usr;

INSERT INTO usr(id, first_name, last_name, email, password, birthday) VALUES (1, 'Tsyupryk', 'Roman', 'tsyupryk.roman.lyubomyrovych@gmail.com', 'strong_password', TO_DATE('26-JUN-1988', 'DD-MON-YYYY'));

INSERT INTO usr(id, first_name, last_name, email, password, birthday) VALUES (2, 'First Name User_2', 'Last Name User_2', 'user_2@gmail.com', 'strong_password', TO_DATE('01-JAN-1970', 'DD-MON-YYYY'));

INSERT INTO usr(id, first_name, last_name, email, password, birthday) VALUES (3, 'Tsyupryk', 'Last Name User_3', 'user_3@gmail.com', 'strong_password', TO_DATE('02-JAN-1970', 'DD-MON-YYYY'));