package dsa.datastructures.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntegerVectorTest {
	private Vector<Integer> vector;

	@BeforeEach
	public void setUp() {
		this.vector = new IntegerVector();
		vector.push(1);
		vector.push(2);
		vector.push(3);
		vector.push(4);
		vector.push(5);
	}

	@Test
	public void testSize() {
		assertEquals(5, vector.size());
	}

	@Test
	public void testCapacity() {
		assertEquals(16, vector.capacity());

		final Vector<Integer> newVector = new IntegerVector(14);
		assertEquals(14, newVector.capacity());

		for (int i = 0; i < 16; i++) {
			vector.push(i);
		}
		assertEquals(32, vector.capacity());
	}

	@Test
	public void testIsEmpty() {
		final Vector<Integer> newVector = new IntegerVector();
		assertTrue(newVector.isEmpty());

		newVector.push(1);
		assertFalse(newVector.isEmpty());

		newVector.pop();
		assertTrue(newVector.isEmpty());
	}

	@Test
	public void testAt() {
		assertEquals(5, vector.at(4));
		assertNull(vector.at(-1));
		assertNull(vector.at(5));
	}

	@Test
	public void testPush() {
		final Vector<Integer> newVector = new IntegerVector();
		newVector.push(1);
		assertEquals(1, newVector.at(0));

		for (int i = 2; i < 33; i++) {
			newVector.push(i);
		}
		assertEquals(32, newVector.at(31));
		assertEquals(32, newVector.capacity());

		newVector.push(33);
		assertEquals(33, newVector.at(32));
		assertEquals(64, newVector.capacity());
	}

	@Test
	public void testInsert() {
		final Vector<Integer> newVector = new IntegerVector();
		for (int i = 0; i < 16; i++) {
			newVector.push(1);
		}
		assertEquals(16, newVector.capacity());
		assertEquals(16, newVector.size());

		newVector.insert(10, 8);
		assertEquals(32, newVector.capacity());
		assertEquals(17, newVector.size());
		assertEquals(8, newVector.at(10));
		assertEquals(1, newVector.at(9));
		assertEquals(1, newVector.at(11));
	}

	@Test
	public void testPrepend() {
		final Vector<Integer> newVector = new IntegerVector();
		for (int i = 0; i < 16; i++) {
			newVector.push(1);
		}
		assertEquals(16, newVector.capacity());
		assertEquals(16, newVector.size());

		newVector.prepend(8);
		assertEquals(32, newVector.capacity());
		assertEquals(17, newVector.size());
		assertEquals(8, newVector.at(0));
		assertEquals(1, newVector.at(1));
	}

	@Test
	public void testPop() {
		final Vector<Integer> newVector = new IntegerVector();
		for (int i = 0; i < 32; i++) {
			newVector.push(i);
		}
		assertEquals(32, newVector.capacity());
		assertEquals(32, newVector.size());

		for (int i = 31; i >= 0; i--) {
			assertEquals(i, newVector.pop());
			assertEquals(32, newVector.capacity());
			assertEquals(i, newVector.size());
		}

		assertNull(newVector.pop());
		assertEquals(32, newVector.capacity());
		assertEquals(0, newVector.size());

		assertNull(newVector.pop());
		assertEquals(32, newVector.capacity());
		assertEquals(0, newVector.size());
	}

	@Test
	public void testDelete() {
		final Vector<Integer> newVector = new IntegerVector();
		newVector.push(1);
		newVector.push(2);
		newVector.push(3);
		newVector.push(4);
		newVector.push(5);
		assertEquals(5, newVector.size());

		newVector.delete(2);
		assertEquals(4, newVector.at(2));
		assertEquals(4, newVector.size());

		newVector.delete(3);
		assertEquals(null, newVector.at(3));
		assertEquals(3, newVector.size());

		newVector.delete(0);
		assertEquals(2, newVector.at(0));
		assertEquals(2, newVector.size());

		newVector.delete(0);
		assertEquals(1, newVector.size());

		newVector.delete(0);
		assertEquals(0, newVector.size());

		newVector.delete(0);
		assertEquals(0, newVector.size());

		newVector.push(1);
		assertEquals(1, newVector.size());
		assertEquals(1, newVector.at(0));
	}

	@Test
	public void testRemove() {
		Vector<Integer> newVector = new IntegerVector();
		newVector.push(1);
		newVector.push(2);
		newVector.push(3);
		newVector.push(4);
		newVector.push(1);
		assertEquals(5, newVector.size());

		newVector.remove(4);
		assertEquals(1, newVector.at(3));
		assertEquals(4, newVector.size());

		newVector.remove(1);
		assertEquals(2, newVector.size());

		assertEquals(2, newVector.at(0));
		assertEquals(3, newVector.at(1));

		newVector = new IntegerVector();
		newVector.push(1);
		newVector.push(1);
		newVector.push(1);
		newVector.push(1);
		newVector.push(1);

		newVector.remove(1);
		assertEquals(0, newVector.size());

		newVector.push(1);
		assertEquals(1, newVector.size());
		assertEquals(1, newVector.at(0));
	}

	@Test
	public void testFind() {
		final Vector<Integer> newVector = new IntegerVector();
		for (int i = 0; i < 32; i++) {
			newVector.push(i);
			assertEquals(i, newVector.find(i));
		}
	}
}
