import {
    Box,
    Button,
    Container,
    Grid, IconButton, Paper, Rating,
    Typography
} from "@mui/material";
import {UserProfileTabs} from "../../Components/UserProfileTabs";
import {useSignUpStore} from "../SignUp/store/signUpStore";
import {Sidebar} from "../../Components/Sidebar";
import {useState} from "react";

export const CommonProfile = () => {

    const Photo = () => {
        const firstName = useSignUpStore(state => state.firstName);
        const lastName = useSignUpStore(state => state.lastName);
        return (
            <>
                <Box sx={{mt: 4}}>
                    <Paper sx={{p: 2}}>
                        <img style={{
                            maxWidth: "100%",
                            margin: "auto",
                            display: "block",
                            borderRadius: 3
                        }}
                             src="https://sunmag.me/wp-content/uploads/2020/08/sunmag-2-kachestva-nastoyashchego-muzhchiny.jpg"
                             alt="Avatar"/>
                        <Typography variant="h6">{firstName} {lastName}</Typography>
                        <Button
                            fullWidth
                            variant="contained"
                            sx={{mt: 4}}>
                            Switch to tutor
                        </Button>
                        <Button
                            fullWidth
                            variant="contained"
                            sx={{mt: 4}}>
                            Switch to student
                        </Button>
                        <Button
                            fullWidth
                            variant="contained"
                            sx={{mt: 4}}>
                            Book a conversation
                        </Button>
                        <Button
                            fullWidth
                            variant="outlined"
                            sx={{mt: 2}}>
                            Edit Profile
                        </Button>
                    </Paper>
                </Box>
            </>
        )
    }

    const Info = () => {
        const [showMore, setShowMore] = useState(false);
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
                            <Typography sx={{mb: 5}}>
                                {showMore ? text : `${text.substring(0, 250) + '...'}`}
                                <span
                                    onClick={() => setShowMore(!showMore)}
                                    style={{marginLeft: '15px', color: '#44734b', cursor: 'pointer'}}>
                                    {showMore ? "Show less" : "Show more"}
                                </span>
                            </Typography>
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
                <Container sx={{mt: 7}}>
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
            <Sidebar component={<Profile/>}></Sidebar>
        </>
    )
}