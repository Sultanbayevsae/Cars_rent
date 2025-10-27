--liquibase formatted sql

--changeset system:011
CREATE TABLE liked_cars (
                            id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                            user_id UUID NOT NULL,
                            car_id UUID NOT NULL,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                            CONSTRAINT fk_likedcars_user
                                FOREIGN KEY (user_id)
                                    REFERENCES users (id)
                                    ON DELETE CASCADE,

                            CONSTRAINT fk_likedcars_car
                                FOREIGN KEY (car_id)
                                    REFERENCES cars (id)
                                    ON DELETE CASCADE
);

-- Prevent a user from liking the same car multiple times
ALTER TABLE liked_cars
    ADD CONSTRAINT unique_user_car UNIQUE (user_id, car_id);
