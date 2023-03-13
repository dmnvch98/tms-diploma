import {Box, Button, Grid, MenuItem, styled, TextField, Typography} from "@mui/material";
import React, {useEffect, useState} from "react";
import {useCreateConversationStore} from "./createConversationStore";
import Link from "@mui/material/Link";
import {useConversationTypeStore} from "../../CommonStore/conversationTypeStore";
import {AdapterDayjs} from '@mui/x-date-pickers/AdapterDayjs';
import {LocalizationProvider} from '@mui/x-date-pickers/LocalizationProvider';
import dayjs, {Dayjs} from "dayjs";
import {DatePicker, TimePicker} from "@mui/x-date-pickers";
import {useLevelsStore} from "../../pages/SignUp/store/levelStore";
import {useLanguagesStore} from "../../pages/SignUp/store/languagesStore";

export const CreateConversation = () => {
    const getTutorAddresses = useCreateConversationStore(state => state.getAddresses);
    const tutorAddresses = useCreateConversationStore(state => state.tutorAddresses);
    const addressId = useCreateConversationStore(state => state.addressId);
    const setAddressId = useCreateConversationStore(state => state.setAddressId);
    const price = useCreateConversationStore(state => state.price);
    const setPrice = useCreateConversationStore(state => state.setPrice);
    const convTypes = useConversationTypeStore(state => state.convTypes);
    const convTypeId = useCreateConversationStore(state => state.convTypeId);
    const setConvTypeId = useCreateConversationStore(state => state.setConvTypeId);
    const setStartTime = useCreateConversationStore(state => state.setStartTime);
    const setEndTime = useCreateConversationStore(state => state.setEndTime);
    const setDate = useCreateConversationStore(state => state.setDate);
    const languageId = useCreateConversationStore(state => state.languageId);
    const setLanguageId = useCreateConversationStore(state => state.setLanguageId);
    const minLevelId = useCreateConversationStore(state => state.levelId);
    const setMinLevelId = useCreateConversationStore(state => state.setMinLevelId);
    const getLevels = useLevelsStore(state => state.getLevels);
    const levels = useLevelsStore(state => state.levelsList);
    const languages = useLanguagesStore(state => state.languagesList);
    const getLanguages = useLanguagesStore(state => state.getLanguages);
    const createConversation = useCreateConversationStore(state => state.createConversation);

    useEffect(() => {
        getTutorAddresses();
        getLevels();
        getLanguages();
    }, [])

    const [pickerDate, setPickerDate] = React.useState<Dayjs | null>(dayjs('2023-04-17'));
    const [pickerStartTime, setPickerStartTime] = React.useState<Dayjs | null>(dayjs('2023-04-17T14:00'));
    const [pickerEndTime, setPickerEndTime] = React.useState<Dayjs | null>(dayjs('2023-04-17T15:00'));

    return (
        <>
            <Box>
                <TextField
                    fullWidth
                    select
                    variant="outlined"
                    label="Address"
                    value={addressId}
                    onChange={(e) => {
                        setAddressId(+e.target.value)
                    }}
                >{tutorAddresses.map((address, index) => (
                    <MenuItem key={index}
                              value={+address.addressId as number}>
                        {address.address}
                    </MenuItem>
                ))}
                </TextField>

                <Typography display='flex' justifyContent='flex-end'>
                    <Link href='/add-address' underline='none'>
                        Add address
                    </Link>
                </Typography>
                <Box display='flex' justifyContent='space-between'>
                    <TextField
                        sx={{width: '48%'}}
                        variant="outlined"
                        label="Price $"
                        value={price}
                        onChange={(e) => {
                            setPrice(+e.target.value)
                        }}>
                    </TextField>

                    <TextField
                        sx={{width: '48%'}}
                        select
                        variant="outlined"
                        label="Type"
                        value={convTypeId}
                        onChange={(e) => {
                            setConvTypeId(+e.target.value)
                        }}
                        fullWidth
                    >{convTypes.map((conv) => (
                        <MenuItem key={conv.convTypeId} value={conv.convTypeId}>{conv.description}</MenuItem>
                    ))}
                    </TextField>
                </Box>

                <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DatePicker
                        onChange={(newValue) => {
                            setPickerDate(newValue);
                            setDate(newValue?.format('YYYY-MM-DD') as string);
                        }}
                        value={pickerDate}
                        disablePast
                        label="Date"
                        sx={{width: '100%', mt: 3}}/>
                    <Box sx={{mt: 3}} display='flex' justifyContent='space-between'>
                        <TimePicker
                            onChange={(newValue) => {
                                setPickerStartTime(newValue);
                                setStartTime(newValue?.format('H:m') as string);
                            }}
                            value={pickerStartTime}
                            label="Start time"
                            sx={{width: '48%'}}/>
                        <TimePicker
                            onChange={(newValue) => {
                                setPickerEndTime(newValue);
                                setEndTime(newValue?.format('H:m') as string);
                            }}
                            value={pickerEndTime}
                            label="End time"
                            sx={{width: '48%'}}/>
                    </Box>
                </LocalizationProvider>
                <Box display='flex' justifyContent='space-between' sx={{mt: 3}}>
                    <TextField
                        select
                        variant="outlined"
                        label="Language"
                        sx={{width: '48%'}}
                        value={languageId}
                        onChange={(e) => {
                            setLanguageId(+e.target.value)
                        }
                        }
                    >{languages.map((lang) => (
                        <MenuItem key={lang.languageId} value={lang.languageId}>{lang.description}</MenuItem>
                    ))}
                    </TextField>
                    <TextField
                        select
                        variant="outlined"
                        label="Min Level"
                        sx={{width: '48%'}}
                        onChange={(e) => setMinLevelId(+e.target.value)}
                        value={minLevelId}
                    >{levels.map((level) => (
                        <MenuItem key={level.levelId} value={level.levelId}>{level.description}</MenuItem>
                    ))}
                    </TextField>
                </Box>
                <Button fullWidth
                        onClick={() => {
                            createConversation();
                        }}
                        variant='contained'
                        size='large'
                        sx={{mt: 3}}>
                    Add Coversation details
                </Button>
            </Box>

        </>
    )
}