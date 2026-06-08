package vut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;


public class Invoicecalculatortest {

    private InvoiceCalculator calc;

    @BeforeEach
    public void setUp() {
        calc = new InvoiceCalculator();
    }
    /**
     * Each row = one scenario: amount, discountPercent, expectedResult
     *
     * Row 1: no discount          -- R100 with 0%  = R100.00
     * Row 2: 10% discount         -- R100 with 10% = R90.00
     * Row 3: 100% discount        -- R100 with 100% = R0.00
     * Row 4: decimal amount       -- R199.99 with 5% = R189.99
     * Row 5: boundary -- 0 amount -- R0 with 10% = R0.00
     */
    @ParameterizedTest
    @CsvSource({
        "100.00, 0,   100.00",
        "100.00, 10,   90.00",
        "100.00, 100,   0.00",
        "199.99, 5,   189.99",
        "  0.00, 10,   0.00"
    })
    public void testCalculateDiscountedAmount(String amount, String discount, String expected) {
        BigDecimal result = calc.calculateDiscountedAmount(
                new BigDecimal(amount),
                new BigDecimal(discount)
        );
        assertEquals(new BigDecimal(expected), result);
    }
    /**
     * Each row = one scenario: amount, expectedResult (amount + 15% VAT)
     *
     * Row 1: zero amount    -- R0 + 15% VAT = R0.00
     * Row 2: decimal amount -- R99.99 + 15% = R114.99
     * Row 3: large amount   -- R10000 + 15% = R11500.00
     * Row 4: rounding case  -- R1.01 + 15% = R1.16  (1.01 x 1.15 = 1.1615 -> rounds to 1.16)
     * Row 5: round amount   -- R200 + 15% = R230.00
     */
    @ParameterizedTest
    @CsvSource({
        "    0.00,     0.00",
        "   99.99,   114.99",
        "10000.00, 11500.00",
        "    1.01,     1.16",
        "  200.00,   230.00"
    })
    public void testApplyVAT(String amount, String expected) {
        BigDecimal result = calc.applyVAT(new BigDecimal(amount));
        assertEquals(new BigDecimal(expected), result);
    }
    /**
     * Each row = one full invoice scenario: amount, discount%, expectedFinal
     *
     * Row 1: no discount, then VAT   -- R100, 0% -> R100 + 15% = R115.00
     * Row 2: 10% discount, then VAT  -- R100, 10% -> R90 + 15% = R103.50
     * Row 3: 20% discount, then VAT  -- R500, 20% -> R400 + 15% = R460.00
     * Row 4: 50% discount, then VAT  -- R200, 50% -> R100 + 15% = R115.00
     */
    @ParameterizedTest
    @CsvSource({
        "100.00,  0, 115.00",
        "100.00, 10, 103.50",
        "500.00, 20, 460.00",
        "200.00, 50, 115.00"
    })
    public void testCalculateFinalAmount(String amount, String discount, String expected) {
        BigDecimal result = calc.calculateFinalAmount(
                new BigDecimal(amount),
                new BigDecimal(discount)
        );
        assertEquals(new BigDecimal(expected), result);
    }
    /**
     * Tests that a negative amount throws IllegalArgumentException.
     * Each row is a different negative value.
     */
    @ParameterizedTest
    @CsvSource({"-1.00", "-0.01", "-999.99"})
    public void testNegativeAmountThrowsException(String amount) {
        try {
            calc.calculateDiscountedAmount(new BigDecimal(amount), new BigDecimal("10"));
            fail("Expected IllegalArgumentException but none was thrown");
        } catch (IllegalArgumentException e) {
            // test passes -- exception was thrown as expected
        }
    }

    /**
     * Tests that a discount below 0 throws IllegalArgumentException.
     */
    @ParameterizedTest
    @CsvSource({"-1", "-0.01", "-50"})
    public void testDiscountBelowZeroThrowsException(String discount) {
        try {
            calc.calculateDiscountedAmount(new BigDecimal("100"), new BigDecimal(discount));
            fail("Expected IllegalArgumentException but none was thrown");
        } catch (IllegalArgumentException e) {
            // test passes -- exception was thrown as expected
        }
    }

    /**
     * Tests that a discount above 100 throws IllegalArgumentException.
     */
    @ParameterizedTest
    @CsvSource({"101", "150", "200"})
    public void testDiscountAbove100ThrowsException(String discount) {
        try {
            calc.calculateDiscountedAmount(new BigDecimal("100"), new BigDecimal(discount));
            fail("Expected IllegalArgumentException but none was thrown");
        } catch (IllegalArgumentException e) {
            // test passes -- exception was thrown as expected
        }
    }

    /**
     * Tests that a negative amount in applyVAT also throws IllegalArgumentException.
     */
    @Test
    public void testApplyVAT_NegativeAmount_ThrowsException() {
        try {
            calc.applyVAT(new BigDecimal("-50.00"));
            fail("Expected IllegalArgumentException but none was thrown");
        } catch (IllegalArgumentException e) {
            // test passes
        }
    }
}
