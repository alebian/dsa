package dsa.algorithms;

import java.util.Objects;
import java.util.function.Function;

public class Ranges {
	public static class Range {
		private final int start;
		private final int end;

		public Range(int start, int end) {
			this.start = Math.min(start, end);
			this.end = Math.max(start, end);
		}

		public int getStart() {
			return start;
		}

		public int getEnd() {
			return end;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			final Range range = (Range) o;
			return getStart() == range.getStart() && getEnd() == range.getEnd();
		}

		@Override
		public int hashCode() {
			return Objects.hash(getStart(), getEnd());
		}
	}

	/**
	 * Pattern 4 from https://hackernoon.com/14-patterns-to-ace-any-coding-interview-question-c5bb3357f6ed
	 * There are 6 possible overlapping cases for 2 ranges
	 * @param a range
	 * @param b range
	 */
	public static <T, R> R mergeIntervals(
			final Range a, final Range b,
			final Function<T, R> aLeftOutB, final Function<T, R> aLeftInB, final Function<T, R> bInA,
			final Function<T, R> bLeftInA, final Function<T, R> aInb, final Function<T, R> bLeftOutA,
			final T element
	) {
		// |---A---|
		//           |---B---|
		if (a.getEnd() < b.getStart()) {
			return aLeftOutB.apply(element);
		// |---A---|
		//       |---B---|
		} else if(b.getStart() >= a.getStart() && b.getStart() <= a.getEnd()) {
			return aLeftInB.apply(element);
		// |---A---|
		//  |--B--|
		} else if(b.getStart() >= a.getStart() && b.getEnd() <= a.getEnd()) {
			return bInA.apply(element);
		//       |---A---|
		// |---B---|
		} else if(b.getEnd() >= a.getStart() && b.getEnd() < a.getEnd()) {
			return bLeftInA.apply(element);
		//  |--A--|
		// |---B---|
		} else if(a.getStart() >= b.getStart() && a.getEnd() <= b.getEnd()) {
			return aInb.apply(element);
		//           |---A---|
		// |---B---|
		} else if(b.getEnd() < a.getStart()) {
			return bLeftOutA.apply(element);
		}
		throw new RuntimeException("We shouldn't reach here. There is a logic problem");
	}
}
