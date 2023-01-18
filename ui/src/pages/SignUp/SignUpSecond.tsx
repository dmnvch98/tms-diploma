import {
    Box, Button,
    FormControl, MenuItem, TextField
} from "@mui/material";
import {Authentication} from "../../Components/Authentication";
import {useSignUpStore} from "./store/signUpStore";
import {Link as RouterLink} from "react-router-dom";
import {useCountryStore} from "./store/countryStore";
import {useEffect} from "react";

export const SignUpSecond = () => {

    const genders = [
        'Male',
        'Female',
        'Other'
    ];

    const Form = () => {
        const gender = useSignUpStore(state => state.gender);
        const nationality = useSignUpStore(state => state.nationality);
        const firstName = useSignUpStore(state => state.firstName);
        const lastName = useSignUpStore(state => state.lastName);
        const countryId = useSignUpStore(state => state.countryId);

        const countriesList = useCountryStore(state => state.countriesList);
        const getCountries = useCountryStore(state => state.getCountries);

        const setGender = useSignUpStore(state => state.setGender);
        const setCountryId = useSignUpStore(state => state.setCountryId);
        const setNationality = useSignUpStore(state => state.setNationality);
        const setFirstName = useSignUpStore(state => state.setFirstName);
        const setLastName = useSignUpStore(state => state.setLastName);

        useEffect(() => getCountries, [])

        return (
            <>
                <Box
                    sx={{m: 2, height: "70vh"}}
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
                            value={countryId}
                            onChange={(e) => {
                                setNationality(countriesList[+e.target.value - 1]);
                                setCountryId(+e.target.value)
                            }}
                        >{countriesList.map((country) => (
                            <MenuItem key={country.description}
                                      value={country.countryId}>{country.description}</MenuItem>
                        ))}
                        </TextField>
                        <Button sx={{mt: 4}}
                                disabled={gender.length == 0
                                    || nationality == null
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