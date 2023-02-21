
import {Container, Grid} from "@mui/material";
import {EditAvatar} from "../../Components/Profile/Edit/EditAvatar";
import {SidebarHeader} from "../../Components/SidebarHeader";
import {EditUserInfo} from "../../Components/Profile/Edit/EditUserInfo";

export const EditProfile = () => {
    return (
        <>
            <SidebarHeader/>
            <Container sx={{mt: 7}}>
                <Grid container spacing={2}>
                    <Grid item xs={3}>
                        <EditAvatar/>
                    </Grid>
                    <Grid item xs={7}>
                        <EditUserInfo/>
                    </Grid>
                </Grid>
            </Container>
        </>
    )
}
