CREATE USER "report-service_user" WITH PASSWORD 'CLlOg42MYtKt';
CREATE DATABASE "report-service" WITH OWNER = "report-service_user";
\c "report-service"
CREATE SCHEMA IF NOT EXISTS application
    AUTHORIZATION "report-service_user";
CREATE TABLE IF NOT EXISTS application.accounts
(
    uuid uuid NOT NULL,
    dt_create timestamp without time zone,
    dt_update timestamp without time zone,
    description character varying COLLATE pg_catalog."default",
    title character varying COLLATE pg_catalog."default",
    CONSTRAINT accounts_pkey PRIMARY KEY (uuid)
    );
CREATE TABLE IF NOT EXISTS application.currency
(
    uuid uuid NOT NULL,
    dt_create timestamp without time zone,
    dt_update timestamp without time zone,
    title character varying COLLATE pg_catalog."default",
    description character varying COLLATE pg_catalog."default",
    CONSTRAINT currency_pkey PRIMARY KEY (uuid)
    );
CREATE TABLE IF NOT EXISTS application.operation_category
(
    uuid uuid NOT NULL,
    dt_create timestamp without time zone,
    dt_update timestamp without time zone,
    title character varying COLLATE pg_catalog."default",
    CONSTRAINT operation_category_pkey PRIMARY KEY (uuid)
    );
CREATE TABLE IF NOT EXISTS application.operations
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    date timestamp(3) without time zone,
    description character varying COLLATE pg_catalog."default",
    value bigint,
    type character varying COLLATE pg_catalog."default",
    account uuid,
    currency character varying COLLATE pg_catalog."default",
    category character varying COLLATE pg_catalog."default",
    CONSTRAINT operations_pkey PRIMARY KEY (uuid)
    );
CREATE TABLE IF NOT EXISTS application.parameters
(
    uuid uuid NOT NULL,
    "from" timestamp without time zone,
    "to" timestamp without time zone,
    accounts character varying COLLATE pg_catalog."default",
    categories character varying COLLATE pg_catalog."default",
    CONSTRAINT parameters_pkey PRIMARY KEY (uuid)
    );
CREATE TABLE IF NOT EXISTS application.reports
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    status character varying COLLATE pg_catalog."default",
    type character varying COLLATE pg_catalog."default",
    description character varying COLLATE pg_catalog."default",
    params_uuid uuid,
    CONSTRAINT reports_pkey PRIMARY KEY (uuid),
    CONSTRAINT params_fk FOREIGN KEY (params_uuid)
    REFERENCES application.parameters (uuid) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION
    NOT VALID
    );
CREATE TABLE IF NOT EXISTS application.report_files
(
    uuid uuid NOT NULL,
    report_uuid uuid,
    file bytea,
    CONSTRAINT report_files_pkey PRIMARY KEY (uuid),
    CONSTRAINT reports_fk FOREIGN KEY (report_uuid)
    REFERENCES application.reports (uuid) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID
    );