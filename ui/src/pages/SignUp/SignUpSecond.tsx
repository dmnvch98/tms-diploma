import {
    Button, CssBaseline,
    MenuItem, TextField
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

    // const Form = () => {
        const gender = useSignUpStore(state => state.gender);
        const nationality = useSignUpStore(state => state.nationality);
        const firstName = useSignUpStore(state => state.firstName);
        const lastName = useSignUpStore(state => state.lastName);
        const countryId = useSignUpStore(state => state.countryId);
        const location = useSignUpStore(state => state.location);

        const countriesList = useCountryStore(state => state.countriesList);
        const getCountries = useCountryStore(state => state.getCountries);

        const setGender = useSignUpStore(state => state.setGender);
        const setCountryId = useSignUpStore(state => state.setCountryId);
        const setNationality = useSignUpStore(state => state.setNationality);
        const setFirstName = useSignUpStore(state => state.setFirstName);
        const setLastName = useSignUpStore(state => state.setLastName);
        const setLocation = useSignUpStore(state => state.setLocation);

        useEffect(() => {
            getCountries()
        }, [])

        return (
            <>
                {/*<Box*/}
                {/*    sx={{m: 2, height: "70vh"}}*/}
                {/*    display="flex"*/}
                {/*    justifyContent="center"*/}
                {/*    alignItems="center">*/}
                {/*    <FormControl*/}
                {/*        sx={{width: '30%', backgroundColor: "white"}}>*/}
                <CssBaseline/>
                <Authentication>
                        <TextField
                            fullWidth
                            select
                            variant="standard"
                            label="Gender"
                            sx={{mb: 2, display: 'block'}}
                            value={gender}
                            onChange={(e) => {
                                setGender(e.target.value);
                            }}
                        >{genders.map((gender) => (
                            <MenuItem key={gender} value={gender}>{gender}</MenuItem>
                        ))}
                        </TextField>
                        <TextField
                            fullWidth
                            variant="standard"
                            label="First Name"
                            sx={{mb: 2, display: 'block'}}
                            value={firstName}
                            onChange={(e) => {
                                setFirstName(e.target.value);
                            }}
                        />
                        <TextField
                            fullWidth
                            variant="standard"
                            label="Last Name"
                            sx={{mb: 2}}
                            value={lastName}
                            onChange={(e) => {
                                setLastName(e.target.value);
                            }}
                        />
                        <TextField
                            fullWidth
                            variant="standard"
                            label="Location"
                            sx={{mb: 2, display: 'block'}}
                            value={location}
                            onChange={(e) => {
                                setLocation(e.target.value);
                            }}
                        />
                        <TextField
                            fullWidth
                            select
                            variant="standard"
                            label="Nationality"
                            sx={{mb: 2, display: 'block'}}
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
                                fullWidth
                                {...{
                                    to: "/sign-up3",
                                    component: RouterLink,
                                }}
                        >
                            Continue
                        </Button>
                        <Button sx={{mt: 4}}
                                color="primary"
                                variant="contained"
                                fullWidth
                                {...{
                                    to: "/sign-up",
                                    component: RouterLink,
                                }}
                        >
                            Back
                        </Button>
                {/*    </FormControl>*/}
                {/*</Box>*/}
                </Authentication>
            </>
        )
    // }
}