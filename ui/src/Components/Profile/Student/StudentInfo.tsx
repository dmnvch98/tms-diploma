import React, {useEffect, useState} from "react";
import {Box, Button, Container, Typography} from "@mui/material";
import {StudentProfileTabs} from "./StudentProfileTabs";
import {LanguageLevel} from "../../../pages/SignUp/store/languagesStore";
import {LanguageLevelsProfile} from "../Common/LanguageLevelsProfile";
import {FeedbackStar} from "../../Feedbacks/FeedbackStar";

type Props = {
    aboutMe: string;
    languageLevels: LanguageLevel[];
    studentId: number;
    studentConversationCount: number;
    studentAverageRate: number;
    presentationUrl: string;
}

export const StudentInfo: React.FC<Props> = ({
                                                 aboutMe,
                                                 languageLevels,
                                                 studentId,
                                                 studentConversationCount,
                                                 studentAverageRate,
                                                 presentationUrl
                                             }) => {
    const [showMore, setShowMore] = useState(false);

    useEffect(() => {
        console.log(presentationUrl);
    })
    return (
        <>
            <Container sx={{bgcolor: "white", borderRadius: 2}}>
                <Box>
                    <Box sx={{mt: 2, display: "flex", pt: 2}}>
                        <Typography sx={{mr: 7}}>Lessons: <b>{studentConversationCount}</b></Typography>
                        <Typography sx={{mr: 1}}><b>Rate:</b></Typography>
                        <FeedbackStar averageRate={studentAverageRate}/>
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
                    <StudentProfileTabs studentId={studentId} presentationUrl={presentationUrl}/>
                </Box>
            </Container>
        </>
    )
}