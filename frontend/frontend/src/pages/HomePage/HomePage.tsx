import "./HomePage.css";

const HomePage = () => {
    return (
        <div className={"homepage"}>
            <div className={"overlay"}/>
            <h1>Welcome on board!</h1>
            <section className="recruiter-message">
                <h2>Dear Recruiter</h2>
                <p>
                    I hope you enjoy exploring this project as much as I enjoyed building it! I've done my best to make
                    everything clean, intuitive, and functional.
                </p>
                <p>
                    If you have any questions, feedback, or just want to say hi –
                    feel free to reach out via phone, email, or LinkedIn - information is at the bottom of the page.
                </p>
                <p>And for now... see you on board!</p>
            </section>
            <h2>Sit back, relax and enjoy your work!</h2>
        </div>
    );
}

export default HomePage;