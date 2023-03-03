import axios from 'axios';

class FileService {
    uploadAvatar = async (file: any) => {
            const response = await axios.post('http://localhost:8080/api/v1/files/',
                file, {
                    withCredentials: true,
                    headers: {
                        'Content-Type': `multipart/form-data; boundary=<calculated when request is sent>`,
                    },
                });
            return response.data.message;
    }

    getAvatar = async (userId: number) => {
            const response = await axios.get('http://localhost:8080/api/v1/files/avatar/' + userId, {
                withCredentials: true
            });
            return response.data.message;
    }

    deleteAvatar = async () => {
            const response = await axios.delete('http://localhost:8080/api/v1/files/avatar', {
                withCredentials: true
            });
            return response.data;
    }

    getDefaultAvatar = async () => {
            const response = await axios.get('http://localhost:8080/api/v1/files/default-avatar', {
                withCredentials: true
            });
            return response.data.message;
    }
}

export default new FileService();
