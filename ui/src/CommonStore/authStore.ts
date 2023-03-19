import {create} from "zustand";
import UserService from "../services/UserService";
import {User} from "./store";

export interface CredentialsDto {
    email: string,
    password: string
}

export interface AuthStore {
    isAuthorized: boolean;
    getToken: (credentials: CredentialsDto) => void;
    setIsAuthorized: (flag: boolean) => void;
    createUser: () => void;
    getMe: () => Promise<User | null>;

}

export const useAuthStore = create<AuthStore>((set, get) => ({
    isAuthorized: false,
    getToken: async (credentials: CredentialsDto) => {
        set({isAuthorized: false})
        const response = await UserService.getToken(credentials.email, credentials.password);

        if (response) {
            set({isAuthorized: true})
        }

    },
    setIsAuthorized: async () => {

    },
    createUser: async () => {

    },
    getMe: async (): Promise<User | null> => {
        const user: User = await UserService.getMe();
        if (user) {
            set({isAuthorized: true})
            return user;
        }
        return null;
    }
}))
