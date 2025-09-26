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

