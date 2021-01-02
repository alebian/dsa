package dsa.datastructures.crypto;

import java.math.BigInteger;

public class StringRollingHash implements RollingHash<Character> {
	private BigInteger hash;
	private int size;

	public StringRollingHash() {
		this.hash = BigInteger.ONE;
		this.size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public BigInteger hash() {
		return hash;
	}

	@Override
	public void add(final Character c) {
		this.hash = hash.multiply(BigInteger.valueOf(c.hashCode()));
		size++;
	}

	@Override
	public void remove(final Character c) {
		this.hash = hash.divide(BigInteger.valueOf(c.hashCode()));
		size--;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final StringRollingHash that = (StringRollingHash) o;
		return hash().equals(that.hash());
	}
}
