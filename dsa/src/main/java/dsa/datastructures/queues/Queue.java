package dsa.datastructures.queues;

public interface Queue<T> {
	T push(T element);

	T pop();

	T peek();

	boolean isEmpty();
}
