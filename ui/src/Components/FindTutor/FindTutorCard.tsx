import {Box, Grid, Paper, styled, Typography} from "@mui/material";
import React from "react";
import {TutorCardInfo} from "../../services/ConversationService";
import StarIcon from '@mui/icons-material/Star';
import Link from "@mui/material/Link";

export const FindTutorCard = (props: TutorCardInfo, key: any) => {
    const Img = styled('img')({
        margin: 'auto',
        display: 'block',
        maxWidth: '100%',
        maxHeight: "100%"
    });


    return (
        <Link href={"/tutors/" + props.tutorId} underline='none' target='_blank'>
            <Paper
                key={key}
                sx={{
                    p: 1,
                    margin: 'auto',
                    flexGrow: 1,
                    mb: 1,
                    cursor: 'pointer',
                    backgroundColor: (theme) =>
                        theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
                }}

            >
                <Grid container spacing={2}>
                    <Grid item>
                        <Box sx={{width: 150, height: 150}}>
                            <Img alt="complex"
                                 src={props.avatarUrl}/>
                        </Box>
                    </Grid>
                    <Grid item xs={12} sm container>
                        <Grid item xs container direction="column" spacing={2}>
                            <Grid item xs>
                                <Box display="flex" sx={{mb: 1}}>
                                    <Typography><b>{props.firstName} {props.lastName}</b></Typography>
                                    <StarIcon sx={{ml: 4, color: '#FFBF00'}}/>
                                    <Typography>4.3</Typography>
                                </Box>

                                <Grid container>
                                    <Grid item xs={6}>
                                        <Typography sx={{mb: 1}}>Price</Typography>
                                        <Typography>Languages speaks</Typography>
                                    </Grid>
                                    <Grid item xs={6}>
                                        <Typography sx={{mb: 1}}>from <b>{props.minPrice}$</b></Typography>
                                        {props.languageLevels.map((ll, index) => (
                                            <Typography key={index}>
                                                {ll.language.description}({ll.level.description})
                                            </Typography>
                                        ))}
                                    </Grid>
                                </Grid>
                            </Grid>
                        </Grid>
                    </Grid>
                </Grid>
            </Paper>
        </Link>
    );
}