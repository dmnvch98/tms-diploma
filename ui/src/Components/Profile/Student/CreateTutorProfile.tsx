import {Box, Button} from "@mui/material";
import {useProfileStore} from "../../../pages/Profile/profileStore";
import {useUpdateUserInfo} from "../../../pages/Profile/Edit/editProfileInfoStore";
import {useNotificationStore} from "../../Notifications/notificationStore";
import {useErrorMessageStore} from "../../Notifications/errorMessageStore";
import {Link as RouterLink, useNavigate} from "react-router-dom";
import {useEffect} from "react";

export const CreateTutorProfile = () => {
    const user = useProfileStore(state => state.user);
    const addTutorToStore = useUpdateUserInfo(state => state.addTutorToStore);
    const deleteTutorFromStore = useUpdateUserInfo(state => state.deleteTutorFromStore);
    const updateUser = useUpdateUserInfo(state => state.updateUser);

    const setNotificationMessage = useNotificationStore(state => state.setMessage)
    const setErrorMessage = useErrorMessageStore(state => state.setMessage)

    const isNotificationOpen = useNotificationStore(state => state.isOpen);
    const isErrorOpen = useErrorMessageStore(state => state.isOpen);

    const setIsNotificationOpen = useNotificationStore(state => state.setIsOpen);
    const setIsErrorOpen = useErrorMessageStore(state => state.setIsOpen);

    const navigate = useNavigate();

    const createTutor = () => {
        addTutorToStore();
        updateUser().then(result => {
            if (result) {
                setIsNotificationOpen(!isNotificationOpen);
                setNotificationMessage("Tutor profile successfully created!");
            } else {
                setIsErrorOpen(!isErrorOpen);
                setErrorMessage("Some error occurred during tutor profile creating")
            }
        })
    }

    const deleteTutor = () => {
        deleteTutorFromStore();
        updateUser().then(result => {
            if (result) {
                setIsNotificationOpen(!isNotificationOpen);
                setNotificationMessage("Tutor profile successfully deleted!");
                navigate("/my-student-profile");
            } else {
                setIsErrorOpen(!isErrorOpen);
                setErrorMessage("Some error occurred during tutor profile deleting")
            }
        })
    }

    return (
        <>
            {user?.tutor == null ?
                (<Box sx={{mt: 2}}>
                    <Button
                        fullWidth
                        onClick={createTutor}
                        variant="contained">
                        Create tutor profile
                    </Button>
                </Box>)
                : user.student != null
                    ?
                    (<Box sx={{mt: 2}}>
                        <Button
                            fullWidth
                            color="error"
                            onClick={deleteTutor}
                            variant="outlined">
                            Delete tutor profile
                        </Button>
                    </Box>)
                    : <></>
            }
        </>
    )
}