import "./FlightsPage.css";
import CustomButton from "../../components/Button/CustomButton.tsx";
import FlightsList from "../../features/flights/FlightsList.tsx";
import Form, { FormField } from "../../components/Form/Form.tsx";
import { useState } from "react";
import { createFlight } from "../../services/flightsService.ts";

const passengersFormFields: FormField[] = [
    { name: "flightNumber", label: "Flight number: ", type: "text" },
    { name: "placeOfDeparture", label: "Place of Departure: ", type: "text" },
    { name: "placeOfArrival", label: "Place of Arrival: ", type: "text" },
    { name: "flightDuration", label: "Flight duration: ", type: "text" },
    { name: "isOnWayFlight", label: "On way flight: ", type: "boolean" },
    { name: "availableSeats", label: "Available seats: ", type: "text" },
];

const FlightsPage = () => {
    const [shouldRenderForm, setShouldRenderForm] = useState(false);
    const [formVisible, setFormVisible] = useState(false);
    const [formError, setFormError] = useState<Record<string, string> | null>(null);

    const openForm = () => {
        setShouldRenderForm(true);
        setTimeout(() => setFormVisible(true), 10);
    };

    const closeForm = () => {
        setFormVisible(false);
        setTimeout(() => setShouldRenderForm(false), 300);
    };

    const handleSubmit = async (values: Record<string, any>) => {
        try {
            await createFlight(values);
            setFormError(null);
            closeForm();
        } catch (error: any) {
            const backendData = error?.response?.data;

            if (backendData && typeof backendData === "object") {
                if (backendData.error && typeof backendData.error === "string") {
                    setFormError({ general: backendData.error });
                }
                else if (Object.values(backendData).every(v => typeof v === "string")) {
                    setFormError(backendData);
                }
                else {
                    setFormError({ general: "Unknown error occurred." });
                }
            } else {
                setFormError({ general: "Something went wrong. Please try again." });
            }
        }
    };

    return (
        <div className="flights-page">
            <div className="overlay" />
            <h1>Flights</h1>
            <section className="flights-list">
                <h2>Flights List</h2>
                <div className="margin-bottom-10px">
                    {!shouldRenderForm ? (
                        <CustomButton label="Add flight" onClick={openForm} />
                    ) : (
                        <div className="wrapper">
                            <Form
                                fields={passengersFormFields}
                                onSubmit={handleSubmit}
                                onCancel={closeForm}
                                isVisible={formVisible}
                                error={formError}
                            />
                        </div>
                    )}
                </div>
                <FlightsList />
            </section>
        </div>
    );
};

export default FlightsPage;
