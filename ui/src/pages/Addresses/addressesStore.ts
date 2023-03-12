import LocationService, {Location} from "../../services/LocationService";
import {Country} from "../SignUp/store/countryStore";
import {create} from "zustand";
import CountryCityService from "../../services/CountryCityService";

export interface Address {
    address: string,
    city: string,
    latitude: string,
    longitude: string
}

export interface AddressesStore {
    location: Location | string;
    address: string;
    countries: Country[],
    country: Country | string,
    countryId: number | string,
    city: string,
    cities: string[],
    loadingCities: boolean,
    setSelectedLatitude: (selectedLatitude: number) => void;
    setSelectedLongitude: (selectedLongitude: number) => void;
    selectedLatitude: number | string,
    selectedLongitude: number | string,
    setLocation: (location: Location) => void;
    setAddress: (address: string) => void;
    getCitiesByCountry: () => void;
    getCountries: () => void;
    setCity: (city: string) => void;
    setCountryId: (countryId: number | string) => void;
    setCountry: (country: Country) => void;
    getAddressByCoordinates: () => void;
    saveTutorAddress: () => void;
}

export const useAddAddressStore = create<AddressesStore>((set, get: any) => ({
    location: '',
    address: '',
    countries: [],
    cities: [],
    city: '',
    loadingCities: false,
    countryId: '',
    country: '',
    selectedLatitude: '',
    selectedLongitude: '',
    setLocation: async (location: Location) => {
        set({location: location})
    },
    setAddress: async (address: string) => {
        set({address: address})
    },
    getCitiesByCountry: async () => {
        try {
            set({loadingCities: true})
            set({cities: await CountryCityService.getCitiesByCountry(get().country.description)})
        } catch (e) {
            console.log(e)
        } finally {
            set({loadingCities: false})
        }
    },
    getCountries: async () => {
        set({countries: await CountryCityService.getCountries()})
    },
    setCountryId: async (countryId: number | string) => {
        set({countryId: countryId})
    },
    setCity: async (city: string) => {
        set({city: city})
    },
    setCountry: (country: Country) => {
        set({country: country})
    },
    getAddressByCoordinates: async () => {
        const latlng: string = get().selectedLatitude + ',' + get().selectedLongitude;
        set({address: await LocationService.getAddressByCoordinates(latlng)});
    },
    setSelectedLatitude: async (selectedLatitude: number) => {
        set({selectedLatitude: selectedLatitude})
    },
    setSelectedLongitude: async (selectedLongitude: number) => {
        set({selectedLongitude: selectedLongitude})
    },
    saveTutorAddress: async () => {
        const address: Address = {
            address: get().address,
            latitude: get().selectedLatitude,
            longitude: get().selectedLongitude,
            city: get().city
        }
        await LocationService.saveTutorAddress(address);
    }
}))