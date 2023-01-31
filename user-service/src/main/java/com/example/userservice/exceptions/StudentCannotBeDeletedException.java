package com.example.userservice.exceptions;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class StudentCannotBeDeletedException extends RuntimeException {
    static final String deleteStudentProfileError =  "you cannot delete a student profile because you only have a " +
        "student profile.To delete a student profile, you must have a tutor profile or delete your entire account";
    public StudentCannotBeDeletedException() {
        super(deleteStudentProfileError);
    }
}
