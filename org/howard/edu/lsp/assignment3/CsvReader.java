package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple CSV reader for products.csv. Handles missing file and malformed rows.
 */
public class CsvReader {
    private final Path input;

    /**
     * Result of extraction.
     */
    public static class ExtractionResult {
        public final String header;
        public final List<Product> products;
        public final int rowsRead; // number of non-empty data rows read (excluding header)
        public final int skippedOnRead; // number of rows skipped during parsing

        public ExtractionResult(String header, List<Product> products, int rowsRead, int skippedOnRead) {
            this.header = header;
            this.products = products;
            this.rowsRead = rowsRead;
            this.skippedOnRead = skippedOnRead;
        }
    }

    /**
     * Construct a CSV reader for the specified path.
     *
     * @param input input CSV path
     */
    public CsvReader(Path input) {
        this.input = input;
    }

    /**
     * Extract products from CSV.
     *
     * @return ExtractionResult containing header, list of parsed products, counts
     * @throws IOException if file cannot be read or is missing (FileNotFoundException)
     */
    public ExtractionResult extract() throws IOException {
        if (!Files.exists(input)) {
            throw new FileNotFoundException("Missing input: " + input.toString());
        }

        try (BufferedReader br = Files.newBufferedReader(input)) {
            String header = br.readLine();
            if (header == null) {
                // Completely empty file (no header)
                return new ExtractionResult(null, new ArrayList<>(), 0, 0);
            }
            List<Product> products = new ArrayList<>();
            String line;
            int readCount = 0;
            int skippedOnRead = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    // skip blank lines silently (do not increment readCount)
                    continue;
                }
                readCount++;
                String[] parts = line.split(",", -1);
                try {
                    Product p = Product.fromCsvParts(parts);
                    products.add(p);
                } catch (Exception ex) {
                    // malformed row (wrong number of fields or parse error)
                    skippedOnRead++;
                }
            }
            return new ExtractionResult(header, products, readCount, skippedOnRead);
        }
    }
}
