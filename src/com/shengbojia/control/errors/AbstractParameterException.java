package com.shengbojia.control.errors;

abstract public class AbstractParameterException extends Throwable {
    private String parameterName;

    public AbstractParameterException(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }

    @Override
    public String toString() {
        return parameterName + " field is invalid!";
    }
}
