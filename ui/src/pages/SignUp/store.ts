import {create} from 'zustand';
import {Language} from "./languagesStore";

export interface LanguageLevel {
    language: Language;
    level: string;
}

export interface User {
    userType: string;
    email: string;
    password: string;

    gender: string;
    firstName: string;
    lastName: string;
    nationality: string,


    languageLevels: LanguageLevel[];
    level: string;
    language: any;

    setUserType: (userType: string) => void;
    setEmail: (email: string) => void;
    setPassword: (password: string) => void;

    setGender: (gender: string) => void;
    setNationality: (nationality: string) => void;

    setFirstName: (firstname: string) => void;
    setLastName: (lastName: string) => void;
    setLanguageLevels: (languageLevels: LanguageLevel[]) => void;

    setLevel: (level: string) => void;
    setLanguage: (language: any) => void;
}

export const useSignUpStore = create<User>((set: any) => ({
    userType: '',
    email: '',
    password: '',
    gender: '',
    nationality: '',
    firstName: '',
    lastName: '',
    languageLevels: [],
    level: '',
    language: '',
    setUserType: async (userType: string) => {
        set({userType: userType})
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
    setLanguage: async (language: any) => {
        set({language: language})
    },
    setLevel: async (level: string) => {
        set({level: level})
    }
}))