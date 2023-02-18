import {create} from "zustand";
import FileService from "../../services/FileService";
export interface EditProfileStore {
    existingAvatarUrl: string;
    newAvatarUrl: string;
    editMode: boolean
    cropper: any
    setExistingAvatarUrl: (url: string) => void;
    setNewAvatarUrl: (url: string) => void;
    setEditMode: (flag: boolean) => void;
    setCropper: (cropper: any) => void;
    uploadAvatar: (file: FormData) => void;
    getAvatar: () => void;
    deleteAvatar: () => void;
}

export const useEditProfileStore = create<EditProfileStore>((set: any) => ({
    existingAvatarUrl: "",
    newAvatarUrl: "",
    editMode: false,
    cropper: null,
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
        set({existingAvatarUrl: await FileService.uploadAvatar(file)})
    },
    getAvatar: async () => {
        set({existingAvatarUrl: await FileService.getAvatar()})
    },
    deleteAvatar: async () => {
        await FileService.deleteAvatar();
        set({existingAvatarUrl: await FileService.getDefaultAvatar()})
    }
}))