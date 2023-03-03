import {create} from "zustand";

export interface Notification {
    isOpen: boolean;
    autoClose: number;
    message: string;
    setIsOpen: (flag: boolean) => void;
    setMessage: (message: string) => void;
}

export const useNotificationStore = create<Notification>((set: any) => ({
    isOpen: false,
    autoClose: 3000,
    message: '',
    setIsOpen: async (flag: boolean) => {
        set({isOpen: flag})
    },
    setMessage: async (message: string) => {
        set({message: message})
    }
}))