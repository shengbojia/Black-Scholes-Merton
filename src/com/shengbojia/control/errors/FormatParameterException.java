package com.shengbojia.control.errors;

/**
 * Signals that the user has inputted data that is not in the string format of a double.
 */
public class FormatParameterException extends AbstractParameterException {

    public FormatParameterException(String parameterName) {
        super(parameterName);
    }

    @Override
    public String toString() {
        return "Please enter a valid decimal value for the " + super.getParameterName() + " field.";
    }
}
