import {Box, FormControl, Paper, TextField, Typography} from "@mui/material";
import {UpdateUserDto} from "../../../CommonStore/store";
import {useEditProfileStore} from "../../../pages/Profile/Edit/editAvatarStore";
import {useProfileStore} from "../../../pages/Profile/profileStore";
import {useEffect} from "react";
import {useUpdateUserInfo} from "../../../pages/Profile/Edit/editProfileInfoStore";

export const StudentEditInfo = () => {

    const aboutMe = useUpdateUserInfo(state => state.studentAboutMe);
    const setAboutMe = useUpdateUserInfo(state => state.setStudentAboutMe);

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
                </Paper>
            </Box>
        </>
    )
}