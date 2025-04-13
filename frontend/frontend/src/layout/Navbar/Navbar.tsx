import "./Navbar.css";
import logo from "../../assets/logo.png";
import {Link} from "react-router-dom";

const Navbar = () => {
    return (
        <div className={"shadow"}>
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
            <div className={"separator"}></div>
        </div>
    );
}

export default Navbar;