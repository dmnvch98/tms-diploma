import {SidebarHeader} from "../../../Components/SidebarHeader";
import {Container, Grid} from "@mui/material";
import {useProfileStore} from "../profileStore";
import {useEffect} from "react";
import {StudentInfo} from "../../../Components/Profile/Student/StudentInfo";
import {MyStudentAvatarSection} from "../../../Components/Profile/Student/MyStudentAvatarSection";
import {useAvatarStore} from "../Edit/avatarStore";
import {ErrorMessage} from "../../../Components/Notifications/ErrorMessage";
import {useErrorMessageStore} from "../../../Components/Notifications/errorMessageStore";
import {LanguageLevel} from "../../SignUp/store/languagesStore";
import {useVideoStore} from "../Edit/videoStore";

export const MyStudentProfile = () => {
    const getMe = useProfileStore(state => state.getMe)
    const user = useProfileStore(state => state.loggedInUser);
    const getAvatar = useAvatarStore(state => state.getAvatar);
    const setIsErrorOpen = useErrorMessageStore(state => state.setIsOpen);
    const setErrorMessage = useErrorMessageStore(state => state.setMessage)
    const isErrorOpen = useErrorMessageStore(state => state.isOpen);
    const getStudentVideoPresentationUrl = useVideoStore(state => state.getStudentVideoPresentationUrl);
    const setVideoUrl = useVideoStore(state => state.setVideoUrl);
    let videoPresentationUrl: string;

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

            getStudentVideoPresentationUrl(user.student.studentId).then(result => {
                setVideoUrl(result);
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
                            <MyStudentAvatarSection/>
                        </Grid>
                        <Grid item xs={9}>
                            <StudentInfo
                                studentAverageRate={user?.studentAverageRate as number}
                                studentConversationCount={user?.studentConversationCount as number}
                                studentId={user?.student.studentId as number}
                                aboutMe={user?.student.aboutMe as string}
                                presentationUrl={videoPresentationUrl as string}
                                languageLevels={user?.languageLevels as LanguageLevel[]}/>
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