package com.shengbojia.view;

import javax.swing.*;

public class OutputPanel {
    private JPanel panel;
    private JLabel callLabel;
    private JLabel putLabel;

    // TODO: Make this better looking

    public OutputPanel() {
        panel = new JPanel();
        callLabel = new JLabel("call");
        putLabel = new JLabel("put");

        panel.add(callLabel);
        panel.add(putLabel);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getCallLabel() {
        return callLabel;
    }

    public JLabel getPutLabel() {
        return putLabel;
    }
}
