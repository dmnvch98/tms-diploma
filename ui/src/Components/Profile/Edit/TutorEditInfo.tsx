import {Box, FormControl, Paper, TextField, Typography} from "@mui/material";
import {UpdateUserDto} from "../../../CommonStore/store";
import {useEditProfileStore} from "../../../pages/Profile/Edit/editProfileStore";

export const TutorEditInfo = () => {
    const updateUserDto = useEditProfileStore(state => state.updateUserDto);

    const setUserDto = useEditProfileStore(state => state.setUser)

    return (
        <>
            <Box sx={{m: 4}}>
                <Paper sx={{p: 2}}>
                            <TextField
                                variant="standard"
                                label="About me"
                                sx={{mb: 2}}
                                value={updateUserDto?.tutor.aboutMe}
                                fullWidth
                                multiline
                                onChange={e => {
                                    const aboutMe: string = e.target.value;
                                    const tutor = {...updateUserDto?.student, aboutMe};
                                    const userDto = {...updateUserDto, tutor}
                                    setUserDto(userDto as UpdateUserDto);
                                }}
                            />
                </Paper>
            </Box>
        </>
    )
}