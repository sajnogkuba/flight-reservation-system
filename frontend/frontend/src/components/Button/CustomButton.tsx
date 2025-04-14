import "./Button.css";

type ButtonProps = {
    label: string;
    onClick: () => void;
    type?: "button" | "submit" | "reset";
};

const CustomButton = ({ label, onClick, type = "button" }: ButtonProps) => {
    return (
        <button className="custom-button" onClick={onClick} type={type}>
            {label}
        </button>
    );
};

export default CustomButton;
