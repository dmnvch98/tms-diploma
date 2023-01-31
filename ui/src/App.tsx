import React from 'react';
import {Route, Routes} from 'react-router-dom';
import {SignUpFirst} from "./pages/SignUp/SignUpFirst";
import {createTheme, ThemeProvider} from "@mui/material";
import {SignUpSecond} from "./pages/SignUp/SignUpSecond";
import {SignUpThird} from "./pages/SignUp/SignUpThird";
import {StudentProfile} from "./pages/Profile/StudentProfile";
import {TutorProfile} from "./pages/Profile/TutorProfile";
import {SignIn} from "./pages/Sign In/SignIn";
import {MyTutorProfile} from "./pages/Profile/MyTutorProfile";
import {MyStudentProfile} from "./pages/Profile/MyStudentProfile";
import {Loading} from "./Components/Loading";

function App() {
    const theme = createTheme({
        typography: {
            fontFamily: [
                'Montserrat',
            ].join(','),
        },
        palette: {
            primary: {
                main: '#a3ccbe',
                light: '#a3ccbe',
                dark: '#44734b',
            },
            secondary: {
                main: '#f50057',
            },
        },
    });

        return (
            <ThemeProvider theme={theme}>
                <Routes>
                    <Route path={'/sign-up'} element={<SignUpFirst/>}/>
                    <Route path={'/sign-up2'} element={<SignUpSecond/>}/>
                    <Route path={'/sign-up3'} element={<SignUpThird/>}/>
                    <Route path={'/sign-in'} element={<SignIn/> }/>
                    <Route path={'/students/:id'} element={<StudentProfile/>}/>
                    <Route path={'/tutors/:id'} element={<TutorProfile/>}/>
                    <Route path={'/my-tutor-profile'} element={<MyTutorProfile/>}/>
                    <Route path={'/my-student-profile'} element={<MyStudentProfile/>}/>
                    <Route path={'/loading'} element={<Loading/>}/>
                </Routes>
            </ThemeProvider>
        );
}

export default App;
