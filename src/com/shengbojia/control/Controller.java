package com.shengbojia.control;

import com.shengbojia.calculator.BlackScholesCalculator;
import com.shengbojia.control.errors.AbstractParameterException;
import com.shengbojia.control.errors.EmptyParameterException;
import com.shengbojia.control.errors.FormatParameterException;
import com.shengbojia.control.errors.IllegalParameterException;
import com.shengbojia.view.ErrorWindow;
import com.shengbojia.view.InputFieldAndLabel;
import com.shengbojia.view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Controller class that handles communication between the GUI and the calculator.
 *
 * @author Shengbo Jia
 */
public class Controller implements ActionListener {
    private BlackScholesCalculator calculator;
    private MainWindow window;

    /**
     * Class constructor
     *
     * @param calculator the Black Scholes calculator that will handle the logic
     * @param window the main window of the gui
     */
    public Controller(BlackScholesCalculator calculator, MainWindow window) {
        this.calculator = calculator;
        this.window = window;
        window.addController(this);
    }

    /**
     * Invokes when the user tells the GUI to start calculations. Calls pricing methods in the calculator using
     * user inputted data.
     *
     * @param e the action event send from the GUI
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            BigDecimal stockPrice = convertInput(window.getInputPanel().getStock());
            BigDecimal strikePrice = convertInput(window.getInputPanel().getStrike());
            BigDecimal timeToMaturity = convertInput(window.getInputPanel().getMaturity());
            BigDecimal volatility = convertInput(window.getInputPanel().getVolatility());
            BigDecimal riskFreeRate = convertInput(window.getInputPanel().getRiskFree());

            BigDecimal callPrice = calculator.callPricing(stockPrice, strikePrice, timeToMaturity,
                    volatility, riskFreeRate);
            BigDecimal putPrice = calculator.putPricing(stockPrice, strikePrice, timeToMaturity,
                    volatility, riskFreeRate);

            String callOutput = NumberFormat.getCurrencyInstance().format(callPrice);
            String putOutput = NumberFormat.getCurrencyInstance().format(putPrice);

            window.getOutputPanel().getCallDisplay().setText(callOutput);
            window.getOutputPanel().getPutDisplay().setText(putOutput);

            // TODO: Consider checking for empty fields first

        } catch (AbstractParameterException e1) {
            new ErrorWindow(e1.toString());
        }
    }

    private double takeInput(InputFieldAndLabel input) throws AbstractParameterException {
        if (input.getInputField().getText().isBlank()) {
            throw new EmptyParameterException(input.getInputName());
        }

        double result;
        try {
            result = Double.parseDouble(input.getInputField().getText());
            return result;
        } catch (NumberFormatException e) {
            throw new FormatParameterException(input.getInputName());
        }
    }

    private BigDecimal convertInput(InputFieldAndLabel input) throws AbstractParameterException {
        double result = takeInput(input);
        String inputParamName = input.getInputName();

        if (result < -9999 || result > 9999) {
            throw new IllegalParameterException(inputParamName, "more reasonable");
        } else if (inputParamName.equals("Risk-free rate")) {
            if (result < -10) {
                throw new IllegalParameterException(inputParamName, "greater than -10");
            } else if (result > 10) {
                throw new IllegalParameterException(inputParamName, "less than 10");
            }
        } else if ((inputParamName.equals("Maturity") || inputParamName.equals("Volatility"))
                && result < 0) {
            throw new IllegalParameterException(inputParamName, "non-negative");
        } else if ((inputParamName.equals("Stock price") || inputParamName.equals("Strike price"))
                && result <= 0) {
            throw new IllegalParameterException(inputParamName, "positive");
        }

        if (inputParamName.equals("Risk-free rate") || inputParamName.equals("Volatility")) {
            return convertPercentInput(result);
        }
        return BigDecimal.valueOf(result);
    }

    private BigDecimal convertPercentInput(double percent) {
        return BigDecimal.valueOf(percent).divide(BigDecimal.valueOf(100));
    }

}
