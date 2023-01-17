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
import {useSignUpStore} from "./store";
import {Link as RouterLink} from "react-router-dom";
import {Language, Level, useLanguagesStore} from "./languagesStore";
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
        {levelId: 1, description: "Beginner"},
        {levelId: 2, description: "Elementary"},
        {levelId: 3, description: "Intermediate"},
        {levelId: 4, description: "Upper-Intermediate"},

    ];

    const LanguagesForm = () => {
        const [open, setOpen] = useState(false);
        const handleOpen = () => setOpen(true);
        const handleClose = () => setOpen(false);

        const languageLevels = useSignUpStore(state => state.languageLevels);
        const setLanguageLevels = useSignUpStore(state => state.setLanguageLevels);
        const level = useSignUpStore(state => state.level);
        const levelId = useSignUpStore(state => state.levelId);
        const levelDescription = useSignUpStore(state => state.levelDescription);
        const language = useSignUpStore(state => state.language);
        const languageId = useSignUpStore(state => state.languageId);
        const languageDescription = useSignUpStore(state => state.languageDescription);

        const setLevelId = useSignUpStore(state => state.setLevelId);
        const setLevelDescription = useSignUpStore(state => state.setLevelDescription);
        const setLevel = useSignUpStore(state => state.setLevel);

        const setLanguageId = useSignUpStore(state => state.setLanguageId);
        const setLanguageDescription = useSignUpStore(state => state.setLanguageDescription);
        const setLanguage = useSignUpStore(state => state.setLanguage);

        const getLanguages = useLanguagesStore(state => state.getLanguages);
        const languagesList = useLanguagesStore(state => state.languagesList);

        const roles = useSignUpStore((state) => state.roles);
        const email = useSignUpStore((state) => state.email);
        const password = usePasswords(state => state.password);
        const gender = useSignUpStore(state => state.gender);
        const nationality = useSignUpStore(state => state.nationality);
        const firstName = useSignUpStore(state => state.firstName);
        const lastName = useSignUpStore(state => state.lastName);

        const createUser = useSignUpStore(state => state.createUser);

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
            const level1: Level = {levelId: levelId as number, description: levelDescription};
            const language1: Language = {languageId: languageId as number, description: languageDescription}
            setLanguageLevels([...languageLevels, {level: level1, language: language1}]);
            setLevelId('');
            setLanguageId('')

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
                            value={languageId}
                            onChange={(e) => {
                                setLanguageId(+e.target.value);
                                setLanguageDescription(languagesList[+e.target.value].description);
                            }}>{languagesList.map((language: Language) => (
                            <MenuItem key={language?.description}
                                      value={language?.languageId - 1}>{language?.description}</MenuItem>
                        ))}
                        </TextField>

                        <TextField
                            select
                            variant="standard"
                            label="Level"
                            sx={{mb: 2}}
                            value={levelId}
                            key="level"
                            onChange={(e) => {
                                setLevelId(+e.target.value)
                                setLevelDescription(levels[+e.target.value].description)
                            }}>{levels.map((level: Level) => (
                            <MenuItem key={level.description}
                                      value={+level.levelId - 1}>{level.description}</MenuItem>
                        ))}
                        </TextField>

                        <Button
                            variant="outlined"
                            sx={{mt: 2}}
                            disabled={languageId == 0 || levelId == 0}
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
                            disabled={languageLevels.length == 0}
                            onClick={() => createUser(userDto)}>Continue</Button>
                        <Button sx={{mt: 4}}
                                color="primary"
                                variant="contained"
                                {...{
                                    to: "/reg2",
                                    component: RouterLink,
                                }}
                        >Back</Button>
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
