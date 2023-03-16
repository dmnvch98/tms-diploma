import {useProfileStore} from "../profileStore";
import {useEffect} from "react";
import {Container, Grid} from "@mui/material";
import {SidebarHeader} from "../../../Components/SidebarHeader";
import {useParams} from "react-router-dom";
import {TutorAvatarSection} from "../../../Components/Profile/Tutor/TutorAvatarSection";
import {TutorInfo} from "../../../Components/Profile/Tutor/TutorInfo";
import {ErrorMessage} from "../../../Components/Notifications/ErrorMessage";
import {useAvatarStore} from "../Edit/avatarStore";
import {useErrorMessageStore} from "../../../Components/Notifications/errorMessageStore";
import {TutorProfileTabs} from "../../../Components/Profile/Tutor/TutorProfileTabs";
import {useNotificationStore} from "../../../Components/Notifications/notificationStore";
import {Notification} from "../../../Components/Notifications/Notification";

export const TutorProfile = () => {
    const getUserByTutorId = useProfileStore(state => state.getUserByTutorId)
    const user = useProfileStore(state => state.lookupUser);
    const getAvatar = useAvatarStore(state => state.getAvatar);
    const setIsErrorOpen = useErrorMessageStore(state => state.setIsOpen);
    const setErrorMessage = useErrorMessageStore(state => state.setMessage);
    const isErrorOpen = useErrorMessageStore(state => state.isOpen);
    const isNotificationOpen = useNotificationStore(state => state.isOpen);

    const { id } = useParams();

    useEffect(() => {
        getUserByTutorId(Number(id));
    }, []);

    useEffect(() => {
        if (user != null) {
            getAvatar(user.id).then(result => {
                if (!result) {
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
                {isNotificationOpen && (<Notification/>)}
                <Container sx={{mt: 7}}>
                    <Grid container spacing={2}>
                        <Grid item xs={3}>
                            <TutorAvatarSection/>
                        </Grid>
                        <Grid item xs={9}>
                            <TutorInfo>
                                <TutorProfileTabs/>
                            </TutorInfo>
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