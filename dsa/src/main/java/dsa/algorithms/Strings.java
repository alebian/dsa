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

		for (Character c : t.substring(0, s.length()).toCharArray()) {
			tHash.add(c);
		}

		// Test this case to avoid generating the tHash inside the loop, which makes the code harder to understand
		if (sHash.equals(tHash)) {
			return 0;
		}

		for (int i = s.length(); i < t.length(); i++) {
			tHash.remove(t.charAt(i - s.length()));
			tHash.add(t.charAt(i));

			if (sHash.equals(tHash)) {
				return i - s.length() + 1;
			}
		}

		return -1;
	}
}
