import {create} from "zustand";
import UserService from "../../services/UserService";

export interface LanguageLevel {
    languageId: number | any;
    levelId: number | any;
}

export interface Language {
    id: number;
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