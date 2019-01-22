package com.shengbojia.control.errors;

public class EmptyParameterException extends AbstractParameterException {
    private String parameterName;

    public EmptyParameterException(String parameterName) {
        super(parameterName);
    }

    @Override
    public String toString() {
        return parameterName + " field is empty!";
    }
}
