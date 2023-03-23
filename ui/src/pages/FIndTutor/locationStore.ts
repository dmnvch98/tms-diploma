import {create} from "zustand";
import LocationService from "../../services/AddressService";

export interface LocationStore {
    latitude: number,
    longitude: number,
    zoom: number,
    getCityCoordinates: (query: string) => void;
}

export const useLocationStore = create<LocationStore>((set, get) => ({
    latitude: 53.709807,
    longitude: 27.953389,
    zoom: 1,
    getCityCoordinates: async (query: string) => {
        const response = await LocationService.getCoordinatesByAdress(query);
        set({latitude: response.results[0].geometry.location.lat,
            longitude: response.results[0].geometry.location.lng, zoom: 13})
    }
}))