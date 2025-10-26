--liquibase formatted sql
--changeset system:012
CREATE TABLE user_photo (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL UNIQUE,
    bytes BYTEA NOT NULL,
    content_type VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_photo_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);
