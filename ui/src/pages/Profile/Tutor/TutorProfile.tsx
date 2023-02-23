import {useProfileStore} from "../profileStore";
import {useEffect} from "react";
import {Container, Grid} from "@mui/material";
import {SidebarHeader} from "../../../Components/SidebarHeader";
import {useParams} from "react-router-dom";
import {TutorAvatarSection} from "../../../Components/Profile/Tutor/TutorAvatarSection";
import {TutorInfo} from "../../../Components/Profile/Tutor/TutorInfo";
import {ErrorMessage} from "../../../Components/Notifications/ErrorMessage";
import {useEditProfileStore} from "../Edit/editProfileStore";
import {useNotificationStore} from "../../../Components/Notifications/notificationStore";

export const TutorProfile = () => {
    const Profile = () => {
        const getUserByTutorId = useProfileStore(state => state.getUserByTutorId)
        const isErrorOpen = useNotificationStore(state => state.isOpen);
        const { id } = useParams();
        useEffect(() => {
            getUserByTutorId(Number(id));
        }, []);

        return (
            <>
                {isErrorOpen && (<ErrorMessage/>)}
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