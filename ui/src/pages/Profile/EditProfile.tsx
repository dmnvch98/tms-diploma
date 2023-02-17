import {FileLoader} from "../../Components/Profile/Common/FileLoader";
import {ChangeEvent, useState} from "react";

export const EditProfile = () => {
    const [newAvatarUrl, setNewAvatarUrl] = useState("");
    const [editMode, setEditMode] = useState(false);


    const getNewAvatarUrl = (e: ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            setEditMode(true);
            setNewAvatarUrl(URL.createObjectURL(e.target.files[0]));
        }
    };
    return (
        <>
            <input
                type="file"
                accept="image/png, image/jpeg, image/jpg"
                onChange={getNewAvatarUrl}
            />
            {editMode && (
                <FileLoader
                    avatarUrl={newAvatarUrl}
                />
            )}
        </>
    )
}