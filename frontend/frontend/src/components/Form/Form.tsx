import React, { useState } from "react";
import CustomButton from "../Button/CustomButton.tsx";
import "./Form.css";

export type FieldType = "text" | "email" | "number" | "boolean" | "select";

export interface FormField {
    name: string;
    label: string;
    type: FieldType;
    options?: string[];
}

interface Props {
    fields: FormField[];
    onSubmit: (values: Record<string, any>) => void;
    onCancel: () => void;
    initialValues?: Record<string, any>;
    isVisible: boolean;
    error?: string|null;
}

const Form = ({ fields, onSubmit, onCancel, initialValues = {}, isVisible, error }: Props) => {
    const [formState, setFormState] = useState<Record<string, any>>(
        fields.reduce((acc, field) => {
            acc[field.name] = initialValues[field.name] ?? (field.type === "boolean" ? false : "");
            return acc;
        }, {} as Record<string, any>)
    );

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value, type } = e.target;

        if (type === "checkbox") {
            const target = e.target as HTMLInputElement;
            setFormState(prev => ({
                ...prev,
                [name]: target.checked
            }));
        } else {
            setFormState(prev => ({
                ...prev,
                [name]: value
            }));
        }
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        const transformed = { ...formState };

        if (
            Array.isArray(formState.availableSeats) === false &&
            typeof formState.availableSeats === "string"
        ) {
            transformed.availableSeats = formState.availableSeats
                .split(",")
                .map((s: string) => s.trim())
                .filter(Boolean);
        }

        onSubmit(transformed);
    };

    return (
        <form onSubmit={handleSubmit} className={`entity-form ${isVisible ? "show" : ""}`}>
            {error && <div className="form-error">{error}</div>}
            {fields.map(field => {
                if (field.type === "boolean") {
                    return (
                        <label key={field.name}>
                            <input
                                type="checkbox"
                                name={field.name}
                                checked={formState[field.name]}
                                onChange={handleChange}
                            />
                            {field.label}
                        </label>
                    );
                }

                if (field.type === "select") {
                    return (
                        <label key={field.name}>
                            {field.label}
                            <select
                                name={field.name}
                                value={formState[field.name]}
                                onChange={handleChange}
                            >
                                {field.options?.map(opt => (
                                    <option key={opt} value={opt}>
                                        {opt}
                                    </option>
                                ))}
                            </select>
                        </label>
                    );
                }

                return (
                    <input
                        key={field.name}
                        name={field.name}
                        placeholder={field.label}
                        type={field.type}
                        value={formState[field.name]}
                        onChange={handleChange}
                    />
                );
            })}

            <div className="form-buttons">
                <CustomButton label="Submit" onClick={() => {}} type="submit" />
                <CustomButton label="Cancel" onClick={onCancel} />
            </div>
        </form>
    );
};

export default Form;
