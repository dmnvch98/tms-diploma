import {useProfileStore} from "../../../pages/Profile/profileStore";
import {Box, Button, Paper, Typography} from "@mui/material";

export const StudentAvatarSection = () => {
    const user = useProfileStore(state => state.user);
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
                         src="https://sunmag.me/wp-content/uploads/2020/08/sunmag-2-kachestva-nastoyashchego-muzhchiny.jpg"
                         alt="Avatar"/>
                    <Box sx={{mt: 1, mb: 3}}>
                        <Typography variant="h6">{user?.firstName} {user?.lastName}</Typography>
                        <Typography  variant="subtitle1">Nationality: {user?.nationality.description}</Typography>
                        <Typography  variant="subtitle1">Location: {user?.location}</Typography>
                    </Box>
                    <Button variant="contained"
                            fullWidth
                            sx={{mt: 2}}
                    >
                        Send a message
                    </Button>
                </Paper>
            </Box>
        </>
    )
}