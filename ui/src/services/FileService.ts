import axios from 'axios';

class FileService {
    uploadAvatar = async (file: any) => {
        const response = await axios.post('http://localhost:8080/api/v1/files/',
            file, {
                withCredentials: true,
                headers: {
                    'Content-Type': `multipart/form-data; boundary=<calculated when request is sent>`,
                }
            });
        return response.data.fileUrl;
    }

    getAvatar = async (userId: number) => {
        const response = await axios.get('http://localhost:8080/api/v1/files/avatar/' + userId, {
            withCredentials: true
        });
        return response.data.fileUrl;
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

    uploadStudentVideoPresentation = async (file: any) => {
        const response = await axios.post("http://localhost:8080/api/v1/files/video-presentation/student/",
            file, {
                withCredentials: true,
                headers: {
                    'Content-Type': `multipart/form-data; boundary=<calculated when request is sent>`,
                }
            })
        return response.data.fileUrl;
    }

    uploadTutorVideoPresentation = async (file: any) => {
        const response = await axios.post("http://localhost:8080/api/v1/files/video-presentation/tutor/",
            file, {
                withCredentials: true,
                headers: {
                    'Content-Type': `multipart/form-data; boundary=<calculated when request is sent>`,
                }
            })
        return response.data.fileUrl;

    }

    getStudentVideoPresentationUrl = async (studentId: number) => {
        const response = await axios.get("http://localhost:8080/api/v1/files/video-presentation/student/" + studentId,
            {withCredentials: true})
        return response.data.fileUrl;
    }

    getTutorVideoPresentationUrl = async (tutorId: number) => {
        const response = await axios.get("http://localhost:8080/api/v1/files/video-presentation/tutor/" + tutorId,
            {withCredentials: true})
        return response.data.fileUrl;
    }

    deleteStudentVideoPresentation = async () => {
        const response = await axios.delete('http://localhost:8080/api/v1/files/video-presentation/student',
            {withCredentials: true})
        return response.data;
    }

    deleteTutorVideoPresentation = async () => {
        const response = await axios.delete('http://localhost:8080/api/v1/files/video-presentation/tutor',
            {withCredentials: true})
        return response.data;
    }
}

export default new FileService();
