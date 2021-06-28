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

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 * Source code for the IterateBFS class. <br>
 * This class when provided a w3c DOM document object, will allow the programmer to traverse
 * an XML file and return subtree items of the root node in breadth-first search order. <br>
 * <br>
 * Steps to accomplish this include:<br>
 * 1. Declaring a new IterateBFS object passing the Document as a parameter
 * 2. Calling the startXMLBFS() method to create the necessary support data structures. 
 * 3. Calling the continueBFS() method to iteratively return Definitions that contain XML Node objects
 * from the XML document object model. 
 * 
 * @author Jonathan A. Saddler
 *
 */
public class IterateBFS {
	
	
	private ArrayList<Definition> allDefs;
	public BFSVisitor bfsv;
	private Document xmlDoc;
	public static Definition lastSibling = Definition.emptyDef;
	int nodes;
	
	/** Constructor for the IterateBFS object. */
	public IterateBFS(Document xmlDoc) { this.xmlDoc = xmlDoc; }
	
	
	/** Print an error from a perceived exception within this project */
	public static void errorOut(Exception e) { 
		System.err.println(e.getClass().getSimpleName() + ":");
		System.err.println(e.getLocalizedMessage());
		System.err.println("at\t" + ErrorOut.someStackTrace(4, e));
	}
	
	/** Start the XML BFS process by setting up the necessary support structures */
	public void startXMLBFS() { 
		allDefs = new ArrayList<>();
		bfsv = new BFSVisitor(xmlDoc);
	}
	
	/** Return true if the definition passed in to this method is considered a method
	 * in need of adoption from the tree. This method must be called on definitions returned
	 * from {@link BFSVisitor##bfsVisit()} to help advance the iteration properly down 
	 * the breadth first tree*/
	public boolean continueXMLBFS(Definition nextDef) { 
		if(nextDef.isEmpty) 
			return false;
		else if(xmlDoc.compareDocumentPosition(nextDef.getNode()) == 0) { 
			allDefs.add(nextDef);
			return false;
		}
		nodes++;
		nextDef = nextDef.respecify();
		allDefs.get(bfsv.parent).adopt(nextDef);
		allDefs.add(nextDef);
		return true;
	}
	
	/** Source for the BFSVisitor internal class. */
	public class BFSVisitor { 
		Queue Q;
		int lastParent;
		int parent; 
		boolean newParent;
		int readyParent;
		ArrayList<Definition> parents;
		public BFSVisitor(Document xmlDoc) {
			parents = new ArrayList<>();
			Q = new Queue();
			Q.enqueue(new Definition(xmlDoc));
			Q.enqueue(IterateBFS.lastSibling);
			lastParent = parent = -1;
			readyParent = -1;
		}
		
		public boolean parentIsReady() {return parent > 0 && allDefs.get(parent).isAdoptingDefinition(); }
		public boolean parentIsReady2() {return parents.isEmpty() && parents.get(0).isAdoptingDefinition(); }
		public boolean parentIsReady3() {return lastParent == parent; }
		public Definition readyParent() { 
			newParent = false;
			return allDefs.get(parent); 
		}
		
		public Definition bfsVisit() {
			lastParent = parent;			
			while(!Q.isEmpty() && Q.first().isEmpty()) { 
				if(parentIsReady()) { 
					newParent = true;
					readyParent = parent;
				}
				parent++;
				Q.dequeue();
			}
			if(Q.isEmpty())
				return new Definition("");
			Definition next = Q.dequeue();
			next = next.respecify();
			Q.enqueueAll(adjacent(next));
			Q.enqueue(IterateBFS.lastSibling);
			parents.add(next);
			return next;
		}
		
		public boolean bfsDone() { return Q.isEmpty(); } 
		
		public List<Definition> adjacent(Definition parent) { 
			ArrayList<Definition> toReturn = new ArrayList<>();
			NodeList nl = parent.getNode().getChildNodes();
			for(int i = 0; i < nl.getLength(); i++) 
				toReturn.add(new Definition(nl.item(i)));
			if(parent.getNode().hasAttributes()) { 
				NamedNodeMap al = parent.getNode().getAttributes();
				for(int i = 0; i < al.getLength(); i++) 
					toReturn.add(new Definition(nl.item(i)));
			}
			return toReturn;
		}
	}
	
	public boolean bfsDone() { return bfsv.bfsDone(); }
	
}
