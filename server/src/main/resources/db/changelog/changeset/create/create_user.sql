CREATE TABLE users
(
    id                      UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    created_at              TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP,
    created_by              UUID             NOT NULL,
    updated_by              UUID             NOT NULL,
    account_non_expired     BOOLEAN          DEFAULT TRUE,
    account_non_locked      BOOLEAN          DEFAULT TRUE,
    credentials_non_expired BOOLEAN          DEFAULT TRUE,
    enabled                 BOOLEAN          DEFAULT TRUE,
    first_name              VARCHAR(255),
    last_name               VARCHAR(255),
    phone_number            VARCHAR(50),
    email                   VARCHAR(255) NOT NULL UNIQUE,
    password                VARCHAR(255) NOT NULL,
    verify_token            VARCHAR(255) UNIQUE
)