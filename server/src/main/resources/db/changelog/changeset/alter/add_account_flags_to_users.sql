--liquibase formatted sql
--changeset system:005

ALTER TABLE users
ADD COLUMN IF NOT EXISTS account_non_expired BOOLEAN NOT NULL DEFAULT TRUE,
ADD COLUMN IF NOT EXISTS account_non_locked BOOLEAN NOT NULL DEFAULT TRUE,
ADD COLUMN IF NOT EXISTS credentials_non_expired BOOLEAN NOT NULL DEFAULT TRUE;