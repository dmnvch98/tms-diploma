import {useProfileStore} from "./profileStore";
import {useEffect} from "react";
import {useNavigate} from "react-router-dom";

export const MyProfile = () => {
    const getMe = useProfileStore(state => state.getMe)
    const user = useProfileStore(state => state.user);
    const navigate = useNavigate();
    useEffect(() => {
        getMe();
    }, [])
    return (
        <>
            <h1>My Profile</h1>
        </>
    )
}