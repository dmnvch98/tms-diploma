import {
    Box, Button,
    FormControl, MenuItem, TextField
} from "@mui/material";
import {Authentication} from "../../Components/Authentication";
import {useSignUpStore} from "./store/store";
import {useEffect} from "react";
import {usePasswords} from "./store/passwordStore";
import {Link as RouterLink} from "react-router-dom";

export const SignUpFirst = () => {
    const types = [
        'Student',
        'Teacher'
    ];

    const Form = () => {
        const roles = useSignUpStore(state => state.roles);
        const email = useSignUpStore((state) => state.email);

        const password = usePasswords(state => state.password);
        const confirmPassword = usePasswords(state => state.confirmPassword);
        const passwordMatches = usePasswords(state => state.matches);

        const setRoles = useSignUpStore(state => state.setRoles);
        const setEmail = useSignUpStore((state) => state.setEmail);

        const setPassword = usePasswords(state => state.setPassword);
        const setConfirmPassword = usePasswords(state => state.setConfirmPassword);
        const setPasswordsMatches = usePasswords(state => state.setMatches)
        const setVerifiedPassword = useSignUpStore(state => state.setPassword);

        useEffect(() => setPasswordsMatches, []);

        return (
            <>
                <Box
                    sx={{m: 2, height: "85vh"}}
                    display="flex"
                    justifyContent="center"
                    alignItems="center">
                    <FormControl
                        sx={{width: '30%', p: 5, backgroundColor: "white", borderRadius: 3}}>
                        <TextField
                            select
                            variant="standard"
                            label="User type"
                            sx={{mb: 2}}
                            value={roles}
                            key="language"
                            onChange={(e) => {
                                setRoles(e.target.value);
                            }}
                        >{types.map((type) => (
                            <MenuItem key={type} value={type}>{type}</MenuItem>
                        ))}
                        </TextField>
                        <TextField
                            key="email"
                            variant="standard"
                            label="Email"
                            sx={{mb: 2}}
                            value={email}
                            type="text"
                            onChange={e => {
                                setEmail(e.target.value)
                            }}
                        />
                        <TextField
                            error={!passwordMatches}
                            key="password"
                            variant="standard"
                            label="Password"
                            sx={{mb: 2}}
                            value={password}
                            type="password"
                            onChange={e => {
                                setPassword(e.target.value)
                                setPasswordsMatches();
                            }}
                        />
                        <TextField
                            error={!passwordMatches}
                            variant="standard"
                            label="Confirm Password"
                            sx={{mb: 2}}
                            type="password"
                            value={confirmPassword}
                            onChange={(e) => {
                                setConfirmPassword(e.target.value)
                                setPasswordsMatches();
                            }}
                        />
                        <Button sx={{mt: 4}}
                                disabled={!passwordMatches
                                    || password.length == 0
                                    || email.length == 0
                                    || roles.length == 0}
                                color="primary"
                                variant="contained"
                                onClick={() => setVerifiedPassword(password)}
                                {...{
                                    to: "/reg2",
                                    component: RouterLink,
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