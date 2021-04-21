package dsa.datastructures.tries;

public interface Trie {
	void add(String word);

	boolean search(String word);

	boolean startsWith(String prefix);
}
