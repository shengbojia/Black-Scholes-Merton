package com.shengbojia.view;

import javax.swing.*;
import java.awt.*;

/**
 * Labelled location for users to input data, indicates which parameter and what unit of measurement the data
 * should be in.
 */
public class InputFieldAndLabel {
    private JPanel panel;
    private JLabel inputLabel;
    private JLabel inputUnitLabel;
    private JTextField inputField;
    private String inputName;

    /**
     * Class constructor specifying for which parameter is the input for as well the unit of measurement being used.
     *
     * @param inputName the name of the parameter
     * @param inputUnit the unit being used
     */
    public InputFieldAndLabel(String inputName, String inputUnit) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));

        this.inputName = inputName;

        inputLabel = new JLabel(inputName + ":");
        inputUnitLabel = new JLabel("in " + inputUnit);
        inputField = new JTextField();

        panel.add(inputLabel);
        panel.add(inputField);
        panel.add(inputUnitLabel);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getInputLabel() {
        return inputLabel;
    }

    public JLabel getInputUnitLabel() {
        return inputUnitLabel;
    }

    public JTextField getInputField() {
        return inputField;
    }

    public String getInputName() {
        return inputName;
    }
}
