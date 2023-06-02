import {Box} from "@mui/material";
import {Head} from "./Head";
import {Footer} from "./Footer";

export const Authentication = ({ children }: any) => {
    return (
        <Box
            sx={{
                display: 'flex',
                flexDirection: 'column',
                minHeight: '100vh',
            }}
        >
            <Head />
            {/*Align container vertically*/}
            <Box
                sx={{
                    flexGrow: 1,
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                }}
            >
                {/* Container for the authentication content */}
                <Box
                    sx={{
                        width: '25%',
                        minWidth: '200px',
                        backgroundColor: "white",
                        borderRadius: 3
                    }}
                >
                    {children}
                </Box>
            </Box>
            <Footer />
        </Box>
    );
};








