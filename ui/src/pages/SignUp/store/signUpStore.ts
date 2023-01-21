import {create} from 'zustand';
import {Language, LanguageLevel} from "./languagesStore";
import UserService from "../../../services/UserService";
import {Country} from "./countryStore";
import {Level} from "./levelStore";

export interface UserDto {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    nationality: Country;
    roles: string;
    gender: string;
    languageLevels: LanguageLevel[];
}

export interface SignUp {
    redirectButtonDisabled: boolean;
    roles: string;
    email: string;
    password: string;

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

    setRoles: (role: string) => void;
    setEmail: (email: string) => void;
    setPassword: (password: string) => void;

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
    createUser: (userDto: UserDto) => UserDto;
    setRedirectButtonDisabled: () => void;

}

export const useSignUpStore = create<SignUp>((set: any, get: any) => ({
    redirectButtonDisabled: false,
    roles: '',
    email: '',
    password: '',
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
    createUser: (userDto: UserDto) => {
        return UserService.createUser(userDto) as unknown as UserDto;
    },
    setRedirectButtonDisabled: async () => {
        try {
            const result: boolean = await UserService.isEmailExists(get().email) as boolean;
            set({redirectButtonDisabled: result})
        } catch (error) {
            alert(error);
        }
    }
}))