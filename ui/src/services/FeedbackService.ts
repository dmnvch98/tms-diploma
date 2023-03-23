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

export interface FeedbackAboutStudentRequestDto {
    conversationId: number
    tutorRate: number
    tutorFeedback: string
}

export interface FeedbackAboutTutorRequestDto {
    conversationId: number
    studentRate: number
    studentFeedback: string
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

    saveFeedbackAboutStudent = async (dto: FeedbackAboutStudentRequestDto) => {
        try {
            const response = await axios.put('http://localhost:8080/api/v1/feedbacks/student/',
                dto, {withCredentials: true});
            return response.status == 201;
        } catch (e: unknown) {
            throw e as AxiosError;
        }
    }

    saveFeedbackAboutTutor = async (dto: FeedbackAboutTutorRequestDto) => {
        try {
            const response = await axios.put('http://localhost:8080/api/v1/feedbacks/tutor/',
                dto, {withCredentials: true});
            return response.status == 201;
        } catch (e: unknown) {
            throw e as AxiosError;
        }
    }
}

export default new FeedbackService();
