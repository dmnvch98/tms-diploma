import {useProfileStore} from "../profileStore";
import {useEffect} from "react";
import {Container, Grid} from "@mui/material";
import {SidebarHeader} from "../../../Components/SidebarHeader";
import {useParams} from "react-router-dom";
import {StudentAvatarSection} from "../../../Components/Profile/Student/StudentAvatarSection";
import {StudentInfo} from "../../../Components/Profile/Student/StudentInfo";
import {useEditProfileStore} from "../Edit/editProfileStore";
import {ErrorMessage} from "../../../Components/Error/ErrorMessage";

export const StudentProfile = () => {
    const getUserByStudentId = useProfileStore(state => state.getUserByStudentId)
    const errorOpen = useEditProfileStore(state => state.errorOpen);
    const {id} = useParams();
    useEffect(() => {
        getUserByStudentId(Number(id))
    }, []);
    const Profile = () => {
        const getUserByStudentId = useProfileStore(state => state.getUserByStudentId)
        const {id} = useParams();
        useEffect(() => {
            getUserByStudentId(Number(id))
        }, []);

        return (
            <>
                {errorOpen && (<ErrorMessage/>)}
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