package vut;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Financial calculation component for a South African retail billing system.
 * Uses BigDecimal for all calculations to avoid floating-point precision errors.
 * VAT rate is fixed at 15%.
 */
public class InvoiceCalculator {

    // VAT rate constant -- stored as BigDecimal to avoid precision issues
    private static final BigDecimal VAT_RATE = new BigDecimal("0.15");

    // -------------------------------------------------------------------------
    // QUESTION 1 -- calculateDiscountedAmount
    // -------------------------------------------------------------------------

    /**
     * Applies a percentage discount to the given amount.
     * Returns the amount after discount, rounded to 2 decimal places.
     *
     * @param amount          the original price (must not be negative)
     * @param discountPercent the discount percentage (must be 0 to 100 inclusive)
     * @return the discounted amount
     */
    public BigDecimal calculateDiscountedAmount(BigDecimal amount, BigDecimal discountPercent) {
        validateAmount(amount);
        validateDiscount(discountPercent);

        // Convert percent to a rate -- e.g. 10 becomes 0.10
        BigDecimal rate = discountPercent.divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);

        // Discount value = amount x rate
        BigDecimal discountValue = amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);

        // Discounted amount = original - discount
        return amount.subtract(discountValue).setScale(2, RoundingMode.HALF_UP);
    }

    // -------------------------------------------------------------------------
    // QUESTION 2 -- applyVAT
    // -------------------------------------------------------------------------

    /**
     * Adds 15% VAT to the given amount.
     * Returns the amount including VAT, rounded to 2 decimal places.
     *
     * @param amount the price before VAT (must not be negative)
     * @return the amount including VAT
     */
    public BigDecimal applyVAT(BigDecimal amount) {
        validateAmount(amount);

        // VAT amount = amount x 0.15
        BigDecimal vatAmount = amount.multiply(VAT_RATE).setScale(2, RoundingMode.HALF_UP);

        // Final = amount + VAT
        return amount.add(vatAmount).setScale(2, RoundingMode.HALF_UP);
    }

    // -------------------------------------------------------------------------
    // QUESTION 3 -- calculateFinalAmount
    // -------------------------------------------------------------------------

    /**
     * Applies discount first, then VAT.
     * This is the complete invoice calculation.
     *
     * @param amount          the original price
     * @param discountPercent the discount percentage
     * @return the final amount after discount and VAT
     */
    public BigDecimal calculateFinalAmount(BigDecimal amount, BigDecimal discountPercent) {
        // Step 1: apply the discount
        BigDecimal afterDiscount = calculateDiscountedAmount(amount, discountPercent);

        // Step 2: apply VAT to the discounted amount
        return applyVAT(afterDiscount);
    }

    // -------------------------------------------------------------------------
    // QUESTION 4 -- private validation methods
    // -------------------------------------------------------------------------

    /**
     * Validates that the amount is not negative.
     * Throws IllegalArgumentException if it is.
     */
    private void validateAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative. Received: " + amount);
        }
    }

    /**
     * Validates that the discount percentage is between 0 and 100 inclusive.
     * Throws IllegalArgumentException if it is out of range.
     */
    private void validateDiscount(BigDecimal discountPercent) {
        if (discountPercent.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Discount cannot be less than 0. Received: " + discountPercent);
        }
        if (discountPercent.compareTo(new BigDecimal("100")) > 0) {
            throw new IllegalArgumentException("Discount cannot exceed 100. Received: " + discountPercent);
        }
    }
}