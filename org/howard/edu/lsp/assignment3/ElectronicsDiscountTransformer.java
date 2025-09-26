package org.howard.edu.lsp.assignment3;


import java.math.BigDecimal;

/** If original category is Electronics, apply 10% discount and round to 2 decimals. */
public class ElectronicsDiscountTransformer implements Transformer {
    private static final BigDecimal DISCOUNT = new BigDecimal("0.10");

    @Override
    public void transform(Product product) {
        if ("Electronics".equalsIgnoreCase(product.getOriginalCategory())) {
            product.applyPercentageDiscount(DISCOUNT);
        } else {
            // keep consistent rounding for non-electronics too (safe)
            product.roundPrice();
        }
    }
}
