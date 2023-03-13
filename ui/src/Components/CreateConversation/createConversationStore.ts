import {Address} from "../../pages/Addresses/addressesStore";
import {create} from "zustand";
import LocationService from "../../services/LocationService";
import ConversationService from "../../services/ConversationService";

export interface ConversationDetailsRequestDto {
    conversationTypeId: number,
    price: number,
    addressId: number,
    minLevelId: number,
    languageId: number,
    startDate: string,
    endDate: string
}

export interface CreateConversationStore {
    tutorAddresses: Address[];
    addressId: number | string;
    price: number | string;
    convTypeId: number | string;
    date: string;
    startTime: string;
    endTime: string;
    languageId: number | string;
    levelId: number | string;
    getAddresses: () => void;
    setAddressId: (addressId: number) => void;
    setPrice: (price: number) => void;
    setConvTypeId: (convTypeId: number) => void;
    setStartTime: (startDate: string) => void;
    setEndTime: (endDate: string) => void;
    setDate: (date: string) => void;
    setLanguageId: (languageId: number | string) => void;
    setMinLevelId: (minLevelId: number | string) => void;
    createConversation: () => void;

}

export const useCreateConversationStore = create<CreateConversationStore>((set, get: any) => ({
    tutorAddresses: [],
    addressId: '',
    price: '',
    convTypeId: '',
    startTime: '',
    endTime: '',
    date: '',
    languageId: '',
    levelId: '',
    getAddresses: async () => {
        const response: Address[] = await LocationService.getTutorAddresses();
        set({tutorAddresses: response})
    },
    setAddressId: async (addressId: number) => {
        set({addressId: addressId});
    },
    setPrice: async (price: number) => {
        set({price: price})
    },
    setConvTypeId: async (convTypeId: number) => {
        set({convTypeId: convTypeId})
    },
    setStartTime: async (startTime: string) => {
        set({startTime: startTime})
    },
    setEndTime: async (endTime: string) => {
        set({endTime: endTime})
        console.log(endTime)
    },
    setDate: async (date: string) => {
        set({date: date});
    },
    setLanguageId: async (languageId: number | string) => {
        set({languageId: languageId})
    },
    setMinLevelId: async (minLevelId: number | string) => {
        set({levelId: minLevelId})
    },
    createConversation: async () => {
        const dto: ConversationDetailsRequestDto = {
            conversationTypeId: get().convTypeId,
            price: get().price,
            addressId: get().addressId,
            minLevelId: get().levelId,
            languageId: get().languageId,
            startDate: get().date + " " + get().startTime,
            endDate: get().date + " " + get().endTime
        }
        console.log(dto);
        await ConversationService.saveConversationDetails(dto);
    }
}))