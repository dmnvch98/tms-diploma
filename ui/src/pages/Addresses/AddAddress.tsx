import {SidebarHeader} from "../../Components/SidebarHeader";
import {Autocomplete, Box, Button, Container, Grid, MenuItem, Paper, TextField, Typography} from "@mui/material";
import React, {useEffect} from "react";
import {useAddAddressStore} from "./addressesStore";
import GoogleMapReact from "google-map-react";
import {useLocationStore} from "../FIndTutor/locationStore";
import Marker from "../../Components/Map/Marker";
import {useNavigate} from "react-router-dom";
import {useNotificationStore} from "../../Components/Notifications/notificationStore";

export const AddAddress = () => {
    const getCountries = useAddAddressStore(state => state.getCountries);
    const latitude = useLocationStore(state => state.latitude);
    const longitude = useLocationStore(state => state.longitude);
    const selectedLatitude = useAddAddressStore(state => state.selectedLatitude);
    const setSelectedLatitude = useAddAddressStore(state => state.setSelectedLatitude);
    const selectedLongitude = useAddAddressStore(state => state.selectedLongitude);
    const setSelectedLongitude = useAddAddressStore(state => state.setSelectedLongitude);
    const zoom = useLocationStore(state => state.zoom);
    const getCityCoordinates = useLocationStore(state => state.getCityCoordinates);
    const getAddressByCoordinates = useAddAddressStore(state => state.getAddressByCoordinates);

    useEffect(() => {
        getCountries();
    }, [])

    const FindCityOnMap = () => {
        const getCities = useAddAddressStore(state => state.getCitiesByCountry);
        const cities = useAddAddressStore(state => state.cities);
        const city = useAddAddressStore(state => state.city);
        const countries = useAddAddressStore(state => state.countries);
        const setCountryId = useAddAddressStore(state => state.setCountryId);
        const setCountry = useAddAddressStore(state => state.setCountry);

        const loadingCities = useAddAddressStore(state => state.loadingCities);
        const setCity = useAddAddressStore(state => state.setCity);
        const countryId = useAddAddressStore(state => state.countryId);

        return (
            <Box maxWidth='500px'>
                <Paper sx={{p: 2}}>
                    <Box display='flex' justifyContent='space-between' alignItems="flex-end">
                        <TextField
                            select
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
                        <Autocomplete
                            disabled={loadingCities || countryId == ''}
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
                        <Button variant='contained' onClick={
                            () => {
                                getCityCoordinates(countries[Number(countryId) - 1].description + "," + city)
                            }
                        }>Apply</Button>
                    </Box>

                </Paper>
            </Box>
        )
    }

    const SelectedAddress = () => {
        const address = useAddAddressStore(state => state.address);
        const saveAddress = useAddAddressStore(state => state.saveTutorAddress);
        const navigate = useNavigate();
        const isNotificationOpen = useNotificationStore(state => state.isOpen);
        const setMessage = useNotificationStore(state => state.setMessage);
        const setNotificationOpen = useNotificationStore(state => state.setIsOpen);

        const setNotificationAddressIsSaved = () => {
            setNotificationOpen(!isNotificationOpen);
            setMessage("Address successfully saved");
        }

        return (
            <>
                <Box maxWidth='500px'>
                    <Paper sx={{p: 2}}>
                        <Typography>
                            Selected address: <b>{address}</b>
                        </Typography>
                        <Box display='flex' sx={{mt: 4}} justifyContent='space-between'>
                            <Button
                                sx={{width:'48%'}}
                                onClick={() => navigate("/my-tutor-profile")}
                                variant='contained'
                                color='error'
                            >
                                Cancel
                            </Button>
                            <Button
                                sx={{width:'48%'}}
                                onClick={() => {
                                    saveAddress().then(result => {
                                        if (result) {
                                            setNotificationAddressIsSaved();
                                            navigate("/my-tutor-profile");
                                        }
                                    })
                                }}
                                variant='contained'
                            >
                                Save address
                            </Button>

                        </Box>
                    </Paper>
                </Box>
            </>
        )
    }

    return (
        <>
            <SidebarHeader/>
            <Container maxWidth="xl">
                <Grid container spacing={1}>
                    <Grid item xs={5}>
                        <Box sx={{mt: 10, ml: 6.5}}>
                            <FindCityOnMap/>
                        </Box>
                        <Box sx={{mt: 10, ml: 6.5}}>
                            <SelectedAddress/>
                        </Box>
                    </Grid>
                    <Grid item xs={7}>
                        <Box style={{height: '90vh'}} sx={{mt: 10}}>
                            <GoogleMapReact
                                bootstrapURLKeys={{key: 'AIzaSyAJ7QA6FkbHEVQXQlUH0rq2nuS0Khv1HUc'}}
                                center={{lat: latitude, lng: longitude}}
                                zoom={zoom}
                                onClick={ev => {
                                    setSelectedLatitude(ev.lat);
                                    setSelectedLongitude(ev.lng);
                                    getAddressByCoordinates();
                                }}
                            >
                                {selectedLatitude != '' && selectedLongitude != '' && (
                                    <Marker
                                        lat={selectedLatitude}
                                        lng={selectedLongitude}
                                    />
                                )}

                            </GoogleMapReact>
                        </Box>
                    </Grid>
                </Grid>

            </Container>
        </>
    )
}