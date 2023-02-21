import {Box, FormControl, MenuItem, Paper, TextField} from "@mui/material";
import {useProfileStore} from "../../../pages/Profile/profileStore";
import {LanguageLevelSelector} from "../../LanguageLevelSelector/LanguageLevelSelector";
import {Language, useLanguagesStore} from "../../../pages/SignUp/store/languagesStore";
import {useSignUpStore} from "../../../pages/SignUp/store/signUpStore";
import {LanguageLevelSelectorTwo} from "../../LanguageLevelSelector/LanguageLevelSelectorTwo";

export const EditUserInfo = () => {
    const user = useProfileStore(state => state.user);

    return (
        <>
            <Box sx={{m: 4}}>
                <Paper>
                    <FormControl sx={{p: 2}}>
                        <Box>
                            <TextField
                                variant="standard"
                                label="Email"
                                sx={{mb: 2}}
                                value={user?.email}
                                fullWidth
                            />
                            <TextField
                                variant="standard"
                                label="First Name"
                                sx={{mb: 2}}
                                value={user?.firstName}
                                fullWidth
                            />
                            <TextField
                                variant="standard"
                                label="Last Name"
                                sx={{mb: 2}}
                                value={user?.lastName}
                                fullWidth
                            />
                            <TextField
                                variant="standard"
                                label="Location"
                                sx={{mb: 2}}
                                value={user?.location}
                                fullWidth
                            />
                        </Box>
                        <LanguageLevelSelectorTwo/>
                    </FormControl>
                </Paper>
            </Box>
        </>
    )
}