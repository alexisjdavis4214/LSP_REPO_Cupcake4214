package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Transformer that reassigns category to "Premium Electronics" if the original
 * category was Electronics and the post-discount price is strictly > $500.00.
 */
public class CategoryRecategorizerTransformer implements Transformer {
    private static final BigDecimal THRESHOLD = new BigDecimal("500.00");

    /**
     * Reclassify category to "Premium Electronics" when conditions met.
     *
     * @param product product to modify
     */
    @Override
    public void transform(Product product) {
        if ("Electronics".equalsIgnoreCase(product.getOriginalCategory())
                && product.getPrice().compareTo(THRESHOLD) == 1) {
            product.setCategory("Premium Electronics");
        }
    }
}
