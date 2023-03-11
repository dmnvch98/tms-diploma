import {create} from "zustand";
import LocationService from "../../services/LocationService";

export interface LocationStore {
    defaultLatitude: string,
    defaultLongitude: string,
    latitude: number,
    longitude: number,
    zoom: number,
    setLatitude: (latitude: number) => void,
    setLongitude: (longitude: number) => void,
    getCityCoordinates: (query: string) => void
}

export const useLocationStore = create<LocationStore>((set, get: any) => ({
    defaultLatitude: '53.709807',
    defaultLongitude: '27.953389',
    latitude: 53.709807,
    longitude: 27.953389,
    zoom: 1,
    setLongitude: async (longitude: number) => {
        set({longitude: longitude})
    },
    setLatitude: async (latitude: number) => {
        set({latitude: latitude})
    },
    getCityCoordinates: async (query: string) => {
        const response = await LocationService.getCountries(query);
        set({latitude: response.lat, longitude: response.lng, zoom: 13})
    }
}))