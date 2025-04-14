import "./PassengersPage.css";
import PassengerList from "../../features/passengers/PassengerList.tsx";
import CustomButton from "../../components/Button/CustomButton.tsx";
import Form, { FormField } from "../../components/Form/Form.tsx";
import { useState } from "react";
import { createPassenger } from "../../services/passengerService.ts";

const passengersFormFields: FormField[] = [
    { name: "passengerId", label: "Passenger ID: ", type: "number" },
    { name: "firstName", label: "First Name", type: "text" },
    { name: "lastName", label: "Last Name", type: "text" },
    { name: "email", label: "Email", type: "email" },
    { name: "phoneNumber", label: "Phone Number", type: "text" }
];

const PassengersPage = () => {
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
            await createPassenger(values);
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
        <div className="passengers-page">
            <div className="overlay" />
            <h1>Passengers</h1>
            <section className="passenger-list">
                <h2>Passengers List</h2>
                <div className="margin-bottom-10px">
                    {!shouldRenderForm ? (
                        <CustomButton label="Add passenger" onClick={openForm} />
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
                <PassengerList />
            </section>
        </div>
    );
};

export default PassengersPage;
