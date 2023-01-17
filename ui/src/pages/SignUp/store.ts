import {create} from 'zustand';
import {Language, LanguageLevel, Level} from "./languagesStore";
import UserService from "../../services/UserService";

export interface UserDto {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    nationality: string;
    roles: string;
    gender: string;
    languageLevels: LanguageLevel[];
}

export interface SignUp {
    roles: string;
    email: string;
    password: string;

    gender: string;
    firstName: string;
    lastName: string;
    nationality: string,


    languageLevels: LanguageLevel[];
    level: Level | null;
    levelId: number | string;
    levelDescription: string;
    language: Language | null;
    languageId: number | string;
    languageDescription: string;

    setRoles: (role: string) => void;
    setEmail: (email: string) => void;
    setPassword: (password: string) => void;

    setGender: (gender: string) => void;
    setNationality: (nationality: string) => void;

    setFirstName: (firstname: string) => void;
    setLastName: (lastName: string) => void;
    setLanguageLevels: (languageLevels: LanguageLevel[]) => void;

    setLanguage: (language: Language | null) => void;
    setLanguageId: (languageId: number | string) => void;
    setLanguageDescription: (description: string) => void;
    setLevel: (level: Level | null) => void;
    setLevelId: (levelId: number | string) => void;
    setLevelDescription: (description: string) => void;

    createUser: (userDto: UserDto) => void;
}

export const useSignUpStore = create<SignUp>((set: any) => ({
    roles: '',
    email: '',
    password: '',
    gender: '',
    nationality: '',
    firstName: '',
    lastName: '',
    languageLevels: [],
    level: null,
    levelId: '',
    levelDescription: '',
    language: null,
    languageId: '',
    languageDescription: '',
    setRoles: async (userType: string) => {
        set({roles: userType})
    },
    setEmail: async (email: string) => {
        set({email: email})
    },
    setPassword: async (password: string) => {
        set({password: password})
    },
    setGender: async (gender: string) => {
        set({gender: gender})
    },
    setNationality: async (nationality: string) => {
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
    setLanguageDescription: async (languageDescription: string) => {
        set({languageDescription: languageDescription})
    },
    setLevelId: async (levelId: number | string) => {
        set({levelId: levelId})
    },
    setLevelDescription: async (levelDescription: string) => {
        set({levelDescription: levelDescription})
    },
    setLevel: async (level: Level | null) => {
        set({level: level})
    },
    createUser: (userDto: UserDto) => {
        const response = UserService.createUser(userDto);
    },
}))