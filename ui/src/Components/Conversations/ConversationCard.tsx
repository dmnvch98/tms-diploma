import {Button, Grid, Paper, Typography} from "@mui/material";
import React from "react";
import {Address} from "../../pages/Addresses/addressesStore";
import {LanguageLevel} from "../../pages/SignUp/store/languagesStore";
import dayjs from "dayjs";
import {ConversationStatus} from "../../pages/Conversations/conversationsStore";

type ConversationProps = {
    conversationType: string
    price: number,
    address: Address
    minLanguageLevel: LanguageLevel,
    startDate: string
    endDate: string
    studentId: number
    tutorId: number
    conversationStatus: ConversationStatus;
    studentLeftFeedback: boolean;
    tutorLeftFeedback: boolean;
    profileType: string;
}

export const ConversationCard: React.FC<ConversationProps> = ({
                                                                  conversationType,
                                                                  price,
                                                                  address,
                                                                  minLanguageLevel,
                                                                  startDate,
                                                                  endDate,
                                                                  studentId,
                                                                  tutorId,
                                                                  conversationStatus,
                                                                  tutorLeftFeedback,
                                                                  studentLeftFeedback,
                                                                  profileType
                                                              }) => {
    const date1 = dayjs(startDate)
    const date2 = dayjs(endDate);
    const duration = date2.diff(date1, 'm');

    const displayFeedbackButton = (): boolean => {
        return profileType == "tutor"
            ? conversationStatus.convStatusId == 5 && !tutorLeftFeedback
            : conversationStatus.convStatusId == 5 && !studentLeftFeedback
    }

    return (
        <>
            <Paper
                sx={{
                    p: 2,
                    margin: 'auto',
                    mt: 2,
                    backgroundColor: (theme) =>
                        theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
                }}

            >
                <Grid item xs={12} sm container>
                    <Grid item xs={4}>
                        <Typography><b>Status</b></Typography>
                        <Typography><b>Student id</b></Typography>
                        <Typography><b>Tutor id</b></Typography>
                        <Typography><b>Type</b></Typography>
                        <Typography><b>Price</b></Typography>
                        <Typography><b>Language</b></Typography>
                        <Typography><b>Min Level</b></Typography>
                        <Typography><b>Start date</b></Typography>
                        <Typography><b>Duration</b></Typography>
                        {conversationType != 'Online' && (<Typography>
                            <b>Address</b>
                        </Typography>)}
                    </Grid>
                    <Grid item xs={6}>
                        <Typography>{conversationStatus.description}</Typography>
                        <Typography>{studentId}</Typography>
                        <Typography>{tutorId}</Typography>
                        <Typography>{conversationType}</Typography>
                        <Typography>{price}</Typography>
                        <Typography>{minLanguageLevel.language.description}</Typography>
                        <Typography>{minLanguageLevel.level.description}</Typography>
                        <Typography>{startDate}</Typography>
                        <Typography>{duration} minutes</Typography>
                        {conversationType != 'Online' && (
                            <Typography>{address?.address}</Typography>)}
                    </Grid>
                    <Grid item xs={2} display='flex'
                          alignItems="flex-end">
                        <Button
                            variant='contained'
                            fullWidth
                            sx={{
                                display: displayFeedbackButton() ? "flex" : "none"
                            }}
                        >
                            Leave a feedback
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        </>
    )
}