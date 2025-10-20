package org.howard.edu.lsp.midterm.question2;

public class Main {
    public static void main(String[] args) {
        /*
         * Overloading is better than using different method names (rectangleArea, circleArea, etc.)
         * because it provides a cleaner, more intuitive API where all area calculations use the same
         * method name. This improves code readability and follows the principle of polymorphism,
         * allowing the compiler to select the correct method based on the argument types.
         */
        
        // Test all area methods with valid inputs
        System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 → area = " + AreaCalculator.area(4));
        
        // Test exception handling with invalid input
        try {
            AreaCalculator.area(-5.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Cannot calculate area with negative or zero dimensions.");
        }
    }
}