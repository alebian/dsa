package dsa.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TailPointerSinglyLinkedListTest extends ListTest {
	@BeforeEach
	public void setUp() {
		this.list = new TailPointerSinglyLinkedList<>();
	}

	@Test
	public void testRemoveAddLast() {
		list.add(0);
		list.add(1);
		list.add(2);

		list.remove(2);

		list.add(3);

		final Object[] result = list.toArray();
		final Object[] expected = new Object[] {0, 1, 3};

		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);
		}
	}
}
