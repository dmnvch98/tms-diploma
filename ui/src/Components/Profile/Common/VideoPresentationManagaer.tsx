import React from "react";
import {Box, Button} from "@mui/material";
import {VideoPlayer} from "./VideoPlayer";

type Props = {
    presentationUrl: string
}

export const VideoPresentationManagaer: React.FC<Props> = ({presentationUrl}) => {
    return (
        <Box>
            <VideoPlayer presentationUrl={presentationUrl}/>
            <Box>
                <Button>
                    Delete video
                </Button>
            </Box>
        </Box>
    )
}