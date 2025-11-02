--liquibase formatted sql
--changeset system:016
CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,
    rental_id BIGINT NOT NULL UNIQUE,
    payment_method VARCHAR(50) NOT NULL,
    amount NUMERIC(19,2) NOT NULL,
    payment_status VARCHAR(50) NOT NULL,
    transaction_id VARCHAR(255) NOT NULL,
    payment_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_rental
        FOREIGN KEY(rental_id)
        REFERENCES rentals(id)
        ON DELETE CASCADE
);
