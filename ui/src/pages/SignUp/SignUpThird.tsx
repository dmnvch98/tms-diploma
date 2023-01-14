import {
    Box,
    Button,
    FormControl,
    MenuItem, Modal,
    TextField
} from "@mui/material";
import {Authentication} from "../../Components/Authentication";
import {useState} from "react";
import {LanguageLevelTable} from "../../Components/LanguageLevelTable";
import {LanguageLevel, useSignUpStore} from "./store";
import {Link as RouterLink} from "react-router-dom";

export const SignUpThird = () => {
    const languageLevels = useSignUpStore((state:any) => state.languageLevels);
    const setLanguageLevels = useSignUpStore((state:any) => state.setLanguageLevels);
    const level = useSignUpStore((state:any) => state.level);
    const language = useSignUpStore((state:any) => state.language);
    const setLevel = useSignUpStore((state:any) => state.setLevel);
    const setLanguage = useSignUpStore((state:any) => state.setLanguage);

    const [open, setOpen] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

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

    const languages = [
        'Belarusian',
        'Chinese',
        'French'
    ];

    const levels = [
        'A1',
        'A2',
        'B1'
    ];

    const addLanguageLevel = () => {
        const languageLevel: LanguageLevel = {level: level, language: language};
        setLanguageLevels([...languageLevels, languageLevel]);
        setLanguage('');
        setLevel('');
    };

    const LanguagesForm = () => {
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
                            value={language}
                            key="language"
                            onChange={(e) => {
                                setLanguage(e.target.value);
                            }}>{languages.map((language) => (
                            <MenuItem key={language} value={language}>{language}</MenuItem>
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
                            disabled={language.length == 0 || level.length == 0}
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
                        <Button sx={{mt: 4}}
                                disabled={languageLevels.length == 0}
                                color="primary"
                                variant="contained"
                                {...{
                                    to: "/reg",
                                    component: RouterLink,
                                }}
                        >
                            Continue
                        </Button>
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