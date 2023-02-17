import axios, {AxiosError} from 'axios';

class FileService {
    uploadAvatar = async (file: any) => {
        try {
            const response = await axios.post('http://localhost:8080/api/v1/files/',
                file, {
                    withCredentials: true,
                    headers: {
                        'Content-Type': `multipart/form-data; boundary=<calculated when request is sent>`,
                    },
                });
            return response.data;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

    getAvatar = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/v1/files/avatar', {
                    withCredentials: true
                });
            return response.data;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }
}

export default new FileService();