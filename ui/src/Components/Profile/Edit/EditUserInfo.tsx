import {Box, Button, FormControl, Paper, TextField} from "@mui/material";
import {useProfileStore} from "../../../pages/Profile/profileStore";
import {LanguageLevelSelector} from "../../LanguageLevelSelector/LanguageLevelSelector";
import {useEffect} from "react";
import {useNotificationStore} from "../../Notifications/notificationStore";
import {Notification} from "../../Notifications/Notification";
import {useUpdateUserInfo} from "../../../pages/Profile/Edit/editProfileInfoStore";

export const EditUserInfo = () => {
    const user = useProfileStore(state => state.user);
    const updateUser = useUpdateUserInfo(state => state.updateUser)
    const isNotificationOpen = useNotificationStore(state => state.isOpen);
    const setMessage = useNotificationStore(state => state.setMessage);
    const setNotificationOpen = useNotificationStore(state => state.setIsOpen);

    const setExistingUser = useUpdateUserInfo(state => state.setExistingUser);
    const firstName = useUpdateUserInfo(state => state.firstName);
    const lastName = useUpdateUserInfo(state => state.lastName);
    const email = useUpdateUserInfo(state => state.email);
    const location = useUpdateUserInfo(state => state.location)

    const setFirstName = useUpdateUserInfo(state => state.setFirstName);
    const setLastName = useUpdateUserInfo(state => state.setLastName);
    const setEmail = useUpdateUserInfo(state => state.setEmail);
    const setLocation = useUpdateUserInfo(state => state.setLocation)

    useEffect(() => {
        if (user != null) {
            setExistingUser(user);
        }
        setMessage('Profile successfully updated')
    }, [])

    return (
        <>
            <Box sx={{m: 4}}>
                <Paper>
                    <FormControl sx={{p: 2}}>
                        <Box>
                            {isNotificationOpen && <Notification/>}
                            <TextField
                                variant="standard"
                                label="Email"
                                sx={{mb: 2}}
                                value={email}
                                fullWidth
                                onChange={e => {
                                    setEmail(e.target.value);
                                }}
                            />
                            <TextField
                                variant="standard"
                                label="First Name"
                                sx={{mb: 2}}
                                value={firstName}
                                fullWidth
                                onChange={e => {
                                    setFirstName(e.target.value);
                                }}
                            />
                            <TextField
                                variant="standard"
                                label="Last Name"
                                sx={{mb: 2}}
                                value={lastName}
                                fullWidth
                                onChange={e => {
                                    setLastName(e.target.value);
                                }}
                            />
                            <TextField
                                variant="standard"
                                label="Location"
                                sx={{mb: 2}}
                                value={location}
                                fullWidth
                                onChange={e => {
                                    setLocation(e.target.value);
                                }}
                            />
                        </Box>
                        <LanguageLevelSelector/>
                        <Button
                            variant="contained"
                            sx={{mt: 4}}
                            onClick={() => {
                                updateUser().then(result => {
                                    if (result) {
                                        setNotificationOpen(!isNotificationOpen);
                                    }
                                })
                            }}
                        >Save</Button>
                    </FormControl>
                </Paper>
            </Box>
        </>
    )
}