import {create} from "zustand";
import LocationService from "../../services/LocationService";

export interface LocationStore {
    latitude: number,
    longitude: number,
    selectedLatitude: number | string,
    selectedLongitude: number | string,
    zoom: number,
    getCityCoordinates: (query: string) => void;
    setSelectedLatitude: (selectedLatitude: number) => void;
    setSelectedLongitude: (selectedLongitude: number) => void;
}

export const useLocationStore = create<LocationStore>((set) => ({
    latitude: 53.709807,
    longitude: 27.953389,
    selectedLatitude: '',
    selectedLongitude: '',
    zoom: 1,
    getCityCoordinates: async (query: string) => {
        const response = await LocationService.getLocationInfo(query);
        set({latitude: response.results[0].geometry.location.lat,
            longitude: response.results[0].geometry.location.lng, zoom: 13})
    },
    setSelectedLatitude: async (selectedLatitude: number) => {
        set({selectedLatitude: selectedLatitude})
    },
    setSelectedLongitude: async (selectedLongitude: number) => {
        set({selectedLongitude: selectedLongitude})
    },
}))