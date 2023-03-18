import {Box, Tab, Tabs, Typography} from "@mui/material";
import React, {useEffect, useState} from "react";
import {FeedbackCard} from "../../Feedbacks/FeedbackCard";
import {ConversationDetailsCard} from "../../Conversations/ConversationDetailsCard";
import {useTutorStore} from "./tutorStore";
import {useFeedbackStore} from "../Common/feedbackStore";

type Props = {
    currentUser: boolean;
    tutorId: number;
}
export const TutorProfileTabs: React.FC<Props> = ({currentUser, tutorId}) => {
    const getTutorNotBookedConversationDetails = useTutorStore(state => state.getTutorNotBookedConversationDetails);
    const convDetails = useTutorStore(state => state.convDetails);
    const feedbacksAboutTutor = useFeedbackStore(state => state.feedbacksAboutTutor);
    const getFeedbacksAboutTutor = useFeedbackStore(state => state.getFeedbacksAboutTutor);

    useEffect(() => {
        if (tutorId) {
            getTutorNotBookedConversationDetails(tutorId);
            getFeedbacksAboutTutor(tutorId);
        }
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

    return (
        <>
            <Box>
                <Box sx={{borderBottom: 1, borderColor: 'divider'}}>
                    <Tabs value={value} onChange={handleChange} aria-label="basic tabs example">
                        <Tab label="Feedbacks" sx={{mr: 4}} {...a11yProps(0)} />
                        <Tab label="Availability" {...a11yProps(1)} />
                    </Tabs>
                </Box>
                <TabPanel value={value} index={0}>
                    {feedbacksAboutTutor.map(feedback => (
                        <Box key={feedback.feedbackId}>
                            <FeedbackCard
                                feedbackId={feedback.feedbackId}
                                firstName={feedback.firstName}
                                lastName={feedback.lastName}
                                tutorId={feedback.tutorId}
                                studentId={feedback.studentId}
                                rate={feedback.rate}
                                feedback={feedback.feedback}
                                languageDescription={feedback.languageDescription}
                                userAvatarUrl={feedback.userAvatarUrl}
                            />
                        </Box>
                    ))}
                </TabPanel>
                <TabPanel value={value} index={1}>

                    {convDetails.map(cd =>
                        (
                            <ConversationDetailsCard
                                convDetailsId={cd.convDetailsId}
                                conversationType={cd.conversationType.description}
                                price={cd.price}
                                minLanguageLevel={cd.minLanguageLevel}
                                startDate={cd.startDate}
                                address={cd.address}
                                displayBookButton={!currentUser}
                                endDate={cd.endDate}/>
                        )
                    )}
                </TabPanel>
            </Box>
        </>
    )
}