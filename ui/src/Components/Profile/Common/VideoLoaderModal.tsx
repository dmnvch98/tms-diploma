import {Box, Button} from "@mui/material";
import React from "react";
import {useVideoStore} from "../../../pages/Profile/Edit/videoStore";
import {VideoPlayer} from "./VideoPlayer";
import {UserRole} from "./userRolesEnum";

type Props = {
    role: UserRole;
};

export const VideoLoaderModal: React.FC<Props> = ({role}) => {
    const editMode = useVideoStore(state => state.editMode);
    const setEditMode = useVideoStore(state => state.setEditMode);
    const videoUrl = useVideoStore(state => state.videoUrl);
    const uploadStudentVideoPresentation = useVideoStore(state => state.uploadStudentVideoPresentation);
    const uploadTutorVideoPresentation = useVideoStore(state => state.uploadTutorVideoPresentation);
    const handleUpload = async () => {
        try {
            const response = await fetch(videoUrl);
            const videoData = await response.blob();

            const videoFile = new File([videoData], 'video.mp4', {
                type: 'video/mp4',
            });

            const form = new FormData();
            form.append('file', videoFile, 'newVideoPresentation.mp4');

            role == UserRole.Student
                ? uploadStudentVideoPresentation(form)
                : uploadTutorVideoPresentation(form)

            setEditMode(false);
        } catch (error) {
            console.error('Error when uploading a video file: ', error);
        }
    };


    return (
        <>
            {editMode && (
                <>
                    <VideoPlayer/>
                    <Box
                        sx={{
                            display: 'flex',
                            justifyContent: 'space-between',
                            marginTop: '16px',
                        }}
                    >
                        <Button
                            sx={{width: '48%'}}
                            variant="contained"
                            onClick={handleUpload}
                        >
                            Upload
                        </Button>
                        <Button
                            variant="contained"
                            color="error"
                            sx={{width: '48%'}}
                            onClick={() => {
                                setEditMode(false);
                            }}
                        >
                            Cancel
                        </Button>

                    </Box>
                </>
            )}

        </>
    )
}