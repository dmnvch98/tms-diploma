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

    getAvatar = async (fileName: string) => {
        try {
            const response = await axios.get('http://localhost:8080/api/v1/files/avatar/' + fileName, {
                withCredentials: true
            });
            return response.data;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

    deleteAvatar = async () => {
        try {
            const response = await axios.delete('http://localhost:8080/api/v1/files/avatar', {
                withCredentials: true
            });
            return response.data;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

    getDefaultAvatar = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/v1/files/default-avatar', {
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