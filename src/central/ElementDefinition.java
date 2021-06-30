package central;

import org.w3c.dom.Node;

public class ElementDefinition extends Definition {
	/** The text content of the ElementDefinition: to be set after 
	 *  the adoption process is complete. 
	 */
	String textContent;
	/** The name of the element of the node passed to the constructor */
	String elementName;
	
	/**
	 * Constructor for the ElementDefinition class. 
	 * ElementDefinitions must take a node as a constructor parameter for future use. 
	 */
	public ElementDefinition(Node node) { 
		super(node); 
		textContent = "";
		elementName = node.getNodeName();
	}
	
	/** The method that defines the adoption process of this element<br/>
	 *  This method is optimized for the default condition where we just want to 
	 *  add to the text content of this node. However, for more complex nodes, it is expected
	 *  that this method will be overridden by subclasses of ElementDefinition.<br/>
	 *  Rules:<br/>
	 *   add to the string if we have a space (+ no quotes)<br/> 
	 *	 add to the string if we have an element (+ brackets [] around the name )<br/>
	 *	 add to the string if we have text content (+ no quotes) <br/> 
	 */
	public void adopt(Definition otherDef) {
		if(otherDef.getNode().getNodeType() == Node.TEXT_NODE)
			textContent += otherDef.getNode().getTextContent();
		else if(otherDef.getNode().getNodeType() == Node.ELEMENT_NODE) 
			textContent += String.format(" [%s]", otherDef.getNode().getNodeName()); 
	}
	/** 
	 * The method that defines the string representation of this element. 
	 */
	public String toString() { return String.format("%s element [%s]", elementName, textContent.replaceAll("\n", " ")); }
	public boolean isAdoptingDefinition() {  return true;  }
}
