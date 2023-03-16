import {Box, Button, MenuItem, TextField, Typography} from "@mui/material";
import React, {useEffect} from "react";
import {useCreateConversationStore} from "./createConversationStore";
import Link from "@mui/material/Link";
import {useConversationTypeStore} from "../../CommonStore/conversationTypeStore";
import {AdapterDayjs} from '@mui/x-date-pickers/AdapterDayjs';
import {LocalizationProvider} from '@mui/x-date-pickers/LocalizationProvider';
import dayjs, {Dayjs} from "dayjs";
import {DatePicker, TimePicker} from "@mui/x-date-pickers";
import {useLevelsStore} from "../../pages/SignUp/store/levelStore";
import {useNotificationStore} from "../Notifications/notificationStore";
import {useProfileStore} from "../../pages/Profile/profileStore";

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
    const user = useProfileStore(state => state.loggedInUser);
    const createConversation = useCreateConversationStore(state => state.createConversation);

    useEffect(() => {
        getTutorAddresses();
        getLevels();
    }, [])

    const [pickerDate, setPickerDate] = React.useState<Dayjs | null>(dayjs(null));
    const [pickerStartTime, setPickerStartTime] = React.useState<Dayjs | null>(dayjs(null));
    const [pickerEndTime, setPickerEndTime] = React.useState<Dayjs | null>(dayjs(null));

    const isNotificationOpen = useNotificationStore(state => state.isOpen);
    const setMessage = useNotificationStore(state => state.setMessage);
    const setNotificationOpen = useNotificationStore(state => state.setIsOpen);

    const setNotificationConvDetailsAreSaved = () => {
        setNotificationOpen(!isNotificationOpen);
        setMessage("Conversation details successfully saved");
    }

    const TIME_FORMAT: string = 'HH:mm';
    const DATE_FORMAT: string = 'YYYY-MM-DD';

    return (
        <>
            <Box>
                <Box display='flex' justifyContent='space-between'>
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
                    <TextField
                        sx={{width: '48%'}}
                        variant="outlined"
                        label="Price $"
                        value={price}
                        onChange={(e) => {
                            setPrice(+e.target.value)
                        }}>
                    </TextField>
                </Box>

                <TextField
                    disabled={convTypeId == 2}
                    fullWidth
                    select
                    variant="outlined"
                    label="Address"
                    value={addressId}
                    sx={{mt: 3}}
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

                <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DatePicker
                        onChange={(newValue) => {
                            setPickerDate(newValue);
                            setDate(newValue?.format(DATE_FORMAT) as string);
                        }}
                        value={pickerDate}
                        disablePast
                        label="Date"
                        sx={{width: '100%'}}/>
                    <Box sx={{mt: 3}} display='flex' justifyContent='space-between'>
                        <TimePicker
                            onChange={(newValue) => {
                                setPickerStartTime(newValue);
                                setStartTime(newValue?.format(TIME_FORMAT) as string);
                            }}
                            ampm={false}
                            value={pickerStartTime}
                            label="Start time"
                            sx={{width: '48%'}}/>
                        <TimePicker
                            onChange={(newValue) => {
                                setPickerEndTime(newValue);
                                setEndTime(newValue?.format(TIME_FORMAT) as string);
                            }}
                            ampm={false}
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
                    >
                        {user?.languageLevels.map((ll, index) => (
                        <MenuItem key={index} value={ll.language.languageId}>{ll.language.description}</MenuItem>
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
                            createConversation().then(result => {
                                if (result) {
                                    setNotificationConvDetailsAreSaved();
                                }
                            });
                        }}
                        variant='contained'
                        size='large'
                        disabled={
                            price == ''
                            || pickerDate == null
                            || pickerStartTime == null
                            || pickerEndTime == null
                            || languageId == ''
                            || minLevelId == ''
                        }
                        sx={{mt: 3}}>
                    Add Coversation details
                </Button>
            </Box>

        </>
    )
}