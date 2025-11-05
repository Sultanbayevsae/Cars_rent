--liquibase formatted sql
--changeset system:022
CREATE TABLE transactions (
    id UUID PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    amount NUMERIC(19,2) NOT NULL,
    bank_transaction_id VARCHAR(255),
    description TEXT,
    payment_id BIGINT NOT NULL,
    card_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    created_by UUID NOT NULL,
    CONSTRAINT fk_payment
        FOREIGN KEY(payment_id) REFERENCES payments(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_card
        FOREIGN KEY(card_id) REFERENCES payment_cards(id)
        ON DELETE CASCADE
);
