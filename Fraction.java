/**
* Simple Java Fraction class, which provides basic arithmetic operations
* @author Rushy Panchal
* @version 0.2
*/

public class Fraction implements Comparable<Fraction> {
	// Private instance fields
	private int numerator, denominator;
	private int sign; // sign of the fraction
	private double rational;

	// Constructors

	/**
	* Instantiate the fraction
	* @param num Numerator of the Fraction
	* @param denom Denominator of the Fraction
	* preconditions: denom cannot be 0
	*/
	private void construct(int num, int denom) {
		// This method is needed to prevent code duplication, for the String/Double constructors
		if (denom == 0) {
			throw new ArithmeticException("Denominator cannot be 0"); // thou shalt not divide by 0
			}
		this.numerator = num;
		this.denominator = denom;
		this.rational = (double) num / denom;
		this.simplify();
		}

	/**
	* Instantiate with both a numerator and denominator
	* @param num Numerator of the Fraction
	* @param denom Denominator of the Fraction
	* preconditions: denom cannot be 0
	*/
	public Fraction(int num, int denom) {
		this.construct(num, denom);
		}

	/**
	* No parameters (default constructor)
	*/
	public Fraction() {
		this(0, 1);
		}

	/**
	* Instantiate with just a numerator
	* @param num Numerator of the Fraction
	*/
	public Fraction(int num) {
		this(num, 1);
		}

	/**
	* Instantiate the Fraction with a rational input
	* @param rational The rational to use for the Fraction
	*/
	public Fraction(double rational) {
		// This method does not account for integer overflow, which is definitely possible
		String[] parts = new String[2];
		parts = Double.toString(rational).split("\\.");
		int numDecimalPlaces = parts[1].length();
		int base = (int) Math.pow(10, numDecimalPlaces); // convert the double to an integer without rounding
		this.construct((int) (rational * base), base);
		}
 
 	/**
	* Instantiate with a string representation of a fraction
	* @param fraction String representation of the fraction
	*/
	public Fraction(String fraction) {
		String[] parts = new String[2];
		parts = fraction.split("/");
		int num = Integer.parseInt(parts[0]);
		int denom =  Integer.parseInt(parts[1]);
		this.construct(num, denom);
		}

	// Public methods

	/**
	* Get the current numerator
	* @return int as the current numerator
	*/
	public int getNumerator() {
		return this.numerator * this.sign;
		}

	/**
	* Get the current denominator
	* @return int as the current denominator
	*/
	public int getDenominator() {
		return this.denominator;
		}

	/**
	* Get the current sign
	* @return int as the current sign
	*/
	public int getSign() {
		return this.sign;
		}

	/**
	* Finds the reciprocal of the fraction
	* @return Fraction as the reciprocal of the current Fraction
	*/
	public Fraction reciprocal() {
		int newNum = this.denominator;
		int newDenom = this.getNumerator();
		Fraction recip = new Fraction(newNum, newDenom);
		return recip;
		}

	/**
	* Negates a fraction
	* @return Fraction as the negated current fraction
	*/
	public Fraction negate() {
		int newNum = this.getNumerator() * -1;
		int newDenom = this.denominator;
		Fraction negated = new Fraction(newNum, newDenom);
		return negated;
		}

	// Mathematical public methods

	/**
	* Multiply two fractions
	* @param other The multiplier
	* @return Fraction as the product of the two multipliers
	*/
	public Fraction multiply(Fraction other) {
		int newNum = this.getNumerator() * other.getNumerator();
		int newDenom = this.denominator * other.getDenominator();
		Fraction product = new Fraction(newNum, newDenom);
		return product;
		}

	/**
	* Multiply a fraction by a floating-point number
	* @param other The multiplier
	* @return Fraction as the product of the two multipliers
	*/
	public Fraction multiply(double other) {
		Fraction otherFraction = new Fraction(other);
		return this.multiply(otherFraction);
		}

	/**
	* Divide the current fraction by another
	* @param other The divisor
	* @return Fraction as the ratio of two fractions
	*/
	public Fraction divide(Fraction other) {
		Fraction ratio = this.multiply(other.reciprocal());
		return ratio;
		}
	
	/**
	* Divide the current fraction by a floating-point number
	* @param other The divisor
	* @return Fraction as the ratio of the fractions
	*/
	public Fraction divide(double other) {
		Fraction otherFraction = new Fraction(other);
		return this.divide(otherFraction);
		}

