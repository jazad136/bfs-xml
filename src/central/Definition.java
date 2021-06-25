package central;

import org.w3c.dom.Node;

/**
 * Source code for the Definition class. Helps keep track of w3c.DOM nodes read in from XML files. 
 * @author jsaddle
 *
 */
public class Definition {
	public static Definition emptyDef = new Definition("");
	public final boolean isEmpty;
	public final boolean hasNode;
	private Node node;
	private String stringRep;
	public Definition(String defString) { 
		stringRep = defString;
		node = null;
		hasNode = false;
		isEmpty = defString.isBlank();
	}
	/** Constructs a definition based on a org.w3c.dom.Node object
	 *  referencing a Node used in an XML document.
	 */
	public Definition(Node defNode) { 
		stringRep = defNode.getNodeType() == Node.ELEMENT_NODE || defNode.getNodeType() == Node.ATTRIBUTE_NODE 
				? defNode.getNodeName() : defNode.getTextContent();
		node = defNode;
		hasNode = true;
		isEmpty = false;
	}
	
	public Node getNode() { return node; } 
	public String toString() { return stringRep; } 
}
