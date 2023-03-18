import {useProfileStore} from "../../../pages/Profile/profileStore";
import {Box, Button, Paper, Typography} from "@mui/material";
import {Avatar} from "../Avatar";

export const StudentAvatarSection = () => {
    const user = useProfileStore(state => state.lookupUser);

    return (
        <>
            <Box sx={{mt: 4}}>
                <Paper sx={{p: 2}}>
                    <Avatar/>
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