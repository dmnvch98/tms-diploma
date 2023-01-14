import {Container} from "@mui/material";
import {Head} from "./Head";
import {Footer} from "./Footer";

export const Authentication = (props: any) => {
    return (
        <>
            <Container
                maxWidth="xl"
                sx={{backgroundColor: "#F5F5F5", height: "95vh"}}>
                <Head/>
                {props.component}
                <Footer/>
            </Container>
        </>
    )
}