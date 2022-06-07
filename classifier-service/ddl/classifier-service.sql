CREATE USER "classifie-service_user" WITH PASSWORD 'kswDW9g1dbfA';
CREATE DATABASE "classifie-service" WITH OWNER = "classifie-service_user";
\c "classifie-service"
CREATE SCHEMA IF NOT EXISTS application
    AUTHORIZATION "classifie-service_user";
CREATE TABLE IF NOT EXISTS application.currency
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    title character varying COLLATE pg_catalog."default",
    description character varying COLLATE pg_catalog."default",
    CONSTRAINT currency_pkey PRIMARY KEY (uuid),
    CONSTRAINT currency_title_key UNIQUE (title)
    );
CREATE TABLE IF NOT EXISTS application.operation_category
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    title character varying COLLATE pg_catalog."default",
    CONSTRAINT operation_category_pkey PRIMARY KEY (uuid),
    CONSTRAINT operation_category_title_key UNIQUE (title)
    );