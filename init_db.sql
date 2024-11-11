CREATE
DATABASE quiz_lab WITH OWNER postgres;

CREATE TABLE IF NOT EXISTS users
(
    username varchar
(
    10
) not null,
    full_name varchar not null,
    constraint user_pkey primary key
(
    username
)
    );

CREATE TABLE IF NOT EXISTS accounts
(
    username varchar
(
    10
) not null,
    password varchar not null,
    role varchar not null default 'STUDENT',
    constraint account_pkey primary key
(
    username
),
    constraint account_user_fkey foreign key
(
    username
) references users
(
    username
)
    );
