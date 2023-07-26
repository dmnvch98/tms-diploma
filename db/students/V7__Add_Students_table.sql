create table students
(
    student_id BIGSERIAL NOT NULL PRIMARY KEY,
    user_id    BIGSERIAL NOT NULL,
    about_me   TEXT,
    presentation_file_name TEXT,
    UNIQUE (user_id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

INSERT INTO students (user_id)
VALUES (2), (4), (5), (7), (9), (12), (14), (15), (16), (20);
