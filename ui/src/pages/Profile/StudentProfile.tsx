import {useProfileStore} from "./profileStore";
import {useEffect} from "react";
import {Container, Grid} from "@mui/material";
import {SidebarHeader} from "../../Components/SidebarHeader";
import {useParams} from "react-router-dom";
import {StudentAvatarSection} from "../../Components/Profile/StudentAvatarSection";
import {StudentInfo} from "../../Components/Profile/StudentInfo";

export const StudentProfile = () => {
    const Profile = () => {
        const getUserByStudentId = useProfileStore(state => state.getUserByStudentId)
        const { id } = useParams();
        useEffect(() => {
            getUserByStudentId(Number(id))
        }, []);

        return (
            <>
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