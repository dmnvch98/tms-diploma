import axios, {AxiosError} from "axios";

export interface Location {
    latitude: number,
    longitude: number
}

class LocationService {
    getCountries = async (query: string) => {
        try {
            const response =
                await axios.get('http://localhost:9093/api/v1/addresses/city-info?query=' + query
                    + '&key=AIzaSyAJ7QA6FkbHEVQXQlUH0rq2nuS0Khv1HUc');
            return response.data.results[0].geometry.location;
        } catch (e: unknown) {
            const error = e as AxiosError;
            console.log(error.message);
        }
    }
}

export default new LocationService();