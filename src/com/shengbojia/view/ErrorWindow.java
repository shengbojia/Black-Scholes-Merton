package com.shengbojia.view;

import javax.swing.*;

public class ErrorWindow {
    private JFrame frame;
    private JLabel errorMsg;

    public ErrorWindow(String error) {
        frame = new JFrame("Error!");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        error = "<html><font color=red>" + error + "</font></html>";
        errorMsg = new JLabel(error, SwingConstants.CENTER);

        frame.add(errorMsg);

        frame.setLocation(500, 200);
        frame.setVisible(true);
    }
}
