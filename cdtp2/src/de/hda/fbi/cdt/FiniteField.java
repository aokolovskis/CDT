package de.hda.fbi.cdt;

public class FiniteField {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// a[0] * x^b[0] + a[1] * x^b[1]

		// p1, f(x) = x^2+x+1 mod 2
		int[] ap1 = { 1, 1, 1 }; //coefficients
		int[] bp1 = { 2, 1, 0 }; //degrees

		// p2, f(x) = x^3+x^2+1 mod 2
		int[] ap2 = { 1, 1, 1 };
		int[] bp2 = { 3, 2, 0 };
		
		int field12 = 2;

		// p5, f(x) = x^2+x+2 mod 5
		int[] ap5 = { 1, 1, 2 };
		int[] bp5 = { 2, 1, 0 };

		// p6, f(x) = x^3+3x+2 mod 5
		int[] ap6 = { 1, 3, 2 };
		int[] bp6 = { 3, 1, 0 };

		int field56 = 5;

		Polynomial a = new Polynomial(ap1, bp1, field12);
		Polynomial b = new Polynomial(ap2, bp2, field12);
		Polynomial c = a.times(b);
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		
		Polynomial d = new Polynomial(ap5, bp5, field56);
		Polynomial e = new Polynomial(ap6, bp6, field56);
		Polynomial f = d.times(e);
		System.out.println(d);
		System.out.println(e);
		System.out.println(f);
		
		System.out.println(a.plus(b));
		
		System.out.println(a.minus(b));
		

	}

}
