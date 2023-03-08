import {LanguageLevel} from "../pages/SignUp/store/languagesStore";
import axios, {AxiosError} from "axios";

export interface TutorCardInfo {
    tutorId: number,
    firstName: string,
    lastName: string,
    languageLevels: LanguageLevel[],
    minPrice: number
    avatarUrl: string
}

export interface TutorCardsResponseData {
    tutors: TutorCardInfo[],
    totalCount: number
}

export interface FilterTutorsRequestDto {
    minPrice: number,
    maxPrice: number
}

class ConversationService {
    getTutorWithExistingConversations = async (id: number): Promise<TutorCardsResponseData> => {
        try {
            const response = await axios.get('http://localhost:9093/api/v1/conversations/details/tutors/?lastTutorId=' + id);
            return response.data;
        } catch (e: unknown) {
            throw e as AxiosError;
        }
    }

    filterTutorsWithExistingConversations = async (id: number, config: FilterTutorsRequestDto): Promise<TutorCardsResponseData> => {
        try {
            const response = await axios.get('http://localhost:9093/api/v1/conversations/details/tutors/filter?lastTutorId='
                + id, {
                params: {
                    minPrice: config.minPrice,
                    maxPrice: config.maxPrice
                }
            });
            return response.data;
        } catch (e: unknown) {
            throw e as AxiosError;
        }
    }
}

export default new ConversationService();