import React, {useEffect} from 'react';
import {Route, Routes} from 'react-router-dom';
import {SignUpFirst} from "./pages/SignUp/SignUpFirst";
import {createTheme, ThemeProvider} from "@mui/material";
import {SignUpSecond} from "./pages/SignUp/SignUpSecond";
import {SignUpThird} from "./pages/SignUp/SignUpThird";
import {StudentProfile} from "./pages/Profile/Student/StudentProfile";
import {TutorProfile} from "./pages/Profile/Tutor/TutorProfile";
import {SignIn} from "./pages/Sign In/SignIn";
import {MyTutorProfile} from "./pages/Profile/Tutor/MyTutorProfile";
import {MyStudentProfile} from "./pages/Profile/Student/MyStudentProfile";
import {Loading} from "./Components/Loading";
import {FindTutor} from "./pages/FIndTutor/FindTutor";
import {EditStudentProfile} from "./pages/Profile/Edit/Student/EditStudentProfile";
import {EditTutorProfile} from "./pages/Profile/Edit/Tutor/EditTutorProfile";
import {TutorsFilter} from "./Components/FindTutor/TutorsFilter";
import {useProfileStore} from "./pages/Profile/profileStore";
import {AddAddress} from "./pages/Addresses/AddAddress";

function App() {
    const getMe = useProfileStore(state => state.getMe)
    const user = useProfileStore(state => state.loggedInUser);

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
            error: {
                main: '#e57373'
            }
        },
    });

    useEffect(() => {
        if (!user) {
            getMe();
        }
    }, [])

    return (
        <ThemeProvider theme={theme}>
            <Routes>
                <Route path={'/students/:id'} element={<StudentProfile/>}/>
                <Route path={'/tutors/:id'} element={<TutorProfile/>}/>
                <Route path={'/my-tutor-profile'} element={<MyTutorProfile/>}/>
                <Route path={'/my-student-profile'} element={<MyStudentProfile/>}/>
                <Route path={'/loading'} element={<Loading/>}/>
                <Route path={'/find-tutor'} element={<FindTutor/>}/>
                <Route path={'/edit-profile-student'} element={<EditStudentProfile/>}/>
                <Route path={'/edit-profile-tutor'} element={<EditTutorProfile/>}/>
                <Route path={'/filter'} element={<TutorsFilter/>}/>
                <Route path={'/sign-up'} element={<SignUpFirst/>}/>
                <Route path={'/sign-up2'} element={<SignUpSecond/>}/>
                <Route path={'/sign-up3'} element={<SignUpThird/>}/>
                <Route path={'/sign-in'} element={<SignIn/>}/>
                <Route path={'/add-address'} element={<AddAddress/>}/>
            </Routes>
        </ThemeProvider>
    )
}

export default App;
