import {create} from "zustand";
import UserService from "../../services/UserService";
import {User} from "../../CommonStore/store";

export interface ProfileStore {
    user: User | null;
    setUser: (user: User) => void;
    getUser: () => void;
}

export const useProfileStore = create<ProfileStore>((set: any) => ({
    user: null,
    setUser: async (user: User) => {
        set({user: user})
    },
    getUser: async () => {
        const response = await UserService.getUser(19);
        set({user: response?.data})
    }
}))