import {Box, Button, Paper, Typography} from "@mui/material";
import {Link as RouterLink} from "react-router-dom";

export const Photo = (props: any) => {
    const switchToTutorBtn = () => {
        return (
            <Button variant="contained" fullWidth
                    {...{
                        to: "/tutor-profile",
                        component: RouterLink,
                    }}>Switch to tutor
            </Button>
        )
    }

    const switchToStudentBtn = () => {
        return (
            <Button variant="contained" fullWidth
                    {...{
                        to: "/student-profile",
                        component: RouterLink,
                    }}>Switch to student
            </Button>
        )
    }
    const editProfile = () => {
        return (
            <>
                <Button
                    fullWidth
                    variant="outlined"
                    sx={{mt: 2}}>
                    Edit Profile
                </Button>
            </>
        )
    }

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
                    <Box sx={{mt: 1, mb: 3}}>
                        <Typography variant="h6">{props.user?.firstName} {props.user?.lastName}</Typography>
                        <Typography variant="subtitle1">Nationality: {props.user?.nationality.description}</Typography>
                        <Typography variant="subtitle1">Location: {props.role == "student"
                            ? props.user?.student.location
                            : props.user?.tutor.location
                        }</Typography>
                    </Box>
                    {props.role == 'student' && props.user?.tutor != null ? switchToTutorBtn() : <></>}
                    {props.role == 'tutor' && props.user?.student != null ? switchToStudentBtn() : <></>}
                    {editProfile()}
                </Paper>
            </Box>
        </>
    )
}