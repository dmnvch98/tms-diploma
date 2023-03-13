import {Box, Container, Grid} from "@mui/material";
import {FindTutorCard} from "../../Components/FindTutor/FindTutorCard";
import {useFindTutorStore} from "./findTutorStore";
import React, {useEffect} from "react";
import {SidebarHeader} from "../../Components/SidebarHeader";
import {TutorsFilter} from "../../Components/FindTutor/TutorsFilter";
import GoogleMapReact from "google-map-react";
import Marker from "../../Components/Map/Marker";
import {useLocationStore} from "./locationStore";

export const FindTutor = () => {
    const tutors = useFindTutorStore(state => state.tutors);
    const getTutors = useFindTutorStore(state => state.getTutors);
    const loading = useFindTutorStore(state => state.loadingTutorCards);
    const setLoading = useFindTutorStore(state => state.setLoadingTutorCards);
    const totalCount = useFindTutorStore(state => state.totalCount);
    const latitude = useLocationStore(state => state.latitude);
    const zoom = useLocationStore(state => state.zoom);
    const longitude = useLocationStore(state => state.longitude);

    useEffect(() => {
        document.addEventListener('scroll', scrollHandler);
        return function () {
            document.removeEventListener('scroll', scrollHandler);
        }
    })

    const scrollHandler = (e: any) => {
        if (e.target.documentElement.scrollHeight - (e.target.documentElement.scrollTop + window.innerHeight) < 100
            && tutors.length < totalCount) {
            setLoading(true);
        }
    }

    useEffect(() => {
        if (loading) {
            getTutors()
        }
    }, [loading])

    return (
        <>
            <SidebarHeader/>
            <TutorsFilter/>
            <Container maxWidth="xl" sx={{ml: 10, mt: 18}}>
                <Grid container spacing={1}>
                    <Grid item xs={5}>
                        {tutors.map((t, index) => (
                            <FindTutorCard
                                tutorId={t.tutorId}
                                firstName={t.firstName}
                                lastName={t.lastName}
                                languageLevels={t.languageLevels}
                                minPrice={t.minPrice}
                                avatarUrl={t.avatarUrl}
                                key={index}
                                addresses={[]}
                            />
                        ))}

                    </Grid>
                    <Grid item xs={7}>
                        <Box>
                            <Box style={{height: '100vh', width: '51%', position: 'fixed'}}>
                                <GoogleMapReact
                                    bootstrapURLKeys={{key: 'AIzaSyAJ7QA6FkbHEVQXQlUH0rq2nuS0Khv1HUc'}}
                                    center={{lat: latitude, lng: longitude}}
                                    zoom={zoom}
                                    // onClick={ev => {
                                    //     console.log("latitide = ", ev.lat);
                                    //     console.log("longitude = ", ev.lng);
                                    // }}
                                >
                                    {tutors.map(t => t.addresses.map(a => (
                                        <Marker
                                            lat={a.latitude}
                                            lng={a.longitude}
                                            tutorId={t.tutorId}
                                            firstName={t.firstName}
                                            lastName={t.lastName}
                                            minPrice={t.minPrice}
                                            avatarUrl={t.avatarUrl}
                                        />
                                    )))}
                                </GoogleMapReact>
                            </Box>
                        </Box>
                    </Grid>
                </Grid>
            </Container>
        </>
    )
}