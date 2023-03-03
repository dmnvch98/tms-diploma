import {Box, Button, Paper} from "@mui/material";
import {useProfileStore} from "../../../pages/Profile/profileStore";
import {useUpdateUserInfo} from "../../../pages/Profile/Edit/editProfileInfoStore";
import {useNotificationStore} from "../../Notifications/notificationStore";
import {useErrorMessageStore} from "../../Notifications/errorMessageStore";
import {useNavigate} from "react-router-dom";

export const CreateDeleteStudentProfile = () => {
    const user = useProfileStore(state => state.user);
    const addStudentToStore = useUpdateUserInfo(state => state.createStudent);
    const deleteStudentFromStore = useUpdateUserInfo(state => state.deleteStudent);

    const setNotificationMessage = useNotificationStore(state => state.setMessage)
    const setErrorMessage = useErrorMessageStore(state => state.setMessage)

    const isNotificationOpen = useNotificationStore(state => state.isOpen);
    const isErrorOpen = useErrorMessageStore(state => state.isOpen);

    const setIsNotificationOpen = useNotificationStore(state => state.setIsOpen);
    const setIsErrorOpen = useErrorMessageStore(state => state.setIsOpen);

    const navigate = useNavigate();

    const createStudent = () => {
        addStudentToStore().then(result => {
            if (result) {
                setIsNotificationOpen(!isNotificationOpen);
                setNotificationMessage("Student profile successfully created!");
            } else {
                setIsErrorOpen(!isErrorOpen);
                setErrorMessage("Some error occurred during student profile creating")
            }
        })
    }

    const deleteStudent = () => {
        deleteStudentFromStore().then(result => {
            if (result) {
                setIsNotificationOpen(!isNotificationOpen);
                setNotificationMessage("Student profile successfully deleted!");
                navigate("/my-tutor-profile");
            } else {
                setIsErrorOpen(!isErrorOpen);
                setErrorMessage("Some error occurred during student profile deleting")
            }
        })
    }

    return (
        <>
            {user?.student == null
                ?
                (<Box sx={{mt: 2}}>
                    <Button
                        fullWidth
                        onClick={createStudent}
                        variant="contained">
                        Create student profile
                    </Button>
                </Box>)
                : user.tutor != null
                    ?
                    (<Box sx={{mt: 2}}>
                        <Button
                            fullWidth
                            color="error"
                            onClick={deleteStudent}
                            variant="outlined">
                            Delete student profile
                        </Button>
                    </Box>)
                    : <></>
            }
        </>
    )
}