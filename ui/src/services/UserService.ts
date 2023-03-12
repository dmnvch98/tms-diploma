import axios, {AxiosError} from 'axios';
import {UserDto} from "../pages/SignUp/store/signUpStore";
import {UpdateUserDto} from "../CommonStore/store";

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
            return response.status;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

    getLevels = async () => {
        try {
            const response =
                await axios.get('http://localhost:8080/api/v1/levels');
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
            const response = await axios.get('http://localhost:9090/api/v1/users/tutors/' + tutorId);
            return response.data;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

    getUserByStudentId = async (studentId: number) => {
        try {
            const response = await axios.get('http://localhost:9090/api/v1/users/students/' + studentId);
            return response.data;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

    getToken = async (email: string, password: string) => {
        let response;
        try {
            response = await axios.post('http://localhost:8080/api/v1/auth/login',
                {email: email, password: password}, {withCredentials: true});
        } catch (e: unknown) {
        }
        return response?.status == 200;
    }

    getMe = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/v1/users/me',
                {withCredentials: true});
            return response.data;
        } catch (e: unknown) {
            const error = e as AxiosError;
            console.log(error.message);
        }
    }

    updateUser = async (user: UpdateUserDto) => {
        try {
            const response = await axios.put('http://localhost:8080/api/v1/users/', user
                , {withCredentials: true});
            return response.status == 200;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

    createTutor = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/v1/tutors/', null ,
                {withCredentials: true});
            return response.status == 201;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

    deleteTutor = async () => {
        try {
            const response = await axios.delete('http://localhost:8080/api/v1/tutors/',
                {withCredentials: true});
            return response.status == 204;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

    createStudent = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/v1/students/', null,
                {withCredentials: true});
            return response.status == 201;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

    deleteStudent = async () => {
        try {
            const response = await axios.delete('http://localhost:8080/api/v1/students/',
                {withCredentials: true});
            return response.status == 204;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

}

export default new UserService();