import {Box, Container, Grid} from "@mui/material";
import {FindTutorCard} from "../../Components/FindTutor/FindTutorCard";
import {useFindTutorStore} from "./findTutorStore";
import {useEffect} from "react";
import {SidebarHeader} from "../../Components/SidebarHeader";
import {TutorsFilter} from "../../Components/FindTutor/TutorsFilter";

export const FindTutor = () => {
    const tutors = useFindTutorStore(state => state.tutors);
    const getTutors = useFindTutorStore(state => state.getTutors);
    const loading = useFindTutorStore(state => state.loading);
    const setLoading = useFindTutorStore(state => state.setLoading);
    const totalCount = useFindTutorStore(state => state.totalCount);

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
                            />
                        ))}

                    </Grid>
                    <Grid item xs={7}>
                        <Box sx={{position: 'fixed', height: '100vh'}}>
                            <iframe
                                src="https://yandex.com/map-widget/v1/?ll=27.569857%2C53.900789&masstransit%5BstopId%5D=station__9880204&mode=masstransit&tab=overview&z=15.28"
                                width='250%' height="100%"
                                ></iframe>
                        </Box>
                    </Grid>
                </Grid>
            </Container>
        </>
    )
}