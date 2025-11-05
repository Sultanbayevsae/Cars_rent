--liquibase formatted sql
--changeset system:030
CREATE TABLE notifications (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(255),
    message TEXT,
    read BOOLEAN DEFAULT FALSE,
    type VARCHAR(50),
    user_id UUID,
    transaction_id UUID NOT NULL,
    car_id UUID NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_notification_user FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT fk_notification_transaction FOREIGN KEY(transaction_id) REFERENCES transactions(id) ON DELETE CASCADE,
    CONSTRAINT fk_notification_car FOREIGN KEY(car_id) REFERENCES cars(id) ON DELETE CASCADE
);