	/**
	* Add two fractions
	* @param other The addend
	* @return Fraction as the sum of the two fractions
	*/
	public Fraction add(Fraction other) {
		int otherDenom = other.getDenominator();
		int leastCommonDenom = this.lcd(this.denominator, otherDenom); // calculate the Least Common Denominator to utilize
		int thisFactor = leastCommonDenom / this.denominator; // ratio of the LCD to the current denominator
		int otherFactor = leastCommonDenom / otherDenom; // ratio of the LCD to the "other" denominator
		int currentNum = this.getNumerator() * thisFactor;
		int otherNum = other.getNumerator() * otherFactor;
 		int newNum = currentNum + otherNum;
		Fraction sum = new Fraction(newNum, leastCommonDenom);
		return sum;
		}

	/**
	* Adds a fraction with a floating-point number
	* @param other The addend
	* @return Fraction as the sum of the fraction and number
	*/
	public Fraction add(double other) {
		Fraction otherFraction = new Fraction(other * this.denominator);
		return this.add(otherFraction);
		}

	/**
	* Subtracts two fractions
	* @param other The subtractor
	* @return Fraction as the difference of two fractions
	*/
	public Fraction subtract(Fraction other) {
		Fraction difference = this.add(other.negate());
		return difference;
		}

	/**
	* Subtracts a fraction by a floating-point number
	* @param other The subtractor
	* @return Fraction as the difference of the fraction and the number
	*/
	public Fraction subtract(double other) {
		Fraction otherFraction = new Fraction(other * this.denominator);
		return this.subtract(otherFraction);
		}

	/**
	* Absolute value of the fraction
	* @return Fraction as the absolute value of the current fraction
	*/
	public Fraction abs() {
		Fraction absFraction = new Fraction(this.numerator, this.denominator);
		return absFraction;
		}

	/**
	* Sine of the fraction in radians
	* @return double as the sin(numerator/denominator)
	*/
	public double sin() {
		return Math.sin(this.rational);
		}

	/**
	* Cosine of the fraction in radians
	* @return double as the cos(numerator/denominator)
	*/
	public double cos() {
		return Math.cos(this.rational);
		}

	/**
	* Tangent of the fraction in radians
	* @return double as the tan(numerator/denominator)
	*/
	public double tan() {
		return Math.tan(this.rational);
		}

	/**
	* Arc-Sine of the fraction in radians
	* @return double as the asin(numerator/denominator)
	*/
	public double asin() {
		return Math.asin(this.rational);
		}

	/**
	* Arc-Cosine of the fraction in radians
	* @return double as the acos(numerator/denominator)
	*/
	public double acos() {
		return Math.acos(this.rational);
		}

	/**
	* Arc-tangent of the fraction in radians
	* @return double as the atan(numerator/denominator)
	*/
	public double atan() {
		return Math.atan(this.rational);
		}

	/**
	* Hyperbolic Sine of the fraction in radians
	* @return double as the sinh(numerator/denominator)
	*/
	public double sinh() {
		return Math.sinh(this.rational);
		}

	/**
	* Hyperbolic Cosine of the fraction in radians
	* @return double as the cosh(numerator/denominator)
	*/
	public double cosh() {
		return Math.cosh(this.rational);
		}

	/**
	* Hyperbolic Tangent of the fraction in radians
	* @return double as the tanh(numerator/denominator)
	*/
	public double tanh() {
		return Math.tanh(this.rational);
		}

	/**
	* Fraction raised to a power
	* @param power Power to raise the fraction to
	* @return double as Fraction^power
	*/
	public double pow(double power) {
		return Math.pow(this.rational, power);
		}

	/**
	* Square-root of the fraction
	* @return double as sqrt(Fraction)
	*/
	public double sqrt() {
		return Math.sqrt(this.rational);
		}

	/**
	* Natural Logarithm of the fraction
	* @return double as natural-log(Fraction)
	*/
	public double logn() {
		return Math.log(this.rational);
		}

	/**
	* Logarithm (base 10) of the fraction
	* @return double as log-10(Fraction)
	*/
	public double log10() {
		return Math.log10(this.rational);
		}

	/**
	* Logarithm of a fraction with arbitrary base
	* @param base Base to use in the logarithm
	* @return double as log-base(Fraction)
	*/
	public double log(double base) {
		return this.logn() / Math.log(base);
		}

	// Comparable Interface (comparison operators)

	/**
	* Compares two fractions
	* @param other The fraction to compare to
	* @return int, positive if this > other, negative if other < less, otherwise 0 if equal
	*/
	public int compareTo(Fraction other) {
		Fraction difference = this.subtract(other);
		return (difference.toDouble() == 0 ? 0: difference.getSign());
		}

	/**
	* Compares a fraction to a floating-point number
	* @param other The double to compare to
	* @return int, positive if this > other, negative if other < less, otherwise 0 if equal
	*/
	public int compareTo(double other) {
		Fraction difference = this.subtract(other);
		return (difference.toDouble() == 0 ? 0: difference.getSign());
		}

	/**
	* Checks if the fraction is equal to another
	* @param other The fraction to compare to
	* @return boolean as whether or not the two fractions are equal
	*/
	public boolean equals(Fraction other) {
		return (this.compareTo(other) == 0);
		}

