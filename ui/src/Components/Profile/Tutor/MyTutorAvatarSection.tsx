import {useProfileStore} from "../../../pages/Profile/profileStore";
import {Box, Button, Modal, Paper, TextField, Typography} from "@mui/material";
import {Link as RouterLink} from "react-router-dom";
import {Avatar} from "../Avatar";
import {useState} from "react";
import {CreateConversation} from "../../CreateConversation/CreateConversation";

export const MyTutorAvatarSection = () => {
    const user = useProfileStore(state => state.loggedInUser);
    const [open, setOpen] = useState(false);
    const style = {
        position: 'absolute' as 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 500,
        bgcolor: 'background.paper',
        border: '2px solid #000',
        boxShadow: 24,
        p: 4,
    };

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
                            onClick={() => setOpen(!open)}
                            sx={{mt: 2}}>
                        Change availability
                    </Button>
                    <Modal
                        open={open}
                        onClose={() => setOpen(!open)}
                    >
                        <Box sx={style}>
                            <CreateConversation/>
                        </Box>
                    </Modal>
                    <Button variant="contained"
                            fullWidth
                            sx={{mt: 2, display: user?.student != null ? "flex" : "none"}}
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