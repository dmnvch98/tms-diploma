import {Box, Button, FormControl, Paper, TextField} from "@mui/material";
import {useProfileStore} from "../../../pages/Profile/profileStore";
import {LanguageLevelSelectorTwo} from "../../LanguageLevelSelector/LanguageLevelSelectorTwo";
import {useSignUpStore} from "../../../pages/SignUp/store/signUpStore";
import {useEffect} from "react";
import {UpdateUserDto, User} from "../../../CommonStore/store";
import {useEditProfileStore} from "../../../pages/Profile/Edit/editProfileStore";

export const EditUserInfo = () => {
    const updateUserDto = useEditProfileStore(state => state.updateUserDto);
    const user = useProfileStore(state => state.user);
    const setUserDto = useEditProfileStore(state => state.setUser)
    const updateUser = useEditProfileStore(state => state.updateUser)

    useEffect(() => {
        if (user!= null) {
            const nationality: number = user.nationality.countryId;
            let userDto: UpdateUserDto = {...user, nationality};
            setUserDto(userDto);
        }
    }, [])

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
                                value={updateUserDto?.email}
                                fullWidth
                                onChange={e => {
                                    const email: string = e.target.value;
                                    const userDto = {...updateUserDto, email}
                                    setUserDto(userDto as UpdateUserDto);
                                }}
                            />
                            <TextField
                                variant="standard"
                                label="First Name"
                                sx={{mb: 2}}
                                value={updateUserDto?.firstName}
                                fullWidth
                                onChange={e => {
                                    const firstName: string = e.target.value;
                                    const userDto = {...updateUserDto, firstName}
                                    setUserDto(userDto as UpdateUserDto);
                                }}
                            />
                            <TextField
                                variant="standard"
                                label="Last Name"
                                sx={{mb: 2}}
                                value={updateUserDto?.lastName}
                                fullWidth
                                onChange={e => {
                                    const lastName: string = e.target.value;
                                    const userDto = {...updateUserDto, lastName}
                                    setUserDto(userDto as UpdateUserDto);
                                }}
                            />
                            <TextField
                                variant="standard"
                                label="Location"
                                sx={{mb: 2}}
                                value={updateUserDto?.location}
                                fullWidth
                                onChange={e => {
                                    const location: string = e.target.value;
                                    const userDto = {...updateUserDto, location}
                                    setUserDto(userDto as UpdateUserDto);
                                }}
                            />
                        </Box>
                        <LanguageLevelSelectorTwo/>
                        <Button
                            variant="contained"
                            sx={{mt: 4}}
                            onClick={() => {
                               updateUser();
                            }}
                        >Save</Button>
                    </FormControl>
                </Paper>
            </Box>
        </>
    )
}