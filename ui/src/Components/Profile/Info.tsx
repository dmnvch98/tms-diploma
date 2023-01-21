import {useEffect, useState} from "react";
import {Box, Container, Rating, Typography} from "@mui/material";
import {UserProfileTabs} from "../UserProfileTabs";

export const Info = (props: any) => {
    const [showMore, setShowMore] = useState(false);
    const [aboutMe, setAboutMe] = useState('');
    const aboutMeLength = props.role
        ? props.user?.student.aboutMe.length
        : props.user?.tutor.aboutMe.length;
    const getAboutMe = () => {
        props.role == "tutor"
            ? setAboutMe(props.user?.student.aboutMe)
            : setAboutMe(props.user?.tutor.aboutMe);
    }

    useEffect(() => getAboutMe, [])
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
                            {showMore
                                ? aboutMe
                                : aboutMe.substring(0, 250) + '...'}
                            {aboutMeLength > 250 ? <span
                                onClick={() => setShowMore(!showMore)}
                                style={{marginLeft: '15px', color: '#44734b', cursor: 'pointer'}}>
                                    {showMore ? "Show less" : "Show more"}
                                </span> : <></>}

                        </Typography>
                    </Box>
                    <UserProfileTabs/>
                </Box>
            </Container>
        </>
    )
}
