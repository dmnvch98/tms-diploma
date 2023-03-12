import axios, {AxiosError} from "axios";

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
}

export default new LocationService();