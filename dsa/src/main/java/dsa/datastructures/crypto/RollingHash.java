package dsa.datastructures.crypto;

import java.math.BigInteger;

public interface RollingHash<T> {
	BigInteger hash();

	void add(T element);

	void remove(T element);

	int size();
}
