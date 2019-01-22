package com.shengbojia.control.errors;

abstract public class AbstractParameterException extends Throwable {
    private String parameterName;

    public AbstractParameterException(String parameterName) {
        this.parameterName = parameterName;
    }

    @Override
    public String toString() {
        return parameterName + " field is invalid!";
    }
}
