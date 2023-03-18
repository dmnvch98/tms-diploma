import {ConversationDetails} from "../../Components/Profile/Tutor/tutorStore";
import {create} from "zustand";
import ConversationService from "../../services/ConversationService";

export interface ConversationStatus {
    convStatusId: number,
    description: string
}

export interface Conversation {
    studentId: number,
    status: ConversationStatus,
    studentLeftFeedback: boolean,
    tutorLeftFeedback: boolean,
    conversationDetails: ConversationDetails
}

export interface ConversationStore {
    tutorConversations: Conversation[],
    studentConversations: Conversation[],
    getTutorConversations: () => void;
    getStudentConversations: () => void;
}

export const useConversationsStore = create<ConversationStore>((set) => ({
    tutorConversations: [],
    studentConversations: [],
    getTutorConversations: async () => {
        await ConversationService.getTutorConversations().then(result => {
            if (result) {
                set({tutorConversations: result})
            }
        })
    },
    getStudentConversations: async () => {
        await ConversationService.getStudentConversations().then(result => {
            if (result) {
                set({studentConversations: result})
            }
        })
    },
}))