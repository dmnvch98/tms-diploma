import {Address} from "../../pages/Addresses/addressesStore";
import {create} from "zustand";
import LocationService from "../../services/LocationService";

export interface CreateConversationStore {
    tutorAddresses: Address[];
    getAddresses: () => void;
}

export const useCreateConversationStore = create<CreateConversationStore>((set, get: any) => ({
    tutorAddresses: [],
    getAddresses: async () => {
        const response: Address[] = await LocationService.getTutorAddresses();
        set({tutorAddresses: response})
        console.log(response);
    }
}))