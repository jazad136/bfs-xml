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
	public boolean isAdoptingDefinition() { return false; }
	public Definition adopt(Definition child) { return this; } 
	public boolean isEmpty() { return isEmpty; } 
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
