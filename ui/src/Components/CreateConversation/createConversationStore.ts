import {Address} from "../../pages/Addresses/addressesStore";
import {create} from "zustand";
import LocationService from "../../services/AddressService";
import ConversationService from "../../services/ConversationService";
import dayjs from "dayjs";

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
    createConversation: () => Promise<boolean>;
    isOpen: boolean;
    setIsOpen: (flag: boolean) => void;
}

export const useCreateConversationStore = create<CreateConversationStore>((set, get: any) => ({
    tutorAddresses: [],
    addressId: '',
    price: '',
    convTypeId: '',
    startTime: '',
    endTime: '',
    date: dayjs().add(1, "day").format('YYYY-MM-DD'),
    languageId: '',
    levelId: '',
    isOpen: false,
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
    createConversation: async ():Promise<boolean> => {
        const dto: ConversationDetailsRequestDto = {
            conversationTypeId: get().convTypeId,
            price: get().price,
            addressId: get().addressId,
            minLevelId: get().levelId,
            languageId: get().languageId,
            startDate: get().date + " " + get().startTime,
            endDate: get().date + " " + get().endTime
        }
        get().clearFields();
        get().setIsOpen(!get().isOpen);
        return await ConversationService.saveConversationDetails(dto);
    },
    setIsOpen: async (flag: boolean) => {
        set({isOpen: flag})
    },
    clearFields: () => {
        set({
            convTypeId: '',
            price: '',
            addressId: '',
            levelId: '',
            languageId: '',
            startTime: '',
            endTime: ''
        })
    }
}))