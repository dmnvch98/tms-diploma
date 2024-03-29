import {useAvatarStore} from "../../../pages/Profile/Edit/avatarStore";
import {useProfileStore} from "../../../pages/Profile/profileStore";
import {ChangeEvent, useEffect} from "react";
import {Box, Button, Modal, Paper} from "@mui/material";
import {Avatar} from "../Avatar";
import {AvatarLoader} from "../Common/AvatarLoader";

export const EditAvatar = () => {
    const newAvatarUrl = useAvatarStore(state => state.newAvatarUrl);
    const setNewAvatarUrl = useAvatarStore(state => state.setNewAvatarUrl);
    const editMode = useAvatarStore(state => state.editMode);
    const setEditMode = useAvatarStore(state => state.setEditMode);
    const deleteAvatar = useAvatarStore(state => state.deleteAvatar);
    const user = useProfileStore(state => state.loggedInUser)
    const getMe = useProfileStore(state => state.getMe)

    useEffect(() => {
        if (user == null) {
            getMe();
        }
    }, [])

    const style = {
        position: 'absolute' as 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        bgcolor: 'background.paper',
        border: '2px solid #000',
        boxShadow: 24,
        p: 4,
        width: '90%',
        maxWidth: 600,
        height: '90vh',
        maxHeight: 800,
    };

    let fileInput: HTMLInputElement | null;
    const getNewFileUrl = (e: ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            setEditMode(true);
            setNewAvatarUrl(URL.createObjectURL(e.target.files[0]));
            if (fileInput != null) {
                fileInput.value = "";
            }
        }
    };

    return (
        <>
            <Box sx={{mt: 4}}>
                <Paper sx={{p: 2}}>
                    <Avatar/>
                    <Button variant="contained"
                            component="label"
                            fullWidth
                            sx={{mt: 2}}
                    >
                        Select
                        <input
                            type="file"
                            hidden
                            accept="image/png, image/jpeg, image/jpg"
                            onChange={getNewFileUrl}
                            ref={ref => fileInput = ref}
                        />
                    </Button>

                    <Button
                        sx={{mt: 2}}
                        fullWidth
                        variant="contained"
                        onClick={deleteAvatar}
                        color="error">
                        Delete
                    </Button>
                </Paper>
                {editMode && (
                    <Modal
                        open={editMode}
                        aria-labelledby="modal-modal-title"
                        aria-describedby="modal-modal-description"
                    >
                        <Box sx={style}>
                            <AvatarLoader
                                avatarUrl={newAvatarUrl}
                            />
                        </Box>
                    </Modal>

                )}
            </Box>
        </>
    )
}