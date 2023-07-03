import {create} from "zustand";
import FileService from "../../../services/FileService";
import {AxiosError} from "axios";

export interface AvatarStore {
    existingAvatarUrl: string;
    newAvatarUrl: string;
    editMode: boolean
    cropper: any
    setNewAvatarUrl: (url: string) => void;
    setEditMode: (flag: boolean) => void;
    setCropper: (cropper: any) => void;
    uploadAvatar: (file: FormData) => void;
    getAvatar: (userId: number) => Promise<boolean>;
    deleteAvatar: () => void;
}

export const useAvatarStore = create<AvatarStore>((set: any, get: any) => ({
    existingAvatarUrl: "",
    newAvatarUrl: "",
    editMode: false,
    cropper: null,
    updateUserDto: null,
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
    getAvatar: async (userId: number): Promise<boolean> => {
        try {
            const avatarUrl: string = await FileService.getAvatar(userId);
            set({existingAvatarUrl: avatarUrl})
            return true;
        } catch (e: unknown) {
            return false;
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
    }
}))