import {SidebarHeader} from "../../Components/SidebarHeader";
import {Container, Grid} from "@mui/material";
import {useProfileStore} from "./profileStore";
import {useEffect} from "react";
import {StudentInfo} from "../../Components/Profile/Student/StudentInfo";
import {NyStudentAvatarSection} from "../../Components/Profile/Student/MyStudentAvatarSection";

export const MyStudentProfile = () => {
    const getMe = useProfileStore(state => state.getMe)
    useEffect(() => {
        getMe();
    }, [])
    const Profile = () => {
        return (
            <>
                <Container sx={{mt: 7}}>
                    <Grid container spacing={2}>
                        <Grid item xs={3}>
                            <NyStudentAvatarSection/>
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