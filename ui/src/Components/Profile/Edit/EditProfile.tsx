import {Container, Grid} from "@mui/material";
import {EditAvatar} from "./EditAvatar";
import {SidebarHeader} from "../../SidebarHeader";
import {EditUserInfo} from "./EditUserInfo";
import {useProfileStore} from "../../../pages/Profile/profileStore";
import {useEffect} from "react";
import {useUpdateUserInfo} from "../../../pages/Profile/Edit/editProfileInfoStore";
import {CreateDeleteTutorProfile} from "../Student/CreateDeleteTutorProfile";
import {CreateDeleteStudentProfile} from "../Tutor/CreateDeleteStudentProfile";

export const EditProfile = ({ children }: any) => {
    const user = useProfileStore(state => state.loggedInUser);
    const getMe = useProfileStore(state => state.getMe)

    const setExistingUser = useUpdateUserInfo(state => state.setExistingUser);

    useEffect(() => {
        if (user == null) {
            getMe();
        }
    }, [])

    useEffect(() => {
        if (user != null) {
            setExistingUser(user);
        }
    }, [user])

    return (
        <>
            <SidebarHeader/>
            <Container sx={{mt: 7}}>
                <Grid container spacing={2}>
                    <Grid item xs={3}>
                        <EditAvatar/>
                        <CreateDeleteTutorProfile/>
                        <CreateDeleteStudentProfile/>
                    </Grid>
                    <Grid item xs={7}>
                        {children}
                        <EditUserInfo/>
                    </Grid>
                </Grid>
            </Container>
        </>
    )
}
