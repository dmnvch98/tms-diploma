import {useProfileStore} from "../profileStore";
import {useEffect} from "react";
import {Container, Grid} from "@mui/material";
import {SidebarHeader} from "../../../Components/SidebarHeader";
import {useParams} from "react-router-dom";
import {TutorAvatarSection} from "../../../Components/Profile/Tutor/TutorAvatarSection";
import {TutorInfo} from "../../../Components/Profile/Tutor/TutorInfo";
import {ErrorMessage} from "../../../Components/Notifications/ErrorMessage";
import {useNotificationStore} from "../../../Components/Notifications/notificationStore";
import {useEditProfileStore} from "../Edit/editProfileStore";

export const TutorProfile = () => {
    const Profile = () => {
        const getUserByTutorId = useProfileStore(state => state.getUserByTutorId)
        const isErrorOpen = useNotificationStore(state => state.isOpen);
        const user = useProfileStore(state => state.user);
        const getAvatar = useEditProfileStore(state => state.getAvatar);
        const setIsErrorOpen = useNotificationStore(state => state.setIsOpen);
        const setErrorMessage = useNotificationStore(state => state.setMessage)

        const { id } = useParams();
        useEffect(() => {
            getUserByTutorId(Number(id));
        }, []);

        useEffect(() => {
            if (user != null) {
                getAvatar(user.id).then(r => {
                    if (!r) {
                        setIsErrorOpen(!isErrorOpen)
                        setErrorMessage("An error occurred during avatar fetching");
                    }
                })
            }
        }, [user])

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