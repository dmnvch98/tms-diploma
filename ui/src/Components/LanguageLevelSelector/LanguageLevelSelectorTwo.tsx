import {Language, useLanguagesStore} from "../../pages/SignUp/store/languagesStore";
import {Box, Button, MenuItem, Modal, TextField} from "@mui/material";
import {useEffect, useState} from "react";
import {useSignUpStore} from "../../pages/SignUp/store/signUpStore";
import {Level, useLevelsStore} from "../../pages/SignUp/store/levelStore";
import {LanguageLevelTable} from "../LanguageLevelTable";

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
export const LanguageLevelSelectorTwo = () => {
    const languagesList = useLanguagesStore(state => state.languagesList);
    const languageLevels = useSignUpStore(state => state.languageLevels);
    const level = useSignUpStore(state => state.level);
    const levelId = useSignUpStore(state => state.levelId);
    const language = useSignUpStore(state => state.language);
    const languageId = useSignUpStore(state => state.languageId);
    const levelsList = useLevelsStore(state => state.levelsList);
    const createUser = useSignUpStore(state => state.createUser);

    const setLanguageLevels = useSignUpStore(state => state.setLanguageLevels);
    const setLanguageId = useSignUpStore(state => state.setLanguageId);
    const setLanguage = useSignUpStore(state => state.setLanguage);
    const setLevelId = useSignUpStore(state => state.setLevelId);
    const setLevel = useSignUpStore(state => state.setLevel);
    const getLevels = useLevelsStore(state => state.getLevels);
    const getLanguages = useLanguagesStore(state => state.getLanguages);
    const isAuthorized = useSignUpStore(state => state.isAuthorized);
    const userCreated = useSignUpStore(state => state.userCreated);

    const [open, setOpen] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    useEffect(() => {
        getLanguages();
        getLevels();
    }, [])

    const addLanguageLevel = () => {
        setLanguageLevels([...languageLevels, {level: level, language: language}]);
        setLevelId('');
        setLanguageId('')
    };

    return (
        <>
            <TextField
                select
                variant="standard"
                label="Language"
                value={languageId}
                onChange={(e) => {
                    setLanguageId(+e.target.value);
                    setLanguage(languagesList[+e.target.value - 1]);
                }}
                sx={{mb: 2}}
            >{languagesList.map((language: Language) => (
                <MenuItem key={language?.description}
                          value={language?.languageId}>{language?.description}</MenuItem>
            ))}
            </TextField>

            <TextField
                select
                variant="standard"
                label="Level"
                sx={{mb: 2}}
                value={levelId}
                onChange={(e) => {
                    setLevelId(+e.target.value)
                    setLevel(levelsList[+e.target.value - 1])
                }}>{levelsList.map((level: Level) => (
                <MenuItem key={level.description}
                          value={+level.levelId}>{level.description}</MenuItem>
            ))}
            </TextField>

            <Button
                variant="outlined"
                sx={{mt: 2}}
                disabled={languageId == '' || levelId == ''}
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
        </>
    )
}