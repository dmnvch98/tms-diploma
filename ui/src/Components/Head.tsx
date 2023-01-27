import {AppBar, Box, Container, Grid, Toolbar, Typography} from "@mui/material";
import {RedirectButton} from "./RedirectButton";

export const Head = () => {
    const headersData = [
        {
            label: "Sign in",
            href: "/sign-in",
        },
        {
            label: "Sign up",
            href: "/sign-up",
        }
    ];
    const getMenuButtons = () => {
        return headersData.map(({label, href}) => {
            return (
                <RedirectButton disabled={false} key={label} color="inherit" variant="text" label={label} href={href}
                                ml={4} mt={0}/>
            );
        });
    };

    return (
        <>
            <AppBar position="static">
                <Toolbar variant="regular">
                    <Typography variant="h5" component="h1">Logo</Typography>
                    <Grid container>
                        <Grid item sm={12}>
                            <Box display="flex" justifyContent="flex-end">
                                {getMenuButtons()}
                            </Box>
                        </Grid>
                    </Grid>
                </Toolbar>
            </AppBar>
        </>
    )
}