# README — Memory Optimization Proposals


### **1. Read the file line by line instead of loading the entire file into memory**
This avoids creating a huge string for large files, significantly reducing RAM usage.

### **2. Use a single `HashMap` for word frequencies instead of multiple arrays**
Storing frequencies in arrays and separate distinct-word lists consumes extra memory unnecessarily.

### **3. Avoid creating temporary strings in loops**
Instead of concatenating strings to detect distinct words, use collections (`HashMap` or `Set`) for efficiency.

### **4. Replace nested loops for frequency counting with a single pass**
Iterating twice over all words is memory and CPU intensive; counting while reading reduces memory footprint.

### **5. Use a simple character-by-character cleaning method instead of regex**
Regex on large text creates many temporary objects; manual cleaning reduces memory allocation.

### **6. Process and count words without storing all words in an array**
Split and count words directly from each line to minimize memory overhead.

### **7. Sort only the entries of the frequency map after counting**
Sorting the entire list of words before counting wastes memory; sorting the smaller frequency map is more efficient.

---
