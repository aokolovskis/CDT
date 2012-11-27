import javax.swing.text.StyledEditorKit.BoldAction;


public class Node implements Element{
	boolean hasChild = false;
	Node leftNode = null;
	Node rightNode = null;
	String character = "";
	double probability = 0 ;
	
	
	public Node (Element e) {
		this.character = e.getCharacter();
		this.probability = e.getProbability();
		hasChild = false;
	}

	public Node(Node leftNode, Node rightNode) {
		super();
		this.leftNode = leftNode;
		this.rightNode =rightNode;
		probability = leftNode.getProbability() + rightNode.getProbability();
		hasChild = true;
	}

	@Override
	public String getCharacter() {
		// TODO Auto-generated method stub
		return character;
	}

	@Override
	public double getProbability() {
		// TODO Auto-generated method stub
		return probability;
	}
	
	public Node getLeftNode() {
		return leftNode;
	}


	public Node getRightNode() {
		return rightNode;
	}


	public boolean hasChild() {
		return hasChild;
	}
	
	
	
}
