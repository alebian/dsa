package dsa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class ShoppingTest {

	@Test
	public void validInputOnePopularCategory() {
		Map<String, List<String>> usersPurchases = Map.of(
				"Anna", List.of("shirt", "sweater", "bag", "belt", "watch"),
				"Sofie", List.of("headphones")
		);

		assertEquals(List.of("Accessories"), Shopping.popularShoppingCategories(usersPurchases));
	}

	@Test
	public void validInputManyPopularCategories() {
		Map<String, List<String>> usersPurchases = Map.of(
				"Michael", List.of("Football", "Shirt", "Shoes", "headphones"),
				"Sara", List.of("TV", "football"),
				"Daniel", List.of("shirt", "sweater", "", "belt", "bag")
		);

		assertEquals(List.of("Clothing", "Sports"), Shopping.popularShoppingCategories(usersPurchases));
	}

	@Test
	public void invalidInput() {
		Map<String, List<String>> usersPurchases = Map.of(
				"Jane", List.of("shirt", "dress")
		);
		Shopping.popularShoppingCategories(usersPurchases);
	}

	@Test
	public void emptyInput() {
		Map<String, List<String>> usersPurchases = new HashMap<>();
		assertEquals(Collections.emptyList(), Shopping.popularShoppingCategories(usersPurchases));
	}
}
