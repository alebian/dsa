# Techniques

## Two pointers (or more)

Two Pointers is a pattern where two pointers iterate through the data structure in tandem until one or both of the pointers hit a certain condition. Two Pointers is often useful when searching pairs in a sorted array or linked list.
Two pointers are needed because with just pointer, you would have to continually loop back through the array to find the answer.

### Variations

* One pointer is N steps ahead all the time
* Fast and slow pointers
* National dutch flag (3 pointers)

## Sliding window

Another variation of the two pointer technique, where pointers are updated separately based on a certain condition.

Examples:
* Longest subarray that...
* Shortest substring containing...

## Merge intervals

There are 6 possible ways 2 ranges can be relatively positioned, 4 of them only overlap.

## Dynamic Programming

## Backtracking

## Sorting

When you are facing a new problem, ask yourself:

* Can I sort the input?
* How would this problem change if the elements were sorted?
* How does sorting affect the overall complexity?

## Variations on binary search

The safe way of computing this middle element is:

`int m = l + (r - l) / 2;`

## BFS

## DFS

## Two instances of the same data structure

Some problems can be solved by using two different instances of the same data structure, so it is worth keeping it in mind when you get stuck in a problem. For example:

* Queues
* Stacks
* Arrays
* Heaps

## Top K elements

## K-way merge
