import {useProfileStore} from "../../profileStore";
import {useEffect} from "react";
import {StudentEditInfo} from "../../../../Components/Profile/Edit/StudentEditInfo";
import {EditProfile} from "../../../../Components/Profile/Edit/EditProfile";

export const EditStudentProfile = () => {
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
                <StudentEditInfo/>
            </EditProfile>
        </>
    )
}