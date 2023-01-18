import React from 'react';
import {Route, Routes} from 'react-router-dom';
import {SignUpFirst} from "./pages/SignUp/SignUpFirst";
import {createTheme, ThemeProvider} from "@mui/material";
import {SignUpSecond} from "./pages/SignUp/SignUpSecond";
import {SignUpThird} from "./pages/SignUp/SignUpThird";
import {CommonProfile} from "./pages/Profile/CommonProfile";

function App() {
    const theme = createTheme({
        typography: {
            fontFamily: [
                'Montserrat',
            ].join(','),
        },
    });

    return (
        <ThemeProvider theme={theme}>
            <Routes>
                <Route path={'/reg'} element={<SignUpFirst/>}/>
                <Route path={'/reg2'} element={<SignUpSecond/>}/>
                <Route path={'/reg3'} element={<SignUpThird/>}/>
                <Route path={'/profile'} element={<CommonProfile/>}/>
            </Routes>
        </ThemeProvider>
    );
}

export default App;
