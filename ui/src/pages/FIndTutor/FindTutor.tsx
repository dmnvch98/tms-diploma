import {Container, Grid} from "@mui/material";
import {FindTutorCard} from "../../Components/FindTutor/FindTutorCard";
import {useFindTutorStore} from "./findTutorStore";
import {useEffect} from "react";

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
            <Container maxWidth="xl">
                <Grid container>
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

                    </Grid>
                </Grid>
            </Container>
        </>
    )
}