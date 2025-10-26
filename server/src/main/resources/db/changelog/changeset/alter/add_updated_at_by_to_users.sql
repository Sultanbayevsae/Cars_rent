--liquibase formatted sql
--changeset system:006
--comment: ensure updated_at and updated_by exist in users

ALTER TABLE users
ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN IF NOT EXISTS updated_by UUID;