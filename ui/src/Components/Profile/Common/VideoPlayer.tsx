import React from "react";
import {Box} from "@mui/material";
import ReactPlayer from "react-player";

type Props = {
    presentationUrl: string
}
export const VideoPlayer: React.FC<Props> = ({presentationUrl}) => {
    return (
        <Box sx={{mt: 2}}>
            <ReactPlayer
                url={presentationUrl}
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