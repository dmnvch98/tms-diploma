import {Box, Paper, TextField} from "@mui/material";
import {useUpdateUserInfo} from "../../../pages/Profile/Edit/editProfileInfoStore";
import {EditVideoPresentation} from "../Common/EditVideoPresentation";
import {UserRole} from "../Common/userRolesEnum";

export const TutorEditInfo = () => {

    const aboutMe = useUpdateUserInfo(state => state.tutorAboutMe);
    const setAboutMe = useUpdateUserInfo(state => state.setTutorAboutMe);

    return (
        <>
            <Box sx={{m: 4}}>
                <Paper sx={{p: 2}}>
                            <TextField
                                variant="standard"
                                label="About me"
                                sx={{mb: 2}}
                                value={aboutMe}
                                fullWidth
                                multiline
                                onChange={e => {
                                    setAboutMe(e.target.value)
                                }}
                            />
                    <EditVideoPresentation role={UserRole.Tutor}/>
                </Paper>
            </Box>
        </>
    )
}