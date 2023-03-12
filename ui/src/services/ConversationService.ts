import {LanguageLevel} from "../pages/SignUp/store/languagesStore";
import axios, {AxiosError} from "axios";
import {Location} from "./LocationService";

export interface TutorCardInfo {
    tutorId: number,
    firstName: string,
    lastName: string,
    languageLevels: LanguageLevel[],
    minPrice: number
    avatarUrl: string,
    addresses: Location[]
}

export interface TutorCardsResponseData {
    tutors: TutorCardInfo[],
    totalCount: number
}

export interface FilterTutorsRequestDto {
    minPrice: number,
    maxPrice: number,
    countryId: number,
    city: string,
    convTypeId: string,
    languageId: number,
    minLevelId: number
}

class ConversationService {
    getTutorWithExistingConversations = async (id: number): Promise<TutorCardsResponseData> => {
        try {
            const response = await axios.get('http://localhost:8080/api/v1/conversations/details/tutors/?lastTutorId='
                + id, {withCredentials: true});
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
                    maxPrice: config.maxPrice,
                    countryId: config.countryId,
                    city: config.city,
                    convTypeId: config.convTypeId,
                    languageId: config.languageId,
                    minLevelId: config.minLevelId
                }
            });
            return response.data;
        } catch (e: unknown) {
            throw e as AxiosError;
        }
    }
}

export default new ConversationService();