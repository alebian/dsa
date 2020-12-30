package dsa.datastructures.crypto;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RollingHashMap<T> implements RollingHash<T> {
	private final Map<T, Integer> map;

	public RollingHashMap() {
		this.map = new HashMap<>();
	}

	@Override
	public BigInteger hash() {
		return BigInteger.valueOf(map.hashCode());
	}

	@Override
	public void add(final T element) {
		map.put(element, Optional.ofNullable(map.get(element)).orElse(0) + 1);
	}

	@Override
	public void remove(final T element) {
		if (map.containsKey(element)) {
			if (map.get(element) == 1) {
				map.remove(element);
			} else {
				map.put(element, map.get(element) - 1);
			}
		}
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final RollingHashMap<?> that = (RollingHashMap<?>) o;
		return map.equals(that.map);
	}
}
