ALTER TABLE addresses
    DROP CONSTRAINT addresses_tutor_id_fkey;

ALTER TABLE addresses
    ADD CONSTRAINT addresses_tutor_id_fkey
        FOREIGN KEY (tutor_id)
            REFERENCES tutors (tutor_id)
            ON DELETE CASCADE;
