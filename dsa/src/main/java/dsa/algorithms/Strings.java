package dsa.algorithms;

import dsa.datastructures.crypto.RabinFingerprint;
import dsa.datastructures.crypto.RollingHash;

public class Strings {
	/**
	 * Karp-Rabin like algorithm
	 */
	public static int isSubstring(final String s, final String t) {
		if (s.length() > t.length()) {
			return -1;
		}

		final RollingHash<Character> sHash = new RabinFingerprint();
		final RollingHash<Character> tHash = new RabinFingerprint();

		for (Character c : s.toCharArray()) {
			sHash.add(c);
		}

		for (int i = 0; i < t.length(); i++) {
			tHash.add(t.charAt(i));

			if (tHash.size() == sHash.size()) {
				int substringStartIdx = i - s.length() + 1;
				if (sHash.equals(tHash)) {
					return substringStartIdx;
				}
				tHash.remove(t.charAt(substringStartIdx));
			}
		}

		return -1;
	}
}
