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
import {useNotificationStore} from "../../../Components/Notifications/notificationStore";
import {Notification} from "../../../Components/Notifications/Notification";
import {LanguageLevel} from "../../SignUp/store/languagesStore";

export const TutorProfile = () => {
    const getUserByTutorId = useProfileStore(state => state.getUserByTutorId)
    const lookupUser = useProfileStore(state => state.lookupUser);
    const loggedInUser = useProfileStore(state => state.loggedInUser);
    const getAvatar = useAvatarStore(state => state.getAvatar);
    const setIsErrorOpen = useErrorMessageStore(state => state.setIsOpen);
    const setErrorMessage = useErrorMessageStore(state => state.setMessage);
    const isErrorOpen = useErrorMessageStore(state => state.isOpen);
    const isNotificationOpen = useNotificationStore(state => state.isOpen);

    const {id} = useParams();

    useEffect(() => {
        getUserByTutorId(Number(id));
    }, []);

    useEffect(() => {
        if (lookupUser != null) {
            getAvatar(lookupUser.id).then(result => {
                if (!result) {
                    setIsErrorOpen(!isErrorOpen)
                    setErrorMessage("An error occurred during avatar fetching");
                }
            })
        }
    }, [lookupUser])
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
                            <TutorInfo
                                tutorAverageRate={lookupUser?.tutorAverageRate as number}
                                tutorConversationsCount={lookupUser?.tutorConversationCount as number}
                                tutorId={lookupUser?.tutor.tutorId as number}
                                currentUser={false}
                                languageLevels={lookupUser?.languageLevels as LanguageLevel[]}
                                currentUserHasStudentProfile={loggedInUser?.student != null}
                                presentationUrl={lookupUser?.tutor.presentationUrl as string}
                                aboutMe={lookupUser?.tutor?.aboutMe as string}/>
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