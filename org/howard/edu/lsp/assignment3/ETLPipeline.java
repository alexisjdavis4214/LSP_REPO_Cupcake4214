package org.howard.edu.lsp.assignment3;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Main entry for Assignment 3 ETL pipeline.
 */
public class ETLPipeline {
    private static final Path INPUT_PATH = Paths.get("data", "products.csv");
    private static final Path OUTPUT_PATH = Paths.get("data", "transformed_products.csv");

    public static void main(String[] args) {
        CsvReader reader = new CsvReader(INPUT_PATH);
        CsvWriter writer = new CsvWriter(OUTPUT_PATH);

        // Build transformer chain in required order:
        CompositeTransformer pipeline = new CompositeTransformer(Arrays.asList(
                new NameUppercaseTransformer(),
                new ElectronicsDiscountTransformer(),
                new CategoryRecategorizerTransformer(),
                new PriceRangeAssignerTransformer()
        ));

        ETLService service = new ETLService(reader, pipeline, writer);
        String result = service.run();
        System.out.println(result);
    }
}
