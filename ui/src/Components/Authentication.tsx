import {Container} from "@mui/material";
import {Head} from "./Head";
import {Footer} from "./Footer";

export const Authentication = ({ children }: any) => {
    return (
        <>
                <Container
                    disableGutters
                    maxWidth="xl"
                    sx={{minHeight: '100vh'}}>
                    <Head/>
                    {children}
                    <Footer/>
                </Container>
        </>
    )
}