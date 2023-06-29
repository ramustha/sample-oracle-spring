-- DROP TABLE PERSON;

CREATE TABLE PERSON
(
    id   VARCHAR2(64) default on null sys_guid() PRIMARY KEY,
    name VARCHAR2(255)
);
