import {useAvatarStore} from "../../pages/Profile/Edit/avatarStore";
import React, {useEffect} from "react";
import {Box} from "@mui/material";

export const Avatar = () => {
    const existingAvatarUrl = useAvatarStore(state => state.existingAvatarUrl);
    useEffect(() => {
        console.log(existingAvatarUrl);
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
