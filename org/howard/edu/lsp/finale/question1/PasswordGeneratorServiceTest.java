package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PasswordGeneratorServiceTest {
    
    private PasswordGeneratorService service;
    
    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
    }
    
    @Test
    public void checkInstanceNotNull() {
        assertNotNull(service, "Service instance should not be null");
    }
    
    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        // Verify they are the SAME object in memory (not just equal)
        assertSame(service, second, "Both references should point to the same singleton instance");
    }
    
    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();
        // Verify that calling generatePassword without setting algorithm throws IllegalStateException
        assertThrows(IllegalStateException.class, () -> {
            s.generatePassword(10);
        }, "Should throw IllegalStateException when no algorithm is set");
    }
    
    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);
        
        // Verify correct length
        assertEquals(10, p.length(), "Password should have length 10");
        
        // Verify all characters are digits
        assertTrue(p.matches("[0-9]+"), "Password should contain only digits");
    }
    
    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);
        
        // Verify correct length
        assertEquals(12, p.length(), "Password should have length 12");
        
        // Verify all characters are alphanumeric
        assertTrue(p.matches("[A-Za-z0-9]+"), "Password should contain only letters and digits");
    }
    
    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);
        
        // Verify correct length
        assertEquals(8, p.length(), "Password should have length 8");
        
        // Verify all characters are letters
        assertTrue(p.matches("[A-Za-z]+"), "Password should contain only letters");
    }
    
    @Test
    public void switchingAlgorithmsChangesBehavior() {
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);
        
        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);
        
        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);
        
        // Verify p1 contains only digits
        assertTrue(p1.matches("[0-9]+"), "Basic algorithm should generate only digits");
        
        // Verify p2 contains only letters
        assertTrue(p2.matches("[A-Za-z]+"), "Letters algorithm should generate only letters");
        
        // Verify p3 contains alphanumeric characters
        assertTrue(p3.matches("[A-Za-z0-9]+"), "Enhanced algorithm should generate alphanumeric characters");
    }
}
