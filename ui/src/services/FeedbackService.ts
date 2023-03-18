import axios, {AxiosError} from "axios";

export interface FeedbackCardData {
    feedbackId: number,
    firstName: string,
    lastName: string,
    tutorId: number | null,
    studentId: number | null,
    rate: number,
    feedback: string,
    languageDescription: string,
    userAvatarUrl: string
}

class FeedbackService {
    findFeedbacksAboutTutor = async (id: number): Promise<FeedbackCardData[]> => {
        try {
            const response = await axios.get('http://localhost:8080/api/v1/feedbacks/tutor/'
                + id, {withCredentials: true});
            return response.data;
        } catch (e: unknown) {
            throw e as AxiosError;
        }
    }

    findFeedbacksAboutStudent = async (id: number): Promise<FeedbackCardData[]> => {
        try {
            const response = await axios.get('http://localhost:8080/api/v1/feedbacks/student/'
                + id, {withCredentials: true});
            return response.data;
        } catch (e: unknown) {
            throw e as AxiosError;
        }
    }
}

export default new FeedbackService();
