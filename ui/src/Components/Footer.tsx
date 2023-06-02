import * as React from 'react';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';
import {AppBar, Container, Toolbar} from "@mui/material";

export const Footer = () => {
    return (
        <>
            <AppBar position="static" color="primary">
                <Toolbar variant="regular">
                    <Container maxWidth="xl">
                        <Typography variant="body2" color="text.secondary" align="center">
                            {'Copyright © '}
                            <Link color="inherit" href="https://github.com/dmnvch98">
                                Dzemyanovich
                            </Link>{' '}
                            {new Date().getFullYear()}
                            {'.'}
                        </Typography>
                    </Container>
                </Toolbar>
            </AppBar>
        </>
    );
};
