import {Container, Grid} from "@mui/material";
export const Profile = (props: any) => {
    return (
        <>
            <Container sx={{mt: 7}}>
                <Grid container spacing={2}>
                    <Grid item xs={3}>
                        {props.avatar}
                    </Grid>
                    <Grid item xs={9}>
                        {props.info}
                    </Grid>
                </Grid>
            </Container>
        </>
    )
}