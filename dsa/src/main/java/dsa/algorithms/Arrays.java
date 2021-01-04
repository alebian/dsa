package dsa.algorithms;

public class Arrays {
	public static int removeDuplicates(final int[] arr) {
		if (arr.length == 0) {
			return 0;
		}

		int n = 1;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] != arr[i - 1]) {
				arr[n++] = arr[i];
			}
		}
		return n;
	}

	public static void dutchNationalFlag(final Integer[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}

		int small = 0;
		int medium = 0;
		int large = arr.length - 1;
		while (medium <= large) {
			if (arr[medium] == 0) {
				swap(arr, small, medium);
				small++;
				medium++;
			} else if (arr[medium] == 2) {
				swap(arr, medium, large);
				large--;
			} else {
				medium++;
			}
		}
	}

	public static void swap(final Object[] arr, final int x, final int y) {
		final Object tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}
}
