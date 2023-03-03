import {create} from "zustand";
import {Student, Tutor, UpdateUserDto, User} from "../../../CommonStore/store";
import {LanguageLevel} from "../../SignUp/store/languagesStore";
import UserService from "../../../services/UserService";

export interface EditProfileInfo {
    firstName: string;
    lastName: string;
    email: string;
    languageLevels: LanguageLevel[];
    student: Student | null;
    tutor: Tutor | null;
    location: string | null;
    studentAboutMe: string | null,
    tutorAboutMe: string | null
    existingUser: User | null,
    isTutorOrStudentProfileCreated: boolean;
    setEmail: (email: string) => void;
    setFirstName: (firstname: string) => void;
    setLastName: (lastName: string) => void;
    setLanguageLevels: (languageLevels: LanguageLevel[]) => void;
    setStudentAboutMe: (aboutMe: string) => void;
    setTutorAboutMe: (aboutMe: string) => void;
    updateUser: () => Promise<boolean>;
    setExistingUser: (user: User) => void;
    createStudent: () => Promise<boolean>;
    createTutor: () => Promise<boolean>;
    setLocation: (location: string) => void;
    setIsTutorOrStudentProfileCreated: (flag: boolean) => void;
    deleteStudent: () => Promise<boolean>;
    deleteTutor: () => Promise<boolean>;
}

export const useUpdateUserInfo = create<EditProfileInfo>((set: any, get: any) => ({
    firstName: '',
    lastName: '',
    email: '',
    languageLevels: [],
    student: null,
    tutor: null,
    location: '',
    studentAboutMe: '',
    tutorAboutMe: '',
    existingUser: null,
    isTutorOrStudentProfileCreated: false,
    setEmail: async (email: string) => {
        set({email: email})
    },
    setFirstName: async (firstname: string) => {
        set({firstName: firstname})
    },
    setLastName: async (lastName: string) => {
        set({lastName: lastName})
    },
    setLanguageLevels: async (data: LanguageLevel[]) => {
        set({languageLevels: data})
    },
    setStudentAboutMe: async (aboutMe: string) => {
        const student: Student = {...get().student, aboutMe}
        set({
            studentAboutMe: aboutMe,
            student: student
        })
    },
    setTutorAboutMe: async (aboutMe: string) => {
        const tutor: Tutor = {...get().tutor, aboutMe}
        set({
            tutorAboutMe: aboutMe,
            tutor: tutor
        })
    },
    updateUser: async (): Promise<boolean> => {
        const updateUserDto: UpdateUserDto = {
            id: get().existingUser.id,
            firstName: get().firstName,
            lastName: get().lastName,
            email: get().email,
            password: get().existingUser.password,
            nationality: get().existingUser.nationality.countryId,
            roles: get().existingUser.roles,
            gender: get().existingUser.gender,
            languageLevels: get().languageLevels,
            student: get().student,
            tutor: get().tutor,
            location: get().location,
            avatarName: get().existingUser.avatarName
        }
        return await UserService.updateUser(updateUserDto) as boolean;
    },
    setExistingUser: async (user: User) => {
        set({
            existingUser: user,
            firstName: user.firstName,
            lastName: user.lastName,
            email: user.email,
            student: user.student,
            tutor: user.tutor,
            location: user.location,
            studentAboutMe: user.student?.aboutMe,
            tutorAboutMe: user.tutor?.aboutMe
        });
    },
    createStudent: async (): Promise<boolean> => {
        set({student: {}});
        return await UserService.createStudent() as boolean;
    },
    createTutor: async (): Promise<boolean> => {
        set({tutor: {}});
        return await UserService.createTutor() as boolean;
    },
    setLocation: async (location: string) => {
        set({location: location})
    },
    setIsTutorOrStudentProfileCreated: async (flag: boolean) => {
        set({isTutorOrStudentProfileCreated: flag})
    },
    deleteStudent: async (): Promise<boolean> => {
        set({student: null})
        return await UserService.deleteStudent() as boolean;
    },
    deleteTutor: async (): Promise<boolean> => {
        set({tutor: null})
        return await UserService.deleteTutor() as boolean;
    }
}))