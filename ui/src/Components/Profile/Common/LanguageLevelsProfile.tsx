import {Box, Grid, Typography} from "@mui/material";
import {useProfileStore} from "../../../pages/Profile/profileStore";

export const LanguageLevelsProfile = () => {
    const user = useProfileStore(state => state.user);
    return (
        <>
            <Box sx={{mt: 3}}>
                <Grid container>
                    <Grid item sx={{mr: 8}}>
                        <Typography><b>Language</b></Typography>
                        {user?.languageLevels.map((ll) =>
                            <Typography key={ll.language.description}>{ll.language.description}</Typography>
                        )}
                    </Grid>
                    <Grid item>
                        <Typography><b>Level</b></Typography>
                        {user?.languageLevels.map((ll, index) =>
                            <Typography key={index}>{ll.level.description}</Typography>
                        )}
                    </Grid>
                </Grid>
            </Box>
        </>
    )
}