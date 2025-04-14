import CustomButton from "../../components/Button/CustomButton.tsx";
import ReservationsList from "../../features/reservations/ReservationsList.tsx";
import "./ReservationsPage.css"
import Form, {FormField} from "../../components/Form/Form.tsx";
import {useState} from "react";
import {createReservation} from "../../services/reservationsService.ts";

const reservationsFormFields: FormField[] = [
    { name: "reservationNumber", label: "Reservation Number: ", type: "number" },
    { name: "seatNumber", label: "Seat Number", type: "text" },
    { name: "alreadyDeparted", label: "Already departed", type: "boolean" },
    { name: "passengerId", label: "passenger ID", type: "number" },
    { name: "flightNumber", label: "Flight Number", type: "text" }
];
const ReservationsPage = () => {
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
            await createReservation(values);
            setFormError(null);
            closeForm();
        } catch (error: any) {
            const backendData = error?.response?.data;

            if (backendData && typeof backendData === "object") {
                if (backendData.error && typeof backendData.error === "string") {
                    setFormError({ general: backendData.error });
                } else if (Object.values(backendData).every(v => typeof v === "string")) {
                    setFormError(backendData);
                } else {
                    setFormError({ general: "Unknown error occurred." });
                }
            } else {
                setFormError({ general: "Something went wrong. Please try again." });
            }
        }
    };
    return (
        <div className={"reservations-page"}>
            <div className={"overlay"}/>
            <h1>Reservations</h1>
            <section className="reservations-list">
                <h2>Reservations List</h2>
                <div className="margin-bottom-10px">
                    {!shouldRenderForm ? (
                        <CustomButton label="Add Reservation" onClick={openForm}/>
                    ) : (
                        <div className="wrapper">
                            <Form
                                fields={reservationsFormFields}
                                onSubmit={handleSubmit}
                                onCancel={closeForm}
                                isVisible={formVisible}
                                error={formError}
                            />
                        </div>
                    )}
                </div>
                <ReservationsList/>
            </section>
        </div>
    );
}

export default ReservationsPage;