package org.howard.edu.lsp.assignment3;

import java.util.List;

/**
 * Transformer that composes a list of transformers and applies them in order.
 */
public class CompositeTransformer implements Transformer {
    private final List<Transformer> transformers;

    /**
     * Build a composite transformer.
     *
     * @param transformers transformers to apply in order
     */
    public CompositeTransformer(List<Transformer> transformers) {
        this.transformers = transformers;
    }

    /**
     * Apply each transformer in sequence to the product.
     *
     * @param product product to transform
     */
    @Override
    public void transform(Product product) {
        for (Transformer t : transformers) {
            t.transform(product);
        }
    }
}
