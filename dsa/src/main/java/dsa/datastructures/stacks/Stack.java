package dsa.datastructures.stacks;

public interface Stack<T> {
	T push(T element);

	T pop();

	T peek();

	boolean isEmpty();
}
