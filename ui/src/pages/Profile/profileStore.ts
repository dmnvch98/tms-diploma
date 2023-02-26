import {create} from "zustand";
import UserService from "../../services/UserService";
import {User} from "../../CommonStore/store";

export interface ProfileStore {
    user: User | null;
    setUser: (user: User) => void;
    getUserByStudentId: (studentId: number) => void;
    getUserByTutorId: (tutorId: number) => void;
    getMe: () => void;
}

export const useProfileStore = create<ProfileStore>((set: any) => ({
    user: null,
    setUser: async (user: User) => {
        set({user: user})
    },
    getUserByStudentId: async (studentId: number) => {
        const user: User = await UserService.getUserByStudentId(studentId);
        if (user.student.aboutMe == null) {
            user.student.aboutMe = '';
        }
        set({user: user})
    },
    getUserByTutorId: async (tutorId: number) => {
        const user: User = await UserService.getUserByTutorId(tutorId);
        if (user.tutor.aboutMe == null) {
            user.tutor.aboutMe = '';
        }
        set({user: user})
    },
    getMe: async () => {
        const user: User = await UserService.getMe();
        if (user?.student != null) {
            if (user.student.aboutMe == null) {
                user.student.aboutMe = '';
            }
        }
        if (user?.tutor != null) {
            if (user.tutor.aboutMe == null) {
                user.tutor.aboutMe = '';
            }
        }
        set({user: user})
    }
}))
