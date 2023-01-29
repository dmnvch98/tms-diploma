import {create} from 'zustand';
import {Language, LanguageLevel} from "./languagesStore";
import UserService from "../../../services/UserService";
import {Country} from "./countryStore";
import {Level} from "./levelStore";
import {Student, Tutor} from "../../../CommonStore/store";

export interface UserDto {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    nationality: number;
    roles: string[];
    gender: string;
    tutor: Tutor | {} | null,
    student: Student | {} | null,
    languageLevels: LanguageLevel[];
    location: string | null;
}

export interface SignUp {
    redirectButtonDisabled: boolean;
    roles: string[];
    email: string;
    gender: string;
    firstName: string;
    lastName: string;
    countryId: number | string;
    nationality: Country | null;
    languageLevels: LanguageLevel[];
    level: Level | null;
    levelId: number | string;
    language: Language | null;
    languageId: number | string;
    tutor: Tutor | {} | null,
    student: Student | {} | null,
    location: string | null,
    password: string,

    setRoles: (role: string) => void;
    setEmail: (email: string) => void;

    setGender: (gender: string) => void;
    setCountryId: (countryId: number | string) => void;
    setNationality: (nationality: Country | null) => void;

    setFirstName: (firstname: string) => void;
    setLastName: (lastName: string) => void;
    setLanguageLevels: (languageLevels: LanguageLevel[]) => void;

    setLanguage: (language: Language | null) => void;
    setLanguageId: (languageId: number | string) => void;
    setLevel: (level: Level | null) => void;
    setLevelId: (levelId: number | string) => void;
    createUser: () => UserDto;
    setRedirectButtonDisabled: () => void;

    setTutor: (tutor: Tutor | {}) => void,
    setStudent: (student: Student | {}) => void;

    setLocation: (location: string) => void;

    setPassword: (password: string) => void;
}

export const useSignUpStore = create<SignUp>((set: any, get: any) => ({
    redirectButtonDisabled: false,
    roles: [],
    email: '',
    gender: '',
    countryId: '',
    nationality: null,
    firstName: '',
    lastName: '',
    languageLevels: [],
    level: null,
    levelId: '',
    language: null,
    languageId: '',
    tutor: null,
    student: null,
    location: null,
    password: '',
    setRoles: async (userType: string) => {
        set({roles: [userType]})
    },
    setEmail: async (email: string) => {
        set({email: email})
    },
    setGender: async (gender: string) => {
        set({gender: gender})
    },
    setNationality: async (nationality: Country | null) => {
        set({nationality: nationality})
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
    setLanguage: async (language: Language | null) => {
        set({language: language})
    },
    setLanguageId: async (languageId: number | string) => {
        set({languageId: languageId})
    },
    setLevelId: async (levelId: number | string) => {
        set({levelId: levelId})
    },
    setCountryId: async (countryId: number | string) => {
        set({countryId: countryId})
    },
    setLevel: async (level: Level | null) => {
        set({level: level})
    },
    createUser: () => {
        const userDto: UserDto = {
            firstName: get().firstName,
            lastName: get().lastName,
            email: get().email,
            password: get().password,
            nationality: get().nationality?.countryId as number,
            roles: get().roles,
            gender: get().gender,
            languageLevels: get().languageLevels,
            tutor: get().tutor,
            student: get().student,
            location: get().location
        }
        return UserService.createUser(userDto) as unknown as UserDto;
    },
    setRedirectButtonDisabled: async () => {
        try {
            const result: boolean = await UserService.isEmailExists(get().email) as boolean;
            set({redirectButtonDisabled: result})
        } catch (error) {
            alert(error);
        }
    },
    setTutor: (tutor: Tutor | {}) => {
        set({tutor: tutor})
    },
    setStudent: (student: Student | {}) => {
        set({student: student})
    },
    setLocation: (location: string) => {
        set({location: location})
    },
    setPassword: (password: string) => {
        set({password: password})
    }
}))