import { useEffect, useState } from "react";
import { Reservation } from "../../types/Reservation.ts";
import ExpandableList from "../../components/ExpandableList/ExpandableList.tsx";
import CustomButton from "../../components/Button/CustomButton.tsx";
import {
    getAllReservations,
    updateReservation,
    deleteReservation,
} from "../../services/reservationsService.ts";

const ReservationsList = () => {
    const [reservations, setReservations] = useState<Reservation[]>([]);
    const [editingId, setEditingId] = useState<number | null>(null);
    const [editedReservation, setEditedReservation] = useState<Partial<Reservation> & { passengerId?: number; flightNumber?: string }>({});

    useEffect(() => {
        const intervalId = setInterval(() => {
            fetchReservations();
        }, 1000);

        return () => clearInterval(intervalId);
    }, []);

    const fetchReservations = () => {
        getAllReservations()
            .then((res) => setReservations(res.data))
            .catch((err) => console.error(err));
    };

    const handleEditClick = (r: Reservation) => {
        setEditingId(r.reservationNumber);
        setEditedReservation({
            reservationNumber: r.reservationNumber,
            seatNumber: r.seatNumber,
            alreadyDeparted: r.alreadyDeparted,
            passengerId: r.passenger.id,
            flightNumber: r.flight.flightNumber,
        });
    };

    const handleSaveClick = async () => {
        if (editingId != null) {
            try {
                await updateReservation(editingId, editedReservation);
                setEditingId(null);
                setEditedReservation({});
                fetchReservations();
            } catch (e) {
                console.error("Update failed", e);
            }
        }
    };

    return (
        <ExpandableList
            items={reservations}
            keyExtractor={(r) => r.reservationNumber}
            renderHeader={(r) => <p>{r.reservationNumber}</p>}
            renderDetails={(r) => {
                const isEditing = editingId === r.reservationNumber;

                return (
                    <>
                        <p>
                            Reservation number: {r.reservationNumber} - (
                            {isEditing ? (
                                <select
                                    value={editedReservation.alreadyDeparted ? "true" : "false"}
                                    onChange={(e) =>
                                        setEditedReservation({
                                            ...editedReservation,
                                            alreadyDeparted: e.target.value === "true",
                                        })
                                    }
                                >
                                    <option value="false">Coming</option>
                                    <option value="true">Departed</option>
                                </select>
                            ) : r.alreadyDeparted ? (
                                "Departed"
                            ) : (
                                "Coming"
                            )}
                            )
                        </p>

                        <p>
                            Seat number:{" "}
                            {isEditing ? (
                                <input
                                    value={editedReservation.seatNumber || ""}
                                    onChange={(e) =>
                                        setEditedReservation({
                                            ...editedReservation,
                                            seatNumber: e.target.value,
                                        })
                                    }
                                />
                            ) : (
                                r.seatNumber
                            )}
                        </p>

                        <p>
                            Flight:{" "}
                            {isEditing ? (
                                <input
                                    value={editedReservation.flightNumber || ""}
                                    onChange={(e) =>
                                        setEditedReservation({
                                            ...editedReservation,
                                            flightNumber: e.target.value,
                                        })
                                    }
                                />
                            ) : (
                                r.flight.flightNumber
                            )}
                        </p>

                        <p>
                            Passenger ID:{" "}
                            {isEditing ? (
                                <input
                                    value={editedReservation.passengerId?.toString() || ""}
                                    onChange={(e) =>
                                        setEditedReservation({
                                            ...editedReservation,
                                            passengerId: parseInt(e.target.value, 10),
                                        })
                                    }
                                />
                            ) : (
                                `${r.passenger.firstName} ${r.passenger.lastName} (ID: ${r.passenger.id})`
                            )}
                        </p>

                        <div className="buttons-div">
                            {isEditing ? (
                                <CustomButton label="Save" onClick={handleSaveClick} />
                            ) : (
                                <CustomButton label="Update" onClick={() => handleEditClick(r)} />
                            )}
                            <CustomButton
                                label="Delete"
                                onClick={() =>
                                    deleteReservation(r.reservationNumber).then(() => fetchReservations())
                                }
                            />
                        </div>
                    </>
                );
            }}
        />
    );
};

export default ReservationsList;
