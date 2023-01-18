import {Container} from "@mui/material";
import {Head} from "./Head";
import {Footer} from "./Footer";

export const Authentication = (props: any) => {
    return (
        <>
            <Container
                disableGutters
                maxWidth="xl"
                sx={{backgroundColor: "#F5F5F5"}}>
                <Head/>
                {props.component}
                <Footer/>
            </Container>
        </>
    )
}