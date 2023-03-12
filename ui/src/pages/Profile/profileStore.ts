import {create} from "zustand";
import UserService from "../../services/UserService";
import {User} from "../../CommonStore/store";

export interface ProfileStore {
    loggedInUser: User | null;
    lookupUser: User | null;
    setUser: (user: User) => void;
    getUserByStudentId: (studentId: number) => void;
    getUserByTutorId: (tutorId: number) => void;
    getMe: () => void;
}

export const useProfileStore = create<ProfileStore>((set: any) => ({
    loggedInUser: null,
    lookupUser: null,
    setUser: async (user: User) => {
        set({user: user})
    },
    getUserByStudentId: async (studentId: number) => {
        const user: User = await UserService.getUserByStudentId(studentId);
        if (!user.student.aboutMe) {
            user.student.aboutMe = '';
        }
        set({lookupUser: user})
    },
    getUserByTutorId: async (tutorId: number) => {
        const user: User = await UserService.getUserByTutorId(tutorId);
        if (!user.tutor.aboutMe) {
            user.tutor.aboutMe = '';
        }
        set({lookupUser: user})
    },
    getMe: async () => {
        const user: User = await UserService.getMe();
        if (user?.student != null) {
            if (!user.student.aboutMe) {
                user.student.aboutMe = '';
            }
        }
        if (user?.tutor != null) {
            if (!user.tutor.aboutMe) {
                user.tutor.aboutMe = '';
            }
        }
        set({loggedInUser: user})
    }
}))
