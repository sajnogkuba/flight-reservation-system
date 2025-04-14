import { useEffect, useState } from "react";
import { getAllFlights, formatDuration, deleteFlight, updateFlight } from "../../services/flightsService.ts";
import ExpandableList from "../../components/ExpandableList/ExpandableList";
import CustomButton from "../../components/Button/CustomButton.tsx";
import { Flight } from "../../types/Flight.ts";

const FlightsList = () => {
    const [flights, setFlights] = useState<Flight[]>([]);
    const [editingId, setEditingId] = useState<string | null>(null);
    const [editedFlight, setEditedFlight] = useState<Partial<Flight>>({});

    useEffect(() => {
        const intervalId = setInterval(() => {
            fetchFlights();
        }, 1000);

        return () => clearInterval(intervalId);
    }, []);

    const fetchFlights = () => {
        getAllFlights()
            .then((res) => setFlights(res.data))
            .catch((err) => console.error(err));
    };

    const handleEditClick = (f: Flight) => {
        setEditingId(f.flightNumber);
        setEditedFlight({
            flightNumber: f.flightNumber,
            placeOfDeparture: f.placeOfDeparture,
            placeOfArrival: f.placeOfArrival,
            flightDuration: f.flightDuration,
            isOnWayFlight: f.isOnWayFlight,
            availableSeats: f.availableSeats,
        });
    };

    const handleSaveClick = async () => {
        if (editingId) {
            try {
                await updateFlight(editingId, editedFlight);
                setEditingId(null);
                setEditedFlight({});
                fetchFlights();
            } catch (e) {
                console.error("Update failed", e);
            }
        }
    };

    return (
        <ExpandableList
            items={flights}
            keyExtractor={(f) => f.flightNumber}
            renderHeader={(f) => <p>{f.flightNumber}</p>}
            renderDetails={(f) => {
                const isEditing = editingId === f.flightNumber;

                return (
                    <>
                        <p>
                            Flight number: {f.flightNumber}
                        </p>
                        <p>
                            Route:{" "}
                            {isEditing ? (
                                <>
                                    <input
                                        value={editedFlight.placeOfDeparture || ""}
                                        onChange={(e) =>
                                            setEditedFlight({ ...editedFlight, placeOfDeparture: e.target.value })
                                        }
                                    />
                                    {" ----- "}
                                    <input
                                        value={editedFlight.placeOfArrival || ""}
                                        onChange={(e) =>
                                            setEditedFlight({ ...editedFlight, placeOfArrival: e.target.value })
                                        }
                                    />
                                    {" "}
                                    <select
                                        value={editedFlight.isOnWayFlight ? "true" : "false"}
                                        onChange={(e) =>
                                            setEditedFlight({ ...editedFlight, isOnWayFlight: e.target.value === "true" })
                                        }
                                    >
                                        <option value="true">one-way flight</option>
                                        <option value="false">round trip</option>
                                    </select>
                                </>
                            ) : (
                                `${f.placeOfDeparture} ----- ${f.placeOfArrival} (${f.isOnWayFlight ? "one-way flight" : "round trip"})`
                            )}
                        </p>
                        <p>
                            Duration:{" "}
                            {isEditing ? (
                                <input
                                    value={editedFlight.flightDuration || ""}
                                    onChange={(e) =>
                                        setEditedFlight({ ...editedFlight, flightDuration: e.target.value })
                                    }
                                />
                            ) : (
                                formatDuration(f.flightDuration)
                            )}
                        </p>
                        <p>
                            Available seats:{" "}
                            {isEditing ? (
                                <input
                                    value={editedFlight.availableSeats?.join(", ") || ""}
                                    onChange={(e) =>
                                        setEditedFlight({
                                            ...editedFlight,
                                            availableSeats: e.target.value
                                                .split(",")
                                                .map((s) => s.trim())
                                                .filter((s) => s !== ""),
                                        })
                                    }
                                />
                            ) : (
                                f.availableSeats.join(", ")
                            )}
                        </p>
                        <div className="buttons-div">
                            {isEditing ? (
                                <CustomButton label="Save" onClick={handleSaveClick} />
                            ) : (
                                <CustomButton label="Update" onClick={() => handleEditClick(f)} />
                            )}
                            <CustomButton
                                label="Delete"
                                onClick={() => {
                                    deleteFlight(f.flightNumber).then(() => fetchFlights());
                                }}
                            />
                        </div>
                    </>
                );
            }}
        />
    );
};

export default FlightsList;
