import {useConversationsStore} from "./conversationsStore";
import React, {useEffect, useState} from "react";
import {ConversationCard} from "../../Components/Conversations/ConversationCard";
import {Box, Button, Container, Modal, Rating, Tab, Tabs, TextField, Typography} from "@mui/material";
import {SidebarHeader} from "../../Components/SidebarHeader";
import {useFeedbackStore} from "../../Components/Profile/Common/feedbackStore";
import {Notification} from "../../Components/Notifications/Notification";
import {useNotificationStore} from "../../Components/Notifications/notificationStore";

export const Conversations = () => {
    const getStudentConversations = useConversationsStore(state => state.getStudentConversations);
    const getTutorConversations = useConversationsStore(state => state.getTutorConversations);
    const tutorConversations = useConversationsStore(state => state.tutorConversations);
    const studentConversations = useConversationsStore(state => state.studentConversations);

    const leaveFeedBackModalOpen = useFeedbackStore(state => state.leaveFeedBackModalOpen);
    const setLeaveFeedBackModalOpen = useFeedbackStore(state => state.setLeaveFeedBackModalOpen);
    const isNotificationOpen = useNotificationStore(state => state.isOpen);

    useEffect(() => {
        getStudentConversations();
        getTutorConversations();
    }, [])

    const style = {
        position: 'absolute' as 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 500,
        bgcolor: 'background.paper',
        border: '2px solid #000',
        boxShadow: 24,
        p: 4,
    };


    interface TabPanelProps {
        children?: React.ReactNode;
        index: number;
        value: number;
    }

    const [value, setValue] = useState(0);

    const handleChange = (event: React.SyntheticEvent, newValue: number) => {
        setValue(newValue);
    };

    const a11yProps = (index: number) => {
        return {
            id: `simple-tab-${index}`,
            'aria-controls': `simple-tabpanel-${index}`,
        };
    }

    function TabPanel(props: TabPanelProps) {
        const {children, value, index, ...other} = props;

        return (
            <div
                role="tabpanel"
                hidden={value !== index}
                id={`simple-tabpanel-${index}`}
                aria-labelledby={`simple-tab-${index}`}
                {...other}
            >
                {value === index && (
                    <Box sx={{minHeight: '30vh', pb: 2}}>
                        <Typography component={'span'}>{children}</Typography>
                    </Box>
                )}
            </div>
        );
    }

    const TutorConversation = () => {
        return (
            <>
                {tutorConversations.map(conversation => (
                    <ConversationCard
                        convId={conversation.id}
                        profileType="tutor"
                        studentLeftFeedback={conversation.studentLeftFeedback}
                        tutorLeftFeedback={conversation.tutorLeftFeedback}
                        conversationType={conversation.conversationDetails.conversationType.description}
                        price={conversation.conversationDetails.price}
                        address={conversation.conversationDetails.address}
                        minLanguageLevel={conversation.conversationDetails.minLanguageLevel}
                        startDate={conversation.conversationDetails.startDate}
                        endDate={conversation.conversationDetails.endDate}
                        studentId={conversation.studentId}
                        tutorId={conversation.conversationDetails.tutorId}
                        conversationStatus={conversation.status}/>
                ))}
            </>
        )
    }

    const StudentConversations = () => {
        return (
            <>
                {studentConversations.map(conversation => (
                    <ConversationCard
                        convId={conversation.id}
                        profileType="student"
                        studentLeftFeedback={conversation.studentLeftFeedback}
                        tutorLeftFeedback={conversation.tutorLeftFeedback}
                        conversationType={conversation.conversationDetails.conversationType.description}
                        price={conversation.conversationDetails.price}
                        address={conversation.conversationDetails.address}
                        minLanguageLevel={conversation.conversationDetails.minLanguageLevel}
                        startDate={conversation.conversationDetails.startDate}
                        endDate={conversation.conversationDetails.endDate}
                        studentId={conversation.studentId}
                        tutorId={conversation.conversationDetails.tutorId}
                        conversationStatus={conversation.status}/>
                ))}
            </>
        )
    }

    const LeaveFeedback = () => {
        const rate = useFeedbackStore(state => state.rate);
        const feedback = useFeedbackStore(state => state.feedback);
        const setRate = useFeedbackStore(state => state.setRate);
        const setFeedback = useFeedbackStore(state => state.setFeedback);
        const saveFeedback = useFeedbackStore(state => state.saveFeedback);

        const isNotificationOpen = useNotificationStore(state => state.isOpen);
        const setMessage = useNotificationStore(state => state.setMessage);
        const setNotificationOpen = useNotificationStore(state => state.setIsOpen);

        const setNotificationFeedbackIsSaved = () => {
            setNotificationOpen(!isNotificationOpen);
            setMessage("Feedback successfully saved");
        }

        const handleSaveFeedback = () => {
            saveFeedback().then(result => {
                if (result) {
                    setNotificationFeedbackIsSaved();
                    getStudentConversations();
                    getTutorConversations();
                }
            });
        }

        return (
            <>
                <Box>
                    <Box display='flex'>
                        <Typography variant='h6' sx={{mr: 4}}>
                            Rating
                        </Typography>
                        <Rating
                            size='large'
                            name="simple-controlled"
                            value={rate}
                            onChange={(event, rate) => {
                                setRate(rate as number);
                            }}
                        />
                    </Box>
                    <TextField
                        id="outlined-multiline-flexible"
                        label="Your impressions"
                        minRows={5}
                        multiline
                        sx={{mt: 2}}
                        maxRows={10}
                        value={feedback}
                        fullWidth
                        onChange={(e) => {
                            setFeedback(e.target.value)
                        }}
                    />
                    <Box display='flex' justifyContent='flex-end' sx={{mt: 2}}>
                        <Button
                            variant='contained'
                            onClick={handleSaveFeedback}
                            sx={{width: '35%'}}>
                            Submit
                        </Button>
                    </Box>

                </Box>

            </>
        )
    }

    return (
        <>
            <SidebarHeader/>
            {isNotificationOpen && (<Notification/>)}
            <Container sx={{mt: 10}}>
                <Box>
                    <Box sx={{borderBottom: 1, borderColor: 'divider'}}>
                        <Tabs value={value} onChange={handleChange} aria-label="basic tabs example">
                            <Tab label="Tutor profile"{...a11yProps(0)} />

                            <Tab label="Student profile" {...a11yProps(1)} />
                        </Tabs>
                    </Box>
                    <TabPanel value={value} index={0}>
                        <TutorConversation/>
                    </TabPanel>
                    <TabPanel value={value} index={1}>
                        <StudentConversations/>
                    </TabPanel>
                    <Modal
                        open={leaveFeedBackModalOpen}
                        onClose={() => setLeaveFeedBackModalOpen(!leaveFeedBackModalOpen)}
                    >
                        <Box sx={style}>
                            <LeaveFeedback/>
                        </Box>
                    </Modal>
                </Box>
            </Container>

        </>
    )
}