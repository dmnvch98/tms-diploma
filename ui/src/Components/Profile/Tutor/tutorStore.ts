import {Address} from "../../../pages/Addresses/addressesStore";
import {LanguageLevel} from "../../../pages/SignUp/store/languagesStore";
import {create} from "zustand";
import ConversationService from "../../../services/ConversationService";

export interface ConversationDetails {
    convDetailsId: number,
    tutorId: number,
    conversationType: {
        convTypeId: 1,
        description: string
    },
    price: number,
    address: Address,
    minLanguageLevel: LanguageLevel,
    startDate: string
    endDate: string
}

export interface TutorStore {
    convDetails: ConversationDetails[];
    getTutorNotBookedConversationDetails: (tutorId: number) => void;
    bookConversation: (convDetailsId: number) => Promise<boolean>;
}

export const useTutorStore = create<TutorStore>((set) => ({
    convDetails: [],
    getTutorNotBookedConversationDetails: async (tutorId: number) => {
        ConversationService.getTutorConversationDetails(tutorId).then(result => {
            set({convDetails: result});
        });
    },
    bookConversation: async (convDetailsId: number): Promise<boolean> => {
        return ConversationService.bookConversation(convDetailsId);
    }
}))