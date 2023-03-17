import React, {useState} from "react";
import {Box, Container, Rating, Typography} from "@mui/material";
import {LanguageLevelsProfile} from "../Common/LanguageLevelsProfile";
import {TutorProfileTabs} from "./TutorProfileTabs";
import {LanguageLevel} from "../../../pages/SignUp/store/languagesStore";

type Props = {
    aboutMe: string;
    languageLevels: LanguageLevel[];
    tutorId: number;
    currentUser: boolean
}
export const TutorInfo: React.FC<Props> = ({aboutMe, languageLevels, tutorId, currentUser}) => {
const [showMore, setShowMore] = useState(false);

    return (
        <>

            <Container sx={{bgcolor: "white", borderRadius: 2}}>
                <Box>
                    <Box sx={{mt: 2, display: "flex", pt: 2}}>
                        <Typography sx={{mr: 7}}>Lessons: <b>26</b></Typography>
                        <Typography sx={{mr: 1}}><b>Rate:</b></Typography>
                        <Rating name="read-only" value={4.5} readOnly precision={0.5}/>
                    </Box>
                    <LanguageLevelsProfile languageLevels={languageLevels}/>
                    <Box>
                        <Typography sx={{mr: 7, mt: 3}}><b>About me:</b> </Typography>
                        <Typography sx={{mb: 5}}>
                            {showMore
                                ? aboutMe?.toString()
                                : aboutMe?.substring(0, 250)
                            }

                            {aboutMe?.length as number > 250 ?
                                <span
                                    onClick={() => setShowMore(state => !state)}
                                    style={{marginLeft: '15px', color: '#44734b', cursor: 'pointer'}}>
                                    {showMore ? "Show less" : "Show more"}
                                </span>
                                : <></>}
                        </Typography>
                    </Box>
                    <TutorProfileTabs tutorId={tutorId} currentUser={currentUser}/>
                </Box>
            </Container>
        </>
    )
}