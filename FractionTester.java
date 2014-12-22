// Rushy Panchal
// FractionTester.java

public class FractionTester {
	private static int numberTests = 0;

	public static void main(String[] args) {
		// Pre-computed values
		Fraction definiteSum = new Fraction(8, 48);
		Fraction definiteDifference = new Fraction(6, 48);
		Fraction definiteProduct = new Fraction(21 * 1, 144 * 48);
		Fraction definiteRatio = new Fraction(21 * 48, 144 * 1);
		Fraction[] definiteFractionArray = {new Fraction(0), new Fraction(1, 2), new Fraction(1), new Fraction(3, 2), new Fraction(2), new Fraction(5, 2), new Fraction(3), new Fraction(7, 2), new Fraction(4), new Fraction(9, 2)};

		// Setup, which also tests constructors implicitly
		Fraction f1 = new Fraction("21/144"); // change to Fraction(21, 144) if no string constructor
		Fraction f2 = new Fraction(1, 48);
		Fraction f3 = new Fraction(1.25); // change to Fraction(5, 4) if no double constructor
		Fraction f4 = new Fraction();
		Fraction f5 = new Fraction(73);

		Fraction toAdd = new Fraction(1, 2);
		Fraction max = new Fraction(5);

		// Just-in-time computed values
		Fraction sum = f1.add(f2);
		Fraction difference = f1.subtract(f2);
		Fraction product = f1.multiply(f2);
		Fraction ratio = f1.divide(f2);

		// UNIT TESTS BEGIN HERE

		// Constructors + simplification
		equals(f1, new Fraction(7, 48));
		equals(f2, new Fraction(1, 48));
		equals(f3, new Fraction(5, 4));
		equals(f4, new Fraction(0, 1));
		equals(f5, new Fraction(73, 1));

		// Comparison operators
		equals(difference.equals(definiteDifference), true);
		equals(sum.greaterThan(difference), true);
		equals(product.greaterThan(1), false);
		equals(product.geq(0), true);
		equals(product.leq(0), false);
		equals(product.compareTo(product), 0);

		// Class conversions
		equals(f1.toString(), "7/48");
		equals(f1.toDouble(), 7.0 / 48);
		equals(f1.toInteger(), 0);

		// Main mathematical operations
		equals(sum, definiteSum);
		equals(difference, definiteDifference);
		equals(product, definiteProduct);
		equals(ratio, definiteRatio);

		// Addition and lessThan (and thus, compareTo)

		for (Fraction f = new Fraction(0); f.lessThan(max); f = f.add(toAdd)) {
			int index = (int) (2 * f.toDouble());
			equals(f, definiteFractionArray[index]);
			}

		print(String.format("All %d tests passed!", numberTests)); // will not print if there is an error
		}

	public static void equals(double a, double b) {
		numberTests++;
		if (a == b) {
			return;
			}
		else {
			error(a, b);
			}
		}

	public static void equals(Fraction f, Fraction g) {
		numberTests++;
		if (f.getNumerator() == g.getNumerator() && f.getDenominator() == g.getDenominator()) {
			return;
			}
		else {
			error(f, g);
			}
		}

	public static void equals(Boolean a, Boolean b) {
		equals(a ? 1: 0, b ? 1: 0);
		}

	public static void equals(Object a, Object b) {
		numberTests++;
		if (a.equals(b)) {
			return;
			}
		else {
			error(a, b);
			}
		}

	public static void error(Object a, Object b) {
		throw new AssertionError(String.format("\n%s != %s", a, b));
		}

	public static Boolean isString(Object o) {
		return o.getClass().equals(String.class);
		}

	public static void print(Object o) {
		System.out.println(o);
		}
	}
