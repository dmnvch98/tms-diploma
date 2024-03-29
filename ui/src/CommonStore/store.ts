import {Country} from "../pages/SignUp/store/countryStore";
import {LanguageLevel} from "../pages/SignUp/store/languagesStore";

export interface Student {
    studentId: number;
    userId: number;
    aboutMe: string;
    presentationUrl: string
}

export interface Tutor {
    tutorId: number;
    userId: number;
    aboutMe: string;
    presentationUrl: string
}

export interface User {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    nationality: Country;
    roles: string[];
    gender: string;
    languageLevels: LanguageLevel[];
    student: Student;
    tutor: Tutor;
    location: string | null;
    avatarName: string | null;
    tutorConversationCount: number;
    studentConversationCount: number;
    tutorAverageRate: number;
    studentAverageRate: number;
}
export interface UpdateUserDto {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    nationality: number;
    roles: string[];
    gender: string;
    languageLevels: LanguageLevel[];
    student: Student;
    tutor: Tutor;
    location: string | null;
    avatarName: string | null;
}