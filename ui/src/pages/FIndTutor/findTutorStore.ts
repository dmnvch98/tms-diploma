import {create} from "zustand";
import ConversationService, {TutorCardInfo} from "../../services/ConversationService";

export interface FindTutor {
    tutors: TutorCardInfo[],
    getTutors: () => void,
}

export const useFindTutorStore = create<FindTutor>((set) => ({
    tutors: [],
    getTutors: async () => {
        set({tutors: await ConversationService.getTutorWithExistingConversations()})
    }
}))