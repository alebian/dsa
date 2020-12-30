package dsa.algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StringsTest {
	@Test
	public void testSubstring() {
		assertEquals(0, Strings.isSubstring("hello", "hello"));
		assertEquals(10, Strings.isSubstring("hello", "this is a hello string"));
		assertEquals(10, Strings.isSubstring("hello", "this is a hello"));
		assertEquals(-1, Strings.isSubstring("hello", "this is a hell0 string"));
		assertEquals(-1, Strings.isSubstring("hello", "hell"));
		assertEquals(-1, Strings.isSubstring("aabb", "ccdd"));
	}
}
