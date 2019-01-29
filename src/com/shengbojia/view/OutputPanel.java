package com.shengbojia.view;

import javax.swing.*;
import java.awt.*;

/**
 * The panel where output is displayed to the user.
 */
public class OutputPanel {
    private JPanel panel;
    private JLabel callLabel;
    private JLabel callDisplay;
    private JLabel putLabel;
    private JLabel putDisplay;

    /**
     * Class constructor
     */
    public OutputPanel() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        callLabel = new JLabel("<html><font size=+1><b>Call:</b></font></html>");
        callDisplay = new JLabel();
        putLabel = new JLabel("<html><font size=+1><b>Put:</b></font></html>");
        putDisplay = new JLabel();


        panel.add(callLabel);
        panel.add(callDisplay);
        panel.add(putLabel);
        panel.add(putDisplay);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getCallLabel() {
        return callLabel;
    }

    public JLabel getCallDisplay() {
        return callDisplay;
    }

    public JLabel getPutLabel() {
        return putLabel;
    }

    public JLabel getPutDisplay() {
        return putDisplay;
    }
}
