import "./Navbar.css";
import logo from "../../assets/logo.png";
import {Link} from "react-router-dom";
import Separator from "../../components/Separator/Separator.tsx";

const Navbar = () => {
    return (
        <div className={"shadow"}>
            <Separator/>
            <div className={"container"}>
                <img src={logo} alt="Logo" className="logo"/>
                <nav>
                    <ul>
                        <li><Link to={"/passengers"}>Passengers</Link></li>
                        <li><Link to={"/flights"}>Flights</Link></li>
                        <li><Link to={"/reservations"}>Reservations</Link></li>
                    </ul>
                </nav>

            </div>
            <Separator/>
        </div>
    );
}

export default Navbar;