package org.howard.edu.lsp.assignment3;

/** Converts product name to uppercase. */
public class NameUppercaseTransformer implements Transformer {
    @Override
    public void transform(Product product) {
        if (product.getName() != null) {
            product.setName(product.getName().toUpperCase());
        }
    }
}
