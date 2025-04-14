import { ReactNode, useState } from "react";
import ExpandableListItem from "../ExpandableListItem/ExpandableListItem";
import "./ExpandableList.css";

interface Props<T> {
    items: T[];
    renderHeader: (item: T) => ReactNode;
    renderDetails: (item: T) => ReactNode;
    keyExtractor?: (item: T, index: number) => string | number;
}

const ExpandableList = <T,>({
                                items,
                                renderHeader,
                                renderDetails,
                                keyExtractor,
                            }: Props<T>) => {
    const [expandedIndex, setExpandedIndex] = useState<number | null>(null);

    const toggle = (index: number) => {
        setExpandedIndex(prev => (prev === index ? null : index));
    };

    return (
        <div className="list">
            {items.map((item, index) => (
                <ExpandableListItem
                    key={keyExtractor?.(item, index) ?? index}
                    isExpanded={expandedIndex === index}
                    onToggle={() => toggle(index)}
                    header={renderHeader(item)}
                >
                    {renderDetails(item)}
                </ExpandableListItem>
            ))}
        </div>
    );
};

export default ExpandableList;
