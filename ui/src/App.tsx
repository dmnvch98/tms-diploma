import React from 'react';
import {Route, Routes} from 'react-router-dom';
import {SignUpFirst} from "./pages/SignUp/SignUpFirst";
import {createTheme, ThemeProvider} from "@mui/material";
import {SignUpSecond} from "./pages/SignUp/SignUpSecond";
import {SignUpThird} from "./pages/SignUp/SignUpThird";
import {StudentProfile} from "./pages/Profile/StudentProfile";
import {TutorProfile} from "./pages/Profile/TutorProfile";
import {SignIn} from "./pages/SignIn/SignIn";
import {MyProfile} from "./pages/Profile/MyProfile";

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
                <Route path={'/reg'} element={<SignUpFirst/>}/>
                <Route path={'/reg2'} element={<SignUpSecond/>}/>
                <Route path={'/reg3'} element={<SignUpThird/>}/>
                <Route path={'/students/:id'} element={<StudentProfile/>}/>
                <Route path={'/tutors/:id'} element={<TutorProfile/>}/>
                <Route path={'/sign-in'} element={<SignIn/> }/>
                <Route path={'/my-profile'} element={<MyProfile/>}/>
            </Routes>
        </ThemeProvider>
    );
}

export default App;
