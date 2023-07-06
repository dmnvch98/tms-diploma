import {create} from "zustand";
import FileService from "../../../services/FileService";
import {AxiosError} from "axios";

export interface VideoStore {
    videoUrl: string
    setVideoUrl: (url: string) => void;
    localVideoUrlToUpload: string
    setLocalVideoUrlToUpload: (url: string) => void;
    editMode: boolean
    setEditMode: (flag: boolean) => void;
    uploadStudentVideoPresentation: (file: FormData) => void;
    uploadTutorVideoPresentation: (file: FormData) => void;
    getStudentVideoPresentationUrl: (studentId: number) => void;
    getTutorVideoPresentationUrl: (tutorId: number) => void;
    deleteStudentVideoPresentation: () => Promise<boolean>;
    deleteTutorVideoPresentation: () => Promise<boolean>;
}

export const useVideoStore = create<VideoStore>((set: any, get: any) => ({
    videoUrl: "",
    localVideoUrlToUpload: "",
    editMode: false,
    setLocalVideoUrlToUpload: async (url: string) => {
        set({localVideoUrlToUpload: url})
    },
    setEditMode: async (flag: boolean) => {
        set({editMode: flag})
    },
    uploadStudentVideoPresentation: async (file: FormData) => {
        try {
            const response = await FileService.uploadStudentVideoPresentation(file);
            set({videoUrl: response})
        } catch (e: unknown) {
            const error = e as AxiosError;
            set({
                errorMessage: error.message,
                errorOpen: true,
            })
        }
    },
    uploadTutorVideoPresentation: async (file: FormData) => {
        try {
            const response = await FileService.uploadTutorVideoPresentation(file);
            set({videoUrl: response})
        } catch (e: unknown) {
            const error = e as AxiosError;
            set({
                errorMessage: error.message,
                errorOpen: true
            })
        }
    },
    setVideoUrl: async (url: string) => {
        set({videoUrl: url})
    },
    getTutorVideoPresentationUrl: async (tutorId: number) => {
        try {
            const response = await FileService.getTutorVideoPresentationUrl((tutorId));
            set({videoUrl: response});
        } catch (e: unknown) {
            const error = e as AxiosError;
            set({
                errorMessage: error.message,
                errorOpen: true,
            });
        }
    }
    ,
    getStudentVideoPresentationUrl: async (studentId: number) => {
        try {
            const response = await FileService.getStudentVideoPresentationUrl(studentId);
            set({videoUrl: response});
        } catch (e: unknown) {
            const error = e as AxiosError;
            set({
                errorMessage: error.message,
                errorOpen: true,
            });
        }
    },
    deleteStudentVideoPresentation: async ():Promise<boolean> => {
        try {
            return await FileService.deleteStudentVideoPresentation();
        } catch (e: unknown) {
            const error = e as AxiosError;
            set({
                errorMessage: error.message,
                errorOpen: true,
            });
            return false;
        }
    },
    deleteTutorVideoPresentation: async ():Promise<boolean> => {
        try {
            return await FileService.deleteTutorVideoPresentation();
        } catch (e: unknown) {
            const error = e as AxiosError;
            set({
                errorMessage: error.message,
                errorOpen: true,
            });
            return false;
        }
    }



}))