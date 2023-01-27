import {create} from "zustand";

export interface SignInStore {
    email: string;
    password: string;
    isAuthorized: boolean;
    setEmail: (email: string) => void;
    setPassword: (password: string) => void;

    setIsAuthorized: (flag: boolean) => void;
}

export const useSignInStore = create<SignInStore>((set: any, get: any) => ({
    email: '',
    password: '',
    isAuthorized: false,
    setEmail: async (email: string) => {
        set({email: email})
    },
    setPassword: async (password: string) => {
        set({password: password})
    },
    setIsAuthorized: async (flag: boolean) => {
        set({isAuthorized: flag})
    }
}))