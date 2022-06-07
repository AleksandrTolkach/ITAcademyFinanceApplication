CREATE USER "account-service_user" WITH PASSWORD 'TiP22r1nJyyu';
CREATE DATABASE "account-service" WITH OWNER = "account-service_user";
\c "account-service"
CREATE SCHEMA IF NOT EXISTS application
    AUTHORIZATION "account-service_user";
CREATE TABLE IF NOT EXISTS application.accounts
(
    uuid uuid NOT NULL,
    title character varying COLLATE pg_catalog."default",
    description character varying COLLATE pg_catalog."default",
    type character varying COLLATE pg_catalog."default",
    currency character varying COLLATE pg_catalog."default",
    dt_update timestamp(3) without time zone,
    dt_create timestamp(3) without time zone,
    CONSTRAINT accounts_pkey PRIMARY KEY (uuid)
    );
CREATE TABLE IF NOT EXISTS application.balances
(
    uuid uuid NOT NULL,
    account_uuid uuid NOT NULL,
    sum numeric(1000,2) NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    CONSTRAINT balances_pkey PRIMARY KEY (uuid),
    CONSTRAINT account_fk FOREIGN KEY (account_uuid)
    REFERENCES application.accounts (uuid) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION
    NOT VALID
    );
CREATE TABLE IF NOT EXISTS application.operations
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    description character varying COLLATE pg_catalog."default",
    value bigint NOT NULL,
    type character varying COLLATE pg_catalog."default" NOT NULL,
    currency character varying COLLATE pg_catalog."default",
    account_uuid uuid NOT NULL,
    date timestamp(3) without time zone NOT NULL,
    category uuid,
    CONSTRAINT operations_pkey PRIMARY KEY (uuid),
    CONSTRAINT account_fk FOREIGN KEY (account_uuid)
    REFERENCES application.accounts (uuid) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION
    NOT VALID
    );
