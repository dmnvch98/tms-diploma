import {create} from "zustand";
import ConversationService, {FilterTutorsRequestDto, TutorCardInfo} from "../../services/ConversationService";
import CountryCityService from "../../services/CountryCityService";
import {Country} from "../SignUp/store/countryStore";
export interface FindTutor {
    tutors: TutorCardInfo[],
    lastTutorId: number,
    totalCount: number,
    loadingTutorCards: boolean,
    loadingCities: boolean,
    minPrice: number | string,
    maxPrice: number | string,
    country: Country | string,
    city: string,
    cities: string[],
    countries: Country[],
    convTypeId: number | string;
    languageId: number | string,
    minLevelId: number | string,
    getTutors: () => void,
    clearTutors: () => void,
    setLoadingTutorCards: (flag: boolean) => void;
    filterTutors: () => void;
    setMinPrice: (minPrice: number) => void;
    setMaxPrice: (maxPrice: number) => void;
    clearLastTutorId: () => void;
    setCountry: (country: Country) => void;
    getCitiesByCountry: () => void;
    getCountries: () => void;
    countryId: number | string;
    setCountryId: (countryId: number | string) => void;
    setLoadingCities: (flag: boolean) => void;
    setCity: (city: string) => void;
    setConvTypeId: (convTypeId: number | string) => void;
    setLanguageId: (languageId: number | string) => void;
    setMinLevelId: (minLevelId: number | string) => void;
    clearFields: () => void;
}

export const useFindTutorStore = create<FindTutor>((set, get: any) => ({
    tutors: [],
    lastTutorId: 0,
    loadingTutorCards: true,
    totalCount: 0,
    minPrice: '',
    maxPrice: '',
    city: '',
    country: '',
    cities: [],
    countries: [],
    countryId: '',
    loadingCities: false,
    convTypeId: '',
    languageId: '',
    minLevelId: '',
    getTutors: async () => {
        get().maxPrice != '' || get().country != '' || get().convTypeId != '' || get().languageId != ''
            || get().minLevelId != ''
            ? get().filterTutors()
            : await get().getAllTutors()
    },
    getAllTutors: async () => {
        try {
            await ConversationService.findTutorsWhoHaveNotBookedConvDetails(get().lastTutorId)
                .then(response => {
                    const lastTutorId: number = Math.max(...response.tutors.map(t => t.tutorId));
                    set({
                        tutors: [...get().tutors, ...response.tutors],
                        lastTutorId: lastTutorId,
                        totalCount: response.totalCount
                    })
                })
        } catch (e: any) {
            alert(e as string)
        } finally {
            set({loadingTutorCards: false})
        }
    },
    setLoadingTutorCards: async (flag: boolean) => {
        set({loadingTutorCards: flag})
    },
    filterTutors: async () => {
        try {
            const filterTutorsRequestDto: FilterTutorsRequestDto =
                {
                    minPrice: get().minPrice,
                    maxPrice: get().maxPrice,
                    countryId: get().countryId,
                    city: get().city,
                    convTypeId: get().convTypeId,
                    languageId: get().languageId,
                    minLevelId: get().minLevelId
                }
            await ConversationService.filterTutorsWithExistingConversations(get().lastTutorId, filterTutorsRequestDto)
                .then(response => {
                    const lastTutorId: number = Math.max(...response.tutors.map(t => t.tutorId));
                    set({
                        tutors: [...get().tutors, ...response.tutors],
                        lastTutorId: lastTutorId,
                        totalCount: response.totalCount
                    })
                })
        } catch (e: any) {
            console.log(e as string)
        } finally {
            set({loadingTutorCards: false})
        }
    },
    clearTutors: () => {
        set({tutors: []})
    },
    setMinPrice: (minPrice: number) => {
        set({minPrice: minPrice})
    },
    setMaxPrice: (maxPrice: number) => {
        set({maxPrice: maxPrice})
    },
    clearLastTutorId: () => {
        set({lastTutorId: 0})
    },
    setCountry: (country: Country) => {
        set({country: country})
    },
    getCitiesByCountry: async () => {
        try {
            set({loadingCities: true})
            set({cities: await CountryCityService.getCitiesByCountry(get().country.description)})
        } catch (e) {
            console.log(e)
        } finally {
            set({loadingCities: false})
        }

    },
    getCountries: async () => {
        set({countries: await CountryCityService.getCountries()})
    },
    setCountryId: async (countryId: number | string) => {
        set({countryId: countryId})
    },
    setLoadingCities: async (flag: boolean) => {
        set({loadingCities: flag})
    },
    setCity: async (city: string) => {
        set({city: city})
    },
    setConvTypeId: async (convTypeId: number | string) => {
        if (convTypeId == 2) {
            set({country: '', countryId: '', city: '', cities: []})
        }
        set({convTypeId: convTypeId})
    },
    setLanguageId: async (languageId: number | string) => {
        set({languageId: languageId})
    },
    setMinLevelId: async (minLevelId: number | string) => {
        set({minLevelId: minLevelId})
    },
    clearFields: async () => {
        set({
            minPrice: '',
            maxPrice: '',
            city: '',
            country: '',
            cities: [],
            countries: [],
            countryId: '',
            convTypeId: '',
            languageId: '',
            minLevelId: ''
        })
    }
}))