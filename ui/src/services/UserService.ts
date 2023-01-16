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
        // const roles = useSignUpStore((state) => state.roles);
        // const email = useSignUpStore((state) => state.email);
        // const password = usePasswords(state => state.password);
        // const gender = useSignUpStore(state => state.gender);
        // const nationality = useSignUpStore(state => state.nationality);
        // const firstName = useSignUpStore(state => state.firstName);
        // const lastName = useSignUpStore(state => state.lastName);
        // const languageLevels = useSignUpStore(state => state.languageLevels);
        //
        // const userDto: UserDto = {
        //     firstName: firstName,
        //     lastName: lastName,
        //     email: email,
        //     password: password,
        //     nationality: nationality,
        //     roles: roles,
        //     gender: gender,
        //     languageLevels: languageLevels
        // }

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