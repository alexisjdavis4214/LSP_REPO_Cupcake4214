package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ETLPipeline {

    private static final Path INPUT_PATH = Paths.get("data", "products.csv");
    private static final Path OUTPUT_PATH = Paths.get("data", "transformed_products.csv");
    private static final String OUTPUT_HEADER = "ProductID,Name,Price,Category,PriceRange";

    public static void main(String[] args) {
        System.out.println("ETL Pipeline started!");
        ExtractionResult ext;
        try {
            ext = extract(INPUT_PATH);
        } catch (FileNotFoundException fnf) {
            System.err.println("ERROR: input file not found at: " + INPUT_PATH.toString());
            System.err.println("Please ensure you run the program from the project root and that data/products.csv exists.");
            return;
        } catch (IOException ioe) {
            System.err.println("ERROR reading input file: " + ioe.getMessage());
            return;
        }

        // If header is missing (file is empty) treat as error (spec assumes at least header present).
        if (ext.header == null) {
            System.err.println("ERROR: input file is empty (no header). Expected header row.");
            return;
        }

        // Transform
        TransformResult tr = transform(ext.rows);

        // Load (always write header; if no rows, output will contain header only)
        try {
            load(tr.transformedRows, OUTPUT_PATH);
        } catch (IOException ioe) {
            System.err.println("ERROR writing output file: " + ioe.getMessage());
            return;
        }

        // Summary
        System.out.println("Run summary:");
        System.out.println("  Rows read (excluding header): " + ext.rowsRead);
        System.out.println("  Rows transformed & written : " + tr.transformedRows.size());
        System.out.println("  Rows skipped (malformed)   : " + tr.skippedRows);
        System.out.println("  Output written to: " + OUTPUT_PATH.toString());
    }

    // ========== Extract ==========
    private static class ExtractionResult {
        String header;
        List<String[]> rows; // each row as String[] of raw fields
        int rowsRead; // number of non-empty data rows read (excluding header)

        ExtractionResult(String header, List<String[]> rows, int rowsRead) {
            this.header = header;
            this.rows = rows;
            this.rowsRead = rowsRead;
        }
    }

    private static ExtractionResult extract(Path input) throws IOException {
        if (!Files.exists(input)) {
            throw new FileNotFoundException("Missing input: " + input.toString());
        }

        try (BufferedReader br = Files.newBufferedReader(input)) {
            String header = br.readLine();
            if (header == null) {
                // empty file (no header)
                return new ExtractionResult(null, Collections.emptyList(), 0);
            }

            List<String[]> rows = new ArrayList<>();
            String line;
            int readCount = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    // skip blank lines silently
                    continue;
                }
                readCount++;
                // safe split (fields do not contain commas or quotes per spec)
                String[] parts = line.split(",", -1);
                rows.add(parts);
            }
            return new ExtractionResult(header, rows, readCount);
        }
    }

    // ========== Transform ==========
    private static class TransformResult {
        List<String[]> transformedRows;
        int skippedRows;

        TransformResult(List<String[]> transformedRows, int skippedRows) {
            this.transformedRows = transformedRows;
            this.skippedRows = skippedRows;
        }
    }

    private static TransformResult transform(List<String[]> rows) {
        List<String[]> out = new ArrayList<>();
        int skipped = 0;

        for (String[] parts : rows) {
            // Expect exactly 4 columns: ProductID,Name,Price,Category
            if (parts.length != 4) {
                skipped++;
                continue;
            }

            String idStr = parts[0].trim();
            String name = parts[1].trim();
            String priceStr = parts[2].trim();
            String category = parts[3].trim();

            try {
                // Validate ProductID numeric
                Integer.parseInt(idStr);

                // Parse price as BigDecimal for precise rounding
                BigDecimal price = new BigDecimal(priceStr);

                // 1) uppercase name
                String nameUpper = name.toUpperCase();

                // keep original category for recategorization condition
                boolean originalElectronics = category.equalsIgnoreCase("Electronics");

                // 2) discount (10% off) if original category is Electronics
                BigDecimal finalPrice = price;
                if (originalElectronics) {
                    finalPrice = price.multiply(new BigDecimal("0.9"));
                }
                //  Rounding to two decimals (HALF_UP)
                finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);

                // 3) possible recategorization (based on original category and post-discount price)
                String finalCategory = category;
                if (originalElectronics && finalPrice.compareTo(new BigDecimal("500.00")) == 1) {
                    // strictly greater than $500.00
                    finalCategory = "Premium Electronics";
                }

                // 4) compute PriceRange from finalPrice
                String priceRange = computePriceRange(finalPrice);

                // Ensure price printed with two decimals
                String priceOut = finalPrice.toPlainString();

                out.add(new String[] { idStr, nameUpper, priceOut, finalCategory, priceRange });

            } catch (NumberFormatException | ArithmeticException ex) {
                // invalid product id or price format -> skip row
                skipped++;
            }
        }

        return new TransformResult(out, skipped);
    }

    // Price ranges use the final (rounded) price:
    // $0.00–$10.00 → Low
    // $10.01–$100.00 → Medium
    // $100.01–$500.00 → High
    // $500.01 and above → Premium
    private static String computePriceRange(BigDecimal price) {
        BigDecimal lowUpper = new BigDecimal("10.00");
        BigDecimal medLower = new BigDecimal("10.01");
        BigDecimal medUpper = new BigDecimal("100.00");
        BigDecimal highLower = new BigDecimal("100.01");
        BigDecimal highUpper = new BigDecimal("500.00");
        BigDecimal premiumLower = new BigDecimal("500.01");

        if (price.compareTo(BigDecimal.ZERO) >= 0 && price.compareTo(lowUpper) <= 0) {
            return "Low";
        } else if (price.compareTo(medLower) >= 0 && price.compareTo(medUpper) <= 0) {
            return "Medium";
        } else if (price.compareTo(highLower) >= 0 && price.compareTo(highUpper) <= 0) {
            return "High";
        } else if (price.compareTo(premiumLower) >= 0) {
            return "Premium";
        } else {
            // for negative or unexpected values
            return "Low";
        }
    }

    // ========== Load ==========
    private static void load(List<String[]> transformedRows, Path output) throws IOException {
        // ensure parent directory exists
        if (output.getParent() != null) {
            Files.createDirectories(output.getParent());
        }

        try (BufferedWriter bw = Files.newBufferedWriter(output, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            // Always write header (as required by spec). Use required output column order.
            bw.write(OUTPUT_HEADER);
            bw.newLine();

            for (String[] row : transformedRows) {
                // join with commas (fields guaranteed not to contain commas per spec)
                bw.write(String.join(",", row));
                bw.newLine();
            }
            bw.flush();
        }
    }
}
