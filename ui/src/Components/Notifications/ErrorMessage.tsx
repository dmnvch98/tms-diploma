import {Alert, Snackbar} from "@mui/material";
import {useState} from "react";
import {useErrorMessageStore} from "./errorMessageStore";

export const ErrorMessage = () => {
    const [snackBarOpen, setSnackBar] = useState(true);
    const isOpen = useErrorMessageStore(state => state.isOpen);
    const setIsOpen = useErrorMessageStore(state => state.setIsOpen);
    const setMessage = useErrorMessageStore(state => state.setMessage);
    const message = useErrorMessageStore(state => state.message);

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
                <Alert severity="error">
                    {message}
                </Alert>
            </Snackbar>
        </>
    )
}