package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.List;

/**
 * IntegerSet class represents a mathematical set of integers.
 * A set cannot contain duplicate values.
 * Provides standard set operations including union, intersection, difference, and complement.
 * 
 * @author Alexis Davis
 */
public class IntegerSet {
    private List<Integer> set = new ArrayList<Integer>();

    /**
     * Clears the internal representation of the set.
     * Removes all elements from the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     * 
     * @return the size of the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if the 2 sets are equal, false otherwise.
     * Two sets are equal if they contain all of the same values in ANY order.
     * This overrides the equals method from the Object class.
     * 
     * @param o the object to compare with this set
     * @return true if the sets are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof IntegerSet)) return false;
        
        IntegerSet other = (IntegerSet) o;
        if (this.set.size() != other.set.size()) return false;
        
        return this.set.containsAll(other.set) && other.set.containsAll(this.set);
    }

    /**
     * Returns true if the set contains the value, otherwise false.
     * 
     * @param value the value to check for membership in the set
     * @return true if the set contains the value, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest item in the set.
     * 
     * @return the largest integer in the set
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        int max = set.get(0);
        for (int num : set) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    /**
     * Returns the smallest item in the set.
     * 
     * @return the smallest integer in the set
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        int min = set.get(0);
        for (int num : set) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    /**
     * Adds an item to the set or does nothing if already present.
     * Ensures no duplicate values are added to the set.
     * 
     * @param item the item to add to the set
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set or does nothing if not there.
     * 
     * @param item the item to remove from the set
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Set union: modifies this set to contain all unique elements in this or other.
     * Performs the union operation: this = this ∪ other
     * 
     * @param other the set to union with this set
     */
    public void union(IntegerSet other) {
        for (int item : other.set) {
            if (!this.set.contains(item)) {
                this.set.add(item);
            }
        }
    }

    /**
     * Set intersection: modifies this set to contain only elements in both sets.
     * Performs the intersection operation: this = this ∩ other
     * 
     * @param other the set to intersect with this set
     */
    public void intersect(IntegerSet other) {
        set.retainAll(other.set);
    }

    /**
     * Set difference (this \ other): modifies this set to remove elements found in other.
     * Performs the difference operation: this = this - other
     * 
     * @param other the set whose elements will be removed from this set
     */
    public void diff(IntegerSet other) {
        set.removeAll(other.set);
    }

    /**
     * Set complement: modifies this set to become (other \ this).
     * Performs the complement operation: this = other - this
     * 
     * @param other the set to take complement from
     */
    public void complement(IntegerSet other) {
        List<Integer> result = new ArrayList<>(other.set);
        result.removeAll(this.set);
        this.set = result;
    }

    /**
     * Returns true if the set is empty, false otherwise.
     * 
     * @return true if the set contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a String representation of the set.
     * Overrides Object.toString().
     * 
     * @return string representation of the set in format [element1, element2, ...]
     */
    @Override
    public String toString() {
        return set.toString();
    }
}