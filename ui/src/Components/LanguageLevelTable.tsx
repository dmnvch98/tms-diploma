import {Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import HighlightOffOutlinedIcon from '@mui/icons-material/HighlightOffOutlined';
import {useSignUpStore} from "../pages/SignUp/store/signUpStore";
import {LanguageLevel} from "../pages/SignUp/store/languagesStore";

export const LanguageLevelTable = () => {
    const languageLevels = useSignUpStore((state:any) => state.languageLevels);
    const setLanguageLevels = useSignUpStore((state:any) => state.setLanguageLevels);
    const deleteLangLevel = (langLevel: LanguageLevel) => {
         const newLangLevels = languageLevels.filter((el: LanguageLevel) => el != langLevel);
         setLanguageLevels(newLangLevels);
    }

    return (
        <>
            <TableContainer>
                <Table aria-label="caption table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Language</TableCell>
                            <TableCell>Level</TableCell>
                            <TableCell></TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {languageLevels.map((l: LanguageLevel, index: number) => (
                            <TableRow key={l.language.description}>
                                <TableCell component="th" scope="row" key={l.language.id}>
                                    {l.language.description}
                                </TableCell>
                                <TableCell key={l.level.description}>{l.level.description}</TableCell>
                                <TableCell align="right">
                                    <Button onClick={() => deleteLangLevel(l)} key={index}>
                                        <HighlightOffOutlinedIcon key={index}/>
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </>
    )
}