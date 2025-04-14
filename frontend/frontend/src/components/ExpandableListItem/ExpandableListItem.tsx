import { ReactNode, useState } from "react";
import "./ExpandableListItem.css";

interface Props {
    header: ReactNode;
    children: ReactNode;
}

const ExpandableListItem = ({ header, children }: Props) => {
    const [isHovered, setIsHovered] = useState(false);

    return (
        <div
            className={`list-card ${isHovered ? "expanded" : ""}`}
            onMouseEnter={() => setIsHovered(true)}
            onMouseLeave={() => setIsHovered(false)}
        >
            <div className="flex-card">
                {header}
                <span className={`chevron ${isHovered ? "open" : ""}`}>⌄</span>
            </div>
            <div className={`details ${isHovered ? "show" : ""}`}>
                {children}
            </div>
        </div>
    );
};

export default ExpandableListItem;
