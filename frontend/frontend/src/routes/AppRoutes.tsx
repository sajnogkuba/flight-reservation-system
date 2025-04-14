import {Route, Routes} from "react-router-dom";
import HomePage from "../pages/HomePage/HomePage.tsx";
import Layout from "../layout/Layout.tsx";
import NotFoundPage from "../pages/NotFoundPage.tsx";


const AppRoutes = () => {
    return (
        <Routes>
            <Route path="/" element={<Layout />}>
                <Route index element={<HomePage />} />
            </Route>
            <Route path="*" element={<NotFoundPage />} />
        </Routes>
    );
}

export default AppRoutes;