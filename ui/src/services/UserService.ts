import axios, {AxiosError} from 'axios';
import {UserDto, useSignUpStore} from "../pages/SignUp/store";
import {usePasswords} from "../pages/SignUp/passwordStore";
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

    createUser = async (userDto: UserDto) => {
        try {
            const response = await axios.post('http://localhost:8080/api/v1/users',
                userDto);
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }
}

export default new UserService();