package dsa.datastructures.lists;

public class TailPointerSinglyLinkedList<T> extends SinglyLinkedList<T> {
	protected Node<T> tail;

	public TailPointerSinglyLinkedList() {
		this.size = 0;
	}

	@Override
	public boolean add(T element) {
		final Node<T> newNode = new Node<>(element);

		if (tail == null) {
			head = newNode;
		} else {
			tail.setNext(newNode);
		}
		size++;
		tail = newNode;

		return true;
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
}
