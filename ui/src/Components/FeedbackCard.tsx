import {Box, Grid, Paper, Rating, styled, Typography} from "@mui/material";
import React, {useState} from "react";
import {FeedbackCardData} from "../services/FeedbackService";

export const FeedbackCard: React.FC<FeedbackCardData> = ({
                                                              feedbackId,
                                                              firstName,
                                                              lastName,
                                                              tutorId,
                                                              studentId,
                                                              rate,
                                                              feedback,
                                                              languageDescription,
                                                              userAvatarUrl
                                                          }) => {
    const Img = styled('img')({
        margin: 'auto',
        display: 'block',
        maxWidth: '100%',
        maxHeight: "100%"
    });

    const [showMore, setShowMore] = useState(false);

    return (
            <Paper
                sx={{
                    p: 2,
                    margin: 'auto',
                    flexGrow: 1,
                    mt: 2,
                    backgroundColor: (theme) =>
                        theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
                }}

            >
                <Grid container spacing={2}>
                    <Grid item>
                        <Box sx={{width: 128, height: 128}}>
                            <Img alt="complex"
                                 src={userAvatarUrl}/>
                        </Box>
                    </Grid>
                    <Grid item xs={12} sm container>
                        <Grid item xs container direction="column" spacing={2}>
                            <Grid item xs>
                                <Typography gutterBottom variant="subtitle1" component="div">
                                    <b>{firstName} {lastName}</b>
                                    <Box display="flex">
                                        <Typography>Language: <b>{languageDescription}</b></Typography>
                                        <Typography sx={{ml: 5}}>Rate:</Typography>
                                        <Rating name="read-only" value={rate} readOnly/>
                                    </Box>
                                </Typography>
                                <Typography variant="body2" color="text.secondary">
                                    {showMore ? feedback?.toString() : `${feedback?.substring(0, 200) + '...'}`}
                                    <span
                                        onClick={() => setShowMore(state => !state)}
                                        style={{marginLeft: '15px', color: '#44734b', cursor: 'pointer'}}>
                                    {showMore ? "Show less" : "Show more"}
                                </span>
                                </Typography>
                            </Grid>
                        </Grid>
                    </Grid>
                </Grid>
            </Paper>
    );
}