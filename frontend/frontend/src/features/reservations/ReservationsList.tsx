import {useEffect, useState} from "react";
import {Reservation} from "../../types/Reservation.ts";
import ExpandableList from "../../components/ExpandableList/ExpandableList.tsx";
import CustomButton from "../../components/Button/CustomButton.tsx";
import {getAllReservations} from "../../services/reservationsService.ts";


const ReservationsList = () => {
    const [reservations, setReservations] = useState<Reservation[]>([]);

    useEffect(() => {
        getAllReservations()
            .then((res) => setReservations(res.data))
            .catch((err) => console.error(err));
    }, []);

    return (
        <ExpandableList
            items={reservations}
            keyExtractor={(r) => r.reservationNumber}
            renderHeader={(r) => <p>{r.reservationNumber}</p>}
            renderDetails={(r) => (
                <>
                    <p>Reservation number: {r.reservationNumber} - ({r.alreadyDeparted ? "Departed" : "Coming"})</p>
                    <p>Seat number: {r.seatNumber}</p>
                    <p>Flight: {r.flight.flightNumber}</p>
                    <p>Passenger: {r.passenger.firstName} {r.passenger.lastName}</p>
                    <div className="buttons-div">
                        <CustomButton label="Update" onClick={() => {}} />
                        <CustomButton label="Delete" onClick={() => {}} />
                    </div>
                </>
            )}
        />
    );
};

export default ReservationsList;