import React, {ChangeEvent, useEffect} from "react";
import {Box, Button, Modal, Paper} from "@mui/material";
import {useProfileStore} from "../../../pages/Profile/profileStore";
import {VideoLoaderModal} from "./VideoLoaderModal";
import {useVideoStore} from "../../../pages/Profile/Edit/videoStore";
import {UserRole} from "./userRolesEnum";
import {VideoPlayer} from "./VideoPlayer";

type Props = {
    role: UserRole;
};

export const EditVideoPresentation: React.FC<Props> = ({role}) => {
    const editMode = useVideoStore(state => state.editMode);
    const setEditMode = useVideoStore(state => state.setEditMode);
    const user = useProfileStore(state => state.loggedInUser)
    const getMe = useProfileStore(state => state.getMe)
    const setVideoUrl = useVideoStore(state => state.setVideoUrl);


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
        p: 4
    };

    let fileInput: HTMLInputElement | null;
    const getNewFileUrl = (e: ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            setEditMode(true);
            setVideoUrl(URL.createObjectURL(e.target.files[0]))
            if (fileInput != null) {
                fileInput.value = "";
            }
        }
    };

    return (
        <>
            <Box sx={{mt: 4}}>
                <VideoPlayer/>
                <Box display='flex'>
                    <Button variant="contained"
                            component="label"
                            fullWidth
                            sx={{mt: 2}}
                    >
                        Select Video
                        <input
                            type="file"
                            hidden
                            accept="video/mp4"
                            onChange={getNewFileUrl}
                            ref={ref => fileInput = ref}
                        />
                    </Button>
                    <Button
                        sx={{mt: 2}}
                        fullWidth
                        variant="contained"
                        color="error">
                        Delete
                    </Button>
                </Box>

                {editMode && (
                    <Modal
                        open={editMode}
                        aria-labelledby="modal-modal-title"
                        aria-describedby="modal-modal-description"
                    >
                        <Box sx={style}>
                            <VideoLoaderModal role={role}/>
                        </Box>
                    </Modal>

                )}
            </Box>
        </>
    )
}