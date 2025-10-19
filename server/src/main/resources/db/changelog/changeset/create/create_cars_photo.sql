--liquibase formatted sql
--changeset system:010
CREATE TABLE cars_photo (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    car_id UUID NOT NULL,
    bytes BYTEA NOT NULL,
    content_type VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cars_photo_car FOREIGN KEY(car_id) REFERENCES cars(id) ON DELETE CASCADE
);
