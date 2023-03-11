import React, {useState} from 'react';
import './Marker.css';
import RoomRoundedIcon from '@mui/icons-material/RoomRounded';
import {Box} from "@mui/material";

const markerStyle = {
    position: 'absolute',
    userSelect: 'none',
    transform: 'translate(-50%, -50%)',
    cursor: 'pointer',
    color: 'red',
}
const Marker = (props: any) => {
    const [showCard, setShowCard] = useState(false);
    return (
        <Box sx={markerStyle}>
            <RoomRoundedIcon onClick={() => setShowCard(!showCard)} sx={{fontSize: '48px'}}/>
        </Box>
    );
};

export default Marker;