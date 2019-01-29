package com.shengbojia.control.errors;

/**
 * Abstract base class for all user input related exceptions thrown by the controller.
 */
public abstract class AbstractParameterException extends Throwable {
    private String parameterName;

    /**
     * Class constructor specifying which parameter at hand has invalid user input
     *
     * @param parameterName the name of the parameter that has invalid user input
     */
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
