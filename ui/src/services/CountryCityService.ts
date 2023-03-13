import axios, {AxiosError} from "axios";

class CountryCity {
    getCitiesByCountry = async (country: string) => {
        try {
            const response = await axios.post('https://countriesnow.space/api/v0.1/countries/cities',
                {country: country});
            return response.data.data;
        } catch (e: unknown) {
            throw e as AxiosError;
        }
    }

    getCountries = async () => {
        try {
            const response =
                await axios.get('http://localhost:8080/api/v1/countries');
            return response.data;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }
}

export default new CountryCity();
