import "cropperjs/dist/cropper.css";
import {Cropper} from "react-cropper";
import FileService from "../../../services/FileService";
import {useEditProfileStore} from "../../../pages/Profile/editProfileStore";
import {Button} from "@mui/material";


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
                    style={{height: 400, width: 400}}
                    initialAspectRatio={4 / 3}
                    minCropBoxHeight={100}
                    minCropBoxWidth={100}
                    guides={false}
                    checkOrientation={false}
                    onInitialized={(instance) => {
                        setCropper(instance);
                    }}/><Button onClick={getCropData} variant="outlined">
                    Upload
                </Button></>
            )}

        </>
    )
}