import {create} from "zustand";
import UserService from "../../../services/UserService";

export interface LanguageLevel {
    language: Language | any;
    level: Level | any;
}

export interface Language {
    languageId: number;
    description: string;
}

export interface Level {
    levelId: number;
    description: string;
}

export interface LanguageList {
    languagesList: Language[];

    getLanguages: () => void;
}

export const useLanguagesStore = create<LanguageList>((set) => ({
    id: 0,
    description: 0,
    languagesList: [],
    getLanguages: async () => {
        try {
            const response = await UserService.getLanguages();
            set({languagesList: response})
        } catch (error) {
            set({languagesList: []})
        }
    }
}))