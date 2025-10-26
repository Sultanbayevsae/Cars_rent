--liquibase formatted sql
--changeset system:300
--comment: extra indexes and constraints

CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);
CREATE INDEX IF NOT EXISTS idx_roles_code ON roles(role_code);
CREATE INDEX IF NOT EXISTS idx_refresh_tokens_token ON refresh_tokens(token);

-- ensure phone_number unique already declared, but enforce via unique index if needed
CREATE UNIQUE INDEX IF NOT EXISTS uq_users_phone_number ON users(phone_number);
