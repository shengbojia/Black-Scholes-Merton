package com.shengbojia.control.errors;

public class FormatParameterException extends AbstractParameterException {

    public FormatParameterException(String parameterName) {
        super(parameterName);
    }

    @Override
    public String toString() {
        return "Please enter a valid decimal value for the " + super.getParameterName() + " field.";
    }
}
