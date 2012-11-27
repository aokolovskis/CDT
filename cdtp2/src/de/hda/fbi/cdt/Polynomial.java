package de.hda.fbi.cdt;

import java.util.Arrays;

public class Polynomial {
	private int[] coefficients;
	private int degree;
	private int field;

	/**
	 * Constructor for array of pairs a[0] * x^b[0] + a[1] * x^b[1] + ...
	 * 
	 * @param a
	 *            coefficients
	 * @param b
	 *            exponents
	 * @param field
	 */
	public Polynomial(int[] a, int[] b, int field) {
		if (a.length == b.length) {
			if (this.validateField(a, field)) {

				this.degree = degree(b);
				this.field = field;
				this.coefficients = new int[this.degree + 1];
				for (int i = 0; i < b.length; i++) {
					this.coefficients[b[i]] = a[i];
				}
			} else {
				// TODO: throw exception or some such.
			}
		} else {
			// TODO: throw exception or some such.
		}
	}

	/**
	 * Constructor for single pair a * x^b
	 * 
	 * @param a
	 *            coefficient
	 * @param b
	 *            exponent
	 * @param field
	 *            FiniteField restriction
	 */
	public Polynomial(int a, int b, int field) {
		if (a < field) {
			this.degree = b;
			this.field = field;
			this.coefficients = new int[this.degree + 1];
			this.coefficients[b] = a;
		} else {
			// TODO: throw exception or some such.
		}
	}

	/**
	 * Check if input is valid
	 * 
	 * @param a
	 *            array of coefficients
	 * @param field
	 *            FiniteField restriction
	 * @return true if all elements are within bounds of field
	 */
	private boolean validateField(int[] a, int field) {
		boolean valid = true;
		for (int i = 0; i < a.length; i++) {
			if (a[i] >= field) {
				valid = false;
				break;
			}
		}
		return valid;
	}

	/**
	 * @param poly
	 * @return poly MOD FiniteField restriction
	 */
	private Polynomial modField(Polynomial poly) {
		// a[0] * x^b[0] + a[1] * x^b[1] + ... MOD field
		for (int i = 0; i < poly.coefficients.length; i++) {
			poly.coefficients[i] %= poly.field;
		}
		return poly;
	}

	private int degree(int[] b) {
		int deg = 0;
		for (int i = 0; i < b.length; i++) {
			if (b[i] > deg) {
				deg = b[i];
			}
		}
		return deg;
	}

	/**
	 * Build new polynomial by calculating a(x)+b(x) mod field
	 * 
	 * @param poly
	 * @return polynomial
	 */
	public Polynomial plus(Polynomial poly) {
		Polynomial a = this;
		Polynomial b = poly;

		Polynomial c = new Polynomial(0, Math.max(
				a.degree, b.degree), this.field);
		if (a.field == b.field) {

			for (int i = 0; i <= a.degree; i++) {
				c.coefficients[i] += a.coefficients[i];
			}

			for (int i = 0; i <= b.degree; i++) {
				c.coefficients[i] += b.coefficients[i];
			}

			c = this.modField(c);

		} else {
			// TODO: throw exception or some such.
		}

		return c;
	}

	/**
	 * Build new polynomial by calculating a(x)-b(x) mod field
	 * 
	 * @param poly
	 * @return polynomial
	 */
	public Polynomial minus(Polynomial poly) {
		Polynomial a = this;
		Polynomial b = poly;

		Polynomial c = new Polynomial(0, Math.max(
				a.degree, b.degree), this.field);
		if (a.field == b.field) {

			for (int i = 0; i <= a.degree; i++) {
				c.coefficients[i] += a.coefficients[i];
			}

			for (int i = 0; i <= b.degree; i++) {
				c.coefficients[i] -= b.coefficients[i];
			}

			c = this.modField(c);

		} else {
			// TODO: throw exception or some such.
		}

		return c;
	}

	/**
	 * Build new polynomial by calculating a(x)*b(x) mod field
	 * 
	 * @param poly
	 * @return polynomial
	 */
	public Polynomial times(Polynomial poly) {
		Polynomial c = null;
		if (poly != null) {
			if (poly.field == this.field) {
				if ((poly.degree != 0) && (this.degree != 0)) {					
					Polynomial a = this;
					Polynomial b = poly;
					int degnew = a.degree + b.degree;
					c = new Polynomial(0, degnew, a.field);

					// a[0] * x^b[0] + a[1] * x^b[1] + ...
					for (int i = 0; i < a.degree + 1; i++) {
						for (int j = 0; j < b.degree + 1; j++) {
							c.coefficients[i + j] += (a.coefficients[i] * b.coefficients[j]);
							c.degree = a.degree + b.degree;
						}
					}

					// a[0] * x^b[0] + a[1] * x^b[1] + ... MOD field
					c = this.modField(c);

				} else {
					c = new Polynomial(0, 0,
							this.field); // 0-Polynomial
					// ->
					// degree
					// =
					// 0;
				}
			} else {
				// TODO: throw exception or some such.
			}
		} else {
			// TODO: throw exception or some such.
		}

		return c;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = this.degree; i > 1; i--) {
			if (this.coefficients[i] != 0) {
				if (this.coefficients[i] != 1) {
					sb.append(this.coefficients[i]).append('x').append('^')
							.append(i).append('+');
				} else {
					sb.append('x').append('^').append(i).append('+');
				}
			}
		}
		if (this.coefficients[1] != 0) {
			sb.append(this.coefficients[1]).append("x+");
		}
		if (this.coefficients[0] != 0) {
			sb.append(this.coefficients[0]);
		} else {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append(" mod ").append(this.field).append(" degree=")
				.append(this.degree);
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(coefficients);
		result = prime * result + degree;
		result = prime * result + field;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Polynomial other = (Polynomial) obj;
		if (!Arrays.equals(coefficients, other.coefficients))
			return false;
		if (degree != other.degree)
			return false;
		if (field != other.field)
			return false;
		return true;
	}

}
