--liquibase formatted sql
--changeset system:101
--comment: optional seed admin user

INSERT INTO users (first_name, last_name, email, password, phone_number, enabled, created_at)
VALUES (
  'Super',
  'Admin',
  'admin@morent.com',
  '$2y$10$7UdMXNMCg3iF0CZjzvZzsuJ1h7e6bVEBQm2nv8fVJnlrNrXNb8a42',
  '+998900000000',
  TRUE,
  CURRENT_TIMESTAMP
)
ON CONFLICT (email) DO NOTHING;

-- attach ROLE_ADMIN to the admin user
INSERT INTO users_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u CROSS JOIN roles r
WHERE u.email = 'admin@morent.com' AND r.role_code = 'ROLE_ADMIN'
ON CONFLICT (user_id, role_id) DO NOTHING;
