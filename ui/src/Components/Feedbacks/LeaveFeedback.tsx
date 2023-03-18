import {Box, Button, Rating, TextField, Typography} from "@mui/material";
import {useFeedbackStore} from "../Profile/Common/feedbackStore";
import React from "react";
import {useNotificationStore} from "../Notifications/notificationStore";

export const LeaveFeedback = () => {
    const rate = useFeedbackStore(state => state.rate);
    const feedback = useFeedbackStore(state => state.feedback);
    const setRate = useFeedbackStore(state => state.setRate);
    const setFeedback = useFeedbackStore(state => state.setFeedback);
    const saveFeedback = useFeedbackStore(state => state.saveFeedback);

    const isNotificationOpen = useNotificationStore(state => state.isOpen);
    const setMessage = useNotificationStore(state => state.setMessage);
    const setNotificationOpen = useNotificationStore(state => state.setIsOpen);

    const setNotificationFeedbackIsSaved = () => {
        setNotificationOpen(!isNotificationOpen);
        setMessage("Feedback successfully saved");
    }

    const handleSaveFeedback = () => {
        saveFeedback().then(result => {
            if (result) {
                setNotificationFeedbackIsSaved();
            }
        });

    }


    return (
        <>
            <Box>
                <Box display='flex'>
                    <Typography variant='h6' sx={{mr: 4}}>
                        Rating
                    </Typography>
                    <Rating
                        size='large'
                        name="simple-controlled"
                        value={rate}
                        onChange={(event, rate) => {
                            setRate(rate as number);
                        }}
                    />
                </Box>
                <TextField
                    id="outlined-multiline-flexible"
                    label="Your impressions"
                    minRows={5}
                    multiline
                    sx={{mt: 2}}
                    maxRows={10}
                    value={feedback}
                    fullWidth
                    onChange={(e) => {
                        setFeedback(e.target.value)
                    }}
                />
                <Box display='flex' justifyContent='flex-end' sx={{mt: 2}}>
                    <Button
                        variant='contained'
                        onClick={handleSaveFeedback}
                        sx={{width: '35%'}}>
                        Submit
                    </Button>
                </Box>

            </Box>

        </>
    )
}