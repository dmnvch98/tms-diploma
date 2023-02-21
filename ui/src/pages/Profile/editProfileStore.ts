import {create} from "zustand";
import FileService from "../../services/FileService";
import {AxiosError} from "axios/index";

export interface EditProfileStore {
    existingAvatarUrl: string;
    newAvatarUrl: string;
    editMode: boolean
    cropper: any
    errorMessage: string;
    errorOpen: boolean
    setExistingAvatarUrl: (url: string) => void;
    setNewAvatarUrl: (url: string) => void;
    setEditMode: (flag: boolean) => void;
    setCropper: (cropper: any) => void;
    uploadAvatar: (file: FormData) => void;
    getAvatar: (userId: number) => void;
    deleteAvatar: () => void;
    getDefaultAvatar: () => void;
    setErrorMessage: (message: any) => void;
    setErrorOpen: (flag: boolean) => void;
}

export const useEditProfileStore = create<EditProfileStore>((set: any, get: any) => ({
    existingAvatarUrl: "",
    newAvatarUrl: "",
    editMode: false,
    cropper: null,
    errorMessage: '',
    errorOpen: false,
    setExistingAvatarUrl: async (url: string) => {
        set({existingAvatarUrl: url})
    },
    setNewAvatarUrl: async (url: string) => {
        set({newAvatarUrl: url})
    },
    setEditMode: async (flag: boolean) => {
        set({editMode: flag})
    },
    setCropper: async (cropper: any) => {
        set({cropper: cropper})
    },
    uploadAvatar: async (file: FormData) => {
        try {
            const response = await FileService.uploadAvatar(file);
            set({existingAvatarUrl: response})
        } catch (e: unknown) {
            const error = e as AxiosError;
            set({
                errorMessage: error.message,
                errorOpen: true
            })
        }
    },
    getAvatar: async (userId: number) => {
        try {
            set({existingAvatarUrl: await FileService.getAvatar(userId)})
        } catch (e: unknown) {
            const error = e as AxiosError;
            set({
                errorMessage: error.message,
                errorOpen: true
            })
        }

    },
    deleteAvatar: async () => {
        try {
            await FileService.deleteAvatar();
            const url = await FileService.getDefaultAvatar();
            set({existingAvatarUrl: url})
        } catch (e: unknown) {
            const error = e as AxiosError;
            set({
                errorMessage: error.message,
                errorOpen: true
            })
        }
    },
    getDefaultAvatar: async () => {
        try {
            const url = await FileService.getDefaultAvatar();
            set({existingAvatarUrl: url})
        } catch (e: unknown) {
            const error = e as AxiosError;
            set({
                errorMessage: error.message,
                errorOpen: true
            })
        }
    },
    setErrorMessage: async (message: string) => {
        set({errorMessage: message})
    },
    setErrorOpen: async (flag: boolean) => {
        set({errorOpen: flag})
    }

}))