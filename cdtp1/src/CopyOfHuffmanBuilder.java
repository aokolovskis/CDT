import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CopyOfHuffmanBuilder {

	public ArrayList<Node> sortedNodes = new ArrayList<Node>();

	public CopyOfHuffmanBuilder(List<Element> elements) {
		for (Element element : elements) {
			sortedNodes.add(new Node(element));
		}

		sortArrayList();

		double check_p = 0;
		for (Node node : sortedNodes) {
			check_p += node.getProbability();
			System.out.println(node.getCharacter() + " "
					+ node.getProbability());
		}
		System.out.println(check_p);

		while (sortedNodes.size() > 1) {
			Node first = sortedNodes.get(0);
			System.out.println("found: " + sortedNodes.remove(first));
			Node second = sortedNodes.get(0);
			sortedNodes.remove(second);
			// sortedNodes.add(new Node(first, second));
			// insertSorted(sortedNodes, new Node(first, second));
		}
	}

	private void sortArrayList() {
		Collections.sort(sortedNodes, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				// Gleich nur dann wenn das object gleich ist
				// wenn beide gleich sind wird das 2 als groe§ere betrachetet
				if (o1.equals(o2)) {
					return 0;
				} else
					return o1.getProbability() < o2.getProbability() ? -1 : 1;
			}
		});
	}

	public void insertSorted(ArrayList<Node> list, Node node) {
		insertSorted(list, node, 0, list.size() - 1);
		System.out.println("---------");
		for (Node node_ : list) {

			System.out.println(node_.getCharacter() + " "
					+ node_.getProbability());
		}
		System.out.println("-_-_-----");
	}

	private void insertSorted(ArrayList<Node> list, Node node, int begin,
			int end) {

		if (begin == end) {
			list.add(begin, node);
		} else if (end - begin == 1) {
			if (list.get(begin).getProbability() < node.getProbability())
				list.add(begin, node);
			else
				list.add(end, node);
		} else {

			int center = begin + end / 2;
			double node_Probability = node.getProbability();
			double center_node_Probability = list.get(center).getProbability();
			if (node_Probability == center_node_Probability) {
				list.add(center, node);
				return;
			} else if (node_Probability < center_node_Probability) {
				end = center;
			} else {
				begin = center;
			}
			insertSorted(list, node, begin, end);
		}

	}
}
