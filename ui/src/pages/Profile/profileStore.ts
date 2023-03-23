import {create} from "zustand";
import UserService from "../../services/UserService";
import {User} from "../../CommonStore/store";

export interface ProfileStore {
    loggedInUser: User | null;
    lookupUser: User | null;
    setUser: (user: User) => void;
    getUserByStudentId: (studentId: number) => void;
    getUserByTutorId: (tutorId: number) => void;
    getMe: () => Promise<boolean>;
    logout: () => Promise<boolean>;
}

export const useProfileStore = create<ProfileStore>((set: any, get: any) => ({
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
    getMe: async (): Promise<boolean> => {
        const response = await UserService.getMe();

        if (response) {
            const user: User = response as User;
            if (user.student) {
                if (!user.student.aboutMe) {
                    user.student.aboutMe = '';
                }
            }
            if (user.tutor) {
                if (!user.tutor.aboutMe) {
                    user.tutor.aboutMe = '';
                }
            }
            set({loggedInUser: user})
            return true;
        }
        return false;
    },
    logout: (): Promise<boolean> => {
        return UserService.logout();
    }

}))
