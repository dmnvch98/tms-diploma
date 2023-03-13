import {Autocomplete, Box, Button, TextField, Typography} from "@mui/material";
import React, {useEffect} from "react";
import {useCreateConversationStore} from "./createConversationStore";
import Link from "@mui/material/Link";


export const CreateConversation = () => {
    const getTutorAddresses = useCreateConversationStore(state => state.getAddresses);
    const tutorAddresses = useCreateConversationStore(state => state.tutorAddresses);

    useEffect(() => {
        getTutorAddresses();
    }, [])

    return (
        <>
            <Box>
                <Autocomplete
                    disablePortal
                    id="combo-box-demo"
                    options={tutorAddresses.map(a => a.address)}
                    // onChange={(e, value) => {
                    //     if (value != null) {
                    //         setCity(value as string);
                    //     }
                    // }}
                    renderInput={(params) =>
                        <TextField {...params}
                                   fullWidth
                                   label="Addresses"
                                   variant='standard'
                        />}
                />
                <Typography display='flex' justifyContent='flex-end'>
                    <Link href='/add-address' underline='none' >
                        Add address
                    </Link>
                </Typography>

            </Box>

        </>
    )
}