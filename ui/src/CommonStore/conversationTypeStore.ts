import {create} from "zustand";
import {FindTutor} from "../pages/FIndTutor/findTutorStore";

export interface ConvType {
    convTypeId: number;
    description: string;
}

interface ConvTypeStore {
    convTypes: ConvType[];
}

export const useConversationTypeStore = create<ConvTypeStore>(() => ({
    convTypes: [
        {convTypeId: 1, description: "Offline"},
        {convTypeId: 2, description: "Online"}
    ]
}))