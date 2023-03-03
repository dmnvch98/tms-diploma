package com.example.userservice.exceptions;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class TutorCannotBeDeletedException extends RuntimeException {
    static final String DELETE_TUTOR_PROFILE_ERROR = "you cannot delete a tutor profile because you only have a " +
        "tutor profile To delete a tutor profile, you must have a student profile or delete your entire account";

    public TutorCannotBeDeletedException() {
        super(DELETE_TUTOR_PROFILE_ERROR);
    }
}
