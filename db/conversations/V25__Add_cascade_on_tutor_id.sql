ALTER TABLE conv_details
    DROP CONSTRAINT conv_details_tutor_id_fkey;

ALTER TABLE conv_details
    ADD CONSTRAINT conv_details_tutor_id_fkey
        FOREIGN KEY (tutor_id)
            REFERENCES tutors (tutor_id)
            ON DELETE CASCADE;
