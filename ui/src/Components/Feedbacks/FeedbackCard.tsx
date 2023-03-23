import {Box, Button, Grid, Paper, Rating, styled, Typography} from "@mui/material";
import React, {useState} from "react";
import {FeedbackCardData} from "../../services/FeedbackService";
import Link from "@mui/material/Link";

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

    const linkToFeedbackCreator = (): string => {
        return tutorId != null
            ? "/tutors/" + tutorId
            : "/students/" + studentId
    }

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
                        <Link href={linkToFeedbackCreator()}>
                            <Img alt="complex"
                                 src={userAvatarUrl}/>
                        </Link>

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
                                {showMore ? feedback?.toString() :
                                    feedback.length > 200
                                        ? `${feedback?.substring(0, 200).concat('...')}`
                                        : feedback?.toString()
                                }

                                {feedback.length > 200 && (
                                    <Button
                                        sx={{
                                            display: 'inline-block',
                                            padding: 0,
                                            minHeight: 0,
                                            minWidth: 0
                                        }}
                                        onClick={() => setShowMore(state => !state)}>
                                        {showMore ? "Show less" : "Show more"}
                                    </Button>
                                )}
                            </Typography>
                        </Grid>
                    </Grid>
                </Grid>
            </Grid>
        </Paper>
    );
}