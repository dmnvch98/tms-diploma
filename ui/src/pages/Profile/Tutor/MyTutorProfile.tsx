import {SidebarHeader} from "../../../Components/SidebarHeader";
import {Container, Grid} from "@mui/material";
import {TutorInfo} from "../../../Components/Profile/Tutor/TutorInfo";
import {MyTutorAvatarSection} from "../../../Components/Profile/Tutor/MyTutorAvatarSection";
import {useProfileStore} from "../profileStore";
import {useEffect} from "react";
import {useAvatarStore} from "../Edit/avatarStore";
import {ErrorMessage} from "../../../Components/Notifications/ErrorMessage";
import {useErrorMessageStore} from "../../../Components/Notifications/errorMessageStore";
import {useNotificationStore} from "../../../Components/Notifications/notificationStore";
import {Notification} from "../../../Components/Notifications/Notification";
import {LanguageLevel} from "../../SignUp/store/languagesStore";

export const MyTutorProfile = () => {
    const getMe = useProfileStore(state => state.getMe)
    const isErrorOpen = useErrorMessageStore(state => state.isOpen);
    const user = useProfileStore(state => state.loggedInUser);
    const getAvatar = useAvatarStore(state => state.getAvatar);
    const setIsErrorOpen = useErrorMessageStore(state => state.setIsOpen);
    const setErrorMessage = useErrorMessageStore(state => state.setMessage)
    const isNotificationOpen = useNotificationStore(state => state.isOpen);

    useEffect(() => {
        getMe();
    }, [])

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
                            <MyTutorAvatarSection/>
                        </Grid>
                        <Grid item xs={9}>
                            <TutorInfo
                                tutorId={user?.tutor.tutorId as number}
                                currentUser={true}
                                languageLevels={user?.languageLevels as LanguageLevel[]}
                                aboutMe={user?.tutor?.aboutMe as string}/>
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