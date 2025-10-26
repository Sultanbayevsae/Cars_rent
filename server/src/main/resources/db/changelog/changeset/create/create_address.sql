--liquibase formatted sql
--changeset system:007
--comment: create addresses table

CREATE TABLE addresses (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    city_or_town VARCHAR(100) NOT NULL,
--    user_id UUID NOT NULL UNIQUE,
    details VARCHAR(100),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by UUID,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by UUID
);