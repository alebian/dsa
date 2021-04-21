package dsa.datastructures.tries;

import java.util.HashMap;
import java.util.Map;

public class TrieNode implements Trie {
	private final Map<Character, TrieNode> children;
	private boolean end;

	TrieNode() {
		this.children = new HashMap<>();
		this.end = false;
	}

	@Override
	public void add(final String word) {
		TrieNode current = this;

		for (Character c : word.toCharArray()) {
			children.putIfAbsent(c, new TrieNode());
			current = children.get(c);
		}

		current.end = true;
	}

	@Override
	public boolean search(final String word) {
		TrieNode last = iterate(word);
		return last != null && last.end;
	}

	@Override
	public boolean startsWith(final String prefix) {
		TrieNode last = iterate(prefix);
		return !last.end;
	}

	private TrieNode iterate(final String word) {
		TrieNode current = this;

		for (Character c : word.toCharArray()) {
			if (!children.containsKey(c)) {
				return null;
			}
			current = children.get(c);
		}

		return current;
	}
}
