import LocationService, {Location} from "../../services/AddressService";
import {Country} from "../SignUp/store/countryStore";
import {create} from "zustand";
import CountryCityService from "../../services/CountryCityService";

export interface Address {
    addressId: number | string,
    address: string,
    city: string,
    latitude: string,
    longitude: string,
    countryId: number
}

export interface AddressesStore {
    location: Location | string;
    address: string;
    countries: Country[],
    country: Country | string,
    countryIdToDisplay: number | string, // This country id is used to display a selected country in drop-down
    countryIdToSaveAddress: number | string, // This countryId is used to send the POST request
    cityToDisplay: string, //This city is used to display on the page
    cityToSaveAddress: string, // This city is used to send the POST request
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
    setCityToDisplay: (city: string) => void;
    setCountryIdToDisplay: (countryId: number | string) => void;
    setCountry: (country: Country) => void;
    getAddressByCoordinates: () => void;
    saveTutorAddress: () => Promise<boolean>;
}

export const useAddAddressStore = create<AddressesStore>((set: any, get: any) => ({
    location: '',
    address: '',
    countries: [],
    cities: [],
    cityToDisplay: '',
    cityToSaveAddress: '',
    loadingCities: false,
    countryIdToDisplay: '',
    countryIdToSaveAddress: '',
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
    setCountryIdToDisplay: async (countryId: number | string) => {
        set({countryIdToDisplay: countryId})
    },
    setCityToDisplay: async (city: string) => {
        set({cityToDisplay: city})
    },
    setCountry: (country: Country) => {
        set({country: country})
    },
    getAddressByCoordinates: async () => {
        const latlng: string = get().selectedLatitude + ',' + get().selectedLongitude;
        const responseAddress = await LocationService.getAddressByCoordinates(latlng);
        const addressComponents = responseAddress.address_components;
        const cityObject = addressComponents.find((component: any) => component.types.includes("locality"));
        const countryObject = addressComponents.find((component: any) => component.types.includes("country"));
        const countryId = get().countries.find((country: any) => country.description == countryObject.long_name).countryId

        set({
            address: responseAddress.formatted_address,
            countryIdToSaveAddress: countryId,
            cityToSaveAddress: cityObject?.long_name,
        });
    },
    setSelectedLatitude: async (selectedLatitude: number) => {
        set({selectedLatitude: selectedLatitude})
    },
    setSelectedLongitude: async (selectedLongitude: number) => {
        set({selectedLongitude: selectedLongitude})
    },
    saveTutorAddress: async (): Promise<boolean> => {
        const address: Address = {
            addressId: '',
            address: get().address,
            latitude: get().selectedLatitude,
            longitude: get().selectedLongitude,
            city: get().cityToSaveAddress,
            countryId: get().countryIdToSaveAddress
        }
        return await LocationService.saveTutorAddress(address);
    }
}))