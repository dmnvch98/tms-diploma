import "cropperjs/dist/cropper.css";
import {Cropper} from "react-cropper";
import {useEditProfileStore} from "../../../pages/Profile/editProfileStore";
import {Box, Button} from "@mui/material";
import React from "react";


type Props = {
    avatarUrl: string;
};

export const FileLoader: React.FC<Props> = ({avatarUrl}) => {
    const editMode = useEditProfileStore(state => state.editMode);
    const setEditMode = useEditProfileStore(state => state.setEditMode);
    const cropper = useEditProfileStore(state => state.cropper);
    const setCropper = useEditProfileStore(state => state.setCropper);
    const uploadAvatar = useEditProfileStore(state => state.uploadAvatar);

    const getCropData = async () => {
        if (cropper) {
            const avatar = await fetch(cropper.getCroppedCanvas().toDataURL())
                .then((res) => res.blob())
                .then((blob) => {
                    return new File([blob], "newAvatar.png", {type: "image/png"});
                });
            const form = new FormData();
            form.append('file', avatar, "newAvatar.png");
            uploadAvatar(form);
            setEditMode(false);
        }
    };

    return (
        <>
            {editMode && (
                <><Cropper
                    src={avatarUrl}
                    initialAspectRatio={1}
                    aspectRatio={1}
                    minCropBoxHeight={100}
                    minCropBoxWidth={100}
                    guides={false}
                    checkOrientation={false}
                    onInitialized={(instance) => {
                        setCropper(instance);
                    }}/>
                    <Box
                        sx={{mt: 2}}
                        display="flex"
                        justifyContent="space-between">
                        <Button
                            sx={{width: "48%"}}
                            onClick={getCropData}
                            variant="contained">
                            Upload
                        </Button>
                        <Button
                            variant="contained"
                            color="error"
                            sx={{width: "48%"}}
                            onClick={() => {
                                setEditMode(false)
                            }}>
                            Cancel
                        </Button>
                    </Box>

                </>
            )}

        </>
    )
}