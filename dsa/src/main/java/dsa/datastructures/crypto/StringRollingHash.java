package dsa.datastructures.crypto;

import java.math.BigInteger;

public class StringRollingHash implements RollingHash<Character> {
	private BigInteger hash;

	public StringRollingHash() {
		this.hash = BigInteger.ONE;
	}

	@Override
	public BigInteger hash() {
		return hash;
	}

	@Override
	public void add(final Character c) {
		this.hash = hash.multiply(BigInteger.valueOf(c.hashCode()));
	}

	@Override
	public void remove(final Character c) {
		this.hash = hash.divide(BigInteger.valueOf(c.hashCode()));
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
