import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entropie {
	String s = null;
	List<Element> elements = null;
	double entropie = 0;

	public Entropie(String text) {
		s = text;
	}

	public List<Element> generateProbabilty() {
		if (elements != null) {
			return elements;
		}
		elements = new ArrayList<Element>();
		Map<String, Double> probabilityMap = new HashMap<String, Double>();
		double increment = 1 / (double) s.length();
		for (int i = 0; i < s.length(); i++) {
			String m_char = s.substring(i, i + 1);
			if (probabilityMap.containsKey(m_char)) {
				double count = probabilityMap.get(m_char) + increment;
				probabilityMap.put(m_char, count);
			} else {
				probabilityMap.put(m_char, increment);
			}
		}
		for (Map.Entry<String, Double> _entry : probabilityMap.entrySet()) {
			final Map.Entry<String, Double> entry = _entry;
			elements.add(new Element() {
				@Override
				public double getProbability() {
					return entry.getValue();
				}

				@Override
				public String getCharacter() {
					return entry.getKey();
				}
			});
		}
		return elements;
	}

	public double calcutleEntropie() {
		if (elements == null) {
			generateProbabilty();
		}
		if (entropie != 0) {
			return entropie;
		}
		for (Element e : elements) {
			entropie += e.getProbability()
					* (-1D * (Math.log(e.getProbability()) / Math.log(2)));
		}
		return entropie;
	}
}
