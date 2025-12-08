package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Letters algorithm that generates passwords with letters only (A-Z, a-z).
 */
public class LettersAlgorithm implements PasswordAlgorithm {
    private static final String LETTERS = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
        "abcdefghijklmnopqrstuvwxyz";
    private final Random random = new Random();
    
    @Override
    public String generate(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(LETTERS.length());
            password.append(LETTERS.charAt(index));
        }
        return password.toString();
    }
}