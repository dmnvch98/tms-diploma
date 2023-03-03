import {Container, Grid} from "@mui/material";
import {FindTutorCard} from "../../Components/FindTutor/FindTutorCard";
import {useFindTutorStore} from "./findTutorStore";
import {useEffect} from "react";
export const FindTutor = () => {
    const tutors = useFindTutorStore(state => state.tutors);
    const getTutors = useFindTutorStore(state => state.getTutors);

    useEffect(() => {getTutors()}, [])
    return (
        <>
            <Container maxWidth="xl">
                <Grid container>
                    <Grid item xs={5}>
                        {tutors.map((t, index) => (
                            <FindTutorCard
                                firstName={t.firstName}
                                lastName={t.lastName}
                                languageLevels={t.languageLevels}
                                minPrice={t.minPrice}
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