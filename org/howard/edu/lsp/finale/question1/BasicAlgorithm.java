package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Basic algorithm that generates passwords with digits only (0-9).
 */
public class BasicAlgorithm implements PasswordAlgorithm {
    private static final String DIGITS = "0123456789";
    private final Random random = new Random();
    
    @Override
    public String generate(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(DIGITS.length());
            password.append(DIGITS.charAt(index));
        }
        return password.toString();
    }
}
