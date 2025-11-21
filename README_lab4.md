# README — Time Optimization Proposals

### **1. Replace `String.split()` with a manual character-by-character parser**
`split()` uses regex internally, which is slow on large text files. A custom parser avoids regex overhead and significantly reduces processing time.

### **2. Avoid using `replaceAll()` for cleaning text**
`replaceAll()` compiles a regex pattern every time, creating heavy performance penalties. Manual filtering of characters is faster.

### **3. Pre-allocate `HashMap` capacity**
Setting the initial capacity (e.g., 60,000) reduces the number of rehashing operations and speeds up inserts.

### **4. Use a single reusable `StringBuilder` for word assembly**
Creating many string objects inside loops slows down execution. Reusing a small `StringBuilder` per line reduces GC pressure.

### **5. Replace Stream API sorting with `Collections.sort()`**
Streams create additional intermediate lists and lambdas. Direct sorting of a list is faster for large datasets.

### **6. Use manual lowercase conversion for ASCII letters**
`(char)(c + 32)` is much faster than `Character.toLowerCase()` and avoids extra method calls.

### **7. Process file line-by-line using `BufferedReader`**
Loading the whole file into memory is slower and unnecessary. Reading per-line improves both speed and memory locality.

### **8. Avoid creating temporary strings during text cleaning**
Appending only necessary characters directly into `StringBuilder` avoids intermediate garbage.

### **9. Use `freq.merge()` instead of manual checks**
`merge()` is optimized in the HashMap implementation and avoids multiple lookups per word.

### **10. Avoid converting the full map to a stream**
Stream operations introduce overhead. Working directly with `new ArrayList<>(map.entrySet())` is faster.

### **11. Use early charset fallback to avoid repeated reads**
Trying several encodings in sequence avoids re-reading large files multiple times and improves startup time.

### **12. Limit output processing**
Printing only the top 30 sorted entries avoids formatting and iteration of the entire dataset unnecessarily.

---
