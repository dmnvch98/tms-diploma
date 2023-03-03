create table conversations
(
    conv_id BIGSERIAL NOT NULL PRIMARY KEY,
    status_id BIGSERIAL NOT NULL,
    student_id BIGSERIAL NOT NULL,
    conv_details_id BIGSERIAL NOT NULL,
    FOREIGN KEY (status_id) REFERENCES conv_statuses(conv_status_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (conv_details_id) REFERENCES conv_details(conv_details_id)
)