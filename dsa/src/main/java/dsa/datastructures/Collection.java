package dsa.datastructures;

public interface Collection<T> {
	int size();

	default boolean isEmpty() {
		return size() == 0;
	}

	boolean contains(T element);

	Object[] toArray();

	boolean add(T element);

	boolean remove(T element);
}
