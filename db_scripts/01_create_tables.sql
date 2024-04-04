CREATE TABLE IF NOT EXISTS users (
     id SERIAL PRIMARY KEY,
     name VARCHAR(50) NOT NULL,
     username VARCHAR(50) UNIQUE NOT NULL,
     password VARCHAR(500) NOT NULL,
     role varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS room (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50)  NOT NULL,
    max_users INT NOT NULL
);

CREATE TABLE IF NOT EXISTS message (
   id   SERIAL PRIMARY KEY,
   user_id INT REFERENCES users(id),
   room_id INT REFERENCES room(id),
   content varchar(500) NOT NULL,
   date timestamptz NOT NULL
);

CREATE TABLE IF NOT EXISTS room_users (
  user_id INT REFERENCES users(id),
  room_id INT REFERENCES room(id),
  primary key (user_id, room_id)
);

CREATE TABLE IF NOT EXISTS user_refresh_token (
    id SERIAL PRIMARY KEY,
    token VARCHAR(500) NOT NULL,
    user_id INT REFERENCES users(id),
    expire_date TIMESTAMPTZ NOT NULL
);
