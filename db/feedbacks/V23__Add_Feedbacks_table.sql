create table feedbacks
(
    feedback_id BIGSERIAL NOT NULL PRIMARY KEY,
    conversation_id BIGSERIAL NOT NULL,
    tutor_rate INT,
    student_rate INT,
    tutor_feedback TEXT NULL ,
    student_feedback TEXT NULL ,
    UNIQUE (conversation_id),

    foreign key (conversation_id) references conversations (conv_id)
)