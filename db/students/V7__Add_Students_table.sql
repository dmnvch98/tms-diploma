create table students
(
    student_id BIGSERIAL NOT NULL PRIMARY KEY,
    user_id    BIGSERIAL NOT NULL,
    about_me   TEXT,
    location   TEXT,
    UNIQUE (user_id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);
