package dsa.datastructures.lists;

class Node<T> {
	private final T value;
	private Node<T> next;
	private Node<T> previous;

	public Node(final T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(final Node<T> next) {
		this.next = next;
	}

	public Node<T> getPrevious() {
		return previous;
	}

	public void setPrevious(final Node<T> previous) {
		this.previous = previous;
	}
}
