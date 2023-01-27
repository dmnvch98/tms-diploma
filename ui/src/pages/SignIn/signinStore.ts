import {create} from "zustand";
import UserService from "../../services/UserService";
import {redirect} from "react-router-dom";

export interface SignInStore {
    email: string;
    password: string;
    tokenRetrieved: boolean;
    setEmail: (email: string) => void;
    setPassword: (password: string) => void;
    getToken: () => void;
}

export const useSignInStore = create<SignInStore>((set: any, get: any) => ({
    email: '',
    password: '',
    tokenRetrieved: false,
    setEmail: async (email: string) => {
        set({email: email})
    },
    setPassword: async (password: string) => {
        set({password: password})
    },
    getToken: () => {
        UserService.getToken(get().email, get().password).then()
        set({tokenRetrieved: true})
    }
}))