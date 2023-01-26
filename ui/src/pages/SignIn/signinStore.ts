import {create} from "zustand";
import UserService from "../../services/UserService";

export interface SignInStore {
    email: string;
    password: string;
    token: string;
    setEmail: (email: string) => void;
    setPassword: (password: string) => void;
    getToken: () => void;
}

export const useSignInStore = create<SignInStore>((set: any, get: any) => ({
    email: '',
    password: '',
    token: '',
    setEmail: async (email: string) => {
        set({email: email})
    },
    setPassword: async (password: string) => {
        set({password: password})
    },
    getToken: async () => {
        UserService.login(get().email, get().password)
    }
}))