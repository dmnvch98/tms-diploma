import {useProfileStore} from "./profileStore";
import {useEffect} from "react";

export const MyProfile = () => {
    const getMe = useProfileStore(state => state.getMe)
    useEffect(() => {
            getMe()
    }, [])
    return (
        <>
            <h1>My Profile</h1>
        </>
    )
}