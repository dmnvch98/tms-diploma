import {Authentication} from "../../Components/Authentication";
import {Box, Button, CssBaseline, FormControl} from "@mui/material";
import {Link as RouterLink, useNavigate} from "react-router-dom";
import {useSignUpStore} from "./store/signUpStore";
import {LanguageLevelSelector} from "../../Components/LanguageLevelSelector/LanguageLevelSelector";
import {useEffect} from "react";

export const SignUpThird = () => {
    const createUser = useSignUpStore(state => state.createUser);
    const languageLevels = useSignUpStore(state => state.languageLevels);

    const userCreated = useSignUpStore(state => state.userCreated);
    const getToken = useSignUpStore(state => state.getToken);
    const isAuthorized = useSignUpStore(state => state.isAuthorized);
    const navigate = useNavigate();
    const roles = useSignUpStore(state => state.roles);

    useEffect(() => {
        getToken()
    }, [userCreated])

    useEffect(() => {
        if (isAuthorized) {
            roles[0] == 'Student'
                ? navigate("/my-student-profile")
                : navigate("/my-tutor-profile")
        }
    }, [isAuthorized])

    return (
        <>
            <Authentication>
                <CssBaseline/>
                        <LanguageLevelSelector/>
                        <Button
                            variant="contained"
                            fullWidth
                            sx={{mt: 4}}
                            disabled={languageLevels.length == 0}
                            onClick={() => {
                                createUser();
                            }}
                        >Continue</Button>
                        <Button sx={{mt: 4}}
                                color="primary"
                                variant="contained"
                                fullWidth
                                {...{
                                    to: "/sign-up2",
                                    component: RouterLink,
                                }}
                        >Back</Button>
            </Authentication>
        </>
    )
}
