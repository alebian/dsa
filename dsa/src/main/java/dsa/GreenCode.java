package dsa;

import java.lang.Math;

public class GreenCode {
	private static final String RED = "red";
	private static final String YELLOW = "yellow";
	private static final String GREEN = "green";

	private static final int MAX_RAM_MB = 32_768;

	public static String verify(Float[] cpuUsage, Integer[] usedMemory) {
		Rule[] rules = new Rule[7];
		rules[0] = new Rule1();
		rules[1] = new Rule2();
		rules[2] = new Rule3();
		rules[3] = new Rule4();
		rules[4] = new Rule5();
		rules[5] = new Rule6();
		rules[6] = new Rule7();

		boolean[] valid = new boolean[7];

		for (int i = 0; i < Math.max(cpuUsage.length, usedMemory.length); i++) {
			for (Rule rule : rules) {
				rule.test(cpuUsage, usedMemory, i);
			}
		}

		for (int i = 0; i < rules.length; i++) {
			valid[i] = rules[i].valid();
		}

		// Rule5 is false or Rule7 is false
		if (!valid[4] || !valid[6]) {
			valid[0] = false;
			valid[1] = false;
		}

		// Rule6 is false or Rule7 is false
		if (!valid[5] || !valid[6]) {
			valid[2] = false;
			valid[3] = false;
		}

		int falseRules = 0;
		for (int i = 0; i < valid.length; i++) {
			if (!valid[i]) {
				falseRules++;
			}
		}

		if (falseRules == 0) {
			return GREEN;
		} else if (falseRules == 1) {
			return YELLOW;
		} else {
			return RED;
		}
	}

	private interface Rule {
		boolean valid();
		void test(Float[] cpuUsage, Integer[] usedMemory, int index);
	}

	private static class Rule1 implements Rule {
		private static final float MAX_CPU_ALLOWED = 0.9f;

		private boolean valid = true;

		@Override
		public boolean valid() {
			return valid;
		}

		@Override
		public void test(Float[] cpuUsage, Integer[] usedMemory, int index) {
			if (index < cpuUsage.length && cpuUsage[index] != null && cpuUsage[index] >= MAX_CPU_ALLOWED) {
				valid = false;
			}
		}
	}

	private static class Rule2 implements Rule {
		boolean size1 = false;

		float maxCpuValue = Float.MIN_VALUE;
		int maxCpuPosition = -1;
		int maxMemoryValue = Integer.MIN_VALUE;
		int maxMemoryPosition = -1;

		@Override
		public boolean valid() {
			return size1 || maxCpuPosition != maxMemoryPosition;
		}

		@Override
		public void test(Float[] cpuUsage, Integer[] usedMemory, int index) {
			if (cpuUsage.length == 1 && usedMemory.length == 1) {
				size1 = true;
				return;
			}

			if (index < cpuUsage.length && cpuUsage[index] != null) {
				if (cpuUsage[index] > maxCpuValue) {
					maxCpuValue = cpuUsage[index];
					maxCpuPosition = index;
				}
			}

			if (index < usedMemory.length && usedMemory[index] != null) {
				if (usedMemory[index] > maxMemoryValue) {
					maxMemoryValue = usedMemory[index];
					maxMemoryPosition = index;
				}
			}
		}
	}

	private static class Rule3 implements Rule {
		private static final double MAX_MEMORY_ALLOWED = 60.0;

		private boolean valid = true;

		@Override
		public boolean valid() {
			return valid;
		}

		@Override
		public void test(Float[] cpuUsage, Integer[] usedMemory, int index) {
			if (index < usedMemory.length && usedMemory[index] != null) {
				double memoryUsagePercentage = usedMemory[index] * 100.0 / MAX_RAM_MB;
				if (memoryUsagePercentage > MAX_MEMORY_ALLOWED) {
					valid = false;
				}
			}
		}
	}

	private static class Rule4 implements Rule {
		private boolean valid = true;

		@Override
		public boolean valid() {
			return valid;
		}

		@Override
		public void test(Float[] cpuUsage, Integer[] usedMemory, int index) {
			if (index - 2 < 0 || index < usedMemory.length) {
				return;
			}

			if (usedMemory[index - 2] != null && usedMemory[index - 1] != null && usedMemory[index] != null) {
				boolean increases = (usedMemory[index - 2] < usedMemory[index - 1]) && (usedMemory[index - 1] < usedMemory[index]);
				if (increases) {
					valid = false;
				}
			}
		}
	}

	private static class Rule5 implements Rule {
		private static final double MAX_INVALID_CPU_POINTS_ALLOWED = 30.0;

		private int invalidCpuCount = 0;
		private int totalCpuCount = 0;

		@Override
		public boolean valid() {
			double invalidCpuPercentage = invalidCpuCount * 100.0 / totalCpuCount;
			return invalidCpuPercentage < MAX_INVALID_CPU_POINTS_ALLOWED;
		}

		@Override
		public void test(Float[] cpuUsage, Integer[] usedMemory, int index) {
			totalCpuCount = cpuUsage.length;

			if (index < cpuUsage.length && cpuUsage[index] == null) {
				invalidCpuCount++;
			}
		}
	}

	private static class Rule6 implements Rule {
		private static final double MAX_INVALID_MEMORY_POINTS_ALLOWED = 25.0;

		private int invalidMemoryCount = 0;
		private int totalMemoryCount = 0;

		@Override
		public boolean valid() {
			double invalidCpuPercentage = invalidMemoryCount * 100.0 / totalMemoryCount;
			return invalidCpuPercentage < MAX_INVALID_MEMORY_POINTS_ALLOWED;
		}

		@Override
		public void test(Float[] cpuUsage, Integer[] usedMemory, int index) {
			totalMemoryCount = usedMemory.length;

			if (index < usedMemory.length && usedMemory[index] == null) {
				invalidMemoryCount++;
			}
		}
	}

	private static class Rule7 implements Rule {
		private boolean valid = true;

		@Override
		public boolean valid() {
			return valid;
		}

		@Override
		public void test(Float[] cpuUsage, Integer[] usedMemory, int index) {
			if (index < cpuUsage.length && index < usedMemory.length && cpuUsage[index] == null && usedMemory[index] == null) {
				valid = false;
			}
		}
	}
}