	/**
	* Checks if the fraction is equal to a floating-point number
	* @param other The double to compare to
	* @return boolean as whether or not the fraction and double are equal
	*/
	public boolean equals(double other) {
		return (this.compareTo(other) == 0);
		}

	/**
	* Checks if the fraction is greater than another
	* @param other The fraction to compare to
	* @return boolean as whether or not the current fraction is greater than the other
	*/
	public boolean greaterThan(Fraction other) {
		return (this.compareTo(other) > 0);
		}

	/**
	* Checks if the fraction is greater than a floating-number
	* @param other The double to compare to
	* @return boolean as whether or not the current fraction is greater than the double
	*/
	public boolean greaterThan(double other) {
		return (this.compareTo(other) > 0);
		}

	/**
	* Checks if the fraction is less than another
	* @param other The fraction to compare to
	* @return boolean as whether or not the current fraction is less than the other
	*/
	public boolean lessThan(Fraction other) {
		return (this.compareTo(other) < 0);
		}

	/**
	* Checks if the fraction is less than a floating-point number
	* @param other The double to compare to
	* @return boolean as whether or not the current fraction is less than the double
	*/
	public boolean lessThan(double other) {
		return (this.compareTo(other) < 0);
		}

	/**
	* Checks if the fraction is greater than or equal to another
	* @param other The fraction to compare to
	* @return boolean as whether or not the current fraction is greater than or equal to another
	*/
	public boolean geq(Fraction other) {
		return (this.compareTo(other) >= 0);
		}

	/**
	* Checks if the fraction is greater than or equal to a floating-point number
	* @param other The double to compare to
	* @return boolean as whether or not the current fraction is greater than or equal to the double
	*/
	public boolean geq(double other) {
		return (this.compareTo(other) >= 0);
		}

	/**
	* Checks if the fraction is less than or equal to another
	* @param other The fraction to compare to
	* @return boolean as whether or not the current fraction is less than or equal to another
	*/
	public boolean leq(Fraction other) {
		return (this.compareTo(other) <= 0);
		}

	/**
	* Checks if the fraction is less than or equal to a floating-point number
	* @param other The double to compare to
	* @return boolean as whether or not the current fraction is less than or equal to the double
	*/
	public boolean leq(double other) {
		return (this.compareTo(other) <= 0);
		}

	// Convert to different classes and/or datatypes

	/**
	* Convert the fraction to a string
	* @return String representation of the fraction as a String
	*/
	public String toString() {
		int num = this.getNumerator();
		int denom = this.denominator;
		if (num == 0) {
			return "0";
			}
		else if (denom == 1) {
			return String.format("%d", num);
			}
		else {
			return String.format("%d/%d", num, denom);
			}
		}
	
	/**
	* Convert the fraction to a double
	* @return double representation of the fraction as a double
	*/
	public double toDouble() {
		return this.rational;
		}

	/**
	* Convert the fraction to an integer
	* @return integer representation of the fraction as a double
	*/
	public int toInteger() {
		return (int) (this.rational);
		}

	/**
	* Interprets the fraction as radians and converts to degrees
	* @return double as the degree value of the radians
	*/
	public double toDegrees() {
		return Math.toDegrees(this.rational);
		}

	/**
	* Interprets the fraction as degrees and converts to radians
	* @return double as the radian value of the degrees
	*/
	public double toRadians() {
		return Math.toRadians(this.rational);
		}

	// Private methods
	
	/**
	* Simplify the fraction
	*/
	private void simplify() {
		this.sign = (this.numerator >= 0 ? 1: -1);
		this.sign *= (this.denominator >= 0 ? 1: -1); // total sign is the sign of the numerator times the sign of the denominator
		this.numerator = Math.abs(this.numerator);
		this.denominator = Math.abs(this.denominator);
		if (this.numerator > 0) { // if numerator IS 0, this.gcf raises an error
			int greatestCommonFactor = this.gcf(this.numerator, this.denominator);
			this.numerator /= greatestCommonFactor;
			this.denominator /= greatestCommonFactor;
			}
		}

	/**
	* Finds the Greatest Common Factor of two numbers
	* @param a First Integer
	* @param b Second Integer
	* @return int as the Greatest Common Factor
	*/
	private int gcf(int a, int b) {
		if (b == 0) {
			return a;
			}
		else {
			return this.gcf(b, a % b); // recursively get the gcf, using Euclid's Algorithm
			}
		}
	
	/**
	* Finds the Least Common Denominator of two numbers
	* @param a First Integer
	* @param b Second Integer
	* @return int as the Least Common Denominator
	*/
	private int lcd(int a, int b) {
		return (a * b) / this.gcf(a, b);
		}
	}
