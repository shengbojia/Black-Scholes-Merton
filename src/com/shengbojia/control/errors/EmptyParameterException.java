package com.shengbojia.control.errors;

public class EmptyParameterException extends AbstractParameterException {

    public EmptyParameterException(String parameterName) {
        super(parameterName);
    }

    @Override
    public String toString() {
        return super.getParameterName() + " field is empty!";
    }
}
