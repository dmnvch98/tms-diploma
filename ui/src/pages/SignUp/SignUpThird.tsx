import {
    Box,
    Button,
    FormControl,
    MenuItem, Modal,
    TextField
} from "@mui/material";
import {Authentication} from "../../Components/Authentication";
import {useEffect, useState} from "react";
import {LanguageLevelTable} from "../../Components/LanguageLevelTable";
import {UserDto, useSignUpStore} from "./store";
import {Link as RouterLink} from "react-router-dom";
import {LanguageLevel, useLanguagesStore} from "./languagesStore";
import UserService from "../../services/UserService";
import {usePasswords} from "./passwordStore";

export const SignUpThird = () => {
    const style = {
        position: 'absolute' as 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 400,
        bgcolor: 'background.paper',
        border: '2px solid #000',
        boxShadow: 24,
        p: 4,
    };

    const levels = [
        'A1',
        'A2',
        'B1'
    ];

    const LanguagesForm = () => {
        const [open, setOpen] = useState(false);
        const handleOpen = () => setOpen(true);
        const handleClose = () => setOpen(false);

        const languageLevels = useSignUpStore(state => state.languageLevels);
        const setLanguageLevels = useSignUpStore(state => state.setLanguageLevels);
        const level = useSignUpStore(state => state.level);
        const language = useSignUpStore(state => state.language);
        const setLevel = useSignUpStore(state => state.setLevel);
        const setLanguage = useSignUpStore(state => state.setLanguage);

        const getLanguages = useLanguagesStore(state => state.getLanguages);
        const languagesList = useLanguagesStore(state => state.languagesList);

        const createUser = useSignUpStore(state => state.createUser);

        const roles = useSignUpStore((state) => state.roles);
        const email = useSignUpStore((state) => state.email);
        const password = usePasswords(state => state.password);
        const gender = useSignUpStore(state => state.gender);
        const nationality = useSignUpStore(state => state.nationality);
        const firstName = useSignUpStore(state => state.firstName);
        const lastName = useSignUpStore(state => state.lastName);

        const userDto = {
            firstName: firstName,
            lastName: lastName,
            email: email,
            password: password,
            nationality: nationality,
            roles: roles,
            gender: gender,
            languageLevels: languageLevels
        }


        const addLanguageLevel = () => {
            const languageLevel: LanguageLevel = {levelId: 3, languageId: language?.id };
            setLanguageLevels([...languageLevels, languageLevel]);
            setLevel('');
            setLanguage(null);
        };

        useEffect(() => {
            getLanguages();
        }, [])

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
                            label="Language"
                            sx={{mb: 2}}
                            value={language?.description}
                            onChange={(e) => {
                                const index: number = +e.target.value - 1;
                                setLanguage(languagesList[index]);
                            }}>{languagesList.map((language) => (
                            <MenuItem key={language.id} value={language.id}>{language.description}</MenuItem>
                        ))}
                        </TextField>

                        <TextField
                            select
                            variant="standard"
                            label="Level"
                            sx={{mb: 2}}
                            value={level}
                            key="level"
                            onChange={(e) => {
                                setLevel(e.target.value);
                            }}>{levels.map((level) => (
                            <MenuItem key={level} value={level}>{level}</MenuItem>
                        ))}
                        </TextField>

                        <Button
                            variant="outlined"
                            sx={{mt: 2}}
                            // disabled={language.length == 0 || level.length == 0}
                            onClick={addLanguageLevel}>Add Language</Button>

                        <Button variant="outlined" sx={{mt: 2}} onClick={handleOpen}>View your languages</Button>
                        <Modal
                            open={open}
                            onClose={handleClose}
                            aria-labelledby="modal-modal-title"
                            aria-describedby="modal-modal-description"
                        >
                            <Box sx={style}>
                                <LanguageLevelTable/>
                            </Box>
                        </Modal>
                        <Button
                            variant="contained"
                            sx={{mt: 4}}
                            // disabled={language.length == 0 || level.length == 0}
                            onClick={() => createUser(userDto)}>Continue</Button>
                        {/*<Button sx={{mt: 4}}*/}
                        {/*        disabled={languageLevels.length == 0}*/}
                        {/*        color="primary"*/}
                        {/*        variant="contained"*/}
                        {/*        onClick={() => createUser()}*/}
                        {/*        {...{*/}
                        {/*            to: "/reg",*/}
                        {/*            component: RouterLink,*/}
                        {/*        }}*/}
                        {/*>*/}
                        {/*    Continue*/}
                        {/*</Button>*/}
                        <Button sx={{mt: 4}}
                                color="primary"
                                variant="contained"
                                {...{
                                    to: "/reg2",
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
            <Authentication component={<LanguagesForm/>}></Authentication>
        </>
    )

}