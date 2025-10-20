package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Model representing a product row from the CSV.
 * Encapsulates fields and provides small helper methods for price operations.
 */
public class Product {
    private final int productId;
    private String name;
    private BigDecimal price;
    private String category;
    private final String originalCategory;
    private String priceRange;

    /**
     * Construct a Product.
     *
     * @param productId numeric product id
     * @param name product name
     * @param price product price as BigDecimal
     * @param category product category
     */
    public Product(int productId, String name, BigDecimal price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.originalCategory = category;
    }

    /**
     * Parse a Product from CSV parts array: {ProductID,Name,Price,Category}.
     *
     * @param parts array of four strings
     * @return Product instance
     * @throws NumberFormatException if ProductID or Price cannot be parsed
     * @throws IllegalArgumentException if parts length != 4
     */
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

    // Getters and setters

    /** @return product id */
    public int getProductId() { return productId; }

    /** @return product name */
    public String getName() { return name; }

    /** @param name set product name */
    public void setName(String name) { this.name = name; }

    /** @return product price */
    public BigDecimal getPrice() { return price; }

    /** @param price set product price */
    public void setPrice(BigDecimal price) { this.price = price; }

    /** @return current category */
    public String getCategory() { return category; }

    /** @param category set current category */
    public void setCategory(String category) { this.category = category; }

    /** @return original category from input */
    public String getOriginalCategory() { return originalCategory; }

    /** @return assigned price range label */
    public String getPriceRange() { return priceRange; }

    /** @param priceRange set price range label */
    public void setPriceRange(String priceRange) { this.priceRange = priceRange; }

    /**
     * Apply a percentage discount and round to two decimals (HALF_UP).
     * Example: percent = 0.10 applies a 10% discount.
     *
     * @param percent fraction to discount (0.10 for 10%)
     */
    public void applyPercentageDiscount(java.math.BigDecimal percent) {
        java.math.BigDecimal multiplier = java.math.BigDecimal.ONE.subtract(percent);
        this.price = this.price.multiply(multiplier).setScale(2, RoundingMode.HALF_UP);
    }

    /** Round price to two decimals using HALF_UP. */
    public void roundPrice() {
        this.price = this.price.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Convert product back to CSV row in required output order:
     * ProductID,Name,Price,Category,PriceRange
     *
     * @return CSV string for this product
     */
    public String toCsvRow() {
        String priceStr = price.setScale(2, RoundingMode.HALF_UP).toPlainString();
        return productId + "," + name + "," + priceStr + "," + category + "," + priceRange;
    }
}