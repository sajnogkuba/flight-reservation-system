import "./Button.css";

type ButtonProps = {
    label: string;
    onClick: () => void;
};

const Button = ({ label, onClick }: ButtonProps) => {
    return (
        <button className="custom-button" onClick={onClick}>
            {label}
        </button>
    );
};

export default Button;