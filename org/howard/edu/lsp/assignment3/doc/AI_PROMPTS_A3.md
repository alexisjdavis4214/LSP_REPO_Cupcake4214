# AI Prompts – Assignment 3

This file lists several of the prompts I used with a generative AI assistant (ChatGPT and Claude.ai) to help me with brainstorming, code design, and documentation.



## Prompt 1
My Prompt:
> I need to redesign my ETL pipeline from Assignment 2 into a more object-oriented version in Java. It currently has one large class. Suggest a reasonable class structure with separate responsibilities.

AI Response (Excerpt):
> Suggested creating:
> - `Product` class to represent data rows.
> - `Extractor` class for reading input.
> - `Transformer` class for applying all business logic.
> - `Loader` class for writing output.
> - A main `ETLApp` class to coordinate the process.

How I Used It:
I adopted this decomposition almost entirely but adapted some method names and error handling to match project requirements.


## Prompt 2
My Prompt:
> Show me how to write a `Product` class that stores ID, name, price, and category and supports methods to apply a discount and compute the price range.

I Response:
> Gave an example `Product` class with fields, getters/setters, and a `computePriceRange()` method that returned a string value.

How I Used It:
I customized the logic for discounting electronics and recategorizing premium electronics.
I also ensured BigDecimal was used for precise rounding.


## Prompt 3
My Prompt:
> How do I properly handle a missing input file and print a clear error message before exiting?

AI Response:
> Recommended using `Files.exists()` to check for the file and catching `FileNotFoundException`.

How I Used It:
I followed this suggestion but kept the error messages consistent with Assignment 2 for testing parity.

## Prompt 4
My Prompt:
> Generate Javadoc comments for my `Extractor` and `Transformer` classes.

AI Response:
> Produced basic Javadocs describing class purpose and public methods.

How I Used It:
I reviewed and edited these comments for accuracy and consistency with our class coding standards.


## Prompt 5
My Prompt:
> Suggest tests or checks to confirm that the new OO version behaves identically to my Assignment 2 version.

AI Response:
> Suggested comparing the output CSV files, testing with empty input, and checking behavior on malformed rows.

How I Used It:
I implemented these checks to confirm correctness.

