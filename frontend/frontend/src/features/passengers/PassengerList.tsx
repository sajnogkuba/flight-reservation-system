import { useEffect, useState } from "react";
import { getAllPassengers } from "../../services/passengerService.ts";
import ExpandableList from "../../components/ExpandableList/ExpandableList";
import Button from "../../components/Button/Button.tsx";
import "./PassengerList.css";
import {Passenger} from "../../types/Passenger.ts";


const PassengerList = () => {
    const [passengers, setPassengers] = useState<Passenger[]>([]);

    useEffect(() => {
        getAllPassengers()
            .then((res) => setPassengers(res.data))
            .catch((err) => console.error(err));
    }, []);

    return (
        <ExpandableList
            items={passengers}
            keyExtractor={(p) => p.id}
            renderHeader={(p) => <p>{p.firstName} {p.lastName}</p>}
            renderDetails={(p) => (
                <>
                    <p>Passenger ID: {p.id}</p>
                    <p>First name: {p.firstName}</p>
                    <p>Last name: {p.lastName}</p>
                    <p>Email: {p.email}</p>
                    <p>Phone: {p.phoneNumber}</p>
                    <div className="buttons-div">
                        <Button label="Update" onClick={() => {}} />
                        <Button label="Delete" onClick={() => {}} />
                    </div>
                </>
            )}
        />
    );
};

export default PassengerList;
