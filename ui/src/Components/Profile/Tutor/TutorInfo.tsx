import React, {useState} from "react";
import {Box, Button, Container, Rating, Typography} from "@mui/material";
import {LanguageLevelsProfile} from "../Common/LanguageLevelsProfile";
import {TutorProfileTabs} from "./TutorProfileTabs";
import {LanguageLevel} from "../../../pages/SignUp/store/languagesStore";
import {FeedbackStar} from "../../Feedbacks/FeedbackStar";

type Props = {
    aboutMe: string;
    languageLevels: LanguageLevel[];
    tutorId: number;
    currentUser: boolean
    currentUserHasStudentProfile: boolean
    tutorConversationsCount: number
    tutorAverageRate: number
}
export const TutorInfo: React.FC<Props> = ({
                                               aboutMe,
                                               languageLevels,
                                               tutorId,
                                               currentUser,
                                               tutorConversationsCount,
                                               tutorAverageRate,
                                               currentUserHasStudentProfile
                                           }) => {
    const [showMore, setShowMore] = useState(false);

    return (
        <>

            <Container sx={{bgcolor: "white", borderRadius: 2}}>
                <Box>
                    <Box sx={{mt: 2, display: "flex", pt: 2}}>
                        <Typography sx={{mr: 7}}>Lessons: <b>{tutorConversationsCount}</b></Typography>
                        <Typography sx={{mr: 1}}><b>Rate:</b></Typography>
                        <FeedbackStar averageRate={tutorAverageRate}/>
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
                    <TutorProfileTabs
                        tutorId={tutorId}
                        currentUser={currentUser}
                        currentUserHasStudentProfile={currentUserHasStudentProfile}/>
                </Box>
            </Container>
        </>
    )
}