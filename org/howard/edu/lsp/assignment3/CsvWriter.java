package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/** Writes transformed products to CSV with required header. */
public class CsvWriter {
    private static final String OUTPUT_HEADER = "ProductID,Name,Price,Category,PriceRange";
    private final Path output;

    public CsvWriter(Path output) {
        this.output = output;
    }

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
