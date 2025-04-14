import { useEffect, useState } from "react";
import { getAllFlights, formatDuration } from "../../services/flightsService.ts";
import ExpandableList from "../../components/ExpandableList/ExpandableList";
import CustomButton from "../../components/Button/CustomButton.tsx";
import {Flight} from "../../types/Flight.ts";


const PassengerList = () => {
    const [flights, setFlights] = useState<Flight[]>([]);

    useEffect(() => {
        getAllFlights()
            .then((res) => setFlights(res.data))
            .catch((err) => console.error(err));
    }, []);

    return (
        <ExpandableList
            items={flights}
            keyExtractor={(f) => f.flightNumber}
            renderHeader={(f) => <p>{f.flightNumber}</p>}
            renderDetails={(f) => (
                <>
                    <p>Flight number: {f.flightNumber}</p>
                    <p>Route: {f.placeOfDeparture} ----- {f.placeOfArrival} ({f.isOnWayFlight ? "one-way flight" : "round trip"})</p>
                    <p>Duration: {formatDuration(f.flightDuration)}</p>
                    <p>Available seats: {f.availableSeats.join(", ")}</p>
                    <div className="buttons-div">
                        <CustomButton label="Update" onClick={() => {}} />
                        <CustomButton label="Delete" onClick={() => {}} />
                    </div>
                </>
            )}
        />
    );
};

export default PassengerList;
