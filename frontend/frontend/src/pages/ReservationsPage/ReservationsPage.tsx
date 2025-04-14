import CustomButton from "../../components/Button/CustomButton.tsx";
import ReservationsList from "../../features/reservations/ReservationsList.tsx";
import "./ReservationsPage.css"

const ReservationsPage = () => {
    return (
        <div className={"reservations-page"}>
            <div className={"overlay"}/>
            <h1>Reservations</h1>
            <section className="reservations-list">
                <h2>Reservations List</h2>
                <div className={"margin-bottom-10px"}>
                    <CustomButton label={"Add reservation"} onClick={() => {}} />
                </div>
                <ReservationsList />
            </section>
        </div>
    );
}

export default ReservationsPage;