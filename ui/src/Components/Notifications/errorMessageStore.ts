import {create} from "zustand";

export interface ErrorMessage {
    isOpen: boolean;
    autoClose: number;
    message: string;
    setIsOpen: (flag: boolean) => void;
    setMessage: (message: string) => void;
}

export const useErrorMessageStore = create<ErrorMessage>((set: any) => ({
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