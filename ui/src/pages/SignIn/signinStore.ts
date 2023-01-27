import {create} from "zustand";
import UserService from "../../services/UserService";
import {useProfileStore} from "../Profile/profileStore";

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
    getToken: async () => {
        const response = await UserService.getToken(get().email, get().password)
        // if (true) {
            set({tokenRetrieved: true})
        // }
    }
}))