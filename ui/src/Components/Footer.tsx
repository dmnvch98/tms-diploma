import * as React from 'react';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';
import {AppBar,Toolbar} from "@mui/material";

function Copyright() {
    return (
        <Typography variant="body2" color="text.secondary">
            {'Copyright © '}
            <Link color="inherit" href="https://github.com/dmnvch98">
                Dzemyanovich
            </Link>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

export const Footer = () => {
    return (
        <>
            <AppBar position="static" color="primary" sx={{top: 'auto', bottom: 0, py: 2, px: 1, mt: 3}}>
                <Toolbar variant="regular">
                    <Copyright/>
                </Toolbar>
            </AppBar>
        </>
    );
}