create table person
(
    id integer not null,
    name varchar(255) not null,
    location varchar(255),
    birth_date timestamp,
    primary key(id)
);
INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE) VALUES(10001, 'Francis', 'Thika', now());
INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE) VALUES(10002, 'Ivan', 'Eldoret', now());
INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE) VALUES(10003, 'Bowen', 'Nakuru', now());
INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE) VALUES(10004, 'Eileen', 'Kakamega', now());
INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE) VALUES(10005, 'Daisy', 'Nairobi', now());

select * from person;