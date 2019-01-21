import static java.math.BigDecimal.valueOf;

import calculator.BlackScholesCalculator;

public class Main {
    public static void main(String[] args) {
        BlackScholesCalculator calculator = new BlackScholesCalculator();
        System.out.println(calculator.dOne(valueOf(52), valueOf(50), valueOf(0.5), valueOf(.12), valueOf(0.05)));
        System.out.println(calculator.dTwo(valueOf(52), valueOf(50), valueOf(0.5), valueOf(.12), valueOf(0.05)));
    }
}
