import FeedbackService, {FeedbackCardData} from "../../../services/FeedbackService";
import {create} from "zustand";

export interface FeedbackStore {
    feedbacksAboutTutor: FeedbackCardData[],
    feedbacksAboutStudent: FeedbackCardData[],
    getFeedbacksAboutTutor: (tutorId: number) => void;
    getFeedbacksAboutStudent: (studentId: number) => void;
}

export const useFeedbackStore = create<FeedbackStore>((set, get) => ({
    feedbacksAboutStudent: [],
    feedbacksAboutTutor: [],
    getFeedbacksAboutTutor: async (tutorId: number) => {
        await FeedbackService.findFeedbacksAboutTutor(tutorId).then(result => {
            if (result) {
                set({feedbacksAboutTutor: result})
            }
        })
    },
    getFeedbacksAboutStudent: async (studentId: number) => {
        await FeedbackService.findFeedbacksAboutStudent(studentId).then(result => {
            if (result) {
                set({feedbacksAboutStudent: result})
            }
        })
    }
}))
