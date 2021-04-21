package dsa.algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RangesTest {
	private static final String A_LEFT_OUT_B_RESULT = "A left outside B";
	private static final String A_LEFT_IN_B_RESULT = "A left inside B";
	private static final String B_IN_A_RESULT = "B inside A";
	private static final String B_LEFT_IN_A_RESULT = "B left inside A";
	private static final String A_IN_B_RESULT = "A inside B";
	private static final String B_LEFT_OUT_A_RESULT = "B left outside A";

	private static final Function<String, String> A_LEFT_OUT_B = (_s) -> A_LEFT_OUT_B_RESULT;
	private static final Function<String, String> A_LEFT_IN_B = (_s) -> A_LEFT_IN_B_RESULT;
	private static final Function<String, String> B_IN_A = (_s) -> B_IN_A_RESULT;
	private static final Function<String, String> B_LEFT_IN_A = (_s) -> B_LEFT_IN_A_RESULT;
	private static final Function<String, String> A_IN_B = (_s) -> A_IN_B_RESULT;
	private static final Function<String, String> B_LEFT_OUT_A = (_s) -> B_LEFT_OUT_A_RESULT;

	private static final BiFunction<Ranges.Range, Ranges.Range, String> TEST = (a, b) ->
		Ranges.mergeIntervals(a, b, A_LEFT_OUT_B, A_LEFT_IN_B, B_IN_A, B_LEFT_IN_A, A_IN_B, B_LEFT_OUT_A, "test");

	@Test
	public void testALeftOutB() {
		assertEquals(
				A_LEFT_OUT_B_RESULT,
				TEST.apply(
						new Ranges.Range(1, 2),
						new Ranges.Range(3, 4)
				)
		);
	}

	@Test
	public void testALeftInB() {
		assertEquals(
				A_LEFT_IN_B_RESULT,
				TEST.apply(
						new Ranges.Range(1, 3),
						new Ranges.Range(3, 4)
				)
		);
		assertEquals(
				A_LEFT_IN_B_RESULT,
				TEST.apply(
						new Ranges.Range(1, 4),
						new Ranges.Range(3, 5)
				)
		);
	}

	@Test
	public void testBInsideA() {
		assertEquals(
				B_IN_A_RESULT,
				TEST.apply(
						new Ranges.Range(5, 10),
						new Ranges.Range(6, 9)
				)
		);
		assertEquals(
				B_IN_A_RESULT,
				TEST.apply(
						new Ranges.Range(5, 10),
						new Ranges.Range(5, 9)
				)
		);
		assertEquals(
				B_IN_A_RESULT,
				TEST.apply(
						new Ranges.Range(5, 10),
						new Ranges.Range(6, 10)
				)
		);
		assertEquals(
				B_IN_A_RESULT,
				TEST.apply(
						new Ranges.Range(5, 10),
						new Ranges.Range(5, 10)
				)
		);
	}

	@Test
	public void testBLeftInA() {
		assertEquals(
				B_LEFT_IN_A_RESULT,
				TEST.apply(
						new Ranges.Range(3, 4),
						new Ranges.Range(1, 3)
				)
		);
		assertEquals(
				B_LEFT_IN_A_RESULT,
				TEST.apply(
						new Ranges.Range(3, 5),
						new Ranges.Range(1, 4)
				)
		);
	}

	@Test
	public void testAInsideB() {
		assertEquals(
				A_IN_B_RESULT,
				TEST.apply(
						new Ranges.Range(6, 9),
						new Ranges.Range(5, 10)
				)
		);
		assertEquals(
				A_IN_B_RESULT,
				TEST.apply(
						new Ranges.Range(5, 9),
						new Ranges.Range(5, 10)
				)
		);
		assertEquals(
				A_IN_B_RESULT,
				TEST.apply(
						new Ranges.Range(6, 10),
						new Ranges.Range(5, 10)
				)
		);
		assertEquals(
				A_IN_B_RESULT,
				TEST.apply(
						new Ranges.Range(5, 10),
						new Ranges.Range(5, 10)
				)
		);
	}

	@Test
	public void testBLeftOutA() {
		assertEquals(
				A_LEFT_OUT_B_RESULT,
				TEST.apply(
						new Ranges.Range(3, 4),
						new Ranges.Range(1, 2)
				)
		);
	}
}
