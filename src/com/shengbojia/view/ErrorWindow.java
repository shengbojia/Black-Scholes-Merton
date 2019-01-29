package com.shengbojia.view;

import javax.swing.*;

/**
 * Pop up window indicating an error has occurred.
 */
public class ErrorWindow {
    private JFrame frame;
    private JLabel errorMsg;

    /**
     * Class constructor which initializes the error window and displays the specified error.
     *
     * @param error the error message that is to be displayed
     */
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
