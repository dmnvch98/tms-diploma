import FeedbackService, {
    FeedbackAboutStudentRequestDto,
    FeedbackAboutTutorRequestDto,
    FeedbackCardData
} from "../../../services/FeedbackService";
import {create} from "zustand";

export interface FeedbackStore {
    feedbacksAboutTutor: FeedbackCardData[],
    feedbacksAboutStudent: FeedbackCardData[],
    leaveFeedBackModalOpen: boolean;
    rate: number;
    conversationId: number | null;
    feedback: string
    profileType: string
    getFeedbacksAboutTutor: (tutorId: number) => void;
    getFeedbacksAboutStudent: (studentId: number) => void;
    setLeaveFeedBackModalOpen: (flag: boolean) => void;
    setRate: (rate: number) => void;
    setConversationId: (id: number) => void;
    setFeedback: (feedback: string) => void;
    saveFeedbackAboutTutor: () => Promise<boolean>
    saveFeedbackAboutStudent: () => Promise<boolean>
    setProfileType: (profile: string) => void;
    saveFeedback: () => Promise<boolean>;
    clearFields: () => void;
}

export const useFeedbackStore = create<FeedbackStore>((set, get) => ({
    feedbacksAboutStudent: [],
    feedbacksAboutTutor: [],
    leaveFeedBackModalOpen: false,
    rate: 0,
    conversationId: null,
    feedback: '',
    profileType: '',
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
    },
    setLeaveFeedBackModalOpen: (flag: boolean) => {
        set({leaveFeedBackModalOpen: flag});
    },
    setRate: async (rate: number) => {
        set({rate: rate})
    },
    setFeedback: async (feedback: string) => {
        set({feedback: feedback})
    },
    setConversationId: async (convId: number) => {
        set({conversationId: convId})
    },
    saveFeedbackAboutStudent: async (): Promise<boolean> => {
        const dto: FeedbackAboutStudentRequestDto = {
            conversationId: get().conversationId as number,
            tutorRate: get().rate,
            tutorFeedback: get().feedback
        }
        get().clearFields();

        return await FeedbackService.saveFeedbackAboutStudent(dto);
    },
    saveFeedbackAboutTutor: async (): Promise<boolean> => {
        const dto: FeedbackAboutTutorRequestDto = {
            conversationId: get().conversationId as number,
            studentRate: get().rate,
            studentFeedback: get().feedback
        }
        get().clearFields();
        return await FeedbackService.saveFeedbackAboutTutor(dto);
    },
    setProfileType: (profileType: string) => {
        set({profileType: profileType})
    },
    saveFeedback: (): Promise<boolean> => {
        set({leaveFeedBackModalOpen: !get().leaveFeedBackModalOpen})
        return get().profileType == "tutor"
            ? get().saveFeedbackAboutStudent()
            : get().saveFeedbackAboutTutor()
    },
    clearFields: () => {
        set({
            conversationId: null,
            feedback: '',
            rate: 0
        })
    }
}))
