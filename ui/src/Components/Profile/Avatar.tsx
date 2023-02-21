import {useProfileStore} from "../../pages/Profile/profileStore";
import {useEditProfileStore} from "../../pages/Profile/editProfileStore";
import React, {useEffect} from "react";
import {Box} from "@mui/material";

export const Avatar = () => {
    const user = useProfileStore(state => state.user);
    const existingAvatarUrl = useEditProfileStore(state => state.existingAvatarUrl);
    const getAvatar = useEditProfileStore(state => state.getAvatar);

    useEffect(() => {
        if (user != null) {
            getAvatar(user.id);
        }
    })

    const style = {
        maxWidth: "100%",
        margin: "auto",
        display: "block",
        borderRadius: 3
    };

    return (
        <>
            <Box
                component="img"
                sx={style}
                alt="Avatar"
                src={existingAvatarUrl}
            />
        </>
    )
}
