import java.util.Map;

public class Encoder {
	Map<String, String> lookup;
	double avgbits = 0;

	public Encoder(Map lookupmap) {
		lookup = lookupmap;
	}

	public String encode(String stringToEncode) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < stringToEncode.length(); i++) {
			String find = stringToEncode.substring(i, i + 1);
			String s = lookup.get(find);
			if (s != null)
				sb.append(s);
			else
				throw new RuntimeException("Encoding error! " + find
						+ " not found in encoding Table");
	//		System.out.println(find);
	//		System.out.println(sb.toString());
		}

		avgbits = sb.length() / (double) stringToEncode.length();

	//	System.out.println(sb.length());
	//	System.out.println(stringToEncode.length());
		
		return sb.toString();
	}

	public double getAvgBits() {
		return avgbits;
	}
}
