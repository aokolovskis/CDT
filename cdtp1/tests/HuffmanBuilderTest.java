import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HuffmanBuilderTest {
	final double DELTA = 1 / 10000000000D;

	private class Elementimpt implements Element {

		String Character;
		double p;

		public Elementimpt(String Character, double p) {
			this.Character = Character;
			this.p = p;
		}

		@Override
		public String getCharacter() {
			// TODO Auto-generated method stub
			return Character;
		}

		@Override
		public double getProbability() {
			// TODO Auto-generated method stub
			return p;
		}

	}

	@Test
	public void entropieTest() {
		Entropie en = new Entropie("adefg");
		for (Element e : en.generateProbabilty()) {
			assertEquals(1 / 5D, e.getProbability(), 0);
		}

		en = new Entropie("aaa");
		for (Element e : en.generateProbabilty()) {
			assertEquals(1, e.getProbability(), 0);
		}

		en = new Entropie("aaa");
		for (Element e : en.generateProbabilty()) {
			assertEquals(1, e.getProbability(), 0);
		}

	}

	@Test
	public void entropieTest2() {
		Entropie en = new Entropie(
				"es war einmal eine kleine suesse dirn, die hatte jedermann\n"
						+ "lieb, der sie nur ansah, am allerliebsten aber ihre grossmutter,\n"
						+ "die wusste gar nicht, was sie alles dem kind geben sollte.\n"
						+ "einmal schenkte sie ihm ein kaeppchen von rothem sammet,\n"
						+ "und weil ihm das so wohl stand, und es nichts anders mehr\n"
						+ "tragen wollte, hiess es nur das rohtkaeppchen.\n");
		double sum = 0;
		for (Element e : en.generateProbabilty()) {
			System.out.println("" + Double.toString(e.getProbability()) + " "
					+ e.getCharacter());
			sum += e.getProbability();

		}
		assertEquals(1, sum, DELTA);
		System.out.println("sum" + sum);
	}

	@Test
	public void entropieTest3() {
		String text = "aaaaaaaaaaaaaaabbbbbbbccccccddddddeeeee";
		Entropie en = new Entropie(text);
		double sum = 0;
		for (Element e : en.generateProbabilty()) {
			double p = e.getProbability();
			if (e.getCharacter().equals("a")) {
				assertEquals(15 / 39D, p, DELTA);
			} else if (e.getCharacter().equals("b")) {
				assertEquals(7 / 39D, p, DELTA);
			} else if (e.getCharacter().equals("c")) {
				assertEquals(6 / 39D, p, DELTA);
			} else if (e.getCharacter().equals("d")) {
				assertEquals(6 / 39D, p, DELTA);
			} else if (e.getCharacter().equals("e")) {
				assertEquals(5 / 39D, p, DELTA);
			}
			sum += p;
		}
		assertEquals(1, sum, DELTA);

		assertEquals(2.186, en.calcutleEntropie(), 1 / 1000D);

		HuffmanBuilder hb = new HuffmanBuilder(en.generateProbabilty());

		Encoder encoder = new Encoder(hb.getEncodeMap());
		Decoder decoder = new Decoder(hb.getDecodeMap());
		assertEquals(text, decoder.decode(encoder.encode(text)));

		assertEquals(encoder.getAvgBits(), en.calcutleEntropie(), 1 / 10D);

	}

	@Test
	public void entropieTest4() {
		String text = "es war einmal eine kleine suesse dirn, die hatte jedermann\n"
				+ "lieb, der sie nur ansah, am allerliebsten aber ihre grossmutter,\n"
				+ "die wusste gar nicht, was sie alles dem kind geben sollte.\n"
				+ "einmal schenkte sie ihm ein kaeppchen von rothem sammet,\n"
				+ "und weil ihm das so wohl stand, und es nichts anders mehr\n"
				+ "tragen wollte, hiess es nur das rohtkaeppchen.\n";
		Entropie en = new Entropie(text);

		HuffmanBuilder hb = new HuffmanBuilder(en.generateProbabilty());

		Encoder encoder = new Encoder(hb.getEncodeMap());
		Decoder decoder = new Decoder(hb.getDecodeMap());
		assertEquals(text, decoder.decode(encoder.encode(text)));
		System.out.println(encoder.encode(text));
		assertEquals(encoder.getAvgBits(), en.calcutleEntropie(), 1 / 10D);

	}
	
	@Test
	public void entropieTest5() {
		String text = "abc";
		Entropie en = new Entropie(text);

		HuffmanBuilder hb = new HuffmanBuilder(en.generateProbabilty());

		Encoder encoder = new Encoder(hb.getEncodeMap());
		Decoder decoder = new Decoder(hb.getDecodeMap());
		assertEquals("bc", decoder.decode(encoder.encode("bc")));

		assertEquals(encoder.getAvgBits(), en.calcutleEntropie(), 1 / 100D);

	}
	
	@Test
	public void entropieTest6() {
		String text = new Data().sb.toString();
		Entropie en = new Entropie(text);

		HuffmanBuilder hb = new HuffmanBuilder(en.generateProbabilty());

		Encoder encoder = new Encoder(hb.getEncodeMap());
		Decoder decoder = new Decoder(hb.getDecodeMap());
		assertEquals(text, decoder.decode(encoder.encode(text)));

		assertEquals(encoder.getAvgBits(), en.calcutleEntropie(), 1 / 100D);

	}
	

	@Test
	public void insertSortedTest() {
		/*
		 * ArrayList<Element> elements = new ArrayList<Element>();
		 * elements.add(new Elementimpt("a",0.3)); HuffmanBuilder hb = new
		 * HuffmanBuilder(elements);
		 * 
		 * hb.insertSorted(hb.sortedNodes, new Node(new Elementimpt("a",0.3)));
		 * hb.insertSorted(hb.sortedNodes, new Node(new Elementimpt("a",0.3)));
		 * hb.insertSorted(hb.sortedNodes, new Node(new Elementimpt("a",0.4)));
		 * hb.insertSorted(hb.sortedNodes, new Node(new Elementimpt("a",0.5)));
		 * hb.insertSorted(hb.sortedNodes, new Node(new Elementimpt("a",0.5)));
		 * System.out.println("----------------------------------");
		 */
	}

	@Test
	public void NodeTest() {
		Node node = new Node(new Elementimpt("a", 1));
		assertEquals(node, node);
		SortedSet<Node> nodes = new TreeSet<Node>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				// Gleich nur dann wenn das object gleich ist
				// wenn beide gleich sind wird das 2 als groe�ere betrachetet
				if (o1.equals(o2)) {
					return 0;
				} else
					return o1.getProbability() < o2.getProbability() ? -1 : 1;
			}
		});
		assertEquals(0, nodes.size());
		nodes.add(node);
		assertEquals(1, nodes.size());
		nodes.remove(node);
		assertEquals(0, nodes.size());
		nodes.add(node);
		assertEquals(1, nodes.size());
		nodes.remove(nodes.first());
		assertEquals(0, nodes.size());
		nodes.add(node);
		assertEquals(1, nodes.size());
		nodes.first();
		assertEquals(1, nodes.size());

	}

	@Test
	public void ConstructorTest() {
		ArrayList<Element> elements = new ArrayList<Element>();
		elements.add(new Elementimpt("e", 0.35));
		elements.add(new Elementimpt("c", 0.15));
		elements.add(new Elementimpt("a", 0.10));
		elements.add(new Elementimpt("b", 0.15));
		elements.add(new Elementimpt("f", 0.25));

		HuffmanBuilder hb = new HuffmanBuilder(elements);

		java.util.List<Element> list = new ArrayList<Element>();

		Encoder encoder = new Encoder(hb.getEncodeMap());
		Decoder decoder = new Decoder(hb.getDecodeMap());

		// assertEquals("11", encoder.encode("e"));
		// assertEquals("00", encoder.encode("b"));

		for (Map.Entry<String, String> e : hb.getEncodeMap().entrySet()) {
			System.out.println(e.getKey() + " " + e.getValue());
		}

		String encodeDecode = "ecabf";

		assertEquals(encodeDecode, decoder.decode(encoder.encode(encodeDecode)));

		boolean thrown = false;

		try {
			assertEquals(encodeDecode,
					decoder.decode(encoder.encode(encodeDecode + "g") + 1));

		} catch (RuntimeException e) {
			thrown = true;
		}

		assertEquals("Too bad", true, thrown);

		thrown = false;

		try {
			assertEquals(encodeDecode,
					decoder.decode(encoder.encode(encodeDecode) + 1));

		} catch (RuntimeException e) {
			thrown = true;
		}

		assertEquals("Too bad", true, thrown);
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

}
