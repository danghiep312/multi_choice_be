CREATE DATABASE quiz_lab WITH OWNER postgres;

CREATE TABLE IF NOT EXISTS users (
  username varchar(10) not null,
  full_name varchar not null,
  constraint user_pkey primary key (username)
);