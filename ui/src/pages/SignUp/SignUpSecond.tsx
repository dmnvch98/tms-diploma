import {
    Box, Button,
    FormControl, MenuItem, TextField
} from "@mui/material";
import {Authentication} from "../../Components/Authentication";
import {useSignUpStore} from "./store";
import {Link as RouterLink} from "react-router-dom";

export const SignUpSecond = () => {

    const genders = [
        'Male',
        'Female',
        'Other'
    ];

    const nationalities = [
        'Belarus',
        'Korea',
        'Brazil'
    ];

    const Form = () => {
        const gender = useSignUpStore(state => state.gender);
        const nationality = useSignUpStore(state => state.nationality);
        const firstName = useSignUpStore(state => state.firstName);
        const lastName = useSignUpStore(state => state.lastName);

        const setGender = useSignUpStore(state => state.setGender);
        const setNationality = useSignUpStore(state => state.setNationality);
        const setFirstName = useSignUpStore(state => state.setFirstName);
        const setLastName = useSignUpStore(state => state.setLastName);
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
                            label="Gender"
                            sx={{mb: 2}}
                            value={gender}
                            key="language"
                            onChange={(e) => {
                                setGender(e.target.value);
                            }}
                        >{genders.map((gender) => (
                            <MenuItem key={gender} value={gender}>{gender}</MenuItem>
                        ))}
                        </TextField>
                        <TextField
                            variant="standard"
                            label="First Name"
                            sx={{mb: 2}}
                            value={firstName}
                            onChange={(e) => {
                                setFirstName(e.target.value);
                            }}
                        />
                        <TextField
                            variant="standard"
                            label="Last Name"
                            sx={{mb: 2}}
                            value={lastName}
                            onChange={(e) => {
                                setLastName(e.target.value);
                            }}
                        />
                        <TextField
                            select
                            variant="standard"
                            label="Nationality"
                            sx={{mb: 2}}
                            value={nationality}
                            key="language"
                            onChange={(e) => {
                                setNationality(e.target.value);
                            }}
                        >{nationalities.map((nationality) => (
                            <MenuItem key={nationality} value={nationality}>{nationality}</MenuItem>
                        ))}
                        </TextField>
                        <Button sx={{mt: 4}}
                                disabled={gender.length == 0
                                    || nationality.length == 0
                                    || firstName.length == 0
                                    || lastName.length == 0}
                                color="primary"
                                variant="contained"
                                {...{
                                    to: "/reg3",
                                    component: RouterLink,
                                }}
                        >
                            Continue
                        </Button>
                        <Button sx={{mt: 4}}
                                color="primary"
                                variant="contained"
                                {...{
                                    to: "/reg",
                                    component: RouterLink,
                                }}
                        >
                            Back
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