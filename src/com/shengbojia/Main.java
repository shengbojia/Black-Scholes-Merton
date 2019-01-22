package com.shengbojia;

import com.shengbojia.calculator.BlackScholesCalculator;
import com.shengbojia.control.Controller;
import com.shengbojia.view.MainWindow;

public class Main {
    public static void main(String[] args) {
        BlackScholesCalculator calculator = new BlackScholesCalculator();
        MainWindow window = new MainWindow();

        Controller controller = new Controller(calculator, window);

        // TODO: Document everything!
    }
}
