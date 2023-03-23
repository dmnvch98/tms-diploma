import {
    Alert,
    Box, Button,
    FormControl, Snackbar, TextField
} from "@mui/material";
import {Authentication} from "../../Components/Authentication";
import {useSignInStore} from "./signinStore";
import {useNavigate} from "react-router-dom";
import {useEffect} from "react";
import {useProfileStore} from "../Profile/profileStore";

export const SignIn = () => {
    const email = useSignInStore(state => state.email);
    const password = useSignInStore(state => state.password);
    const setEmail = useSignInStore(state => state.setEmail);
    const setPassword = useSignInStore(state => state.setPassword);
    const navigate = useNavigate();
    const isAuthorized = useSignInStore(state => state.isAuthorized);
    const getToken = useSignInStore(state => state.getToken);
    const snackBarOpen = useSignInStore(state => state.snackbarOpen);
    const setSnackBar = useSignInStore(state => state.setSnackBar);
    const user = useProfileStore(state => state.loggedInUser);

    useEffect(() => {
        if (isAuthorized) {
            user?.roles[0] == 'Student'
                ? navigate("/my-student-profile")
                : navigate("/my-tutor-profile")
        }
    }, [isAuthorized])
    return (
        <>
            <Authentication>
                <Box
                    sx={{m: 2, height: "70vh"}}
                    display="flex"
                    justifyContent="center"
                    alignItems="center">
                    <FormControl
                        sx={{width: '30%', backgroundColor: "white", borderRadius: 3}}>
                        <TextField
                            variant="standard"
                            label="Email"
                            sx={{mb: 2}}
                            value={email}
                            onChange={(e) =>
                                setEmail(e.target.value)
                            }
                        />
                        <TextField
                            variant="standard"
                            label="Password"
                            sx={{mb: 2}}
                            value={password}
                            type='password'
                            onChange={(e) =>
                                setPassword(e.target.value)
                            }
                        />

                        <Button sx={{mt: 4}}
                                color="primary"
                                variant="contained"
                                onClick={getToken}
                        >
                            Continue
                        </Button>
                        <Snackbar
                            open={snackBarOpen}
                            autoHideDuration={3000}
                            onClose={() => setSnackBar(!snackBarOpen)}
                        >
                            <Alert severity="error">
                                User doesn't exist
                            </Alert>
                        </Snackbar>
                    </FormControl>
                </Box>
            </Authentication>
        </>
    )
}