import {create} from "zustand";
import ConversationService, {TutorCardInfo} from "../../services/ConversationService";

export interface FindTutor {
    tutors: TutorCardInfo[],
    lastTutorId: number,
    totalCount: number,
    loading: boolean,
    getTutors: () => void,
    setLoading: (flag: boolean) => void;
}

export const useFindTutorStore = create<FindTutor>((set, get: any) => ({
    tutors: [],
    lastTutorId: 0,
    loading: true,
    totalCount: 0,
    getTutors: async () => {
        try {
            await ConversationService.getTutorWithExistingConversations(get().lastTutorId)
                .then(response => {
                    const lastTutorId: number = Math.max(...response.tutors.map(t => t.tutorId));
                    set({
                        tutors: [...get().tutors, ...response.tutors],
                        lastTutorId: lastTutorId,
                        totalCount: response.totalCount
                    })
                })
        } catch (e: any) {
            alert(e as string)
        } finally {
            set({loading: false})
        }
    },
    setLoading: async (flag: boolean) => {
        set({loading: flag})
    }
}))