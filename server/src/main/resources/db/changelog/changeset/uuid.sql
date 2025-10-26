--liquibase formatted sql
--changeset system:001
--comment: enable uuid extension for Postgres
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
