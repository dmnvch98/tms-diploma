import {Alert, Snackbar} from "@mui/material";
import {useState} from "react";
import {useNotificationStore} from "./notificationStore";

export const ErrorMessage = () => {
    const [snackBarOpen, setSnackBar] = useState(true);
    const isOpen = useNotificationStore(state => state.isOpen);
    const setIsOpen = useNotificationStore(state => state.setIsOpen);
    const setMessage = useNotificationStore(state => state.setMessage);
    const message = useNotificationStore(state => state.message);

    return (
        <>
            <Snackbar
                open={snackBarOpen}
                autoHideDuration={3000}
                onClose={() => {
                    setSnackBar(!snackBarOpen)
                    setIsOpen(!isOpen)
                    setMessage('')
                }}
            >
                <Alert severity="error">
                    {message}
                </Alert>
            </Snackbar>
        </>
    )
}