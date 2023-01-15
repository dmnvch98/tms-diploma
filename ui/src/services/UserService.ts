import axios, {AxiosError} from 'axios';
export class UserService {
    getLanguages = async () => {
        try {
            const response =
                await axios.get('http://localhost:8080/api/v1/organization/settings/');
            console.log("token: " + response);
            return response.data.accessToken;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }
}