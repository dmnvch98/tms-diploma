import {SidebarHeader} from "../../../Components/SidebarHeader";
import {Container, Grid} from "@mui/material";
import {TutorInfo} from "../../../Components/Profile/Tutor/TutorInfo";
import {MyTutorAvatarSection} from "../../../Components/Profile/Tutor/MyTutorAvatarSection";
import {useProfileStore} from "../profileStore";
import {useEffect} from "react";
import {useEditProfileStore} from "../Edit/editProfileStore";
import {ErrorMessage} from "../../../Components/Notifications/ErrorMessage";
import {useNotificationStore} from "../../../Components/Notifications/notificationStore";

export const MyTutorProfile = () => {
    const getMe = useProfileStore(state => state.getMe)
    const isErrorOpen = useNotificationStore(state => state.isOpen);
    const user = useProfileStore(state => state.user);
    const getAvatar = useEditProfileStore(state => state.getAvatar);
    const setIsErrorOpen = useNotificationStore(state => state.setIsOpen);
    const setErrorMessage = useNotificationStore(state => state.setMessage)
    useEffect(() => {
        getMe();
    }, [])

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

    const Profile = () => {
        return (
            <>
                {isErrorOpen && (<ErrorMessage/>)}
                <Container sx={{mt: 7}}>
                    <Grid container spacing={2}>
                        <Grid item xs={3}>
                            <MyTutorAvatarSection/>
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