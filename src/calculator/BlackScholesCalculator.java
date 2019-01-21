package calculator;

import static bigmath.BigDecimalMath.MINUS_ONE;
import static bigmath.BigDecimalMath.THREE;
import static bigmath.BigDecimalMath.TWO;
import static bigmath.BigDecimalMath.exp;
import static bigmath.BigDecimalMath.log;
import static bigmath.BigDecimalMath.sqrt;
import static bigmath.BigDecimalMath.square;
import static bigmath.BigDecimalMath.tau;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BlackScholesCalculator {

    final private static MathContext DEFAULT_PRECISION = new MathContext(8, RoundingMode.HALF_EVEN);

    final private MathContext precision;

    public BlackScholesCalculator() {
        this(DEFAULT_PRECISION);
    }

    public BlackScholesCalculator(MathContext precision) {
        this.precision = precision;
    }

    public BigDecimal dOne(BigDecimal stockPrice, BigDecimal strikePrice, BigDecimal timeToMaturity,
                            BigDecimal volatility, BigDecimal riskFreeRate) {

        BigDecimal a = log(stockPrice.divide(strikePrice, precision), precision);

        BigDecimal b = riskFreeRate.add(square(volatility).divide(TWO, precision));

        BigDecimal numerator = a.add(b.multiply(timeToMaturity));

        BigDecimal denominator = volatility.multiply(sqrt(timeToMaturity, precision));

        return numerator.divide(denominator, precision);
    }

    public BigDecimal dTwo(BigDecimal stockPrice, BigDecimal strikePrice, BigDecimal timeToMaturity,
                            BigDecimal volatility, BigDecimal riskFreeRate) {

        return dOne(stockPrice, strikePrice, timeToMaturity, volatility, riskFreeRate)
                .subtract(volatility.multiply(sqrt(timeToMaturity, precision)));
    }

    private BigDecimal normPdf(BigDecimal x) {

        BigDecimal factor = ONE.divide(sqrt(tau(precision), precision), precision);
        
        BigDecimal exponentOfE = square(x)
                .divide(TWO, precision)
                .multiply(MINUS_ONE);

        return factor.multiply(exp(exponentOfE, precision));
    }

    private BigDecimal normCdf(BigDecimal x) {
        final boolean isNegative;

        if (x.compareTo(ZERO) < 0) {
            isNegative = true;
            x = x.multiply(MINUS_ONE);
        } else {
            isNegative = false;
        }

        BigDecimal factor1 = ONE.divide(sqrt(tau(precision), precision), precision);
        BigDecimal factor2;

        BigDecimal numerator = exp(MINUS_ONE
                        .multiply(square(x))
                        .divide(TWO, precision),
                precision);

        BigDecimal denominatorAddend1 = valueOf(0.226)
                .add(valueOf(0.64).multiply(x));

        BigDecimal denominatorAddend2 = valueOf(0.33)
                .multiply(sqrt(square(x).add(THREE), precision));

        factor2 = numerator.divide(denominatorAddend1.add(denominatorAddend2, precision));

        BigDecimal result = ONE.subtract(factor1.multiply(factor2));

        if (!isNegative) {
            return result;
        } else {
            return ONE.subtract(result);
        }
    }
}
