package central;

import java.util.Iterator;

import org.w3c.dom.Document;

public class XMLIterator implements Iterator<Definition> {
	IterateBFS itController;
	private Definition nextDef;
	public XMLIterator(Document xmlDoc) { 
		itController = new IterateBFS(xmlDoc); 
		itController.startXMLBFS();
		nextDef = new Definition("");
	}
	public boolean hasNext() { return !itController.bfsDone(); } 
	
	public boolean lastSibling() { return itController.lastSibling(); }
	public boolean fullyExploredParent() { 
		return itController.hasLastParent() && itController.lastSibling(); 
	}
	
	public Definition next() { 
		nextDef = itController.bfsVisit();
		boolean useful = itController.continueXMLBFS(nextDef);
		while(!useful && !itController.bfsDone()) {
			nextDef = itController.bfsVisit();
			useful = itController.continueXMLBFS(nextDef);
		}
		return nextDef; 
	}
	
	public boolean parentIsReady() { return itController.bfsv.parentIsReady(); }
//	public Definition readyParent() { return itController.bfsv.readyParent(); } 
	public Definition getParent() { return itController.lastParent(); } 
	public boolean hasLastParent() { return itController.hasLastParent(); } 
}
