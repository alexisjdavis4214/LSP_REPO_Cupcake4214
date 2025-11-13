package org.howard.edu.lsp.assignment6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for IntegerSet.
 * Contains comprehensive unit tests for all public methods.
 * Tests cover normal cases, edge cases, and exception handling.
 * 
 * @author Alexis Davis
 */
public class IntegerSetTest {
    
    private IntegerSet set1;
    private IntegerSet set2;
    
    /**
     * Sets up test fixtures before each test method.
     * Initializes two empty IntegerSet objects.
     */
    @BeforeEach
    public void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
    }
    
    /**
     * Tests the clear method.
     * Verifies that clear removes all elements from the set.
     */
    @Test
    @DisplayName("Test clear method")
    public void testClear() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.clear();
        assertEquals(0, set1.length());
        assertTrue(set1.isEmpty());
    }
    
    /**
     * Tests the length method.
     * Verifies that length returns the correct number of elements.
     */
    @Test
    @DisplayName("Test length method")
    public void testLength() {
        assertEquals(0, set1.length());
        set1.add(1);
        assertEquals(1, set1.length());
        set1.add(2);
        assertEquals(2, set1.length());
        set1.add(1); // duplicate should not increase length
        assertEquals(2, set1.length());
    }
    
    /**
     * Tests the isEmpty method.
     * Verifies correct behavior for empty and non-empty sets.
     */
    @Test
    @DisplayName("Test isEmpty method")
    public void testIsEmpty() {
        assertTrue(set1.isEmpty());
        set1.add(1);
        assertFalse(set1.isEmpty());
        set1.remove(1);
        assertTrue(set1.isEmpty());
    }
    
    /**
     * Tests the contains method.
     * Verifies that contains correctly identifies present and absent elements.
     */
    @Test
    @DisplayName("Test contains method")
    public void testContains() {
        assertFalse(set1.contains(1));
        set1.add(1);
        assertTrue(set1.contains(1));
        assertFalse(set1.contains(2));
    }
    
    /**
     * Tests the add method.
     * Verifies that add prevents duplicate elements.
     */
    @Test
    @DisplayName("Test add method with no duplicates")
    public void testAdd() {
        set1.add(1);
        assertTrue(set1.contains(1));
        assertEquals(1, set1.length());
        
        set1.add(1); // attempt to add duplicate
        assertEquals(1, set1.length());
        
        set1.add(2);
        assertEquals(2, set1.length());
        assertTrue(set1.contains(2));
    }
    
    /**
     * Tests the remove method.
     * Verifies that remove correctly removes present elements and handles absent elements.
     */
    @Test
    @DisplayName("Test remove method")
    public void testRemove() {
        set1.add(1);
        set1.add(2);
        set1.remove(1);
        assertFalse(set1.contains(1));
        assertEquals(1, set1.length());
        
        set1.remove(99); // remove element not in set
        assertEquals(1, set1.length());
    }
    
    /**
     * Tests the largest method with normal input.
     * Verifies that largest returns the maximum element.
     */
    @Test
    @DisplayName("Test largest method")
    public void testLargest() {
        set1.add(3);
        set1.add(1);
        set1.add(5);
        set1.add(2);
        assertEquals(5, set1.largest());
    }
    
    /**
     * Tests that largest throws IllegalStateException on empty set.
     */
    @Test
    @DisplayName("Test largest throws exception on empty set")
    public void testLargestException() {
        assertThrows(IllegalStateException.class, () -> {
            set1.largest();
        });
    }
    
    /**
     * Tests the largest method with negative numbers.
     */
    @Test
    @DisplayName("Test largest with negative numbers")
    public void testLargestNegative() {
        set1.add(-5);
        set1.add(-1);
        set1.add(-10);
        assertEquals(-1, set1.largest());
    }
    
    /**
     * Tests the smallest method with normal input.
     * Verifies that smallest returns the minimum element.
     */
    @Test
    @DisplayName("Test smallest method")
    public void testSmallest() {
        set1.add(3);
        set1.add(1);
        set1.add(5);
        set1.add(2);
        assertEquals(1, set1.smallest());
    }
    
    /**
     * Tests that smallest throws IllegalStateException on empty set.
     */
    @Test
    @DisplayName("Test smallest throws exception on empty set")
    public void testSmallestException() {
        assertThrows(IllegalStateException.class, () -> {
            set1.smallest();
        });
    }
    
    /**
     * Tests the smallest method with negative numbers.
     */
    @Test
    @DisplayName("Test smallest with negative numbers")
    public void testSmallestNegative() {
        set1.add(-5);
        set1.add(-1);
        set1.add(-10);
        assertEquals(-10, set1.smallest());
    }
    
    /**
     * Tests the equals method with equal sets.
     * Verifies that order does not matter for equality.
     */
    @Test
    @DisplayName("Test equals method with equal sets")
    public void testEquals() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(3);
        set2.add(2);
        set2.add(1);
        
        assertTrue(set1.equals(set2));
        assertTrue(set2.equals(set1));
    }
    
    /**
     * Tests the equals method with unequal sets.
     */
    @Test
    @DisplayName("Test equals with unequal sets")
    public void testEqualsUnequal() {
        set1.add(1);
        set1.add(2);
        
        set2.add(1);
        set2.add(3);
        
        assertFalse(set1.equals(set2));
    }
    
    /**
     * Tests the equals method with empty sets.
     */
    @Test
    @DisplayName("Test equals with empty sets")
    public void testEqualsEmpty() {
        assertTrue(set1.equals(set2));
    }
    
    /**
     * Tests the equals method with null.
     */
    @Test
    @DisplayName("Test equals with null")
    public void testEqualsNull() {
        set1.add(1);
        assertFalse(set1.equals(null));
    }
    
    /**
     * Tests the equals method with same object reference.
     */
    @Test
    @DisplayName("Test equals with same reference")
    public void testEqualsSameReference() {
        set1.add(1);
        assertTrue(set1.equals(set1));
    }
    
    /**
     * Tests the toString method.
     * Verifies that toString returns proper format.
     */
    @Test
    @DisplayName("Test toString method")
    public void testToString() {
        set1.add(1);
        set1.add(2);
        String result = set1.toString();
        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
        assertTrue(result.startsWith("["));
        assertTrue(result.endsWith("]"));
    }
    
    /**
     * Tests the toString method with empty set.
     */
    @Test
    @DisplayName("Test toString with empty set")
    public void testToStringEmpty() {
        assertEquals("[]", set1.toString());
    }
    
    /**
     * Tests the union method.
     * Verifies that union combines elements from both sets.
     */
    @Test
    @DisplayName("Test union method")
    public void testUnion() {
        set1.add(1);
        set1.add(2);
        
        set2.add(2);
        set2.add(3);
        
        set1.union(set2);
        
        assertEquals(3, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
    }
    
    /**
     * Tests the union method with empty set.
     */
    @Test
    @DisplayName("Test union with empty set")
    public void testUnionEmpty() {
        set1.add(1);
        set1.add(2);
        set1.union(set2);
        assertEquals(2, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
    }
    
    /**
     * Tests the union method with disjoint sets.
     */
    @Test
    @DisplayName("Test union with disjoint sets")
    public void testUnionDisjoint() {
        set1.add(1);
        set1.add(2);
        
        set2.add(3);
        set2.add(4);
        
        set1.union(set2);
        assertEquals(4, set1.length());
    }
    
    /**
     * Tests the intersect method.
     * Verifies that intersect keeps only common elements.
     */
    @Test
    @DisplayName("Test intersect method")
    public void testIntersect() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(2);
        set2.add(3);
        set2.add(4);
        
        set1.intersect(set2);
        
        assertEquals(2, set1.length());
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertFalse(set1.contains(1));
        assertFalse(set1.contains(4));
    }
    
    /**
     * Tests the intersect method with disjoint sets.
     */
    @Test
    @DisplayName("Test intersect with disjoint sets")
    public void testIntersectDisjoint() {
        set1.add(1);
        set1.add(2);
        
        set2.add(3);
        set2.add(4);
        
        set1.intersect(set2);
        assertTrue(set1.isEmpty());
    }
    
    /**
     * Tests the intersect method with empty set.
     */
    @Test
    @DisplayName("Test intersect with empty set")
    public void testIntersectEmpty() {
        set1.add(1);
        set1.add(2);
        
        set1.intersect(set2);
        assertTrue(set1.isEmpty());
    }
    
    /**
     * Tests the diff method.
     * Verifies that diff removes elements present in other set.
     */
    @Test
    @DisplayName("Test diff method")
    public void testDiff() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(2);
        set2.add(3);
        
        set1.diff(set2);
        
        assertEquals(1, set1.length());
        assertTrue(set1.contains(1));
        assertFalse(set1.contains(2));
        assertFalse(set1.contains(3));
    }
    
    /**
     * Tests the diff method with disjoint sets.
     */
    @Test
    @DisplayName("Test diff with disjoint sets")
    public void testDiffDisjoint() {
        set1.add(1);
        set1.add(2);
        
        set2.add(3);
        set2.add(4);
        
        set1.diff(set2);
        assertEquals(2, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
    }
    
    /**
     * Tests the diff method with empty set.
     */
    @Test
    @DisplayName("Test diff with empty set")
    public void testDiffEmpty() {
        set1.add(1);
        set1.add(2);
        
        set1.diff(set2);
        assertEquals(2, set1.length());
    }
    
    /**
     * Tests the complement method.
     * Verifies that complement produces other minus this.
     */
    @Test
    @DisplayName("Test complement method")
    public void testComplement() {
        set1.add(1);
        set1.add(2);
        
        set2.add(2);
        set2.add(3);
        set2.add(4);
        
        set1.complement(set2);
        
        assertEquals(2, set1.length());
        assertTrue(set1.contains(3));
        assertTrue(set1.contains(4));
        assertFalse(set1.contains(1));
        assertFalse(set1.contains(2));
    }
    
    /**
     * Tests the complement method with disjoint sets.
     */
    @Test
    @DisplayName("Test complement with disjoint sets")
    public void testComplementDisjoint() {
        set1.add(1);
        set1.add(2);
        
        set2.add(3);
        set2.add(4);
        
        set1.complement(set2);
        assertEquals(2, set1.length());
        assertTrue(set1.contains(3));
        assertTrue(set1.contains(4));
    }
    
    /**
     * Tests the complement method with empty other set.
     */
    @Test
    @DisplayName("Test complement with empty other set")
    public void testComplementEmptyOther() {
        set1.add(1);
        set1.add(2);
        
        set1.complement(set2);
        assertTrue(set1.isEmpty());
    }
}