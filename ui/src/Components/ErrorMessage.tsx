import {Alert, Snackbar} from "@mui/material";
import {useState} from "react";
import {useEditProfileStore} from "../pages/Profile/editProfileStore";

export const ErrorMessage = (props: any) => {
    const [snackBarOpen, setSnackBar] = useState(true);
    const errorOpen = useEditProfileStore(state => state.errorOpen);
    const setErrorOpen = useEditProfileStore(state => state.setErrorOpen);
    const setErrorMessage = useEditProfileStore(state => state.setErrorMessage);

    return (
        <>
            <Snackbar
                open={snackBarOpen}
                autoHideDuration={3000}
                onClose={() => {
                    setSnackBar(!snackBarOpen)
                    setErrorOpen(!errorOpen)
                    setErrorMessage('')
                }}
            >
                <Alert severity="error">
                    {props.message}
                </Alert>
            </Snackbar>
        </>
    )
}