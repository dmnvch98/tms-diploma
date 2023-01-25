import {useProfileStore} from "./profileStore";
import {useEffect} from "react";
import {Container, Grid} from "@mui/material";
import {Photo} from "../../Components/Profile/Photo";
import {Info} from "../../Components/Profile/Info";
import {SidebarHeader} from "../../Components/SidebarHeader";
import {useParams} from "react-router-dom";

export const TutorProfile = () => {
    const Profile = () => {
        const getUserByTutorId = useProfileStore(state => state.getUserByTutorId)
        const tutorId = useParams().id;
        useEffect(() => {
            getUserByTutorId(Number(tutorId));
        }, []);

        return (
            <>
                <Container sx={{mt: 7}}>
                    <Grid container spacing={2}>
                        <Grid item xs={3}>
                            <Photo role="tutor"/>
                        </Grid>
                        <Grid item xs={9}>
                            <Info role="tutor"/>
                        </Grid>
                    </Grid>
                </Container>
            </>
        )
    }

    return (
        <>
            <SidebarHeader component={<Profile/>}></SidebarHeader>
        </>
    )
}