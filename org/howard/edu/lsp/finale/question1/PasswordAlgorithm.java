package org.howard.edu.lsp.finale.question1;

/**
 * Strategy interface for password generation algorithms.
 */
public interface PasswordAlgorithm {
    /**
     * Generates a password of the specified length.
     * @param length the desired password length
     * @return the generated password
     */
    String generate(int length);
}
