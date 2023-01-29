import axios, {AxiosError} from 'axios';
import {UserDto} from "../pages/SignUp/store/signUpStore";

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
            return response.data;
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

    isEmailExists = async (email: string) => {
        try {
            const response =
                await axios.get('http://localhost:8080/api/v1/users/exists/' + email);
            const result: boolean = response.data;
            return result;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

    getUserByTutorId = async (tutorId: number) => {
        try {
            const response = await axios.get('http://localhost:9090/api/v1/users/tutors/' + tutorId,
                {withCredentials: true});
            return response.data;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

    getUserByStudentId = async (studentId: number) => {
        try {
            const response = await axios.get('http://localhost:9090/api/v1/users/students/' + studentId,
                {withCredentials: true});
            return response.data;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

    getToken = async (email: string, password: string) => {
        let response;
        try{
            response = await axios.post('http://localhost:8080/api/v1/auth/login',
                {email: email, password: password}, {withCredentials: true});
        }
        catch (e: unknown) {}
        return response?.status == 200;
    }

    getMe = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/v1/users/me',
                {withCredentials: true});
            return response.data;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

}

export default new UserService();