--liquibase formatted sql
--changeset system:009
CREATE TABLE cars (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(100) NOT NULL UNIQUE,
    car_number VARCHAR(50) NOT NULL UNIQUE,
    gas_used INT NOT NULL,
    price_per_day INT NOT NULL,
    auto_or_manual BOOLEAN NOT NULL,
    is_available BOOLEAN NOT NULL,
    seats INT NOT NULL,
    branch_id UUID NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_branch FOREIGN KEY(branch_id) REFERENCES branches(id) ON DELETE CASCADE
);
