import { useEffect, useState } from "react";
import {deletePassenger, getAllPassengers} from "../../services/passengerService.ts";
import ExpandableList from "../../components/ExpandableList/ExpandableList";
import CustomButton from "../../components/Button/CustomButton.tsx";
import "./PassengerList.css";
import {Passenger} from "../../types/Passenger.ts";


const PassengerList = () => {
    const [passengers, setPassengers] = useState<Passenger[]>([]);

    useEffect(() => {
        fetchPassengers();
    }, []);

    const fetchPassengers = () => {
        getAllPassengers()
            .then((res) => setPassengers(res.data))
            .catch((err) => console.error(err));
    };

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
                        <CustomButton label="Update" onClick={() => {}} />
                        <CustomButton label="Delete" onClick={() => {
                            deletePassenger(p.id).then(() => fetchPassengers())
                        }} />
                    </div>
                </>
            )}
        />
    );
};

export default PassengerList;
