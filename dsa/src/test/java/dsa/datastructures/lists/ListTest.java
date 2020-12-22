package dsa.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public abstract class ListTest {
	protected List<Integer> list;

	@Test
	public void testAdd() {
		list.add(0);
		list.add(1);
		list.add(2);

		assertEquals(3, list.size());
	}

	@Test
	public void testAddFirst() {
		list.addFirst(0);
		list.addFirst(1);
		list.addFirst(2);

		final Object[] result = list.toArray();

		for (int i = 0; i < 3; i++) {
			assertEquals(2 - i, result[i]);
		}
	}

	@Test
	public void testToArray() {
		list.add(0);
		list.add(1);
		list.add(2);

		final Object[] result = list.toArray();

		for (int i = 0; i < 3; i++) {
			assertEquals(i, result[i]);
		}
	}

	@Test
	public void testSize() {
		list.add(1);
		list.add(2);
		list.add(3);

		assertEquals(3, list.size());
		assertEquals(3, list.toArray().length);
	}

	@Test
	public void testIsEmpty() {
		assertTrue(list.isEmpty());

		list.add(1);
		assertFalse(list.isEmpty());

		list.remove(1);
		assertTrue(list.isEmpty());
	}

	@Test
	public void testContains() {
		list.add(1);
		list.add(2);
		list.add(3);

		assertTrue(list.contains(3));
		assertFalse(list.contains(4));
	}

	@Test
	public void testRemove() {
		list.add(30);
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(40);
		list.add(50);

		list.remove(30);
		list.remove(40);
		list.remove(50);

		final Object[] result = list.toArray();

		for (int i = 0; i < 3; i++) {
			assertEquals(i, result[i]);
		}

		list.remove(0);
		list.remove(1);
		list.remove(2);

		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
		assertEquals(0, list.toArray().length);
	}
}
