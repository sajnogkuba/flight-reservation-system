import { ReactNode } from "react";
import "./ExpandableListItem.css";

interface Props {
    isExpanded: boolean;
    onToggle: () => void;
    header: ReactNode;
    children: ReactNode;
}

const ExpandableListItem = ({ isExpanded, onToggle, header, children }: Props) => {
    return (
        <div
            className={`list-card ${isExpanded ? "expanded" : ""}`}
            onClick={onToggle}
        >
            <div className="flex-card">
                {header}
                <span className={`chevron ${isExpanded ? "open" : ""}`}>⌄</span>
            </div>
            <div className={`details ${isExpanded ? "show" : ""}`}>
                {children}
            </div>
        </div>
    );
};

export default ExpandableListItem;
