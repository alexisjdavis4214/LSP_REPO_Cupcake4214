package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/** If original category Electronics and post-discount price > 500.00, set Premium Electronics. */
public class CategoryRecategorizerTransformer implements Transformer {
    private static final BigDecimal THRESHOLD = new BigDecimal("500.00");

    @Override
    public void transform(Product product) {
        if ("Electronics".equalsIgnoreCase(product.getOriginalCategory())
                && product.getPrice().compareTo(THRESHOLD) == 1) {
            product.setCategory("Premium Electronics");
        }
    }
}
