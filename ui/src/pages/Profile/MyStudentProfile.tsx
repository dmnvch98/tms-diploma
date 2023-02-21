import {SidebarHeader} from "../../Components/SidebarHeader";
import {Container, Grid} from "@mui/material";
import {useProfileStore} from "./profileStore";
import {useEffect} from "react";
import {StudentInfo} from "../../Components/Profile/Student/StudentInfo";
import {MyStudentAvatarSection} from "../../Components/Profile/Student/MyStudentAvatarSection";
import {useEditProfileStore} from "./editProfileStore";
import {ErrorMessage} from "../../Components/Error/ErrorMessage";

export const MyStudentProfile = () => {
    const getMe = useProfileStore(state => state.getMe)
    const errorOpen = useEditProfileStore(state => state.errorOpen);

    useEffect(() => {
        getMe();
    }, [])
    const Profile = () => {
        return (
            <>
                {errorOpen && (<ErrorMessage/>)}
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