import "./ContactInfo.css";

const ContactInfo = () => {
    return(
        <div className={"contact-info"}>
            <ul>
                <li>Feel free to contact: </li>
                <li>Jakub Sajnóg</li>
                <li>
                    tel:{" "}
                    <a href="tel:+48603337347" className="contact-link">
                        +48 603 337 347
                    </a>
                </li>
                <li>
                    email:{" "}
                    <a href="mailto:jakubsajnog@gmail.com" className="contact-link">
                        jakubsajnog@gmail.com
                    </a>
                </li>
            </ul>
        </div>
    );
}

export default ContactInfo;