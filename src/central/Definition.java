/*******************************************************************************
 *    Copyright (c) 2021 Jonathan A. Saddler 
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *    
 *    Contributors:
 *     Jonathan A. Saddler
 *******************************************************************************/
package central;

import org.w3c.dom.Node;
import static org.w3c.dom.Node.*;
/**
 * Source code for the Definition class in the bfs-xml project. A Definition helps keep track of w3c.DOM nodes 
 * read in from XML files
 * @author Jonathan A. Saddler
 *
 */
public class Definition {
	/** Template for the empty definition object. */
	public static Definition emptyDef = new Definition("");
	/** is true if the string object passed to the string constructor is an empty string */
	public final boolean isEmpty;
	/** is true if some Node object was passed to the Node constructor */
	public final boolean hasNode;
	/** the Node object this definition helps represent, if it exists */
	private Node node;
	/** the default string representation of this node */
	private String stringRep;
	
	
	public Definition(String defString) { 
		stringRep = defString;
		node = null;
		hasNode = false;
		isEmpty = defString.trim().isEmpty();
	}
	/** Constructs a definition based on a org.w3c.dom.Node object
	 *  referencing a Node used in an XML document.
	 */
	public Definition(Node defNode) { 
		stringRep = defaultNodeStringRep(defNode);
		node = defNode;
		hasNode = true;
		isEmpty = false;
	}
	
	/**
	 * get the default string representation of the node encapsulated in this Definition. 
	 * For all other than elements attributes and root (document) XML nodes, returns the result
	 * of {@link Node##getTextContent()}, and for these three special node types, returns the node
	 * name. 
	 * @param defNode
	 * @return
	 */
	public final String defaultNodeStringRep(Node defNode) {
		switch(defNode.getNodeType()) { 
			case ELEMENT_NODE:
			case ATTRIBUTE_NODE: 
			case DOCUMENT_NODE: return defNode.getNodeName();
		}
		return defNode.getTextContent();
		
	}
	/** Retrieve the Node object this definition helps represent */
	public Node getNode() { return node; } 
	
	/** Prints the string representation of this Definition as defined by {@link Definition#defaultNodeStringRep(Node)}*/
	public String toString() { return stringRep; }
	
	/** Should be overridden to return true if the definition at stake is an "adopting" definition, or 
	 *  needs to pull information from child nodes in the XML file (including text child nodes) 
	 *  to be fully ready for use in downstream programs. 
	 */
	public boolean isAdoptingDefinition() { return false; }
	
	/** The method used to create fully functional downstream program definitions after BFS has considered
	 * 	the parent and all its children. 
	 */
	
	public void adopt(Definition child) {  } 
	
	/** Returns true if this node was provided an empty string to its String constructor. */
	public boolean isEmpty() { return isEmpty; } 
	
	/** Should be overridden to return different subtypes of definition depending on the desired
	 * 	behavior of picking up downstream node information. By default, attributes and elements
	 *  are designed to be created when we run across an element or attribute node. 
	 */
	public Definition respecify() {
		if(hasNode) {  
			switch(node.getNodeType()) { 
				case Node.ELEMENT_NODE: 
					return new ElementDefinition(node);
				case Node.ATTRIBUTE_NODE:
					return new AttributeDefinition(node);
			}
		}
		return this;
	}
}
