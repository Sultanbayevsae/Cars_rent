--liquibase formatted sql
--changeset system:025-invoice
CREATE TABLE invoices (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    invoice_number VARCHAR(100) NOT NULL UNIQUE,
    amount NUMERIC(19,2) NOT NULL,
    currency VARCHAR(10) NOT NULL DEFAULT 'USD',
    status VARCHAR(20) NOT NULL,
    rental_id BIGINT NOT NULL,
    user_id UUID NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    paid_at TIMESTAMP,
    CONSTRAINT fk_invoice_rental FOREIGN KEY(rental_id) REFERENCES rentals(id) ON DELETE CASCADE,
    CONSTRAINT fk_invoice_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);
