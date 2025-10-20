package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Orchestrator service that runs extract -> transform -> load and collects statistics.
 */
public class ETLService {
    private final CsvReader reader;
    private final CompositeTransformer transformer;
    private final CsvWriter writer;

    /**
     * Construct ETL service with its components.
     *
     * @param reader CSV reader
     * @param transformer transformation pipeline
     * @param writer CSV writer
     */
    public ETLService(CsvReader reader, CompositeTransformer transformer, CsvWriter writer) {
        this.reader = reader;
        this.transformer = transformer;
        this.writer = writer;
    }

    /**
     * Run the pipeline and return a run summary string.
     *
     * @return summary string (or error message starting with "ERROR:")
     */
    public String run() {
        CsvReader.ExtractionResult ext;
        try {
            ext = reader.extract();
        } catch (IOException ioe) {
            return "ERROR: " + ioe.getMessage();
        }

        // ext.header == null means file completely empty (no header)
        if (ext.header == null) {
            // As per spec: treat missing header as error
            return "ERROR: input file is empty (no header). Expected header row.";
        }

        List<Product> transformed = new ArrayList<>();
        int skippedDuringTransform = 0;
        for (Product p : ext.products) {
            try {
                transformer.transform(p);
                transformed.add(p);
            } catch (Exception e) {
                skippedDuringTransform++;
            }
        }

        try {
            writer.write(transformed);
        } catch (IOException ioe) {
            return "ERROR writing output file: " + ioe.getMessage();
        }

        int totalSkipped = ext.skippedOnRead + skippedDuringTransform;

        StringBuilder sb = new StringBuilder();
        sb.append("Run summary:\n");
        sb.append("  Rows read (excluding header): ").append(ext.rowsRead).append("\n");
        sb.append("  Rows transformed & written : ").append(transformed.size()).append("\n");
        sb.append("  Rows skipped (malformed)   : ").append(totalSkipped).append("\n");
        sb.append("  Output written to: ").append(writerOutputPath()).append("\n");
        return sb.toString();
    }

    private String writerOutputPath() {
        // attempt to reflect writer output path; writer is package-private, so we'll rely on writer.toString
        // Instead, compose path using writer's internal field isn't accessible; return a placeholder handled by main.
        return "(see output file in data/transformed_products.csv)";
    }
}
