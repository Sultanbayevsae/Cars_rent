--liquibase formatted sql
--changeset system:301

CREATE OR REPLACE VIEW v_user_summary AS
SELECT
  u.id,
  u.first_name,
  u.last_name,
  u.email,
  u.phone_number,
  u.enabled,
  array_agg(r.role_code) AS roles
FROM users u
LEFT JOIN users_roles ur ON ur.user_id = u.id
LEFT JOIN roles r ON r.id = ur.role_id
GROUP BY u.id, u.first_name, u.last_name, u.email, u.phone_number, u.enabled;
