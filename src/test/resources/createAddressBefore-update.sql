delete from address;
delete from bank_data;
delete from usr;



INSERT INTO usr(id, first_name, last_name, email, password, birthday) VALUES (1, 'Tsyupryk', 'Roman', 'tsyupryk.roman.lyubomyrovych@gmail.com', 'strong_password', TO_DATE('26-JUN-1988', 'DD-MON-YYYY'));

INSERT INTO address(id, country, city, street, house_number, postcode, usr_id) VALUES (1, 'Spain', 'Madrid', 'Calle Madrid', '1A', 111111, 1);
INSERT INTO address(id, country, city, street, house_number, postcode, usr_id) VALUES (2, 'Spain', 'Barcelona', 'Calle Barcelona', '2A', 222222, 1);