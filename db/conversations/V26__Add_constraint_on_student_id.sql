ALTER TABLE conversations
    DROP CONSTRAINT conversations_student_id_fkey;

ALTER TABLE conversations
    ADD CONSTRAINT conversations_student_id_fkey
        FOREIGN KEY (student_id)
            REFERENCES students (student_id)
            ON DELETE CASCADE;
