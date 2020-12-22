package dsa.datastructures.lists;

import dsa.datastructures.Collection;

public interface List<T> extends Collection<T> {
	//	void sort();

	//	void sort(Comparator<? super T> comparator);

	//	T get(int index);

	//	T set(int index, T element);

	boolean addFirst(T element);
}
