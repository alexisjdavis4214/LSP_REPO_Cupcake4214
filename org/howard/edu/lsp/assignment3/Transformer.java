package org.howard.edu.lsp.assignment3;

/**
 * Interface for transformation steps that mutate a Product in-place.
 */
public interface Transformer {
    /**
     * Apply transformation to the given product.
     *
     * @param product the product to transform
     */
    void transform(Product product);
}
