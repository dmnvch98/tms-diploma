import axios, {AxiosError} from 'axios';
class UserService {
    getLanguages = async () => {
        try {
            const response =
                await axios.get('http://localhost:8080/api/v1/languages');
            return response.data;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }
}

export default new UserService();