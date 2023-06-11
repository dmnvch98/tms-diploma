import {Button, CssBaseline, MenuItem, TextField} from "@mui/material";
import {useSignUpStore} from "./store/signUpStore";
import {useEffect} from "react";
import {usePasswords} from "./store/passwordStore";
import {Link as RouterLink} from "react-router-dom";
import {Authentication} from "../../Components/Authentication";

export const SignUpFirst = () => {
    const types = [
        'Student',
        'Tutor'
    ];

    const redirectButtonDisabled: boolean = useSignUpStore(state => state.redirectButtonDisabled);
    const setRedirectButtonDisabled = useSignUpStore(state => state.setRedirectButtonDisabled);
    const roles = useSignUpStore(state => state.roles);
    const email = useSignUpStore(state => state.email);
    const password = usePasswords(state => state.password);
    const confirmPassword = usePasswords(state => state.confirmPassword);
    const passwordMatches = usePasswords(state => state.matches);
    const setRoles = useSignUpStore(state => state.setRoles);
    const setEmail = useSignUpStore(state => state.setEmail);
    const setPassword = usePasswords(state => state.setPassword);
    const setPasswordSignUpStore = useSignUpStore(state => state.setPassword)
    const setConfirmPassword = usePasswords(state => state.setConfirmPassword);
    const setPasswordsMatches = usePasswords(state => state.setMatches)
    const setStudent = useSignUpStore(state => state.setStudent);
    const setTutor = useSignUpStore(state => state.setTutor);

    useEffect(() => setPasswordsMatches, []);

    return (
        <>
            <CssBaseline/>
            <Authentication>
                <TextField
                    fullWidth
                    select
                    variant="standard"
                    label="User type"
                    sx={{mb: 2, display: 'block'}}
                    value={roles}
                    onChange={(e) => {
                        setRoles(e.target.value);
                        e.target.value == 'Student'
                            ? setStudent({})
                            : setTutor({})
                    }}
                >{types.map((type) => (
                    <MenuItem key={type} value={type}>{type}</MenuItem>
                ))}
                </TextField>
                <TextField
                    error={redirectButtonDisabled}
                    fullWidth
                    key="email"
                    variant="standard"
                    label="Email"
                    sx={{mb: 2, display: 'block'}}
                    value={email}
                    type="text"
                    onBlur={() => setRedirectButtonDisabled()}
                    onChange={e => {
                        setEmail(e.target.value)
                    }}
                />
                <TextField
                    error={!passwordMatches}
                    fullWidth
                    key="password"
                    variant="standard"
                    label="Password"
                    sx={{mb: 2, display: 'block'}}
                    value={password}
                    type="password"
                    onChange={e => {
                        setPassword(e.target.value)
                        setPasswordsMatches();
                    }}
                />
                <TextField
                    error={!passwordMatches}
                    fullWidth
                    variant="standard"
                    label="Confirm Password"
                    sx={{mb: 2, display: 'block'}}
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
                            || redirectButtonDisabled
                            || roles.length == 0}
                        color="primary"
                        fullWidth
                        variant="contained"
                        onClick={() => setPasswordSignUpStore(password)}
                        {...{
                            to: "/sign-up2",
                            component: RouterLink,
                        }}
                >
                    Continue
                </Button>
            </Authentication>
        </>
    )

}