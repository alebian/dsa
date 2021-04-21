package dsa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Shopping {
	private static final String CLOTHING_CATEGORY = "Clothing";
	private static final String SPORTS_CATEGORY = "Sports";
	private static final String ACCESSORIES_CATEGORY = "Accessories";
	private static final String ELECTRONICS_CATEGORY = "Electronics";

	private static final Map<String, List<String>> PRODUCTS_CATEGORIES = new HashMap<>();
	static {
		PRODUCTS_CATEGORIES.put("shirt", List.of(CLOTHING_CATEGORY));
		PRODUCTS_CATEGORIES.put("sweater", List.of(CLOTHING_CATEGORY));
		PRODUCTS_CATEGORIES.put("jacket", List.of(CLOTHING_CATEGORY));
		PRODUCTS_CATEGORIES.put("shorts", List.of(CLOTHING_CATEGORY, SPORTS_CATEGORY));
		PRODUCTS_CATEGORIES.put("shoes", List.of(CLOTHING_CATEGORY, SPORTS_CATEGORY));

		PRODUCTS_CATEGORIES.put("socks", List.of(SPORTS_CATEGORY));
		PRODUCTS_CATEGORIES.put("football", List.of(SPORTS_CATEGORY));
		PRODUCTS_CATEGORIES.put("helmet", List.of(SPORTS_CATEGORY));
		PRODUCTS_CATEGORIES.put("bag", List.of(SPORTS_CATEGORY, ACCESSORIES_CATEGORY));

		PRODUCTS_CATEGORIES.put("belt", List.of(ACCESSORIES_CATEGORY));
		PRODUCTS_CATEGORIES.put("hat", List.of(ACCESSORIES_CATEGORY));
		PRODUCTS_CATEGORIES.put("sunglasses", List.of(ACCESSORIES_CATEGORY));
		PRODUCTS_CATEGORIES.put("watch", List.of(ACCESSORIES_CATEGORY));

		PRODUCTS_CATEGORIES.put("tv", List.of(ELECTRONICS_CATEGORY));
		PRODUCTS_CATEGORIES.put("camera", List.of(ELECTRONICS_CATEGORY));
		PRODUCTS_CATEGORIES.put("headphones", List.of(ELECTRONICS_CATEGORY));
		PRODUCTS_CATEGORIES.put("laptop", List.of(ELECTRONICS_CATEGORY));
	}

	static List<String> popularShoppingCategories(Map<String, List<String>> usersPurchases) {
		if (usersPurchases == null) {
			throw new IllegalArgumentException();
		}

		final Map<String, Integer> purchasesByCategory = new HashMap<>();
		final List<String> result = new ArrayList<>();

		for (List<String> userPurchases : usersPurchases.values()) {
			for (String purchase : userPurchases) {
				if (purchase == null) {
					throw new IllegalArgumentException();
				}

				final String normalizedPurchase = purchase.toLowerCase();
				if (PRODUCTS_CATEGORIES.containsKey(normalizedPurchase)) {
					for (String category : PRODUCTS_CATEGORIES.get(normalizedPurchase)) {
						purchasesByCategory.putIfAbsent(category, 0);
						purchasesByCategory.put(category, purchasesByCategory.get(category) + 1);
					}
				} else if (!normalizedPurchase.isEmpty()) {
					throw new IllegalArgumentException();
				}
			}
		}

		final PriorityQueue<CategoryWithPurchases> queue = new PriorityQueue<>(new CategoryWithPurchasesComparator());
		for (String category : purchasesByCategory.keySet()) {
			queue.add(new CategoryWithPurchases(category, purchasesByCategory.get(category)));
		}

		if (!queue.isEmpty() && queue.peek().purchases > 0) {
			CategoryWithPurchases first = queue.poll();
			int lastTaken = first.purchases;
			result.add(first.category);
			while (!queue.isEmpty() && queue.peek().purchases == lastTaken) {
				first = queue.poll();
				result.add(first.category);
			}
		}

		return result;
	}

	private static class CategoryWithPurchases {
		private String category;
		private int purchases;

		public CategoryWithPurchases(String category, int purchases) {
			this.category = category;
			this.purchases = purchases;
		}
	}


	/**
	 * Comparator to use in the heap, we reverse the natural order because it's a max heap.
	 */
	private static class CategoryWithPurchasesComparator implements Comparator<CategoryWithPurchases> {
		@Override
		public int compare(CategoryWithPurchases cat1, CategoryWithPurchases cat2) {
			if (cat1.purchases == cat2.purchases) {
				return cat1.category.compareTo(cat2.category);
			} else {
				return Integer.compare(cat2.purchases, cat1.purchases);
			}
		}
	}
}
