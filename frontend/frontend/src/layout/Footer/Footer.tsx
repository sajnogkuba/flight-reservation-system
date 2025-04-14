import "./Footer.css";
import Separator from "../../components/Separator/Separator.tsx";
import ContactInfo from "../../components/ContactInfo/ContactInfo.tsx";

const Footer = () => {
    return(
        <div className={"container-sticky"}>
            <Separator/>
            <div className={"footer-container"}>
                <footer className={"footer"}>
                    © {new Date().getFullYear()} LOT Polish Airlines - Summer Internship Project
                </footer>
                <ContactInfo/>
            </div>
            <Separator/>
        </div>
    );
}

export default Footer;