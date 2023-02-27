import {SidebarHeader} from "../../../Components/SidebarHeader";
import {Container, Grid} from "@mui/material";
import {useProfileStore} from "../profileStore";
import {useEffect} from "react";
import {StudentInfo} from "../../../Components/Profile/Student/StudentInfo";
import {MyStudentAvatarSection} from "../../../Components/Profile/Student/MyStudentAvatarSection";
import {useEditProfileStore} from "../Edit/editProfileStore";
import {ErrorMessage} from "../../../Components/Notifications/ErrorMessage";
import {useErrorMessageStore} from "../../../Components/Notifications/errorMessageStore";

export const MyStudentProfile = () => {
    const getMe = useProfileStore(state => state.getMe)
    const user = useProfileStore(state => state.user);
    const getAvatar = useEditProfileStore(state => state.getAvatar);
    const setIsErrorOpen = useErrorMessageStore(state => state.setIsOpen);
    const setErrorMessage = useErrorMessageStore(state => state.setMessage)
    const isErrorOpen = useErrorMessageStore(state => state.isOpen);

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
                <Container sx={{mt: 7}}>
                    <Grid container spacing={2}>
                        <Grid item xs={3}>
                            <MyStudentAvatarSection/>
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