import {useProfileStore} from "../../profileStore";
import {useEffect} from "react";
import {EditProfile} from "../../../../Components/Profile/Edit/EditProfile";
import {TutorEditInfo} from "../../../../Components/Profile/Edit/TutorEditInfo";

export const EditTutorProfile = () => {
    const user = useProfileStore(state => state.user);
    const getMe = useProfileStore(state => state.getMe)

    useEffect(() => {
        if (user == null) {
            getMe();
        }
    }, [])

    return (
        <>
            <EditProfile>
                <TutorEditInfo/>
            </EditProfile>
        </>
    )
}