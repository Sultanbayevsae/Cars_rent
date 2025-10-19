--liquibase formatted sql
--changeset system:004
CREATE TABLE roles (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    role_name VARCHAR(255) NOT NULL,
    role_code VARCHAR(255) NOT NULL,
    CONSTRAINT uq_roles_role_code UNIQUE (role_code)
);
