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
    error?: Record<string, string> | null;
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
            typeof formState.availableSeats === "string" &&
            !Array.isArray(formState.availableSeats)
        ) {
            transformed.availableSeats = formState.availableSeats
                .split(",")
                .map((s: string) => s.trim())
                .filter(Boolean);
        }

        onSubmit(transformed);
    };

    const renderFieldError = (name: string) => {
        if (error && error[name]) {
            return <p className="form-error">{error[name]}</p>;
        }
        return null;
    };

    return (
        <form onSubmit={handleSubmit} className={`entity-form ${isVisible ? "show" : ""}`}>
            {error?.general && (
                <div className="form-error general-error">
                    {error.general}
                </div>
            )}

            {fields.map(field => {
                const value = formState[field.name];

                if (field.type === "boolean") {
                    return (
                        <div key={field.name} className="form-field checkbox-field">
                            <label className="checkbox-inline">
                                <span>{field.label}</span>
                                <input
                                    type="checkbox"
                                    name={field.name}
                                    checked={value}
                                    onChange={handleChange}
                                />
                            </label>
                            {renderFieldError(field.name)}
                        </div>
                    );
                }

                if (field.type === "select") {
                    return (
                        <div key={field.name} className="form-field">
                            <label>
                                {field.label}
                                <select
                                    name={field.name}
                                    value={value}
                                    onChange={handleChange}
                                >
                                    {field.options?.map(opt => (
                                        <option key={opt} value={opt}>{opt}</option>
                                    ))}
                                </select>
                            </label>
                            {renderFieldError(field.name)}
                        </div>
                    );
                }

                return (
                    <div key={field.name} className="form-field">
                        <input
                            name={field.name}
                            placeholder={field.label}
                            type={field.type}
                            value={value}
                            onChange={handleChange}
                        />
                        {renderFieldError(field.name)}
                    </div>
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
