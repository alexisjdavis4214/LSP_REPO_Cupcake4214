Author: Alexis Davis
Course: CSCI 363/540

Assignment 1: Hello World 
===========================

Assignment 2: ETL Pipeline
===========================

Assumptions
-----------
- Input CSV (products.csv) is properly formatted with a header and no commas/quotes in fields.
- Prices are valid decimal numbers.
- Program runs from the project root (src/ and data/ are siblings).
- Missing input file or empty CSV handled gracefully.

Design Notes
------------
- Program follows Extract → Transform → Load structure:
  1. Extract: Reads CSV from data/products.csv.
  2. Transform:
     - Name → UPPERCASE
     - Electronics → 10% discount
     - Price > $500 & Electronics → Premium Electronics
     - Compute PriceRange based on final price
  3. Load: Writes transformed CSV to data/transformed_products.csv with header.
- Transformations applied in order: uppercase → discount → recategorization → price range.

How to Run
----------
Compile:
    
    mkdir -p bin
    javac -d bin -sourcepath src org/howard/edu/lsp/assignment2/ETLPipeline.java
    

Run:
    
    java -cp bin org.howard.edu.lsp.assignment2.ETLPipeline

AI Usage Summary
----------------
Tool Used: ChatGPT

Purpose:
- Troubleshoot Java compilation errors with packages and classpath.
- Guidance for adding main method and project structure.
- Helped write README structure and content.

Example Prompt:
"How do I compile and run a Java class with package org.howard.edu.lsp.assignment2 from the command line on macOS?"

Excerpt from AI Response:
"Use javac -d bin -sourcepath src src/org/howard/edu/lsp/assignment2/ETLPipeline.java to compile, then run with java -cp bin org.howard.edu.lsp.assignment2.ETLPipeline."

How Used:
- Followed instructions, modified for this project’s folder structure, added main method.

External Sources
----------------
N/A



Assignment 3: OO Redesign of ETL Pipeline
=========================================

Assumptions
-----------
- Input CSV (products.csv) remains in the project’s data/ directory with a valid header and no embedded commas/quotes.
- Prices are valid decimal numbers.
- Program runs from the project root so that src/ and data/ are siblings.
- Missing or empty input file is handled gracefully as in Assignment 2.

Design Notes
------------
- The ETL pipeline was redesigned with object-oriented decomposition.
- Main components:
  1. **Product** – represents a single product and its attributes.
  2. **Extractor** – reads products.csv and converts each valid row into a Product.
  3. **Transformer** – applies transformations:
       - Uppercases product names
       - Applies 10% discount for Electronics
       - Recategorizes as Premium Electronics if discounted price > $500
       - Computes PriceRange (Low, Medium, High, Premium)
  4. **Loader** – writes transformed data to data/transformed_products.csv
  5. **ETLApp (main class)** – orchestrates Extract → Transform → Load steps.
- This redesign improves **encapsulation** and **separation of concerns** compared to the single-file Assignment 2 solution.

How to Compile & Run
--------------------
Compile from project root:
    
    mkdir -p bin
    javac -d bin -sourcepath src src/org/howard/edu/lsp/assignment3/*.java

Run from project root:
    
    java -cp bin org.howard.edu.lsp.assignment3.ETLApp

(Ensure data/products.csv exists in the data/ directory.)


## Assignment 6: Integer Set Implementation with JUnit Testing

### Assumptions
* IntegerSet stores unique integers only (no duplicates allowed).
* `largest()` and `smallest()` throw `IllegalStateException` if the set is empty.
* `equals(Object o)` compares set contents in an order-independent manner.
* `toString()` returns elements in square brackets format (e.g., `[1, 2, 3]`).
* All mutator methods (`add`, `remove`, `union`, `intersect`, `diff`, `complement`) modify the current instance rather than returning a new object.
* JUnit 5 is used for testing.

### Design Notes
* **IntegerSet** uses an `ArrayList<Integer>` as the internal data structure to store elements.
* **No duplicates:** The `add()` method checks if an element exists before adding.
* **Set operations** leverage ArrayList methods:
  * `union()` - iterates through the other set and adds unique elements
  * `intersect()` - uses `retainAll()` to keep only common elements
  * `diff()` - uses `removeAll()` to remove elements found in the other set
  * `complement()` - creates a new list from other set and removes elements from this set
* **Exception handling:** `largest()` and `smallest()` validate that the set is non-empty before proceeding.
* **equals() implementation:** Compares size first, then uses `containsAll()` for order-independent equality.
* **Comprehensive testing:** 31 JUnit test cases cover:
  * Normal operations (add, remove, contains, length, isEmpty, clear)
  * Edge cases (empty sets, single elements, duplicates)
  * Exception scenarios (operations on empty sets)
  * Set operations (union, intersect, diff, complement)
  * Equality and string representation

### How to Compile & Run

#### From Command Line:

**Compile:**
```bash
javac -cp "lib/junit-platform-console-standalone-1.10.1.jar:." \
  org/howard/edu/lsp/assignment6/*.java
```

**Run Tests:**
```bash
java -jar lib/junit-platform-console-standalone-1.10.1.jar \
  --class-path ".:lib/*" \
  --select-class org.howard.edu.lsp.assignment6.IntegerSetTest
```

#### From VS Code:
1. Open `IntegerSetTest.java`
2. Click "Run Test" button above the class or individual test methods
3. View results in the Test Explorer panel

### Test Results
```
[        31 tests found           ]
[         0 tests skipped         ]
[        31 tests started         ]
[         0 tests aborted         ]
[        31 tests successful      ]
[         0 tests failed          ]
```

All 31 tests pass, covering:
* 7 basic operations tests (clear, length, isEmpty, contains, add, remove, toString)
* 6 largest/smallest tests (including exception handling)
* 5 equals tests (empty sets, null, same reference, equal/unequal sets)
* 13 set operation tests (union, intersect, diff, complement with various scenarios)

### AI Usage Summary

**Tool Used:** Claude (Anthropic)

**Purpose:**
* Guidance on JUnit 5 setup and configuration in VS Code
* Troubleshooting compilation errors related to missing JUnit dependencies
* Help structuring comprehensive test cases for edge cases
* Verification that all design rules were properly implemented
* Assistance with command-line compilation and test execution

**Example Prompt:** "How do I set up JUnit 5 for a Java project in VS Code on macOS? I'm getting 'package org.junit.jupiter.api does not exist' errors."

**Excerpt from AI Response:** "The issue is that JUnit 5 is not installed in your project. Download the junit-platform-console-standalone jar file and add it to your classpath..."

**How Used:**
* Downloaded JUnit 5 standalone JAR as instructed
* Added proper imports (`org.junit.jupiter.api.*` instead of `org.junit.*`)
* Followed compilation commands with correct classpath configuration
* Created comprehensive test suite based on suggested test patterns

