--liquibase formatted sql
--changeset system:023
CREATE TABLE payment_cards (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    card_number_masked VARCHAR(50),
    card_holder_name VARCHAR(255),
    expiry_date VARCHAR(50),
    token VARCHAR(255),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    user_id UUID NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);