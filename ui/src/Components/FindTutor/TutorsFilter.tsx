import {Autocomplete, Button, Container, Grid, MenuItem, TextField} from "@mui/material";
import {ConvType, useFindTutorStore} from "../../pages/FIndTutor/findTutorStore";
import {useEffect} from "react";
import {useLanguagesStore} from "../../pages/SignUp/store/languagesStore";
import {useLevelsStore} from "../../pages/SignUp/store/levelStore";
import {useLocationStore} from "../../pages/FIndTutor/locationStore";

const fieldWidth = {
    width: '8vw'
}

const gridMarginRight = {
    mr: 3
}

export const TutorsFilter = () => {
    const getTutors = useFindTutorStore(state => state.getTutors);
    const clearTutors = useFindTutorStore(state => state.clearTutors);
    const setMinPrice = useFindTutorStore(state => state.setMinPrice);
    const minPrice = useFindTutorStore(state => state.minPrice);
    const setMaxPrice = useFindTutorStore(state => state.setMaxPrice);
    const maxPrice = useFindTutorStore(state => state.maxPrice);
    const clearLastTutorId = useFindTutorStore(state => state.clearLastTutorId);
    const countryId = useFindTutorStore(state => state.countryId);
    const setCountry = useFindTutorStore(state => state.setCountry);
    const getCities = useFindTutorStore(state => state.getCitiesByCountry);
    const cities = useFindTutorStore(state => state.cities);
    const countries = useFindTutorStore(state => state.countries);
    const setCountryId = useFindTutorStore(state => state.setCountryId);
    const getCountries = useFindTutorStore(state => state.getCountries);
    const loadingCities = useFindTutorStore(state => state.loadingCities);
    const setCity = useFindTutorStore(state => state.setCity);
    const convTypeId = useFindTutorStore(state => state.convTypeId);
    const setConvTypeId = useFindTutorStore(state => state.setConvTypeId);
    const languageId = useFindTutorStore(state => state.languageId);
    const setLanguageId = useFindTutorStore(state => state.setLanguageId);
    const minLevelId = useFindTutorStore(state => state.minLevelId);
    const setMinLevelId = useFindTutorStore(state => state.setMinLevelId);
    const levels = useLevelsStore(state => state.levelsList);
    const getLevels = useLevelsStore(state => state.getLevels);
    const languages = useLanguagesStore(state => state.languagesList);
    const getLanguages = useLanguagesStore(state => state.getLanguages);
    const getCityCoordinates = useLocationStore(state => state.getCityCoordinates);
    const city = useFindTutorStore(state => state.city);

    useEffect(() => {
        getCountries();
        getLevels();
        getLanguages();
    }, [])

    const convTypes: ConvType[] = [
        {convTypeId: 1, description: "Offline"},
        {convTypeId: 2, description: "Online"}
    ]

    return (
        <>
            <Container maxWidth="xl" sx={{position: 'fixed', backgroundColor: 'white', pb: 2}}>
                <Grid container sx={{mt: 10, ml: 10}} alignItems="flex-end">
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField
                            select
                            variant="standard"
                            label="Type"
                            value={convTypeId}
                            onChange={(e) => {
                                setConvTypeId(+e.target.value)
                            }}
                            sx={fieldWidth}
                        >{convTypes.map((conv) => (
                            <MenuItem key={conv.convTypeId} value={conv.convTypeId}>{conv.description}</MenuItem>
                        ))}
                        </TextField>
                    </Grid>
                    <Grid item xs={1.5} sx={gridMarginRight}>
                        <TextField
                            select
                            disabled={convTypeId == 2}
                            variant="standard"
                            label="Country"
                            value={countryId}
                            sx={{width: '12vw'}}
                            onChange={(e) => {
                                setCountry(countries[+e.target.value - 1]);
                                setCountryId(+e.target.value)
                                getCities()
                            }}
                        >{countries.map((country) => (
                            <MenuItem key={country.description}
                                      value={country.countryId}>{country.description}</MenuItem>
                        ))}
                        </TextField>
                    </Grid>
                    <Grid item xs={1.5} sx={gridMarginRight}>
                        <Autocomplete
                            disabled={loadingCities || convTypeId == 2 || countryId == ''}
                            disablePortal
                            id="combo-box-demo"
                            options={cities}
                            onChange={(e, value) => {
                                if (value != null) {
                                    setCity(value as string);
                                }
                            }}
                            sx={{width: '12vw'}}
                            renderInput={(params) =>
                                <TextField {...params}
                                           label="City"
                                           variant='standard'
                                />}
                        />
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField
                            label="Min Price $"
                            variant="standard"
                            value={minPrice}
                            onChange={e => setMinPrice(Number(e.target.value))}
                            sx={fieldWidth}/>
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField
                            label="Max Price $"
                            value={maxPrice}
                            onChange={e => setMaxPrice(Number(e.target.value))}
                            variant="standard"
                            sx={fieldWidth}/>
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField
                            select
                            variant="standard"
                            label="Language"
                            sx={fieldWidth}
                            value={languageId}
                            onChange={(e) => {
                                setLanguageId(+e.target.value)
                            }
                            }
                        >{languages.map((lang) => (
                            <MenuItem key={lang.languageId} value={lang.languageId}>{lang.description}</MenuItem>
                        ))}
                        </TextField>
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField
                            select
                            variant="standard"
                            label="Min Level"
                            sx={fieldWidth}
                            onChange={(e) => setMinLevelId(+e.target.value)}
                            value={minLevelId}
                        >{levels.map((level) => (
                            <MenuItem key={level.levelId} value={level.levelId}>{level.description}</MenuItem>
                        ))}
                        </TextField>
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <Button
                            variant="outlined"
                            onClick={() => {
                                if (countryId != '') {
                                    getCityCoordinates(countries[Number(countryId) - 1].description + "," + city)
                                }
                                clearTutors();
                                clearLastTutorId();
                                getTutors();
                            }
                            }>Apply</Button>
                    </Grid>

                </Grid>
            </Container>
        </>
    )
}