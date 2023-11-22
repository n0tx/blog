-- Insert admin and user to role
INSERT INTO roles
(id, name)
VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');

-- Insert an user to users
-- username: admin, password: admin
INSERT INTO users
(id, email, name, password, username)
VALUES(1, 'admin@mail.com', 'Administrator', '$2a$10$4tiw0XIqpIdrF8w3BEVSe.90Ztsbbcqt7MfszeTOhxY7K6n81f.E2', 'admin');

---- username: riki, password: admin
INSERT INTO users
(id, email, name, password, username)
VALUES(2, 'riki@mail.com', 'riki', '$2a$10$JRb.V80.iOtVnsJSBy18EOEdmSxEmfYrczrYl6MtUV9RuythcE2MC', 'riki');

---- Insert an user who have admin role
INSERT INTO users_roles
(user_id, role_id)
VALUES(1, 1);

---- Insert an user who have user role
INSERT INTO users_roles
(user_id, role_id)
VALUES(2, 2);

INSERT
INTO
  posts
  (title, body, user_id)
VALUES
  ('title post 1', 'body post 1', 1);

INSERT
INTO
  posts
  (title, body, user_id)
VALUES
  ('title post 2', 'body post 2', 1);

INSERT
INTO
  posts
  (title, body, user_id)
VALUES
  ('title post 3', 'body post 3', 1);
