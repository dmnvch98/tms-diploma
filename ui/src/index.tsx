import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import {BrowserRouter} from "react-router-dom";
import axios, {Axios} from "axios";
import {AxiosError} from "axios";

const getRefreshToken = async (): Promise<boolean> => {
    try {
        const response = await axios.post("http://localhost:8080/api/v1/auth/refresh",
            null, {withCredentials: true})
        return response.status == 200;

    } catch (e: unknown) {
        const error = e as AxiosError;
        console.log(error.message);
    }
    return false;
}
let refreshTokenPromise: any // this holds any in-progress token refresh requests

axios.defaults.xsrfCookieName = 'XSRF';
axios.defaults.xsrfHeaderName = 'X-XSRF';


axios.interceptors.response.use(r => r, error => {
    if (error.config && error.response && error.response.status === 401) {
        if (!refreshTokenPromise) { // check for an existing in-progress request
            // if nothing is in-progress, start a new refresh token request
            refreshTokenPromise = getRefreshToken().then(() => {
                refreshTokenPromise = null // clear state
            })
        }

        return refreshTokenPromise.then(() => {
            return axios.request(error.config)
        })
    }
    return Promise.reject(error)
})
const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
);
root.render(
    <BrowserRouter>
        <App/>
    </BrowserRouter>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
