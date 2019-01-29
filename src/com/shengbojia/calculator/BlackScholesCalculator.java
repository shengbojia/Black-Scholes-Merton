package com.shengbojia.calculator;

import static com.shengbojia.bigmath.BigDecimalMath.MINUS_ONE;
import static com.shengbojia.bigmath.BigDecimalMath.THREE;
import static com.shengbojia.bigmath.BigDecimalMath.TWO;
import static com.shengbojia.bigmath.BigDecimalMath.exp;
import static com.shengbojia.bigmath.BigDecimalMath.log;
import static com.shengbojia.bigmath.BigDecimalMath.sqrt;
import static com.shengbojia.bigmath.BigDecimalMath.square;
import static com.shengbojia.bigmath.BigDecimalMath.tau;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Pricing calculator for vanilla European options, using the Black-Scholes-Merton model along with BigDecimal math.
 *
 * @author Shengbo Jia
 */
public class BlackScholesCalculator {

    /**
     * Default context used for calculations
     */
    public static final MathContext DEFAULT_PRECISION = new MathContext(8, RoundingMode.HALF_EVEN);

    private final MathContext precision;

    /**
     * Default constructor
     */
    public BlackScholesCalculator() {
        this(DEFAULT_PRECISION);
    }

    /**
     * Constructor specifying the math context
     *
     * @param precision context to use
     */
    public BlackScholesCalculator(MathContext precision) {
        this.precision = precision;
    }

    public MathContext getPrecision() {
        return precision;
    }

    /**
     * Returns the calculated price of a call option based on inputted parameters.
     *
     * @param stockPrice big decimal value of the underlying asset price
     * @param strikePrice big decimal value of the option strike price
     * @param timeToMaturity big decimal value of the time to maturity, in years
     * @param volatility big decimal value of the asset's volatility, in decimal
     * @param riskFreeRate big decimal value of the current risk free interest rate, in decimal
     * @return big decimal value of the calculated call price
     */
    public BigDecimal callPricing(
            BigDecimal stockPrice,
            BigDecimal strikePrice,
            BigDecimal timeToMaturity,
            BigDecimal volatility,
            BigDecimal riskFreeRate) {

        BigDecimal minuend = stockPrice
                .multiply(normCdf(dOne(stockPrice, strikePrice, timeToMaturity, volatility, riskFreeRate)));

        BigDecimal subtrahendFactor1 = strikePrice
                .multiply(exp(MINUS_ONE.multiply(riskFreeRate).multiply(timeToMaturity), precision));

        BigDecimal subtrahendFactor2 =
                normCdf(dTwo(stockPrice, strikePrice, timeToMaturity, volatility, riskFreeRate));

        return minuend.subtract(subtrahendFactor1.multiply(subtrahendFactor2));
    }

    /**
     * Returns the calculated price of a put option based on input parameters.
     *
     * @param stockPrice big decimal value of the underlying asset price
     * @param strikePrice big decimal value of the option strike price
     * @param timeToMaturity big decimal value of the time to maturity, in years
     * @param volatility big decimal value of the asset's volatility, in decimal
     * @param riskFreeRate big decimal value of the current risk free interest rate, in decimal
     * @return big decimal value of the calculated put price
     */
    public BigDecimal putPricing(
            BigDecimal stockPrice,
            BigDecimal strikePrice,
            BigDecimal timeToMaturity,
            BigDecimal volatility,
            BigDecimal riskFreeRate) {

        BigDecimal callPrice = callPricing(stockPrice, strikePrice, timeToMaturity, volatility, riskFreeRate);

        BigDecimal minuend = strikePrice.divide(exp(riskFreeRate.multiply(timeToMaturity), precision), precision);

        return callPrice.add(minuend.subtract(stockPrice));
    }

    private BigDecimal dOne(
            BigDecimal stockPrice,
            BigDecimal strikePrice,
            BigDecimal timeToMaturity,
            BigDecimal volatility,
            BigDecimal riskFreeRate) {

        BigDecimal a = log(stockPrice.divide(strikePrice, precision), precision);

        BigDecimal b = riskFreeRate.add(square(volatility).divide(TWO, precision));

        BigDecimal numerator = a.add(b.multiply(timeToMaturity));

        BigDecimal denominator = volatility.multiply(sqrt(timeToMaturity, precision));

        return numerator.divide(denominator, precision);
    }

    private BigDecimal dTwo(
            BigDecimal stockPrice,
            BigDecimal strikePrice,
            BigDecimal timeToMaturity,
            BigDecimal volatility,
            BigDecimal riskFreeRate) {

        return dOne(stockPrice, strikePrice, timeToMaturity, volatility, riskFreeRate)
                .subtract(volatility.multiply(sqrt(timeToMaturity, precision)));
    }

    // TODO: Consider Greeks using this pdf

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

        factor2 = numerator.divide(denominatorAddend1.add(denominatorAddend2), precision);

        BigDecimal result = ONE.subtract(factor1.multiply(factor2));

        if (!isNegative) {
            return result;
        } else {
            return ONE.subtract(result);
        }
    }
}
