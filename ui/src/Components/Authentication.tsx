import {Box, Container} from "@mui/material";
import {Head} from "./Head";
import {Footer} from "./Footer";

export const Authentication = ({ children }: any) => {
    return (
        <>
            <Box
                sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    minHeight: '100vh',
                }}
            >
                <Head />
                <Box
                    sx={{
                        flexGrow: 1,
                        display: 'flex',
                        justifyContent: 'center',
                        alignItems: 'center',
                    }}
                >
                    {children}
                </Box>
                <Footer />
            </Box>
        </>
    );
};
