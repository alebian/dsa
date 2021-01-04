package dsa.datastructures;

public class Pair<S, T> {
	private final S first;
	private final T last;

	public Pair(S first, T last) {
		this.first = first;
		this.last = last;
	}

	public S getFirst() {
		return first;
	}

	public T getLast() {
		return last;
	}
}
