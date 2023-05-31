import {create} from "zustand";

export interface Passwords {
    password: string;
    confirmPassword: string;

    matches: boolean;

    displayError: boolean;

    setPassword: (password: string) => void;

    setConfirmPassword: (password: string) => void;

    setMatches: () => void;
}

export const usePasswords = create<Passwords>((set: any, get) => ({
    password: '',
    confirmPassword: '',
    matches: true,
    displayError: false,
    setPassword: async (password: string) => {
        set({password: password})
    },
    setConfirmPassword: async (confirmPassword: string) => {
        set({confirmPassword: confirmPassword})
    },
    setMatches: async () => {
        set({matches: get().password == get().confirmPassword})
    }
}))