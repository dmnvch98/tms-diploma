import {SidebarHeader} from "../../Components/SidebarHeader";
import {Container, Grid} from "@mui/material";
import {TutorInfo} from "../../Components/Profile/Tutor/TutorInfo";
import {MyTutorAvatarSection} from "../../Components/Profile/Tutor/MyTutorAvatarSection";
import {useProfileStore} from "./profileStore";
import {useEffect} from "react";
import {useEditProfileStore} from "./editProfileStore";
import {ErrorMessage} from "../../Components/Error/ErrorMessage";

export const MyTutorProfile = () => {
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