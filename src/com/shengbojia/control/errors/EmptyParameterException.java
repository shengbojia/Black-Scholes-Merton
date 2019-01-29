package com.shengbojia.control.errors;

/**
 * Signals that the user has left the input blank.
 */
public class EmptyParameterException extends AbstractParameterException {

    public EmptyParameterException(String parameterName) {
        super(parameterName);
    }

    @Override
    public String toString() {
        return super.getParameterName() + " field is empty!";
    }
}
