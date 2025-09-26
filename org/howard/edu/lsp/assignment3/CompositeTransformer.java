package org.howard.edu.lsp.assignment3;

import java.util.List;

/**
 * Chain of transformers applied in order.
 */
public class CompositeTransformer implements Transformer {
    private final List<Transformer> transformers;

    public CompositeTransformer(List<Transformer> transformers) {
        this.transformers = transformers;
    }

    @Override
    public void transform(Product product) {
        for (Transformer t : transformers) {
            t.transform(product);
        }
    }
}
