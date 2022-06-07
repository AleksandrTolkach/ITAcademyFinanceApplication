CREATE USER "telegram-service_user" WITH PASSWORD 'kswDW9g1dbfA';
CREATE DATABASE "telegram-service" WITH OWNER = "telegram-service_user";
\c "telegram-service"
CREATE SCHEMA IF NOT EXISTS application
    AUTHORIZATION "telegram-service_user";
CREATE TABLE IF NOT EXISTS application.chats
(
    uuid uuid NOT NULL,
    chat_id bigint NOT NULL,
    state character varying COLLATE pg_catalog."default",
    operation_uuid uuid,
    CONSTRAINT chats_pkey PRIMARY KEY (uuid)
    );
CREATE TABLE IF NOT EXISTS application.operations
(
    uuid uuid NOT NULL,
    date timestamp without time zone,
    category uuid,
    account uuid,
    description character varying COLLATE pg_catalog."default",
    value numeric,
    currency uuid,
    dt_create timestamp without time zone,
    dt_update timestamp without time zone,
    CONSTRAINT operations_pkey PRIMARY KEY (uuid)
    );