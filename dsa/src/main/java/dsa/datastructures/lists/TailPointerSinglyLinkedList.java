package dsa.datastructures.lists;

import dsa.datastructures.Pair;

public class TailPointerSinglyLinkedList<T> extends SinglyLinkedList<T> {
	private Node<T> tail;

	public TailPointerSinglyLinkedList() {
		this.size = 0;
	}

	@Override
	public T set(final int index, final T element) {
		final Node<T> result = insertNode(index, element);
		if (result != null) {
			if (result.getNext() == null) {
				tail = result;
			}
			return result.getValue();
		}
		return null;
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
						tail = head;
					} else {
						previous.setNext(current.getNext());
						if (current.equals(tail)) {
							tail = previous;
						}
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
				if (pair.getFirst().getNext() == null) {
					tail = pair.getFirst();
				}
			}

			size--;
			return result;
		}
		return null;
	}

	@Override
	public T last() {
		if (!isEmpty()) {
			return tail.getValue();
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
			head.setPrevious(current);
			head = current;
			current = next;
		}
	}
}
