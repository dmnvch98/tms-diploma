import React, {useState} from "react";
import {Box, Button, Container, Rating, Typography} from "@mui/material";
import {StudentProfileTabs} from "../../StudentProfileTabs";
import {LanguageLevel} from "../../../pages/SignUp/store/languagesStore";
import {LanguageLevelsProfile} from "../Common/LanguageLevelsProfile";

type Props = {
    aboutMe: string;
    languageLevels: LanguageLevel[];
    studentId: number
}

export const StudentInfo: React.FC<Props> = ({aboutMe, languageLevels, studentId}) => {
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

                            {aboutMe?.length > 250 && (
                                <Button
                                    onClick={() => setShowMore(state => !state)}>
                                    {showMore ? "Show less" : "Show more"}
                                </Button>
                            )}
                        </Typography>
                    </Box>
                    <StudentProfileTabs studentId={studentId}/>
                </Box>
            </Container>
        </>
    )
}