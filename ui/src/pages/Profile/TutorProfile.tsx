import {useProfileStore} from "./profileStore";
import {useEffect} from "react";
import {Container, Grid} from "@mui/material";
import {Photo} from "../../Components/Profile/Photo";
import {Info} from "../../Components/Profile/Info";
import {SidebarHeader} from "../../Components/SidebarHeader";

export const TutorProfile = () => {
    const getUser = useProfileStore(state => state.getUser);
    const user = useProfileStore(state => state.user);

    useEffect(() => {
        getUser()
    }, []);

    const Profile = () => {
        return (
            <>
                <Container sx={{mt: 7}}>
                    <Grid container spacing={2}>
                        <Grid item xs={3}>
                            <Photo role="tutor" user={user}/>
                        </Grid>
                        <Grid item xs={9}>
                            <Info role="tutor" user={user}/>
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