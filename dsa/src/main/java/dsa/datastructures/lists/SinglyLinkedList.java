package dsa.datastructures.lists;

import dsa.datastructures.Pair;

public class SinglyLinkedList<T> implements List<T> {
	protected Node<T> head;
	protected int size;

	public SinglyLinkedList() {
		this.size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(final T element) {
		if (head != null) {
			Node<T> current = head;
			while (current != null) {
				if (current.getValue().equals(element)) {
					return true;
				}
				current = current.getNext();
			}
		}
		return false;
	}

	@Override
	public Object[] toArray() {
		final Object[] result = new Object[size];

		if (head != null) {
			Node<T> current = head;
			int idx = 0;
			while (current != null) {
				result[idx] = current.getValue();
				current = current.getNext();
				idx++;
			}
		}

		return result;
	}

	@Override
	public boolean add(final T element) {
		return set(size(), element) != null;
	}

	@Override
	public boolean remove(final T element) {
		if (head != null) {
			Node<T> current = head;
			Node<T> previous = null;

			while (current != null) {
				if (current.getValue().equals(element)) {
					if (previous == null) {
						head = current.getNext();
					} else {
						previous.setNext(current.getNext());
					}
					size--;
					return true;
				}

				previous = current;
				current = current.getNext();
			}
		}
		return false;
	}

	@Override
	public T get(final int index) {
		if (index >= 0 && index < size()) {
			int currentIndex = 0;
			Node<T> current = head;

			while (index != currentIndex) {
				current = current.getNext();
				currentIndex++;
			}

			return current.getValue();
		}
		return null;
	}

	@Override
	public T set(final int index, final T element) {
		final Node<T> result = insertNode(index, element);
		if (result != null) {
			return result.getValue();
		}
		return null;
	}

	@Override
	public T removeAt(final int index) {
		if (index >= 0 && index < size()) {
			T result;

			if (index == 0) {
				result = head.getValue();
				head = head.getNext();
			} else {
				final Pair<Node<T>, Node<T>> pair = advanceWithDifference(index, 1);
				result = pair.getLast().getValue();
				pair.getFirst().setNext(pair.getLast().getNext());
			}

			size--;
			return result;
		}
		return null;
	}

	@Override
	public void reverse() {
		if (size < 2) {
			return;
		}

		Node<T> current = head;
		current = current.getNext();
		head.setNext(null);

		while (current != null) {
			final Node<T> next = current.getNext();
			current.setNext(head);
			head = current;
			current = next;
		}
	}

	@Override
	public T valueNFromLast(final int n) {
		if (size() > n) {
			final Pair<Node<T>, Node<T>> pair = getLastAndNFromLast(n);
			return pair.getFirst().getValue();
		}
		return null;
	}

	protected Pair<Node<T>, Node<T>> getLastAndNFromLast(final int n) {
		return advanceWithDifference(size() - 1, n);
	}

	protected Pair<Node<T>, Node<T>> advanceWithDifference(final int index, final int difference) {
		int currentIndex = 0;
		Node<T> current = head;
		Node<T> previous = head;

		while (currentIndex != difference) {
			current = current.getNext();
			currentIndex++;
		}

		while (index != currentIndex) {
			previous = current;
			current = current.getNext();
			currentIndex++;
		}

		return new Pair<>(previous, current);
	}

	protected Node<T> insertNode(final int index, final T element) {
		if (index >= 0 && index <= size()) {
			final Node<T> newNode = new Node<>(element);

			if (index == 0) {
				newNode.setNext(head);
				head = newNode;
			} else {
				int currentIdx = 0;
				Node<T> current = head;

				while (currentIdx < index - 1) {
					current = current.getNext();
					currentIdx++;
				}

				if (current.getNext() != null) {
					// We have to insert at the last position
					newNode.setNext(current.getNext().getNext());
				}

				current.setNext(newNode);
			}

			size++;
			return newNode;
		}
		return null;
	}
}
