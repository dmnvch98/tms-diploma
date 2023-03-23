import {useProfileStore} from "../../pages/Profile/profileStore";
import {useEditProfileStore} from "../../pages/Profile/Edit/editAvatarStore";
import React, {useEffect, useState} from "react";
import {Box} from "@mui/material";
import {AxiosError} from "axios";
import {useNotificationStore} from "../Notifications/notificationStore";

export const Avatar = () => {
    const existingAvatarUrl = useEditProfileStore(state => state.existingAvatarUrl);

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
