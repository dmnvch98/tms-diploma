import {create} from "zustand";
import UserService from "../../../services/UserService";

export interface Country {
    countryId: number;
    description: string;
}

export interface CountriesList {
    countriesList: Country[];

    getCountries: () => void;
}

export const useCountryStore = create<CountriesList>((set) => ({
    countriesList: [],
    getCountries: async () => {
        try {
            const response = await UserService.getCountries();
            set({countriesList: response})
        } catch (error) {
            set({countriesList: []})
        }
    }
}))