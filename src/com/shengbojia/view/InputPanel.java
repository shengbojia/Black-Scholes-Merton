package com.shengbojia.view;

import javax.swing.*;
import java.awt.*;

public class InputPanel {
    private JPanel panel;
    private InputFieldAndLabel stock;
    private InputFieldAndLabel strike;
    private InputFieldAndLabel maturity;
    private InputFieldAndLabel volatility;
    private InputFieldAndLabel riskFree;

    public InputPanel() {
        panel = new JPanel();
        GridLayout layout = new GridLayout(5, 1);
        panel.setLayout(layout);

        createComponents();
    }

    private void createComponents() {
        stock = new InputFieldAndLabel("Stock price", "USD");
        strike = new InputFieldAndLabel("Strike price", "USD");
        maturity = new InputFieldAndLabel("Time to maturity", "years");
        volatility = new InputFieldAndLabel("Volatility", "%");
        riskFree = new InputFieldAndLabel("Risk-free rate", "%");

        panel.add(stock.getPanel());
        panel.add(strike.getPanel());
        panel.add(maturity.getPanel());
        panel.add(volatility.getPanel());
        panel.add(riskFree.getPanel());
    }

    public InputFieldAndLabel getStock() {
        return stock;
    }

    public InputFieldAndLabel getStrike() {
        return strike;
    }

    public InputFieldAndLabel getMaturity() {
        return maturity;
    }

    public InputFieldAndLabel getVolatility() {
        return volatility;
    }

    public InputFieldAndLabel getRiskFree() {
        return riskFree;
    }

    public JPanel getPanel() {
        return panel;
    }
}
