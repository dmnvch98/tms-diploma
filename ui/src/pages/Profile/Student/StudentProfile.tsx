import {useProfileStore} from "../profileStore";
import {useEffect} from "react";
import {Container, Grid} from "@mui/material";
import {SidebarHeader} from "../../../Components/SidebarHeader";
import {useParams} from "react-router-dom";
import {StudentAvatarSection} from "../../../Components/Profile/Student/StudentAvatarSection";
import {StudentInfo} from "../../../Components/Profile/Student/StudentInfo";
import {useEditProfileStore} from "../Edit/editProfileStore";
import {ErrorMessage} from "../../../Components/Notifications/ErrorMessage";
import {useNotificationStore} from "../../../Components/Notifications/notificationStore";

export const StudentProfile = () => {
    const getUserByStudentId = useProfileStore(state => state.getUserByStudentId)
    const isErrorOpen = useNotificationStore(state => state.isOpen);
    const user = useProfileStore(state => state.user);
    const getAvatar = useEditProfileStore(state => state.getAvatar);
    const setIsErrorOpen = useNotificationStore(state => state.setIsOpen);
    const setErrorMessage = useNotificationStore(state => state.setMessage)

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
                            <StudentInfo/>
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