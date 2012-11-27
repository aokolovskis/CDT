import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class HuffmanBuilder {

	private interface Callback {
		void execture(String s, Node n);
	}

	SortedSet<Node> sortedNodes = null;

	public HuffmanBuilder(List<Element> elements) {

		sortedNodes = new TreeSet<Node>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				// Gleich nur dann wenn das object gleich ist
				// wenn beide gleich sind wird das 2 als groe§ere betrachetet
				if (o1.equals(o2)) {
					return 0;
				} else if (o1.getProbability() == o2.getProbability()) {
					return o1.hashCode() < o2.hashCode() ? -1 : 1;
				} else
					return o1.getProbability() < o2.getProbability() ? -1 : 1;
			}
		});

		for (Element element : elements) {
			sortedNodes.add(new Node(element));
		}

		// sortArrayList();

		double check_p = 0;
		for (Node node : sortedNodes) {
			check_p += node.getProbability();
		}

		if (check_p != 1)
			System.out.println("Error!! Probability is not 1! " + check_p);

		while (sortedNodes.size() > 1) {
			Node first = sortedNodes.first();
			sortedNodes.remove(first);
			Node second = sortedNodes.first();
			sortedNodes.remove(second);
			sortedNodes.add(new Node(first, second));

		}

	}

	public void traveToString() {
		// traverse(sortedNodes.first(),new String());

	}

	private void traverse(Node node, String s, Callback c) {

		if (node.hasChild) {
			traverse(node.getRightNode(), s + 1, c);
			traverse(node.getLeftNode(), s + 0, c);
		} else {
			c.execture(s, node);
		}
	}

	public Map<String, String> getEncodeMap() {
		final Map<String, String> encodeMap = new HashMap<String, String>();
		traverse(sortedNodes.first(), "", new Callback() {

			@Override
			public void execture(String s, Node n) {
				encodeMap.put(n.getCharacter(), s);
			}
		});
		return encodeMap;
	}

	public Map<String, String> getDecodeMap() {
		final Map<String, String> decodeMap = new HashMap<String, String>();
		traverse(sortedNodes.first(), "", new Callback() {

			@Override
			public void execture(String s, Node n) {
				decodeMap.put(s, n.getCharacter());
			}
		});
		return decodeMap;
	}

	/*
	 * private void sortArrayList() { Collections.sort(sortedNodes, new
	 * Comparator<Node>() {
	 * 
	 * @Override public int compare(Node o1, Node o2) { // Gleich nur dann wenn
	 * das object gleich ist // wenn beide gleich sind wird das 2 als groe§ere
	 * betrachetet if (o1.equals(o2)) { return 0; } else return
	 * o1.getProbability() < o2.getProbability() ? -1 : 1; } }); }
	 * 
	 * private void instertSorted(ArrayList<Node> list, Node node) { if
	 * (list.size() == 0) { list.add(node); } else if (list.size() == 1) { if
	 * (list.get(0).getProbability() < node.getProbability()) list.add(node);
	 * else list.add(0, node); } else insertSorted(list, node, 0, list.size());
	 * }
	 * 
	 * private void insertSorted(ArrayList<Node> list, Node node, int begin, int
	 * end) {
	 * 
	 * }
	 */

}