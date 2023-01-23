import {create} from "zustand";
import UserService from "../../services/UserService";
import {User} from "../../CommonStore/store";

export interface ProfileStore {
    user: User | null;
    setUser: (user: User) => void;
    getUser: (userId: number) => void;
}

export const useProfileStore = create<ProfileStore>((set: any) => ({
    user: null,
    setUser: async (user: User) => {
        set({user: user})
    },
    getUser: async (userId:number) => {
        const response = await UserService.getUser(userId);
        set({user: response?.data})
    }
}))