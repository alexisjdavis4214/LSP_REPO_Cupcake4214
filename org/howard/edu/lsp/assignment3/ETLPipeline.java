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

    /**
     * Program entry. Builds components, runs pipeline, and prints the summary.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        CsvReader reader = new CsvReader(INPUT_PATH);
        CsvWriter writer = new CsvWriter(OUTPUT_PATH);

        CompositeTransformer pipeline = new CompositeTransformer(Arrays.asList(
                new NameUppercaseTransformer(),
                new ElectronicsDiscountTransformer(),
                new CategoryRecategorizerTransformer(),
                new PriceRangeAssignerTransformer()
        ));

        ETLService service = new ETLService(reader, pipeline, writer);
        String result = service.run();

        // If result begins with ERROR:, print to stderr
        if (result.startsWith("ERROR:")) {
            System.err.println(result.substring(6).trim());
        } else {
            System.out.println("ETL Pipeline started!");
            System.out.println(result);
        }
    }
}
