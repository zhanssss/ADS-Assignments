# Sorting and Searching Algorithm Analysis System

## Project Overview

Github Link: https://github.com/zhanssss/ADS-Assignments/tree/master/Assignment-4

This project compares three algorithms:

- Insertion Sort as a basic sorting algorithm
- Heap Sort as an advanced sorting algorithm
- Binary Search as a searching algorithm

The purpose of this project is to analyze how different algorithms perform on arrays of different sizes and input types.

## Selected Algorithms

### Insertion Sort

Insertion Sort builds a sorted part of the array one element at a time. It compares the current element with previous elements and shifts them until the correct position is found.

Time complexity:

- O(n²)

### Heap Sort

Heap Sort uses a heap data structure. First, it builds a max heap. Then it repeatedly moves the largest element to the end of the array and heapifies the remaining part.

Time complexity:

- O(n log n)

### Binary Search

Binary Search works only on a sorted array. It repeatedly checks the middle element and removes half of the search area.

Time complexity:

- O(log n)

## Experimental Results

| Size | Input Type | Insertion Sort |      Heap Sort |
|---|---|---------------:|---------------:|
| 10 | Random |        3600 ns |        2900 ns |
| 10 | Sorted |         400 ns |        2400 ns |
| 100 | Random | 98600 ns | 58900 ns |
| 100 | Sorted | 3100 ns | 63100 ns |
| 1000 | Random | 2537700 ns | 100900 ns |
| 1000 | Sorted | 3100 ns | 75400 ns |

## Searching Results

| Size | Algorithm | Time |
|---|---|---:|
| 10 | Binary Search | 335400 ns |
| 100 | Binary Search | 600 ns |
| 1000 | Binary Search | 800 ns |

## Analysis

Heap Sort usually performs faster than Insertion Sort on large arrays because Heap Sort has O(n log n) complexity, while Insertion Sort has O(n²) complexity in average and worst cases.

When the input size grows, Insertion Sort becomes much slower because it may need many comparisons and swaps. Heap Sort grows more slowly because it divides work using the heap structure.

Sorted input affects Insertion Sort positively. If the array is already sorted, Insertion Sort can work close to O(n). Heap Sort usually stays O(n log n), even if the input is already sorted.

The results mostly match the expected Big-O complexity. Small arrays may show unstable results because execution time is very small and can be affected by CPU, memory, and JVM behavior.

Binary Search is efficient because it eliminates half of the array on every step. However, Binary Search requires a sorted array because it decides whether to continue searching on the left or right side based on the middle element.

## Screenshots

Example:
## Test run 1
![Program output](docs/screenshots/1.png)
## Test run 2
![Program output](docs/screenshots/2.png)
## Test run 3
![Program output](docs/screenshots/3.png)

## Reflection

During this assignment, I learned how different algorithms behave with different input sizes. I saw that theoretical complexity becomes more visible when the input size becomes larger.

The main challenge was measuring execution time correctly because sorting changes the original array. To solve this, I used array copies with clone() for fair comparison.