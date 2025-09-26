# Reflection – Assignment 3 vs. Assignment 2

## 1. Design Differences
In Assignment 2, I implemented the entire ETL pipeline in a single class (`ETLPipeline`) with nested static helper classes for extraction and transformation.
While functional, this design tightly coupled the extraction, transformation, and loading steps, making it harder to test individual parts or extend the program.

In Assignment 3, I refactored the solution into separate classes:

- `ETLApp` – Main entry point that coordinates the pipeline.
- `Product` – Represents a single product with fields and price/category logic.
- `Extractor` – Reads and parses CSV input.
- `Transformer` – Handles all transformations (uppercase names, discount, recategorization, price range).
- `Loader` – Writes the transformed list back to `data/transformed_products.csv`.

This separation created clearer responsibilities for each class and allowed me to better follow object-oriented practices.

------------------------------------

## 2-3. How Assignment 3 is More Object-Oriented and what principles were used

- Encapsulation:
  Each class encapsulates its own data and behavior (e.g., `Product` stores its state and provides transformation logic; `Extractor` hides file-reading details).
  
- Objects and Classes:
  The pipeline now manipulates `Product` objects instead of raw string arrays, improving type safety and readability.

- Polymorphism and Extensibility:
  The design allows potential future enhancements, such as subclassing `Product` for different product types or swapping out the `Transformer` implementation.

- Separation of Concerns:
  The main application (`ETLApp`) delegates work to specialized components, making it easier to understand and maintain.

-----------------------------

## 4. Testing and Verification
To ensure that Assignment 3 produced identical results to Assignment 2:

1. I ran the new program on the same input file (`data/products.csv`) and compared the generated `transformed_products.csv` to the previous assignment’s output.
2. I tested edge cases:
   - Missing input file → correct error message.
   - Empty input file (header only) → correct error message.
   - Malformed rows (wrong number of columns or invalid price) → skipped as required.
3. All results matched the expected behavior from Assignment 2.