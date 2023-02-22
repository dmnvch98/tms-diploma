import {useProfileStore} from "../../../pages/Profile/profileStore";
import {Box, Button, Paper, Typography} from "@mui/material";
import {Link as RouterLink} from "react-router-dom";
import {Avatar} from "../Avatar";

export const MyTutorAvatarSection = () => {
    const user = useProfileStore(state => state.user);

    return (
        <>
            <Box sx={{mt: 4}}>
                <Paper sx={{p: 2}}>
                    <Avatar/>
                    <Box sx={{mt: 1, mb: 3}}>
                        <Typography variant="h6">{user?.firstName} {user?.lastName}</Typography>
                        <Typography variant="subtitle1">Nationality: {user?.nationality?.description}</Typography>
                        <Typography variant="subtitle1">Location: {user?.location}</Typography>
                    </Box>
                    <Button variant="contained"
                            fullWidth
                            sx={{mt: 2}}>
                        Change availability
                    </Button>
                    <Button variant="contained"
                            fullWidth
                            sx={{mt: 2}}
                            {...{
                                to: "/my-student-profile",
                                component: RouterLink,
                            }}
                    >
                        Switch to student
                    </Button>
                    <Button variant="outlined"
                            fullWidth
                            sx={{mt: 2}}
                            {...{
                                to: "/edit-profile-tutor",
                                component: RouterLink,
                            }}
                    >
                        Edit Profile
                    </Button>
                </Paper>
            </Box>
        </>
    )
}