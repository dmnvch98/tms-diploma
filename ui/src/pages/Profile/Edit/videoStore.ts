import {create} from "zustand";
import {AvatarStore} from "./avatarStore";
import FileService from "../../../services/FileService";
import {AxiosError} from "axios";

export interface VideoStore {
    newVideoUrl: string;
    editMode: boolean
    setNewVideoUrl: (url: string) => void;
    setEditMode: (flag: boolean) => void;
    uploadVideo: (file: FormData) => void;
    deleteVideo: () => void;
}

export const useAvatarStore = create<VideoStore>((set: any, get: any) => ({
    existingVideoUrl: "",
    newVideoUrl: "",
    editMode: false,
    setNewVideoUrl: async (url: string) => {
        set({newVideoUrl: url})
    },
    setEditMode: async (flag: boolean) => {
        set({editMode: flag})
    },
    uploadVideo: async (file: FormData) => {
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
    deleteVideo: async () => {
        try {
            await FileService.deleteAvatar();
        } catch (e: unknown) {
            const error = e as AxiosError;
            set({
                errorMessage: error.message,
                errorOpen: true
            })
        }
    }

}))