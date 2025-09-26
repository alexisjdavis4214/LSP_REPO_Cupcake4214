package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/** Assigns PriceRange label based on final price. */
public class PriceRangeAssignerTransformer implements Transformer {
    private static final BigDecimal LOW_UP = new BigDecimal("10.00");
    private static final BigDecimal MED_LO = new BigDecimal("10.01");
    private static final BigDecimal MED_UP = new BigDecimal("100.00");
    private static final BigDecimal HIGH_LO = new BigDecimal("100.01");
    private static final BigDecimal HIGH_UP = new BigDecimal("500.00");
    private static final BigDecimal PREMIUM_LO = new BigDecimal("500.01");

    @Override
    public void transform(Product product) {
        BigDecimal price = product.getPrice();
        String label;
        if (price.compareTo(BigDecimal.ZERO) >= 0 && price.compareTo(LOW_UP) <= 0) {
            label = "Low";
        } else if (price.compareTo(MED_LO) >= 0 && price.compareTo(MED_UP) <= 0) {
            label = "Medium";
        } else if (price.compareTo(HIGH_LO) >= 0 && price.compareTo(HIGH_UP) <= 0) {
            label = "High";
        } else if (price.compareTo(PREMIUM_LO) >= 0) {
            label = "Premium";
        } else {
            label = "Low";
        }
        product.setPriceRange(label);
    }
}
