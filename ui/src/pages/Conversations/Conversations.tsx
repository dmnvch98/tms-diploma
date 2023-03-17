import {useConversationsStore} from "./conversationsStore";
import React, {useEffect, useState} from "react";
import {ConversationCard} from "../../Components/Conversations/ConversationCard";
import {Box, Container, Tab, Tabs, Typography} from "@mui/material";
import {SidebarHeader} from "../../Components/SidebarHeader";

export const Conversations = () => {
    const getStudentConversations = useConversationsStore(state => state.getStudentConversations);
    const getTutorConversations = useConversationsStore(state => state.getTutorConversations);
    const tutorConversations = useConversationsStore(state => state.tutorConversations);
    const studentConversations = useConversationsStore(state => state.studentConversations);

    useEffect(() => {
        getStudentConversations();
        getTutorConversations();
    }, [])

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

    return (
        <>
            <SidebarHeader/>
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
                </Box>
            </Container>

        </>
    )
}