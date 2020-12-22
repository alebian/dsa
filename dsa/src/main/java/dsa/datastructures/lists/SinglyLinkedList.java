package dsa.datastructures.lists;

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
		final Node<T> newNode = new Node<>(element);

		if (head == null) {
			this.head = newNode;
		} else {
			Node<T> current = head;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(newNode);
		}
		size++;

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
	public boolean addFirst(final T element) {
		final Node<T> newNode = new Node<>(element);
		newNode.setNext(head);
		head = newNode;
		size++;
		return true;
	}
}
