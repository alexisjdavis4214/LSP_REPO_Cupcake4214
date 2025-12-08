package org.howard.edu.lsp.finale.question1;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton service for generating passwords using pluggable algorithms.
 * 
 * DESIGN PATTERN DOCUMENTATION:
 * 
 * 1. PATTERNS USED:
 *    - Singleton Pattern: Ensures only one instance of PasswordGeneratorService exists
 *    - Strategy Pattern: Allows runtime selection of password generation algorithms
 * 
 * 2. WHY THESE PATTERNS ARE APPROPRIATE:
 *    - Singleton: The requirement states "Only one instance of the service may exist"
 *      and "Provide a single shared access point." This makes Singleton the natural
 *      choice to guarantee a single instance and global access point.
 *    
 *    - Strategy: The requirements specify "Support multiple approaches to password
 *      generation," "Allow the caller to select the approach at run time," 
 *      "Make password-generation behavior swappable," and "Support future expansion
 *      of password-generation approaches." The Strategy pattern encapsulates each
 *      algorithm in its own class, making them interchangeable and allowing new
 *      algorithms to be added without modifying existing code (Open/Closed Principle).
 */
public class PasswordGeneratorService {
    
    // Singleton instance
    private static PasswordGeneratorService instance;
    
    // Strategy pattern: current algorithm
    private PasswordAlgorithm currentAlgorithm;
    
    // Registry of available algorithms
    private final Map<String, PasswordAlgorithm> algorithms;
    
    /**
     * Private constructor to prevent external instantiation.
     */
    private PasswordGeneratorService() {
        algorithms = new HashMap<>();
        // Register the three required algorithms
        algorithms.put("basic", new BasicAlgorithm());
        algorithms.put("enhanced", new EnhancedAlgorithm());
        algorithms.put("letters", new LettersAlgorithm());
    }
    
    /**
     * Returns the singleton instance of PasswordGeneratorService.
     * @return the singleton instance
     */
    public static PasswordGeneratorService getInstance() {
        if (instance == null) {
            instance = new PasswordGeneratorService();
        }
        return instance;
    }
    
    /**
     * Selects the password generation algorithm by name.
     * @param name the algorithm name ("basic", "enhanced", or "letters")
     * @throws IllegalArgumentException if the algorithm name is not recognized
     */
    public void setAlgorithm(String name) {
        PasswordAlgorithm algorithm = algorithms.get(name);
        if (algorithm == null) {
            throw new IllegalArgumentException("Unknown algorithm: " + name);
        }
        this.currentAlgorithm = algorithm;
    }
    
    /**
     * Generates a password of the specified length using the current algorithm.
     * @param length the desired password length
     * @return the generated password
     * @throws IllegalStateException if no algorithm has been set
     */
    public String generatePassword(int length) {
        if (currentAlgorithm == null) {
            throw new IllegalStateException("No algorithm selected. Call setAlgorithm() first.");
        }
        return currentAlgorithm.generate(length);
    }
}