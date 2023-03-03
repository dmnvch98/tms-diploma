import {Alert, Snackbar} from "@mui/material";
import {useState} from "react";
import {useNotificationStore} from "./notificationStore";

export const Notification = () => {
    const [snackBarOpen, setSnackBar] = useState(true);
    const isOpen = useNotificationStore(state => state.isOpen);
    const setIsOpen = useNotificationStore(state => state.setIsOpen);
    const setMessage = useNotificationStore(state => state.setMessage);
    const message = useNotificationStore(state => state.message);

    return (
        <>
            <Snackbar
                open={snackBarOpen}
                autoHideDuration={1500}
                onClose={() => {
                    setSnackBar(!snackBarOpen)
                    setIsOpen(!isOpen)
                    setMessage('')
                }}
            >
                <Alert severity="success">
                    {message}
                </Alert>
            </Snackbar>
        </>
    )
}