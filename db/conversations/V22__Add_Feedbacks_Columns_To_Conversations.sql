alter table conversations
    add column student_left_feedback BOOLEAN default false;

alter table conversations
    add column tutor_left_feedback BOOLEAN default false;