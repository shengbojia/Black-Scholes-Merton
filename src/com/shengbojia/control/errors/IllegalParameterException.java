package com.shengbojia.control.errors;

/**
 * Signals that the user has inputted data which is not possible in the context of finance and stocks.
 */
public class IllegalParameterException extends AbstractParameterException {
    private String whatIsValid;

    /**
     * Class constructor specifying the name of the illegal parameter and the correct range for that parameter
     * @param parameterName the name of the parameter with an illegal value
     * @param whatIsValid the correct range of the parameter at hand
     */
    public IllegalParameterException(String parameterName, String whatIsValid) {
        super(parameterName);

        this.whatIsValid = whatIsValid;
    }

    @Override
    public String toString() {
        return super.getParameterName() + " must be " + whatIsValid + "!";
    }
}
