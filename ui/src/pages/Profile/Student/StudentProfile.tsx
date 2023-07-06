import {useProfileStore} from "../profileStore";
import {useEffect} from "react";
import {Container, Grid} from "@mui/material";
import {SidebarHeader} from "../../../Components/SidebarHeader";
import {useParams} from "react-router-dom";
import {StudentAvatarSection} from "../../../Components/Profile/Student/StudentAvatarSection";
import {StudentInfo} from "../../../Components/Profile/Student/StudentInfo";
import {useAvatarStore} from "../Edit/avatarStore";
import {ErrorMessage} from "../../../Components/Notifications/ErrorMessage";
import {useErrorMessageStore} from "../../../Components/Notifications/errorMessageStore";
import {LanguageLevel} from "../../SignUp/store/languagesStore";
import {useVideoStore} from "../Edit/videoStore";

export const StudentProfile = () => {
    const getUserByStudentId = useProfileStore(state => state.getUserByStudentId)
    const user = useProfileStore(state => state.lookupUser);
    const getAvatar = useAvatarStore(state => state.getAvatar);
    const setIsErrorOpen = useErrorMessageStore(state => state.setIsOpen);
    const setErrorMessage = useErrorMessageStore(state => state.setMessage);
    const isErrorOpen = useErrorMessageStore(state => state.isOpen);
    const getStudentVideoPresentationUrl = useVideoStore(state => state.getStudentVideoPresentationUrl);

    const {id} = useParams();

    useEffect(() => {
        getUserByStudentId(Number(id))
    }, []);

    useEffect(() => {
        if (user != null) {
            getAvatar(user.id).then(r => {
                if (!r) {
                    setIsErrorOpen(!isErrorOpen)
                    setErrorMessage("An error occurred during avatar fetching");
                }
            })

            getStudentVideoPresentationUrl(user.student.studentId);
        }
    }, [user])
    const Profile = () => {
        return (
            <>
                {isErrorOpen && (<ErrorMessage/>)}
                <Container sx={{mt: 7}}>
                    <Grid container spacing={2}>
                        <Grid item xs={3}>
                            <StudentAvatarSection/>
                        </Grid>
                        <Grid item xs={9}>
                            <StudentInfo
                                studentAverageRate={user?.studentAverageRate as number}
                                studentConversationCount={user?.studentConversationCount as number}
                                studentId={user?.student.studentId as number}
                                aboutMe={user?.student.aboutMe as string}
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