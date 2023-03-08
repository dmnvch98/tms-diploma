import {create} from "zustand";
import ConversationService, {FilterTutorsRequestDto, TutorCardInfo} from "../../services/ConversationService";

export interface FindTutor {
    tutors: TutorCardInfo[],
    lastTutorId: number,
    totalCount: number,
    loading: boolean,
    minPrice: number,
    maxPrice: number,
    getTutors: () => void,
    clearTutors: () => void,
    setLoading: (flag: boolean) => void;
    filterTutors: () => void;
    setMinPrice: (minPrice: number) => void;
    setMaxPrice: (maxPrice: number) => void;
    clearLastTutorId: () => void;
}

export const useFindTutorStore = create<FindTutor>((set, get: any) => ({
    tutors: [],
    lastTutorId: 0,
    loading: true,
    totalCount: 0,
    minPrice: 0,
    maxPrice: 0,
    getTutors: async () => {
        get().maxPrice != 0
            ? get().filterTutors()
            : await get().getAllTutors()
    },
    getAllTutors: async () => {
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
    },
    filterTutors: async () => {
        try {
            const filterTutorsRequestDto: FilterTutorsRequestDto =
                {
                    minPrice: get().minPrice,
                    maxPrice: get().maxPrice
                }
            await ConversationService.filterTutorsWithExistingConversations(get().lastTutorId, filterTutorsRequestDto)
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
    clearTutors: () => {
        set({tutors: []})
    },
    setMinPrice: (minPrice: number) => {
        set({minPrice: minPrice})
    },
    setMaxPrice: (maxPrice: number) => {
        set({maxPrice: maxPrice})
    },
    clearLastTutorId: () => {
        set({lastTutorId: 0})
    }
}))