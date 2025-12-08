package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Enhanced algorithm that generates passwords with A-Z, a-z, and 0-9.
 */
public class EnhancedAlgorithm implements PasswordAlgorithm {
    private static final String ALLOWED = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
        "abcdefghijklmnopqrstuvwxyz" +
        "0123456789";
    private final SecureRandom secureRandom = new SecureRandom();
    
    @Override
    public String generate(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(ALLOWED.length());
            password.append(ALLOWED.charAt(index));
        }
        return password.toString();
    }
}