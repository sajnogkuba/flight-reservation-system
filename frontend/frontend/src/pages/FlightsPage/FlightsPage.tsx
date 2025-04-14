import "./FlightsPage.css"
import CustomButton from "../../components/Button/CustomButton.tsx";
import FlightsList from "../../features/flights/FlightsList.tsx";

const FlightsPage = () => {
    return (
        <div className={"flights-page"}>
            <div className={"overlay"}/>
            <h1>Flights</h1>
            <section className="flights-list">
                <h2>Flights List</h2>
                <div className={"margin-bottom-10px"}>
                    <CustomButton label={"Add flight"} onClick={() => {}} />
                </div>
                <FlightsList />
            </section>
        </div>
    );
}

export default FlightsPage;