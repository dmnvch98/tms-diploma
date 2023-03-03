import {LanguageLevel} from "../pages/SignUp/store/languagesStore";
import axios, {AxiosError} from "axios";

export interface TutorCardInfo {
    firstName: string,
    lastName: string,
    languageLevels: LanguageLevel[],
    minPrice: number
}

class ConversationService {
    getTutorWithExistingConversations = async () => {
        try {
            const response =
                await axios.get('http://localhost:9093/api/v1/conversations/details/tutors');
            return response.data;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }
}

export default new ConversationService();