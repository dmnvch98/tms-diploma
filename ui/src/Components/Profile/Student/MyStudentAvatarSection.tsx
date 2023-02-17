import {useProfileStore} from "../../../pages/Profile/profileStore";
import {Box, Button, Paper, Typography} from "@mui/material";
import {Link as RouterLink} from "react-router-dom";
import {useEditProfileStore} from "../../../pages/Profile/editProfileStore";
import {useEffect} from "react";

export const MyStudentAvatarSection = () => {
    const user = useProfileStore(state => state.user);
    const getAvatar = useEditProfileStore(state => state.getAvatar);
    const existingAvatarUrl = useEditProfileStore(state => state.existingAvatarUrl);
    useEffect(() => {
        getAvatar()
    }, [])
    return (
        <>
            <Box sx={{mt: 4}}>
                <Paper sx={{p: 2}}>
                    <img style={{
                        maxWidth: "100%",
                        margin: "auto",
                        display: "block",
                        borderRadius: 3
                    }}
                         src={existingAvatarUrl}
                         alt="Avatar"/>
                    <Box sx={{mt: 1, mb: 3}}>
                        <Typography  variant="h6">{user?.firstName} {user?.lastName}</Typography>
                        <Typography  variant="subtitle1">Nationality: {user?.nationality.description}</Typography>
                        <Typography  variant="subtitle1">Location: {user?.location}</Typography>
                    </Box>
                    <Button variant="contained"
                            fullWidth
                            sx={{mt: 2}}
                            {...{
                                to: "/my-tutor-profile",
                                component: RouterLink,
                            }}
                    >
                        Switch to tutor
                    </Button>
                    <Button variant="outlined"
                            fullWidth
                            sx={{mt: 2}}
                            {...{
                                to: "/edit-profile",
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