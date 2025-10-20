package org.howard.edu.lsp.assignment3;

/**
 * Transformer that converts product name to uppercase.
 */
public class NameUppercaseTransformer implements Transformer {
    /**
     * Convert name to uppercase if non-null.
     *
     * @param product product to modify
     */
    @Override
    public void transform(Product product) {
        if (product.getName() != null) {
            product.setName(product.getName().toUpperCase());
        }
    }
}
