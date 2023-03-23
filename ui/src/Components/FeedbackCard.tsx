import {Box, Grid, Paper, Rating, styled, Typography} from "@mui/material";
import React, {useState} from "react";

export const FeedbackCard = () => {
    const Img = styled('img')({
        margin: 'auto',
        display: 'block',
        maxWidth: '100%',
        maxHeight: "100%"
    });

    const [showMore, setShowMore] = useState(false);

    const feedBackText = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";

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
                             src="https://images.pexels.com/photos/2269872/pexels-photo-2269872.jpeg?auto=compress&cs=tinysrgb&w=640&h=640&dpr=2"/>
                    </Box>
                </Grid>
                <Grid item xs={12} sm container>
                    <Grid item xs container direction="column" spacing={2}>
                        <Grid item xs>
                            <Typography gutterBottom variant="subtitle1" component="div">
                                <b>Jean Barrett</b>
                                <Box display="flex">
                                    <Typography>Language: <b>English</b></Typography>
                                    <Typography sx={{ml: 5}}>Rate:</Typography>
                                    <Rating name="read-only" value={4.5} readOnly precision={0.5}/>
                                </Box>
                            </Typography>
                            <Typography variant="body2" color="text.secondary">
                                {showMore ? feedBackText : `${feedBackText.substring(0, 200) + '...'}`}
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