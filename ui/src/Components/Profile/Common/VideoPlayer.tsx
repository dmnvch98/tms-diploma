import React from "react";
import {Box} from "@mui/material";
import ReactPlayer from "react-player";
import {useVideoStore} from "../../../pages/Profile/Edit/videoStore";

// type Props = {
//     presentationUrl: string
// }
export const VideoPlayer = () => {
    const videoUrl = useVideoStore(state => state.videoUrl);
    return (
        <Box sx={{mt: 2}}>
            <ReactPlayer
                url={videoUrl}
                controls
                width="100%"
                height="100%"
                config={{
                    file: {
                        attributes: {
                            controlsList: 'nodownload',
                            disablePictureInPicture: true
                        }
                    }
                }}
            />
        </Box>
    )
}