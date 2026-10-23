# Print Job Management System (Java - Data Structures)

A console-based Java application that simulates a shared network printer queue, utilizing the Queue data structure and advanced sorting algorithms to manage print jobs based on the First-In-First-Out (FIFO) principle.

## 🚀 Features
- **Enqueue (Insertion):** Add new print jobs dynamically with custom file names and page counts.
- **Dequeue (Deletion):** Process and remove jobs from the front of the queue, featuring robust Underflow error handling.
- **Queue Traversal:** Sequential processing of all remaining print jobs.
- **Linear Search:** Search for specific print jobs by file name.
- **Priority-Based Sorting:** Implements the **Merge Sort** algorithm to rearrange the queue by priority (shortest files with lowest page counts first).

## 📊 Time Complexity Analysis

| Algorithm | Time Complexity | Notes |
| :--- | :--- | :--- |
| **Insertion (Enqueue)** | $O(1)$ | Constant time pointer update |
| **Deletion (Dequeue)** | $O(1)$ | Constant time front removal |
| **Searching** | $O(n)$ | Linear scan in worst case |
| **Sorting (Merge Sort)**| $O(n \log n)$ | Efficient divide-and-conquer approach |

## 🛠️ Tech Stack
- **Language:** Java Core
- **Concepts:** Queues, LinkedList, Algorithm Analysis, Recursion
