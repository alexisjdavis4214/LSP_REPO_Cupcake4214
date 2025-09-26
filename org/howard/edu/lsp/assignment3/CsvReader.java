package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Simple CSV reader for products.csv. Throws FileNotFoundException if missing. */
public class CsvReader {
    private final Path input;

    public CsvReader(Path input) {
        this.input = input;
    }

    public static class ExtractionResult {
        public final String header;
        public final List<Product> products;
        public final int rowsRead;
        public ExtractionResult(String header, List<Product> products, int rowsRead) {
            this.header = header;
            this.products = products;
            this.rowsRead = rowsRead;
        }
    }

    public ExtractionResult extract() throws IOException {
        if (!Files.exists(input)) {
            throw new FileNotFoundException("Missing input: " + input.toString());
        }
        try (BufferedReader br = Files.newBufferedReader(input)) {
            String header = br.readLine();
            if (header == null) {
                return new ExtractionResult(null, new ArrayList<>(), 0);
            }
            List<Product> products = new ArrayList<>();
            String line;
            int readCount = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                readCount++;
                String[] parts = line.split(",", -1);
                try {
                    Product p = Product.fromCsvParts(parts);
                    products.add(p);
                } catch (Exception ex) {
                    // skip malformed row (ETLService will count skipped)
                }
            }
            return new ExtractionResult(header, products, readCount);
        }
    }
}
