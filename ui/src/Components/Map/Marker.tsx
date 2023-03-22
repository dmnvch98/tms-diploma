import React, {useState} from 'react';
import RoomRoundedIcon from '@mui/icons-material/RoomRounded';
import {Box, ClickAwayListener, Grid, Paper, styled, SxProps, Typography} from "@mui/material";
import StarIcon from "@mui/icons-material/Star";
import Link from "@mui/material/Link";
import {FeedbackStar} from "../Feedbacks/FeedbackStar";

const markerStyle = {
    position: 'absolute',
    userSelect: 'none',
    transform: 'translate(-50%, -50%)',
    cursor: 'pointer',
    color: 'red',
    fontSize: '48px',
}

const Img = styled('img')({
    margin: 'auto',
    display: 'block',
    maxWidth: '100%',
    maxHeight: "100%"
});
const Marker = (props: any) => {

    const [open, setOpen] = useState(false);

    const styles: SxProps = {
        position: 'absolute',
        top: 28,
        right: 0,
        left: 0,
        zIndex: 1
    };

    const Card = () => {
        return (
            <>
                <Link href={"/tutors/" + props.tutorId} underline='none' target='_blank'>
                    <Grid container>
                        <Paper sx={{p: 1}}>
                            <Grid item xs={2}>
                                <Box sx={{width: 170, height: 170, mb: 1}}>
                                    <Img alt="complex"
                                         src={props.avatarUrl}/>
                                </Box>
                            </Grid>
                            <Grid item xs={10}>
                                <Grid item>
                                    <Box display="flex" sx={{mb: 1}}>
                                        <Typography><b>{props.firstName} {props.lastName}</b></Typography>
                                    </Box>
                                    <Grid container>
                                        <Grid item xs={5}>
                                            <Typography sx={{mb: 1}}>Price</Typography>
                                            <Typography>Rating</Typography>
                                        </Grid>
                                        <Grid item xs={7}>
                                            <Typography sx={{mb: 1}}>from {props.minPrice}$</Typography>

                                            <Box display='flex' justifyContent='flex-end'>
                                                <FeedbackStar averageRate={props.tutorAverageRate}/>
                                            </Box>
                                        </Grid>
                                    </Grid>
                                </Grid>
                            </Grid>
                        </Paper>
                    </Grid>
                </Link>
            </>
        )
    }

    return (
        <>
            <ClickAwayListener onClickAway={() => (setOpen(false))}>
                <Box sx={{position: 'relative'}}>
                    <RoomRoundedIcon sx={markerStyle} onClick={() => {
                        if (props.firstName) {
                            setOpen(!open)
                        }
                    }}/>
                    {open ? (
                        <Box sx={styles}>
                            <Card/>
                        </Box>
                    ) : null}
                </Box>
            </ClickAwayListener>
        </>
    );
};

export default Marker;