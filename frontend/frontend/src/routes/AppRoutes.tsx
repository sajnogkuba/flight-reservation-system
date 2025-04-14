import {Route, Routes} from "react-router-dom";
import HomePage from "../pages/HomePage/HomePage.tsx";
import Layout from "../layout/Layout.tsx";
import NotFoundPage from "../pages/NotFoundPage.tsx";
import PassengersPage from "../pages/PassengersPage/PassengersPage.tsx";


const AppRoutes = () => {
    return (
        <Routes>
            <Route path="/" element={<Layout />}>
                <Route index element={<HomePage />} />
                <Route path="/passengers" element={<PassengersPage />} />
            </Route>
            <Route path="*" element={<NotFoundPage />} />
        </Routes>
    );
}

export default AppRoutes;