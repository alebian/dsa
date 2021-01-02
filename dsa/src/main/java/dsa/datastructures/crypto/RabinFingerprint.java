package dsa.datastructures.crypto;

import java.math.BigInteger;

public class RabinFingerprint implements RollingHash<Character> {
	private static final int M = 128;
	private BigInteger hash;
	private int size;

	public RabinFingerprint() {
		this.hash = BigInteger.ZERO;
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
	public void add(final Character element) {
		this.hash = hash
				.multiply(BigInteger.valueOf(M))
				.add(BigInteger.valueOf(element.hashCode()));
		this.size++;
	}

	@Override
	public void remove(final Character element) {
		this.hash = hash.subtract(
				BigInteger.valueOf(element.hashCode()).multiply(BigInteger.valueOf(M).pow(size - 1))
		);
		this.size--;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final RabinFingerprint that = (RabinFingerprint) o;
		return hash().equals(that.hash());
	}
}
