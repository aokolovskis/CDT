import java.util.Map;

public class Decoder {
	Map<String, String> lookup;

	public Decoder(Map lookupmap) {
		lookup = lookupmap;
	}

	public String decode(String stringTodecode) {
		StringBuilder sb = new StringBuilder();
		
		int i = 0;
		while (i < stringTodecode.length()) {
			String find = null;
			String s = null;
			for (int j = 1; i + j <= stringTodecode.length(); j++) {
				find = stringTodecode.substring(i, i + j);
				s = lookup.get(find);
				if (s != null) {
					sb.append(s);
					i += j;
					break;
				} else if (i+j == stringTodecode.length()) {
					throw new RuntimeException("Decoding error! " + find
							+ " not found in decoding Table" + "machted " + sb.toString());
				}
			}

		}
		return sb.toString();
	}
}
