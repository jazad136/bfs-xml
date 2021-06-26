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

public class IterateBFS {
	
	public ArrayList<Definition> allDefs;
	public BFSVisitor bfsv;
	public Document xmlDoc;
	static Definition lastSibling = Definition.emptyDef;
	int nodes;
	
	public IterateBFS(Document xmlDoc) { this.xmlDoc = xmlDoc; }
	
	
	public static void errorOut(Exception e) { 
		System.err.println(e.getClass().getSimpleName() + ":");
		System.err.println(e.getLocalizedMessage());
		System.err.println("at\t" + ErrorOut.someStackTrace(4, e));
	}
	
	public void startXMLBFS() { 
		allDefs = new ArrayList<>();
		bfsv = new BFSVisitor(xmlDoc);
	}
	
	public Definition continueBFS(Definition nextDef) { 
		if(nextDef.isEmpty) 
			return Definition.emptyDef;
		else if(xmlDoc.compareDocumentPosition(nextDef.getNode()) == 0) { 
			allDefs.add(nextDef);
			return Definition.emptyDef;
		}
		nodes++;
		nextDef = nextDef.respecify();
		allDefs.get(bfsv.parent).adopt(nextDef);
		allDefs.add(nextDef);
		return allDefs.get(bfsv.parent);
	}
	
	public boolean continueXMLBFS(Definition nextDef) { 
		if(nextDef.isEmpty) 
			return false;
		else if(xmlDoc.compareDocumentPosition(nextDef.getNode()) == 0) { 
			allDefs.add(nextDef);
			return true;
		}
		nodes++;
		nextDef = nextDef.respecify();
		allDefs.get(bfsv.parent).adopt(nextDef);
		allDefs.add(nextDef);
		return true;
	}
	
	public class BFSVisitor { 
		Queue Q;
		int lastParent;
		int parent; 
		boolean newParent;
		ArrayList<Definition> parents;
		public BFSVisitor(Document xmlDoc) {
			parents = new ArrayList<>();
			Q = new Queue();
			Q.enqueue(new Definition(xmlDoc));
			Q.enqueue(IterateBFS.lastSibling);
			lastParent = parent = -1;
		}
		
		public boolean parentIsReadyOld() {return parent > 0 && allDefs.get(parent).isAdoptingDefinition(); }
		public boolean parentIsReady2() {return parents.isEmpty() && parents.get(0).isAdoptingDefinition(); }
		public boolean parentIsReady3() {return lastParent == parent; }
		public Definition bfsVisit() {
			lastParent = parent;			
			while(!Q.isEmpty() && Q.first().isEmpty()) { 
				parent++;
				parents.remove(0);
				Q.dequeue();
			}
			if(Q.isEmpty())
				return new Definition("");
			Definition next = Q.dequeue();
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
