import StarIcon from "@mui/icons-material/Star";
import {Typography} from "@mui/material";
import React from "react";
import StarBorderIcon from "@mui/icons-material/StarBorder";

type Props = {
    averageRate: number
}
export const FeedbackStar: React.FC<Props> = ({averageRate}) => {
    return (
        <>
            {averageRate
                ?
                (<>
                    <StarIcon sx={{ml: 4, color: '#FFBF00'}}/>
                    <Typography>{averageRate}</Typography>
                </>)
                : <StarBorderIcon sx={{color: '#b5b3b3', ml: 4}}/>}
        </>
    )
}