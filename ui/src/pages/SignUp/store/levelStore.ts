import {create} from "zustand";
import UserService from "../../../services/UserService";

export interface Level {
    levelId: number;
    description: string;
}
export interface LevelList {
    levelsList: Level[];

    getLevels: () => void;
}

export const useLevelsStore = create<LevelList>((set) => ({
    levelsList: [],
    getLevels: async () => {
        try {
            const response = await UserService.getLevels();
            set({levelsList: response})
        } catch (error) {
            set({levelsList: []})
        }
    }
}))