package dsa.datastructures.lists;

import dsa.datastructures.Collection;

public interface List<T> extends Collection<T> {
	T get(int index);

	T set(int index, T element);

	T removeAt(int index);

	default boolean addFirst(T element) {
		return set(0, element) != null;
	}

	default T removeFirst() {
		return removeAt(0);
	}

	default T removeLast() {
		return removeAt(size() - 1);
	}

	default T first() {
		return get(0);
	}

	default T last() {
		return get(size() - 1);
	}

	void reverse();

	T valueNFromLast(int n);

	//	void sort();

	//	void sort(Comparator<? super T> comparator);
}
