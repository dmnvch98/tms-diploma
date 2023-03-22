import {Box, Button, Grid, Paper, Typography} from "@mui/material";
import React from "react";
import {ConversationDetails, useTutorStore} from "../Profile/Tutor/tutorStore";
import {Address} from "../../pages/Addresses/addressesStore";
import {LanguageLevel} from "../../pages/SignUp/store/languagesStore";
import dayjs from "dayjs";
import {useNotificationStore} from "../Notifications/notificationStore";

interface ConversationDetailsShortInfo {
    convDetailsId: number,
    conversationType: string
    price: number,
    address: Address | null,
    minLanguageLevel: LanguageLevel,
    startDate: string
    endDate: string
    displayBookButton: boolean
}

export const ConversationDetailsCard = (props: ConversationDetailsShortInfo) => {
    const date1 = dayjs(props.startDate)
    const date2 = dayjs(props.endDate);
    const duration = date2.diff(date1, 'm');
    const bookConversation = useTutorStore(state => state.bookConversation);

    const isNotificationOpen = useNotificationStore(state => state.isOpen);
    const setMessage = useNotificationStore(state => state.setMessage);
    const setNotificationOpen = useNotificationStore(state => state.setIsOpen);

    const setNotificationConvIsBooked = () => {
        setNotificationOpen(!isNotificationOpen);
        setMessage("Conversation successfully booked!");
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
                        <Typography><b>Type</b></Typography>
                        <Typography><b>Price</b></Typography>
                        <Typography><b>Language</b></Typography>
                        <Typography><b>Min Level</b></Typography>
                        <Typography><b>Start date</b></Typography>
                        <Typography><b>Duration</b></Typography>
                        {props.conversationType != 'Online' && (<Typography>
                            <b>Address</b>
                        </Typography>)}
                    </Grid>
                    <Grid item xs={6}>
                        <Typography>{props.conversationType}</Typography>
                        <Typography>{props.price}</Typography>
                        <Typography>{props.minLanguageLevel.language.description}</Typography>
                        <Typography>{props.minLanguageLevel.level.description}</Typography>
                        <Typography>{props.startDate}</Typography>
                        <Typography>{duration} minutes</Typography>
                        {props.conversationType != 'Online' && (
                            <Typography>{props?.address?.address}</Typography>)}
                    </Grid>
                    <Grid item xs={2} display='flex'
                          alignItems="flex-end">
                        <Button
                            variant='contained'
                            fullWidth
                            sx={{
                                display: props.displayBookButton ? "flex" : "none"
                            }}
                            onClick={() => {
                                bookConversation(props.convDetailsId).then(result => {
                                    if (result) {
                                        setNotificationConvIsBooked();
                                    }
                                })
                            }}
                        >
                            Book
                        </Button>
                    </Grid>

                </Grid>
            </Paper>
        </>
    )
}