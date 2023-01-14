import {Button} from "@mui/material";
import {Link as RouterLink} from "react-router-dom";
import {IRedirectButton} from "./store";

export const RedirectButton = (data: IRedirectButton) => {
    return (
        <Button sx={{mt: data.mt, ml: data.ml}}
            {...{
                color: data.color,
                variant: data.variant,
                to: data.href,
                component: RouterLink,
                disabled: data.disabled
            }}
        >
            {data.label}
        </Button>
    );
}