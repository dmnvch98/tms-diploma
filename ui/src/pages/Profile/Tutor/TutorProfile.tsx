import {useProfileStore} from "../profileStore";
import {useEffect} from "react";
import {Container, Grid} from "@mui/material";
import {SidebarHeader} from "../../../Components/SidebarHeader";
import {useParams} from "react-router-dom";
import {TutorAvatarSection} from "../../../Components/Profile/Tutor/TutorAvatarSection";
import {TutorInfo} from "../../../Components/Profile/Tutor/TutorInfo";
import {ErrorMessage} from "../../../Components/Error/ErrorMessage";
import {useEditProfileStore} from "../Edit/editProfileStore";

export const TutorProfile = () => {
    const Profile = () => {
        const getUserByTutorId = useProfileStore(state => state.getUserByTutorId)
        const errorOpen = useEditProfileStore(state => state.errorOpen);
        const { id } = useParams();
        useEffect(() => {
            getUserByTutorId(Number(id));
        }, []);

        return (
            <>
                {errorOpen && (<ErrorMessage/>)}
                <Container sx={{mt: 7}}>
                    <Grid container spacing={2}>
                        <Grid item xs={3}>
                            <TutorAvatarSection/>
                        </Grid>
                        <Grid item xs={9}>
                            <TutorInfo/>
                        </Grid>
                    </Grid>
                </Container>
            </>
        )
    }

    return (
        <>
            <SidebarHeader/>
            <Profile/>
        </>
    )
}