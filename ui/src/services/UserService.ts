import axios, {AxiosError} from 'axios';
import {UserDto, useSignUpStore} from "../pages/SignUp/store/signUpStore";
import {usePasswords} from "../pages/SignUp/store/passwordStore";
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

    createUser = async (userDto: UserDto) => {
        try {
            const response = await axios.post('http://localhost:8080/api/v1/users',
                userDto);
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

    getLevels = async () => {
        try {
            const response =
                await axios.get('http://localhost:9090/api/v1/levels');
            return response.data;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

    isEmailExists = async(email: string) => {
        try {
            const response =
                await axios.get('http://localhost:8080/api/v1/users/is-exists/' + email);
            const result: boolean = response.data;
            return result;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

}

export default new UserService();