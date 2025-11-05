--liquibase formatted sql
--changeset system:010
CREATE TABLE user_history (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL,
    car_id UUID NOT NULL,
    rental_time TIMESTAMP NOT NULL,
    return_time TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_car FOREIGN KEY(car_id) REFERENCES cars(id) ON DELETE CASCADE
);
