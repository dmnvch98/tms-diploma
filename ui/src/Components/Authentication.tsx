import {Box, Container} from "@mui/material";
import {Head} from "./Head";
import {Footer} from "./Footer";

export const Authentication = (props: any) => {
    return (
        <>
                <Container
                    disableGutters
                    maxWidth="xl"
                    sx={{minHeight: '100vh'}}>
                    <Head/>
                    {props.component}
                    <Footer/>
                </Container>
        </>
    )
}