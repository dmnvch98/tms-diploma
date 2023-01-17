import {create} from "zustand";
import UserService from "../../../services/UserService";
import {Level} from "./levelStore";

export interface LanguageLevel {
    language: Language | any;
    level: Level | any;
}

export interface Language {
    languageId: number;
    description: string;
}

export interface LanguageList {
    languagesList: Language[];

    getLanguages: () => void;
}

export const useLanguagesStore = create<LanguageList>((set) => ({
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