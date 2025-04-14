import { useEffect, useState } from "react";
import { deletePassenger, getAllPassengers, updatePassenger } from "../../services/passengerService.ts";
import ExpandableList from "../../components/ExpandableList/ExpandableList";
import CustomButton from "../../components/Button/CustomButton.tsx";
import "./PassengerList.css";
import { Passenger } from "../../types/Passenger.ts";

const PassengerList = () => {
    const [passengers, setPassengers] = useState<Passenger[]>([]);
    const [editingId, setEditingId] = useState<number | null>(null);
    const [editedPassenger, setEditedPassenger] = useState<Partial<Passenger>>({});

    useEffect(() => {
        const intervalId = setInterval(() => {
            fetchPassengers();
        }, 1000);

        return () => clearInterval(intervalId);
    }, []);

    const fetchPassengers = () => {
        getAllPassengers()
            .then((res) => setPassengers(res.data))
            .catch((err) => console.error(err));
    };

    const handleEditClick = (p: Passenger) => {
        setEditingId(p.id);
        setEditedPassenger(p);
    };

    const handleSaveClick = async () => {
        if (editingId != null) {
            try {
                await updatePassenger(editingId, editedPassenger);
                setEditingId(null);
                setEditedPassenger({});
                fetchPassengers();
            } catch (e) {
                console.error("Update failed", e);
            }
        }
    };

    return (
        <ExpandableList
            items={passengers}
            keyExtractor={(p) => p.id}
            renderHeader={(p) => <p>{p.firstName} {p.lastName}</p>}
            renderDetails={(p) => {
                const isEditing = editingId === p.id;

                return (
                    <>
                        <p>ID:{p.id}</p>
                        <p>
                            First name:{" "}
                            {isEditing ? (
                                <input
                                    value={editedPassenger.firstName || ""}
                                    onChange={(e) =>
                                        setEditedPassenger({ ...editedPassenger, firstName: e.target.value })
                                    }
                                />
                            ) : (
                                p.firstName
                            )}
                        </p>
                        <p>
                            Last name:{" "}
                            {isEditing ? (
                                <input
                                    value={editedPassenger.lastName || ""}
                                    onChange={(e) =>
                                        setEditedPassenger({ ...editedPassenger, lastName: e.target.value })
                                    }
                                />
                            ) : (
                                p.lastName
                            )}
                        </p>
                        <p>
                            Email:{" "}
                            {isEditing ? (
                                <input
                                    value={editedPassenger.email || ""}
                                    onChange={(e) =>
                                        setEditedPassenger({ ...editedPassenger, email: e.target.value })
                                    }
                                />
                            ) : (
                                p.email
                            )}
                        </p>
                        <p>
                            Phone:{" "}
                            {isEditing ? (
                                <input
                                    value={editedPassenger.phoneNumber || ""}
                                    onChange={(e) =>
                                        setEditedPassenger({ ...editedPassenger, phoneNumber: e.target.value })
                                    }
                                />
                            ) : (
                                p.phoneNumber
                            )}
                        </p>

                        <div className="buttons-div">
                            {isEditing ? (
                                <CustomButton label="Save" onClick={handleSaveClick} />
                            ) : (
                                <CustomButton label="Update" onClick={() => handleEditClick(p)} />
                            )}
                            <CustomButton
                                label="Delete"
                                onClick={() => {
                                    deletePassenger(p.id).then(() => fetchPassengers());
                                }}
                            />
                        </div>
                    </>
                );
            }}
        />
    );
};

export default PassengerList;
