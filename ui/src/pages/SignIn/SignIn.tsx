import {
    Box, Button,
    FormControl, TextField
} from "@mui/material";
import {Authentication} from "../../Components/Authentication";
import {useSignInStore} from "./signinStore";
import {redirect,useNavigate} from "react-router-dom";
import UserService from "../../services/UserService";

export const SignIn = () => {
    const Form = () => {
        const email = useSignInStore(state => state.email);
        const password = useSignInStore(state => state.password);
        const tokenRetrieved = useSignInStore(state => state.tokenRetrieved);
        const setEmail = useSignInStore(state => state.setEmail);
        const setPassword = useSignInStore(state => state.setPassword);
        // const getToken = useSignInStore(state => state.getToken);
        const navigate = useNavigate();

        const getToken = () => {
            UserService.getToken(email, password).then(() => navigate('/my-profile'));
        }

        return (
            <>
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
                            onChange={(e) =>
                                setPassword(e.target.value)
                            }
                        />

                        <Button sx={{mt: 4}}
                                color="primary"
                                variant="contained"
                                onClick={() => {
                                    getToken();
                                    //return navigate('/my-profile');
                                }}
                        >
                            Continue
                        </Button>
                    </FormControl>
                </Box>
            </>
        )
    }

    return (
        <>
            <Authentication component={<Form/>}></Authentication>
        </>
    )
}