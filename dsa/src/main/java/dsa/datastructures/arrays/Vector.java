package dsa.datastructures.arrays;

public interface Vector<T> {
	// Number of items
	int	size();

	// Number of items it can hold
	int capacity();

	boolean isEmpty();

	// Returns item at given index, blows up if index out of bounds
	T at(int index);

	void push(T item);

	// Inserts item at index, shifts that index's value and trailing elements to the right
	void insert(int index, T item);

	// Can use insert above at index 0
	void prepend(T item);

	// Remove from end, return value
	T pop();

	// delete item at index, shifting all trailing elements left
	void delete(int index);

	// Looks for value and removes index holding it (even if in multiple places)
	void remove(T item);

	// Looks for value and returns first index with that value, -1 if not found
	int find(T item);
}
