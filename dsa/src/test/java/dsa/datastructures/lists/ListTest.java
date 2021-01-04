package dsa.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public abstract class ListTest {
	protected List<Integer> list;

	@Test
	public void testAdd() {
		for (int i = 0; i < 3; i++) {
			list.add(i);
		}

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
	public void testRemoveFirst() {
		list.add(30);
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(40);
		list.add(50);

		assertEquals(30, list.removeFirst());
		assertEquals(5, list.size());
	}

	@Test
	public void testRemoveFirstOneElement() {
		list.add(30);

		assertEquals(30, list.removeFirst());
		assertEquals(0, list.size());
		assertNull(list.last());

		assertNull(list.removeFirst());
	}

	@Test
	public void testToArray() {
		for (int i = 0; i < 3; i++) {
			list.add(i);
		}

		final Object[] result = list.toArray();

		for (int i = 0; i < 3; i++) {
			assertEquals(i, result[i]);
		}
	}

	@Test
	public void testSize() {
		for (int i = 0; i < 3; i++) {
			list.add(i);
		}

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
		for (int i = 0; i < 3; i++) {
			list.add(i);
		}

		assertTrue(list.contains(2));
		assertFalse(list.contains(3));
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

	@Test
	public void testGet() {
		for (int i = 0; i < 3; i++) {
			list.add(i);
		}

		for (int i = 0; i < 3; i++) {
			assertEquals(i, list.get(i));
		}
	}

	@Test
	public void testSet() {
		for (int i = 0; i < 3; i++) {
			list.add(i);
		}

		for (int i = 3; i < 6; i++) {
			list.set(i, i);
		}

		final Object[] result = list.toArray();

		for (int i = 0; i < 6; i++) {
			assertEquals(i, result[i]);
		}
	}

	@Test
	public void testFirst() {
		list.add(0);
		assertEquals(0, list.first());

		list.addFirst(1);
		assertEquals(1, list.first());
	}

	@Test
	public void testFirstEmptyList() {
		assertNull(list.first());
	}

	@Test
	public void testLastOneElement() {
		list.add(0);
		assertEquals(0, list.last());
	}

	@Test
	public void testLast() {
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		assertEquals(9, list.last());
	}

	@Test
	public void testRemoveAt() {
		for (int i = 0; i < 5; i++) {
			list.add(i);
		}

		assertEquals(0, list.removeAt(0));
		assertEquals(4, list.size());

		assertEquals(4, list.removeAt(3));
		assertEquals(3, list.size());
		assertEquals(3, list.last());

		assertNull(list.removeAt(3));
		assertEquals(3, list.size());

		assertNull(list.removeAt(-1));
		assertEquals(3, list.size());

		assertEquals(2, list.removeAt(1));
		assertEquals(2, list.size());
	}

	@Test
	public void testRemoveLast() {
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		assertEquals(9, list.removeLast());
		assertEquals(8, list.last());
		assertEquals(9, list.size());
	}

	@Test
	public void testLastEmptyList() {
		assertNull(list.last());
	}

	@Test
	public void testReverse() {
		for (int i = 0; i < 6; i++) {
			list.add(i);
		}
		list.reverse();

		Object[] result = list.toArray();

		for (int i = 5; i >= 0; i--) {
			assertEquals(i, result[5 - i]);
		}

		list.reverse();
		result = list.toArray();

		for (int i = 0; i < 6; i++) {
			assertEquals(i, result[i]);
		}
	}

	// 0 -> 1 -> 2 -> 3 -> 4
	@Test
	public void testValueNFromLastWithBigEnoughList() {
		for (int i = 0; i < 4; i++) {
			list.add(i);
		}

		assertEquals(0, list.valueNFromLast(3));
	}

	@Test
	public void testValueNFromLastWithSmallList() {
		for (int i = 0; i < 3; i++) {
			list.add(i);
		}

		assertNull(list.valueNFromLast(3));
	}
}
