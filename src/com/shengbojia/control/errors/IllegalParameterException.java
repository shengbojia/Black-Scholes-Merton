package com.shengbojia.control.errors;

public class IllegalParameterException extends AbstractParameterException {
    private String whatIsValid;

    public IllegalParameterException(String parameterName, String whatIsValid) {
        super(parameterName);

        this.whatIsValid = whatIsValid;
    }

    @Override
    public String toString() {
        return super.getParameterName() + " must be " + whatIsValid + "!";
    }
}
