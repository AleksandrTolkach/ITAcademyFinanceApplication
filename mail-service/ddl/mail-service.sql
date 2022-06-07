CREATE USER "mail-service_user" WITH PASSWORD 'kswDW9g1dbfA';
CREATE DATABASE "mail-service" WITH OWNER = "mail-service_user";
\c "mail-service"
CREATE SCHEMA IF NOT EXISTS application
    AUTHORIZATION "mail-service_user";
CREATE TABLE IF NOT EXISTS application.mails
(
    uuid uuid NOT NULL,
    address character varying COLLATE pg_catalog."default",
    subject character varying COLLATE pg_catalog."default",
    text character varying COLLATE pg_catalog."default",
    date timestamp(3) without time zone,
    CONSTRAINT mails_pkey PRIMARY KEY (uuid)
    );