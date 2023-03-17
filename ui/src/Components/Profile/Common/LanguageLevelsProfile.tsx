import {Box, Grid, Typography} from "@mui/material";
import {LanguageLevel} from "../../../pages/SignUp/store/languagesStore";
import React from "react";

type Props = {
    languageLevels: LanguageLevel[]
}

export const LanguageLevelsProfile: React.FC<Props> = ({languageLevels}) => {

    return (
        <>
            <Box sx={{mt: 3}}>
                <Grid container>
                    <Grid item sx={{mr: 8}}>
                        <Typography><b>Language</b></Typography>
                        {languageLevels?.map((ll: LanguageLevel) =>
                            <Typography key={ll.language.description}>{ll.language.description}</Typography>
                        )}
                    </Grid>
                    <Grid item>
                        <Typography><b>Level</b></Typography>
                        {languageLevels?.map((ll : LanguageLevel, index: number) =>
                            <Typography key={index}>{ll.level.description}</Typography>
                        )}
                    </Grid>
                </Grid>
            </Box>
        </>
    )
}