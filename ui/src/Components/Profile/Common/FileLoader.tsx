import "cropperjs/dist/cropper.css";
import {Cropper} from "react-cropper";
import {useAvatarStore} from "../../../pages/Profile/Edit/avatarStore";
import {Box, Button} from "@mui/material";
import React from "react";
import {useNotificationStore} from "../../Notifications/notificationStore";


type Props = {
    avatarUrl: string;
};

export const FileLoader: React.FC<Props> = ({avatarUrl}) => {
    const editMode = useAvatarStore(state => state.editMode);
    const setEditMode = useAvatarStore(state => state.setEditMode);
    const cropper = useAvatarStore(state => state.cropper);
    const setCropper = useAvatarStore(state => state.setCropper);
    const uploadAvatar = useAvatarStore(state => state.uploadAvatar);
    const isErrorOpen = useNotificationStore(state => state.isOpen);
    const setIsErrorOpen = useNotificationStore(state => state.setIsOpen)
    const setErrorMessage = useNotificationStore(state => state.setMessage)

    const getCropData = async () => {
        if (cropper) {
            try {
                const avatar = await fetch(cropper.getCroppedCanvas().toDataURL())
                    .then((res) => res.blob())
                    .then((blob) => {
                        return new File([blob], "newAvatar.png", {type: "image/png"});
                    });
                const form = new FormData();
                form.append('file', avatar, "newAvatar.png");
                uploadAvatar(form);
                setEditMode(!editMode);
                setIsErrorOpen(!isErrorOpen);
            } catch (e: unknown) {
                setErrorMessage(e as string);
            }
        }
    };

    const cropperStyle = {
        width: '100%',
        height: '70vh',
        marginBottom: '16px',
    };


    return (
        <>
            {editMode && (
                <>
                    <Cropper
                    style={cropperStyle}
                    src={avatarUrl}
                    viewMode={1}
                    initialAspectRatio={1}
                    aspectRatio={1}
                    minCropBoxHeight={100}
                    minCropBoxWidth={100}
                    guides={false}
                    checkOrientation={false}
                    onInitialized={(instance) => {
                        setCropper(instance);
                    }}
                />
                    <Box
                        sx={{
                            display: 'flex',
                            justifyContent: 'space-between',
                            marginTop: '16px',
                        }}
                    >
                        <Button
                            sx={{ width: '48%' }}
                            onClick={getCropData}
                            variant="contained"
                        >
                            Upload
                        </Button>
                        <Button
                            variant="contained"
                            color="error"
                            sx={{ width: '48%' }}
                            onClick={() => {
                                setEditMode(false);
                            }}
                        >
                            Cancel
                        </Button>
                    </Box>
                </>
            )}

        </>
    )
}
