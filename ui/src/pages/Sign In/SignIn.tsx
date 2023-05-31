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
import { CssBaseline } from '@mui/material';

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
            <CssBaseline />
            <Authentication>
                <Box
                    sx={{
                        height: '100%',
                        display: 'flex',
                        justifyContent: 'center',
                        alignItems: 'center',
                        width: '25%',
                        minWidth: '200px'
                    }}
                >
                    <Box
                        sx={{width: "100%", backgroundColor: "white", borderRadius: 3}}>
                        <TextField
                            variant="standard"
                            label="Email"
                            fullWidth
                            sx={{mb: 2, display: 'block'}}
                            value={email}
                            onChange={(e) =>
                                setEmail(e.target.value)
                            }
                        />
                        <TextField
                            variant="standard"
                            label="Password"
                            fullWidth
                            sx={{mb: 2, display: 'block'}}
                            value={password}
                            onChange={(e) =>
                                setPassword(e.target.value)
                            }
                        />

                        <Button sx={{mt: 4, display: 'block'}}
                                color="primary"
                                fullWidth
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
                    </Box>
                </Box>
            </Authentication>
        </>
    )
}