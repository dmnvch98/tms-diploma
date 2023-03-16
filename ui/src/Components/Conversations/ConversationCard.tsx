import {Box, Button, Grid, Paper, Typography} from "@mui/material";
import React from "react";
import {ConversationDetails} from "../Profile/Tutor/tutorStore";
import {Address} from "../../pages/Addresses/addressesStore";
import {LanguageLevel} from "../../pages/SignUp/store/languagesStore";
import dayjs from "dayjs";

interface ConversationDetailsShortInfo {
    conversationType: string
    price: number,
    address: Address | null,
    minLanguageLevel: LanguageLevel,
    startDate: string
    endDate: string
}

export const ConversationCard = (props: ConversationDetailsShortInfo) => {
    const date1 = dayjs(props.startDate)
    const date2 = dayjs(props.endDate);
    const duration = date2.diff(date1, 'm', );
    return (
        <>
            <Paper
                sx={{
                    p: 2,
                    margin: 'auto',
                    mb: 1,
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
                            <Button variant='contained' fullWidth>
                                Book
                            </Button>
                    </Grid>

                </Grid>
            </Paper>
        </>
    )
}