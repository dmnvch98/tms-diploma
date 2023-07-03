import {Box, Tab, Tabs, Typography} from "@mui/material";
import React, {useEffect, useState} from "react";
import {useFeedbackStore} from "../Common/feedbackStore";
import {FeedbackCard} from "../../Feedbacks/FeedbackCard";
import {VideoPlayer} from "../Common/VideoPlayer";

type Props = {
    studentId: number;
    presentationUrl: string
}

export const StudentProfileTabs: React.FC<Props> = ({studentId, presentationUrl}) => {
    const feedbacksAboutStudent = useFeedbackStore(state => state.feedbacksAboutStudent);
    const getFeedbacksAboutStudent = useFeedbackStore(state => state.getFeedbacksAboutStudent);

    useEffect(() => {
        if (studentId) {
            getFeedbacksAboutStudent(studentId);
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
        const { children, value, index, ...other } = props;

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
                        <Tab label="Presentation" {...a11yProps(1)} />
                    </Tabs>
                </Box>
                <TabPanel value={value} index={0}>
                    {feedbacksAboutStudent.map(feedback => (
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
                    <Box sx={{mt: 2}}>
                        <VideoPlayer presentationUrl={presentationUrl}/>
                    </Box>
                </TabPanel>
            </Box>
        </>
    )
}