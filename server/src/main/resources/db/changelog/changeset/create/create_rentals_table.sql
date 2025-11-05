--liquibase formatted sql
--changeset system:021
CREATE TABLE rentals (
    id BIGSERIAL PRIMARY KEY,
    user_id UUID NOT NULL,
    car_id UUID NOT NULL,
    pickup_branch_id UUID NOT NULL,
    dropoff_branch_id UUID NOT NULL,
    billing_address_id UUID NOT NULL,
    rental_start_date TIMESTAMP NOT NULL,
    rental_end_date TIMESTAMP NOT NULL,
    total_price NUMERIC(19,2) NOT NULL,
    rental_status VARCHAR(50) NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_car
        FOREIGN KEY(car_id) REFERENCES cars(id) ON DELETE CASCADE,
    CONSTRAINT fk_pickup_branch
        FOREIGN KEY(pickup_branch_id) REFERENCES branches(id) ON DELETE CASCADE,
    CONSTRAINT fk_dropoff_branch
        FOREIGN KEY(dropoff_branch_id) REFERENCES branches(id) ON DELETE CASCADE,
    CONSTRAINT fk_billing_address
        FOREIGN KEY(billing_address_id) REFERENCES addresses(id) ON DELETE CASCADE
);
