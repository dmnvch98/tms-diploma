import {Authentication} from "../../Components/Authentication";
import {LanguageLevelSelector} from "../../Components/LanguageLevelSelector/LanguageLevelSelector";
import {Box, Button, FormControl} from "@mui/material";
import {Link as RouterLink} from "react-router-dom";
import {useSignUpStore} from "./store/signUpStore";
import {LanguageLevelSelectorTwo} from "../../Components/LanguageLevelSelector/LanguageLevelSelectorTwo";

export const SignUpThird = () => {
    const createUser = useSignUpStore(state => state.createUser);
    const languageLevels = useSignUpStore(state => state.languageLevels);

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
                        <LanguageLevelSelectorTwo/>
                        <Button
                            variant="contained"
                            sx={{mt: 4}}
                            disabled={languageLevels.length == 0}
                            onClick={()=> {
                                createUser();
                            }}
                        >Continue</Button>
                        <Button sx={{mt: 4}}
                                color="primary"
                                variant="contained"
                                {...{
                                    to: "/sign-up2",
                                    component: RouterLink,
                                }}
                        >Back</Button>
                    </FormControl>
                </Box>
            </Authentication>
        </>
    )
}
