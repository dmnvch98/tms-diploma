import {create} from "zustand";
import UserService from "../../services/UserService";

export interface SignInStore {
    email: string;
    password: string;
    isAuthorized: boolean;
    snackbarOpen: boolean;
    setEmail: (email: string) => void;
    setPassword: (password: string) => void;
    setIsAuthorized: (flag: boolean) => void;
    getToken: () => void;
    setSnackBar: (flag: boolean) => void;
}

export const useSignInStore = create<SignInStore>((set: any, get: any) => ({
    email: '',
    password: '',
    isAuthorized: false,
    snackbarOpen: false,
    setEmail: async (email: string) => {
        set({email: email})
    },
    setPassword: async (password: string) => {
        set({password: password})
    },
    setIsAuthorized: async (flag: boolean) => {
        set({isAuthorized: flag})
    },
    getToken: async () => {
        set({isAuthorized: false})
        await UserService.getToken(get().email, get().password)
            ? set({isAuthorized: true})
            : set({snackbarOpen: true})
    },
    setSnackBar: async (flag: boolean) => {
        set({snackbarOpen: flag})
    }
}))