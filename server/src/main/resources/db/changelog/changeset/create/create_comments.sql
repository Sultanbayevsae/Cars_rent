CREATE TABLE comments (
                          id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),

                          comment TEXT NOT NULL,
                          is_reviewer BOOLEAN NOT NULL,

                          user_id BIGINT NOT NULL,
                          car_id BIGINT NOT NULL,

                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                          CONSTRAINT fk_comments_user
                              FOREIGN KEY (user_id) REFERENCES users(id)
                                  ON DELETE CASCADE,

                          CONSTRAINT fk_comments_car
                              FOREIGN KEY (car_id) REFERENCES cars(id)
                                  ON DELETE CASCADE
);
