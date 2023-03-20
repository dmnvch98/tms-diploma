import {Navigate, Route} from "react-router-dom";
import {Component, ComponentType} from "react";

type PrivateRouteProps = {
    path: string;
    element: ComponentType<any>;
    isAuthenticated: boolean;
};

export const PrivateRoute = ({element: Component, isAuthenticated, ...rest}: PrivateRouteProps) => {
    return (
        <Route
            {...rest}
            element={
                isAuthenticated ? (
                    <Component/>
                ) : (
                    <Navigate to="/login" replace={true}/>
                )
            }
        />
    );
}