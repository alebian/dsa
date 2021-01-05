package dsa.datastructures.lists;

import dsa.datastructures.Pair;

public class DoubleLinkedList<T> extends SinglyLinkedList<T> {
	public DoubleLinkedList() {
		super();
	}

	@Override
	public boolean remove(T element) {
		if (head != null) {
			Node<T> current = head;
			Node<T> previous = null;

			while (current != null) {
				if (current.getValue().equals(element)) {
					if (previous == null) {
						head = current.getNext();
						if (head != null) {
							head.setPrevious(null);
						}
					} else {
						previous.setNext(current.getNext());
						if (current.getNext() != null) {
							current.getNext().setPrevious(previous);
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
	public T removeAt(int index) {
		if (index >= 0 && index < size()) {
			T result;

			if (index == 0) {
				result = head.getValue();
				head = head.getNext();
				if (head != null) {
					head.setPrevious(null);
				}
			} else {
				final Pair<Node<T>, Node<T>> pair = advanceWithDifference(index, 1);
				result = pair.getLast().getValue();

				pair.getFirst().setNext(pair.getLast().getNext());
				if (pair.getLast().getNext() != null) {
					pair.getLast().getNext().setPrevious(pair.getFirst());
				}
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
		head.setPrevious(null);

		while (current != null) {
			final Node<T> next = current.getNext();
			current.setNext(head);
			head.setPrevious(current);
			head = current;
			current = next;
		}
	}

	protected Node<T> insertNode(final int index, final T element) {
		if (index >= 0 && index <= size()) {
			final Node<T> newNode = new Node<>(element);

			if (index == 0) {
				newNode.setNext(head);
				if (head != null) {
					head.setPrevious(newNode);
				}
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
					current.getNext().setPrevious(newNode);
					newNode.setPrevious(current);
				}

				current.setNext(newNode);
			}

			size++;
			return newNode;
		}
		return null;
	}
}
