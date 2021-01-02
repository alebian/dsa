package dsa.datastructures.arrays;

/**
 * Mutable array with automatic resizing
 */
public class IntegerVector implements Vector<Integer> {
	private static final int CAPACITY = 16;
	private int capacity;
	private int insertPosition;
	private Integer[] elements;

	IntegerVector(final int initialCapacity) {
		this.capacity = initialCapacity;
		this.insertPosition = 0;
		this.elements = new Integer[capacity];

	}

	IntegerVector() {
		this(CAPACITY);
	}

	@Override
	public int size() {
		return insertPosition;
	}

	@Override
	public int capacity() {
		return capacity;
	}

	@Override
	public boolean isEmpty() {
		return insertPosition == 0;
	}

	@Override
	public Integer at(final int index) {
		if (index < 0 || index > insertPosition - 1) {
			return null;
		}
		return elements[index];
	}

	@Override
	public void push(final Integer item) {
		if (size() == capacity()) {
			resize(capacity * 2);
		}
		elements[insertPosition++] = item;
	}

	@Override
	public void insert(final int index, final Integer item) {
		if (size() == capacity()) {
			resize(capacity * 2);
		}

		for (int i = size() - 1; i >= index; i--) {
			elements[i + 1] = elements[i];
		}
		elements[index] = item;

		insertPosition++;
	}

	@Override
	public void prepend(final Integer item) {
		insert(0, item);
	}

	@Override
	public Integer pop() {
		if (size() == 0) {
			return null;
		}

		final Integer result = elements[--insertPosition];
		shrink();

		return result;
	}

	@Override
	public void delete(final int index) {
		if (size() == 0) {
			return;
		}

		insertPosition--;
		for (int i = index; i < size(); i++) {
			elements[i] = elements[i + 1];
		}

		shrink();
	}

	@Override
	public void remove(final Integer item) {
		for (int i = size() - 1; i >= 0; i--) {
			if (elements[i] == item) {
				delete(i);
			}
		}
	}

	@Override
	public int find(final Integer item) {
		for (int i = 0; i < size(); i++) {
			if (elements[i] == item) {
				return i;
			}
		}
		return -1;
	}

	private void shrink() {
		// If we shrink with a load factor of 2 we get logN amortized time instead of constant amortized time
		if (capacity > CAPACITY && capacity / size() >= 4) {
			resize(capacity / 2);
		}
	}

	private void resize(final int newCapacity) {
		final Integer[] oldElements = elements;
		this.elements = new Integer[newCapacity];

		for (int i = 0; i < size(); i++) {
			elements[i] = oldElements[i];
		}

		this.capacity = newCapacity;
	}
}

