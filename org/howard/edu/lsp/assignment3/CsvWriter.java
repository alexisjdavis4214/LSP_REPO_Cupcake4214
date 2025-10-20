package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Writes transformed products to CSV with the required header.
 */
public class CsvWriter {
    private static final String OUTPUT_HEADER = "ProductID,Name,Price,Category,PriceRange";
    private final Path output;

    /**
     * Construct a writer for the specified output.
     *
     * @param output output CSV path
     */
    public CsvWriter(Path output) {
        this.output = output;
    }

    /**
     * Write header and given products to the output CSV.
     *
     * @param products list of transformed products (may be empty)
     * @throws IOException if write fails
     */
    public void write(List<Product> products) throws IOException {
        if (output.getParent() != null) {
            Files.createDirectories(output.getParent());
        }
        try (BufferedWriter bw = Files.newBufferedWriter(output)) {
            bw.write(OUTPUT_HEADER);
            bw.newLine();
            for (Product p : products) {
                bw.write(p.toCsvRow());
                bw.newLine();
            }
            bw.flush();
        }
    }
}

