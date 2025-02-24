INSERT INTO role (role) VALUES ('ROLE_EMPLOYEE');
INSERT INTO role (role) VALUES ('ROLE_ADMIN');

INSERT INTO employee (login, password_hashed, is_block)
VALUES ('admin', '$2a$10$jUSU1.20p4yQLIm3Pjclce92lWKB0ND7NIFCi8L1AaP9eARC9jBqO', false);
INSERT INTO employee (login, password_hashed, is_block)
VALUES ('employee', '$2a$12$tcu/4mrJaMwLO5Uskojstu45joSdR2E5/WrLRELDis554DAo.Y5tS', false);

INSERT INTO employee_data (owner_id, name, photo, employee_position, last_visit)
VALUES (1, 'peter', 'photo', 'backend developer', '2024-02-12T08:30');
INSERT INTO employee_data (owner_id, name, photo, employee_position, last_visit)
VALUES (2, 'semyon', 'photo', 'frontend developer', '2024-02-12T08:30');

INSERT INTO relationship_employee_and_role (employee_id, role_id) VALUES (1, 2);
INSERT INTO relationship_employee_and_role (employee_id, role_id) VALUES (2, 1);

INSERT INTO code (value)
VALUES
(1234567890123456789),
(9223372036854775807),
(1122334455667788990),
(998877665544332211),
(5566778899001122334);