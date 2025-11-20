# README — Code Review Proposals


### **1. Duplicate text-cleaning logic should be extracted into one method**
Cleaning text is performed twice; it should be centralized to avoid repetition.

### **2. Hard-coded file path reduces flexibility**
`"src/edu/pro/txt/harry.txt"` should be a constant or external parameter.

### **3. Distinct-word detection via string `.contains()` is incorrect**
This approach may match substrings (e.g., `"he"` inside `"the"`), leading to errors.

### **4. Distinct-word detection using a growing string is inefficient**
String concatenation in a loop creates many temporary objects; collections should be used instead.

### **5. Counting frequencies with nested loops is highly inefficient**
The O(n²) algorithm should be replaced by a `Map<String, Long>` frequency table.

### **6. All logic is placed inside `main()`**
Processing, counting, sorting, and output should be extracted into separate helper methods.

### **7. Sorting strings using regex to extract numbers is fragile**
Sorting by stripping characters inside strings is unreliable; sorting should operate on structured data.

### **8. Lack of descriptive variable names reduces readability**
Names like `distincts`, `freq`, and `string` should be replaced with clearer identifiers.

### **9. No validation for empty or malformed input**
The program assumes the file always exists and contains valid text.

### **10. Unnecessary array sorting before frequency counting**
Sorting words before processing adds cost but offers no functional benefit.

### **11. No use of Java Streams for common operations**
Counting and sorting can be simplified and optimized with Streams.

### **12. Magic regex `[^A-Za-z ]` should be defined as a constant**
This improves readability and eliminates repetition.

### **13. Variables and logic use minimal comments**
Key steps (reading, cleaning, counting, sorting) should be documented for clarity.

### **14. No separation between data processing and output**
Computation and printing are mixed together, making the code harder to reuse or test.

---
