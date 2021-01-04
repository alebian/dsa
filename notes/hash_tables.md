## Complexity

Let's say N is the subset of all keys that we could insert and M is the size of the table. The probability of a key falling in a position is 1/M, then we define alpha = N / M (load factor) as the expected amount of keys to be in the same bucket. If M has the correct size and we use a good hash function, then we can consider alpha to be constant.

Since for some operations we have to double the size of the table, those operations have a complexity of O(N), but that cost is amortized T(N).

Hash tables in general exhibit poor locality of reference—that is, the data to be accessed is distributed seemingly at random in memory. Because hash tables cause access patterns that jump around, this can trigger microprocessor cache misses that cause long delays. Compact data structures such as arrays searched with linear search may be faster, if the table is relatively small and keys are compact.
