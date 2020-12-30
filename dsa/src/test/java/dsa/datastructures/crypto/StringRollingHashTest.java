package dsa.datastructures.crypto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

public class StringRollingHashTest {
	@Test
	public void testBasicCase() {
		RollingHash<Character> hash1 = new StringRollingHash();
		RollingHash<Character> hash2 = new StringRollingHash();

		assertEquals(hash1.hash(), hash2.hash());

		for (Character c : "hello world".toCharArray()) {
			hash1.add(c);
		}

		assertNotEquals(hash1.hash(), hash2.hash());

		for (Character c : "hello world".toCharArray()) {
			hash2.add(c);
		}

		assertEquals(hash1.hash(), hash2.hash());

		hash1.remove('h');
		assertNotEquals(hash1.hash(), hash2.hash());
		hash2.remove('h');
		assertEquals(hash1.hash(), hash2.hash());

		hash1 = new StringRollingHash();
		hash2 = new StringRollingHash();

		hash1.add('a');
		for (int i = 0; i < 7; i++) {
			hash1.add('a');
		}
		hash1.add('b');
		hash1.add('c');
		hash1.remove('b');
		for (int i = 0; i < 7; i++) {
			hash1.remove('a');
		}

		hash2.add('a');
		hash2.add('c');

		assertEquals(hash2.hash(), hash1.hash());
	}
}
