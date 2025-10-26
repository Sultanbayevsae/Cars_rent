--liquibase formatted sql
--changeset system:100

INSERT INTO roles (role_name, role_code)
VALUES
  ('User', 'ROLE_USER'),
  ('Admin', 'ROLE_ADMIN')
ON CONFLICT (role_code) DO NOTHING;
