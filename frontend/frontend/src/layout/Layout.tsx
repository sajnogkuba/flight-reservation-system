import Navbar from "./Navbar/Navbar.tsx";
import {Outlet} from "react-router-dom";
import Footer from "./Footer/Footer.tsx";


const Layout = () => {
    return(
        <>
            <Navbar/>
            <main className={"main-content"}>
                <Outlet />
            </main>
            <Footer/>
        </>
    );
}

export default Layout;