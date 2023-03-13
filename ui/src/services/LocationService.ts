import axios, {AxiosError} from "axios";
import {Address} from "../pages/Addresses/addressesStore";

export interface Location {
    latitude: number,
    longitude: number
}

class LocationService {
    getLocationInfo = async (query: string) => {
        try {
            const response =
                await axios.get('http://localhost:9093/api/v1/addresses/city-info?query=' + query
                    + '&key=AIzaSyAJ7QA6FkbHEVQXQlUH0rq2nuS0Khv1HUc');
            return response.data;
        } catch (e: unknown) {
            const error = e as AxiosError;
            console.log(error.message);
        }
    }
    getAddressByCoordinates = async (latlng: string) => {
        try {
            const response =
                await axios.get('http://localhost:8080/api/v1/addresses/?latlng=' + latlng
                    , {withCredentials: true});
            return response.data.results[0].formatted_address;
        } catch (e: unknown) {
            const error = e as AxiosError;
            console.log(error.message);
        }
    }

    saveTutorAddress = async (address: Address): Promise<boolean> => {
        try {
            const response =
                await axios.post('http://localhost:8080/api/v1/addresses/', address
                    , {withCredentials: true});
            return response.status == 200;
        } catch (e: unknown) {
            const error = e as AxiosError;
            console.log(error.message);
        }
        return false;
    }

    getTutorAddresses = async () => {
        try {
            const response =
                await axios.get('http://localhost:8080/api/v1/addresses/tutors'
                    , {withCredentials: true});
            return response.data;
        } catch (e: unknown) {
            const error = e as AxiosError;
            console.log(error.message);
        }
    }
}

export default new LocationService();