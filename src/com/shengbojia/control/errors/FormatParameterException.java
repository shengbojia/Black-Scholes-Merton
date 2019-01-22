package com.shengbojia.control.errors;

public class FormatParameterException extends AbstractParameterException {
    private String parameterName;

    public FormatParameterException(String parameterName) {
        super(parameterName);
    }

    @Override
    public String toString() {
        return "Please enter a valid decimal value for the " + parameterName + " field.";
    }
}
