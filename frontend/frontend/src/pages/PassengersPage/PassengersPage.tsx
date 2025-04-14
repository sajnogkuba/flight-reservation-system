import "./PassengersPage.css";
import PassengerList from "../../features/passengers/PassengerList.tsx";
import Button from "../../components/Button/Button.tsx";

const PassengersPage = () => {
    return (
        <div className={"passengers-page"}>
            <div className={"overlay"}/>
            <h1>Passengers</h1>
            <section className="passenger-list">
                <h2>Passengers List</h2>
                <div className={"margin-bottom-10px"}>
                    <Button label={"Add passenger"} onClick={() => {}} />
                </div>
                    <PassengerList/>
            </section>
        </div>
    );
}

export default PassengersPage;