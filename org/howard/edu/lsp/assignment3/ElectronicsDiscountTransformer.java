package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Transformer that applies a 10% discount to products whose original category
 * was "Electronics". Rounds price to two decimals (HALF_UP).
 */
public class ElectronicsDiscountTransformer implements Transformer {
    private static final BigDecimal DISCOUNT = new BigDecimal("0.10");

    /**
     * Apply 10% discount if original category is Electronics; otherwise ensure price rounded.
     *
     * @param product product to modify
     */
    @Override
    public void transform(Product product) {
        if ("Electronics".equalsIgnoreCase(product.getOriginalCategory())) {
            product.applyPercentageDiscount(DISCOUNT);
        } else {
            // Ensure price is represented with two decimals for downstream logic
            product.roundPrice();
        }
    }
}
