package org.howard.edu.lsp.assignment3;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Orchestrates extract -> transform -> load.
 */
public class ETLService {
    private final CsvReader reader;
    private final CompositeTransformer transformer;
    private final CsvWriter writer;

    public ETLService(CsvReader reader, CompositeTransformer transformer, CsvWriter writer) {
        this.reader = reader;
        this.transformer = transformer;
        this.writer = writer;
    }

    /** Runs the pipeline and returns a short summary string. */
    public String run() {
        CsvReader.ExtractionResult ext;
        try {
            ext = reader.extract();
        } catch (IOException ioe) {
            return "ERROR: " + ioe.getMessage();
        }

        if (ext.header == null) {
            return "ERROR: input file is empty (no header).";
        }

        List<Product> transformed = new ArrayList<>();
        int skipped = 0;
        for (Product p : ext.products) {
            try {
                transformer.transform(p);
                transformed.add(p);
            } catch (Exception e) {
                skipped++;
            }
        }

        try {
            writer.write(transformed);
        } catch (IOException ioe) {
            return "ERROR writing output file: " + ioe.getMessage();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Run summary:\n");
        sb.append("  Rows read (excluding header): ").append(ext.rowsRead).append("\n");
        sb.append("  Rows transformed & written : ").append(transformed.size()).append("\n");
        sb.append("  Rows skipped (malformed)   : ").append(skipped).append("\n");
        return sb.toString();
    }
}
