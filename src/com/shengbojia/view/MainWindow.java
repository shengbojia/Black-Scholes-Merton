package com.shengbojia.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The main window of the application on which all other GUI components are displayed.
 */
public class MainWindow {
    private JFrame frame;

    private InputPanel inputPanel;
    private JButton pressToCalculate;
    private OutputPanel outputPanel;

    /**
     * Class constructor
     */
    public MainWindow() {
        frame = new JFrame("Calculator");
        //frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents();

        frame.setLocation(100, 100);
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents() {
        GridLayout layout = new GridLayout(3, 1);
        frame.setLayout(layout);

        inputPanel = new InputPanel();
        outputPanel = new OutputPanel();
        pressToCalculate = new JButton("Calculate");

        frame.add(inputPanel.getPanel());
        frame.add(pressToCalculate);
        frame.add(outputPanel.getPanel());
    }

    /**
     * Adds a controller in the form of an action listener.
     *
     * @param controller the controller to be added as a listener
     */
    public void addController(ActionListener controller) {
        pressToCalculate.addActionListener(controller);
    }

    public JFrame getFrame() {
        return frame;
    }

    public InputPanel getInputPanel() {
        return inputPanel;
    }

    public OutputPanel getOutputPanel() {
        return outputPanel;
    }

    public JButton getPressToCalculate() {
        return pressToCalculate;
    }
}
