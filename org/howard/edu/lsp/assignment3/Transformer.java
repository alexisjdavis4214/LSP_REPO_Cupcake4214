package org.howard.edu.lsp.assignment3;

/**
 * A transformation step that mutates a Product.
 */
public interface Transformer {
    /**
     * Applies a transformation to the given product (in-place).
     */
    void transform(Product product);
}
