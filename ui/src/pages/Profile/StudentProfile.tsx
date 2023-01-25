import {useProfileStore} from "./profileStore";
import {useEffect} from "react";
import {Container, Grid} from "@mui/material";
import {Photo} from "../../Components/Profile/Photo";
import {Info} from "../../Components/Profile/Info";
import {SidebarHeader} from "../../Components/SidebarHeader";
import {useParams} from "react-router-dom";

export const StudentProfile = () => {
    const Profile = () => {
        const getUserByStudentId = useProfileStore(state => state.getUserByStudentId)
        const params = useParams();
        const studentId = params.id;

        useEffect(() => {
            getUserByStudentId(Number(studentId))
        }, []);

        return (
            <>
                <Container sx={{mt: 7}}>
                    <Grid container spacing={2}>
                        <Grid item xs={3}>
                            <Photo role="student"/>
                        </Grid>
                        <Grid item xs={9}>
                            <Info role="student"/>
                        </Grid>
                    </Grid>
                </Container>
            </>
        )
    }

    return (
        <>
            <SidebarHeader component={<Profile/>}></SidebarHeader>
        </>
    )
}