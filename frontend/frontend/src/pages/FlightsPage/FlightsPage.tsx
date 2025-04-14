import "./FlightsPage.css"
import Button from "../../components/Button/Button.tsx";
import FlightsList from "../../features/flights/FlightsList.tsx";

const FlightsPage = () => {
    return (
        <div className={"flights-page"}>
            <div className={"overlay"}/>
            <h1>Flights</h1>
            <section className="flights-list">
                <h2>Flights List</h2>
                <div className={"margin-bottom-10px"}>
                    <Button label={"Add flight"} onClick={() => {}} />
                </div>
                <FlightsList />
            </section>
        </div>
    );
}

export default FlightsPage;