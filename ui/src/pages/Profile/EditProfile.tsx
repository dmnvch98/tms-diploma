import {FileLoader} from "../../Components/Profile/Common/FileLoader";
import {ChangeEvent, useEffect} from "react";
import {useEditProfileStore} from "./editProfileStore";
import {Box, Button, Modal, Paper} from "@mui/material";
import {SidebarHeader} from "../../Components/SidebarHeader";

export const EditProfile = () => {
    const existingAvatarUrl = useEditProfileStore(state => state.existingAvatarUrl);
    const newAvatarUrl = useEditProfileStore(state => state.newAvatarUrl);
    const setNewAvatarUrl = useEditProfileStore(state => state.setNewAvatarUrl);
    const editMode = useEditProfileStore(state => state.editMode);
    const setEditMode = useEditProfileStore(state => state.setEditMode);
    const getAvatar = useEditProfileStore(state => state.getAvatar);
    const deleteAvatar = useEditProfileStore(state => state.deleteAvatar);

    useEffect(() => {
        getAvatar()
    }, [])

    const style = {
        position: 'absolute' as 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 600,
        bgcolor: 'background.paper',
        border: '2px solid #000',
        boxShadow: 24,
        p: 4,
    };


    let fileInput: HTMLInputElement | null;
    const getNewAvatarUrl = (e: ChangeEvent<HTMLInputElement>) => {
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
            <SidebarHeader/>
            <Box sx={{m: 2, height: "62vh"}}
                 display="flex"
                 justifyContent="center"
                 alignItems="center">
                <Box sx={{maxWidth: "300px"}}>
                    <Paper sx={{p: 1, mb: 2}}>
                        <img src={existingAvatarUrl} width="250px" height="250px" alt=""/>
                    </Paper>
                    <Box justifyContent="space-between" display="flex">
                        <Button variant="contained"
                                component="label"
                                sx={{width: "47%"}}
                        >
                            Select
                            <input
                                type="file"
                                hidden
                                accept="image/png, image/jpeg, image/jpg"
                                onChange={getNewAvatarUrl}
                                ref={ref => fileInput = ref}
                            />
                        </Button>
                        <Button
                            variant="contained"
                            sx={{width: "47%"}}
                            onClick={deleteAvatar}
                            color="error">
                            Delete
                        </Button>
                    </Box>
                </Box>

                {editMode && (
                    <Modal
                        open={true}
                        aria-labelledby="modal-modal-title"
                        aria-describedby="modal-modal-description"
                    >
                        <Box sx={style}>
                            <FileLoader
                                avatarUrl={newAvatarUrl}
                            />
                        </Box>
                    </Modal>

                )}
            </Box>
        </>
    )
}