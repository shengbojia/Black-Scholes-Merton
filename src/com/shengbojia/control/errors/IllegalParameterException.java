package com.shengbojia.control.errors;

public class IllegalParameterException extends AbstractParameterException {
    private String parameterName;
    private String whatIsValid;

    public IllegalParameterException(String parameterName, String whatIsValid) {
        super(parameterName);

        this.whatIsValid = whatIsValid;
    }

    @Override
    public String toString() {
        return parameterName + " must be " + whatIsValid + "!";
    }
}
