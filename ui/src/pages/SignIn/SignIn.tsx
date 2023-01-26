import {
    Box, Button,
    FormControl, TextField
} from "@mui/material";
import {Authentication} from "../../Components/Authentication";
import {useState} from "react";
import {useSignInStore} from "./signinStore";

export const SignIn = () => {
    const Form = () => {
        // const [email, setEmail] = useState('');
        // const [password, setPassword] = useState('');

        const email = useSignInStore(state => state.email);
        const password = useSignInStore(state => state.password);

        const setEmail = useSignInStore(state => state.setEmail);
        const setPassword = useSignInStore(state => state.setPassword);
        const getToken = useSignInStore(state => state.getToken);
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
                                onClick={() => getToken()}
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