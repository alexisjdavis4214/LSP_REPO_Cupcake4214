package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Model representing a product row from CSV.
 */
public class Product {
    private final int productId;
    private String name;
    private BigDecimal price;
    private String category;
    private final String originalCategory;
    private String priceRange;

    public Product(int productId, String name, BigDecimal price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.originalCategory = category;
    }

    /** Parse from CSV parts: {ProductID,Name,Price,Category} */
    public static Product fromCsvParts(String[] parts) {
        if (parts.length != 4) {
            throw new IllegalArgumentException("Expected 4 fields, got: " + parts.length);
        }
        int id = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        BigDecimal price = new BigDecimal(parts[2].trim());
        String category = parts[3].trim();
        return new Product(id, name, price, category);
    }

    // Getters / setters
    public int getProductId() { return productId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getOriginalCategory() { return originalCategory; }
    public String getPriceRange() { return priceRange; }
    public void setPriceRange(String priceRange) { this.priceRange = priceRange; }

    /** Apply a percentage discount (e.g., 0.10 for 10%) and round HALF_UP to 2 decimals */
    public void applyPercentageDiscount(BigDecimal percent) {
        BigDecimal multiplier = BigDecimal.ONE.subtract(percent);
        this.price = this.price.multiply(multiplier).setScale(2, RoundingMode.HALF_UP);
    }

    /** Ensure price is rounded to 2 decimals HALF_UP */
    public void roundPrice() {
        this.price = this.price.setScale(2, RoundingMode.HALF_UP);
    }

    /** Convert object back to CSV row (fields won't contain commas per spec) */
    public String toCsvRow() {
        String priceStr = price.setScale(2, RoundingMode.HALF_UP).toPlainString();
        return productId + "," + name + "," + priceStr + "," + category + "," + priceRange;
    }
}
