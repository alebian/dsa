package dsa.algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ArraysTest {
	@Test
	public void testRemoveDuplicates() {
		int[] arr = new int[]{1,2,2,2,2,3,3,4};
		assertEquals(4, Arrays.removeDuplicates(arr));
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	@Test
	public void testDutchNationalFlag() {
		Integer[] arr = new Integer[]{2,2,0,1,2,2,1,1,0,0,1};
		Arrays.dutchNationalFlag(arr);

		assertTrue(java.util.Arrays.equals(new Integer[]{0,0,0,1,1,1,1,2,2,2,2}, arr));
	}
}
