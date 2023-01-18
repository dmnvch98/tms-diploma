import {Authentication} from "../../Components/Authentication";
import {
    Box,
    Button,
    Container,
    Grid, Rating,
    Typography
} from "@mui/material";
import {UserProfileTabs} from "../../Components/UserProfileTabs";

export const CommonProfile = () => {

    const Photo = () => {
        return (
            <>
                <Box sx={{borderRadius: 2, p: 2, mt: 2, bgcolor: "white"}}>
                    <img style={{
                        maxWidth: "100%",
                        margin: "auto",
                        display: "block",
                        borderRadius: 3
                    }}
                         src="https://sunmag.me/wp-content/uploads/2020/08/sunmag-2-kachestva-nastoyashchego-muzhchiny.jpg"
                         alt="Avatar"/>
                    <Typography variant="h6">John Black</Typography>
                    <Button
                        fullWidth
                        variant="contained"
                        sx={{mt: 4}}>
                        Switch to teacher
                    </Button>
                    <Button
                        fullWidth
                        variant="outlined"
                        sx={{mt: 2}}>
                        Edit Info
                    </Button>
                </Box>
            </>
        )
    }

    const Info = () => {
        const text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        return (
            <>
                <Container sx={{bgcolor: "white", borderRadius: 2}}>
                    <Box>
                        <Box sx={{mt: 2, display: "flex", pt: 2}}>
                            <Typography sx={{mr: 7}}>Lessons: <b>26</b></Typography>
                            <Typography sx={{mr: 1}}><b>Rate:</b></Typography>
                            <Rating name="read-only" value={4.5} readOnly precision={0.5}/>
                        </Box>
                        <Box>
                            <Typography sx={{mr: 7, mt: 3}}><b>About me:</b> </Typography>
                            <Typography>{text}</Typography>
                        </Box>
                        <UserProfileTabs/>
                    </Box>
                </Container>
            </>
        )
    }

    const Profile = () => {
        return (
            <>
                <Container>
                    <Grid container spacing={2}>
                        <Grid item xs={3}>
                            <Photo/>
                        </Grid>
                        <Grid item xs={9}>
                            <Info/>
                        </Grid>
                    </Grid>
                </Container>
            </>
        )
    }
    return (
        <>
            <Authentication component={<Profile/>}></Authentication>
        </>
    )
}